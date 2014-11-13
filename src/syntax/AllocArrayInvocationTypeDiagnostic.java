package syntax;

import notifications.CompilerDiagnosticBuilder;
import notifications.ImplicitTypeContractDiagnostic;

public class AllocArrayInvocationTypeDiagnostic extends
		CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public AllocArrayInvocationTypeDiagnostic(
			AllocArrayInvocation allocArrayInvocation, Type typeOf, Expression array) {
		this.typeError = new ImplicitTypeContractDiagnostic(allocArrayInvocation, typeOf, array);
	}

}
