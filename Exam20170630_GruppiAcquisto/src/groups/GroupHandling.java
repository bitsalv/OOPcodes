package groups;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class GroupHandling {
//R1	
	
	private Map<String,Product> prodotti= new TreeMap<>();
	private Map<String,Supplier> fornitori= new TreeMap<>();
	private Map<String,Group> gruppi= new TreeMap<>();
	
	public void addProduct (String productTypeName, String... supplierNames) throws GroupHandlingException {
		if(prodotti.containsKey(productTypeName)) throw new GroupHandlingException();
		prodotti.put(productTypeName, new Product(productTypeName, supplierNames));
		
		for (String fornitore : supplierNames) {
			if(!fornitori.containsKey(fornitore)) {
				fornitori.put(fornitore, new Supplier(fornitore,productTypeName));
			}
			else {
					fornitori.get(fornitore).addProdotto(productTypeName);
				}
		}
	}
	
	public List<String> getProductTypes (String supplierName) {
		return fornitori.get(supplierName).getProdotti();
	}
	
//R2	
	public void addGroup (String name, String productName, String... customerNames) throws GroupHandlingException {
		if(gruppi.containsKey(name)) throw new GroupHandlingException("Gruppo duplicato");
		if(!prodotti.containsKey(productName)) throw new GroupHandlingException("Prodotto inesistente");
		gruppi.put(name, new Group(name, productName, customerNames));
	}
	
	public List<String> getGroups (String customerName) {
        return gruppi.values().stream().filter(t->t.getCustomerNames().stream().anyMatch(x->x.equals(customerName))).map(t->t.toString()).sorted().collect(Collectors.toList());
	}

//R3
	public void setSuppliers (String groupName, String... supplierNames) throws GroupHandlingException {
		if(!gruppi.containsKey(groupName)) throw new GroupHandlingException("Gruppo inesistente");
		for (String fornitore : supplierNames) {
			if(!fornitori.containsKey(fornitore)) throw new GroupHandlingException("Fornitore inseistente: "+fornitore);
			if(!fornitori.get(fornitore).getProdotti().stream().anyMatch(x->x.equals(prodotti.get(gruppi.get(groupName).getProdotto()).toString()))) throw new GroupHandlingException("Il fornitore:"+fornitore+" non tratta il prodotto richiesto");
		}
		
		gruppi.get(groupName).addFornitori(supplierNames);
		
	}
	
	public void addBid (String groupName, String supplierName, int price) throws GroupHandlingException {
		if(!gruppi.get(groupName).getFornitori().stream().anyMatch(t->t.equals(supplierName))) throw new GroupHandlingException("Fornitore non collegato al gruppo");
		gruppi.get(groupName).addOfferta(supplierName, price);
		fornitori.get(supplierName).nBids++;
	}
	
	public String getBids (String groupName) {
        return gruppi.get(groupName).getOfferte();
	}
	
	
//R4	
	public void vote (String groupName, String customerName, String supplierName)throws GroupHandlingException {
		if(!gruppi.get(groupName).getCustomerNames().stream().anyMatch(t->t.equals(customerName))) throw new GroupHandlingException("Il cliente non partecipa al gruppo:"+customerName);
		if(!gruppi.get(groupName).getFornitoriOfferta().stream().anyMatch(t->t.equals(supplierName))) throw new GroupHandlingException("Il fornitore non ha presentato un'offerta per il gruppo"+supplierName);
		
		gruppi.get(groupName).getOfferta(supplierName).nVotes++;
		
	}
	
	public String  getVotes (String groupName) {
		return gruppi.get(groupName).getVoti();	
}
	
	public String getWinningBid (String groupName) {
        return gruppi.get(groupName).getVincitore();
	}
	
//R5
	public SortedMap<String, Integer> maxPricePerProductType() { //serve toMap
        return gruppi.values().stream().flatMap(t->t.getOfferteS().stream()).collect(Collectors.toMap(Offerta::getProductName, Offerta::getPrice,(p1,p2)-> { return p1>=p2 ? p1:p2;},TreeMap::new));
	}
	
	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
        //return gruppi.values().stream().filter(t->t.getNumeroOfferte()>0).flatMap(t->t.getFornitori().stream()).collect(Collectors.groupingBy(Supplier::getNumBids,()-> new TreeMap<Integer, List<String>>(Comparator.reverseOrder()),Collectors.toList()));
		return null;
	}
	
	public SortedMap<String, Long> numberOfCustomersPerProductType() {
        return gruppi.values().stream().filter(t->t.getCustomerNames().size()>0).collect(Collectors.groupingBy(Group::getProdotto,TreeMap::new,Collectors.summingLong(t->t.getCustomerNames().size())));
	}
	
}
