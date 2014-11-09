package syntax;

import compiler.Position;
import compiler.RichDiagnostic; 

public class InvalidUseOfModifiedClassDiagnostic extends RichDiagnostic {

	private NewExpr useSite;
	private ClassType declaration;

	public InvalidUseOfModifiedClassDiagnostic(NewExpr useSite, ClassType declaration) {
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
