package org.devmobile.myapplicationrecyclerview;

public class Adherent {
    String idAdherent;
    String nom;
    String tel;
    public Adherent() {
    }
    public Adherent(String idAdherent, String nom, String tel) {
        this.idAdherent = idAdherent;
        this.nom = nom;
        this.tel = tel;
    }
    public String getIdAdherent() {
        return idAdherent;
    }
    public String getNom() {
        return nom;
    }
    public String getTel() {
        return tel;
    }
    public void setIdAdherent(String idAdherent) {
        this.idAdherent = idAdherent;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}
