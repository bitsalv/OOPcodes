package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element { //sorgente
	
	public Source(String name) {
		super(name);
		//TODO: complete
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
public void setFlow(double flow){
		outputFlow=flow;
}
	

@Override public Element getOutput(){
		return output;
}

	


@Override public double getOutputFlow() {
		return outputFlow;
	}

@Override public void calculateFlow(Element output, SimulationObserver observer) {
	
	output.setOutputFlow(this.outputFlow);
	observer.notifyFlow(this.getClass().getSimpleName(), this.getName(), NO_FLOW, this.outputFlow);
	output.calculateFlow(output.output,observer);
}

@Override
public String toString() {
	
	String s= "["+this.getName()+"]"+this.getClass().getSimpleName()+"->";
	
	return s;
}

}
