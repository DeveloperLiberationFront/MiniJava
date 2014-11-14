package notifications;

import syntax.Syntax;
import syntax.Type;

import compiler.Diagnostic;
import compiler.Position;

public class SyntaxRequiresTypeDiagnostic extends Diagnostic {

	private Syntax syntax;
	private Type type;
	public SyntaxRequiresTypeDiagnostic(Syntax syntax,
			Type type) {
		this.syntax = syntax;
		this.type = type;
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
