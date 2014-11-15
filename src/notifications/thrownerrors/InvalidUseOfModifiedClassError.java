package notifications.thrownerrors;

import syntax.ClassType;
import syntax.NewExpr;

import compiler.Position;
import compiler.RichDiagnostic;

public class InvalidUseOfModifiedClassError extends RichDiagnostic {

	private NewExpr useSite;
	private ClassType declaration;

	public InvalidUseOfModifiedClassError(NewExpr useSite, ClassType declaration) {
		this.useSite = useSite;
		this.declaration = declaration;
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
