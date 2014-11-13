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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import notifications.IsDeclaration;
import notifications.LocalVarDeclTypeError;
import checker.Context;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;
import compiler.NameClashDiagnostic;
import compiler.Position;

/** Provides a representation for local variable declarations in a block.
 */
public class LocalVarDecl extends Statement implements IsDeclaration {
    private Type type;
    private VarDecls varDecls;
    private Block block;
    public LocalVarDecl(Position pos, Type type, VarDecls varDecls) {
        super(pos);
        this.type     = type;
        this.varDecls = varDecls;
    }

    public boolean check(Context ctxt, VarEnv env, int frameOffset) {
        return check(ctxt, env, frameOffset,
                     Collections.<Statement>emptyList().iterator());
    }
    /** Check whether this statement is valid and return a boolean
     *  indicating whether execution can continue at the next statement.
     */
    public boolean check(Context ctxt, VarEnv env, int frameOffset,
                         Iterator<Statement> iter) {
        ArrayList<Statement> assigns = new ArrayList<Statement>();

        type = type.check(ctxt);
        if (type != null) {
            int size = type.size();
            for (VarDecls vs = varDecls; vs != null; vs = vs.getNext()) {
                try {
                    Type init = null;
                    if (vs.getInitExpr() != null) {
                        init = vs.getInitExpr().typeOf(ctxt, env);
                    }
                    if (VarEnv.find(vs.getId().getName(), env) != null) {
                    	ctxt.report(new NameClashDiagnostic(vs.getId(), env));
                    } else if (init != null && !type.isSuperOf(init)) {
                    	ctxt.report(new LocalVarDeclTypeError(vs, type,
                    			vs.getInitExpr(), vs.getInitExpr().typeOf(ctxt, env)));
//                    	ctxt.report(new TypeError(, vs.getInitExpr(), vs));
//                    	ctxt.report(new TypeError(vs.getId(), new DummyVariableDeclaration(), vs.getInitExpr()));
                    } else {
                        frameOffset -= size;
                        VarEnv prev_env = env;
                        env = new VarEnv(vs.getId(), type, frameOffset, env, this);
                        Expression e;
                        if (vs.getInitExpr() != null) {
                            e = vs.getInitExpr();
                        } else if (type.isClass() != null) {
                            e = new CastExpr(vs.getId().getPos(), type,
                                             new NullLiteral(vs.getId().getPos()));
                        } else {
                            e = new CastExpr(vs.getId().getPos(), type, new IntLiteral(vs.getId().getPos(),
                                             0));
                        }
                        Statement s = new ExprStmt(vs.getId().getPos(),
                                                   new AssignExpr(vs.getId().getPos(), new FrameAccess(env), e));
                        assigns.add(s);
                        s.check(ctxt, prev_env, frameOffset);
                    }
                } catch (Diagnostic d) {
                	// catching, not generating code
                    ctxt.report(d);
                }
            }
            ctxt.reserveSpace(frameOffset);
        }
        block = new Block(pos, assigns.toArray(new Statement[0]));
        if (!iter.hasNext()) {
        	ctxt.report(new DeadCodeDiagnostic(this));
            return true;
        } else {
            Statement s = iter.next();
            return s.check(ctxt, env, frameOffset, iter);
        }
    }

    /** Emit code to execute this statement.
     */
    void compile(Assembly a) {
        block.compile(a);
    }

    public void llvmGen(LLVM l) {
        org.llvm.Builder b = l.getBuilder();
        for (VarDecls vs = varDecls; vs != null; vs = vs.getNext()) {
            org.llvm.Value v = b.buildAlloca(type.llvmTypeField(), vs.getId().getName());
            l.setNamedValue(type.isClass() != null, type.llvmTypeField(),
                            vs.getId().getName(), v);
            l.markGCRoot(v, type);
        }
        block.llvmGen(l);
    }


    /** Execute this statement.  If the statement is terminated by a
     *  return statement, return the corresponding value.  Otherwise,
     *  a null indicates that no result was returned.
     */
    public Value exec(State st) {
        block.exec(st);
        return null;
    }
}
