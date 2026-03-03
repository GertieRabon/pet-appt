package com.gabriel.appoms.model;
import lombok.Data;
import java.util.Date;

@Data
public class Appointment{
	private int id;
	private int ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhoneNumber;
	private String ownerAddress;
	private int petId;
	private String petName;
	private String petBreed;
	private int petAge;
	private String petMedicalNotes;
	private int groomerId;
	private String groomerName;
	private int serviceTypeId;
	private String serviceTypeName;
	private String serviceTypeDescription;
	private double servicePrice;
	private int apptStatusId;
	private String apptStatusName;
	private int apptId;
	private Date apptDate;
	private String apptNotes;
	private int ownerId;
	private int petId;
	private int groomerId;
	private int serviceTypeId;
	private int apptStatusId;
	private Date lastUpdated;
	private Date created;
	@Override
	public String toString(){
		return name;
	}
}
