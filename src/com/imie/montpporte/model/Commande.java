package com.imie.montpporte.model;



public class Commande {

	private int id;
	protected int quantite;
	protected String typeItem;
	protected String materiaux;
	protected int idClient;

	public Commande() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor of Commande
	 * @param quantite
	 * @param typeIetem
	 * @param materiaux
	 * @param idclient
	 */
	public  Commande(int quantite, String typeitem, String materiaux, int idclient) {
		this.quantite 	= quantite;
		this.typeItem 	= typeitem;
		this.materiaux 	= materiaux;
		this.idClient 	= idclient;

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

	public String getTypeItem() {
		return typeItem;
	}

	public void setTypeItem(String typeItem) {
		this.typeItem = typeItem;
	}

	public String getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(String materiaux) {
		this.materiaux = materiaux;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	@Override
	public String toString() {
		return  "Commande n°" + this.getId();
	}

}
