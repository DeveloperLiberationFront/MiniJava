package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.MustNotExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ClassType;
import syntax.Modifiers;
import checker.MemberEnv;

public class InaccessableMemberError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public InaccessableMemberError(MemberEnv memberEnv,
			ClassType cls,
			Modifiers mods,
			ClassType owner) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(mods),
				new MustNotExist("reference to " + memberEnv.toString()));
	}
	
	public String getText() {
		return this.unsatisfiedImplication.getText();
	}

}
