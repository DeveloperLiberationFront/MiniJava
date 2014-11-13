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

import checker.Context;
import checker.FieldEnv;
import checker.MethEnv;
import checker.VarEnv;

import compiler.Position;

/** Provides a representation for names, both simple and qualified.
 */
public class Name {
    private Name prefix;
    private Id id;

    /** Construct a name with a qualifying prefix.
     */
    public Name(Name prefix, Id id) {
        this.prefix = prefix;
        this.id     = id;
    }

    /** Construct a simple name with no qualifying prefix.
     */
    public Name(Id id) {
        this(null, id);
    }

    /** Returns the position of this syntactic element.
     */
    public Position getPos() {
        Name n = this;
        while (n.prefix != null) {
            n = n.prefix;
        }
        return n.id.getPos();
    }

    /** Generate a printable representation of a name.
     */
    public String toString() {
        if (prefix == null) {
            return id.toString();
        } else {
            return prefix + "." + id;
        }
    }

    public Id getId() {
        return id;
    }

    /** Lookup name as a class.
     */
    public ClassType asClass(Context ctxt) {
        if (prefix == null) {
            return ctxt.findClass(id.getName());
        }
        return null;
    }

    public InterfaceType asInterface(Context ctxt) {
        ClassType c = asClass(ctxt);
        if (c != null) {
            return c.isInterface();
        }
        return null;
    }
    /** Lookup name as a value.
     * @throws UnknownNameDiagnostic 
     */
    public FieldAccess asValue(Context ctxt, VarEnv env) throws UnknownNameDiagnostic {
        if (prefix == null) {
            VarEnv ve = VarEnv.find(id.getName(), env);
            if (ve != null) {
                return new FrameAccess(ve);
            }
            ClassType cls = ctxt.getCurrClass();
            FieldEnv fe;
            if (cls != null && (fe = cls.findField(id.getName())) != null) {
                return new SimpleAccess(id, fe);
            }
        } else {
            Expression object = prefix.asValue(ctxt, env);
            if (object != null) {
                return new ObjectAccess(object, id);
            }
            ClassType cls = prefix.asClass(ctxt);
            FieldEnv  fe;
            if (cls != null && (fe = cls.findField(id.getName())) != null) {
                return new ClassAccess(fe);
            }
            throw new UnknownNameDiagnostic(new Name(this.id), cls.getFields());
        }
        return null;
    }

    /** Lookup name as a method.
     */
    public Invocation asMethod(Context ctxt, VarEnv env, Args args) {
        if (prefix == null) {
            MethEnv menv = ctxt.getCurrClass().findMethod(id.getName());
            if (menv != null) {
                return new ThisInvocation(id, args, menv);
            }
        } else {
            Expression object = prefix.asValue(ctxt, env);
            if (object != null) {
                return new ObjectInvocation(object, id, args);
            }

            ClassType cls = prefix.asClass(ctxt);
            if (cls != null) {
                return new ClassInvocation(cls, id, args);
            }
        }
        return null;
    }
}
