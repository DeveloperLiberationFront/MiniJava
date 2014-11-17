package notifications.thrownerrors;

import notifications.diagnostics.AccessibilityDiagnostic;
import syntax.ClassType;
import syntax.Modifiers;
import checker.MemberEnv;

import compiler.Position;

public class ExplicitAccessibilityContractError extends CompilerDiagnosticBuilder 
implements AccessibilityErrorInterface {

	private AccessibilityDiagnostic accessibilityDiagnostic;

	public ExplicitAccessibilityContractError(MemberEnv memberEnv,
			ClassType cls, Modifiers mods, ClassType owner) {
		this.accessibilityDiagnostic = new AccessibilityDiagnostic(owner, memberEnv, mods, cls);
	}

	@Override
	public String getText() {
		return this.accessibilityDiagnostic.getText();
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessibilityDiagnostic getAccessibiltyDiagnostic() {
		// TODO Auto-generated method stub
		return this.accessibilityDiagnostic;
	}

}
