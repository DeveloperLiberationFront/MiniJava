package syntax;

import checker.Context;
import compiler.Position;
import compiler.RichDiagnostic;

public class TypeError extends RichDiagnostic {

	private Syntax expression;
	private Context ctxt;
	private Type expectedType;
	private Statement statement;
	public TypeError(Syntax lhs,
			VariableDeclaration lhsDecl,
			Syntax rhs,
			VariableDeclaration rhsDecl) {
		// TODO Auto-generated constructor stub
	}

	public TypeError(Syntax lhs,
			VariableDeclaration lhsDecl, Syntax rhs) {
		// for when, e.g., rhs is a literal
		// TODO Auto-generated constructor stub
	}


	
	public TypeError(Expression expression, Type expectedType) {
		// TODO Auto-generated constructor stub
	}

	public TypeError(Expression expression, Context ctxt, Type expected) {
		this.expression = expression;
		this.ctxt = ctxt;
		this.expectedType = expected;
	}

	public TypeError(Statement statement, Type expectedType) {
		this.expectedType = expectedType;
		this.statement = statement;
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
