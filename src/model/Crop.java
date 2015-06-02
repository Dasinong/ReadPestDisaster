package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Crop implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long cropId;
	private String cropName;
	private Set<Variety> varieties = new HashSet<Variety>();
	private Set<PetDisSpec> petDisSpecs = new HashSet<PetDisSpec>();
	private String other;
	
	public Crop(){}
	public Crop(String cropName){
		this.cropName = cropName;
	}
	
	public Long getCropId() {
		return cropId;
	}
	public void setCropId(Long cropId) {
		this.cropId = cropId;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Set<Variety> getVarieties() {
		return varieties;
	}
	public void setVarieties(Set<Variety> varieties) {
		this.varieties = varieties;
	}
	public Set<PetDisSpec> getPetDisSpecs() {
		return petDisSpecs;
	}
	public void setPetDisSpecs(Set<PetDisSpec> petDisSpecs) {
		this.petDisSpecs = petDisSpecs;
	}

}
