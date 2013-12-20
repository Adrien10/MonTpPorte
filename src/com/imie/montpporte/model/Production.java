package com.imie.montpporte.model;

public class Production {

	private int id;
	protected Commande commande;
	protected int nOrdre;
	protected Zone stationCourante;
	
	public Production() {
		// TODO Auto-generated constructor stub
	}
	
	public Production(Commande commande , int nordre) {
		this.commande 	= commande;
		this.nOrdre 	= nordre;
		this.stationCourante = null;
	}
	
	/**
	 * get id production
	 * @return int
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public int getnOrdre() {
		return nOrdre;
	}

	public void setnOrdre(int nOrdre) {
		this.nOrdre = nOrdre;
	}

	public Zone getStationCourante() {
		return stationCourante;
	}

	public void setStationCourante(Zone stationCourante) {
		this.stationCourante = stationCourante;
	}

	@Override
	public String toString() {
		return  this.getCommande().getId() + "|" + this.getnOrdre();
	}

}
