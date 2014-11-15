package notifications.thrownerrors;

import notifications.IsDeclaration;
import notifications.diagnostics.ExplicitSignatureContractDiagnostic;
import checker.VarEnv;

public class TooManyArgumentsError extends CompilerDiagnosticBuilder {

	private ExplicitSignatureContractDiagnostic signatureError;

	public TooManyArgumentsError(VarEnv env, IsDeclaration formalsDeclaration) {
		this.signatureError = new ExplicitSignatureContractDiagnostic(env, formalsDeclaration);
	}

}
