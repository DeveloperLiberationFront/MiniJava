package notifications;

import syntax.Id;
import checker.Env;
import compiler.Position;
import compiler.RichDiagnostic;

public class NameClashError extends RichDiagnostic {

	public NameClashError(Id id, Env clashingDeclarations) {
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
