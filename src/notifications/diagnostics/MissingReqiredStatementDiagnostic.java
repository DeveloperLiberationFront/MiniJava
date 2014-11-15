package notifications.diagnostics;

import syntax.Statement;
import syntax.SuperInvocation;
import checker.Env;

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

	public MissingReqiredStatementDiagnostic(SuperInvocation superInvocation,
			Statement body) {
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
