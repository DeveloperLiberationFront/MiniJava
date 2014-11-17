package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.ClassType;
import syntax.ObjectInvocation;

public class NonClassHasNoMethodsError extends CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public NonClassHasNoMethodsError(ObjectInvocation objectInvocation,
			ClassType currClass) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(objectInvocation, currClass);
	}

}
