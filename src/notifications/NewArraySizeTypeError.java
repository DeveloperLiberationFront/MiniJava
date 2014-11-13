package notifications;

import syntax.Expression;
import syntax.NewArrayExpr;
import syntax.Type;

import compiler.Position;

public class NewArraySizeTypeError extends CompilerDiagnosticBuilder {
	
	private ImplicitTypeContractDiagnostic typeError;

	public NewArraySizeTypeError(Expression sizeExpression, Type actualType, NewArrayExpr newArrayExpr) {
		this.typeError = new ImplicitTypeContractDiagnostic(sizeExpression, actualType, newArrayExpr);
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
