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

import compiler.*;
import checker.*;
import codegen.*;
import interp.*;

import org.llvm.BasicBlock;

/** Provides a representation for return statements.
 */
public final class Return extends Statement {
    private Expression result;

    public Return(Position pos, Expression result) {
        super(pos);
        this.result = result;
    }

    public Return(Position pos) {
        this(pos, null);
    }

	/** Check whether this statement is valid and return a boolean
     *  indicating whether execution can continue at the next statement.
     */
    public boolean check(Context ctxt, VarEnv env, int frameOffset) {
        Type rt = ctxt.getCurrMethod().getType();
        if (result != null) {
            if (rt != null) {
                try {
                    Type it = result.typeOf(ctxt, env);
                    if (!rt.isSuperOf(it)) {
                    	ctxt.report(new TypeError(this, rt)); // needs to point to return type of method declaration
                    } else if (rt != it) {
                        result = new CastExpr(pos, rt, result);
                    }
                } catch (Diagnostic d) {
                    ctxt.report(d);
                }
            } else {
            	ctxt.report(new TypeError(this, new DummyVariableDeclaration(),
            			ctxt.getCurrMethod().getDeclaration()));
            }
        } else if (rt != Type.VOID) {
        	ctxt.report(new TypeError(this, new DummyVariableDeclaration(),
        			ctxt.getCurrMethod().getDeclaration()));
        }


        return false;
    }

    /** Emit code to execute this statement.
     */
    void compile(Assembly a) {
        if (result != null) {
            result.compileExpr(a);
        }
        a.emitEpilogue();
    }

    /** Emit code that executes this statement and then branches
     *  to a specified label.
     */
    void compileThen(Assembly a, String lab) {
        compile(a);
    }

    /** Emit code that executes this statement and then returns from the
     *  current method.
     */
    public void compileRet(Assembly a) {
        compile(a);
    }

    /** Execute this statement.  If the statement is terminated by a
     *  return statement, return the corresponding value.  Otherwise,
     *  a null indicates that no result was returned.
     */
    public Value exec(State st) {
        return (result == null) ? Value.NULL : result.eval(st);
    }

    public void llvmGen(LLVM l) {
        if (result != null) {
            org.llvm.Value v = result.llvmGen(l);
            l.getBuilder().buildRet(v);
            /* this is a small hack to allow extra instructions after a return */
            /* without it, llvm complains that the block does not follow their desired form */
        } else {
            l.getBuilder().buildRetVoid();
        }
        BasicBlock new_block = l.getFunction().appendBasicBlock("after_ret");
        l.getBuilder().positionBuilderAtEnd(new_block);
    }
}
