package com.reunium.blog.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Registration {

	@JsonProperty("FormID")
	private String formID;

	@JsonProperty("UniqueID")
	private String uniqueID;

	private String personal_information;

	private Name name;

	private Address address;

	private String email;

	private String event_information;

	private String will_you_be_attending;

	private String number_attending;

	private String amount;

	private String upload;

	public void setFormID(String formID) {
		this.formID = formID;
	}

	public String getFormID() {
		return this.formID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getUniqueID() {
		return this.uniqueID;
	}

	public void setPersonal_information(String personal_information) {
		this.personal_information = personal_information;
	}

	public String getPersonal_information() {
		return this.personal_information;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Name getName() {
		return this.name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEvent_information(String event_information) {
		this.event_information = event_information;
	}

	public String getEvent_information() {
		return this.event_information;
	}

	public void setWill_you_be_attending(String will_you_be_attending) {
		this.will_you_be_attending = will_you_be_attending;
	}

	public String getWill_you_be_attending() {
		return this.will_you_be_attending;
	}

	public void setNumber_attending(String number_attending) {
		this.number_attending = number_attending;
	}

	public String getNumber_attending() {
		return this.number_attending;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getUpload() {
		return this.upload;
	}
}