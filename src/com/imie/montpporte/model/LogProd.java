package com.imie.montpporte.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class LogProd {

	private int id;
	private String moment;
	private Date date;
	private Production ligneproduction;
	private User user;
	private Zone zone;
	
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public LogProd() {
		// TODO Auto-generated constructor stub
	}
	public LogProd(String moment, Production ligneproduction,
			User user, Zone zone) {
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

	public Production getLigneproduction() {
		return ligneproduction;
	}

	public void setLigneproduction(Production ligneproduction) {
		this.ligneproduction = ligneproduction;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
	@Override
	public String toString() {
	
		String resultLog = "LOG_PRODUCTION : Commande n° " +
		this.getLigneproduction().getCommande().getId() +
		" ligne de production n° " + 
		this.getLigneproduction().getnOrdre() +
		"/" +
		this.getLigneproduction().getCommande().getQuantite() +
		" " +
		this.getMoment() +
		" à " +
		this.getDate().toString();
		return resultLog;
	}

}
