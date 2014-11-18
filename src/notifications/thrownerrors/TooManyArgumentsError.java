package notifications.thrownerrors;

import notifications.IsDeclaration;
import notifications.diagnostics.SignatureContractDiagnostic;
import checker.VarEnv;

public class TooManyArgumentsError extends CompilerDiagnosticBuilder {

	private SignatureContractDiagnostic signatureError;

	public TooManyArgumentsError(VarEnv env, IsDeclaration formalsDeclaration) {
		this.signatureError = new SignatureContractDiagnostic(env, formalsDeclaration);
	}

}
