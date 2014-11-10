package syntax;

import compiler.Position;
import compiler.RichDiagnostic;

public class DeadCodeDiagnostic extends RichDiagnostic {

	private Expression deadExpr;

	public DeadCodeDiagnostic(Expression deadExpr) {
		this.deadExpr = deadExpr;
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
