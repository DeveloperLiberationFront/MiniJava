// Copyright (c) Mark P Jones, Portland State University
// Subject to conditions of distribution and use; see LICENSE for details
// February 3 2008 11:12 AM

package syntax;

import compiler.*;
import checker.*;
import codegen.*;
import interp.*;

/** Provides a representation for assignment expressions.
 */
public final class AssignExpr extends StatementExpr {
    private LeftHandSide lhs;
    private Expression rhs;

    public AssignExpr(Position pos, LeftHandSide lhs, Expression rhs) {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** Check this expression and return an object that describes its
     *  type (or throw an exception if an unrecoverable error occurs).
     */
    public Type typeOf(Context ctxt, VarEnv env)
      throws Diagnostic {
        Type lt = lhs.typeOf(ctxt, env);
        Type rt = rhs.typeOf(ctxt, env);
        if (!lt.isSuperOf(rt)) {
            throw new Failure(pos, "Cannot assign value of type " + rt +
                                   " to variable of type " + lt);
        }
        return lt;
    }

    /** Generate code to evaluate this expression and
     *  leave the result in the specified free variable.
     */ 
    public void compileExpr(Assembly a, int free) {
        rhs.compileExpr(a, free);
        lhs.saveVar(a, free);
    }

    /** Evaluate this expression.
     */
    public Value eval(State st) {
        Value val = rhs.eval(st);
        lhs.save(st, val);
        return val;
    }
}
