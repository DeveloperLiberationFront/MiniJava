package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.Type;

public class ArrayAccessNonIntIndexTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public ArrayAccessNonIntIndexTypeError(Expression index, Type i) {
		this.typeError = new ImplicitTypeContractDiagnostic(index, i, null);
	}

}
