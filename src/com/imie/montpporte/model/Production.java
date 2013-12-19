package com.imie.montpporte.model;

public class Production {

	private int id;
	protected int commande;
	protected int nOrdre;
	protected int stationCourante;
	
	public Production() {
		// TODO Auto-generated constructor stub
	}
	
	public Production(Commande commande , int nordre) {
		this.commande 	= commande.getId();
		this.nOrdre 	= nordre;
		this.stationCourante = 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommande() {
		return commande;
	}

	public void setCommande(int commande) {
		this.commande = commande;
	}

	public int getnOrdre() {
		return nOrdre;
	}

	public void setnOrdre(int nOrdre) {
		this.nOrdre = nOrdre;
	}

	public int getStationCourante() {
		return stationCourante;
	}

	public void setStationCourante(int stationCourante) {
		this.stationCourante = stationCourante;
	}

	@Override
	public String toString() {
		return  this.getCommande() + "|" + this.getnOrdre();
	}

}
