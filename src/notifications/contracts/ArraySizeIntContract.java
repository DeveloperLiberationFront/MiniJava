package notifications.contracts;

import syntax.Type;

public class ArraySizeIntContract extends ImplicitTypeContract {

	public ArraySizeIntContract(Type requiredType) {
		super(requiredType);
	}
	// says the size declaration must be an integer
}
