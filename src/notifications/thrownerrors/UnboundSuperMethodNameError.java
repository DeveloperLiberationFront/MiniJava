package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.ClassType;
import syntax.SuperInvocation;

public class UnboundSuperMethodNameError extends CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameError;

	public UnboundSuperMethodNameError(SuperInvocation superInvocation,
			ClassType sup) {
		this.unboundNameError = new UnboundNameDiagnostic(superInvocation, sup);
	}
}
