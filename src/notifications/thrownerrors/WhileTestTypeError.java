package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.Type;
import syntax.While;

public class WhileTestTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public WhileTestTypeError(Expression test, Type testExpressionActualType, While whileStatement) {
		this.typeError = new ImplicitTypeContractDiagnostic(test, testExpressionActualType,
				whileStatement);
	}

}
