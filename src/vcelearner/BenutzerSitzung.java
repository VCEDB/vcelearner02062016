package vcelearner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author J.Bleich
 */
public class BenutzerSitzung {

    // Verbindungsvariablen
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;

    private int zeitVorgabe;
    private Benutzer benutzer;
    private ArrayList<SitzungsLernKarte> sLKs;
    private int aktuellerSLKindex;
    private LernSitzung lernsitzung;

    public BenutzerSitzung(int zeitVorgabe, Benutzer benutzer,
            ArrayList<LernKarte> lKs) {
        this.zeitVorgabe = zeitVorgabe;
        this.benutzer = benutzer;
        sLKs = new ArrayList<>();
        for (LernKarte lK : lKs) {
            this.sLKs.add(new SitzungsLernKarte(lK));
        }
    }
    // KONSTRUKTOR NUR ZUM TESTEN !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
    public BenutzerSitzung(Benutzer benutzer, ArrayList<LernKarte> lKs, LernSitzung lernsitzung) {
        this.benutzer = benutzer;
        sLKs = new ArrayList<>();
        for (LernKarte lK : lKs) {
            this.sLKs.add(new SitzungsLernKarte(lK));
        }
        this.lernsitzung = lernsitzung;
    }
    
    

    public static void insert(BenutzerSitzung benutzerSitzung) {

        for (int i = 0; i < benutzerSitzung.getsLKs().size(); i++) {

            // Wiedervorlage in Benutzer2Lernkarte speichern, nur wenn
            // Wiedervorlage = true und kein diesbezüglicher Eintrag in der DB
            // vorhaneden ist
            Benutzer2LernKarte b2lk = 
                        new Benutzer2LernKarte(benutzerSitzung.getBenutzer().getId(),
                        benutzerSitzung.getsLKs().get(i).getlK().getId(),
                        benutzerSitzung.getsLKs().get(i).isWiederVorlage());
            
            if (benutzerSitzung.getsLKs().get(i).isWiederVorlage() == true && 
                Benutzer2LernKarte.checkWiedervorlage(b2lk)== false) {
                
                
                Benutzer2LernKarte.insert(b2lk);
            }

            // ArrayList Gegebene Antworten (als PotentielleAntworten) in 
            // LernSitzung2PotentielleAntwort speichern
            for (int j = 0; j < benutzerSitzung.getsLKs().get(i).getGegebeneAntworten().size(); j++) {

                LernSitzung2PotentielleAntwort ls2pa
                        = new LernSitzung2PotentielleAntwort(benutzerSitzung.getLernsitzung().getId(),
                                benutzerSitzung.getsLKs().get(i).getGegebeneAntworten().get(j).getId());
                LernSitzung2PotentielleAntwort.insert(ls2pa);

            }

            // Gemogelt in LernSitzung2LernKarte speichern
            LernSitzung2LernKarte ls2lk
                    = new LernSitzung2LernKarte(benutzerSitzung.getLernsitzung().getId(),
                            benutzerSitzung.getsLKs().get(i).getlK().getId(),
                            benutzerSitzung.getsLKs().get(i).isGemogelt());
            LernSitzung2LernKarte.insert(ls2lk);

        }

    }

    public int getAktuellerSLKindex() {
        return aktuellerSLKindex;
    }

    public void setAktuellerSLKindex(int aktuellerSLKindex) {
        this.aktuellerSLKindex = aktuellerSLKindex;
    }

    public LernSitzung getLernsitzung() {
        return lernsitzung;
    }

    public void setLernsitzung(LernSitzung lernsitzung) {
        this.lernsitzung = lernsitzung;
    }

    public int getZeitVorgabe() {
        return zeitVorgabe;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public ArrayList<SitzungsLernKarte> getsLKs() {
        return sLKs;
    }

    public SitzungsLernKarte getAktuelleSitzungsLernKarte() {
        return sLKs.get(aktuellerSLKindex);
    }

    public SitzungsLernKarte geheZu(int nummer) {
        aktuellerSLKindex = nummer - 1;
        return getAktuelleSitzungsLernKarte();
    }

    public String getTitelString(int modus) {
        // modus 0 : Frage x / y (ID xxx) Schwierigkeit xxx
        // modus 1 : Themengebiete
        String rueckgabe = "";
        if (modus == 1) {
            rueckgabe += "noch nicht drin";
        } else {
            rueckgabe += "Frage " + (aktuellerSLKindex + 1) + " / " + sLKs.size();
            rueckgabe += " (ID : " + getAktuelleSitzungsLernKarte().getlK().getId() + ")";
            rueckgabe += "Schwierigkeit " + sLKs.get(aktuellerSLKindex).getlK().getSchwierigkeitsGrad();
        }
        return rueckgabe;
    }

    @Override
    public String toString() {
        return "BenutzerSitzung{" + "zeitVorgabe=" + zeitVorgabe + ", benutzer=" + benutzer + ", sLKs=" + sLKs + ", aktuellerSLKindex=" + aktuellerSLKindex + ", lernsitzung=" + lernsitzung + '}';
    }
    
    
    

}