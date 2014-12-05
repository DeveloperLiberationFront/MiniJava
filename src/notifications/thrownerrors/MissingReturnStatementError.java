package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.HasType;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Return;
import checker.MethEnv;

public class MissingReturnStatementError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public MissingReturnStatementError(MethEnv method) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new HasType(method, method.getType()),
				new MustExist(new Return(null, null), method.getBody()));
	}

}
