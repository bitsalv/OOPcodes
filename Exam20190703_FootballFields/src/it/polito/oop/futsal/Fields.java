package it.polito.oop.futsal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {
    
    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }
    }
    
    
   
    private Map<Integer,Campo> campi= new TreeMap<>();
    private Map<Integer,Soci> soci= new TreeMap<>();
    private List<Integer> prenotazioni= new ArrayList<>();
    private int contatoreSoci=1;
    private int contatore=1;
    private int openTimeInt;
    private int closeTimeInt;
    private int openTimeOre;
    private int openTimeMinuti;
    private int closeTimeOre;
    private int closeTimeMinuti;
    private String openTimeString;
    private String closeTimeString;
  
    
    public void defineFields(Features... features) throws FutsalException {
    	
    	for (Features caratteristiche : features) {
			if(caratteristiche.indoor==false && (caratteristiche.heating==true || caratteristiche.ac==true)) throw new FutsalException();
			campi.put(contatore, new Campo(contatore,caratteristiche));
			contatore++;
    	}
    		
    }
    
    public long countFields() {
        return (long) campi.size();
    }

    public long countIndoor() {
        return campi.values().stream().filter(t->t.getIndoor()==true).count();
    }
    
    public String getOpeningTime() {
        return openTimeString;
    }
    
    public void setOpeningTime(String time) {
    	
    		openTimeString=time;
			String splitter []= time.split(":");
			this.openTimeOre= Integer.parseInt(splitter[0]);
			this.openTimeMinuti= Integer.parseInt(splitter[1]);
		this.openTimeInt=openTimeMinuti+openTimeOre*60;
    	
    }
    
    public String getClosingTime() {
        return closeTimeString;
    }
    
    public void setClosingTime(String time) {
    	closeTimeString=time;
    	String splitter []= time.split(":");
		this.closeTimeOre= Integer.parseInt(splitter[0]);
		this.closeTimeMinuti= Integer.parseInt(splitter[1]);
		this.closeTimeInt=closeTimeMinuti+closeTimeOre*60;
    }

    public int newAssociate(String first, String last, String mobile) {
    	soci.put(contatoreSoci, new Soci(first, last, mobile, contatoreSoci));
    	contatoreSoci++;
        return contatoreSoci-1;
    }
    
    public String getFirst(int partyId) throws FutsalException {
    	if(!soci.containsKey(partyId)) throw new FutsalException();
        return soci.get(partyId).getNome();
    }
    
    public String getLast(int associate) throws FutsalException {
    	if(!soci.containsKey(associate)) throw new FutsalException();
        return soci.get(associate).getCognome();
    }
    
    public String getPhone(int associate) throws FutsalException {
    	if(!soci.containsKey(associate)) throw new FutsalException();
        return soci.get(associate).getTelefono();
    }
    
    public int countAssociates() {
        return soci.size();
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
    	if(!campi.containsKey(field)) throw new FutsalException();
    	if(!soci.containsKey(associate)) throw new FutsalException();
    	String splitter []= time.split(":");
		int prenotationTimeOre= Integer.parseInt(splitter[0]);
		int prenotationTimeMinuti= Integer.parseInt(splitter[1]);
		if(prenotationTimeMinuti!=openTimeMinuti) throw new FutsalException();
		
		campi.get(field).Prenota(time, associate);
		
		prenotazioni.add(associate);
		
    }

    public boolean isBooked(int field, String time) {
        return campi.get(field).isPrenotato(time);
    }
    

    public int getOccupation(int field) {
        return campi.get(field).nPrenotazioni();
    }
    
    
    public List<FieldOption> findOptions(String time, Features required){
        return campi.values().stream().filter(t->!t.isPrenotato(time)).filter(t->t.rispetta(required)).sorted(Comparator.comparing(Campo::getOccupation,Comparator.naturalOrder()).thenComparing(Comparator.comparing(Campo::getCodice,Comparator.naturalOrder()))).collect(Collectors.toList());
    }
    
    public long countServedAssociates() {
        return prenotazioni.stream().distinct().count() ;
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return campi.values().stream().collect(Collectors.toMap(Campo::getCodice, Campo::nPrenotazioniLong));
    }
    
    public double occupation() {
    	
    	int nPrenotazioniTotali=campi.values().stream().collect(Collectors.summingInt(t->t.nPrenotazioni()));
    	double nBlocchiOra;
    	
    	if(closeTimeMinuti<openTimeMinuti)
    		  nBlocchiOra=(closeTimeOre-openTimeOre-1)*campi.size();
    	   
    	      nBlocchiOra=(closeTimeOre-openTimeOre)*campi.size();

     	double d= (double) (nPrenotazioniTotali/nBlocchiOra);

        return d;
    }
    
}
