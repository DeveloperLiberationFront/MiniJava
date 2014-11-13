package notifications;

import checker.MemberEnv;
import syntax.ClassType;
import syntax.Modifiers;
import syntax.Type;

public class InaccessableMemberError extends CompilerDiagnosticBuilder {

	private ExplicitAccessibilityContractError accessibilityContractDiagnostic;

	public InaccessableMemberError(MemberEnv memberEnv, Modifiers mods,
			ClassType owner) {
		this.accessibilityContractDiagnostic = new ExplicitAccessibilityContractError(memberEnv, owner);
	}
	
	public String getText() {
		return this.accessibilityContractDiagnostic.getText();
	}

}
