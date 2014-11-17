

package notifications.diagnostics;

import notifications.RichDiagnostic;
import syntax.Id;
import checker.Env;
import compiler.Position;

public class NameClashDiagnostic extends RichDiagnostic implements ClashDiagnosticInterface {

	private Id firstDeclaration;
	private Id secondDeclaration;
	private Id clashingDeclaration;
	private Env otherDeclarations;

	@Override
	public String getText() {
		return "Name Clash between " + firstDeclaration + " and " + secondDeclaration +
				"at "+ firstDeclaration.getPos().describe() +
				" and " + secondDeclaration.getPos().describe();
	}

	@Override
	public Position getPos() {
		return this.firstDeclaration.getPos();
	}


	public NameClashDiagnostic(Id firstDeclaration, Id secondDeclaration) {
		this.firstDeclaration = firstDeclaration;
		this.secondDeclaration = secondDeclaration;
	}

}
