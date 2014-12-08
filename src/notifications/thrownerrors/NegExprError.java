package notifications.thrownerrors;

import notifications.implication.Exists;
import notifications.implication.MustHaveType;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Expression;
import syntax.NegExpr;
import syntax.Type;

public class NegExprError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public NegExprError(Expression argument, Type argActualType, NegExpr negExpr) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(negExpr),
				new MustHaveType(argument, Type.INT));
	}
}
