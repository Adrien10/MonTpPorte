package com.imie.montpporte.model;



public class Commande {

	private int id;
	protected int quantite;
	protected int typeItem;
	protected String materiaux;
	protected String idClient;

	public Commande() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getTypeItem() {
		return typeItem;
	}

	public void setTypeItem(int typeItem) {
		this.typeItem = typeItem;
	}

	public String getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(String materiaux) {
		this.materiaux = materiaux;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

}
