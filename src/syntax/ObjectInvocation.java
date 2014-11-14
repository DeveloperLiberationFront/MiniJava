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

import notifications.MissingFieldDiagnostic;
import notifications.MissingMethodDiagnostic;

import org.llvm.Builder;

import checker.Context;
import checker.MethEnv;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;

/** Represents an instance method invocation.
 */
public class ObjectInvocation extends Invocation {
    private Expression object;
    private String     name;
    private MethEnv    menv;

    public ObjectInvocation(Expression object, Id id, Args args) {
        super(id.getPos(), args);
        this.object = new NullCheck(pos, object);
        this.name   = id.getName();
    }

    /** Calculate the type of this method invocation.
     */
    Type typeInvocation(Context ctxt, VarEnv env)
    throws Diagnostic {
        Type receiver = object.typeOf(ctxt, env);
        ClassType cls = receiver.isClass();
        if (cls == null) {
        	throw new MissingFieldDiagnostic(this, ctxt.getCurrClass());
        } else if ((this.menv = cls.findMethod(name)) == null) {
        	throw new MissingMethodDiagnostic(this, ctxt.getCurrClass());
        }
        return checkInvocation(ctxt, env, this.menv);
    }

    /** Generate code for this method invocation, leaving
     *  the result in the specified free variable.
     */
    void compileInvocation(Assembly a, int free) {
        object.compileExpr(a, 0);
        menv.compileInvocation(a, args, free);
    }

    public Expression getObject() {
        return object;
    }
    /** Evaluate this expression.
     */
    public Value eval(State st) {
        return menv.call(st, object.eval(st).getObj(), args);
    }

    public org.llvm.Value llvmGen(LLVM l) {
        Builder b = l.getBuilder();
        org.llvm.Value func, method_this;
        if (!menv.isStatic()) {
            org.llvm.Value obj = object.llvmGen(l);
            org.llvm.Value vtable;
            if (menv.getOwner().isInterface() == null) {
                org.llvm.Value vtable_addr = b.buildStructGEP(obj, 0, "vtable_lookup");
                vtable = b.buildLoad(vtable_addr, "vtable");
            } else {
                vtable = menv.getOwner().getVtableLoc();
            }
            org.llvm.Value func_addr = b.buildStructGEP(vtable, menv.getSlot(),
                                       "func_lookup");
            func = b.buildLoad(func_addr, menv.getName());
            method_this = b.buildBitCast(obj,
                                         menv.getOwner().llvmType().pointerType(), "cast_this");

        } else {
            method_this = null;
            func = menv.getFunctionVal(l);
        }
        return llvmInvoke(l, menv, func, method_this);
    }
}
