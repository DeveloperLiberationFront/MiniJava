package notifications.implication;

import compiler.Declaration;
import syntax.ClassType;
import syntax.Expression;
import syntax.Syntax;

public class MustNotExist extends Consequent {

	private Object element;

	public MustNotExist(ClassType disallowedClass) {
		// TODO Auto-generated constructor stub
	}
	
	public MustNotExist(String message) {
		this.element = message;
	}

	public MustNotExist(Syntax programElement) {
		// TODO Auto-generated constructor stub
	}

	public MustNotExist(Declaration declaration) {
		// TODO Auto-generated constructor stub
	}

	@Override
	String getText() {
		// TODO Auto-generated method stub
		return this.element.toString();
	}

}
