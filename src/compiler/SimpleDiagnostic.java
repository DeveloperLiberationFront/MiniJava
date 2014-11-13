package compiler;

public abstract class SimpleDiagnostic extends Diagnostic {

    protected String text;
	protected Position position;
	
	public String getText() {
		return this.text;
	}
	
	public Position getPos() {
		return this.position;
	}

	/** Construct a simple diagnostic with a fixed description.
     */
    public SimpleDiagnostic(String text) {
        this.text = text;
    }

    /** Construct a diagnostic object for a particular source position.
     */
    public SimpleDiagnostic(Position position) {
        this.position = position;
    }

    /** Construct a simple diagnostic with a fixed description
     *  and a source position.
     */
    public SimpleDiagnostic(Position position, String text) {
        this.position = position;
        this.text     = text;
    }
}
