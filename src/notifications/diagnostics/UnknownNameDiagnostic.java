package notifications.diagnostics;

import syntax.ClassType;
import syntax.Name;
import syntax.NewExpr;
import checker.Context;
import checker.Env;

import compiler.Position;
import compiler.RichDiagnostic;

public class UnknownNameDiagnostic extends RichDiagnostic {

	private Name name;
	private Env env;

	public UnknownNameDiagnostic(Name name, Env env) {
		this.name = name;
		this.env= env;
	}

	public UnknownNameDiagnostic(Name name2, ClassType cls) {
		// TODO Auto-generated constructor stub
	}

	public UnknownNameDiagnostic(Name name2, Context ctxt, NewExpr newExpr) {
		// TODO Auto-generated constructor stub
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
