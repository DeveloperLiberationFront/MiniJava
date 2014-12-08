package notifications.diagnostics;

import notifications.RichDiagnostic;
import notifications.implication.Exists;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ClassType;
import syntax.Id;
import checker.FieldEnv;
import checker.MemberEnv;

import compiler.Position;

public class MissingInheritedPropertyDiagnostic extends RichDiagnostic {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;
	
	public MissingInheritedPropertyDiagnostic(ClassType classType,
			ClassType ancestor, MemberEnv superProperty) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(superProperty),
				new MustExist(new FieldEnv(null,
						new Id(null, null),
						superProperty.getType(), null, 0, 0, null, null)));
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
