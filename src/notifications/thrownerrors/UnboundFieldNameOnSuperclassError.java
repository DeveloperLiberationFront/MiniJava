package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.ClassType;
import syntax.SuperAccess;

public class UnboundFieldNameOnSuperclassError extends
		CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundFieldNameOnSuperclassError(SuperAccess superAccess,
			ClassType sup) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(superAccess, sup);
	}

}
