package com.imie.montpporte.model;


public class Zone  {

	/**
	 * Zone
	 */
	
	protected int id;
	protected String nom;
	protected int quantite_tampo;
	
	public Zone() {
	}
	
	public Zone(String nom, int quantite_tampo) {
		this.nom = nom;
		this.quantite_tampo = quantite_tampo;
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

	public int getQuantite_tampo() {
		return quantite_tampo;
	}

	public void setQuantite_tampo(int quantite_tampo) {
		this.quantite_tampo = quantite_tampo;
	}
	

}
