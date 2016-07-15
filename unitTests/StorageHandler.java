import java.util.Collection;
import java.util.LinkedList;

import compiler.Diagnostic;
import compiler.Failure;
import compiler.Handler;


public class StorageHandler extends Handler {
    private Collection<Diagnostic> storage = new LinkedList<Diagnostic>();

    protected void respondTo(Diagnostic d) {
        storage.add(d);
    }

	public String aFailure() {

		for(Diagnostic d : storage){
			if(d instanceof Failure){
				return d.getText() + " on line " + d.getPos().getRow();
			}
		}
		
		return null;
	}
}
