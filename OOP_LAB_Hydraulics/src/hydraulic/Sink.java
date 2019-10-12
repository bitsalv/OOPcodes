package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element { //scarico

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	@Override public void connect(Element elem) {
		//non fa nulla
	}
	
	
	@Override public void calculateFlow(Element output,SimulationObserver observer) {
		observer.notifyFlow(this.getClass().getSimpleName(), this.getName(), this.getOutputFlow(), NO_FLOW);
	}
	
	@Override
	public String toString() {
		
		String s= "["+this.getName()+"]"+this.getClass().getSimpleName()+"|";
		
		return s;
	}
	
}
