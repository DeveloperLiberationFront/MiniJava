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
import checker.Context;
import checker.MethEnv;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;

/** Represents a class method invocation.
 */
public final class ClassInvocation extends Invocation {
    private ClassType cls;
    private String    name;
    private MethEnv   menv;

    public ClassInvocation(ClassType cls, Id id, Args args) {
        super(id.getPos(), args);
        this.cls  = cls;
        this.name = id.getName();
    }

    /** Calculate the type of this method invocation.
     */
    Type typeInvocation(Context ctxt, VarEnv env)
    throws Diagnostic {
        this.menv = cls.findMethod(name);
        if (this.menv == null) {
        	throw new MissingFieldDiagnostic(this, cls);
        } else if (!this.menv.isStatic()) {
        	throw new MissingFieldDiagnostic(this, cls); // missing information: what to call to get an instance
        }
        return checkInvocation(ctxt, env, this.menv);
    }

    /** Generate code for this method invocation, leaving
     *  the result in the specified free variable.
     */
    void compileInvocation(Assembly a, int free) {
        menv.compileInvocation(a, args, free);
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        return menv.call(st, null, args);
    }

    public org.llvm.Value llvmGen(LLVM l) {
        return llvmInvoke(l, menv, menv.getFunctionVal(l), null);
    }
}
