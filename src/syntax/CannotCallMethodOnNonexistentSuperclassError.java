package syntax;

import notifications.RichDiagnostic;
import notifications.implication.Exists;
import notifications.implication.MustExist;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import checker.VarEnv;

import compiler.Position;

public class CannotCallMethodOnNonexistentSuperclassError extends
		RichDiagnostic {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public CannotCallMethodOnNonexistentSuperclassError(VarEnv env,
			ClassType currClass) {
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new Exists(env),
				new MustExist(new ExtendsSyntax(this.getPos()))); // MustExist(extends expression)
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
