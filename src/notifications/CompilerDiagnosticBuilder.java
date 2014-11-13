package notifications;

import compiler.Diagnostic;
import compiler.Position;

public class CompilerDiagnosticBuilder extends Diagnostic {

	@Override
	public String getText() {
		return this.getClass().getName();
	}

	@Override
	public Position getPos() {
		// TODO Auto-generated method stub
		return null;
	}

}
