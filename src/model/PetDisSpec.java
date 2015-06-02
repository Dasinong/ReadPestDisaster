package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PetDisSpec implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long petDisSpecId;
	private String petDisSpecName;
	private Set<PetSolu> petSolus = new HashSet<PetSolu>();
	
	private String type;
	private String alias;
	private int severity;
	private int commonImpactonYield;
	private int maxImpactonYield;
	private String preventionorRemedy;
	private String indvidualorGroup;
	private String impactedArea;
	private String color;
	private String shape;
	private String description;
	private String sympton;
	private String form;
	private String habbits;
	private String rule;
	private String cropNames;
	
	public String toString(){
		String output = this.petDisSpecName+" "+this.type+" "+this.cropNames+"\n"+this.description+"\n"+this.sympton+"\n"+this.form+"\n"+this.habbits
						+"\n"+this.rule+"\n";
		Set<PetSolu> petSolus =  this.petSolus;
		for (PetSolu petSolu : petSolus) {
			output = output + petSolu.getPetSoluDes()+"\n";
		}
		output = output +"--------------------------------";
		return output;
	}
	
	public PetDisSpec(){}
	public PetDisSpec(String petDisSpecName){
		this.petDisSpecName = petDisSpecName;
	}
	
	public PetDisSpec(String petDisSpecName, String type, String alias,
			int severity, int commonImpactonYield, int maxImpactonYield,
			String preventionorRemedy, String indvidualorGroup,
			String impactedArea, String color, String shape, String description) {
		super();
		this.petDisSpecName = petDisSpecName;
		this.type = type;
		this.alias = alias;
		this.severity = severity;
		this.commonImpactonYield = commonImpactonYield;
		this.maxImpactonYield = maxImpactonYield;
		this.preventionorRemedy = preventionorRemedy;
		this.indvidualorGroup = indvidualorGroup;
		this.impactedArea = impactedArea;
		this.color = color;
		this.shape = shape;
		this.description = description;
	}
	
	
	public Long getPetDisSpecId() {
		return petDisSpecId;
	}
	public void setPetDisSpecId(Long petDisSpecId) {
		this.petDisSpecId = petDisSpecId;
	}
	public String getPetDisSpecName() {
		return petDisSpecName;
	}
	public void setPetDisSpecName(String petDisSpecName) {
		this.petDisSpecName = petDisSpecName;
	}
	public Set<PetSolu> getPetSolus() {
		return petSolus;
	}
	public void setPetSolus(Set<PetSolu> petSolus) {
		this.petSolus = petSolus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public int getCommonImpactonYield() {
		return commonImpactonYield;
	}
	public void setCommonImpactonYield(int commonImpactonYield) {
		this.commonImpactonYield = commonImpactonYield;
	}
	public int getMaxImpactonYield() {
		return maxImpactonYield;
	}
	public void setMaxImpactonYield(int maxImpactonYield) {
		this.maxImpactonYield = maxImpactonYield;
	}
	public String getPreventionorRemedy() {
		return preventionorRemedy;
	}
	public void setPreventionorRemedy(String preventionorRemedy) {
		this.preventionorRemedy = preventionorRemedy;
	}
	public String getIndvidualorGroup() {
		return indvidualorGroup;
	}
	public void setIndvidualorGroup(String indvidualorGroup) {
		this.indvidualorGroup = indvidualorGroup;
	}
	public String getImpactedArea() {
		return impactedArea;
	}
	public void setImpactedArea(String impactedArea) {
		this.impactedArea = impactedArea;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSympton() {
		return sympton;
	}
	public void setSympton(String sympton) {
		this.sympton = sympton;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getHabbits() {
		return habbits;
	}
	public void setHabbits(String habbits) {
		this.habbits = habbits;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getCropNames() {
		return cropNames;
	}
	public void setCropNames(String cropNames) {
		this.cropNames = cropNames;
	}
	
}
