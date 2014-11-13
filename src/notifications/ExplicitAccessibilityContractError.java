package notifications;

import syntax.ClassType;
import checker.MemberEnv;
import compiler.Position;
import compiler.RichDiagnostic;

public class ExplicitAccessibilityContractError extends RichDiagnostic {

	private MemberEnv brokenReference;
	private ClassType contractProvider;

	public ExplicitAccessibilityContractError(MemberEnv memberEnv,
			ClassType owner) {
		this.contractProvider = owner;
		this.brokenReference = memberEnv;
	}

	@Override
	public String getText() {
		return brokenReference.getName() + " from " + contractProvider +
//				" is inaccessible because it is " + contractProvider.getMethods().find(brokenReference.getName(), contractProvider.getMethods()).getMods().describe();
				" is inaccessible because it is " + contractProvider.getMethods().find(brokenReference.getName(), contractProvider.getMethods());
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
