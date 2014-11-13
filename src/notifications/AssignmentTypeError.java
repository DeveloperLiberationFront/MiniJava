package notifications;

import syntax.Expression;
import syntax.LeftHandSide;
import syntax.Type;

public class AssignmentTypeError extends CompilerDiagnosticBuilder {

	private ExplicitTypeContractDiagnostic typeError;

	public AssignmentTypeError(Expression rightHandSide, Type rhsType,
			LeftHandSide leftHandSide, Type lhsType) {
		this.typeError = new ExplicitTypeContractDiagnostic(rightHandSide, rhsType, leftHandSide);
	}
	
	@Override
	public String getText() {
		return "lol I'm an assignment type error";
	}

}
