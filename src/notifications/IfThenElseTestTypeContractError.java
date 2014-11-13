package notifications;

import syntax.Expression;
import syntax.IfThenElse;
import syntax.Type;

public class IfThenElseTestTypeContractError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public IfThenElseTestTypeContractError(Expression test, Type testActualType,
			IfThenElse ifThenElse) {
		this.typeError = new ImplicitTypeContractDiagnostic(test, testActualType, ifThenElse);
	}

}
