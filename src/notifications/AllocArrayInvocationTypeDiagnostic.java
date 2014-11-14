package notifications;

import syntax.AllocArrayInvocation;
import syntax.Expression;
import syntax.Type;

public class AllocArrayInvocationTypeDiagnostic extends
		CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public AllocArrayInvocationTypeDiagnostic(
			AllocArrayInvocation allocArrayInvocation, Type typeOf, Expression array) {
		this.typeError = new ImplicitTypeContractDiagnostic(allocArrayInvocation, typeOf, array);
	}

}
