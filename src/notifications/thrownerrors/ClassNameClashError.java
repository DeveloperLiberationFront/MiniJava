package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.MustNotExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ClassType;
import syntax.Id;

public class ClassNameClashError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public ClassNameClashError(ClassType firstClass, ClassType secondClass) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(firstClass),
				new MustNotExist(secondClass));
	}

}
