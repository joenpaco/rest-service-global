package com.joenpaco.models.dtos;

import org.springframework.stereotype.Component;

@Component
public class Phone {

	private String number;

	private String citycode;

	private String countrycode;

	private Long PhoneId;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public Long getPhoneId() {
		return PhoneId;
	}

	public void setPhoneId(Long PhoneId) {
		this.PhoneId = PhoneId;
	}

}
