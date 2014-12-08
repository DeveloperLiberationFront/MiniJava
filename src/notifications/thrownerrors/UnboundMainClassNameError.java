package notifications.thrownerrors;

import syntax.ClassType;
import notifications.implication.Implicit;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;

public class UnboundMainClassNameError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public UnboundMainClassNameError() {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Implicit(null), 
				new MustExist(new ClassType(null, null, null, null, null)));
	}
	
}
