package se.thematrix.infinispan.cache;

public class CachedVehicle {
	
	private String vin;
	private String license;
	
	public CachedVehicle(String vin, String license) {
		super();
		this.vin = vin;
		this.license = license;
	}

	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}

	@Override
	public String toString() {
		return "CachedVehicle [vin=" + vin + ", license=" + license + "]";
	}
}
