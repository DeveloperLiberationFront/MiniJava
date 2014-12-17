package notifications.thrownerrors;

import notifications.diagnostics.ExplicitTypeContractDiagnostic;
import syntax.BitOpExpr;
import syntax.Expression;
import syntax.Type;

public class BitOpExprTypeError extends CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic explicitTypeContractDiagnostic;

	public BitOpExprTypeError(Expression left, Type lt, Expression right,
			Type rt, BitOpExpr bitOpExpr) {
		this.explicitTypeContractDiagnostic = new ExplicitTypeContractDiagnostic(right, rt, right);
	}

}
