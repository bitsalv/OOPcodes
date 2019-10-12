package clinic;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;




/**
 * Represents a clinic with patients and doctors.
 * 
 */



public class Clinic {
		
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	
	
	private Map<String,Paziente> pazienti= new HashMap<>();
	private Map<Integer,Dottore> dottori= new HashMap<>();
	
	
	public void addPatient(String first, String last, String ssn) {
		
		if(pazienti.containsKey(ssn)) {
			pazienti.get(ssn).setFirstName(first);
			pazienti.get(ssn).setLastName(last);
		} else {
			pazienti.put(ssn, new Paziente(first,last,ssn));
		}
		
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		
		if(pazienti.containsKey(ssn)) {
		return pazienti.get(ssn).toString();
		}
		throw new NoSuchPatient();
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		Dottore d= new Dottore(first, last, ssn, docID, specialization);
		dottori.put(docID, d);
		Paziente p= d;
		pazienti.put(ssn, p);
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		
			if(dottori.containsKey(docID)) {
				return dottori.get(docID).toString();
		}
			throw new NoSuchDoctor();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {


		if(dottori.containsKey(docID) && pazienti.containsKey(ssn)) {
			dottori.get(docID).addPaziente(pazienti.get(ssn));
			pazienti.get(ssn).addDottore(dottori.get(docID));
			
		} else if(!dottori.containsKey(docID)) {
			throw new NoSuchDoctor();
		} else if(!pazienti.containsKey(ssn)) {
			throw new NoSuchPatient();
		}
		
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		
		if(pazienti.containsKey(ssn) && pazienti.get(ssn).getDottore().isPresent()) {
			Dottore d=(Dottore) pazienti.get(ssn).getDottore().get();
			return d.getBadgeNumber();
		} 
		if(!pazienti.containsKey(ssn)) {
			throw new NoSuchPatient();
		}
		throw new NoSuchDoctor();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {

		if(dottori.containsKey(id)) {
		
			Collection<String> c = dottori.get(id).getPazienti().stream().map(t-> t.getSsn()).collect(toList());
			return c;
		}
		throw new NoSuchDoctor() ;
	}

	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public void loadData(Reader reader) throws IOException {
		
		BufferedReader br= new BufferedReader(reader);

		try {
	
				String line = br.readLine();
				   
				
				String codF = "([a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1})";
				String P= "[P];([Aa-Zz]);([Aa-Zz]);"+codF+"\r\n"; 
				String M= "[M];([0-9]+);([Aa-Zz]);([Aa-Zz]);"+codF+";([Aa-Zz])\r\n"; 
				        
				Pattern patternP= Pattern.compile(P);
				Pattern patternM= Pattern.compile(M);
				        
				Matcher matcherP= patternP.matcher(P);
				Matcher matcherM= patternM.matcher(M);
				
				
				while(line!=null) {  
			          
			          while(matcherP.find() || matcherM.find()) {
			          
			            if(line.matches(P)) {
			          
			             Paziente p = new Paziente(matcherP.group(1),matcherP.group(2),matcherP.group(3));
			             pazienti.put(p.getSsn(), p);
			          
			            
			            } else if(line.matches(M)) {
			            
			             Dottore d = new Dottore(matcherM.group(2),matcherM.group(3),matcherM.group(4),Integer.parseInt(matcherM.group(1)),matcherP.group(5));
			             dottori.put(d.getBadgeNumber(), d);
			            
			            }
			         
			         }
			      }
			  }	
		
		catch (IOException er) {
			throw er;
		}
		
		finally {
			br.close();
		}
	
	}




	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
	
		return dottori.values().stream().filter(r->r.getPazienti().size()==0).sorted(comparing(d1-> d1.getName().toLowerCase(), naturalOrder())).collect(mapping(t->t.getBadgeNumber(), toList()));
		/*
		 * 		dottori.values().stream().filter(r->r.getNumeroPazienti()==0).collect(mapping(t->t.getBadgeNumber(), toList()));
		 */
	}
	
	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		
		Double media=dottori.values().stream().collect(averagingInt(t->t.getNumeroPazienti()));
		return dottori.values().stream().filter(t->t.getPazienti().size()>media).map(t->t.getBadgeNumber()).collect(toList());		
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		
		return dottori.values().stream().sorted(comparing(d->d.getPazienti().size(),reverseOrder())).map(t->t.toStringFormattato()).collect(toList());
		 
		}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		
		return 
		dottori.values().stream().collect(groupingBy(t->t.getSpecialization(),summingInt(t->t.getPazienti().size())))
		.entrySet().stream().sorted(comparing(Map.Entry::getKey,naturalOrder())).sorted(comparing(Map.Entry::getValue,reverseOrder()))
		.map(t->String.format("%3d",t.getValue()+" - "+t.getKey())).collect(toList());
	}
	
}
