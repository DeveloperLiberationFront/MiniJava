package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.AllocArrayInvocation;
import syntax.Expression;
import syntax.Type;

public class AllocArrayInvocationTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public AllocArrayInvocationTypeError(
			AllocArrayInvocation allocArrayInvocation, Type typeOf, Expression array) {
		this.typeError = new ImplicitTypeContractDiagnostic(allocArrayInvocation, typeOf, array);
	}

}
