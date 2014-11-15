package notifications.thrownerrors;

import java.util.Collection;

import notifications.diagnostics.AccessibilityDiagnostic;

import syntax.Expression;
import syntax.MethDecl;
import checker.Context;
import checker.Env;

import compiler.Position;
import compiler.RichDiagnostic;

public class ScopeAccessibilityError extends RichDiagnostic implements
		AccessibilityDiagnostic {

	private Object valid;
	private Env currEnv;
	private Expression invocation;
	private Collection<MethDecl> validEnvironments;
	public ScopeAccessibilityError(Position pos, Context ctxt) {
		// TODO Auto-generated constructor stub
	}

	public ScopeAccessibilityError(Expression superAccess,
			Env currEnv, Collection<MethDecl> environments) {
		this.invocation = superAccess;
		this.currEnv = currEnv;
		this.validEnvironments = environments;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
