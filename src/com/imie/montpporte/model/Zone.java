package com.imie.montpporte.model;


public class Zone  {

	/**
	 * Zone
	 */
	
	protected int id;
	protected String nom;
	protected int quantite_tampon;
	protected Boolean etat;
	
	public Zone() {
	}
	
	public Zone(String nom, int quantite_tampon) {
		this.nom = nom;
		this.quantite_tampon = quantite_tampon;
		this.etat=false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantite_tampon() {
		return quantite_tampon;
	}

	public void setQuantite_tampon(int quantite_tampon) {
		this.quantite_tampon = quantite_tampon;
	}

	public Boolean getEtat() {
		return etat;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	

}
