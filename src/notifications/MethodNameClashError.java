package notifications;

import syntax.Id;
import checker.VarEnv;

public class MethodNameClashError extends CompilerDiagnosticBuilder {

	private NameClashDiagnostic nameClash;

	public MethodNameClashError(Id paramId, Id clashingMethod) {
		this.nameClash = new NameClashDiagnostic(paramId, clashingMethod);
	}

}
