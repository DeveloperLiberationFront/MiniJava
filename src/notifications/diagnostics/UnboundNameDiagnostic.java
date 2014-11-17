package notifications.diagnostics;

import notifications.RichDiagnostic;
import syntax.ClassType;
import syntax.Expression;
import syntax.Name;
import syntax.NewExpr;
import syntax.ObjectInvocation;
import syntax.SuperAccess;
import checker.Context;
import checker.Env;
import compiler.Position;

public class UnboundNameDiagnostic extends RichDiagnostic implements UnboundDiagnosticInterface {

	private Name name;
	private Env env;

	public UnboundNameDiagnostic(Name name, Env env) {
		this.name = name;
		this.env= env;
	}

	public UnboundNameDiagnostic(Name name2, ClassType cls) {
		// TODO Auto-generated constructor stub
	}

	public UnboundNameDiagnostic(Name name2, Context ctxt, NewExpr newExpr) {
		// TODO Auto-generated constructor stub
	}

	public UnboundNameDiagnostic(ObjectInvocation objectInvocation,
			ClassType currClass) {
		// TODO Auto-generated constructor stub
	}

	public UnboundNameDiagnostic(Expression superAccess, ClassType sup) {
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
