package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.ExtendsSyntax;
import syntax.Statement;
import syntax.SuperInvocation;

public class ConstructorMustCallSuperError extends CompilerDiagnosticBuilder {
	
	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public ConstructorMustCallSuperError(SuperInvocation superInvocation,
			Statement body) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(new ExtendsSyntax(null)), new MustExist(new SuperInvocation(null, null, null)));
	}

}
