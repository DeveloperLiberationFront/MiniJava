package notifications;

import syntax.Id;

public class ClassNameClashDiagnostic extends CompilerDiagnosticBuilder {

	private NameClashDiagnostic nameClash;

	public ClassNameClashDiagnostic(Id clashingClassId, Id existingClassId) {
		this.nameClash = new NameClashDiagnostic(clashingClassId, existingClassId);
	}

}
