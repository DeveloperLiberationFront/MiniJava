package notifications.implication;

import java.util.List;

public class OrConsequent extends Consequent {

	private List<Consequent> consequents;

	public OrConsequent(List<Consequent> consequents) {
		this.consequents = consequents;
	}

	@Override
	String getText() {
		String rv, delimiter;
		rv = delimiter = "";
		for (Consequent c : this.consequents) {
			rv += delimiter + c.getText();
			delimiter = "\n\tOR ";
		}
		return rv;
	}

}
