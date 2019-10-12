package groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.*;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Group {
	
	private String name;
	private String productName; 
	private List<String> customerNames=new ArrayList<>();
	private List<String> fornitori= new ArrayList<>();
	private Map<String,Offerta> offerte = new TreeMap<>();
	
	public Group(String name, String productName, String... customerNames) {
		this.name = name;
		this.productName = productName;
		this.customerNames.addAll(Arrays.asList(customerNames));
	}
	
	public List<String> getCustomerNames(){
		return customerNames.stream().sorted().collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void addFornitori(String fornitore) {
		fornitori.add(fornitore);
	}
	
	public void addFornitori(String... fornitori) {
		this.fornitori.addAll(Arrays.asList(fornitori));
	}
	
	public void addFornitori(List<String> fornitori) {
		this.fornitori.addAll(fornitori);
	}
	
	public String getProdotto() {
		return productName;
	}
	
	
	public void addOfferta(String fornitore, Integer prezzo) {
		offerte.put(fornitore, new Offerta(fornitore,prezzo,productName));
	}
	
	public void addVoto(String fornitore, Integer voto) {
	}
	
	public String getOfferte() {
		return offerte.entrySet().stream().sorted(comparing(t->t.getKey(),naturalOrder())).sorted(comparing(t->t.getValue().getPrice(),naturalOrder())).map(t->t.getKey()+":"+t.getValue().getPrice()).collect(Collectors.joining(","));		
	}
	
	public List<String> getFornitori(){
		return fornitori;
	}
	
	public List<String> getFornitoriOfferta(){
		return offerte.keySet().stream().collect(Collectors.toList());
	}
	
	public Offerta getOfferta(String fornitore) {
		return offerte.get(fornitore);
	}
	
	
	public String getVoti() {
		return offerte.entrySet().stream().filter(t->t.getValue().getNVotes()>0).map(t->t.getKey()+":"+t.getValue().getNVotes()).collect(Collectors.joining(","));		
	}
	
	public String getVincitore() {
		Offerta o=offerte.values().stream().max(Comparator.comparing(Offerta::getNVotes).thenComparing(Offerta::getPrice,reverseOrder())).orElse(null);
		if(o==null)
			return null;
		return o.getSupplierName()+":"+o.getNVotes();
	}
	
	public int getNumeroOfferte() {
		return offerte.size();
	}
	
	public Integer getMaxPrice(){
		return offerte.values().stream().max(Comparator.comparing(Offerta::getPrice)).map(Offerta::getPrice).orElse(0);
	}
	
	public List<Offerta> getOfferteS(){
		return offerte.values().stream().collect(Collectors.toList());
	}
}
