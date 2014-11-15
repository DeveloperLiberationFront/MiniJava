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

import notifications.contracts.ImplicitTypeContract;
import codegen.Assembly;

import compiler.Position;

/** Provides a representation for expressions using binary operators.
 */
public abstract class BinaryOp extends Expression {
    protected Expression left;
    protected Expression right;
    private   int        depth;
	private ImplicitTypeContract typeContract;
    
    public ImplicitTypeContract getTypeContract(){
    	return this.typeContract;
    }

    public BinaryOp(Position pos, Expression left, Expression right) {
        super(pos);
        this.left  = left;
        this.right = right;
        this.depth = 1 + Math.max(left.getDepth(), right.getDepth());
    }

    public Expression getLeft() {
        return left;
    }
    public Expression getRight() {
        return right;
    }
    /** Return the depth of this expression tree as a measure of its
     *  complexity.  For binary operations, we precompute the depth
     *  when we construct the node as the maximum of the left and
     *  right depths, plus one.
     */
    int getDepth() {
        return depth;
    }

    /** Return a false value to indicate that, in general, binary
     *  operators are not commutative.
     */
    boolean commutes() {
        return false;
    }

    void compileOp(Assembly a, String op, int free) {
        if (left.getDepth() > right.getDepth() || right.getDepth() >= DEEP) {
            left.compileExpr(a, free);
            right.compileExprOp(a, op, free);
        } else if (commutes()) {
            right.compileExpr(a, free);
            left.compileExprOp(a, op, free);
        } else {
            right.compileExpr(a, free);
            a.spill(free + 1);
            left.compileExpr(a, free + 1);
            a.emit("xchgl", a.reg(free + 1), a.reg(free));
            a.emit(op,      a.reg(free + 1), a.reg(free));
            a.unspill(free + 1);
        }
    }

    void compileComp(Assembly a, String trueTest, int free) {
        compileOp(a, "cmpl", free);
        String l1 = a.newLabel();
        String l2 = a.newLabel();
        a.emit(trueTest, l1);
        a.emit("movl", a.immed(0), a.reg(free));
        a.emit("jmp", l2);

        a.emitLabel(l1);
        a.emit("movl", a.immed(1), a.reg(free));

        a.emitLabel(l2);
    }

    void branchCond(Assembly a, String test, String lab, int free) {
        compileOp(a, "cmpl", free);
        a.emit(test, lab);
    }
}
