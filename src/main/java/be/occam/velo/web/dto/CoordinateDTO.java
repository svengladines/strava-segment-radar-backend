package be.occam.velo.web.dto;


public class CoordinateDTO {
	
	protected double longitude;
	protected double latitude;
	
	public double getLongitude() {
		return longitude;
	}
	public CoordinateDTO setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
	public double getLatitude() {
		return latitude;
	}
	public CoordinateDTO setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

}
