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
import notifications.diagnostics.UnboundSuperCallDiagnostic;
import notifications.thrownerrors.ScopeAccessibilityError;
import notifications.thrownerrors.UnboundFieldNameOnSuperclassError;
import notifications.thrownerrors.UndeclaredSuperclassError;
import checker.Context;
import checker.FieldEnv;
import checker.VarEnv;
import codegen.Assembly;
import codegen.LLVM;
import compiler.Diagnostic;

/** Represents an access to a field through the superclass.
 */
public final class SuperAccess extends FieldAccess {
    private String   name;
    private FieldEnv env;
    private int      size;

    public SuperAccess(Id id) {
        super(id.getPos());
        this.name = id.getName();
    }

    /** Check this expression and return an object that describes its
     *  type (or throw an exception if an unrecoverable error occurs).
     */
    public Type typeOf(Context ctxt, VarEnv env)
    throws Diagnostic {
        ClassType sup = ctxt.getCurrClass().getSuper();
        if (sup == null) {
        	throw new UndeclaredSuperclassError(this, ctxt.getCurrClass().getDeclaration());
        } else if (ctxt.isStatic()) {
        	throw new ScopeAccessibilityError(this, ctxt.getCurrMethod(), null);
        } else if ((this.env = sup.findField(name)) == null) {
        	throw new UnboundFieldNameOnSuperclassError(this, sup);
        }
        this.env.accessCheck(ctxt, pos);
        size = ctxt.getCurrMethod().getSize();
        return this.env.getType();
    }

    /** Generate code to evaluate this expression and
     *  leave the result in the specified free variable.
     */
    public void compileExpr(Assembly a, int free) {
        a.loadThis(size, free);
        env.loadField(a, free);
    }

    /** Save the value in the free register in the variable specified by
     *  this expression.
     */
    void saveVar(Assembly a, int free) {
        a.spill(free + 1);
        a.loadThis(size, free + 1);
        env.saveField(a, free);
        a.unspill(free + 1);
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        return env.getField(st.getThis(size));
    }

    /** Save a value in the location specified by this left hand side.
     */
    public void save(State st, Value val) {
        env.setField(st.getThis(size), val);
    }

    public void llvmSave(LLVM l, org.llvm.Value r) {
        env.llvmStore(l, r, l.getFunction().getParam(0));
    }

    public org.llvm.Value llvmGen(LLVM l) {
        return env.llvmLoad(l, l.getFunction().getParam(0));
    }
}
