/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.networkmanager;

import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author akash
 */
public class SendBulkRequest {
    HashMap<String, String> map;
    String url;

    public SendBulkRequest(HashMap<String, String> map, String url) {
        this.map = map;
        this.url = url;
    }
    
    public String serverResponse()
    {
        String result = "";
        try {
            HttpUtility.sendPostRequest(url, map);
            String[] response = HttpUtility.readMultipleLinesRespone();
            for (String line : response) {
                result += line;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        HttpUtility.disconnect();
        return result;
    }
}
