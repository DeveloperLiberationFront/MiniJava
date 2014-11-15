package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.NegExpr;
import syntax.Type;

public class NegExprError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public NegExprError(Expression argument, Type argActualType, NegExpr negExpr) {
		this.typeError = new ImplicitTypeContractDiagnostic(argument, argActualType, argument);
	}
}
