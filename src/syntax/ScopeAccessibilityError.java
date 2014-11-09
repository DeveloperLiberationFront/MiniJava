package syntax;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

import checker.Context;
import checker.Env;
import checker.MethEnv;
import compiler.Position;
import compiler.RichDiagnostic;

public class ScopeAccessibilityError extends RichDiagnostic implements
		AccessibilityDiagnostic {

	private Object valid;
	private Env currEnv;
	private Invocation invocation;
	public ScopeAccessibilityError(Position pos, Context ctxt) {
		// TODO Auto-generated constructor stub
	}

	public ScopeAccessibilityError(Invocation invocation,
			Env currEnv, Collection<MethDecl> environments) {
		this.invocation = invocation;
		this.currEnv = currEnv;
		this.validEnvironments = environments;
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
