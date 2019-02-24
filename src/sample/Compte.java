package sample;

public class Compte {

    private String nom;
    private String mdp;
    private Integer solde;
    private Integer retrait;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public Integer getRetrait() {
        return retrait;
    }

    public void setRetrait(Integer retrait) {
        this.retrait = retrait;
    }
}
