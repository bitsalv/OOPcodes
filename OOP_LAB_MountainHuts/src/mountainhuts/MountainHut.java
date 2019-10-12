package mountainhuts;

import java.util.Optional;

public class MountainHut {
	
	
	private String name;
	private String category;
	private Integer bedsNumber;
	private Municipality municipal;
	private Optional<Integer> altitude;
	
	
	public MountainHut(String name, Optional<Integer> altitude, String category, Integer bedsNumber, Municipality municipal) {
		this.name=name;
		this.altitude=altitude;
		this.category=category;
		this.bedsNumber=bedsNumber;
		this.municipal=municipal;
	}
	
	/*
	public MountainHut(String name, String category, Integer bedsNumber, Municipality municipal) {
		this.name=name;
		this.category=category;
		this.bedsNumber=bedsNumber;
		this.municipal=municipal;
	}
	*/
	
	public String getName() {
		return name;
	}

	public Optional<Integer> getAltitude() {
		return altitude;
	}

	public String getCategory() {
		return category;
	}

	public Integer getBedsNumber() {
		return bedsNumber;
	}

	public Municipality getMunicipality() {
		return municipal;
	}

}
