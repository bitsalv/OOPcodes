package clinic;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dottore extends Paziente {

	
	private String specialization;
	private int badgeNumber;
	private int numeroPazienti=0;
	private Map<String,Paziente> pazienti= new HashMap<>();
	
	public Dottore (String first, String last, String ssn, int docID, String specialization ) {
		super(first,last,ssn);
		this.badgeNumber=docID;
		this.specialization=specialization;
	}
	
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getBadgeNumber() {
		return badgeNumber; 
	}
	public void setSpecialization(String specialization) {
		this.specialization=specialization;
	}
	public String getSpecialization() {
		return specialization;
	}
	
	public void addPaziente(Paziente p) {
		pazienti.put(p.getSsn(),p);
		numeroPazienti++;
	}
	
	public Paziente getPaziente(String ssn) {
		return pazienti.get(ssn);
	}
	
	public Collection<Paziente> getPazienti() {
		
		return pazienti.values();
	}
	
	public int getNumeroPazienti() {
		return numeroPazienti;
	}
	
	
	public String toStringFormattato() {
		return String.format("%3d", numeroPazienti)+ ": "+badgeNumber+" "+lastName+" "+firstName;
	}
	
	@Override public String toString() {
		return this.lastName+" "+this.firstName+" "+"("+this.ssn+")"+" "+"["+this.badgeNumber+"]"+":"+" "+this.specialization;
	}
	
	
	public String getName() {
		return lastName+" "+firstName;
	}
	
	
	
}
