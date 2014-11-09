package syntax;

import checker.Context;
import compiler.Position;
import compiler.RichDiagnostic;

public class UnknownNameDiagnostic extends RichDiagnostic {

	private Name name;
	private Type expectedType;

	public UnknownNameDiagnostic(Name name, Type expectedType) {
		this.expectedType = expectedType;
		this.name = name;
	}

	private Context ctxt;
	public UnknownNameDiagnostic(Name name, Context ctxt) {
		this.name = name;
		this.ctxt = ctxt;
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
