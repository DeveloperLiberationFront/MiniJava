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

import interp.Value;

import org.llvm.binding.LLVMLibrary.LLVMIntPredicate;

import codegen.Assembly;

import compiler.Position;

/** Provides a representation for the equality test (==).
 */
public final class EqualExpr extends RelOpExpr {
    public EqualExpr(Position pos, Expression left, Expression right) {
        super(pos, left, right);
    }

    /** Generate code to evaluate this expression and
     *  leave the result in the specified free variable.
     */
    public void compileExpr(Assembly a, int free) {
        compileComp(a, "jz", free);
    }

    /** Generate code to evaluate this expression and
     *  branch to a specified label if the result is false.
     */
    void branchFalse(Assembly a, String lab, int free) {
        branchCond(a, "jnz", lab, free);
    }

    /** Generate code to evaluate this expression and
     *  branch to a specified label if the result is true.
     */
    void branchTrue(Assembly a, String lab, int free) {
        branchCond(a, "jz", lab, free);
    }

    public Value.COMPARE_OP getInterpRelOp() {
        return Value.COMPARE_OP.OP_EQ;
    }

    public LLVMIntPredicate getllvmRelOp() {
        return LLVMIntPredicate.LLVMIntEQ;
    }

}
