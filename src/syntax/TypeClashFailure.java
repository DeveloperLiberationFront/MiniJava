package syntax;

import checker.Context;
import compiler.Position;
import compiler.RichDiagnostic;

public class TypeClashFailure extends RichDiagnostic {

	private Type type1;
	private Type type2;
	private String relationship;
	private Context ctxt;

	// TODO: relationship shouldn't be a string etc
	// factor out string as relationship, pass in something about the relationship to be rendered later
	// similarly, ctxt is TMI, factor out into pos, whatever else gets used
	public TypeClashFailure(String relationship, Context ctxt, Type type1, Type type2) {
		this.relationship = relationship;
		this.type1 = type1;
		this.type2 = type2;
		this.ctxt = ctxt;
	}

	@Override
	public String getText() {
		return "Type Clash in " + relationship + " between " + type1.toString() + " and " + type2.toString();
	}

	@Override
	public Position getPos() {
		return ctxt.getCurrClass().getPos();
	}
	
	

}
