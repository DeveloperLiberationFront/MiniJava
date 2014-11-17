package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.Name;
import checker.VarEnv;

public class UnboundMethodNameError extends CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundMethodNameError(Name name, VarEnv env) {
		this.unboundNameDiagnostic = new UnboundNameDiagnostic(name, env);
	}

}
