package notifications;

import syntax.Expression;
import syntax.Type;
import checker.MethEnv;

public class ReturnNothingFromTypedMethodTypeError extends
		CompilerDiagnosticBuilder {
	private ExplicitTypeContractDiagnostic typeError;

	public ReturnNothingFromTypedMethodTypeError(Expression result, Type rt,
			MethEnv methEnv) {
		this.typeError = new ExplicitTypeContractDiagnostic(result, rt, methEnv);
	}

}
