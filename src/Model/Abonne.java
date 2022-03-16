package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Abonne {

	private int id;
	private String nom;
	private String prenom;
	private String tele;
	private double paiement;
	private  String etat;
	public String getEtat() {
		int n=Traitement.isPaye(this);
		if(n==1) etat="Payé";
		else etat="Non Payé";
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	private Date dernierPaiem=null;

	public Date getDernierPaiem() {
		return dernierPaiem;
	}

	public void setDernierPaiem(Date dernierPaiem) {
		this.dernierPaiem = dernierPaiem;
	}

	@Override
	public String toString() {
		return "Abonne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tele=" + tele + ", paiement=" + paiement
				+ ", date=" + date + ", indexDate=" + indexDate + "]";
	}

	public double getPaiement() {
		return paiement;
	}

	public void setPaiement(double paiement) {
		this.paiement = paiement;
	}

	private ArrayList<Date> date;
	private ArrayList<Integer> indexDate;

	public ArrayList<Integer> getIndexDate() {
		return indexDate;
	}

	public void setIndexDate(ArrayList<Integer> indexDate) {
		this.indexDate = indexDate;
	}

	public Abonne() {
		date = new ArrayList<Date>();
		indexDate = new ArrayList<Integer>();
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public ArrayList<Date> getDate() {
		return date;
	}

	public void setDate(ArrayList<Date> date) {
		this.date = date;
		dernierPaiem=date.get(0);
	}

}
