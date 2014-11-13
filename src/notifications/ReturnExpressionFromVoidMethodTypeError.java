package notifications;

import syntax.Expression;
import syntax.Type;
import checker.MethEnv;

public class ReturnExpressionFromVoidMethodTypeError extends
		CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic typeError;

	public ReturnExpressionFromVoidMethodTypeError(Expression result, Type rt,
			MethEnv methEnv) {
		this.typeError = new ExplicitTypeContractDiagnostic(result, rt, methEnv);
	}

}
