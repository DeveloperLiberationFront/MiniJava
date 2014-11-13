package notifications;

import syntax.Id;
import syntax.Name;
import checker.Context;

public class UnboundMainClassNameError extends CompilerDiagnosticBuilder {

	private ImplicitUnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundMainClassNameError() {
		this.unboundNameDiagnostic = new ImplicitUnboundNameDiagnostic(new Name(new Id(null, "Main")), new RequiredMainClassContract());
	}
	
}
