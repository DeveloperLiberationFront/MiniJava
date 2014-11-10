package syntax;

import compiler.Declaration;
import compiler.Position;
import compiler.RichDiagnostic;

public class UndeclaredSuperclassDiagnostic extends RichDiagnostic {

	private Declaration subclassDeclaration;
	private SuperAccess superAccess;
	private ExtendsExpression extendsExpression;

	public UndeclaredSuperclassDiagnostic(SuperAccess superAccess,
			Declaration subclassDeclaration, ExtendsExpression extendsExpression) {
		// instantiate "missing expression" and "scope accessibility" diagnostics
		this.superAccess = superAccess;
		this.subclassDeclaration = subclassDeclaration;
		this.extendsExpression = extendsExpression;;
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
