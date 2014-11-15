package notifications.thrownerrors;

import notifications.contracts.RequiredMainClassContract;
import notifications.diagnostics.ImplicitUnboundNameDiagnostic;
import syntax.Id;
import syntax.Name;

public class UnboundMainClassNameError extends CompilerDiagnosticBuilder {

	private ImplicitUnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundMainClassNameError() {
		this.unboundNameDiagnostic = new ImplicitUnboundNameDiagnostic(new Name(new Id(null, "Main")), new RequiredMainClassContract());
	}
	
}
