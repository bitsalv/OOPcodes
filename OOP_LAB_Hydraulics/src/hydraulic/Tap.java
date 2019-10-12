package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element { //rubinetto
	

	public Tap(String name) {
		super(name);
		//TODO: complete
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		if(open==false) {
			this.setOutputFlow(0);
		}
	}
	
	
	@Override public Element getOutput(){
		return this.output;
}
	@Override public void calculateFlow(Element output,SimulationObserver observer) {
		output.setOutputFlow(this.outputFlow);
		observer.notifyFlow(this.getClass().getSimpleName(), this.getName(), this.outputFlow, this.outputFlow);
		output.calculateFlow(output.output,observer);
	}
	
	@Override
	public String toString() {
		
		String s= "["+this.getName()+"]"+this.getClass().getSimpleName()+"->";
		
		return s;
	}
}
