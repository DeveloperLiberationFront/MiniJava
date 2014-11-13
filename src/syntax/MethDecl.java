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

import notifications.NameClashDiagnostic;
import checker.Context;
import checker.VarEnv;


/** Provides a representation for a method declaration in a class.
 */
public class MethDecl extends Decls {
    private Type type;
    private Id id;
    private Formals formals;
    private Block body;
    private Boolean is_constructor;
    public MethDecl(Boolean is_constructor,
                    Modifiers mods,
                    Type type, Id id, Formals formals,
                    Block body) {
        super(mods);
        this.type    = type;
        this.id      = id;
        this.formals = formals;
        this.body    = body;
        this.is_constructor = is_constructor;

        if (is_constructor) {
            this.body.appendStatement(new Return(id.getPos(), new This(id.getPos())));
        }
    }

    public Block getBody() {
        return body;
    }
    /** Add a declared item to a specified class.
     */
    public void addToClass(Context ctxt, ClassType cls) {
        if (type != null) {
            type = type.check(ctxt);
        }

        VarEnv params = null;
        for (; formals != null; formals = formals.getNext()) {
            Id   paramId   = formals.getId();
            Type paramType = formals.getType().check(ctxt);
            VarEnv otherEnv = VarEnv.find(paramId.getName(), params);
			if (otherEnv != null) {
            	ctxt.report(new NameClashDiagnostic(paramId, otherEnv.getId()));
            }
            params = new VarEnv(paramId, paramType, params, this);
        }
        if (is_constructor) {
            type = cls;
        }

        cls.addMethod(ctxt, is_constructor, mods, id, type, params, body);
    }
}
