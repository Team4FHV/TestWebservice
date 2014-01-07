/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import controller.CorbaController;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import webserviceDTO.*;
import corba.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monika
 */
@WebService(name = "TTTwebservice")
@Stateless
public class WebserviceTTT {

    private CorbaController _corbaCtrl;

    public WebserviceTTT() {
        _corbaCtrl = new CorbaController();
    }

    /**
     * Web service operation
     *
     * @param datum
     * @param ort
     * @param kuenstler
     * @return
     */
    @WebMethod(operationName = "sucheVeranstaltungNachKriterien")
    public ArrayList<WebVeranstaltung> sucheVeranstaltungNachKriterien(@WebParam(name = "datum") String datum, @WebParam(name = "ort") String ort, @WebParam(name = "kuenstler") String kuenstler) {
        ArrayList<WebVeranstaltung> rv = new ArrayList<WebVeranstaltung>();
        StructVeranstaltung[] temp = _corbaCtrl.sucheVeranstaltungNachKriterien(datum, ort, kuenstler);
        for (int i = 0; i < temp.length; i++) {
            rv.add(mapStructVeranstaltungToDTO(temp[i]));
        }
        return rv;
    }

    /**
     * Web service operation
     *
     * @param veranstaltung
     * @return
     */
    @WebMethod(operationName = "getKategorieInfoVonVeranstaltung")
    public ArrayList<WebKategorieInformation> getKategorieInfoVonVeranstaltung(@WebParam(name = "veranstaltung") WebVeranstaltungAnzeigen veranstaltung) {
        ArrayList<WebKategorieInformation> rv = new ArrayList<WebKategorieInformation>();
        StructKategorieInformation[] temp = _corbaCtrl.getKategorieInfoVonVeranstaltung(mappWebKategorieInformationToStruct(veranstaltung));
        for (int i = 0; i < temp.length; i++) {
            rv.add(mapStructKategorieInformationToDTO(temp[i]));
        }
        return rv;
    }

    /**
     * Web service operation
     *
     * @param kategorieID
     * @return
     */
    @WebMethod(operationName = "getAlleFreieKartenNachKategorie")
    public WebKategorieKarte getAlleFreieKartenNachKategorie(@WebParam(name = "kategorieID") int kategorieID) {
        return mapKategorieKarteToDTO(_corbaCtrl.getAlleFreieKartenNachKategorie(mapIntegerToKategorieAuswaehlen(kategorieID)));
    }

    /**
     * Web service operation
     *
     * @param list
     */
    @WebMethod(operationName = "verkaufSpeichern")
    public void verkaufSpeichern(@WebParam(name = "list") List<WebKarteBestellen> list) {
        try {
            _corbaCtrl.verkaufSpeichern(mapWebKarteBestellenToStructArray(list));
        } catch (CobraException ex) {
            Logger.getLogger(WebserviceTTT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Web service operation
     *
     * @param kategorieID
     * @return
     */
    @WebMethod(operationName = "getKategorieInfo")
    public WebKategorieInformation getKategorieInfo(@WebParam(name = "kategorieID") int kategorieID) {
        return mapStructKategorieInformationToDTO(_corbaCtrl.getKategorieInfo(kategorieID));
    }

    /**
     * Web service operation
     *
     * @param veranstaltungID
     * @return
     */
    @WebMethod(operationName = "getVeranstaltungById")
    public WebVeranstaltung getVeranstaltungById(@WebParam(name = "veranstaltungID") int veranstaltungID) {
        return mapStructVeranstaltungToDTO(_corbaCtrl.getVeranstaltungById(veranstaltungID));
    }

    //Mapping Methoden
    /**
     *
     * @param temp
     * @return
     */
    private WebVeranstaltung mapStructVeranstaltungToDTO(StructVeranstaltung temp) {
        WebVeranstaltung rv = new WebVeranstaltung(temp.vid, temp.vDatum, temp.vName, temp.vOrt, temp.kuenstler, temp.ermaessigt);
        return rv;
    }

    /**
     *
     * @param veranstaltung
     * @return
     */
    private StructVeranstaltungAnzeigen mappWebKategorieInformationToStruct(WebVeranstaltungAnzeigen veranstaltung) {
        return new StructVeranstaltungAnzeigen(veranstaltung.getVanzId());
    }

    /**
     *
     * @param temp
     * @return
     */
    private WebKategorieInformation mapStructKategorieInformationToDTO(StructKategorieInformation temp) {
        return new WebKategorieInformation(temp.kategId, temp.katName, temp.katPreis, temp.freiePlaetze, temp.ermaessigung);
    }

    /**
     *
     * @param list
     * @return
     */
    private StructKarteBestellen[] mapWebKarteBestellenToStructArray(List<WebKarteBestellen> list) {
        StructKarteBestellen[] rv = new StructKarteBestellen[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rv[i] = new StructKarteBestellen(list.get(i).getKbKartenId(), list.get(i).getKbKundenId(), list.get(i).isKbErmaessigt());
        }
        return rv;
    }

    /**
     *
     * @param kategorieID
     * @return
     */
    private StructKategorieAuswaehlen mapIntegerToKategorieAuswaehlen(int kategorieID) {
        return new StructKategorieAuswaehlen(kategorieID);
    }

    /**
     *
     * @param alleFreieKartenNachKategorie
     * @return
     */
    private WebKategorieKarte mapKategorieKarteToDTO(StructKategorieKarte alleFreieKartenNachKategorie) {
        return new WebKategorieKarte(mapStructKarteToDTOArray(alleFreieKartenNachKategorie.kartenList));
    }

    /**
     *
     * @param temp
     * @return
     */
    private WebKarte[] mapStructKarteToDTOArray(StructKarte[] temp) {
        WebKarte[] rv = new WebKarte[temp.length];
        for (int i = 0; i < temp.length; i++) {
            rv[i] = new WebKarte(temp[i].kartenId, temp[i].reihe, temp[i].platz);
        }
        return rv;
    }

}
