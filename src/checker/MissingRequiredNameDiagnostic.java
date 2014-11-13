package checker;

import syntax.ClassType;

import compiler.Position;
import compiler.RichDiagnostic;

public class MissingRequiredNameDiagnostic extends RichDiagnostic {

	public MissingRequiredNameDiagnostic(ClassType missingClass, Context ctxt) {
		// TODO Auto-generated constructor stub
	}


//  Don't need this yet, but will come in handy for when there's a missing reference to something declared elsewhere	
//	public MissingRequiredName(ClassType missingClass, Context ctxt) {
//		// TODO Auto-generated constructor stub
//	}


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
