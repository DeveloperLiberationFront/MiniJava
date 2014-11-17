package notifications.thrownerrors;

import notifications.diagnostics.UnboundNameDiagnostic;
import syntax.Name;
import checker.VarEnv;

public class UnboundFieldNameErrorInObjectAccess extends
		CompilerDiagnosticBuilder {

	private UnboundNameDiagnostic unboundNameDiagnostic;

	public UnboundFieldNameErrorInObjectAccess(Name name, VarEnv env) {
    	this.unboundNameDiagnostic = new UnboundNameDiagnostic(name, env);
	}

}
