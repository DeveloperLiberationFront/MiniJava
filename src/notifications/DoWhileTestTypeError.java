package notifications;

import syntax.DoWhile;
import syntax.Expression;
import syntax.Type;

public class DoWhileTestTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public DoWhileTestTypeError(Expression test, Type testExpressionActualType, DoWhile doWhileStatement) {
		this.typeError = new ImplicitTypeContractDiagnostic(test, testExpressionActualType,
				doWhileStatement);
	}

}
