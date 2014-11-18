package notifications.thrownerrors;

import notifications.IsDeclaration;
import notifications.diagnostics.SignatureContractDiagnostic;
import checker.VarEnv;

public class TooManyParametersError extends CompilerDiagnosticBuilder {

	private SignatureContractDiagnostic signatureError;

	public TooManyParametersError(VarEnv env, IsDeclaration declaration) {
		this.signatureError = new SignatureContractDiagnostic(env, declaration);
	}

}
