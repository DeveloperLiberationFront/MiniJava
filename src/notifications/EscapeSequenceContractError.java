package notifications;


public class EscapeSequenceContractError extends CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;

	public EscapeSequenceContractError(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
