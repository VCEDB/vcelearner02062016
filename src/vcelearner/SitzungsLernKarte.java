/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcelearner;

import java.util.ArrayList;

/**
 *
 * @author J.Bleich
 */
public class SitzungsLernKarte {
    private LernKarte lK;
    private ArrayList<PotentielleAntwort> gegebeneAntworten;
    private boolean wiederVorlage;
    private boolean gemogelt;
    
    public SitzungsLernKarte (LernKarte lK) {
        this.lK=lK;
    }

    public SitzungsLernKarte(LernKarte lK, ArrayList<PotentielleAntwort> gegebeneAntworten, boolean wiederVorlage, boolean gemogelt) {
        this.lK = lK;
        this.gegebeneAntworten = gegebeneAntworten;
        this.wiederVorlage = wiederVorlage;
        this.gemogelt = gemogelt;
    }
    
    

    public ArrayList<PotentielleAntwort> getGegebeneAntworten() {
        return gegebeneAntworten;
    }

    public void setGegebeneAntworten(ArrayList<PotentielleAntwort> gegebeneAntworten) {
        this.gegebeneAntworten = gegebeneAntworten;
    }

    public boolean isWiederVorlage() {
        return wiederVorlage;
    }

    public void setWiederVorlage(boolean wiederVorlage) {
        this.wiederVorlage = wiederVorlage;
    }

    public boolean isGemogelt() {
        return gemogelt;
    }

    public void setGemogeltTrue() {
        this.gemogelt = true;
    }

    public LernKarte getlK() {
        return lK;
    }

    @Override
    public String toString() {
        return "\n\nSitzungsLernKarte{" + "lK=" + lK + ", gegebeneAntworten=" + gegebeneAntworten + ", wiederVorlage=" + wiederVorlage + ", gemogelt=" + gemogelt + '}';
    }
    
    
}