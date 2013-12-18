package com.imie.montpporte.model;

public class Production {

	private int id;
	protected int commande;
	protected int nOrdre;
	
	public Production() {
		// TODO Auto-generated constructor stub
	}
	
	public Production(Commande commande , int nordre) {
		this.commande 	= commande.getId();
		this.nOrdre 	= nordre;
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

	public int getNOrdre() {
		return nOrdre;
	}

	public void setNOrdre(int nordre) {
		this.nOrdre = nordre;
	}

}
