package notifications;


public class UnterminatedStringSyntaxContractDiagnostic extends
		CompilerDiagnosticBuilder {

	private UnterminatedSyntaxContractDiagnostic syntaxError;

	public UnterminatedStringSyntaxContractDiagnostic(RichTokens startToken) {
		this.syntaxError = new UnterminatedSyntaxContractDiagnostic(startToken);
	}

}
