package notifications.thrownerrors;

import notifications.RichDiagnostic;
import syntax.ClassType;
import syntax.Modifiers;
import checker.MemberEnv;
import compiler.Position;

public class ExplicitAccessibilityContractError extends RichDiagnostic {

	private MemberEnv brokenReference;
	private ClassType contractProvider;
	private Modifiers mods;
	private ClassType cls;

	public ExplicitAccessibilityContractError(MemberEnv memberEnv,
			ClassType cls, Modifiers mods, ClassType owner) {
		this.contractProvider = owner;
		this.brokenReference = memberEnv;
		this.mods = mods;
		this.cls = cls;
	}

	@Override
	public String getText() {
		return brokenReference.describe() + " (declared in " + contractProvider + ")" +
//				" is inaccessible because it is " + contractProvider.getMethods().find(brokenReference.getName(), contractProvider.getMethods()).getMods().describe();
				" is inaccessible from " + " because it is " + mods.describe();
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
