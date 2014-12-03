package notifications.diagnostics;

import notifications.RichDiagnostic;
import compiler.Position;

public class TypeMismatchDiagnostic extends RichDiagnostic {

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return this.getClass().toString();
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
