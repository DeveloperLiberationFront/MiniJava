package notifications;

import syntax.Id;
import checker.VarEnv;

public class LocalVarAlreadyExistsError extends CompilerDiagnosticBuilder {

	private NameClashError nameClashError;

	public LocalVarAlreadyExistsError(Id id, VarEnv env) {
		this.nameClashError = new NameClashError(id, env);
	}
	
}
