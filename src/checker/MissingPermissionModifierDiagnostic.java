package checker;

import compiler.Position;
import compiler.RichDiagnostic;

public class MissingPermissionModifierDiagnostic extends RichDiagnostic {

	private MethEnv method;
	private int missingModifierMask;

	public MissingPermissionModifierDiagnostic(MethEnv method, int missingModifierMask) {
		this.method = method;
		this.missingModifierMask = missingModifierMask;
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
