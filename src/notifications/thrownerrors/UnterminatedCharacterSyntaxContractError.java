package notifications.thrownerrors;

import notifications.RichTokens;
import notifications.diagnostics.UnterminatedSyntaxContractDiagnostic;


public class UnterminatedCharacterSyntaxContractError extends
		CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;

	public UnterminatedCharacterSyntaxContractError(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
