package notifications;

import syntax.Return;
import syntax.Type;
import checker.MethEnv;

import compiler.Diagnostic;
import compiler.Position;

public class ExplicitContractTypeError extends Diagnostic {

	public ExplicitContractTypeError(Return returnExpr, Type returnExprType,
			MethEnv method) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
