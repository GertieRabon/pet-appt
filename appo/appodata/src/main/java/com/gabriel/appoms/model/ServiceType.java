package com.gabriel.appoms.model;
import lombok.Data;
import java.util.Date;

@Data
public class ServiceType{
	private int id;
	private String name;
	private Date lastUpdated;
	private Date created;
}
