package notifications.implication;

import compiler.Position;
import notifications.RichDiagnostic;

public class UnsatisfiedImplicationDiagnostic extends RichDiagnostic {

	private Antecedent antecedent;
	private Consequent consequent;

	public UnsatisfiedImplicationDiagnostic(Antecedent antecedent, Consequent consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}

	@Override
	public String getText() {
		return this.antecedent.getText() + ", which requires that \n\t" + this.consequent.getText();
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
