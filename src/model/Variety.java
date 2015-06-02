package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Variety implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// 需要的Variables:
	// varietyID varietyName crop? cropName registerationID yearOfRegisteration issuedBy createdBy varietySource isTransgenic type 
	// genoType maturityType suitableArea tatNeeded fullCycleDuration typicalYield nationalStandard characteristics yieldPerformance
	// antiDisaster antiKaline antiDrought antiLowTemp antiDrop 
	// TECHNICAL PARAMETERS: chucaoRate perfectRate ebailiRate ebaiDegree flourRate density lengthToWidth hasFlavor
	// other
	
	private Long varietyId;
	private String varietyName;
	private Crop crop;
	private String other;
// 	Data from source file, core variables lossless information	
	private String cropName;
	private String registerationID;
	private int yearOfRegisteration;
	private String issuedBy;
	private String createdBy;
	private String varietySource;
	private boolean isTransgenic;
	private String suitableArea;
	private String characteristics;
	private String yieldPerformance;
// 	Derived entries
	private String type;
	private String genoType;
	private String maturityType;
	private int tatNeeded;
	private double fullCycleDuration;
	private int typicalYield;
	private String nationalStandard;
	private String antiDisaster;
	private String antiKaline;
	private String antiDrought;
	private String antiLowTemp;
	private String antiDrop;
	// technical parameters
//	private double chucaoRate; // percentage
//	private double perfectRate; // percentage
//	private double ebailiRate; // percentage
//	private double ebaiDegree; // percentage
//	private String transparency;
//	private double flourRate; // percentage
//	private double protienRate; // percentage
//	private double density; // unit: mm
//	private double length; // unit: mm
//	private double lengthToWidth;
//	private boolean hasFlavor;
	
	public Variety (){}
	
	public Variety (String varietyName, Crop crop){
		this.varietyName = varietyName;
		this.crop = crop;
	}
	
	public Long getVarietyId() {
		return varietyId;
	}
	public void setVarietyId(Long varietyId) {
		this.varietyId = varietyId;
	}
	public String getVarietyName() {
		return varietyName;
	}
	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Crop getCrop() {
		return crop;
	}
	public void setCrop(Crop crop) {
		this.crop = crop;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getRegisterationID() {
		return registerationID;
	}

	public void setRegisterationID(String registerationID) {
		this.registerationID = registerationID;
	}

	public int getYearOfRegisteration() {
		return yearOfRegisteration;
	}

	public void setYearOfRegisteration(int yearOfRegisteration) {
		this.yearOfRegisteration = yearOfRegisteration;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getVarietySource() {
		return varietySource;
	}

	public void setVarietySource(String varietySource) {
		this.varietySource = varietySource;
	}

	public boolean isTransgenic() {
		return isTransgenic;
	}

	public void setTransgenic(boolean isTransgenic) {
		this.isTransgenic = isTransgenic;
	}

	public String getSuitableArea() {
		return suitableArea;
	}

	public void setSuitableArea(String suitableArea) {
		this.suitableArea = suitableArea;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public String getYieldPerformance() {
		return yieldPerformance;
	}

	public void setYieldPerformance(String yieldPerformance) {
		this.yieldPerformance = yieldPerformance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenoType() {
		return genoType;
	}

	public void setGenoType(String genoType) {
		this.genoType = genoType;
	}

	public String getMaturityType() {
		return maturityType;
	}

	public void setMaturityType(String maturityType) {
		this.maturityType = maturityType;
	}

	public int getTatNeeded() {
		return tatNeeded;
	}

	public void setTatNeeded(int tatNeeded) {
		this.tatNeeded = tatNeeded;
	}

	public double getFullCycleDuration() {
		return fullCycleDuration;
	}

	public void setFullCycleDuration(double fullCycleDuration) {
		this.fullCycleDuration = fullCycleDuration;
	}

	public int getTypicalYield() {
		return typicalYield;
	}

	public void setTypicalYield(int typicalYield) {
		this.typicalYield = typicalYield;
	}

	public String getNationalStandard() {
		return nationalStandard;
	}

	public void setNationalStandard(String nationalStandard) {
		this.nationalStandard = nationalStandard;
	}

	public String getAntiDisaster() {
		return antiDisaster;
	}

	public void setAntiDisaster(String antiDisaster) {
		this.antiDisaster = antiDisaster;
	}

	public String getAntiKaline() {
		return antiKaline;
	}

	public void setAntiKaline(String antiKaline) {
		this.antiKaline = antiKaline;
	}

	public String getAntiDrought() {
		return antiDrought;
	}

	public void setAntiDrought(String antiDrought) {
		this.antiDrought = antiDrought;
	}

	public String getAntiLowTemp() {
		return antiLowTemp;
	}

	public void setAntiLowTemp(String antiLowTemp) {
		this.antiLowTemp = antiLowTemp;
	}

	public String getAntiDrop() {
		return antiDrop;
	}

	public void setAntiDrop(String antiDrop) {
		this.antiDrop = antiDrop;
	}
	
}
