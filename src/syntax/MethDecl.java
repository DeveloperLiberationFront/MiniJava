// Copyright (c) Mark P Jones, Portland State University
// Subject to conditions of distribution and use; see LICENSE for details
// February 3 2008 11:12 AM

package syntax;

import compiler.*;
import checker.*;

/** Provides a representation for a method declaration in a class.
 */
public class MethDecl extends Decls {
    private Type type;
    private Id id;
    private Formals formals;
    private Statement body;
    public MethDecl(Modifiers mods,
                    Type type, Id id, Formals formals,
                    Statement body) {
        super(mods);
        this.type    = type;
        this.id      = id;
        this.formals = formals;
        this.body    = body;
    }

    /** Add a declared item to a specified class.
     */
    public void addToClass(Context ctxt, ClassType cls) {
        if (type!=null) {
            type = type.check(ctxt);
        }
        VarEnv params = null;
        for (; formals!=null; formals=formals.getNext()) {
            Id   paramId   = formals.getId();
            Type paramType = formals.getType().check(ctxt);
            if (VarEnv.find(paramId.getName(), params)!=null) {
                ctxt.report(new Failure(paramId.getPos(),
                                        "Multiple uses of parameter " +
                                        paramId));
            }
            params = new VarEnv(paramId, paramType, params);
        }
        cls.addMethod(ctxt, mods, id, type, params, body);
    }
}
