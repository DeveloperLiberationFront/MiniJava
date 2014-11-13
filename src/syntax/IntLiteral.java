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

import interp.IntValue;
import interp.State;
import interp.Value;
import checker.Context;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;
import compiler.Position;

/** Provides a representation for integer literals.
 */
public final class IntLiteral extends Literal {
    private int value;
    public int getValue() {
        return value;
    }
    public IntLiteral(Position pos, int value) {
        super(pos);
        this.value = value;
    }

    /** Check this expression and return an object that describes its
     *  type (or throw an exception if an unrecoverable error occurs).
     */
    public Type typeOf(Context ctxt, VarEnv env)
    throws Diagnostic {
        return Type.INT;
    }

    /** Generate code to evaluate this expression and
     *  leave the result in the specified free variable.
     */
    public void compileExpr(Assembly a, int free) {
        a.emit("movl", a.immed(value), a.reg(free));
    }

    void compileExprOp(Assembly a, String op, int free) {
        a.emit(op, a.immed(value), a.reg(free));
    }

    /** Generate code to evaluate this expression and
     *  branch to a specified label if the result is false.
     */
    void branchFalse(Assembly a, String lab, int free) {
        if (value == 0) {
            a.emit("jmp", lab);
        }
    }

    /** Generate code to evaluate this expression and
     *  branch to a specified label if the result is true.
     */
    void branchTrue(Assembly a, String lab, int free) {
        if (value != 0) {
            a.emit("jmp", lab);
        }
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        return new IntValue(value);
    }
    public org.llvm.Value llvmGen(LLVM l) {
        return Type.INT.llvmType().constInt(value, false);
    }
}
