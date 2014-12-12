package notifications.thrownerrors;

import notifications.Region;
import notifications.RichTokens;
import notifications.implication.Exists;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;


public class EscapeSequenceContractError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public EscapeSequenceContractError(RichTokens startToken) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(startToken),
				new MustExist(new RichTokens("valid escape sequence char"), new Region()));
	}

}
