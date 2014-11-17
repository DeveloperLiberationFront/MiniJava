package notifications.thrownerrors;

import notifications.RichTokens;
import notifications.diagnostics.UnterminatedSyntaxContractDiagnostic;


public class UnterminatedStringSyntaxContractError extends
		CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;

	public UnterminatedStringSyntaxContractError(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
