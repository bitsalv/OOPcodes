package mountainhuts;

import static java.util.stream.Collectors.toList;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import static java.util.Optional.*;


 
public class Region {

	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	
	private String nameRegion;
	private Collection<String> ranges = new ArrayList<>();
	private Map<String,Municipality> municipalities= new TreeMap<>();
	private Map<String,MountainHut> mountainHuts= new TreeMap<>();
	
	public Region(String name) {
		this.nameRegion=name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return nameRegion;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		for(int i=0;i<ranges.length;i++) {
			this.ranges.add(ranges[i]);
		}
	}
	
	

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
				
			for(String range: this.ranges) {
				String splitter []= range.split("-");
				int min= Integer.parseInt(splitter[0]);
				int max= Integer.parseInt(splitter[1]);
				if(altitude>=min && altitude<=max) 
					return range;
			}
		return "0-INF";
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities.values();
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mountainHuts.values();
	}
	
	
	

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		
		if(!municipalities.containsKey(name)) {
		municipalities.put(name, new Municipality(name, province, altitude));
		}
		return municipalities.get(name);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber, Municipality municipality) {
		return createOrGetMountainHut(name,Optional.empty(),category,bedsNumber,municipality);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 * 
	 */
	
	
	public MountainHut createOrGetMountainHut(String name, Optional<Integer> altitude, String category, Integer bedsNumber, Municipality municipality) {
		
		if(!mountainHuts.containsKey(name)) {
			mountainHuts.put(name,new MountainHut(name,altitude,category,bedsNumber,municipality));
		}
		return mountainHuts.get(name);	
		
	}
	
	
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,Municipality municipality) {
		
		return this.createOrGetMountainHut(name, Optional.ofNullable(altitude), category, bedsNumber, municipality);
	}

	
	
	/**
	 * Creates a new region and loads its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region region = new Region(name);
        region.readData(file);
        return region;
	}

	@SuppressWarnings("unused")
	private void readData(String file) {
		List<String> lines = null;

		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			lines = in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		if (lines == null)
			return;

		// Continue the implementation here		
		
		
        Iterator<String> iterator = lines.iterator();
        String riga = iterator.next();
        
        while(iterator.hasNext()) {
        	
        riga = iterator.next();
		String parole[]=riga.split(";");
			
			this.createOrGetMountainHut(parole[3],
					 					Optional.ofNullable(parole[4].isEmpty() ? null : Integer.parseInt(parole[4])), 
										parole[5], 
										Integer.parseInt(parole[6]), 
										this.createOrGetMunicipality(parole[1], 
																	 parole[0], 
																	 Integer.parseInt(parole[2])));
		}
		
		
		/*for(int i=1;i<lines.size();++i) {
			String riga=lines.get(i);
			String parole[]=riga.split(";");
			this.createOrGetMountainHut(parole[3],
 					Optional.ofNullable(parole[4].isEmpty() ? null : Integer.parseInt(parole[4])), 
					parole[5], 
					Integer.parseInt(parole[6]), 
					this.createOrGetMunicipality(parole[1], 
												 parole[0], 
												 Integer.parseInt(parole[2])));
		}*/
	}
		
		
	

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	
	
	
	public Map<String, Long> countMunicipalitiesPerProvince() {
			return this.getMunicipalities().stream()
					.collect(groupingBy((s)->s.getProvince(),counting()));
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		return this.getMountainHuts().stream().collect(groupingBy(s->s.getMunicipality().getProvince(),groupingBy(t->t.getMunicipality().getName(),counting())));
		
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		 	
		return this.getMountainHuts().stream().collect(groupingBy(t-> this.getAltitudeRange((t.getAltitude().orElse(t.getMunicipality().getAltitude()))),counting()));

	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		
		return	this.getMountainHuts().stream().collect(groupingBy(t->t.getMunicipality().getProvince(),summingInt(r->r.getBedsNumber()))); 
				
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */

	 public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		 
		return this.getMountainHuts().stream().collect(groupingBy(t->this.getAltitudeRange(t.getAltitude().orElse(t.getMunicipality().getAltitude())),mapping(t->t.getBedsNumber(), maxBy((t,r)-> t-r))));
		 
	 }
	
	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
	
		
		return this.getMountainHuts()
			.stream()
			.map(t->t.getMunicipality().getName())
			.collect(groupingBy(x ->x,TreeMap::new,counting()))
						.entrySet()
						.stream()
						.collect(groupingBy(Map.Entry::getValue,mapping(Map.Entry::getKey,toList())));
			
					

	}

}
