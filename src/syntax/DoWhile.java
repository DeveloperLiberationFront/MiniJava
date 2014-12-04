/*
 * MiniJava Compiler - X86, LLVM Compiler/Interpreter for MiniJava.
 * Copyright (C) 2014, 2008 Mitch Souders, Mark A. Smith, Mark P. Jones
 *
 * MiniJava Compiler is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * MiniJava Compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MiniJava Compiler; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */


package syntax;

import interp.State;
import interp.Value;
import notifications.contracts.ImplicitTypeContract;
import notifications.thrownerrors.DoWhileTestTypeError;

import org.llvm.BasicBlock;
import org.llvm.Builder;

import checker.Context;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;
import compiler.Position;

/** Provides a representation for while statements.
 */
public final class DoWhile extends Statement {
    private Expression test;
    private Statement body;
    public DoWhile(Position pos, Expression test, Statement body) {
        super(pos);
        this.test = test;
        this.body = body;
    }
    
    public static ImplicitTypeContract getTypeContract() {
    	return new ImplicitTypeContract(Type.BOOLEAN); 
    }

    /** Check whether this statement is valid and return a boolean
     *  indicating whether execution can continue at the next statement.
     */
    public boolean check(Context ctxt, VarEnv env, int frameOffset) {
        try {
            Type testType = test.typeOf(ctxt, env);
			if (!testType.equal(Type.BOOLEAN)) {
            	ctxt.report(new DoWhileTestTypeError(test, testType, this));
//                ctxt.report(new TypeError(this.test, Type.BOOLEAN));
            }
        } catch (Diagnostic d) {
            ctxt.report(d);
        }
        return body.check(ctxt, env, frameOffset);
    }

    /** Emit code to execute this statement.
     */
    void compile(Assembly a) {
        String l1 = a.newLabel();
        a.emitLabel(l1);
        body.compile(a);
        test.branchTrue(a, l1);
    }

    /** Execute this statement.  If the statement is terminated by a
     *  return statement, return the corresponding value.  Otherwise,
     *  a null indicates that no result was returned.
     */
    public Value exec(State st) {
        Value v = null;
        do {
            v = body.exec(st);
        } while (v == null && test.eval(st).getBool());
        return v;
    }

    public void llvmGen(LLVM l) {
        Builder b = l.getBuilder();
        BasicBlock loopBody = l.getFunction().appendBasicBlock("do_body");
        BasicBlock loopEnd = l.getFunction().appendBasicBlock("do_end");
        b.buildBr(loopBody);

        b.positionBuilderAtEnd(loopBody);
        body.llvmGen(l);
        b.buildCondBr(test.llvmGen(l), loopBody, loopEnd);

        b.positionBuilderAtEnd(loopEnd);
    }

}
