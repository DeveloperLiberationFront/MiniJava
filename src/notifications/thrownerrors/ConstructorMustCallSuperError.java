package notifications.thrownerrors;

import notifications.diagnostics.MissingRequiredStatementDiagnostic;
import syntax.Statement;
import syntax.SuperInvocation;

public class ConstructorMustCallSuperError extends CompilerDiagnosticBuilder {

	private MissingRequiredStatementDiagnostic missingStatementDiagnostic;

	public ConstructorMustCallSuperError(SuperInvocation superInvocation,
			Statement body) {
    	this.missingStatementDiagnostic = new MissingRequiredStatementDiagnostic(
    			superInvocation, body);
	}

}
