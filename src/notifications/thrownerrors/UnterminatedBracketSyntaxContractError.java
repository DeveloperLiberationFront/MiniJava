package notifications.thrownerrors;

import notifications.RichTokens;
import notifications.diagnostics.UnterminatedSyntaxContractDiagnostic;


public class UnterminatedBracketSyntaxContractError extends
		CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;
	
	public UnterminatedBracketSyntaxContractError(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
