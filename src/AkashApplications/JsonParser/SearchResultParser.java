/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.JsonParser;

import AkashApplications.src.ClientModel;
import AkashApplications.src.ProductModel;
import java.util.ArrayList;
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
public class SearchResultParser {
    
    public ArrayList<ProductModel> parseProductSeachList(String serverReply)
    {
        ArrayList<ProductModel> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(serverReply);
            JSONArray array = object.getJSONArray("product");
            for(int i = 0; i< array.length(); i++)
            {
                JSONObject item = array.getJSONObject(i);
                ProductModel pm = new ProductModel(String.valueOf(item.getString("pid")),
                                                    String.valueOf(item.getString("design")),
                                                    String.valueOf(item.getString("rack")),
                                                    String.valueOf(item.getString("subrack")),
                                                    String.valueOf(item.getString("texture")),
                                                    String.valueOf(item.getString("qty")),
                                                    String.valueOf(item.getString("barcode")));
                list.add(pm);
            }
        } catch (JSONException ex) {
            JOptionPane.showMessageDialog(null,"Failed to parse server response\n\nError : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
    
    public ArrayList<ClientModel> parseClientSeachList(String serverReply)
    {
        ArrayList<ClientModel> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(serverReply);
            JSONArray array = object.getJSONArray("client");
            for(int i = 0; i< array.length(); i++)
            {
                JSONObject item = array.getJSONObject(i);
                ClientModel pm = new ClientModel(String.valueOf(item.getString("id")),
                                                    String.valueOf(item.getString("name")),
                                                    String.valueOf(item.getString("company")),
                                                    String.valueOf(item.getString("phone")),
                                                    String.valueOf(item.getString("address")),
                                                    String.valueOf(item.getString("favourite")));
                list.add(pm);
            }
        } catch (JSONException ex) {
            JOptionPane.showMessageDialog(null,"Failed to parse server response\n\nError : "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
}
