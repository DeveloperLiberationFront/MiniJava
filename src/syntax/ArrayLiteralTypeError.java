package syntax;

import notifications.CompilerDiagnosticBuilder;
import notifications.ImplicitTypeContractDiagnostic;

public class ArrayLiteralTypeError extends CompilerDiagnosticBuilder {

	private Object typeError ;

	public ArrayLiteralTypeError(ArrayLiteral actualLiteral, Type actualType) {
		this.typeError = new ImplicitTypeContractDiagnostic(actualLiteral, actualType,
				actualLiteral);
	}

}
