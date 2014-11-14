package notifications;

import syntax.ClassType;
import syntax.Modifiers;
import checker.MemberEnv;

public class InaccessableMemberError extends CompilerDiagnosticBuilder {

	private ExplicitAccessibilityContractError accessibilityContractDiagnostic;

	public InaccessableMemberError(MemberEnv memberEnv, ClassType cls, Modifiers mods,
			ClassType owner) {
		this.accessibilityContractDiagnostic = new ExplicitAccessibilityContractError(memberEnv, cls, mods, owner);
	}
	
	public String getText() {
		return this.accessibilityContractDiagnostic.getText();
	}

}
