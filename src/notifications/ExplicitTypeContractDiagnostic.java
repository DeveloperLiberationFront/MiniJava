package notifications;

import syntax.Expression;
import syntax.Type;
import checker.Env;

public class ExplicitTypeContractDiagnostic extends TypeMismatchDiagnostic {

	public ExplicitTypeContractDiagnostic(Expression result, Type rt,
			Env env) {
	}

	public ExplicitTypeContractDiagnostic(Expression expr,
			Type actualExprType, Expression contractProvider) {
		// TODO Auto-generated constructor stub
	}

}
