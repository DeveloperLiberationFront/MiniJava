package notifications;

import syntax.Expression;
import syntax.NotExpr;
import syntax.Type;

public class NotExprError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public NotExprError(Expression argument, Type argActualType, NotExpr notExpr) {
		this.typeError = new ImplicitTypeContractDiagnostic(argument, argActualType, argument);
	}

}
