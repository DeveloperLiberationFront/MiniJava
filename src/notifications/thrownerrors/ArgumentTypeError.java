package notifications.thrownerrors;

import notifications.diagnostics.ExplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.Type;
import checker.VarEnv;

public class ArgumentTypeError extends CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic typeError;

	public ArgumentTypeError(Expression arg, Type argt, VarEnv formals) {
		this.typeError = new ExplicitTypeContractDiagnostic(arg, argt, formals);
	}

}
