package hu.webuni.transport.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AddressDto {

	private Long id;
	@NotBlank
	private String iso;
	@NotBlank
	private String city;
	@NotBlank
	private String street;
	@Min(1000)
	private Integer zip;
	@NotBlank
	private String sn;
	
	private Float latitude;
	private Float longitude;
	
	public AddressDto() {
	}

	public AddressDto(Long id, String iso, String city, String street, Integer zip, String sn, Float latitude,
			Float longitude) {
		this.id = id;
		this.iso = iso;
		this.city = city;
		this.street = street;
		this.zip = zip;
		this.sn = sn;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", iso=" + iso + ", city=" + city + ", street=" + street + ", zip=" + zip + ", sn="
				+ sn + "]";
	}
	
	
	
	
}
