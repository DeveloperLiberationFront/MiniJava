package syntax;

import checker.Context;
import checker.Env;
import compiler.Position;
import compiler.RichDiagnostic;

public class UnknownNameDiagnostic extends RichDiagnostic {

	private Name name;
	private Type expectedType;

	public UnknownNameDiagnostic(Name name, Type expectedType) {
		this.expectedType = expectedType;
		this.name = name;
	}

	private Env env;
	public UnknownNameDiagnostic(Name name, Env env) {
		this.name = name;
		this.env= env;
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
