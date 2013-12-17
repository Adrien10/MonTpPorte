package com.imie.montpporte.model;

public class Production {

	private int id;
	protected Commande commade;
	protected String typeProduit;
	
	public Production() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commande getCommade() {
		return commade;
	}

	public void setCommade(Commande commade) {
		this.commade = commade;
	}

	public String getTypeProduit() {
		return typeProduit;
	}

	public void setTypeProduit(String typeProduit) {
		this.typeProduit = typeProduit;
	}

}
