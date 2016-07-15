package notifications.thrownerrors;

import java.util.Collection;

import notifications.RichDiagnostic;
import notifications.diagnostics.AccessibilityDiagnostic;
import notifications.thrownerrors.interfaces.AccessibilityErrorInterface;
import syntax.Expression;
import syntax.MethDecl;
import checker.Context;
import checker.Env;
import compiler.Position;

public class ScopeAccessibilityError extends RichDiagnostic implements
		AccessibilityErrorInterface {

	private Object valid;
	private Env currEnv;
	private Expression invocation;
	private Collection<MethDecl> validEnvironments;
	private AccessibilityDiagnostic accessibilityDiagnostic;
	public ScopeAccessibilityError(Position pos, Context ctxt) {
		this.accessibilityDiagnostic = new AccessibilityDiagnostic(null, null, null, null);
	}

	public ScopeAccessibilityError(Expression superAccess,
			Env currEnv, Collection<MethDecl> environments) {
		this.invocation = superAccess;
		this.currEnv = currEnv;
		this.validEnvironments = environments;
		this.accessibilityDiagnostic = new AccessibilityDiagnostic(null, null, null, null);
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

	public AccessibilityDiagnostic getAccessibiltyDiagnostic() {
		return this.accessibilityDiagnostic;
	}
	
	
}
