package notifications.thrownerrors;

import notifications.Region;
import notifications.RichTokens;
import notifications.implication.Exists;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;


public class UnterminatedBracketSyntaxContractError extends
		CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;
	
	public UnterminatedBracketSyntaxContractError(RichTokens startToken) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(startToken),
				new MustExist(new RichTokens(null, "*/"), new Region()));
	}

}
