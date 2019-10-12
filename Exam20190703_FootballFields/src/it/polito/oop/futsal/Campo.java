package it.polito.oop.futsal;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import it.polito.oop.futsal.Fields.Features;

public class Campo implements FieldOption {
	
	private boolean indoor;
	private boolean heating;
	private boolean ac;
	private int codice;
	private Map<String,Integer> prenotato= new TreeMap<>();
	
	
	
	public Campo(int codice,Features f) {
		this.indoor=f.indoor;
		this.heating=f.heating;
		this.ac=f.ac;
		this.codice=codice;
	}
	
	public boolean getIndoor(){
		return indoor;
	}

	public int getCodice() {
		return codice;
	}

	
	public void Prenota(String orario,int codCliente) {
		prenotato.put(orario, codCliente);
	}
	
	public boolean isPrenotato(String orario) {
		return prenotato.keySet().stream().anyMatch(t->t.equals(orario));
	}
	
	public int nPrenotazioni() {
		return prenotato.size();
	}
	
	public long nPrenotazioniLong() {
		return (long) prenotato.size();
	}
	
	
	public List<Integer> associatiPrenotazioni(){
		return prenotato.values().stream().collect(Collectors.toList());
	}
	
	public List<Integer> getPrenotato(){
		return prenotato.values().stream().collect(Collectors.toList());
	}

	@Override
	public int getField() {
		// TODO Auto-generated method stub
		return codice;
	}

	@Override
	public int getOccupation() {
		// TODO Auto-generated method stub
		return prenotato.size();
	}
	
	public boolean rispetta(Features required) {
		if(required.indoor)
			if((this.indoor!=required.indoor) || (required.heating && required.heating!=this.heating) || (required.ac && required.ac != this.ac)) 
				return false;
		
		if(required.heating)
			if(this.heating==required.heating)
				return true;
		
		if(required.ac)
			if(this.ac==required.ac)
				return true;

		return true;
	}
}
