package notifications;


public class UnterminatedBracketSyntaxContractError extends
		CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;
	
	public UnterminatedBracketSyntaxContractError(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
