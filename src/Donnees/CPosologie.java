/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Donnees;

import java.util.List;

/**
 *
 * @author camilletilmont
 */
public class CPosologie {
    
    //attributs Posologie
    
    protected CTypeIndividu typeIndividu;
    protected CDosagePeriodique dosagePeriodique;
    protected int nbrJourPosologie;
    
    
    // Getters et Setters

    
    public CTypeIndividu getTypeIndividu() {
        return typeIndividu;
    }

    public final void setTypeIndividu(CTypeIndividu typeIndividu) {
        this.typeIndividu = typeIndividu;
    }

    public CDosagePeriodique getDosagePeriodique() {
        return dosagePeriodique;
    }

    public final void setDosagePeriodique(CDosagePeriodique dosagePeriodique) {
        this.dosagePeriodique = dosagePeriodique;
    }

    public int getNbrJourPosologie() {
        return nbrJourPosologie;
    }

    public final void setNbrJourPosologie(int nbrJour) {
        this.nbrJourPosologie = nbrJour;
    }
    
    
    //Constucteur Custom
    
    public CPosologie(CTypeIndividu typeInd, CDosagePeriodique dosPer, int nbrJourPoso){
        setTypeIndividu(typeInd);
        setDosagePeriodique(dosPer);
        setNbrJourPosologie(nbrJourPoso);
    
    }
    
    
}
