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
import codegen.Assembly;
import codegen.LLVM;

import compiler.Position;

/** Provides a representation for multiplication expressions.
 */
public final class ModExpr extends NumericOpExpr {
    public ModExpr(Position pos, Expression left, Expression right) {
        super(pos, left, right);
    }

    /** Return a true value to indicate that multiplication is commutative.
     */
    boolean commutes() {
        return false;
    }

    /** Generate code to evaluate this expression and
     *  leave the result in the specified free variable.
     */
    public void compileExpr(Assembly a, int free) {
        int orig_free = free;
        while (!a.reg(free).equals("%eax")) {
            free++;
        }
        a.spill(free);
        left.compileExpr(a, free);
        a.spill(free + 1);
        right.compileExpr(a, free + 1);
        a.emit("movl", a.immed(0), "%edx");
        a.emit("idivl", a.reg(free + 1));

        /* %edx will not be touched by this */
        a.unspillAll(orig_free);
        a.emit("movl", "%edx", a.reg(free));
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        return new IntValue(left.eval(st).getInt() % right.eval(st).getInt());
    }

    public org.llvm.Value llvmBuildOp(LLVM l, org.llvm.Value left,
                                      org.llvm.Value right) {
        return l.getBuilder().buildSRem(left, right, "modtemp");
    }
}
