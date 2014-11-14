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

import notifications.MissingMethodDiagnostic;
import notifications.ScopeAccessibilityError;
import checker.Context;
import checker.MethEnv;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;

import compiler.Diagnostic;
import compiler.Failure;
import compiler.Position;

/** Represents a method invocation through the superclass.
 */
public final class SuperInvocation extends Invocation {
    private String  name;
    private MethEnv menv;
    private int     size;
    private boolean unnamed;
    private boolean isFirst;

    public SuperInvocation(Position pos, Id id, Args args) {
        super(pos, args);
        this.unnamed = (id == null);
        this.name = null;
        this.isFirst = false;
        if (!unnamed) {
            this.name = id.getName();
        }
    }

    public void setFirst() {
        this.isFirst = true;
    }
    /** Calculate the type of this method invocation.
     */
    Type typeInvocation(Context ctxt, VarEnv env) throws Diagnostic {
        ClassType sup = ctxt.getCurrClass().getSuper();
        if (sup != null && unnamed) {
            this.name = sup.getId().getName();
        }
        if (sup == null) {
        	throw new ScopeAccessibilityError(this, ctxt.getCurrMethod(), null); // missing: ExtendsExpression 
        } else if (name == null) {
        	// really not sure about what this one does
            throw new Failure(pos, "No super constructor determined for super()");
        } else if (!isFirst && unnamed) {
            throw new Failure(pos,
            "Super constructor must be first instruction in constructor.");
        } else if (unnamed && ctxt.getCurrMethod() != null && !ctxt.getCurrMethod().isConstructor()) {
        	throw new ScopeAccessibilityError(this, ctxt.getCurrMethod(), new ArrayList<MethDecl>());
//            throw new Failure(pos, "Super constructor can only be in a constructor.");
        } else if (ctxt.isStatic()) {
        	throw new ScopeAccessibilityError(pos, ctxt); // missing: reason it won't work
        } else if ((this.menv = sup.findMethod(name)) == null) {
        	throw new MissingMethodDiagnostic(this, sup);
        }
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
        return menv.call(st, st.getThis(size), args);
    }

    public org.llvm.Value llvmGen(LLVM l) {
        return llvmInvoke(l, menv, menv.getFunctionVal(l), l.getFunction().getParam(0));
    }
}
