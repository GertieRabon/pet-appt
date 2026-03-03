package com.gabriel.appoms.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "appointment_data")
public class AppointmentData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date lastUpdated;


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date created;

}
