package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.ClassType;
import syntax.Name;


public class UnboundClassNameError extends CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundClassNameError(Name name, ClassType cls) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(name, cls);
	}
}
