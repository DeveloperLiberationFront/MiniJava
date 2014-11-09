package syntax;

import checker.Context;
import compiler.Position;
import compiler.RichDiagnostic;

public class TypeError extends RichDiagnostic {

	private Expression expression;
	private Context ctxt;
	private Type expectedType;
	public TypeError(Expression lhs,
			VariableDeclaration lhsDecl, Expression rhs,
			VariableDeclaration rhsDecl,
			Expression errorContext) {
		// TODO Auto-generated constructor stub
	}

	public TypeError(Expression lhs,
			VariableDeclaration lhsDecl, Expression rhs,
			Expression errorContext) {
		// for when, e.g., rhs is a literal
		// TODO Auto-generated constructor stub
	}


	
	public TypeError(Expression expression, Type expectedType, Statement statement) {
		// TODO Auto-generated constructor stub
	}

	public TypeError(Expression expression, Context ctxt, Type expected) {
		this.expression = expression;
		this.ctxt = ctxt;
		this.expectedType = expected;
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
