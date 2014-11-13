package notifications;

import syntax.Expression;
import syntax.NullCheck;
import syntax.Type;


public class NullCheckTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public NullCheckTypeError(Expression object, Type objectActualType, NullCheck nullCheck) {
		this.typeError = new ImplicitTypeContractDiagnostic(object, objectActualType, nullCheck);
	}
	
}
