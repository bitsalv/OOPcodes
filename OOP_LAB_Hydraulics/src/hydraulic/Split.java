package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {

	/**
	 * Constructor
	 * @param name
	 */
	private final int numOfSplit=2;
	private Element[] outputs = new Element [numOfSplit];
	
	
	public Split(String name) {
		super(name);
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
    	return outputs;
    }
    
  

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){

		if(noutput==0 || noutput==1) {
		outputs[noutput]=elem;
		}
	}
	
	
	@Override public void calculateFlow(Element output,SimulationObserver observer) {
		
		outputs[0].setOutputFlow(this.outputFlow/2); //entrata nello split
		outputs[1].setOutputFlow(this.outputFlow/2); //entrata nello split

		observer.notifyFlow(this.getClass().getSimpleName(), this.getName(), outputs[0].outputFlow*2, outputs[0].outputFlow); //notifico per uno, tanto è uguale per entrambi

		outputs[0].calculateFlow(outputs[0].output,observer); //chiamata ricorsiva per i due rami creati
		outputs[1].calculateFlow(outputs[1].output,observer); //come sopra
		
		
	}
	
	
	@Override
	public String toString() {
		
		String s= "["+this.getName()+"]"+this.getClass().getSimpleName()+"+->";
		
		return s;
	}
	
}
