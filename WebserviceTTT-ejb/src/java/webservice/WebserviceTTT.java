/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import webserviceDTO.*;
/**
 *
 * @author Monika
 */
@WebService(name = "TTTwebservice")
@Stateless
public class WebserviceTTT {

    /**
     * Web service operation
     * @param datum
     * @param ort
     * @param kuenstler
     * @return 
     */
    @WebMethod(operationName = "sucheVeranstaltungNachKriterien")
    public ArrayList<WebVeranstaltung> sucheVeranstaltungNachKriterien(@WebParam(name = "datum") String datum, @WebParam(name = "ort") String ort, @WebParam(name = "kuenstler") String kuenstler) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param veranstaltung
     * @return 
     */
    @WebMethod(operationName = "getKategorieInfoVonVeranstaltung")
    public ArrayList<WebKategorieInformation> getKategorieInfoVonVeranstaltung(@WebParam(name = "veranstaltung") WebVeranstaltungAnzeigen veranstaltung) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param kategorieID
     * @return 
     */
    @WebMethod(operationName = "getAlleFreieKartenNachKategorie")
    public WebKategorieKarte getAlleFreieKartenNachKategorie(@WebParam(name = "kategorieID") int kategorieID) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param list
     */
    @WebMethod(operationName = "verkaufSpeichern")
    public void verkaufSpeichern(@WebParam(name = "list") List<WebKarteBestellen> list) {
        //TODO write your implementation code here:
    }

    /**
     * Web service operation
     * @param kategorieID
     * @return 
     */
    @WebMethod(operationName = "getKategorieInfo")
    public WebKategorieKarte getKategorieInfo(@WebParam(name = "kategorieID") int kategorieID) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param veranstaltungID
     * @return 
     */
    @WebMethod(operationName = "getVeranstaltungById")
    public WebVeranstaltung getVeranstaltungById(@WebParam(name = "veranstaltungID") int veranstaltungID) {
        //TODO write your implementation code here:
        return null;
    }

}
