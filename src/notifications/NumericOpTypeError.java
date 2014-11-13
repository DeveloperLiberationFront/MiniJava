package notifications;

import syntax.Expression;
import syntax.NumericOpExpr;
import syntax.Type;

public class NumericOpTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public NumericOpTypeError(Expression operand, Type operandType,
			NumericOpExpr numericOpExpr) {
		this.typeError = new ImplicitTypeContractDiagnostic(operand, operandType, numericOpExpr);
	}

}
