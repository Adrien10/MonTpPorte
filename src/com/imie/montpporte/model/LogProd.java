package com.imie.montpporte.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class LogProd {

	private int id;
	private String moment;
	private Date date;
	private int ligneproduction;
	private int user;
	private int zone;
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public LogProd() {
		// TODO Auto-generated constructor stub
	}
	public LogProd(String moment, int ligneproduction,
			int user, int zone) {
		this.moment = moment;
		try {
			this.date = df.parse(df.format(gc.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		this.ligneproduction = ligneproduction;
		this.user = user;
		this.zone = zone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMoment() {
		return moment;
	}

	public void setMoment(String moment) {
		this.moment = moment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLigneproduction() {
		return ligneproduction;
	}

	public void setLigneproduction(int ligneproduction) {
		this.ligneproduction = ligneproduction;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

}
