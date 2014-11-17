package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.Name;
import checker.VarEnv;

public class UnboundClassNameInObjectAccessError extends
		CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundClassNameInObjectAccessError(Name name, VarEnv env) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(name, env);
	}

}
