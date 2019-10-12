package clinic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Paziente {

	protected String firstName;
	protected String lastName;
	protected String ssn;
	private Dottore dottore;
	
	public Paziente(String n, String c, String ssv) {
		this.ssn=ssn;
		this.firstName=n;
		this.lastName=c;

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addDottore(Dottore d) {
		this.dottore=d;
	}
	
	public Optional getDottore() {
		return Optional.of(dottore);
	}
	
	@Override public String toString() {
		return this.lastName+" "+this.firstName+" "+"("+this.ssn+")";
	}
}
