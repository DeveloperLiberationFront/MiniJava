package notifications.thrownerrors;

import syntax.Id;
import checker.Env;

public class FieldAlreadyExistsError extends CompilerDiagnosticBuilder {
	private NameClashError nameClashError;

	public FieldAlreadyExistsError(Id id, Env clashingDeclarations) {
		this.nameClashError = new NameClashError(id, clashingDeclarations);
	}
}