package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.Type;
import checker.MethEnv;

public class MainMethodVoidError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public MainMethodVoidError(MethEnv mainMeth, Type mainMethType) {
		this.typeError = new ImplicitTypeContractDiagnostic(mainMeth, mainMethType, null);
	}

}
