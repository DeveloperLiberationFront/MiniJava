package syntax;

import checker.Env;
import checker.VarEnv;
import compiler.Position;
import compiler.RichDiagnostic;

public class MissingReqiredStatementDiagnostic extends RichDiagnostic {

	private Statement missingExpression;
	private Env validStatementLocation;
	public MissingReqiredStatementDiagnostic(Statement statement,
			Env env) {
		this.missingExpression = statement;
		this.validStatementLocation = env;
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
