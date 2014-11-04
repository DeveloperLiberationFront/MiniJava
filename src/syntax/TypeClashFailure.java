package syntax;

import checker.Context;
import compiler.Position;
import compiler.RichDiagnostic;

public class TypeClashFailure extends RichDiagnostic {

	private Context ctxt;
	private Type type;

	public TypeClashFailure(Context ctxt, Type type) {
		this.ctxt = ctxt;
		this.type = type;
	}

	@Override
	public String getText() {
		return "Type Clash between " + ctxt.toString() + " and " + type.toString();
	}

	@Override
	public Position getPos() {
		return ctxt.getCurrClass().getPos();
	}
	
	

}
