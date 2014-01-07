/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Monika
 */
public class TTTWebeservicePublish {

    public static void main(String[] args) {
        Endpoint endpoint = Endpoint.publish("http://localhost/TTTwebserviceService/",
                new WebserviceTTT());
        JOptionPane.showMessageDialog(null, "Server beenden");
        endpoint.stop();
    }
}
