/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.JsonParser;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author akash
 */
public class CustomJsonParser {
    String reason, serverReply;
    boolean status;

    public CustomJsonParser(String serverReply) {
        this.serverReply = serverReply;
        startParsing();
    }

    private void startParsing() {
        try {
            JSONArray array = new JSONArray(serverReply);
            JSONObject object = new JSONObject(array.get(0).toString());
            status = new Boolean(object.getString("status"));
            reason = object.getString("reason");
        } catch (JSONException ex) {
            JOptionPane.showMessageDialog(null,"Failed to parse server response\n\nError : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getReason() {
        return reason;
    }

    public boolean getStatus() {
        return status;
    }
    
    
}
