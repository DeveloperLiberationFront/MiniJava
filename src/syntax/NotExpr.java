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

import interp.BoolValue;
import interp.State;
import interp.Value;
import notifications.thrownerrors.NotExprError;
import checker.Context;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;
import compiler.Position;

/** Provides a representation for unary Boolean not.
 */
public final class NotExpr extends UnaryOp {
    public NotExpr(Position pos, Expression expr) {
        super(pos, expr);
    }

    /** Check this expression and return an object that describes its
     *  type (or throw an exception if an unrecoverable error occurs).
     */
    public Type typeOf(Context ctxt, VarEnv env) throws Diagnostic {
    	if (expr.typeOf(ctxt, env) != Type.BOOLEAN) {
    		ctxt.report(new NotExprError(expr, expr.typeOf(ctxt, env), this));
    	}
        return Type.BOOLEAN;
    }

    /** Generate code to evaluate this expression and
     *  leave the result in the specified free variable.
     */
    public void compileExpr(Assembly a, int free) {
        expr.compileExpr(a, free);
        a.emit("xorl", a.immed(1), a.reg(free));
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        return BoolValue.make(!expr.eval(st).getBool());
    }

    public org.llvm.Value llvmGen(LLVM l) {
        return l.getBuilder().buildNot(expr.llvmGen(l), "not_temp");
    }
}
