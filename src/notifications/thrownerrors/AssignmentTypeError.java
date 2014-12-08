package notifications.thrownerrors;

import java.util.ArrayList;

import notifications.implication.Consequent;
import notifications.implication.HasType;
import notifications.implication.MustHaveSubtype;
import notifications.implication.MustHaveType;
import notifications.implication.OrConsequent;
import notifications.implication.UnsatisfiedImplicationDiagnostic;
import syntax.Expression;
import syntax.LeftHandSide;
import syntax.Type;

public class AssignmentTypeError extends CompilerDiagnosticBuilder {

	private UnsatisfiedImplicationDiagnostic unsatisfiedImplication;

	public AssignmentTypeError(Expression rightHandSide, Type rhsType,
			LeftHandSide leftHandSide, Type lhsType) {
		
		ArrayList<Consequent> consequents = new ArrayList<Consequent>();
		consequents.add(new MustHaveType(rightHandSide, lhsType));
		consequents.add(new MustHaveSubtype(rightHandSide, lhsType));
		
		this.unsatisfiedImplication = new UnsatisfiedImplicationDiagnostic(
				new HasType(leftHandSide, lhsType),
				new OrConsequent(consequents));
	}
	
	@Override
	public String getText() {
		return this.unsatisfiedImplication.getText();
	}

}
