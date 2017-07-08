/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.networkmanager;

import AkashApplications.src.MainUI;

/**
 *
 * @author akash
 */
public class ServerConstants {
    //public static final String BASE_URL = "http://plywoodmanor.rf.gd/";
    
    public String BASE_URL ,ADD_PRODUCT, UPDATE_PRODUCT, SEARCH_PRODUCT, GET_PRODUCT ,
            SAVE_CLIENT, DELETE_CLIENT,UPDATE_CLIENT,FETCH_CLIENT, DOWNLOAD_BARCODE, DELETE_STOCK, DECREMENT_STOCK;

    public ServerConstants(boolean ruby) {
        
        if(ruby)
            BASE_URL = "http://plywoodmanor.16mb.com/";
        else
            BASE_URL = "http://plywoodmanor.16mb.com/";
        
        ADD_PRODUCT = BASE_URL + "AddStock.php";
        UPDATE_PRODUCT = BASE_URL + "UpdateStock.php";
        SEARCH_PRODUCT = BASE_URL + "SearchStock.php";
        GET_PRODUCT = BASE_URL + "GetProduct.php";

        SAVE_CLIENT = BASE_URL + "AddClient.php";
        DELETE_CLIENT = BASE_URL + "DeleteClient.php";
        UPDATE_CLIENT = BASE_URL + "UpdateClient.php";
        FETCH_CLIENT = BASE_URL + "FetchClient.php";

        DOWNLOAD_BARCODE = BASE_URL + "Compress.php";
        DELETE_STOCK = BASE_URL + "DeleteStock.php";
        DECREMENT_STOCK = BASE_URL + "DecrementStock.php";
    }
    
    
}
