package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.Name;
import checker.VarEnv;

public class UnboundNameInNameAccessError extends CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundNameInNameAccessError(Name name, VarEnv env) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(name, env);
	}

}
