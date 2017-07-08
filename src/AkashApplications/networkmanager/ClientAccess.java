/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.networkmanager;

import AkashApplications.JsonParser.SearchResultParser;
import AkashApplications.src.ClientModel;
import AkashApplications.src.ProductModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author akash
 */
public class ClientAccess {
    HashMap<String, String> map;
    String url;

    public ClientAccess(HashMap<String, String> map, String url) {
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
    
    public ArrayList<ClientModel> allResult()
    {
        String serverReply = serverResponse();
        return new SearchResultParser().parseClientSeachList(serverReply);
    }
}
