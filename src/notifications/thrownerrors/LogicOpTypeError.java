package notifications.thrownerrors;

import notifications.diagnostics.ImplicitTypeContractDiagnostic;
import syntax.Expression;
import syntax.LogicOpExpr;
import syntax.Type;

public class LogicOpTypeError extends CompilerDiagnosticBuilder {

	private ImplicitTypeContractDiagnostic typeError;

	public LogicOpTypeError(Expression leftOperand, Type leftOperandType,
			LogicOpExpr logicOpExpr) {
		this.typeError = new ImplicitTypeContractDiagnostic(leftOperand, leftOperandType, logicOpExpr);
	}
	

}
