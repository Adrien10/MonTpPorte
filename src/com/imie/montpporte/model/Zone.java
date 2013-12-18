package com.imie.montpporte.model;

import java.io.Serializable;


public class Zone implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8593042547455909477L;
	/**
	 * Zone
	 */
	
	protected int id;
	protected String nom;
	protected int quantite_tampon;
	protected int etat;
	
	public Zone() {
	}
	
	public Zone(String nom, int quantite_tampon) {
		this.nom = nom;
		this.quantite_tampon = quantite_tampon;
		this.etat=0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nom;
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

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	

}
