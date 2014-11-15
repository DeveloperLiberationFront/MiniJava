package notifications;


public class UnterminatedCharacterSyntaxContractError extends
		CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;

	public UnterminatedCharacterSyntaxContractError(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
