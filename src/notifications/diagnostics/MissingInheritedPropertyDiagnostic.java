package notifications.diagnostics;

import syntax.ClassType;
import checker.MemberEnv;

import compiler.Position;
import compiler.RichDiagnostic;

public class MissingInheritedPropertyDiagnostic extends RichDiagnostic {

	private ClassType classType;
	private ClassType ancestor;
	private MemberEnv superProperty;

	public MissingInheritedPropertyDiagnostic(ClassType classType,
			ClassType ancestor, MemberEnv superProperty) {
				this.classType = classType;
				this.ancestor = ancestor;
				this.superProperty = superProperty;
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
