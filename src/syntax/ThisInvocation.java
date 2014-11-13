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

import org.llvm.Builder;

import checker.Context;
import checker.MethEnv;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;

/** Represents a method invocation through this.
 */
public final class ThisInvocation extends Invocation {
    private MethEnv menv;
    private int     size;

    public ThisInvocation(Id id, Args args, MethEnv menv) {
        super(id.getPos(), args);
        this.menv = menv;
    }

    /** Calculate the type of this method invocation.
     */
    Type typeInvocation(Context ctxt, VarEnv env) throws Diagnostic {
        size = ctxt.getCurrMethod().getSize();
        return checkInvocation(ctxt, env, this.menv);
    }

    /** Generate code for this method invocation, leaving
     *  the result in the specified free variable.
     */
    void compileInvocation(Assembly a, int free) {
        if (!menv.isStatic()) {
            a.loadThis(size, 0);
        }
        menv.compileInvocation(a, args, free);
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        if (menv.isStatic()) {
            return menv.call(st, null, args);
        } else {
            return menv.call(st, st.getThis(size), args);
        }
    }

    public org.llvm.Value llvmGen(LLVM l) {
        Builder b = l.getBuilder();
        org.llvm.Value method_this;
        org.llvm.Value func;
        if (!menv.isStatic()) {
            org.llvm.Value obj = l.getFunction().getParam(0);
            org.llvm.Value vtable_addr =  b.buildStructGEP(obj, 0, "vtable_lookup");
            org.llvm.Value vtable = b.buildLoad(vtable_addr, "vtable");
            org.llvm.Value func_addr = b.buildStructGEP(vtable, menv.getSlot(),
                                       "func_lookup");
            func = b.buildLoad(func_addr, menv.getName());
            method_this = b.buildBitCast(obj, menv.getOwner().llvmType().pointerType(),
                                         "cast_this");
        } else {
            /* static method can just use the existing function name */
            method_this = null;
            func = menv.getFunctionVal(l);
        }

        return llvmInvoke(l, menv, func, method_this);
    }
}
