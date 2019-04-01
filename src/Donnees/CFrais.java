/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

/**
 *
 * @author camilletilmont
 */
public class CFrais {
    //attributs
    
    protected int quantiteFrais;
    protected float montantFrais;
    protected CTypeFrais typeFrais;

  

    
    //getter et setter
    
    public int getQuantiteFrais() {
        return quantiteFrais;
    }

    public final void setQuantiteFrais(int quantiteFrais) {
        this.quantiteFrais = quantiteFrais;
    }

    public float getMontantFrais() {
        return montantFrais;
    }

    public final void setMontantFrais(float montantFras) {
        this.montantFrais = montantFras;
    }

      public CTypeFrais gettypeFrais() {
        return typeFrais;
    }

    public final void settypeFrais(CTypeFrais typeFrais) {
        this.typeFrais = typeFrais;
    }
  
    
    //constructeur
    public CFrais(int qte, float montant, CTypeFrais type){
        setQuantiteFrais(qte);
        setMontantFrais(montant);
        settypeFrais(type);
        
    } 
}
