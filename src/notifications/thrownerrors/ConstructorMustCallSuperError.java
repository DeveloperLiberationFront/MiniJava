package notifications.thrownerrors;

import notifications.diagnostics.MissingReqiredStatementDiagnostic;
import syntax.Statement;
import syntax.SuperInvocation;

public class ConstructorMustCallSuperError extends CompilerDiagnosticBuilder {

	private MissingReqiredStatementDiagnostic missingStatementDiagnostic;

	public ConstructorMustCallSuperError(SuperInvocation superInvocation,
			Statement body) {
    	this.missingStatementDiagnostic = new MissingReqiredStatementDiagnostic(
    			superInvocation, body);
	}

}
