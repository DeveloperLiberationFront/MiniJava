package notifications.thrownerrors;

import syntax.Return;
import syntax.Type;
import checker.MethEnv;

public class ReturnTypeNotSubclassTypeError extends CompilerDiagnosticBuilder {

	private Object typeError;

	public ReturnTypeNotSubclassTypeError(Return returnExpr, Type returnExprType,
			MethEnv method) {
		this.typeError = new ExplicitContractTypeError(returnExpr, returnExprType, method); 
	}

}
