package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.RelOpExpr;
import syntax.Type;

public class RelOpTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public RelOpTypeError(Expression left, Type lt, Expression right, Type rt,
			RelOpExpr relOpExpr) {
		typeError = new ImplicitTypeContractDiagnostic(left, lt, right, rt, relOpExpr);
	}

}
