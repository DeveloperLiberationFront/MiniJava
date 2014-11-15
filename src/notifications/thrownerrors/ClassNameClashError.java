package notifications.thrownerrors;

import notifications.diagnostics.NameClashDiagnostic;
import syntax.Id;

public class ClassNameClashError extends CompilerDiagnosticBuilder {

	private NameClashDiagnostic nameClash;

	public ClassNameClashError(Id clashingClassId, Id existingClassId) {
		this.nameClash = new NameClashDiagnostic(clashingClassId, existingClassId);
	}

}
