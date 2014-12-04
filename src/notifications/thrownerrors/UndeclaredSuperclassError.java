package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.MustHaveSuperclass;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ClassType;
import syntax.SuperAccess;

public class UndeclaredSuperclassError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public UndeclaredSuperclassError(SuperAccess superAccess,
			ClassType classContainingSuperAccess) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(superAccess),
				new MustHaveSuperclass(classContainingSuperAccess));
	}

}
