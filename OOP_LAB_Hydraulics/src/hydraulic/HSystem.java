package hydraulic;
import static hydraulic.SimulationObserver.exists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem implements SimulationObserver {
	
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	private List<Element> elementList = new LinkedList<Element>();
	private Element[] elementArray;
	private Element[] out=new Element [2];
	
	protected Element prossimo= new Element("prossimo");
	int 	countNotifications=0;

	
	public void addElement(Element elem){
		// TODO: to be implemented
		elementList.add(elem);
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to the number of added elements
	 */
	public Element[] getElements(){
		// TODO: to be implemented
		elementArray=(Element[]) elementList.toArray(new Element[elementList.size()]);
		return elementArray;
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		
		String s=" ";
		
		
		
		
		return s;
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		
		for(int i=0;i<elementArray.length;i++) {
			if(elementArray[i] instanceof Source) {
				elementArray[i].calculateFlow(elementArray[i].getOutput(),observer);
			}
		}
	}


	@Override
	public void notifyFlow (String type, String name, double inFlow, double outFlow) {
		// TODO Auto-generated method stub
		System.out.println(type + " " + name + ": ");
		if(exists(inFlow)) System.out.println("\t-> in flow=" + inFlow);
		if(exists(outFlow)) System.out.println("\t<- out flow=" + outFlow);
		countNotifications++;
		
	}

}
