package notifications.thrownerrors;

import notifications.implication.MustNotExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import notifications.implication.HasType;
import syntax.Expression;
import syntax.Type;
import checker.MethEnv;

public class ReturnExpressionFromVoidMethodTypeError extends
		CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public ReturnExpressionFromVoidMethodTypeError(Expression result, Type rt,
			MethEnv methEnv) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new HasType(methEnv, null), new MustNotExist(result));
	}

}
