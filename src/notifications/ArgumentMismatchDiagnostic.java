package notifications;

import checker.Env;
import checker.VarEnv;
import compiler.Position;
import compiler.RichDiagnostic;

public class ArgumentMismatchDiagnostic extends RichDiagnostic {

	private Env args;
	private VarEnv params;
	public ArgumentMismatchDiagnostic(Env args, VarEnv params) {
		this.args = args;
		this.params = params;
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
