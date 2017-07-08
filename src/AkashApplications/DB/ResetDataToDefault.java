/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.DB;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author akash
 */
public class ResetDataToDefault {
    
        //String url = "jdbc:derby://localhost:1527/IMS;create=true";
        String url = "jdbc:derby:IMS;create=true";
        Connection con = null;
        Statement stmt = null;
        NetworkServerControl server = null;
        
    public void execute()
    {
       
        try{
            //server = new NetworkServerControl(InetAddress.getByName("localhost"), 1527);
            //server.start(null);
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            
            try{
                System.out.println("create client");
                stmt.execute("CREATE TABLE CLIENT (ID int not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME varchar(500) NOT NULL,COMPANY varchar(500) NOT NULL,PHONE varchar(15) NOT NULL,ADDRESS varchar(800) NOT NULL,FAVOURITE varchar(10) NOT NULL)");
            }
            catch(Exception e)
            {
                System.err.println(e);
            }
            finally
            {
                System.err.println("Delete stock : "+stmt.executeUpdate("DELETE FROM CLIENT"));
                String query = String.format("INSERT INTO CLIENT (NAME, COMPANY, PHONE, ADDRESS, FAVOURITE) VALUES ('Akash Giri', 'Akash Application Pvt. Ltd.', '9804945122', '123, Sillicon Valley,California, CA 10523.', 'Yes')");
                stmt.executeUpdate(query);
            }
            
            
            try{
                System.out.println("create stock");
                stmt.execute("CREATE TABLE STOCK (ID int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),PID varchar(300) NOT NULL primary key,DESIGN varchar(30000) NOT NULL,RACK varchar(500) NOT NULL,SUBRACK varchar(500) NOT NULL,TEXTURE varchar(30000) NOT NULL,QUANTITY int NOT NULL,BARCODE varchar(30000) NOT NULL)");
            }
            catch(Exception e)
            {
                System.err.println(e);
            }
            finally
            {
                System.err.println("Delete stock : "+stmt.executeUpdate("DELETE FROM STOCK"));
                String query = "INSERT INTO STOCK (PID, DESIGN, RACK, SUBRACK, TEXTURE, QUANTITY, BARCODE) VALUES" +
                "('550 RDF', 'Ruffle Doorskin 550 RDF', '0', '0', 'RDF', 0, 'Barcodes/550 RDF.jpg')," +
                "('546 RDF', 'Ruffle Doorskin 546 RDF', '0', '0', 'RDF', 0, 'Barcodes/546 RDF.jpg')," +
                "('545 RDF', 'Ruffle Doorskin 545 RDF', '0', '0', 'RDF', 0, 'Barcodes/545 RDF.jpg')," +
                "('539 RDF', 'Ruffle Doorskin 539 RDF', '0', '0', 'RDF', 0, 'Barcodes/539 RDF.jpg')," +
                "('536 RDF', 'Ruffle Doorskin 536 RDF', '0', '0', 'RDF', 0, 'Barcodes/536 RDF.jpg')," +
                "('533 RDF', 'Ruffle Doorskin 533 RDF', '0', '0', 'RDF', 0, 'Barcodes/533 RDF.jpg')," +
                "('529 RDF', 'Ruffle Doorskin 529 RDF', '0', '0', 'RDF', 0, 'Barcodes/529 RDF.jpg')," +
                "('538 RDF', 'Ruffle Doorskin 538 RDF', '0', '0', 'RDF', 0, 'Barcodes/538 RDF.jpg')," +
                "('535 RDF', 'Ruffle Doorskin 535 RDF', '0', '0', 'RDF', 0, 'Barcodes/535 RDF.jpg')," +
                "('526 RDF', 'Ruffle Doorskin 526 RDF', '0', '0', 'RDF', 0, 'Barcodes/526 RDF.jpg')," +
                "('628 RDF', 'Ruffle Doorskin 628 RDF', '0', '0', 'RDF', 0, 'Barcodes/628 RDF.jpg')," +
                "('684 RDF', 'Ruffle Doorskin 684 RDF', '0', '0', 'RDF', 0, 'Barcodes/684 RDF.jpg')," +
                "('697 RDT 6', 'Ruffle Doorskin 697 RDT 6', '0', '0', 'RDT', 0, 'Barcodes/697 RDT 6.jpg')," +
                "('628 RDT', 'Ruffle Doorskin 628 RDT 8', '101', '1', 'RDT', 4, 'Barcodes/628 RDT.jpg')," +
                "('712 RDT', 'Ruffle Doorskin 712 RDT 5', '0', '0', 'RDT', 0, 'Barcodes/712 RDT.jpg')," +
                "('742 RDT', 'Ruffle Doorskin 742 RDT 4', '101', '1', 'RDT', 10, 'Barcodes/742 RDT.jpg')," +
                "('742 VL RDT', 'Ruffle Doorskin 742 RDT 1', '101', '1', 'RDT', 6, 'Barcodes/742 VL RDT.jpg')," +
                "('745 RDF', 'Ruffle Doorskin 745 RDF', '0', '0', 'RDF', 0, 'Barcodes/745 RDF.jpg')," +
                "('746 RDF', 'Ruffle Doorskin 746 RDF', '0', '0', 'RDF', 0, 'Barcodes/746 RDF.jpg')," +
                "('6011 RDF', 'Ruffle Doorskin 6011 RDF', '0', '0', 'RDF', 0, 'Barcodes/6011 RDF.jpg')," +
                "('712 HZ RDT', 'Ruffle Doorskin 712 RDT 2', '101', '1', 'RDT', 3, 'Barcodes/712 HZ RDT.jpg')," +
                "('697 RDT', 'Ruffle Doorskin 697 RDT 1', '0', '0', 'RDT', 0, 'Barcodes/697 RDT.jpg')," +
                "('684 RDT', 'Ruffle Doorskin 684 RDT 2', '0', '0', 'RDT', 0, 'Barcodes/684 RDT.jpg')," +
                "('708 RDT', 'Ruffle Doorskin 708 RDT 8', '0', '0', 'RDT', 0, 'Barcodes/708 RDT.jpg')," +
                "('720 RDT', 'Ruffle Doorskin 720 RDT 3', '101', '4', 'RDT', 7, 'Barcodes/720 RDT.jpg')," +
                "('745 RDT', 'Ruffle Doorskin 745 RDT 3', '101', '1', 'RDT', 5, 'Barcodes/745 RDT.jpg')," +
                "('732 RDT', 'Ruffle Doorskin 732 RDT 4', '0', '0', 'RDT', 0, 'Barcodes/732 RDT.jpg')," +
                "('746 RDT', 'Ruffle Doorskin 746 RDT 5', '101', '1', 'RDT', 6, 'Barcodes/746 RDT.jpg')," +
                "('606 RDT', 'Ruffle Doorskin 606 RDT 5', '0', '0', 'RDT', 0, 'Barcodes/606 RDT.jpg')," +
                "('697 RDF', 'Ruffle Doorskin 697 RDF', '0', '0', 'RDF', 0, 'Barcodes/697 RDF.jpg')," +
                "('708 RDF', 'Ruffle Doorskin 708 RDF', '0', '0', 'RDF', 0, 'Barcodes/708 RDF.jpg')," +
                "('712 RDF', 'Ruffle Doorskin 712 RDF', '0', '0', 'RDF', 0, 'Barcodes/712 RDF.jpg')," +
                "('720 RDF', 'Ruffle Doorskin 720 RDF', '0', '0', 'RDF', 0, 'Barcodes/720 RDF.jpg')," +
                "('732 RDF', 'Ruffle Doorskin 732 RDF', '0', '0', 'RDF', 0, 'Barcodes/732 RDF.jpg')," +
                "('742 RDF', 'Ruffle Doorskin 742 RDF', '0', '0', 'RDF', 0, 'Barcodes/742 RDF.jpg')," +
                "('6046 RDF', 'Ruffle Doorskin 6046 RDF', '0', '0', 'RDF', 0, 'Barcodes/6046 RDF.jpg')," +
                "('3057 RDML', 'Ruffle Doorskin 3057 RDML', '0', '0', 'RDML', 0, 'Barcodes/3057 RDML.jpg')," +
                "('3086 RDML', 'Ruffle Doorskin 3086 RDML', '0', '0', 'RDML', 0, 'Barcodes/3086 RDML.jpg')," +
                "('3087 RDML', 'Ruffle Doorskin 3087 RDML', '0', '0', 'RDML', 0, 'Barcodes/3087 RDML.jpg')," +
                "('3088 RDML', 'Ruffle Doorskin 3088 RDML', '0', '0', 'RDML', 0, 'Barcodes/3088 RDML.jpg')," +
                "('3089 RDML', 'Ruffle Doorskin 3089 RDML', '0', '0', 'RDML', 0, 'Barcodes/3089 RDML.jpg')," +
                "('3090 RDMD', 'Ruffle Doorskin 3090 RDMD', '101', '2', 'RDMD', 6, 'Barcodes/3090 RDMD.jpg')," +
                "('3091 RDMD', 'Ruffle Doorskin 3091 RDMD', '101', '6', 'RDMD', 2, 'Barcodes/3091 RDMD.jpg')," +
                "('3093 RDMD', 'Ruffle Doorskin 3093 RDMD', '0', '0', 'RDMD', 0, 'Barcodes/3093 RDMD.jpg')," +
                "('3094 RDMD', 'Ruffle Doorskin 3094 RDMD', '0', '0', 'RDMD', 0, 'Barcodes/3094 RDMD.jpg')," +
                "('3095 RDMD', 'Ruffle Doorskin 3095 RDMD', '102', '2', 'RDMD', 7, 'Barcodes/3095 RDMD.jpg')," +
                "('3097 RDMD', 'Ruffle Doorskin 3097 RDMD', '102', '1', 'RDMD', 3, 'Barcodes/3097 RDMD.jpg')," +
                "('5098 RDMD', 'Ruffle Doorskin 5098 RDMD', '101', '4', 'RDMD', 2, 'Barcodes/5098 RDMD.jpg')," +
                "('3060 RDM', 'Ruffle Doorskin 3060 RDM', '101', '2', 'RDM', 6, 'Barcodes/3060 RDM.jpg')," +
                "('3061 RDM', 'Ruffle Doorskin 3061 RDM', '101', '2', 'RDM', 6, 'Barcodes/3061 RDM.jpg')," +
                "('3063 RDM', 'Ruffle Doorskin 3063 RDM', '102', '4', 'RDM', 15, 'Barcodes/3063 RDM.jpg')," +
                "('3064 RDM', 'Ruffle Doorskin 3064 RDM', '0', '0', 'RDM', 0, 'Barcodes/3064 RDM.jpg')," +
                "('3065 RDM', 'Ruffle Doorskin 3065 RDM', '0', '0', 'RDM', 0, 'Barcodes/3065 RDM.jpg')," +
                "('3066 RDM', 'Ruffle Doorskin 3066 RDM', '102', '5', 'RDM', 4, 'Barcodes/3066 RDM.jpg')," +
                "('3068 RDM', 'Ruffle Doorskin 3068 RDM', '101', '2', 'RDM', 16, 'Barcodes/3068 RDM.jpg')," +
                "('3069 RDM', 'Ruffle Doorskin 3069 RDM', '101', '2', 'RDM', 7, 'Barcodes/3069 RDM.jpg')," +
                "('3070 RDM', 'Ruffle Doorskin 3070 RDM', '0', '0', 'RDM', 0, 'Barcodes/3070 RDM.jpg')," +
                "('3071 RDM', 'Ruffle Doorskin 3071 RDM', '101', '2', 'RDM', 5, 'Barcodes/3071 RDM.jpg')," +
                "('3092 RDM', 'Ruffle Doorskin 3092 RDM', '101', '2', 'RDM', 1, 'Barcodes/3092 RDM.jpg')," +
                "('3100 RDM', 'Ruffle Doorskin 3100 RDM', '101', '1', 'RDM', 6, 'Barcodes/3100 RDM.jpg')," +
                "('3101 RDM', 'Ruffle Doorskin 3101 RDM', '101', '1', 'RDM', 6, 'Barcodes/3101 RDM.jpg')," +
                "('3102 RDM', 'Ruffle Doorskin 3102 RDM', '101', '1', 'RDM', 6, 'Barcodes/3102 RDM.jpg')," +
                "('5101 RDM', 'Ruffle Doorskin 5101 RDM', '0', '0', 'RDM', 0, 'Barcodes/5101 RDM.jpg')," +
                "('5102 RDM', 'Ruffle Doorskin 5102 RDM', '102', '5', 'RDM', 2, 'Barcodes/5102 RDM.jpg')," +
                "('5103 RDM', 'Ruffle Doorskin 5103 RDM', '0', '0', 'RDM', 0, 'Barcodes/5103 RDM.jpg')," +
                "('5106 RDM', 'Ruffle Doorskin 5106 RDM', '102', '5', 'RDM', 9, 'Barcodes/5106 RDM.jpg')," +
                "('3037 RDCP', 'Ruffle Doorskin 3037 RDCP', '101', '5', 'RDCP', 10, 'Barcodes/3037 RDCP.jpg')," +
                "('3038 RDCP', 'Ruffle Doorskin 3038 RDCP', '101', '6', 'RDCP', 10, 'Barcodes/3038 RDCP.jpg')," +
                "('3039 RDCP', 'Ruffle Doorskin 3039 RDCP', '0', '0', 'RDCP', 0, 'Barcodes/3039 RDCP.jpg')," +
                "('3045 RDCP', 'Ruffle Doorskin 3045 RDCP', '0', '0', 'RDCP', 0, 'Barcodes/3045 RDCP.jpg')," +
                "('3047 RDCP', 'Ruffle Doorskin 3047 RDCP', '102', '4', 'RDCP', 2, 'Barcodes/3047 RDCP.jpg')," +
                "('3048 RDCP', 'Ruffle Doorskin 3048 RDCP', '101', '5', 'RDCP', 5, 'Barcodes/3048 RDCP.jpg')," +
                "('3051 RDCP', 'Ruffle Doorskin 3051 RDCP', '101', '5', 'RDCP', 4, 'Barcodes/3051 RDCP.jpg')," +
                "('3054 RDCP', 'Ruffle Doorskin 3054 RDCP', '0', '0', 'RDCP', 0, 'Barcodes/3054 RDCP.jpg')," +
                "('3056 RDCP', 'Ruffle Doorskin 3056 RDCP', '0', '0', 'RDCP', 0, 'Barcodes/3056 RDCP.jpg')," +
                "('3103 RDCP', 'Ruffle Doorskin 3103 RDCP', '101', '5', 'RDCP', 8, 'Barcodes/3103 RDCP.jpg')," +
                "('3108 EDD', 'Ruffle Doorskin 3108 EDD', '0', '0', 'EDD', 0, 'Barcodes/3108 EDD.jpg')," +
                "('3109 EDD', 'Ruffle Doorskin 3109 EDD', '0', '0', 'EDD', 0, 'Barcodes/3109 EDD.jpg')," +
                "('3110 EDD', 'Ruffle Doorskin 3110 EDD', '0', '0', 'EDD', 0, 'Barcodes/3110 EDD.jpg')," +
                "('3111 EDD', 'Ruffle Doorskin 3111 EDD', '101', '1', 'EDD', 6, 'Barcodes/3111 EDD.jpg')," +
                "('3072 RDD', 'Ruffle Doorskin 3072 RDD', '101', '5', 'RDD', 5, 'Barcodes/3072 RDD.jpg')," +
                "('3073 RDD', 'Ruffle Doorskin 3073 RDD', '0', '0', 'RDD', 0, 'Barcodes/3073 RDD.jpg')," +
                "('3076 RDD', 'Ruffle Doorskin 3076 RDD', '101', '5', 'RDD', 5, 'Barcodes/3076 RDD.jpg')," +
                "('3077 RDD', 'Ruffle Doorskin 3077 RDD', '101', '5', 'RDD', 6, 'Barcodes/3077 RDD.jpg')," +
                "('3079 RDD', 'Ruffle Doorskin 3079 RDD', '101', '5', 'RDD', 4, 'Barcodes/3079 RDD.jpg')," +
                "('3083 RDD', 'Ruffle Doorskin 3083 RDD', '102', '1', 'RDD', 4, 'Barcodes/3083 RDD.jpg')," +
                "('3080 RDD', 'Ruffle Doorskin 3080 RDD', '101', '5', 'RDD', 5, 'Barcodes/3080 RDD.jpg')," +
                "('3081 RDD', 'Ruffle Doorskin 3081 RDD', '102', '2', 'RDD', 10, 'Barcodes/3081 RDD.jpg')," +
                "('3084 RDD', 'Ruffle Doorskin 3084 RDD', '0', '0', 'RDD', 0, 'Barcodes/3084 RDD.jpg')," +
                "('3085 RDD', 'Ruffle Doorskin 3085 RDD', '102', '1', 'RDD', 9, 'Barcodes/3085 RDD.jpg')," +
                "('3001 RDP', 'Ruffle Doorskin 3001 RDP', '102', '3', 'RDP', 17, 'Barcodes/3001 RDP.jpg')," +
                "('3004 RDP', 'Ruffle Doorskin 3004 RDP', '102', '3', 'RDP', 10, 'Barcodes/3004 RDP.jpg')," +
                "('3005 RDP', 'Ruffle Doorskin 3005 RDP', '102', '3', 'RDP', 3, 'Barcodes/3005 RDP.jpg')," +
                "('3006 RDP', 'Ruffle Doorskin 3006 RDP', '102', '3', 'RDP', 12, 'Barcodes/3006 RDP.jpg')," +
                "('3009 RDP', 'Ruffle Doorskin 3009 RDP', '102', '3', 'RDP', 6, 'Barcodes/3009 RDP.jpg')," +
                "('3010 RDP', 'Ruffle Doorskin 3010 RDP', '0', '0', 'RDP', 0, 'Barcodes/3010 RDP.jpg')," +
                "('3012 RDP', 'Ruffle Doorskin 3012 RDP', '0', '0', 'RDP', 0, 'Barcodes/3012 RDP.jpg')," +
                "('3015 RDP', 'Ruffle Doorskin 3015 RDP', '102', '5', 'RDP', 14, 'Barcodes/3015 RDP.jpg')," +
                "('3016 RDP', 'Ruffle Doorskin 3016 RDP', '102', '3', 'RDP', 7, 'Barcodes/3016 RDP.jpg')," +
                "('3018 RDP', 'Ruffle Doorskin 3018 RDP', '102', '2', 'RDP', 10, 'Barcodes/3018 RDP.jpg')," +
                "('3019 RDP', 'Ruffle Doorskin 3019 RDP', '102', '2', 'RDP', 8, 'Barcodes/3019 RDP.jpg')," +
                "('3020 RDP', 'Ruffle Doorskin 3020 RDP', '101', '5', 'RDP', 0, 'Barcodes/3020 RDP.jpg')," +
                "('3021 RDP', 'Ruffle Doorskin 3021 RDP', '102', '2', 'RDP', 13, 'Barcodes/3021 RDP.jpg')," +
                "('3022 RDP', 'Ruffle Doorskin 3022 RDP', '102', '2', 'RDP', 5, 'Barcodes/3022 RDP.jpg')," +
                "('3023 RDP', 'Ruffle Doorskin 3023 RDP', '102', '2', 'RDP', 10, 'Barcodes/3023 RDP.jpg')," +
                "('3024 RDP', 'Ruffle Doorskin 3024 RDP', '102', '4', 'RDP', 4, 'Barcodes/3024 RDP.jpg')," +
                "('3025 RDP', 'Ruffle Doorskin 3025 RDP', '102', '4', 'RDP', 6, 'Barcodes/3025 RDP.jpg')," +
                "('3026 RDP', 'Ruffle Doorskin 3026 RDP', '102', '5', 'RDP', 15, 'Barcodes/3026 RDP.jpg')," +
                "('3027 RDP', 'Ruffle Doorskin 3027 RDP', '102', '4', 'RDP', 6, 'Barcodes/3027 RDP.jpg')," +
                "('3028 RDP', 'Ruffle Doorskin 3028 RDP', '102', '4', 'RDP', 5, 'Barcodes/3028 RDP.jpg')," +
                "('3029 RDP', 'Ruffle Doorskin 3029 RDP', '101', '6', 'RDP', 11, 'Barcodes/3029 RDP.jpg')," +
                "('3030 RDP', 'Ruffle Doorskin 3030 RDP', '101', '6', 'RDP', 10, 'Barcodes/3030 RDP.jpg')," +
                "('3031 RDP', 'Ruffle Doorskin 3031 RDP', '102', '5', 'RDP', 6, 'Barcodes/3031 RDP.jpg')," +
                "('3032 RDP', 'Ruffle Doorskin 3032 RDP', '101', '3', 'RDP', 10, 'Barcodes/3032 RDP.jpg')," +
                "('3033 RDP', 'Ruffle Doorskin 3033 RDP', '102', '5', 'RDP', 4, 'Barcodes/3033 RDP.jpg')," +
                "('3112 RDP', 'Ruffle Doorskin 3112 RDP', '101', '3', 'RDP', 9, 'Barcodes/3112 RDP.jpg')," +
                "('3114 RDP', 'Ruffle Doorskin 3114 RDP', '101', '6', 'RDP', 0, 'Barcodes/3114 RDP.jpg')," +
                "('3115 RDP', 'Ruffle Doorskin 3115 RDP', '101', '3', 'RDP', 11, 'Barcodes/3115 RDP.jpg')," +
                "('5067 RDP', 'Ruffle Doorskin 5067 RDP', '101', '3', 'RDP', 8, 'Barcodes/5067 RDP.jpg')," +
                "('5068 RDP', 'Ruffle Doorskin 5068 RDP', '102', '1', 'RDP', 3, 'Barcodes/5068 RDP.jpg')," +
                "('5069 RDP', 'Ruffle Doorskin 5069 RDP', '102', '1', 'RDP', 5, 'Barcodes/5069 RDP.jpg')," +
                "('5070 RDP', 'Ruffle Doorskin 5070 RDP', '102', '5', 'RDP', 7, 'Barcodes/5070 RDP.jpg')," +
                "('5072 RDP', 'Ruffle Doorskin 5072 RDP', '101', '3', 'RDP', 6, 'Barcodes/5072 RDP.jpg')," +
                "('5074 RDP', 'Ruffle Doorskin 5074 RDP', '101', '3', 'RDP', 10, 'Barcodes/5074 RDP.jpg')," +
                "('5077 RDP', 'Ruffle Doorskin 5077 RDP', '102', '2', 'RDP', 8, 'Barcodes/5077 RDP.jpg')," +
                "('5078 RDP', 'Ruffle Doorskin 5078 RDP', '101', '3', 'RDP', 14, 'Barcodes/5078 RDP.jpg')," +
                "('5108 RDP', 'Ruffle Doorskin 5108 RDP', '101', '4', 'RDP', 1, 'Barcodes/5108 RDP.jpg')," +
                "('5109 RDP', 'Ruffle Doorskin 5109 RDP', '0', '0', 'RDP', 0, 'Barcodes/5109 RDP.jpg')," +
                "('5110 RDP', 'Ruffle Doorskin 5110 RDP', '101', '4', 'RDP', 6, 'Barcodes/5110 RDP.jpg')," +
                "('5111 RDP', 'Ruffle Doorskin 5111 RDP', '0', '0', 'RDP', 0, 'Barcodes/5111 RDP.jpg')," +
                "('5112 RDP', 'Ruffle Doorskin 5112 RDP', '101', '4', 'RDP', 10, 'Barcodes/5112 RDP.jpg')," +
                "('5114 RDP', 'Ruffle Doorskin 5112 RDP', '101', '4', 'RDP', 8, 'Barcodes/5114 RDP.jpg')," +
                "('5115 RDP', 'Ruffle Doorskin 5115 RDP', '0', '0', 'RDP', 0, 'Barcodes/5115 RDP.jpg')," +
                "('5116 RDP', 'Ruffle Doorskin 5116 RDP', '101', '4', 'RDP', 9, 'Barcodes/5116 RDP.jpg')," +
                "('5117 RDP', 'Ruffle Doorskin 5117 RDP', '101', '4', 'RDP', 7, 'Barcodes/5117 RDP.jpg')," +
                "('5118 RDP', 'Ruffle Doorskin 5118 RDP', '101', '4', 'RDP', 5, 'Barcodes/5118 RDP.jpg')," +
                "('5119 RDP', 'Ruffle Doorskin 5119 RDP', '101', '4', 'RDP', 6, 'Barcodes/5119 RDP.jpg')," +
                "('5120 RDP', 'Ruffle Doorskin 5120 RDP', '101', '3', 'RDP', 6, 'Barcodes/5120 RDP.jpg')," +
                "('R367 B-16', 'Ruffle Lam 0.8MM 367 (B-16)', '0', '0', 'B-16', 0, 'Barcodes/R367 B-16.jpg')," +
                "('R368 B-16', 'Ruffle Lam 0.8MM 368 (B-16)', '0', '0', 'B-16', 0, 'Barcodes/R368 B-16.jpg')," +
                "('R369 B-16', 'Ruffle Lam 0.8MM 369 (B-16)', '0', '0', 'B-16', 0, 'Barcodes/R369 B-16.jpg')," +
                "('R370 B-16', 'Ruffle Lam 0.8MM 370 (B-16)', '0', '0', 'B-16', 0, 'Barcodes/R370 B-16.jpg')," +
                "('R371 B-16', 'Ruffle Lam 0.8MM 371 (B-16)', '0', '0', 'B-16', 0, 'Barcodes/R371 B-16.jpg')," +
                "('R372 B-16', 'Ruffle Lam 0.8MM 372 (B-16)', '0', '0', 'B-16', 0, 'Barcodes/R372 B-16.jpg')," +
                "('R102 B-12', 'Ruffle Lam 0.8MM 102 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R102 B-12.jpg')," +
                "('R111 B-12', 'Ruffle Lam 0.8MM 111 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R111 B-12.jpg')," +
                "('R114 B-12', 'Ruffle Lam 0.8MM 114 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R114 B-12.jpg')," +
                "('R131 B-12', 'Ruffle Lam 0.8MM 131 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R131 B-12.jpg')," +
                "('R132 B-12', 'Ruffle Lam 0.8MM 132 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R132 B-12.jpg')," +
                "('R133 B-12', 'Ruffle Lam 0.8MM 133 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R133 B-12.jpg')," +
                "('R135 B-12', 'Ruffle Lam 0.8MM 135 (B-12)', '0', '0', 'B-12', 0, 'Barcodes/R135 B-12.jpg')," +
                "('R305 B-3', 'Ruffle Lam 0.8MM 305 (B-3)', '0', '0', 'B-3', 0, 'Barcodes/R305 B-3.jpg')," +
                "('R308 B-3', 'Ruffle Lam 0.8MM 308 (B-3)', '0', '0', 'B-3', 0, 'Barcodes/R308 B-3.jpg')," +
                "('R338 B-3', 'Ruffle Lam 0.8MM 338 (B-3)', '0', '0', 'B-3', 0, 'Barcodes/R338 B-3.jpg')," +
                "('R339 B-3', 'Ruffle Lam 0.8MM 339 (B-3)', '0', '0', 'B-3', 0, 'Barcodes/R339 B-3.jpg')," +
                "('R340 B-3', 'Ruffle Lam 0.8MM 340 (B-3)', '0', '0', 'B-3', 0, 'Barcodes/R340 B-3.jpg')," +
                "('R350 B-3', 'Ruffle Lam 0.8MM 350 (B-3)', '0', '0', 'B-3', 0, 'Barcodes/R350 B-3.jpg')," +
                "('R319 B-8', 'Ruffle Lam 0.8MM 319 (B-8)', '0', '0', 'B-8', 0, 'Barcodes/R319 B-8.jpg')," +
                "('R327 B-8', 'Ruffle Lam 0.8MM 327 (B-8)', '0', '0', 'B-8', 0, 'Barcodes/R327 B-8.jpg')," +
                "('R329 B-8', 'Ruffle Lam 0.8MM 329 (B-8)', '0', '0', 'B-8', 0, 'Barcodes/R329 B-8.jpg')," +
                "('R348 B-8', 'Ruffle Lam 0.8MM 348 (B-8)', '0', '0', 'B-8', 0, 'Barcodes/R348 B-8.jpg')," +
                "('R349 B-8', 'Ruffle Lam 0.8MM 349 (B-8)', '0', '0', 'B-8', 0, 'Barcodes/R349 B-8.jpg')," +
                "('R336 B-2', 'Ruffle Lam 0.8MM 336 (B-2)', '0', '0', 'B-2', 0, 'Barcodes/R336 B-2.jpg')," +
                "('R348 B-2', 'Ruffle Lam 0.8MM 348 (B-2)', '0', '0', 'B-2', 0, 'Barcodes/R348 B-2.jpg')," +
                "('R349 B-2', 'Ruffle Lam 0.8MM 349 (B-2)', '0', '0', 'B-2', 0, 'Barcodes/R349 B-2.jpg')," +
                "('R350 B-2', 'Ruffle Lam 0.8MM 350 (B-2)', '0', '0', 'B-2', 0, 'Barcodes/R350 B-2.jpg')," +
                "('R615 B-15', 'Ruffle Lam 0.8MM 615 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R615 B-15.jpg')," +
                "('R616 B-15', 'Ruffle Lam 0.8MM 616 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R616 B-15.jpg')," +
                "('R617 B-15', 'Ruffle Lam 0.8MM 617 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R617 B-15.jpg')," +
                "('R618 B-15', 'Ruffle Lam 0.8MM 618 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R618 B-15.jpg')," +
                "('R770 B-15', 'Ruffle Lam 0.8MM 770 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R770 B-15.jpg')," +
                "('R771 B-15', 'Ruffle Lam 0.8MM 771 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R771 B-15.jpg')," +
                "('R772 B-15', 'Ruffle Lam 0.8MM 772 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R772 B-15.jpg')," +
                "('R773 B-15', 'Ruffle Lam 0.8MM 773 (B-15)', '0', '0', 'B-15', 0, 'Barcodes/R773 B-15.jpg')," +
                "('R685 N-8', 'Ruffle Lam 0.8MM 685 (N-8)', '0', '0', 'N-8', 0, 'Barcodes/R685 N-8.jpg')," +
                "('R743 N-8', 'Ruffle Lam 0.8MM 743 (N-8)', '0', '0', 'N-8', 0, 'Barcodes/R743 N-8.jpg')," +
                "('R745 N-8', 'Ruffle Lam 0.8MM 745 (N-8)', '0', '0', 'N-8', 0, 'Barcodes/R745 N-8.jpg')," +
                "('R746 N-8', 'Ruffle Lam 0.8MM 746 (N-8)', '0', '0', 'N-8', 0, 'Barcodes/R746 N-8.jpg')," +
                "('R650 HD S-21', 'Ruffle Lam 0.8MM 650 HD (S-21 HD)', '0', '0', 'S-21 HD', 0, 'Barcodes/R650 HD S-21.jpg')," +
                "('R652 HD S-21', 'Ruffle Lam 0.8MM 652 HD (S-21 HD)', '0', '0', 'S-21 HD', 0, 'Barcodes/R652 HD S-21.jpg')," +
                "('R653 HD S-21', 'Ruffle Lam 0.8MM 653 HD (S-21 HD)', '0', '0', 'S-21 HD', 0, 'Barcodes/R653 HD S-21.jpg')," +
                "('R762 HD S-21', 'Ruffle Lam 0.8MM 762 HD (S-21 HD)', '0', '0', 'S-21 HD', 0, 'Barcodes/R762 HD S-21.jpg')," +
                "('R340 HD B-9', 'Ruffle Lam 0.8MM 340 HD (B-9)', '0', '0', 'B-9', 0, 'Barcodes/R340 HD B-9.jpg')," +
                "('R348 HD B-9', 'Ruffle Lam 0.8MM 348 HD (B-9)', '0', '0', 'B-9', 0, 'Barcodes/R348 HD B-9.jpg')," +
                "('R350 HD B-9', 'Ruffle Lam 0.8MM 350 HD (B-9)', '0', '0', 'B-9', 0, 'Barcodes/R350 HD B-9.jpg')," +
                "('R684 HD B-9', 'Ruffle Lam 0.8MM 684 HD (B-9)', '0', '0', 'B-9', 0, 'Barcodes/R684 HD B-9.jpg')," +
                "('R685 HD B-9', 'Ruffle Lam 0.8MM 685 HD (B-9)', '0', '0', 'B-9', 0, 'Barcodes/R685 HD B-9.jpg')," +
                "('R319 S-19', 'Ruffle Lam 0.8MM 319 (S-19)', '0', '0', 'S-19', 0, 'Barcodes/R319 S-19.jpg')," +
                "('R582 HD S-19', 'Ruffle Lam 0.8MM 582 HD (S-19)', '0', '0', 'S-19', 0, 'Barcodes/R582 HD S-19.jpg')," +
                "('R731 HD S-19', 'Ruffle Lam 0.8MM 731 HD (S-19)', '0', '0', 'S-19', 0, 'Barcodes/R731 HD S-19.jpg')," +
                "('R732 HD S-19', 'Ruffle Lam 0.8MM 732 HD (S-19)', '0', '0', 'S-19', 0, 'Barcodes/R732 HD S-19.jpg')," +
                "('R364 HD S-26', 'Ruffle Lam 0.8MM 364 HD (S-26 HD)', '0', '0', 'S-26 HD', 0, 'Barcodes/R364 HD S-26.jpg')," +
                "('R706 HD S-26', 'Ruffle Lam 0.8MM 706 HD (S-26 HD)', '0', '0', 'S-26 HD', 0, 'Barcodes/R706 HD S-26.jpg')," +
                "('R740 HD S-26', 'Ruffle Lam 0.8MM 740 HD (S-26 HD)', '0', '0', 'S-26', 0, 'Barcodes/R740 HD S-26.jpg')," +
                "('R742 HD S-26', 'Ruffle Lam 0.8MM 742 HD (S-26 HD)', '0', '0', 'S-26 HD', 0, 'Barcodes/R742 HD S-26.jpg')," +
                "('R305 S-15', 'Ruffle Lam 0.8MM 305 (S-15)', '0', '0', 'S-15', 0, 'Barcodes/R305 S-15.jpg')," +
                "('R307 S-15', 'Ruffle Lam 0.8MM 307 (S-15)', '0', '0', 'S-15', 0, 'Barcodes/R307 S-15.jpg')," +
                "('R311 S-15', 'Ruffle Lam 0.8MM 311 (S-15)', '0', '0', 'S-15', 0, 'Barcodes/R311 S-15.jpg')," +
                "('R327 S-15', 'Ruffle Lam 0.8MM 327 (S-15)', '0', '0', 'S-15', 0, 'Barcodes/R327 S-15.jpg')," +
                "('R345 S-15', 'Ruffle Lam 0.8MM 345 (S-15)', '0', '0', 'S-15', 0, 'Barcodes/R345 S-15.jpg')," +
                "('R347 S-15', 'Ruffle Lam 0.8MM 347 (S-15)', '0', '0', 'S-15', 0, 'Barcodes/R347 S-15.jpg')," +
                "('R341 S-22', 'Ruffle Lam 0.8MM 341 (S-22)', '0', '0', 'S-22', 0, 'Barcodes/R341 S-22.jpg')," +
                "('R342 S-22', 'Ruffle Lam 0.8MM 342 (S-22)', '0', '0', 'S-22', 0, 'Barcodes/R342 S-22.jpg')," +
                "('R343 S-22', 'Ruffle Lam 0.8MM 343 (S-22)', '0', '0', 'S-22', 0, 'Barcodes/R343 S-22.jpg')," +
                "('R344 S-22', 'Ruffle Lam 0.8MM 344 (S-22)', '0', '0', 'S-22', 0, 'Barcodes/R344 S-22.jpg')," +
                "('R110 HGL', 'Ruffle Lam 0.8MM 110 HGL', '0', '0', 'HGL', 0, 'Barcodes/R110 HGL.jpg')," +
                "('R115 HGL', 'Ruffle Lam 0.8MM 115 HGL', '0', '0', 'HGL', 0, 'Barcodes/R115 HGL.jpg')," +
                "('R132 HGL', 'Ruffle Lam 0.8MM 132 HGL', '0', '0', 'HGL', 0, 'Barcodes/R132 HGL.jpg')," +
                "('R139 HGL', 'Ruffle Lam 0.8MM 139 HGL', '0', '0', 'HGL', 0, 'Barcodes/R139 HGL.jpg')," +
                "('R301 HGL', 'Ruffle Lam 0.8MM 301 HGL', '0', '0', 'HGL', 0, 'Barcodes/R301 HGL.jpg')," +
                "('R302 HGL', 'Ruffle Lam 0.8MM 302 HGL', '0', '0', 'HGL', 0, 'Barcodes/R302 HGL.jpg')," +
                "('R303 HGL', 'Ruffle Lam 0.8MM 303 HGL', '0', '0', 'HGL', 0, 'Barcodes/R303 HGL.jpg')," +
                "('R304 HGL', 'Ruffle Lam 0.8MM 304 HGL', '0', '0', 'HGL', 0, 'Barcodes/R304 HGL.jpg')," +
                "('R305 HGL', 'Ruffle Lam 0.8MM 305 HGL', '0', '0', 'HGL', 0, 'Barcodes/R305 HGL.jpg')," +
                "('R306 HGL', 'Ruffle Lam 0.8MM 306 HGL', '0', '0', 'HGL', 0, 'Barcodes/R306 HGL.jpg')," +
                "('R307 HGL', 'Ruffle Lam 0.8MM 307 HGL', '0', '0', 'HGL', 0, 'Barcodes/R307 HGL.jpg')," +
                "('R333 HGL', 'Ruffle Lam 0.8MM 333 HGL', '0', '0', 'HGL', 0, 'Barcodes/R333 HGL.jpg')," +
                "('R334 HGL', 'Ruffle Lam 0.8MM 334 HGL', '0', '0', 'HGL', 0, 'Barcodes/R334 HGL.jpg')," +
                "('R615 HGL', 'Ruffle Lam 0.8MM 615 HGL', '0', '0', 'HGL', 0, 'Barcodes/R615 HGL.jpg')," +
                "('R616 HGL', 'Ruffle Lam 0.8MM 616 HGL', '0', '0', 'HGL', 0, 'Barcodes/R616 HGL.jpg')," +
                "('R618 HGL', 'Ruffle Lam 0.8MM 618 HGL', '0', '0', 'HGL', 0, 'Barcodes/R618 HGL.jpg')," +
                "('R750 HGL', 'Ruffle Lam 0.8MM 750 HGL', '0', '0', 'HGL', 0, 'Barcodes/R750 HGL.jpg')," +
                "('R751 HGL', 'Ruffle Lam 0.8MM 751 HGL', '0', '0', 'HGL', 0, 'Barcodes/R751 HGL.jpg')," +
                "('R752 HGL', 'Ruffle Lam 0.8MM 752 HGL', '0', '0', 'HGL', 0, 'Barcodes/R752 HGL.jpg')," +
                "('R770 HGL', 'Ruffle Lam 0.8MM 770 HGL', '0', '0', 'HGL', 0, 'Barcodes/R770 HGL.jpg')," +
                "('R771 HGL', 'Ruffle Lam 0.8MM 771 HGL', '0', '0', 'HGL', 0, 'Barcodes/R771 HGL.jpg')," +
                "('R773 HGL', 'Ruffle Lam 0.8MM 773 HGL', '0', '0', 'HGL', 0, 'Barcodes/R773 HGL.jpg')," +
                "('R818 PR HGL', 'Ruffle Lam 0.8MM 818 (PR) HGL', '0', '0', '(PR) HGL', 0, 'Barcodes/R818 PR HGL.jpg')," +
                "('R819 PR HGL', 'Ruffle Lam 0.8MM 819 (PR) HGL', '0', '0', '(PR) HGL', 0, 'Barcodes/R819 PR HGL.jpg')," +
                "('R834 PR HGL', 'Ruffle Lam 0.8MM 834 (PR) HGL', '0', '0', '(PR) HGL', 0, 'Barcodes/R834 PR HGL.jpg')," +
                "('R835 PR HGL', 'Ruffle Lam 0.8MM 835 (PR) HGL', '0', '0', '(PR) HGL', 0, 'Barcodes/R835 PR HGL.jpg')," +
                "('R305 SF', 'Ruffle Lam 0.8MM 305 SF', '0', '0', 'SF', 0, 'Barcodes/R305 SF.jpg')," +
                "('R306 SF', 'Ruffle Lam 0.8MM 306 SF', '0', '0', 'SF', 0, 'Barcodes/R306 SF.jpg')," +
                "('R307 SF', 'Ruffle Lam 0.8MM 307 SF', '0', '0', 'SF', 0, 'Barcodes/R307 SF.jpg')," +
                "('R308 SF', 'Ruffle Lam 0.8MM 308 SF', '0', '0', 'SF', 0, 'Barcodes/R308 SF.jpg')," +
                "('R309 SF', 'Ruffle Lam 0.8MM 309 SF', '0', '0', 'SF', 0, 'Barcodes/R309 SF.jpg')," +
                "('R310 SF', 'Ruffle Lam 0.8MM 310 SF', '0', '0', 'SF', 0, 'Barcodes/R310 SF.jpg')," +
                "('R311 SF', 'Ruffle Lam 0.8MM 311 SF', '0', '0', 'SF', 0, 'Barcodes/R311 SF.jpg')," +
                "('R312 SF', 'Ruffle Lam 0.8MM 312 SF', '0', '0', 'SF', 0, 'Barcodes/R312 SF.jpg')," +
                "('R335 SF', 'Ruffle Lam 0.8MM 335 SF', '0', '0', 'SF', 0, 'Barcodes/R335 SF.jpg')," +
                "('R314 SF', 'Ruffle Lam 0.8MM 314 SF', '0', '0', 'SF', 0, 'Barcodes/R314 SF.jpg')," +
                "('R315 SF', 'Ruffle Lam 0.8MM 315 SF', '0', '0', 'SF', 0, 'Barcodes/R315 SF.jpg')," +
                "('R316 SF', 'Ruffle Lam 0.8MM 316 SF', '0', '0', 'SF', 0, 'Barcodes/R316 SF.jpg')," +
                "('R317 SF', 'Ruffle Lam 0.8MM 317 SF', '0', '0', 'SF', 0, 'Barcodes/R317 SF.jpg')," +
                "('R318 SF', 'Ruffle Lam 0.8MM 318 SF', '0', '0', 'SF', 0, 'Barcodes/R318 SF.jpg')," +
                "('R319 SF', 'Ruffle Lam 0.8MM 319 SF', '0', '0', 'SF', 0, 'Barcodes/R319 SF.jpg')," +
                "('R325 SF', 'Ruffle Lam 0.8MM 325 SF', '0', '0', 'SF', 0, 'Barcodes/R325 SF.jpg')," +
                "('R326 SF', 'Ruffle Lam 0.8MM 326 SF', '0', '0', 'SF', 0, 'Barcodes/R326 SF.jpg')," +
                "('R327 SF', 'Ruffle Lam 0.8MM 327 SF', '0', '0', 'SF', 0, 'Barcodes/R327 SF.jpg')," +
                "('R328 SF', 'Ruffle Lam 0.8MM 328 SF', '0', '0', 'SF', 0, 'Barcodes/R328 SF.jpg')," +
                "('R329 SF', 'Ruffle Lam 0.8MM 329 SF', '0', '0', 'SF', 0, 'Barcodes/R329 SF.jpg')," +
                "('R330 SF', 'Ruffle Lam 0.8MM 330 SF', '0', '0', 'SF', 0, 'Barcodes/R330 SF.jpg')," +
                "('R336 SF', 'Ruffle Lam 0.8MM 336 SF', '0', '0', 'SF', 0, 'Barcodes/R336 SF.jpg')," +
                "('R337 SF', 'Ruffle Lam 0.8MM 337 SF', '0', '0', 'SF', 0, 'Barcodes/R337 SF.jpg')," +
                "('R338 SF', 'Ruffle Lam 0.8MM 338 SF', '0', '0', 'SF', 0, 'Barcodes/R338 SF.jpg')," +
                "('R339 SF', 'Ruffle Lam 0.8MM 339 SF', '0', '0', 'SF', 0, 'Barcodes/R339 SF.jpg')," +
                "('R340 SF', 'Ruffle Lam 0.8MM 340 SF', '0', '0', 'SF', 0, 'Barcodes/R340 SF.jpg')," +
                "('R352 SF', 'Ruffle Lam 0.8MM 352 SF', '0', '0', 'SF', 0, 'Barcodes/R352 SF.jpg')," +
                "('R351 SF', 'Ruffle Lam 0.8MM 351 SF', '0', '0', 'SF', 0, 'Barcodes/R351 SF.jpg')," +
                "('R349 SF', 'Ruffle Lam 0.8MM 349 SF', '0', '0', 'SF', 0, 'Barcodes/R349 SF.jpg')," +
                "('R350 SF', 'Ruffle Lam 0.8MM 350 SF', '0', '0', 'SF', 0, 'Barcodes/R350 SF.jpg')," +
                "('R348 SF', 'Ruffle Lam 0.8MM 348 SF', '0', '0', 'SF', 0, 'Barcodes/R348 SF.jpg')," +
                "('R347 SF', 'Ruffle Lam 0.8MM 347 SF', '0', '0', 'SF', 0, 'Barcodes/R347 SF.jpg')," +
                "('R345 SF', 'Ruffle Lam 0.8MM 345 SF', '0', '0', 'SF', 0, 'Barcodes/R345 SF.jpg')," +
                "('R353 SF', 'Ruffle Lam 0.8MM 353 SF', '0', '0', 'SF', 0, 'Barcodes/R353 SF.jpg')," +
                "('R354 SF', 'Ruffle Lam 0.8MM 354 SF', '0', '0', 'SF', 0, 'Barcodes/R354 SF.jpg')," +
                "('R362 SF', 'Ruffle Lam 0.8MM 362 SF', '0', '0', 'SF', 0, 'Barcodes/R362 SF.jpg')," +
                "('R363 SF', 'Ruffle Lam 0.8MM 363 SF', '0', '0', 'SF', 0, 'Barcodes/R363 SF.jpg')," +
                "('R364 SF', 'Ruffle Lam 0.8MM 364 SF', '0', '0', 'SF', 0, 'Barcodes/R364 SF.jpg')," +
                "('R365 SF', 'Ruffle Lam 0.8MM 365 SF', '0', '0', 'SF', 0, 'Barcodes/R365 SF.jpg')," +
                "('R366 SF', 'Ruffle Lam 0.8MM 366 SF', '0', '0', 'SF', 0, 'Barcodes/R366 SF.jpg')," +
                "('R520 SF', 'Ruffle Lam 0.8MM 520 SF', '0', '0', 'SF', 0, 'Barcodes/R520 SF.jpg')," +
                "('R521 SF', 'Ruffle Lam 0.8MM 521 SF', '0', '0', 'SF', 0, 'Barcodes/R521 SF.jpg')," +
                "('R526 SF', 'Ruffle Lam 0.8MM 526 SF', '0', '0', 'SF', 0, 'Barcodes/R526 SF.jpg')," +
                "('R534 SF', 'Ruffle Lam 0.8MM 534 SF', '0', '0', 'SF', 0, 'Barcodes/R534 SF.jpg')," +
                "('R536 SF', 'Ruffle Lam 0.8MM 536 SF', '0', '0', 'SF', 0, 'Barcodes/R536 SF.jpg')," +
                "('R537 SF', 'Ruffle Lam 0.8MM 537 SF', '0', '0', 'SF', 0, 'Barcodes/R537 SF.jpg')," +
                "('R538 SF', 'Ruffle Lam 0.8MM 538 SF', '0', '0', 'SF', 0, 'Barcodes/R538 SF.jpg')," +
                "('R539 SF', 'Ruffle Lam 0.8MM 539 SF', '0', '0', 'SF', 0, 'Barcodes/R539 SF.jpg')," +
                "('R545 SF', 'Ruffle Lam 0.8MM 545 SF', '0', '0', 'SF', 0, 'Barcodes/R545 SF.jpg')," +
                "('R546 SF', 'Ruffle Lam 0.8MM 546 SF', '0', '0', 'SF', 0, 'Barcodes/R546 SF.jpg')," +
                "('R549 SF', 'Ruffle Lam 0.8MM 549 SF', '0', '0', 'SF', 0, 'Barcodes/R549 SF.jpg')," +
                "('R550 SF', 'Ruffle Lam 0.8MM 550 SF', '0', '0', 'SF', 0, 'Barcodes/R550 SF.jpg')," +
                "('R582 SF', 'Ruffle Lam 0.8MM 582 SF', '0', '0', 'SF', 0, 'Barcodes/R582 SF.jpg')," +
                "('R628 SF', 'Ruffle Lam 0.8MM 628 SF', '0', '0', 'SF', 0, 'Barcodes/R628 SF.jpg')," +
                "('R650 SF', 'Ruffle Lam 0.8MM 650 SF', '0', '0', 'SF', 0, 'Barcodes/R650 SF.jpg')," +
                "('R652 SF', 'Ruffle Lam 0.8MM 652 SF', '0', '0', 'SF', 0, 'Barcodes/R652 SF.jpg')," +
                "('R653 SF', 'Ruffle Lam 0.8MM 653 SF', '0', '0', 'SF', 0, 'Barcodes/R653 SF.jpg')," +
                "('R684 SF', 'Ruffle Lam 0.8MM 684 SF', '0', '0', 'SF', 0, 'Barcodes/R684 SF.jpg')," +
                "('R685 SF', 'Ruffle Lam 0.8MM 685 SF', '0', '0', 'SF', 0, 'Barcodes/R685 SF.jpg')," +
                "('R706 SF', 'Ruffle Lam 0.8MM 706 SF', '0', '0', 'SF', 0, 'Barcodes/R706 SF.jpg')," +
                "('R731 SF', 'Ruffle Lam 0.8MM 731 SF', '0', '0', 'SF', 0, 'Barcodes/R731 SF.jpg')," +
                "('R732 SF', 'Ruffle Lam 0.8MM 732 SF', '0', '0', 'SF', 0, 'Barcodes/R732 SF.jpg')," +
                "('R740 SF', 'Ruffle Lam 0.8MM 740 SF', '0', '0', 'SF', 0, 'Barcodes/R740 SF.jpg')," +
                "('R742 SF', 'Ruffle Lam 0.8MM 742 SF', '0', '0', 'SF', 0, 'Barcodes/R742 SF.jpg')," +
                "('R743 SF', 'Ruffle Lam 0.8MM 743 SF', '0', '0', 'SF', 0, 'Barcodes/R743 SF.jpg')," +
                "('R745 SF', 'Ruffle Lam 0.8MM 745 SF', '0', '0', 'SF', 0, 'Barcodes/R745 SF.jpg')," +
                "('R746 SF', 'Ruffle Lam 0.8MM 746 SF', '0', '0', 'SF', 0, 'Barcodes/R746 SF.jpg')," +
                "('R750 SF', 'Ruffle Lam 0.8MM 750 SF', '0', '0', 'SF', 0, 'Barcodes/R750 SF.jpg')," +
                "('R751 SF', 'Ruffle Lam 0.8MM 751 SF', '0', '0', 'SF', 0, 'Barcodes/R751 SF.jpg')," +
                "('R752 SF', 'Ruffle Lam 0.8MM 752 SF', '0', '0', 'SF', 0, 'Barcodes/R752 SF.jpg')," +
                "('R762 SF', 'Ruffle Lam 0.8MM 762 SF', '0', '0', 'SF', 0, 'Barcodes/R762 SF.jpg')," +
                "('R101 SF', 'Ruffle Lam 0.8MM 101 SF', '0', '0', 'SF', 0, 'Barcodes/R101 SF.jpg')," +
                "('R101 MT', 'Ruffle Lam 0.8MM 101 MT', '0', '0', 'MT', 0, 'Barcodes/R101 MT.jpg')," +
                "('R102 SF', 'Ruffle Lam 0.8MM 102 SF', '0', '0', 'SF', 0, 'Barcodes/R102 SF.jpg')," +
                "('R102 MT', 'Ruffle Lam 0.8MM 102 MT', '0', '0', 'MT', 0, 'Barcodes/R102 MT.jpg')," +
                "('R103 SF', 'Ruffle Lam 0.8MM 103 SF', '0', '0', 'SF', 0, 'Barcodes/R103 SF.jpg')," +
                "('R105 SF', 'Ruffle Lam 0.8MM 105 SF', '0', '0', 'SF', 0, 'Barcodes/R105 SF.jpg')," +
                "('R107 SF', 'Ruffle Lam 0.8MM 107 SF', '0', '0', 'SF', 0, 'Barcodes/R107 SF.jpg')," +
                "('R110 SF', 'Ruffle Lam 0.8MM 110 SF', '0', '0', 'SF', 0, 'Barcodes/R110 SF.jpg')," +
                "('R111 SF', 'Ruffle Lam 0.8MM 111 SF', '0', '0', 'SF', 0, 'Barcodes/R111 SF.jpg')," +
                "('R117 SF', 'Ruffle Lam 0.8MM 117 SF', '0', '0', 'SF', 0, 'Barcodes/R117 SF.jpg')," +
                "('R118 SF', 'Ruffle Lam 0.8MM 118 SF', '0', '0', 'SF', 0, 'Barcodes/R118 SF.jpg')," +
                "('R119 SF', 'Ruffle Lam 0.8MM 119 SF', '0', '0', 'SF', 0, 'Barcodes/R119 SF.jpg')," +
                "('R121 SF', 'Ruffle Lam 0.8MM 121 SF', '0', '0', 'SF', 0, 'Barcodes/R121 SF.jpg')," +
                "('R123 SF', 'Ruffle Lam 0.8MM 123 SF', '0', '0', 'SF', 0, 'Barcodes/R123 SF.jpg')," +
                "('R124 SF', 'Ruffle Lam 0.8MM 124 SF', '0', '0', 'SF', 0, 'Barcodes/R124 SF.jpg')," +
                "('R127 SF', 'Ruffle Lam 0.8MM 127 SF', '0', '0', 'SF', 0, 'Barcodes/R127 SF.jpg')," +
                "('R128 SF', 'Ruffle Lam 0.8MM 128 SF', '0', '0', 'SF', 0, 'Barcodes/R128 SF.jpg')," +
                "('R129 SF', 'Ruffle Lam 0.8MM 129 SF', '0', '0', 'SF', 0, 'Barcodes/R129 SF.jpg')," +
                "('R130 SF', 'Ruffle Lam 0.8MM 130 SF', '0', '0', 'SF', 0, 'Barcodes/R130 SF.jpg')," +
                "('R131 SF', 'Ruffle Lam 0.8MM 131 SF', '0', '0', 'SF', 0, 'Barcodes/R131 SF.jpg')," +
                "('R132 SF', 'Ruffle Lam 0.8MM 132 SF', '0', '0', 'sf', 0, 'Barcodes/R132 SF.jpg')," +
                "('R134 SF', 'Ruffle Lam 0.8MM 134 SF', '0', '0', 'SF', 0, 'Barcodes/R134 SF.jpg')," +
                "('R135 SF', 'Ruffle Lam 0.8MM 135 SF', '0', '0', 'SF', 0, 'Barcodes/R135 SF.jpg')," +
                "('R138 SF', 'Ruffle Lam 0.8MM 138 SF', '0', '0', 'SF', 0, 'Barcodes/R138 SF.jpg')," +
                "('R102 HGL', 'Ruffle Lam 0.8MM 102 HGL', '0', '0', 'HGL', 0, 'Barcodes/R102 HGL.jpg')," +
                "('R105 HGL', 'Ruffle Lam 0.8MM 105 HGL', '0', '0', 'HGL', 0, 'Barcodes/R105 HGL.jpg')," +
                "('R107 HGL', 'Ruffle Lam 0.8MM 107 HGL', '0', '0', 'HGL', -5, 'Barcodes/R107 HGL.jpg')," +
                "('R111 HGL', 'Ruffle Lam 0.8MM 111 HGL', '0', '0', 'HGL', 0, 'Barcodes/R111 HGL.jpg')," +
                "('R117 HGL', 'Ruffle Lam 0.8MM 117 HGL', '0', '0', 'HGL', 0, 'Barcodes/R117 HGL.jpg')," +
                "('R121 HGL', 'Ruffle Lam 0.8MM 121 HGL', '0', '0', 'HGL', 0, 'Barcodes/R121 HGL.jpg')," +
                "('R123 HGL', 'Ruffle Lam 0.8MM 123 HGL', '0', '0', 'HGL', 0, 'Barcodes/R123 HGL.jpg')," +
                "('R129 HGL', 'Ruffle Lam 0.8MM 129 HGL', '0', '0', 'HGL', 0, 'Barcodes/R129 HGL.jpg')," +
                "('R131 HGL', 'Ruffle Lam 0.8MM 131 HGL', '0', '0', 'HGL', 0, 'Barcodes/R131 HGL.jpg')," +
                "('R135 HGL', 'Ruffle Lam 0.8MM 135 HGL', '0', '0', 'HGL', 0, 'Barcodes/R135 HGL.jpg')," +
                "('R137 HGL', 'Ruffle Lam 0.8MM 137 HGL', '0', '0', 'HGL', 0, 'Barcodes/R137 HGL.jpg')," +
                "('PINK MT', 'Ruffle Lam 0.8MM Pink MT', '0', '0', 'MT', -9, 'Barcodes/PINK MT.jpg')," +
                "('GOLD MT', 'Ruffle Lam 0.8MM Gold MT', '0', '0', 'MT', 0, 'Barcodes/GOLD MT.jpg')," +
                "('GREEN MT', 'Ruffle Lam 0.8MM Green MT', '0', '0', 'MT', 0, 'Barcodes/GREEN MT.jpg')," +
                "('BLACK MT', 'Ruffle Lam 0.8MM Black MT', '0', '0', 'MT', 0, 'Barcodes/BLACK MT.jpg')," +
                "('SILVER MT', 'Ruffle Lam 0.8MM Silver MT', '0', '0', 'MT', 0, 'Barcodes/SILVER MT.jpg')," +
                "('CHARCOAL MT', 'Ruffle Lam 0.8MM Charcoal MT', '0', '0', 'MT', 0, 'Barcodes/CHARCOAL MT.jpg')," +
                "('GREY MT', 'Ruffle Lam 0.8MM Grey MT', '0', '0', 'MT', 0, 'Barcodes/GREY MT.jpg')," +
                "('WHITE CC', 'Ruffle Lam 0.8MM White HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/WHITE CC.jpg')," +
                "('OFFWHITE CC', 'Ruffle Lam 0.8MM OffWhite HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/OFFWHITE CC.jpg')," +
                "('GREY CC', 'Ruffle Lam 0.8MM Grey HGL (CC)', '0', '0', 'CC', -2, 'Barcodes/GREY CC.jpg')," +
                "('GREEN CC', 'Ruffle Lam 0.8MM Green HGL (CC)', '0', '0', 'CC', -4, 'Barcodes/GREEN CC.jpg')," +
                "('ORANGE CC', 'Ruffle Lam 0.8MM Green HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/ORANGE CC.jpg')," +
                "('YELLOW CC', 'Ruffle Lam 0.8MM Yellow HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/YELLOW CC.jpg')," +
                "('RED CC', 'Ruffle Lam 0.8MM Red HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/RED CC.jpg')," +
                "('WINE CC', 'Ruffle Lam 0.8MM Wine HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/WINE CC.jpg')," +
                "('BLACK CC', 'Ruffle Lam 0.8MM Black HGL (CC)', '0', '0', 'CC', 0, 'Barcodes/BLACK CC.jpg')," +
                "('V1001 SF', 'Virgo Lam 0.8MM 1001 SF', '0', '0', 'SF', 0, 'Barcodes/V1001 SF.jpg')," +
                "('V1002 SF', 'Virgo Lam 0.8MM 1002 SF', '0', '0', 'SF', 0, 'Barcodes/V1002 SF.jpg')," +
                "('V1005 SF', 'Virgo Lam 0.8MM 1005 SF', '0', '0', 'SF', 0, 'Barcodes/V1005 SF.jpg')," +
                "('V1006 CF', 'Virgo Lam 0.8MM 1006 CF', '0', '0', 'CF', 0, 'Barcodes/V1006 CF.jpg')," +
                "('V1006 SF', 'Virgo Lam 0.8MM 1006 SF', '0', '0', 'SF', 0, 'Barcodes/V1006 SF.jpg')," +
                "('V1006 GF', 'Virgo Lam 0.8MM 1006 GF', '0', '0', 'GF', 0, 'Barcodes/V1006 GF.jpg')," +
                "('V1018 SF', 'Virgo Lam 0.8MM 1018 SF', '0', '0', 'SF', 0, 'Barcodes/V1018 SF.jpg')," +
                "('V1025 SF', 'Virgo Lam 0.8MM 1025 SF', '0', '0', 'SF', 0, 'Barcodes/V1025 SF.jpg')," +
                "('V1027 SF', 'Virgo Lam 0.8MM 1027 SF', '0', '0', 'SF', -9, 'Barcodes/V1027 SF.jpg')," +
                "('V1028 SF', 'Virgo Lam 0.8MM 1028 SF', '0', '0', 'SF', 0, 'Barcodes/V1028 SF.jpg')," +
                "('V1035 SF', 'Virgo Lam 0.8MM 1035 SF', '0', '0', 'SF', 0, 'Barcodes/V1035 SF.jpg')," +
                "('V1037 SF', 'Virgo Lam 0.8MM 1037 SF', '0', '0', 'SF', 0, 'Barcodes/V1037 SF.jpg')," +
                "('V1038 SF', 'Virgo Lam 0.8MM 1038 SF', '0', '0', 'SF', 0, 'Barcodes/V1038 SF.jpg')," +
                "('V1045 HG', 'Virgo Lam 0.8MM 1045 HG', '0', '0', 'HG', 0, 'Barcodes/V1045 HG.jpg')," +
                "('V1045 SF', 'Virgo Lam 0.8MM 1045 SF', '0', '0', 'SF', 0, 'Barcodes/V1045 SF.jpg')," +
                "('V1047 SF', 'Virgo Lam 0.8MM 1047 SF', '0', '0', 'SF', 0, 'Barcodes/V1047 SF.jpg')," +
                "('V1048 SF', 'Virgo Lam 0.8MM 1048 SF', '0', '0', 'SF', 0, 'Barcodes/V1048 SF.jpg')," +
                "('V1057 SF', 'Virgo Lam 0.8MM 1057 SF', '0', '0', 'SF', 0, 'Barcodes/V1057 SF.jpg')," +
                "('V1058 SF', 'Virgo Lam 0.8MM 1058 SF', '0', '0', 'SF', 0, 'Barcodes/V1058 SF.jpg')," +
                "('V1063 SF', 'Virgo Lam 0.8MM 1063 SF', '0', '0', 'SF', 0, 'Barcodes/V1063 SF.jpg')," +
                "('V1067 SF', 'Virgo Lam 0.8MM 1067 SF', '0', '0', 'SF', 0, 'Barcodes/V1067 SF.jpg')," +
                "('V1068 SF', 'Virgo Lam 0.8MM 1068 SF', '0', '0', 'SF', 0, 'Barcodes/V1068 SF.jpg')," +
                "('V1073 SF', 'Virgo Lam 0.8MM 1073 SF', '0', '0', 'SF', 0, 'Barcodes/V1073 SF.jpg')," +
                "('V1083 SF', 'Virgo Lam 0.8MM 1083 SF', '0', '0', 'SF', 0, 'Barcodes/V1083 SF.jpg')," +
                "('V1088 SF', 'Virgo Lam 0.8MM 1088 SF', '0', '0', 'SF', 0, 'Barcodes/V1088 SF.jpg')," +
                "('V1098 SF', 'Virgo Lam 0.8MM 1098 SF', '0', '0', 'SF', 0, 'Barcodes/V1098 SF.jpg')," +
                "('V1101 SF', 'Virgo Lam 0.8MM 1101 SF', '0', '0', 'SF', 0, 'Barcodes/V1101 SF.jpg')," +
                "('V1209 SF', 'Virgo Lam 0.8MM 1209 SF', '0', '0', 'SF', 0, 'Barcodes/V1209 SF.jpg')," +
                "('V1209 MF', 'Virgo Lam 0.8MM 1209 MF', '0', '0', 'MF', 0, 'Barcodes/V1209 MF.jpg')," +
                "('V1209 GF', 'Virgo Lam 0.8MM 1209 GF', '0', '0', 'GF', 0, 'Barcodes/V1209 GF.jpg')," +
                "('V1245 SF', 'Virgo Lam 0.8MM 1245 SF', '0', '0', 'SF', 0, 'Barcodes/V1245 SF.jpg')," +
                "('V1303 SF', 'Virgo Lam 0.8MM 1303 SF', '0', '0', 'SF', 0, 'Barcodes/V1303 SF.jpg')," +
                "('V1303 MF', 'Virgo Lam 0.8MM 1303 MF', '0', '0', 'MF', 0, 'Barcodes/V1303 MF.jpg')," +
                "('V1504 SF', 'Virgo Lam 0.8MM 1504 SF', '0', '0', 'SF', 0, 'Barcodes/V1504 SF.jpg')," +
                "('V1709 HG', 'Virgo Lam 0.8MM 1709 HG', '0', '0', 'HG', 0, 'Barcodes/V1709 HG.jpg')," +
                "('V1709 CF', 'Virgo Lam 0.8MM 1709 CF', '0', '0', 'cf', 0, 'Barcodes/V1709 CF.jpg')," +
                "('V1709 DC', 'Virgo Lam 0.8MM 1709 DC', '0', '0', 'DC', 0, 'Barcodes/V1709 DC.jpg')," +
                "('V1709 SF', 'Virgo Lam 0.8MM 1709 SF', '0', '0', 'SF', 0, 'Barcodes/V1709 SF.jpg')," +
                "('V1709 MF', 'Virgo Lam 0.8MM 1709 MF', '0', '0', 'MF', 0, 'Barcodes/V1709 MF.jpg')," +
                "('V2028 SF', 'Virgo Lam 0.8MM 2028 SF', '0', '0', 'sf', 0, 'Barcodes/V2028 SF.jpg')," +
                "('V2060 SF', 'Virgo Lam 0.8MM 2060 SF', '0', '0', 'SF', 0, 'Barcodes/V2060 SF.jpg')," +
                "('V2204 SF', 'Virgo Lam 0.8MM 2204 SF', '0', '0', 'SF', 0, 'Barcodes/V2204 SF.jpg')," +
                "('V2207 SF', 'Virgo Lam 0.8MM 2207 SF', '0', '0', 'SF', 0, 'Barcodes/V2207 SF.jpg')," +
                "('V2329 SF', 'Virgo Lam 0.8MM 2329 SF', '0', '0', 'SF', 0, 'Barcodes/V2329 SF.jpg')," +
                "('V2334 SF', 'Virgo Lam 0.8MM 2334 SF', '0', '0', 'SF', 0, 'Barcodes/V2334 SF.jpg')," +
                "('V2335 SF', 'Virgo Lam 0.8MM 2335 SF', '0', '0', 'SF', 0, 'Barcodes/V2335 SF.jpg')," +
                "('V2338 SF', 'Virgo Lam 0.8MM 2338 SF', '0', '0', 'SF', 0, 'Barcodes/V2338 SF.jpg')," +
                "('V2340 SF', 'Virgo Lam 0.8MM 2340 SF', '0', '0', 'SF', 0, 'Barcodes/V2340 SF.jpg')," +
                "('V2343 SF', 'Virgo Lam 0.8MM 2343 SF', '0', '0', 'SF', 0, 'Barcodes/V2343 SF.jpg')," +
                "('V2360 SF', 'Virgo Lam 0.8MM 2360 SF', '0', '0', 'SF', 0, 'Barcodes/V2360 SF.jpg')," +
                "('V2362 SF', 'Virgo Lam 0.8MM 2362 SF', '0', '0', 'SF', 0, 'Barcodes/V2362 SF.jpg')," +
                "('V2420 SF', 'Virgo Lam 0.8MM 2420 SF', '0', '0', 'SF', 0, 'Barcodes/V2420 SF.jpg')," +
                "('V2435 SF', 'Virgo Lam 0.8MM 2435 SF', '0', '0', 'SF', 0, 'Barcodes/V2435 SF.jpg')," +
                "('V2462 SF', 'Virgo Lam 0.8MM 2462 SF', '0', '0', 'SF', 0, 'Barcodes/V2462 SF.jpg')," +
                "('V2499 SF', 'Virgo Lam 0.8MM 2499 SF', '0', '0', 'SF', 0, 'Barcodes/V2499 SF.jpg')," +
                "('V2562 SF', 'Virgo Lam 0.8MM 2562 SF', '0', '0', 'SF', 0, 'Barcodes/V2562 SF.jpg')," +
                "('V2599 SF', 'Virgo Lam 0.8MM 2599 SF', '0', '0', 'SF', 0, 'Barcodes/V2599 SF.jpg')," +
                "('V2606 SF', 'Virgo Lam 0.8MM 2606 SF', '0', '0', 'SF', 0, 'Barcodes/V2606 SF.jpg')," +
                "('V2607 SF', 'Virgo Lam 0.8MM 2607 SF', '0', '0', 'SF', 0, 'Barcodes/V2607 SF.jpg')," +
                "('V2608 SF', 'Virgo Lam 0.8MM 2608 SF', '0', '0', 'SF', 0, 'Barcodes/V2608 SF.jpg')," +
                "('V2609 SF', 'Virgo Lam 0.8MM 2609 SF', '0', '0', 'SF', 0, 'Barcodes/V2609 SF.jpg')," +
                "('V2622 SF', 'Virgo Lam 0.8MM 2622 SF', '0', '0', 'SF', 0, 'Barcodes/V2622 SF.jpg')," +
                "('V2628 SF', 'Virgo Lam 0.8MM 2628 SF', '0', '0', 'SF', 0, 'Barcodes/V2628 SF.jpg')," +
                "('V2629 SF', 'Virgo Lam 0.8MM 2629 SF', '0', '0', 'SF', 0, 'Barcodes/V2629 SF.jpg')," +
                "('V2630 SF', 'Virgo Lam 0.8MM 2630 SF', '0', '0', 'SF', 0, 'Barcodes/V2630 SF.jpg')," +
                "('V2631 SF', 'Virgo Lam 0.8MM 2631 SF', '0', '0', 'SF', 0, 'Barcodes/V2631 SF.jpg')," +
                "('V2641 SF', 'Virgo Lam 0.8MM 2641 SF', '0', '0', 'SF', 0, 'Barcodes/V2641 SF.jpg')," +
                "('V2641 PL', 'Virgo Lam 0.8MM 2641 PL', '0', '0', 'PL', 0, 'Barcodes/V2641 PL.jpg')," +
                "('V2655 SF', 'Virgo Lam 0.8MM 2655 SF', '0', '0', 'SF', 0, 'Barcodes/V2655 SF.jpg')," +
                "('V2701 SF', 'Virgo Lam 0.8MM 2701 SF', '0', '0', 'SF', 0, 'Barcodes/V2701 SF.jpg')," +
                "('V2701 WS', 'Virgo Lam 0.8MM 2701 WS', '0', '0', 'WS', 0, 'Barcodes/V2701 WS.jpg')," +
                "('V2701 SLS', 'Virgo Lam 0.8MM 2701 SLS', '0', '0', 'SLS', 0, 'Barcodes/V2701 SLS.jpg')," +
                "('V2702 SF', 'Virgo Lam 0.8MM 2702 SF', '0', '0', 'SF', 0, 'Barcodes/V2702 SF.jpg')," +
                "('V2703 SF', 'Virgo Lam 0.8MM 2703 SF', '0', '0', 'SF', 0, 'Barcodes/V2703 SF.jpg')," +
                "('V2703 WS', 'Virgo Lam 0.8MM 2703 WS', '0', '0', 'WS', 0, 'Barcodes/V2703 WS.jpg')," +
                "('V2703 HG', 'Virgo Lam 0.8MM 2703 HG', '0', '0', 'HG', 0, 'Barcodes/V2703 HG.jpg')," +
                "('V2703 SLS', 'Virgo Lam 0.8MM 2703 SLS', '0', '0', 'SLS', 0, 'Barcodes/V2703 SLS.jpg')," +
                "('V2709 SF', 'Virgo Lam 0.8MM 2709 SF', '0', '0', 'SF', 0, 'Barcodes/V2709 SF.jpg')," +
                "('V2709 PL', 'Virgo Lam 0.8MM 2709 PL', '0', '0', 'PL', 0, 'Barcodes/V2709 PL.jpg')," +
                "('V2710 HG', 'Virgo Lam 0.8MM 2710 HG', '0', '0', 'HG', 0, 'Barcodes/V2710 HG.jpg')," +
                "('V2710 SL', 'Virgo Lam 0.8MM 2710 SL', '0', '0', 'SL', 0, 'Barcodes/V2710 SL.jpg')," +
                "('V2710 WS', 'Virgo Lam 0.8MM 2710 WS', '0', '0', 'WS', 0, 'Barcodes/V2710 WS.jpg')," +
                "('V2710 WC', 'Virgo Lam 0.8MM 2710 WC', '0', '0', 'WC', 0, 'Barcodes/V2710 WC.jpg')," +
                "('V2710 SF', 'Virgo Lam 0.8MM 2710 SF', '0', '0', 'SF', 0, 'Barcodes/V2710 SF.jpg')," +
                "('V2710 PL', 'Virgo Lam 0.8MM 2710 PL', '0', '0', 'PL', 0, 'Barcodes/V2710 PL.jpg')," +
                "('V2801 SF', 'Virgo Lam 0.8MM 2801 SF', '0', '0', 'SF', 0, 'Barcodes/V2801 SF.jpg')," +
                "('V2803 SF', 'Virgo Lam 0.8MM 2803 SF', '0', '0', 'SF', 0, 'Barcodes/V2803 SF.jpg')," +
                "('V2803 SLS', 'Virgo Lam 0.8MM 2803 SLS', '0', '0', 'SLS', 0, 'Barcodes/V2803 SLS.jpg')," +
                "('V2811 SF', 'Virgo Lam 0.8MM 2811 SF', '0', '0', 'SF', 0, 'Barcodes/V2811 SF.jpg')," +
                "('V2813 SF', 'Virgo Lam 0.8MM 2813 SF', '0', '0', 'SF', 0, 'Barcodes/V2813 SF.jpg')," +
                "('V2813 BE', 'Virgo Lam 0.8MM 2813 BE', '0', '0', 'BE', 0, 'Barcodes/V2813 BE.jpg')," +
                "('V2813 WC', 'Virgo Lam 0.8MM 2813 WC', '0', '0', 'WC', 0, 'Barcodes/V2813 WC.jpg')," +
                "('V2814 BE', 'Virgo Lam 0.8MM 2814 BE', '0', '0', 'BE', 0, 'Barcodes/V2814 BE.jpg')," +
                "('V2814 GA', 'Virgo Lam 0.8MM 2814 GA', '0', '0', 'GA', 0, 'Barcodes/V2814 GA.jpg')," +
                "('V2814 CM', 'Virgo Lam 0.8MM 2814 CM', '0', '0', 'CM', 0, 'Barcodes/V2814 CM.jpg')," +
                "('V2814 ME', 'Virgo Lam 0.8MM 2814 ME', '0', '0', 'ME', 0, 'Barcodes/V2814 ME.jpg')," +
                "('V2814 CF', 'Virgo Lam 0.8MM 2814 CF', '0', '0', 'CF', 0, 'Barcodes/V2814 CF.jpg')," +
                "('V2814 SL', 'Virgo Lam 0.8MM 2814 SL', '0', '0', 'SL', 0, 'Barcodes/V2814 SL.jpg')," +
                "('V2814 WS', 'Virgo Lam 0.8MM 2814 WS', '0', '0', 'WS', 0, 'Barcodes/V2814 WS.jpg')," +
                "('V2814 WC', 'Virgo Lam 0.8MM 2814 WC', '0', '0', 'WC', 0, 'Barcodes/V2814 WC.jpg')," +
                "('V2814 SF', 'Virgo Lam 0.8MM 2814 SF', '0', '0', 'SF', 0, 'Barcodes/V2814 SF.jpg')," +
                "('V2821 SLS', 'Virgo Lam 0.8MM 2821 SLS', '0', '0', 'SLS', 0, 'Barcodes/V2821 SLS.jpg')," +
                "('V2821 SF', 'Virgo Lam 0.8MM 2821 SF', '0', '0', 'SF', 0, 'Barcodes/V2821 SF.jpg')," +
                "('V2822 SF', 'Virgo Lam 0.8MM 2822 SF', '0', '0', 'SF', 0, 'Barcodes/V2822 SF.jpg')," +
                "('V2822 SLS', 'Virgo Lam 0.8MM 2822 SLS', '0', '0', 'SLS', 0, 'Barcodes/V2822 SLS.jpg')," +
                "('V2831 SF', 'Virgo Lam 0.8MM 2831 SF', '0', '0', 'SF', 0, 'Barcodes/V2831 SF.jpg')," +
                "('V2831 WC', 'Virgo Lam 0.8MM 2831 WC', '0', '0', 'WC', 0, 'Barcodes/V2831 WC.jpg')," +
                "('V2831 PL', 'Virgo Lam 0.8MM 2831 PL', '0', '0', 'PL', 0, 'Barcodes/V2831 PL.jpg')," +
                "('V2832 SF', 'Virgo Lam 0.8MM 2832 SF', '0', '0', 'SF', 0, 'Barcodes/V2832 SF.jpg')," +
                "('V2832 PL', 'Virgo Lam 0.8MM 2832 PL', '0', '0', 'PL', 0, 'Barcodes/V2832 PL.jpg')," +
                "('V2832 WC', 'Virgo Lam 0.8MM 2832 WC', '0', '0', 'WC', 0, 'Barcodes/V2832 WC.jpg')," +
                "('V2835 SF', 'Virgo Lam 0.8MM 2835 SF', '0', '0', 'SF', 0, 'Barcodes/V2835 SF.jpg')," +
                "('V2835 WS', 'Virgo Lam 0.8MM 2835 WS', '0', '0', 'WS', 0, 'Barcodes/V2835 WS.jpg')," +
                "('V2835 ME', 'Virgo Lam 0.8MM 2835 ME', '0', '0', 'ME', 0, 'Barcodes/V2835 ME.jpg')," +
                "('V2837 SF', 'Virgo Lam 0.8MM 2837 SF', '0', '0', 'SF', 0, 'Barcodes/V2837 SF.jpg')," +
                "('V2901 HG', 'Virgo Lam 0.8MM 2901 HG', '0', '0', 'HG', 0, 'Barcodes/V2901 HG.jpg')," +
                "('V2901 ME', 'Virgo Lam 0.8MM 2901 ME', '0', '0', 'ME', 0, 'Barcodes/V2901 ME.jpg')," +
                "('V2901 CF', 'Virgo Lam 0.8MM 2901 CF', '0', '0', 'CF', 0, 'Barcodes/V2901 CF.jpg')," +
                "('V2901 SF', 'Virgo Lam 0.8MM 2901 SF', '0', '0', 'SF', 0, 'Barcodes/V2901 SF.jpg')," +
                "('V2901 SL', 'Virgo Lam 0.8MM 2901 SL', '0', '0', 'SL', 0, 'Barcodes/V2901 SL.jpg')," +
                "('V2902 HG', 'Virgo Lam 0.8MM 2902 HG', '0', '0', 'HG', 0, 'Barcodes/V2902 HG.jpg')," +
                "('V2902 SS', 'Virgo Lam 0.8MM 2902 SS', '0', '0', 'SS', 0, 'Barcodes/V2902 SS.jpg')," +
                "('V2902 SF', 'Virgo Lam 0.8MM 2902 SF', '0', '0', 'SF', 0, 'Barcodes/V2902 SF.jpg')," +
                "('V2902 ME', 'Virgo Lam 0.8MM 2902 ME', '0', '0', 'ME', 0, 'Barcodes/V2902 ME.jpg')," +
                "('V2903 GA', 'Virgo Lam 0.8MM 2903 GA', '0', '0', 'GA', 0, 'Barcodes/V2903 GA.jpg')," +
                "('V2903 ME', 'Virgo Lam 0.8MM 2903 ME', '0', '0', 'ME', 0, 'Barcodes/V2903 ME.jpg')," +
                "('V2903 SS', 'Virgo Lam 0.8MM 2903 SS', '0', '0', 'SS', 0, 'Barcodes/V2903 SS.jpg')," +
                "('V2903 SF', 'Virgo Lam 0.8MM 2903 SF', '0', '0', 'SF', 0, 'Barcodes/V2903 SF.jpg')," +
                "('V2903 CF', 'Virgo Lam 0.8MM 2903 CF', '0', '0', 'CF', 0, 'Barcodes/V2903 CF.jpg')," +
                "('V2903 SL', 'Virgo Lam 0.8MM 2903 SL', '0', '0', 'SL', 0, 'Barcodes/V2903 SL.jpg')," +
                "('V2905 SF', 'Virgo Lam 0.8MM 2905 SF', '0', '0', 'SF', 0, 'Barcodes/V2905 SF.jpg')," +
                "('V2905 SL', 'Virgo Lam 0.8MM 2905 SL', '0', '0', 'SL', 0, 'Barcodes/V2905 SL.jpg')," +
                "('V2906 SF', 'Virgo Lam 0.8MM 2906 SF', '0', '0', 'SF', 0, 'Barcodes/V2906 SF.jpg')," +
                "('V2906 HG', 'Virgo Lam 0.8MM 2906 HG', '0', '0', 'HG', 0, 'Barcodes/V2906 HG.jpg')," +
                "('V2906 SL', 'Virgo Lam 0.8MM 2906 SL', '0', '0', 'SL', 0, 'Barcodes/V2906 SL.jpg')," +
                "('V2906 TF', 'Virgo Lam 0.8MM 2906 TF', '0', '0', 'TF', 0, 'Barcodes/V2906 TF.jpg')," +
                "('V2906 CF', 'Virgo Lam 0.8MM 2906 CF', '0', '0', 'CF', 0, 'Barcodes/V2906 CF.jpg')," +
                "('V2909 SF', 'Virgo Lam 0.8MM 2909 SF', '0', '0', 'SF', 0, 'Barcodes/V2909 SF.jpg')," +
                "('V2910 SF', 'Virgo Lam 0.8MM 2910 SF', '0', '0', 'SF', 0, 'Barcodes/V2910 SF.jpg')," +
                "('V2971 SF', 'Virgo Lam 0.8MM 2971 SF', '0', '0', 'SF', 0, 'Barcodes/V2971 SF.jpg')," +
                "('V2971 GA', 'Virgo Lam 0.8MM 2971 GA', '0', '0', 'GA', 0, 'Barcodes/V2971 GA.jpg')," +
                "('V2971 TF', 'Virgo Lam 0.8MM 2971 TF', '0', '0', 'TF', 0, 'Barcodes/V2971 TF.jpg')," +
                "('V2971 WS', 'Virgo Lam 0.8MM 2971 WS', '0', '0', 'WS', 0, 'Barcodes/V2971 WS.jpg')," +
                "('V2972 CM', 'Virgo Lam 0.8MM 2972 CM', '0', '0', 'CM', 0, 'Barcodes/V2972 CM.jpg')," +
                "('V2972 SF', 'Virgo Lam 0.8MM 2972 SF', '0', '0', 'SF', 0, 'Barcodes/V2972 SF.jpg')," +
                "('V2972 TF', 'Virgo Lam 0.8MM 2972 TF', '0', '0', 'TF', 0, 'Barcodes/V2972 TF.jpg')," +
                "('V2977 GA', 'Virgo Lam 0.8MM 2977 GA', '0', '0', 'GA', 0, 'Barcodes/V2977 GA.jpg')," +
                "('V2977 SF', 'Virgo Lam 0.8MM 2977 SF', '0', '0', 'SF', 0, 'Barcodes/V2977 SF.jpg')," +
                "('V2977 CM', 'Virgo Lam 0.8MM 2977 CM', '0', '0', 'CM', 0, 'Barcodes/V2977 CM.jpg')," +
                "('V2978 HG', 'Virgo Lam 0.8MM 2978 HG', '0', '0', 'HG', 0, 'Barcodes/V2978 HG.jpg')," +
                "('V2978 SF', 'Virgo Lam 0.8MM 2978 SF', '0', '0', 'SF', 0, 'Barcodes/V2978 SF.jpg')," +
                "('V2978 TF', 'Virgo Lam 0.8MM 2978 TF', '0', '0', 'TF', 0, 'Barcodes/V2978 TF.jpg')," +
                "('V2978 CM', 'Virgo Lam 0.8MM 2978 CM', '0', '0', 'CM', 0, 'Barcodes/V2978 CM.jpg')," +
                "('V2981 HG', 'Virgo Lam 0.8MM 2981 HG', '0', '0', 'HG', 0, 'Barcodes/V2981 HG.jpg')," +
                "('V2981 DC', 'Virgo Lam 0.8MM 2981 DC', '0', '0', 'DC', 0, 'Barcodes/V2981 DC.jpg')," +
                "('V2981 CM', 'Virgo Lam 0.8MM 2981 CM', '0', '0', 'CM', 0, 'Barcodes/V2981 CM.jpg')," +
                "('V2981 GA', 'Virgo Lam 0.8MM 2981 GA', '0', '0', 'GA', 0, 'Barcodes/V2981 GA.jpg')," +
                "('V2981 TF', 'Virgo Lam 0.8MM 2981 TF', '0', '0', 'TF', 0, 'Barcodes/V2981 TF.jpg')," +
                "('V2981 SF', 'Virgo Lam 0.8MM 2981 SF', '0', '0', 'SF', 0, 'Barcodes/V2981 SF.jpg')," +
                "('V2981 WC', 'Virgo Lam 0.8MM V2981 WC', '0', '0', 'WC', 0, 'Barcodes/V2981 WC.jpg')," +
                "('V2982 SF', 'Virgo Lam 0.8MM 2982 SF', '0', '0', 'SF', 0, 'Barcodes/V2982 SF.jpg')," +
                "('V2982 GA', 'Virgo Lam 0.8MM 2982 GA', '0', '0', 'GA', 0, 'Barcodes/V2982 GA.jpg')," +
                "('V2983 SS', 'Virgo Lam 0.8MM 2983 SS', '0', '0', 'SS', 0, 'Barcodes/V2983 SS.jpg')," +
                "('V2983 BE', 'Virgo Lam 0.8MM 2983 BE', '0', '0', 'BE', 0, 'Barcodes/V2983 BE.jpg')," +
                "('V2983 SF', 'Virgo Lam 0.8MM 2983 SF', '0', '0', 'SF', 0, 'Barcodes/V2983 SF.jpg')," +
                "('V2984 BE', 'Virgo Lam 0.8MM 2984 BE', '0', '0', 'BE', 0, 'Barcodes/V2984 BE.jpg')," +
                "('V2984 SS', 'Virgo Lam 0.8MM 2984 SS', '0', '0', 'SS', 0, 'Barcodes/V2984 SS.jpg')," +
                "('V2984 SF', 'Virgo Lam 0.8MM 2984 SF', '0', '0', 'SF', 0, 'Barcodes/V2984 SF.jpg')," +
                "('V2985 TF', 'Virgo Lam 0.8MM 2985 TF', '0', '0', 'TF', 0, 'Barcodes/V2985 TF.jpg')," +
                "('V2985 SF', 'Virgo Lam 0.8MM 2985 SF', '0', '0', 'SF', 0, 'Barcodes/V2985 SF.jpg')," +
                "('V2985 HG', 'Virgo Lam 0.8MM 2985 HG', '0', '0', 'HG', 0, 'Barcodes/V2985 HG.jpg')," +
                "('V2985 CM', 'Virgo Lam 0.8MM 2985 CM', '0', '0', 'CM', 0, 'Barcodes/V2985 CM.jpg')," +
                "('V2987 DC', 'Virgo Lam 0.8MM 2987 DC', '0', '0', 'DC', 0, 'Barcodes/V2987 DC.jpg')," +
                "('V2987 SF', 'Virgo Lam 0.8MM 2987 SF', '0', '0', 'SF', 0, 'Barcodes/V2987 SF.jpg')," +
                "('V2988 DC', 'Virgo Lam 0.8MM 2988 DC', '0', '0', 'DC', 0, 'Barcodes/V2988 DC.jpg')," +
                "('V2988 SF', 'Virgo Lam 0.8MM 2988 SF', '0', '0', 'SF', 0, 'Barcodes/V2988 SF.jpg')," +
                "('V2991 HG', 'Virgo Lam 0.8MM 2991 HG', '0', '0', 'HG', 0, 'Barcodes/V2991 HG.jpg')," +
                "('V2991 SF', 'Virgo Lam 0.8MM 2991 SF', '0', '0', 'SF', 0, 'Barcodes/V2991 SF.jpg')," +
                "('V2992 HG', 'Virgo Lam 0.8MM 2992 HG', '0', '0', 'HG', 0, 'Barcodes/V2992 HG.jpg')," +
                "('V2992 SF', 'Virgo Lam 0.8MM 2992 SF', '0', '0', 'SF', 0, 'Barcodes/V2992 SF.jpg')," +
                "('V2994 SF', 'Virgo Lam 0.8MM 2994 SF', '0', '0', 'SF', 0, 'Barcodes/V2994 SF.jpg')," +
                "('V2994 HG', 'Virgo Lam 0.8MM 2994 HG', '0', '0', 'HG', 0, 'Barcodes/V2994 HG.jpg')," +
                "('V2995 HG', 'Virgo Lam 0.8MM 2995 HG', '0', '0', 'HG', 0, 'Barcodes/V2995 HG.jpg')," +
                "('V2995 SF', 'Virgo Lam 0.8MM 2995 SF', '0', '0', 'SF', 0, 'Barcodes/V2995 SF.jpg')," +
                "('V2996 DC', 'Virgo Lam 0.8MM 2996 DC', '0', '0', 'DC', 0, 'Barcodes/V2996 DC.jpg')," +
                "('V2996 SF', 'Virgo Lam 0.8MM 2996 SF', '0', '0', 'SF', 0, 'Barcodes/V2996 SF.jpg')," +
                "('V2997 DC', 'Virgo Lam 0.8MM 2997 DC', '0', '0', 'DC', 0, 'Barcodes/V2997 DC.jpg')," +
                "('V2997 SF', 'Virgo Lam 0.8MM 2997 SF', '0', '0', 'SF', 0, 'Barcodes/V2997 SF.jpg')," +
                "('V2998 BE', 'Virgo Lam 0.8MM 2998 BE', '0', '0', 'BE', 0, 'Barcodes/V2998 BE.jpg')," +
                "('V2998 HG', 'Virgo Lam 0.8MM 2998 HG', '0', '0', 'HG', 0, 'Barcodes/V2998 HG.jpg')," +
                "('V2998 SF', 'Virgo Lam 0.8MM 2998 SF', '0', '0', 'SF', 0, 'Barcodes/V2998 SF.jpg')," +
                "('V2998 SS', 'Virgo Lam 0.8MM 2988 SS', '0', '0', 'SS', 0, 'Barcodes/V2998 SS.jpg')," +
                "('V2999 SF', 'Virgo Lam 0.8MM 2999 SF', '0', '0', 'SF', 0, 'Barcodes/V2999 SF.jpg')," +
                "('V2999 HG', 'Virgo Lam 0.8MM 2999 HG', '0', '0', 'HG', 0, 'Barcodes/V2999 HG.jpg')," +
                "('V2999 BE', 'Virgo Lam 0.8MM 2999 BE', '0', '0', 'BE', 0, 'Barcodes/V2999 BE.jpg')," +
                "('V2999 SS', 'Virgo Lam 0.8MM 2999 SS', '0', '0', 'SS', 0, 'Barcodes/V2999 SS.jpg')," +
                "('V4043 PL', 'Virgo Lam 0.8MM 4043 PL', '0', '0', 'PL', 0, 'Barcodes/V4043 PL.jpg')," +
                "('V4043 SLS', 'Virgo Lam 0.8MM 4043 SLS', '0', '0', 'SLS', 0, 'Barcodes/V4043 SLS.jpg')," +
                "('V4043 SF', 'Virgo Lam 0.8MM 4043 SF', '0', '0', 'SF', 0, 'Barcodes/V4043 SF.jpg')," +
                "('V4052 SF', 'Virgo Lam 0.8MM 4052 SF', '0', '0', 'SF', 0, 'Barcodes/V4052 SF.jpg')," +
                "('V4052 SLS', 'Virgo Lam 0.8MM 4052 SLS', '0', '0', 'SLS', 0, 'Barcodes/V4052 SLS.jpg')," +
                "('V4052 PL', 'Virgo Lam 0.8MM 4052 PL', '0', '0', 'PL', 0, 'Barcodes/V4052 PL.jpg')," +
                "('DG 101', 'Virgo Lam 1.00MM DG 101 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 101.jpg')," +
                "('DG 102', 'Virgo Lam 1.00MM DG 102 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 102.jpg')," +
                "('DG 103', 'Virgo Lam 1.00MM DG 103 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 103.jpg')," +
                "('DG 104', 'Virgo Lam 1.00MM DG 104 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 104.jpg')," +
                "('DG 105', 'Virgo Lam 1.00MM DG 105 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 105.jpg')," +
                "('DG 106', 'Virgo Lam 1.00MM DG 106 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 106.jpg')," +
                "('DG 201', 'Virgo Lam 1.00MM DG 201 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 201.jpg')," +
                "('DG 202', 'Virgo Lam 1.00MM DG 202 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 202.jpg')," +
                "('DG 203', 'Virgo Lam 1.00MM DG 203 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 203.jpg')," +
                "('DG 204', 'Virgo Lam 1.00MM DG 204 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 204.jpg')," +
                "('DG 205', 'Virgo Lam 1.00MM DG 205 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 205.jpg')," +
                "('DG 206', 'Virgo Lam 1.00MM DG 206 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 206.jpg')," +
                "('DG 301', 'Virgo Lam 1.00MM DG 301 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 301.jpg')," +
                "('DG 302', 'Virgo Lam 1.00MM DG 302 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 302.jpg')," +
                "('DG 303', 'Virgo Lam 1.00MM DG 303 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 303.jpg')," +
                "('DG 304', 'Virgo Lam 1.00MM DG 304 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 304.jpg')," +
                "('DG 305', 'Virgo Lam 1.00MM DG 305 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 305.jpg')," +
                "('DG 306', 'Virgo Lam 1.00MM DG 306 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 306.jpg')," +
                "('DG 401', 'Virgo Lam 1.00MM DG 401 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 401.jpg')," +
                "('DG 402', 'Virgo Lam 1.00MM DG 402 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 402.jpg')," +
                "('DG 403', 'Virgo Lam 1.00MM DG 403 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 403.jpg')," +
                "('DG 404', 'Virgo Lam 1.00MM DG 404 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 404.jpg')," +
                "('DG 405', 'Virgo Lam 1.00MM DG 405 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 405.jpg')," +
                "('DG 406', 'Virgo Lam 1.00MM DG 406 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 406.jpg')," +
                "('DG 407', 'Virgo Lam 1.00MM DG 407 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 407.jpg')," +
                "('DG 408', 'Virgo Lam 1.00MM DG 408 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 408.jpg')," +
                "('DG 409', 'Virgo Lam 1.00MM DG 409 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 409.jpg')," +
                "('DG 410', 'Virgo Lam 1.00MM DG 410 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 410.jpg')," +
                "('DG 411', 'Virgo Lam 1.00MM DG 411 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 411.jpg')," +
                "('DG 412', 'Virgo Lam 1.00MM DG 412 SHG', '0', '0', 'SHG', 0, 'Barcodes/DG 412.jpg')," +
                "('HD1', 'Virgo Lam 1.00MM HD1 SHG', '0', '0', 'SHG', 0, 'Barcodes/HD1.jpg')," +
                "('HD2', 'Virgo Lam 1.00MM HD2 SHG', '0', '0', 'SHG', 0, 'Barcodes/HD2.jpg')," +
                "('6951 PW', 'Virgo Lam 1.00MM 6951 PW', '0', '0', 'PW', 0, 'Barcodes/6951 PW.jpg')," +
                "('6952 PW', 'Virgo Lam 1.00MM 6952 PW', '0', '0', 'PW', 0, 'Barcodes/6952 PW.jpg')," +
                "('6953 PW', 'Virgo Lam 1.00MM 6953 PW', '0', '0', 'PW', 0, 'Barcodes/6953 PW.jpg')," +
                "('6941 CO', 'Virgo Lam 1.00MM 6941 CO', '0', '0', 'CO', 0, 'Barcodes/6941 CO.jpg')," +
                "('6942 CO', 'Virgo Lam 1.00MM 6942 CO', '0', '0', 'CO', 0, 'Barcodes/6942 CO.jpg')," +
                "('6943 CO', 'Virgo Lam 1.00MM 6943 CO', '0', '0', 'CO', 0, 'Barcodes/6943 CO.jpg')," +
                "('6901 FB', 'Virgo Lam 1.00MM 6901 FB', '0', '0', 'FB', 0, 'Barcodes/6901 FB.jpg')," +
                "('6902 FB', 'Virgo Lam 1.00MM 6902 FB', '0', '0', 'FB', 0, 'Barcodes/6902 FB.jpg')," +
                "('6903 FB', 'Virgo Lam 1.00MM 6903 FB', '0', '0', 'FB', 0, 'Barcodes/6903 FB.jpg')," +
                "('6931 DW', 'Virgo Lam 1.00MM 6931 DW', '0', '0', 'DW', 0, 'Barcodes/6931 DW.jpg')," +
                "('6932 DW', 'Virgo Lam 1.00MM 6932 DW', '0', '0', 'DW', 0, 'Barcodes/6932 DW.jpg')," +
                "('6933 DW', 'Virgo Lam 1.00MM 6933 DW', '0', '0', 'DW', 0, 'Barcodes/6933 DW.jpg')," +
                "('6921 TR', 'Virgo Lam 1.00MM 6921 TR', '0', '0', 'TR', 0, 'Barcodes/6921 TR.jpg')," +
                "('6922 TR', 'Virgo Lam 1.00MM 6922 TR', '0', '0', 'TR', 0, 'Barcodes/6922 TR.jpg')," +
                "('6923 TR', 'Virgo Lam 1.00MM 6923 TR', '0', '0', 'TR', 0, 'Barcodes/6923 TR.jpg')," +
                "('6911 CA', 'Virgo Lam 1.00MM 6911 CA', '0', '0', 'CA', 0, 'Barcodes/6911 CA.jpg')," +
                "('6912 CA', 'Virgo Lam 1.00MM 6912 CA', '0', '0', 'CA', 0, 'Barcodes/6912 CA.jpg')," +
                "('6913 CA', 'Virgo Lam 1.00MM 6913 CA', '0', '0', 'CA', 0, 'Barcodes/6913 CA.jpg')," +
                "('1002 SHG', 'Virgo Lam 1.00MM 1002 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1002 SHG.jpg')," +
                "('1005 SHG', 'Virgo Lam 1.00MM 1005 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1005 SHG.jpg')," +
                "('1006 SHG', 'Virgo Lam 1.00MM 1006 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1006 SHG.jpg')," +
                "('1038 SHG', 'Virgo Lam 1.00MM 1038 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1038 SHG.jpg')," +
                "('1055 SHG', 'Virgo Lam 1.00MM 1055 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1055 SHG.jpg')," +
                "('1057 SHG', 'Virgo Lam 1.00MM 1057 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1057 SHG.jpg')," +
                "('1075 SHG', 'Virgo Lam 1.00MM 1075 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1075 SHG.jpg')," +
                "('1085 SHG', 'Virgo Lam 1.00MM 1085 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1085 SHG.jpg')," +
                "('1086 SHG', 'Virgo Lam 1.00MM 1086 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1086 SHG.jpg')," +
                "('1098 SHG', 'Virgo Lam 1.00MM 1098 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1098 SHG.jpg')," +
                "('1101 SHG', 'Virgo Lam 1.00MM 1101 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1101 SHG.jpg')," +
                "('1245 SHG', 'Virgo Lam 1.00MM 1245 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1245 SHG.jpg')," +
                "('1303 SHG', 'Virgo Lam 1.00MM 1303 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1303 SHG.jpg')," +
                "('1309 SHG', 'Virgo Lam 1.00MM 1309 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1309 SHG.jpg')," +
                "('1409 SHG', 'Virgo Lam 1.00MM 1409 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1409 SHG.jpg')," +
                "('1505 SHG', 'Virgo Lam 1.00MM 1505 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1505 SHG.jpg')," +
                "('1507 SHG', 'Virgo Lam 1.00MM 1507 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1507 SHG.jpg')," +
                "('1515 SHG', 'Virgo Lam 1.00MM 1515 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1515 SHG.jpg')," +
                "('1517 SHG', 'Virgo Lam 1.00MM 1517 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1517 SHG.jpg')," +
                "('1521 SHG', 'Virgo Lam 1.00MM 1521 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1521 SHG.jpg')," +
                "('1522 SHG', 'Virgo Lam 1.00MM 1522 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1522 SHG.jpg')," +
                "('1523 SHG', 'Virgo Lam 1.00MM 1523 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1523 SHG.jpg')," +
                "('1524 SHG', 'Virgo Lam 1.00MM 1524 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1524 SHG.jpg')," +
                "('1525 SHG', 'Virgo Lam 1.00MM 1525 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1525 SHG.jpg')," +
                "('1527 SHG', 'Virgo Lam 1.00MM 1527 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1527 SHG.jpg')," +
                "('1921 SHG', 'Virgo Lam 1.00MM 1921 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1921 SHG.jpg')," +
                "('1922 SHG', 'Virgo Lam 1.00MM 1922 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1922 SHG.jpg')," +
                "('1923 SHG', 'Virgo Lam 1.00MM 1923 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/1923 SHG.jpg')," +
                "('5328 SHG', 'Virgo Lam 1.00MM 5328 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/5328 SHG.jpg')," +
                "('5329 SHG', 'Virgo Lam 1.00MM 5329 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/5329 SHG.jpg')," +
                "('5391 SHG', 'Virgo Lam 1.00MM 5291 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/5391 SHG.jpg')," +
                "('5831 SHG', 'Virgo Lam 1.00MM 5831 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/5831 SHG.jpg')," +
                "('5832 SHG', 'Virgo Lam 1.00MM 5832 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/5832 SHG.jpg')," +
                "('6302 SHG', 'Virgo Lam 1.00MM 6302 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6302 SHG.jpg')," +
                "('6303 SHG', 'Virgo Lam 1.00MM 6306 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6303 SHG.jpg')," +
                "('6305 SHG', 'Virgo Lam 1.00MM 6305 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6305 SHG.jpg')," +
                "('6306 SHG', 'Virgo Lam 1.00MM 6306 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6306 SHG.jpg')," +
                "('6312 SHG', 'Virgo Lam 1.00MM 6312 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6312 SHG.jpg')," +
                "('6313 SHG', 'Virgo Lam 1.00MM 6313 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6313 SHG.jpg')," +
                "('6316 SHG', 'Virgo Lam 1.00MM 6316 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6316 SHG.jpg')," +
                "('6317 SHG', 'Virgo Lam 1.00MM 6317 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6317 SHG.jpg')," +
                "('6318 SHG', 'Virgo Lam 1.00MM 6318 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6318 SHG.jpg')," +
                "('6319 SHG', 'Virgo Lam 1.00MM 6319 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6319 SHG.jpg')," +
                "('6320 SHG', 'Virgo Lam 1.00MM 6320 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6320 SHG.jpg')," +
                "('6321 SHG', 'Virgo Lam 1.00MM 6321 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6321 SHG.jpg')," +
                "('6322 SHG', 'Virgo Lam 1.00MM 6322 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6322 SHG.jpg')," +
                "('6329 SHG', 'Virgo Lam 1.00MM 6329 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6329 SHG.jpg')," +
                "('6330 SHG', 'Virgo Lam 1.00MM 6330 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6330 SHG.jpg')," +
                "('6601 SHG', 'Virgo Lam 1.00MM 6601 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6601 SHG.jpg')," +
                "('6602 SHG', 'Virgo Lam 1.00MM 6602 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6602 SHG.jpg')," +
                "('6603 SHG', 'Virgo Lam 1.00MM 6603 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6603 SHG.jpg')," +
                "('6905 SHG', 'Virgo Lam 1.00MM 6905 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6905 SHG.jpg')," +
                "('6906 SHG', 'Virgo Lam 1.00MM 6906 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/6906 SHG.jpg')," +
                "('9907 SHG', 'Virgo Lam 1.00MM 9907 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/9907 SHG.jpg')," +
                "('9910 SHG', 'Virgo Lam 1.00MM 9910 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/9910 SHG.jpg')," +
                "('9911 SHG', 'Virgo Lam 1.00MM 9911 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/9911 SHG.jpg')," +
                "('9913 SHG', 'Virgo Lam 1.00MM 9913 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/9913 SHG.jpg')," +
                "('9914 SHG', 'Virgo Lam 1.00MM 9914 SHG (UV+)', '0', '0', 'SHG', 0, 'Barcodes/9914 SHG.jpg')," +
                "('1055 UC', 'Virgo Lam 1.00MM 1055 UC', '0', '0', 'UC', 0, 'Barcodes/1055 UC.jpg')," +
                "('1098 UC', 'Virgo Lam 1.00MM 1098 UC', '0', '0', 'UC', 0, 'Barcodes/1098 UC.jpg')," +
                "('1245 UC', 'Virgo Lam 1.00MM 1245 UC', '0', '0', 'UC', 0, 'Barcodes/1245 UC.jpg')," +
                "('1309 UC', 'Virgo Lam 1.00MM 1309 UC', '0', '0', 'UC', 0, 'Barcodes/1309 UC.jpg')," +
                "('1409 UC', 'Virgo Lam 1.00MM 1409 UC', '0', '0', 'UC', 0, 'Barcodes/1409 UC.jpg')," +
                "('1055 GLX2', 'Virgo Lam 1.00MM 1055 GLX2', '0', '0', 'GLX2', 0, 'Barcodes/1055 GLX2.jpg')," +
                "('1055 GLX1', 'Virgo Lam 1.00MM 1055 GLX1', '0', '0', 'GLX1', 0, 'Barcodes/1055 GLX1.jpg')," +
                "('1098 GLX2', 'Virgo Lam 1.00MM 1098 GLX2', '0', '0', 'GLX2', 0, 'Barcodes/1098 GLX2.jpg')," +
                "('1098 GLX1', 'Virgo Lam 1.00MM 1098 GLX1', '0', '0', 'GLX1', 0, 'Barcodes/1098 GLX1.jpg')," +
                "('1245 GLX2', 'Virgo Lam 1.00MM 1245 GLX2', '0', '0', 'GLX2', 0, 'Barcodes/1245 GLX2.jpg')," +
                "('1245 GLX1', 'Virgo Lam 1.00MM 1245 GLX1', '0', '0', 'GLX1', 0, 'Barcodes/1245 GLX1.jpg')," +
                "('1409 GLX2', 'Virgo Lam 1.00MM 1409 GLX2', '0', '0', 'GLX2', 0, 'Barcodes/1409 GLX2.jpg')," +
                "('1409 GLX1', 'Virgo Lam 1.00MM 1409 GLX1', '0', '0', 'GLX1', 0, 'Barcodes/1409 GLX1.jpg')," +
                "('1038 OB', 'Virgo Lam 1.00MM 1038 OB', '0', '0', 'OB', 0, 'Barcodes/1038 OB.jpg')," +
                "('1055 OB', 'Virgo Lam 1.00MM 1055 OB', '0', '0', 'OB', 0, 'Barcodes/1055 OB.jpg')," +
                "('1075 OB', 'Virgo Lam 1.00MM 1075 OB', '0', '0', 'OB', 0, 'Barcodes/1075 OB.jpg')," +
                "('1098 OB', 'Virgo Lam 1.00MM 1098 OB', '0', '0', 'OB', 0, 'Barcodes/1098 OB.jpg')," +
                "('1409 OB', 'Virgo Lam 1.00MM 1409 OB', '0', '0', 'OB', 0, 'Barcodes/1409 OB.jpg')," +
                "('1507 OB', 'Virgo Lam 1.00MM 1507 OB', '0', '0', 'OB', 0, 'Barcodes/1507 OB.jpg')," +
                "('5809 FH', 'Virgo Lam 1.00MM 5809 FH', '0', '0', 'FH', 0, 'Barcodes/5809 FH.jpg')," +
                "('9912 FH', 'Virgo Lam 1.00MM 9912 FH', '0', '0', 'FH', 0, 'Barcodes/9912 FH.jpg')," +
                "('9913 FH', 'Virgo Lam 1.00MM 9913 FH', '0', '0', 'FH', 0, 'Barcodes/9913 FH.jpg')," +
                "('9914 FH', 'Virgo Lam 1.00MM 9914 FH', '0', '0', 'FH', 0, 'Barcodes/9914 FH.jpg')," +
                "('9915 FH', 'Virgo Lam 1.00MM 9915 FH', '0', '0', 'FH', 0, 'Barcodes/9915 FH.jpg')," +
                "('9916 FH', 'Virgo Lam 1.00MM 9916 FH', '0', '0', 'FH', 0, 'Barcodes/9916 FH.jpg')," +
                "('1409 HCL', 'Virgo Lam 1.00MM 1409 HCL', '0', '0', 'HCL', 0, 'Barcodes/1409 HCL.jpg')," +
                "('5416 HCL', 'Virgo Lam 1.00MM 5416 HCL', '0', '0', 'HCL', 0, 'Barcodes/5416 HCL.jpg')," +
                "('5418 HCL', 'Virgo Lam 1.00MM 5418 HCL', '0', '0', 'HCL', 0, 'Barcodes/5418 HCL.jpg')," +
                "('9901 RB', 'Virgo Lam 1.00MM 9901 RB', '0', '0', 'RB', 0, 'Barcodes/9901 RB.jpg')," +
                "('9904 RB', 'Virgo Lam 1.00MM 9904 RB', '0', '0', 'RB', 0, 'Barcodes/9904 RB.jpg')," +
                "('9906 RB', 'Virgo Lam 1.00MM 9906 RB', '0', '0', 'RB', 0, 'Barcodes/9906 RB.jpg')," +
                "('9907 RB', 'Virgo Lam 1.00MM 9907 RB', '0', '0', 'RB', 0, 'Barcodes/9907 RB.jpg')," +
                "('1409 CW', 'Virgo Lam 1.00MM 1409 CW', '0', '0', 'CW', 0, 'Barcodes/1409 CW.jpg')," +
                "('5277 CW', 'Virgo Lam 1.00MM 5277 CW', '0', '0', 'CW', 0, 'Barcodes/5277 CW.jpg')," +
                "('5278 CW', 'Virgo Lam 1.00MM 5278 CW', '0', '0', 'CW', 0, 'Barcodes/5278 CW.jpg')," +
                "('5403 CW', 'Virgo Lam 1.00MM 5403 CW', '0', '0', 'CW', 0, 'Barcodes/5403 CW.jpg')," +
                "('5806 CW', 'Virgo Lam 1.00MM 5806 CW', '0', '0', 'CW', 0, 'Barcodes/5806 CW.jpg')," +
                "('5807 CW', 'Virgo Lam 1.00MM 5807 CW', '0', '0', 'CW', 0, 'Barcodes/5807 CW.jpg')," +
                "('5809 CW', 'Virgo Lam 1.00MM 5809 CW', '0', '0', 'CW', 0, 'Barcodes/5809 CW.jpg')," +
                "('5908 CW', 'Virgo Lam 1.00MM 5908 CW', '0', '0', 'CW', 0, 'Barcodes/5908 CW.jpg')," +
                "('1409 CN', 'Virgo Lam 1.00MM 1409 CN', '0', '0', 'CN', 0, 'Barcodes/1409 CN.jpg')," +
                "('5401 CN', 'Virgo Lam 1.00MM 5401 CN', '0', '0', 'CN', 0, 'Barcodes/5401 CN.jpg')," +
                "('5809 CN', 'Virgo Lam 1.00MM 5809 CN', '0', '0', 'CN', 0, 'Barcodes/5809 CN.jpg')," +
                "('6316 CN', 'Virgo Lam 1.00MM 6316 CN', '0', '0', 'CN', 0, 'Barcodes/6316 CN.jpg')," +
                "('6319 CN', 'Virgo Lam 1.00MM 6319 CN', '0', '0', 'CN', 0, 'Barcodes/6319 CN.jpg')," +
                "('5400 WL', 'Virgo Lam 1.00MM 5400 WL', '0', '0', 'WL', 0, 'Barcodes/5400 WL.jpg')," +
                "('5401 WL', 'Virgo Lam 1.00MM 5401 WL', '0', '0', 'WL', 0, 'Barcodes/5401 WL.jpg')," +
                "('5403 WL', 'Virgo Lam 1.00MM 5403 WL', '0', '0', 'WL', 0, 'Barcodes/5403 WL.jpg')," +
                "('5410 WL', 'Virgo Lam 1.00MM 5410 WL', '0', '0', 'WL', 0, 'Barcodes/5410 WL.jpg')," +
                "('5415 WL', 'Virgo Lam 1.00MM 5415 WL', '0', '0', 'WL', 0, 'Barcodes/5415 WL.jpg')," +
                "('5418 WL', 'Virgo Lam 1.00MM 5418 WL', '0', '0', 'WL', 0, 'Barcodes/5418 WL.jpg')," +
                "('1409 TG', 'Virgo Lam 1.00MM 1409 TG', '0', '0', 'TG', 0, 'Barcodes/1409 TG.jpg')," +
                "('5416 TG', 'Virgo Lam 1.00MM 5416 TG', '0', '0', 'TG', 0, 'Barcodes/5416 TG.jpg')," +
                "('5809 TG', 'Virgo Lam 1.00MM 5809 TG', '0', '0', 'TG', 0, 'Barcodes/5809 TG.jpg')," +
                "('6604 TG', 'Virgo Lam 1.00MM 6604 TG', '0', '0', 'TG', 0, 'Barcodes/6604 TG.jpg')," +
                "('6605 TG', 'Virgo Lam 1.00MM 6605 TG', '0', '0', 'TG', 0, 'Barcodes/6605 TG.jpg')," +
                "('6609 TG', 'Virgo Lam 1.00MM 6609 TG', '0', '0', 'TG', 0, 'Barcodes/6609 TG.jpg')," +
                "('6610 TG', 'Virgo Lam 1.00MM 6610 TG', '0', '0', 'TG', 0, 'Barcodes/6610 TG.jpg')," +
                "('1409 FA', 'Virgo Lam 1.00MM 1409 FA', '0', '0', 'FA', 0, 'Barcodes/1409 FA.jpg')," +
                "('5181 FA', 'Virgo Lam 1.00MM 5181 FA', '0', '0', 'FA', 0, 'Barcodes/5181 FA.jpg')," +
                "('5185 FA', 'Virgo Lam 1.00MM 5185 FA', '0', '0', 'FA', 0, 'Barcodes/5185 FA.jpg')," +
                "('5260 FA', 'Virgo Lam 1.00MM 5260 FA', '0', '0', 'FA', 0, 'Barcodes/5260 FA.jpg')," +
                "('5804 FA', 'Virgo Lam 1.00MM 5260 FA', '0', '0', 'FA', 0, 'Barcodes/5804 FA.jpg')," +
                "('5805 FA', 'Virgo Lam 1.00MM 5805 FA', '0', '0', 'FA', 0, 'Barcodes/5805 FA.jpg')," +
                "('5904 FA', 'Virgo Lam 1.00MM 5904 FA', '0', '0', 'FA', 0, 'Barcodes/5904 FA.jpg')," +
                "('6325 FA', 'Virgo Lam 1.00MM 6325 FA', '0', '0', 'FA', 0, 'Barcodes/6325 FA.jpg')," +
                "('6312 PP', 'Virgo Lam 1.00MM 6312 PP', '0', '0', 'PP', 0, 'Barcodes/6312 PP.jpg')," +
                "('6313 PP', 'Virgo Lam 1.00MM 6313 PP', '0', '0', 'PP', 0, 'Barcodes/6313 PP.jpg')," +
                "('6314 PP', 'Virgo Lam 1.00MM 6314 PP', '0', '0', 'PP', 0, 'Barcodes/6314 PP.jpg')," +
                "('6315 PP', 'Virgo Lam 1.00MM 6315 PP', '0', '0', 'PP', 0, 'Barcodes/6315 PP.jpg')," +
                "('5809 AA', 'Virgo Lam 1.00MM 5809 AA', '0', '0', 'AA', 0, 'Barcodes/5809 AA.jpg')," +
                "('6609 AA', 'Virgo Lam 1.00MM 6609 AA', '0', '0', 'AA', 0, 'Barcodes/6609 AA.jpg')," +
                "('6610 AA', 'Virgo Lam 1.00MM 6610 AA', '0', '0', 'AA', 0, 'Barcodes/6610 AA.jpg')," +
                "('6912 AA', 'Virgo Lam 1.00MM 6912 AA', '0', '0', 'AA', 0, 'Barcodes/6912 AA.jpg')," +
                "('1075 GC', 'Virgo Lam 1.00MM 1075 GC', '0', '0', 'GC', 0, 'Barcodes/1075 GC.jpg')," +
                "('5383 GC', 'Virgo Lam 1.00MM 5383 GC', '0', '0', 'GC', 0, 'Barcodes/5383 GC.jpg')," +
                "('5388 GC', 'Virgo Lam 1.00MM 5388 GC', '0', '0', 'GC', 0, 'Barcodes/5388 GC.jpg')," +
                "('5389 GC', 'Virgo Lam 1.00MM 5389 GC', '0', '0', 'GC', 0, 'Barcodes/5389 GC.jpg')," +
                "('5391 GC', 'Virgo Lam 1.00MM 5391 GC', '0', '0', 'GC', 0, 'Barcodes/5391 GC.jpg')," +
                "('6609 GC', 'Virgo Lam 1.00MM 6609 GC', '0', '0', 'GC', 0, 'Barcodes/6609 GC.jpg')," +
                "('5277 CC', 'Virgo Lam 1.00MM 5277 CC', '0', '0', 'CC', 0, 'Barcodes/5277 CC.jpg')," +
                "('5278 CC', 'Virgo Lam 1.00MM 5278 CC', '0', '0', 'CC', 0, 'Barcodes/5278 CC.jpg')," +
                "('5395 CC', 'Virgo Lam 1.00MM 5395 CC', '0', '0', 'CC', 0, 'Barcodes/5395 CC.jpg')," +
                "('5400 CC', 'Virgo Lam 1.00MM 5400 CC', '0', '0', 'CC', 0, 'Barcodes/5400 CC.jpg')," +
                "('5401 CC', 'Virgo Lam 1.00MM 5401 CC', '0', '0', 'CC', 0, 'Barcodes/5401 CC.jpg')," +
                "('5809 CC', 'Virgo Lam 1.00MM 5809 CC', '0', '0', 'CC', 0, 'Barcodes/5809 CC.jpg')," +
                "('6609 CC', 'Virgo Lam 1.00MM 6609 CC', '0', '0', 'CC', 0, 'Barcodes/6609 CC.jpg')," +
                "('5403 BW', 'Virgo Lam 1.00MM 5403 BW', '0', '0', 'BW', 0, 'Barcodes/5403 BW.jpg')," +
                "('5410 BW', 'Virgo Lam 1.00MM 5410 BW', '0', '0', 'BW', 0, 'Barcodes/5410 BW.jpg')," +
                "('5415 BW', 'Virgo Lam 1.00MM 5415 BW', '0', '0', 'BW', 0, 'Barcodes/5415 BW.jpg')," +
                "('5809 SA', 'Virgo Lam 1.00MM 5809 SA', '0', '0', 'SA', 0, 'Barcodes/5809 SA.jpg')," +
                "('5881 SA', 'Virgo Lam 1.00MM 5881 SA', '0', '0', 'SA', 0, 'Barcodes/5881 SA.jpg')," +
                "('6609 SA', 'Virgo Lam 1.00MM 6609 SA', '0', '0', 'SA', 0, 'Barcodes/6609 SA.jpg')," +
                "('6610 SA', 'Virgo Lam 1.00MM 6610 SA', '0', '0', 'SA', 0, 'Barcodes/6610 SA.jpg')," +
                "('5383 BC', 'Virgo Lam 1.00MM 5383 BC', '0', '0', 'BC', 0, 'Barcodes/5383 BC.jpg')";
                
                System.err.println("Insert first half : "+stmt.executeUpdate(query));
                
                
                
                
                
                query = "INSERT INTO STOCK (PID, DESIGN, RACK, SUBRACK, TEXTURE, QUANTITY, BARCODE) VALUES" +
                        "('5385 BC', 'Virgo Lam 1.00MM 5385 BC', '0', '0', 'BC', 0, 'Barcodes/5385 BC.jpg'), " +
                        "('5386 BC', 'Virgo Lam 1.00MM 5386 BC', '0', '0', 'BC', 0, 'Barcodes/5386 BC.jpg'), " +
                        "('5388 BC', 'Virgo Lam 1.00MM 5388 BC', '0', '0', 'BC', 0, 'Barcodes/5388 BC.jpg'), " +
                        "('5389 BC', 'Virgo Lam 1.00MM 5389 BC', '0', '0', 'BC', 0, 'Barcodes/5389 BC.jpg'), " +
                        "('5395 BC', 'Virgo Lam 1.00MM 5395 BC', '0', '0', 'BC', 0, 'Barcodes/5395 BC.jpg'), " +
                        "('5410 BC', 'Virgo Lam 1.00MM 5410 BC', '0', '0', 'BC', 0, 'Barcodes/5410 BC.jpg'), " +
                        "('1098 CLVT', 'Virgo Lam 1.00MM 1098 CLVT', '0', '0', 'CLVT', 0, 'Barcodes/1098 CLVT.jpg'), " +
                        "('1409 CLVT', 'Virgo Lam 1.00MM 1409 CLVT', '0', '0', 'CLVT', 0, 'Barcodes/1409 CLVT.jpg'), " +
                        "('5181 CLVT', 'Virgo Lam 1.00MM 5181 CLVT', '0', '0', 'CLVT', 0, 'Barcodes/5181 CLVT.jpg'), " +
                        "('5403 CLVT', 'Virgo Lam 1.00MM 5403 CLVT', '0', '0', 'CLVT', 0, 'Barcodes/5403 CLVT.jpg'), " +
                        "('5908 CLVT', 'Virgo Lam 1.00MM 5908 CLVT', '0', '0', 'CLVT', 0, 'Barcodes/5908 CLVT.jpg'), " +
                        "('5910 CLVT', 'Virgo Lam 1.00MM 5910 CLVT', '0', '0', 'CLVT', 0, 'Barcodes/5910 CLVT.jpg'), " +
                        "('5388 CM', 'Virgo Lam 1.00MM 5388 CM', '0', '0', 'CM', 0, 'Barcodes/5388 CM.jpg'), " +
                        "('5389 CM', 'Virgo Lam 1.00MM 5389 CM', '0', '0', 'CM', 0, 'Barcodes/5389 CM.jpg'), " +
                        "('5391 CM', 'Virgo Lam 1.00MM 5391 CM', '0', '0', 'CM', 0, 'Barcodes/5391 CM.jpg'), " +
                        "('5392 CM', 'Virgo Lam 1.00MM 5392 CM', '0', '0', 'CM', 0, 'Barcodes/5392 CM.jpg'), " +
                        "('5403 CM', 'Virgo Lam 1.00MM 5403 CM', '0', '0', 'CM', 0, 'Barcodes/5403 CM.jpg'), " +
                        "('5415 CM', 'Virgo Lam 1.00MM 5415 CM', '0', '0', 'CM', 0, 'Barcodes/5415 CM.jpg'), " +
                        "('5271 TX', 'Virgo Lam 1.00MM 5271 TX', '0', '0', 'TX', 0, 'Barcodes/5271 TX.jpg'), " +
                        "('5272 TX', 'Virgo Lam 1.00MM 5272 TX', '0', '0', 'TX', 0, 'Barcodes/5272 TX.jpg'), " +
                        "('5800 TX', 'Virgo Lam 1.00MM 5800 TX', '0', '0', 'TX', 0, 'Barcodes/5800 TX.jpg'), " +
                        "('5801 TX', 'Virgo Lam 1.00MM 5801 TX', '0', '0', 'TX', 0, 'Barcodes/5801 TX.jpg'), " +
                        "('5811 TX', 'Virgo Lam 1.00MM 5811 TX', '0', '0', 'TX', 0, 'Barcodes/5811 TX.jpg'), " +
                        "('5940 TX', 'Virgo Lam 1.00MM 5940 TX', '0', '0', 'TX', 0, 'Barcodes/5940 TX.jpg'), " +
                        "('1002 LA', 'Virgo Lam 1.00MM 1002 LA', '0', '0', 'LA', 0, 'Barcodes/1002 LA.jpg'), " +
                        "('1075 LA', 'Virgo Lam 1.00MM 1075 LA', '0', '0', 'LA', 0, 'Barcodes/1075 LA.jpg'), " +
                        "('1098 LA', 'Virgo Lam 1.00MM 1098 LA', '0', '0', 'LA', 0, 'Barcodes/1098 LA.jpg'), " +
                        "('1245 LA', 'Virgo Lam 1.00MM 1245 LA', '0', '0', 'LA', 0, 'Barcodes/1245 LA.jpg'), " +
                        "('1409 LA', 'Virgo Lam 1.00MM 1409 LA', '0', '0', 'LA', 0, 'Barcodes/1409 LA.jpg'), " +
                        "('1923 LA', 'Virgo Lam 1.00MM 1923 LA', '0', '0', 'LA', 0, 'Barcodes/1923 LA.jpg'), " +
                        "('1409 SMT', 'Virgo Lam 1.00MM 1409 SMT', '0', '0', 'SMT', 0, 'Barcodes/1409 SMT.jpg'), " +
                        "('5804 SMT', 'Virgo Lam 1.00MM 5804 SMT', '0', '0', 'SMT', 0, 'Barcodes/5804 SMT.jpg'), " +
                        "('5809 SMT', 'Virgo Lam 1.00MM 5809 SMT', '0', '0', 'SMT', 0, 'Barcodes/5809 SMT.jpg'), " +
                        "('5811 SMT', 'Virgo Lam 1.00MM 5811 SMT', '0', '0', 'SMT', 0, 'Barcodes/5811 SMT.jpg'), " +
                        "('1409 SC', 'Virgo Lam 1.00MM 1409 SC', '0', '0', 'SC', 0, 'Barcodes/1409 SC.jpg'), " +
                        "('5395 SC', 'Virgo Lam 1.00MM 5395 SC', '0', '0', 'SC', 0, 'Barcodes/5395 SC.jpg'), " +
                        "('5410 SC', 'Virgo Lam 1.00MM 5410 SC', '0', '0', 'SC', 0, 'Barcodes/5410 SC.jpg'), " +
                        "('5808 SC', 'Virgo Lam 1.00MM 5808 SC', '0', '0', 'SC', 0, 'Barcodes/5808 SC.jpg'), " +
                        "('5810 SC', 'Virgo Lam 1.00MM 5810 SC', '0', '0', 'SC', 0, 'Barcodes/5810 SC.jpg'), " +
                        "('5881 SC', 'Virgo Lam 1.00MM 5881 SC', '0', '0', 'SC', 0, 'Barcodes/5881 SC.jpg'), " +
                        "('6305 SH', 'Virgo Lam 1.00MM 6305 SH', '0', '0', 'SH', 0, 'Barcodes/6305 SH.jpg'), " +
                        "('6306 SH', 'Virgo Lam 1.00MM 6306 SH', '0', '0', 'SH', 0, 'Barcodes/6306 SH.jpg'), " +
                        "('1409 LR', 'Virgo Lam 1.00MM 1409 LR', '0', '0', 'LR', 0, 'Barcodes/1409 LR.jpg'), " +
                        "('6878 ST', 'Virgo Lam 1.00MM 6878 ST', '0', '0', 'ST', 0, 'Barcodes/6878 ST.jpg'), " +
                        "('6879 ST', 'Virgo Lam 1.00MM 6879 ST', '0', '0', 'ST', 0, 'Barcodes/6879 ST.jpg'), " +
                        "('6252 LR', 'Virgo Lam 1.00MM 6252 LR', '0', '0', 'LR', 0, 'Barcodes/6252 LR.jpg'), " +
                        "('5802 RC', 'Virgo Lam 1.00MM 5802 RC', '0', '0', 'RC', 0, 'Barcodes/5802 RC.jpg'), " +
                        "('5803 RC', 'Virgo Lam 1.00MM 5803 RC', '0', '0', 'RC', 0, 'Barcodes/5803 RC.jpg'), " +
                        "('5810 RC', 'Virgo Lam 1.00MM 5810 RC', '0', '0', 'RC', 0, 'Barcodes/5810 RC.jpg'), " +
                        "('5809 CS', 'Virgo Lam 1.00MM 5809 CS', '0', '0', 'CS', 0, 'Barcodes/5809 CS.jpg'), " +
                        "('6604 CS', 'Virgo Lam 1.00MM 6604 CS', '0', '0', 'CS', 0, 'Barcodes/6604 CS.jpg'), " +
                        "('6605 CS', 'Virgo Lam 1.00MM 6605 CS', '0', '0', 'CS', 0, 'Barcodes/6605 CS.jpg'), " +
                        "('5400 PL', 'Virgo Lam 1.00MM 5400 PL', '0', '0', 'PL', 0, 'Barcodes/5400 PL.jpg'), " +
                        "('5401 PL', 'Virgo Lam 1.00MM 5401 PL', '0', '0', 'PL', 0, 'Barcodes/5401 PL.jpg'), " +
                        "('5403 PL', 'Virgo Lam 1.00MM 5403 PL', '0', '0', 'PL', 0, 'Barcodes/5403 PL.jpg'), " +
                        "('5901 PL', 'Virgo Lam 1.00MM 5P01 PL', '0', '0', 'PL', 0, 'Barcodes/5901 PL.jpg'), " +
                        "('5910 PL', 'Virgo Lam 1.00MM 5910 PL', '0', '0', 'PL', 0, 'Barcodes/5910 PL.jpg'), " +
                        "('1055 STH', 'Virgo Lam 1.00MM 1055 STH', '0', '0', 'STH', 0, 'Barcodes/1055 STH.jpg'), " +
                        "('1409 STH', 'Virgo Lam 1.00MM 1409 STH', '0', '0', 'STH', 0, 'Barcodes/1409 STH.jpg'), " +
                        "('6302 STH', 'Virgo Lam 1.00MM 6302 STH', '0', '0', 'STH', 0, 'Barcodes/6302 STH.jpg'), " +
                        "('6303 STH', 'Virgo Lam 1.00MM 6303 STH', '0', '0', 'STH', 0, 'Barcodes/6303 STH.jpg'), " +
                        "('6316 STH', 'Virgo Lam 1.00MM 6316 STH', '0', '0', 'STH', 0, 'Barcodes/6316 STH.jpg'), " +
                        "('6317 STH', 'Virgo Lam 1.00MM 6317 STH', '0', '0', 'STH', 0, 'Barcodes/6317 STH.jpg'), " +
                        "('1409 SL', 'Virgo Lam 1.00MM 1409 SL', '0', '0', 'SL', 0, 'Barcodes/1409 SL.jpg'), " +
                        "('5278 SL', 'Virgo Lam 1.00MM 5278 SL', '0', '0', 'SL', 0, 'Barcodes/5278 SL.jpg'), " +
                        "('5391 SL', 'Virgo Lam 1.00MM 5391 SL', '0', '0', 'SL', 0, 'Barcodes/5391 SL.jpg'), " +
                        "('5401 SL', 'Virgo Lam 1.00MM 5401 SL', '0', '0', 'SL', 0, 'Barcodes/5401 SL.jpg'), " +
                        "('6609 SL', 'Virgo Lam 1.00MM 6609 SL', '0', '0', 'SL', 0, 'Barcodes/6609 SL.jpg'), " +
                        "('6610 SL', 'Virgo Lam 1.00MM 6610 SL', '0', '0', 'SL', 0, 'Barcodes/6610 SL.jpg'), " +
                        "('1001 SF', 'Virgo Lam 1.00MM 1001 SF', '0', '0', 'SF', 0, 'Barcodes/1001 SF.jpg'), " +
                        "('1002 SF', 'Virgo Lam 1.00MM 1002 SF', '0', '0', 'SF', 0, 'Barcodes/1002 SF.jpg'), " +
                        "('1005 SF', 'Virgo Lam 1.00MM 1005 SF', '0', '0', 'SF', 0, 'Barcodes/1005 SF.jpg'), " +
                        "('1006 SF', 'Virgo Lam 1.00MM 1006 SF', '0', '0', 'SF', 0, 'Barcodes/1006 SF.jpg'), " +
                        "('1006 GL', 'Virgo Lam 1.00MM 1006 GL', '0', '0', 'GL', 0, 'Barcodes/1006 GL.jpg'), " +
                        "('1015 SF', 'Virgo Lam 1.00MM 1015 SF', '0', '0', 'SF', 0, 'Barcodes/1015 SF.jpg'), " +
                        "('1018 SF', 'Virgo Lam 1.00MM 1018 SF', '0', '0', 'SF', 0, 'Barcodes/1018 SF.jpg'), " +
                        "('1025 SF', 'Virgo Lam 1.00MM 1025 SF', '0', '0', 'SF', 0, 'Barcodes/1025 SF.jpg'), " +
                        "('1027 SF', 'Virgo Lam 1.00MM 1027 SF', '0', '0', 'SF', 0, 'Barcodes/1027 SF.jpg'), " +
                        "('1028 SF', 'Virgo Lam 1.00MM 1028 SF', '0', '0', 'SF', 0, 'Barcodes/1028 SF.jpg'), " +
                        "('1035 SF', 'Virgo Lam 1.00MM 1035 SF', '0', '0', 'SF', 0, 'Barcodes/1035 SF.jpg'), " +
                        "('1037 SF', 'Virgo Lam 1.00MM 1037 SF', '0', '0', 'SF', 0, 'Barcodes/1037 SF.jpg'), " +
                        "('1038 SF', 'Virgo Lam 1.00MM 1038 SF', '0', '0', 'SF', 0, 'Barcodes/1038 SF.jpg'), " +
                        "('1045 SF', 'Virgo Lam 1.00MM 1045 SF', '0', '0', 'SF', 0, 'Barcodes/1045 SF.jpg'), " +
                        "('1047 SF', 'Virgo Lam 1.00MM 1047 SF', '0', '0', 'SF', 0, 'Barcodes/1047 SF.jpg'), " +
                        "('1048 SF', 'Virgo Lam 1.00MM 1048 SF', '0', '0', 'SF', 0, 'Barcodes/1048 SF.jpg'), " +
                        "('1055 SF', 'Virgo Lam 1.00MM 1055 SF', '0', '0', 'SF', 0, 'Barcodes/1055 SF.jpg'), " +
                        "('1057 SF', 'Virgo Lam 1.00MM 1057 SF', '0', '0', 'SF', 0, 'Barcodes/1057 SF.jpg'), " +
                        "('1058 SF', 'Virgo Lam 1.00MM 1058 SF', '0', '0', 'SF', 0, 'Barcodes/1058 SF.jpg'), " +
                        "('1063 SF', 'Virgo Lam 1.00MM 1063 SF', '0', '0', 'SF', 0, 'Barcodes/1063 SF.jpg'), " +
                        "('1067 SF', 'Virgo Lam 1.00MM 1067 SF', '0', '0', 'SF', 0, 'Barcodes/1067 SF.jpg'), " +
                        "('1068 SF', 'Virgo Lam 1.00MM 1068 SF', '0', '0', 'SF', 0, 'Barcodes/1068 SF.jpg'), " +
                        "('1073 SF', 'Virgo Lam 1.00MM 1073 SF', '0', '0', 'SF', 0, 'Barcodes/1073 SF.jpg'), " +
                        "('1075 SF', 'Virgo Lam 1.00MM 1075 SF', '0', '0', 'SF', 0, 'Barcodes/1075 SF.jpg'), " +
                        "('1088 SF', 'Virgo Lam 1.00MM 1088 SF', '0', '0', 'SF', 0, 'Barcodes/1088 SF.jpg'), " +
                        "('1098 SF', 'Virgo Lam 1.00MM 1098 SF', '0', '0', 'SF', 0, 'Barcodes/1098 SF.jpg'), " +
                        "('1101 SF', 'Virgo Lam 1.00MM 1101 SF', '0', '0', 'SF', 0, 'Barcodes/1101 SF.jpg'), " +
                        "('1101 MF', 'Virgo Lam 1.00MM 1101 MF', '0', '0', 'MF', 0, 'Barcodes/1101 MF.jpg'), " +
                        "('1209 SF', 'Virgo Lam 1.00MM 1209 SF', '0', '0', 'SF', 0, 'Barcodes/1209 SF.jpg'), " +
                        "('1209 MF', 'Virgo Lam 1.00MM 1209 MF', '0', '0', 'MF', 0, 'Barcodes/1209 MF.jpg'), " +
                        "('1245 SF', 'Virgo Lam 1.00MM 1245 SF', '0', '0', 'MF', 0, 'Barcodes/1245 SF.jpg'), " +
                        "('1303 SF', 'Virgo Lam 1.00MM 1303 SF', '0', '0', 'SF', 0, 'Barcodes/1303 SF.jpg'), " +
                        "('1303 MF', 'Virgo Lam 1.00MM 1303 MF', '0', '0', 'MF', 0, 'Barcodes/1303 MF.jpg'), " +
                        "('1309 SF', 'Virgo Lam 1.00MM 1309 SF', '0', '0', 'SF', 0, 'Barcodes/1309 SF.jpg'), " +
                        "('1409 SF', 'Virgo Lam 1.00MM 1409 SF', '0', '0', 'SF', 0, 'Barcodes/1409 SF.jpg'), " +
                        "('1409 MF', 'Virgo Lam 1.00MM 1409 MF', '0', '0', 'MF', 0, 'Barcodes/1409 MF.jpg'), " +
                        "('1504 SF', 'Virgo Lam 1.00MM 1504 SF', '0', '0', 'SF', 0, 'Barcodes/1504 SF.jpg'), " +
                        "('1505 SF', 'Virgo Lam 1.00MM 1505 SF', '0', '0', 'SF', 0, 'Barcodes/1505 SF.jpg'), " +
                        "('1507 SF', 'Virgo Lam 1.00MM 1507 SF', '0', '0', 'SF', 0, 'Barcodes/1507 SF.jpg'), " +
                        "('1509 SF', 'Virgo Lam 1.00MM 1509 SF', '0', '0', 'SF', 0, 'Barcodes/1509 SF.jpg'), " +
                        "('1515 SF', 'Virgo Lam 1.00MM 1515 SF', '0', '0', 'SF', 0, 'Barcodes/1515 SF.jpg'), " +
                        "('1517 SF', 'Virgo Lam 1.00MM 1517 SF', '0', '0', 'SF', 0, 'Barcodes/1517 SF.jpg'), " +
                        "('1521 SF', 'Virgo Lam 1.00MM 1521 SF', '0', '0', 'SF', 0, 'Barcodes/1521 SF.jpg'), " +
                        "('1522 SF', 'Virgo Lam 1.00MM 1522 SF', '0', '0', 'SF', 0, 'Barcodes/1522 SF.jpg'), " +
                        "('1523 SF', 'Virgo Lam 1.00MM 1523 SF', '0', '0', 'SF', 0, 'Barcodes/1523 SF.jpg'), " +
                        "('1524 SF', 'Virgo Lam 1.00MM 1524 SF', '0', '0', 'SF', 0, 'Barcodes/1524 SF.jpg'), " +
                        "('1525 SF', 'Virgo Lam 1.00MM 1525 SF', '0', '0', 'SF', 0, 'Barcodes/1525 SF.jpg'), " +
                        "('1527 SF', 'Virgo Lam 1.00MM 1527 SF', '0', '0', 'SF', 0, 'Barcodes/1527 SF.jpg'), " +
                        "('1921 SF', 'Virgo Lam 1.00MM 1921 SF', '0', '0', 'SF', 0, 'Barcodes/1921 SF.jpg'), " +
                        "('1922 SF', 'Virgo Lam 1.00MM 1922 SF', '0', '0', 'SF', 0, 'Barcodes/1922 SF.jpg'), " +
                        "('1923 SF', 'Virgo Lam 1.00MM 1923 SF', '0', '0', 'SF', 0, 'Barcodes/1923 SF.jpg'), " +
                        "('1929 SF', 'Virgo Lam 1.00MM 1929 SF', '0', '0', 'SF', 0, 'Barcodes/1929 SF.jpg'), " +
                        "('1931 SF', 'Virgo Lam 1.00MM 1931 SF', '0', '0', 'SF', 0, 'Barcodes/1931 SF.jpg'), " +
                        "('5001 SF', 'Virgo Lam 1.00MM 5001 SF', '0', '0', 'SF', 0, 'Barcodes/5001 SF.jpg'), " +
                        "('5002 SF', 'Virgo Lam 1.00MM 5002 SF', '0', '0', 'SF', 0, 'Barcodes/5002 SF.jpg'), " +
                        "('5008 SF', 'Virgo Lam 1.00MM 5008 SF', '0', '0', 'SF', 0, 'Barcodes/5008 SF.jpg'), " +
                        "('5029 SF', 'Virgo Lam 1.00MM 5029 SF', '0', '0', 'SF', 0, 'Barcodes/5029 SF.jpg'), " +
                        "('5034 SF', 'Virgo Lam 1.00MM 5034 SF', '0', '0', 'SF', 0, 'Barcodes/5034 SF.jpg'), " +
                        "('5038 SF', 'Virgo Lam 1.00MM 5038 SF', '0', '0', 'SF', 0, 'Barcodes/5038 SF.jpg'), " +
                        "('5040 SF', 'Virgo Lam 1.00MM 5040 SF', '0', '0', 'SF', 0, 'Barcodes/5040 SF.jpg'), " +
                        "('5089 SF', 'Virgo Lam 1.00MM 5089 SF', '0', '0', 'SF', 0, 'Barcodes/5089 SF.jpg'), " +
                        "('5116 SF', 'Virgo Lam 1.00MM 5116 SF', '0', '0', 'SF', 0, 'Barcodes/5116 SF.jpg'), " +
                        "('5121 SF', 'Virgo Lam 1.00MM 5121 SF', '0', '0', 'SF', 0, 'Barcodes/5121 SF.jpg'), " +
                        "('5122 SF', 'Virgo Lam 1.00MM 5122 SF', '0', '0', 'SF', 0, 'Barcodes/5122 SF.jpg'), " +
                        "('5176 SF', 'Virgo Lam 1.00MM 5176 SF', '0', '0', 'SF', 0, 'Barcodes/5176 SF.jpg'), " +
                        "('5181 SF', 'Virgo Lam 1.00MM 5181 SF', '0', '0', 'SF', 0, 'Barcodes/5181 SF.jpg'), " +
                        "('5185 SF', 'Virgo Lam 1.00MM 5185 SF', '0', '0', 'SF', 0, 'Barcodes/5185 SF.jpg'), " +
                        "('5251 SF', 'Virgo Lam 1.00MM 5251 SF', '0', '0', 'SF', 0, 'Barcodes/5251 SF.jpg'), " +
                        "('5271 SF', 'Virgo Lam 1.00MM 5271 SF', '0', '0', 'SF', 0, 'Barcodes/5271 SF.jpg'), " +
                        "('5272 SF', 'Virgo Lam 1.00MM 5272 SF', '0', '0', 'SF', 0, 'Barcodes/5272 SF.jpg'), " +
                        "('5277 SF', 'Virgo Lam 1.00MM 5277 SF', '0', '0', 'SF', 0, 'Barcodes/5277 SF.jpg'), " +
                        "('5278 SF', 'Virgo Lam 1.00MM 5278 SF', '0', '0', 'SF', 0, 'Barcodes/5278 SF.jpg'), " +
                        "('5328 SF', 'Virgo Lam 1.00MM 5328 SF', '0', '0', 'SF', 0, 'Barcodes/5328 SF.jpg'), " +
                        "('5329 SF', 'Virgo Lam 1.00MM 5329 SF', '0', '0', 'SF', 0, 'Barcodes/5329 SF.jpg'), " +
                        "('5385 SF', 'Virgo Lam 1.00MM 5385 SF', '0', '0', 'SF', 0, 'Barcodes/5385 SF.jpg'), " +
                        "('5386 SF', 'Virgo Lam 1.00MM 5386 SF', '0', '0', 'SF', 0, 'Barcodes/5386 SF.jpg'), " +
                        "('5388 SF', 'Virgo Lam 1.00MM 5388 SF', '0', '0', 'SF', 0, 'Barcodes/5388 SF.jpg'), " +
                        "('5389 SF', 'Virgo Lam 1.00MM 5389 SF', '0', '0', 'SF', 0, 'Barcodes/5389 SF.jpg'), " +
                        "('5391 SF', 'Virgo Lam 1.00MM 5391 SF', '0', '0', 'SF', 0, 'Barcodes/5391 SF.jpg'), " +
                        "('5395 SF', 'Virgo Lam 1.00MM 5395 SF', '0', '0', 'SF', 0, 'Barcodes/5395 SF.jpg'), " +
                        "('5400 SF', 'Virgo Lam 1.00MM 5400 SF', '0', '0', 'SF', 0, 'Barcodes/5400 SF.jpg'), " +
                        "('5401 SF', 'Virgo Lam 1.00MM 5401 SF', '0', '0', 'SF', 0, 'Barcodes/5401 SF.jpg'), " +
                        "('5403 SF', 'Virgo Lam 1.00MM 5403 SF', '0', '0', 'SF', 0, 'Barcodes/5403 SF.jpg'), " +
                        "('5410 SF', 'Virgo Lam 1.00MM 5410 SF', '0', '0', 'SF', 0, 'Barcodes/5410 SF.jpg'), " +
                        "('5415 SF', 'Virgo Lam 1.00MM 5415 SF', '0', '0', 'SF', 0, 'Barcodes/5415 SF.jpg'), " +
                        "('5416 SF', 'Virgo Lam 1.00MM 5416 SF', '0', '0', 'SF', 0, 'Barcodes/5416 SF.jpg'), " +
                        "('5504 SF', 'Virgo Lam 1.00MM 5504 SF', '0', '0', 'SF', 0, 'Barcodes/5504 SF.jpg'), " +
                        "('5507 SF', 'Virgo Lam 1.00MM 5507 SF', '0', '0', 'SF', 0, 'Barcodes/5507 SF.jpg'), " +
                        "('5800 SF', 'Virgo Lam 1.00MM 5800 SF', '0', '0', 'SF', 0, 'Barcodes/5800 SF.jpg'), " +
                        "('5801 SF', 'Virgo Lam 1.00MM 5801 SF', '0', '0', 'SF', 0, 'Barcodes/5801 SF.jpg'), " +
                        "('5802 SF', 'Virgo Lam 1.00MM 5802 SF', '0', '0', 'SF', 0, 'Barcodes/5802 SF.jpg'), " +
                        "('5803 SF', 'Virgo Lam 1.00MM 5803 SF', '0', '0', 'SF', 0, 'Barcodes/5803 SF.jpg'), " +
                        "('5804 SF', 'Virgo Lam 1.00MM 5804 SF', '0', '0', 'SF', 0, 'Barcodes/5804 SF.jpg'), " +
                        "('5805 SF', 'Virgo Lam 1.00MM 5805 SF', '0', '0', 'SF', 0, 'Barcodes/5805 SF.jpg'), " +
                        "('5808 SF', 'Virgo Lam 1.00MM 5808 SF', '0', '0', 'SF', 0, 'Barcodes/5808 SF.jpg'), " +
                        "('5809 SF', 'Virgo Lam 1.00MM 5809 SF', '0', '0', 'SF', 0, 'Barcodes/5809 SF.jpg'), " +
                        "('5810 SF', 'Virgo Lam 1.00MM 5810 SF', '0', '0', 'SF', 0, 'Barcodes/5810 SF.jpg'), " +
                        "('5811 SF', 'Virgo Lam 1.00MM 5811 SF', '0', '0', 'SF', 0, 'Barcodes/5811 SF.jpg'), " +
                        "('5831 SF', 'Virgo Lam 1.00MM 5831 SF', '0', '0', 'SF', 0, 'Barcodes/5831 SF.jpg'), " +
                        "('5832 SF', 'Virgo Lam 1.00MM 5832 SF', '0', '0', 'SF', 0, 'Barcodes/5832 SF.jpg'), " +
                        "('5833 SF', 'Virgo Lam 1.00MM 5833 SF', '0', '0', 'SF', 0, 'Barcodes/5833 SF.jpg'), " +
                        "('5881 SF', 'Virgo Lam 1.00MM 5881 SF', '0', '0', 'SF', 0, 'Barcodes/5881 SF.jpg'), " +
                        "('5901 SF', 'Virgo Lam 1.00MM 5901 SF', '0', '0', 'SF', 0, 'Barcodes/5901 SF.jpg'), " +
                        "('5904 SF', 'Virgo Lam 1.00MM 5904 SF', '0', '0', 'SF', 0, 'Barcodes/5904 SF.jpg'), " +
                        "('5908 SF', 'Virgo Lam 1.00MM 5908 SF', '0', '0', 'SF', 0, 'Barcodes/5908 SF.jpg'), " +
                        "('5910 SF', 'Virgo Lam 1.00MM 5910 SF', '0', '0', 'SF', 0, 'Barcodes/5910 SF.jpg'), " +
                        "('5940 SF', 'Virgo Lam 1.00MM 5940 SF', '0', '0', 'SF', 0, 'Barcodes/5940 SF.jpg'), " +
                        "('6211 SF', 'Virgo Lam 1.00MM 6211 SF', '0', '0', 'SF', 0, 'Barcodes/6211 SF.jpg'), " +
                        "('6252 SF', 'Virgo Lam 1.00MM 6252 SF', '0', '0', 'SF', 0, 'Barcodes/6252 SF.jpg'), " +
                        "('6302 SF', 'Virgo Lam 1.00MM 6302 SF', '0', '0', 'SF', 0, 'Barcodes/6302 SF.jpg'), " +
                        "('6303 SF', 'Virgo Lam 1.00MM 6303 SF', '0', '0', 'SF', 0, 'Barcodes/6303 SF.jpg'), " +
                        "('6305 SF', 'Virgo Lam 1.00MM 6305 SF', '0', '0', 'SF', 0, 'Barcodes/6305 SF.jpg'), " +
                        "('6306 SF', 'Virgo Lam 1.00MM 6306 SF', '0', '0', 'SF', 0, 'Barcodes/6306 SF.jpg'), " +
                        "('6312 SF', 'Virgo Lam 1.00MM 6312 SF', '0', '0', 'SF', 0, 'Barcodes/6312 SF.jpg'), " +
                        "('6313 SF', 'Virgo Lam 1.00MM 6313 SF', '0', '0', 'SF', 0, 'Barcodes/6313 SF.jpg'), " +
                        "('6314 SF', 'Virgo Lam 1.00MM 6314 SF', '0', '0', 'SF', 0, 'Barcodes/6314 SF.jpg'), " +
                        "('6315 SF', 'Virgo Lam 1.00MM 6315 SF', '0', '0', 'SF', 0, 'Barcodes/6315 SF.jpg'), " +
                        "('6316 SF', 'Virgo Lam 1.00MM 6316 SF', '0', '0', 'SF', 0, 'Barcodes/6316 SF.jpg'), " +
                        "('6317 SF', 'Virgo Lam 1.00MM 6317 SF', '0', '0', 'SF', 0, 'Barcodes/6317 SF.jpg'), " +
                        "('6318 SF', 'Virgo Lam 1.00MM 6318 SF', '0', '0', 'SF', 0, 'Barcodes/6318 SF.jpg'), " +
                        "('6319 SF', 'Virgo Lam 1.00MM 6319 SF', '0', '0', 'SF', 0, 'Barcodes/6319 SF.jpg'), " +
                        "('6320 SF', 'Virgo Lam 1.00MM 6320 SF', '0', '0', 'SF', 0, 'Barcodes/6320 SF.jpg'), " +
                        "('6321 SF', 'Virgo Lam 1.00MM 6321 SF', '0', '0', 'SF', 0, 'Barcodes/6321 SF.jpg'), " +
                        "('6322 SF', 'Virgo Lam 1.00MM 6322 SF', '0', '0', 'SF', 0, 'Barcodes/6322 SF.jpg'), " +
                        "('6324 SF', 'Virgo Lam 1.00MM 6324 SF', '0', '0', 'SF', 0, 'Barcodes/6324 SF.jpg'), " +
                        "('6325 SF', 'Virgo Lam 1.00MM 6325 SF', '0', '0', 'SF', 0, 'Barcodes/6325 SF.jpg'), " +
                        "('6329 SF', 'Virgo Lam 1.00MM 6329 SF', '0', '0', 'SF', 0, 'Barcodes/6329 SF.jpg'), " +
                        "('6330 SF', 'Virgo Lam 1.00MM 6330 SF', '0', '0', 'SF', 0, 'Barcodes/6330 SF.jpg'), " +
                        "('6601 SF', 'Virgo Lam 1.00MM 6601 SF', '0', '0', 'SF', 0, 'Barcodes/6601 SF.jpg'), " +
                        "('6602 SF', 'Virgo Lam 1.00MM 6602 SF', '0', '0', 'SF', 0, 'Barcodes/6602 SF.jpg'), " +
                        "('6603 SF', 'Virgo Lam 1.00MM 6603 SF', '0', '0', 'SF', 0, 'Barcodes/6603 SF.jpg'), " +
                        "('6604 SF', 'Virgo Lam 1.00MM 6604 SF', '0', '0', 'SF', 0, 'Barcodes/6604 SF.jpg'), " +
                        "('6605 SF', 'Virgo Lam 1.00MM 6605 SF', '0', '0', 'SF', 0, 'Barcodes/6605 SF.jpg'), " +
                        "('6609 SF', 'Virgo Lam 1.00MM 6609 SF', '0', '0', 'SF', 0, 'Barcodes/6609 SF.jpg'), " +
                        "('6610 SF', 'Virgo Lam 1.00MM 6610 SF', '0', '0', 'SF', 0, 'Barcodes/6610 SF.jpg'), " +
                        "('6706 SF', 'Virgo Lam 1.00MM 6706 SF', '0', '0', 'SF', 0, 'Barcodes/6706 SF.jpg'), " +
                        "('6708 SF', 'Virgo Lam 1.00MM 6708 SF', '0', '0', 'SF', 0, 'Barcodes/6708 SF.jpg'), " +
                        "('6709 SF', 'Virgo Lam 1.00MM 6709 SF', '0', '0', 'SF', 0, 'Barcodes/6709 SF.jpg'), " +
                        "('6731 SF', 'Virgo Lam 1.00MM 6731 SF', '0', '0', 'SF', 0, 'Barcodes/6731 SF.jpg'), " +
                        "('6732 SF', 'Virgo Lam 1.00MM 6732 SF', '0', '0', 'SF', 0, 'Barcodes/6732 SF.jpg'), " +
                        "('6758 SF', 'Virgo Lam 1.00MM 6758 SF', '0', '0', 'SF', 0, 'Barcodes/6758 SF.jpg'), " +
                        "('6878 SF', 'Virgo Lam 1.00MM 6878 SF', '0', '0', 'SF', 0, 'Barcodes/6878 SF.jpg'), " +
                        "('6879 SF', 'Virgo Lam 1.00MM 6879 SF', '0', '0', 'SF', 0, 'Barcodes/6879 SF.jpg'), " +
                        "('6905 SF', 'Virgo Lam 1.00MM 6905 SF', '0', '0', 'SF', 0, 'Barcodes/6905 SF.jpg'), " +
                        "('6906 SF', 'Virgo Lam 1.00MM 6906 SF', '0', '0', 'SF', 0, 'Barcodes/6906 SF.jpg'), " +
                        "('6907 SF', 'Virgo Lam 1.00MM 6907 SF', '0', '0', 'SF', 0, 'Barcodes/6907 SF.jpg'), " +
                        "('6908 SF', 'Virgo Lam 1.00MM 6908 SF', '0', '0', 'SF', 0, 'Barcodes/6908 SF.jpg'), " +
                        "('6909 SF', 'Virgo Lam 1.00MM 6909 SF', '0', '0', 'SF', 0, 'Barcodes/6909 SF.jpg'), " +
                        "('6910 SF', 'Virgo Lam 1.00MM 6910 SF', '0', '0', 'SF', 0, 'Barcodes/6910 SF.jpg'), " +
                        "('6915 SF', 'Virgo Lam 1.00MM 6915 SF', '0', '0', 'SF', 0, 'Barcodes/6915 SF.jpg'), " +
                        "('6916 SF', 'Virgo Lam 1.00MM 6916 SF', '0', '0', 'SF', 0, 'Barcodes/6916 SF.jpg'), " +
                        "('6917 SF', 'Virgo Lam 1.00MM 6917 SF', '0', '0', 'SF', 0, 'Barcodes/6917 SF.jpg'), " +
                        "('6918 SF', 'Virgo Lam 1.00MM 6918 SF', '0', '0', 'SF', 0, 'Barcodes/6918 SF.jpg'), " +
                        "('6919 SF', 'Virgo Lam 1.00MM 6919 SF', '0', '0', 'SF', 0, 'Barcodes/6919 SF.jpg'), " +
                        "('6920 SF', 'Virgo Lam 1.00MM 6920 SF', '0', '0', 'SF', 0, 'Barcodes/6920 SF.jpg'), " +
                        "('9000 MF', 'Virgo Lam 1.00MM 9000 MF', '0', '0', 'MF', 0, 'Barcodes/9000 MF.jpg'), " +
                        "('9001 MF', 'Virgo Lam 1.00MM 9001 MF', '0', '0', 'MF', 0, 'Barcodes/9001 MF.jpg'), " +
                        "('VTD 101', 'Virgo Doorskin VTD 101', '11', '4', 'VTD', 2, 'Barcodes/VTD 101.jpg'), " +
                        "('VTD 102', 'Virgo Doorskin VTD 102', '11', '1', 'VTD', 12, 'Barcodes/VTD 102.jpg'), " +
                        "('VTD 103', 'Virgo Doorskin VTD 103', '12', '4', 'VTD', 8, 'Barcodes/VTD 103.jpg'), " +
                        "('VTD 104', 'Virgo Doorskin VTD 104', '11', '3', 'VTD', 2, 'Barcodes/VTD 104.jpg'), " +
                        "('VTD 105', 'Virgo Doorskin VTD 105', '11', '4', 'VTD', 4, 'Barcodes/VTD 105.jpg'), " +
                        "('VTD 106', 'Virgo Doorskin VTD 106', '11', '3', 'VTD', 10, 'Barcodes/VTD 106.jpg'), " +
                        "('VTD 107', 'Virgo Doorskin VTD 107', '11', '1', 'VTD', 6, 'Barcodes/VTD 107.jpg'), " +
                        "('VTD 108', 'Virgo Doorskin VTD 108', '12', '6', 'VTD', 9, 'Barcodes/VTD 108.jpg'), " +
                        "('VTD 109', 'Virgo Doorskin VTD 109', '12', '1', 'VTD', 6, 'Barcodes/VTD 109.jpg'), " +
                        "('VTD 110', 'Virgo Doorskin VTD 110', '11', '4', 'VTD', 7, 'Barcodes/VTD 110.jpg'), " +
                        "('VTD 111', 'Virgo Doorskin VTD 111', '11', '3', 'VTD', 6, 'Barcodes/VTD 111.jpg'), " +
                        "('VTD 112', 'Virgo Doorskin VTD 112', '12', '6', 'VTD', 12, 'Barcodes/VTD 112.jpg'), " +
                        "('VTD 113', 'Virgo Doorskin VTD 113', '11', '5', 'VTD', 6, 'Barcodes/VTD 113.jpg'), " +
                        "('VTD 114', 'Virgo Doorskin VTD 114', '12', '1', 'VTD', 15, 'Barcodes/VTD 114.jpg'), " +
                        "('VTD 115', 'Virgo Doorskin VTD 115', '11', '2', 'VTD', 4, 'Barcodes/VTD 115.jpg'), " +
                        "('VTD 116', 'Virgo Doorskin VTD 116', '12', '1', 'VTD', 11, 'Barcodes/VTD 116.jpg'), " +
                        "('VMC 151', 'Virgo Doorskin VMC 151', '12', '1', 'VMC', 10, 'Barcodes/VMC 151.jpg'), " +
                        "('VMC 152', 'Virgo Doorskin VMC 152', '12', '5', 'VMC', 1, 'Barcodes/VMC 152.jpg'), " +
                        "('VMC 153', 'Virgo Doorskin VMC 153', '12', '5', 'VMC', 10, 'Barcodes/VMC 153.jpg'), " +
                        "('VMC 154', 'Virgo Doorskin VMC 154', '11', '4', 'VMC', 7, 'Barcodes/VMC 154.jpg'), " +
                        "('DG 155', 'Virgo Doorskin DG 155', '12', '2', 'DG', 13, 'Barcodes/DG 155.jpg'), " +
                        "('DG 156', 'Virgo Doorskin DG 156', '11', '1', 'DG', 9, 'Barcodes/DG 156.jpg'), " +
                        "('DG 157', 'Virgo Doorskin DG 157', '12', '5', 'DG', 8, 'Barcodes/DG 157.jpg'), " +
                        "('DG 158', 'Virgo Doorskin DG 158', '11', '2', 'DG', 2, 'Barcodes/DG 158.jpg'), " +
                        "('DG 159', 'Virgo Doorskin DG 159', '12', '1', 'DG', 6, 'Barcodes/DG 159.jpg'), " +
                        "('DG 160', 'Virgo Doorskin DG 160', '11', '5', 'DG', 3, 'Barcodes/DG 160.jpg'), " +
                        "('VCD 224', 'Virgo Doorskin VCD 224', '11', '2', 'VCD', 5, 'Barcodes/VCD 224.jpg'), " +
                        "('VCD 225', 'Virgo Doorskin VCD 225', '11', '3', 'VCD', 13, 'Barcodes/VCD 225.jpg'), " +
                        "('VCD 226', 'Virgo Doorskin VCD 226', '11', '5', 'VCD', 8, 'Barcodes/VCD 226.jpg'), " +
                        "('VCD 227', 'Virgo Doorskin VCD 227', '12', '3', 'VCD', 11, 'Barcodes/VCD 227.jpg'), " +
                        "('VCD 201', 'Virgo Doorskin VCD 201', '12', '6', 'VCD', 2, 'Barcodes/VCD 201.jpg'), " +
                        "('VCD 202', 'Virgo Doorskin VCD 202', '12', '2', 'VCD', 6, 'Barcodes/VCD 202.jpg'), " +
                        "('VCD 203', 'Virgo Doorskin VCD 203', '11', '1', 'VCD', 13, 'Barcodes/VCD 203.jpg'), " +
                        "('VCD 204', 'Virgo Doorskin VCD 204', '12', '3', 'VCD', 5, 'Barcodes/VCD 204.jpg'), " +
                        "('VCD 205', 'Virgo Doorskin VCD 205', '12', '3', 'VCD', 10, 'Barcodes/VCD 205.jpg'), " +
                        "('VCD 206', 'Virgo Doorskin VCD 206', '12', '3', 'VCD', 8, 'Barcodes/VCD 206.jpg'), " +
                        "('VCD 207', 'Virgo Doorskin VCD 207', '12', '5', 'VCD', 8, 'Barcodes/VCD 207.jpg'), " +
                        "('VCD 208', 'Virgo Doorskin VCD 208', '11', '2', 'VCD', 9, 'Barcodes/VCD 208.jpg'), " +
                        "('VCD 209', 'Virgo Doorskin VCD 209', '11', '4', 'vcd', 9, 'Barcodes/VCD 209.jpg'), " +
                        "('VCD 210', 'Virgo Doorskin VCD 210', '12', '2', 'VCD', 6, 'Barcodes/VCD 210.jpg'), " +
                        "('VCD 211', 'Virgo Doorskin VCD 211', '12', '3', 'VCD', 3, 'Barcodes/VCD 211.jpg'), " +
                        "('VCD 212', 'Virgo Doorskin VCD 212', '12', '1', 'VCD', 9, 'Barcodes/VCD 212.jpg'), " +
                        "('VCD 213', 'Virgo Doorskin VCD 213', '11', '2', 'VCD', 6, 'Barcodes/VCD 213.jpg'), " +
                        "('VCD 214', 'Virgo Doorskin VCD 214', '12', '2', 'VCD', 8, 'Barcodes/VCD 214.jpg'), " +
                        "('VCD 215', 'Virgo Doorskin VCD 215', '11', '5', 'VCD', 10, 'Barcodes/VCD 215.jpg'), " +
                        "('VCD 216', 'Virgo Doorskin VCD 216', '12', '1', 'VCD', 8, 'Barcodes/VCD 216.jpg'), " +
                        "('VCD 217', 'Virgo Doorskin VCD 217', '11', '1', 'VCD', 6, 'Barcodes/VCD 217.jpg'), " +
                        "('VCD 218', 'Virgo Doorskin VCD 218', '11', '3', 'VCD', 10, 'Barcodes/VCD 218.jpg'), " +
                        "('VCD 219', 'Virgo Doorskin VCD 218', '11', '2', 'VCD', 10, 'Barcodes/VCD 219.jpg'), " +
                        "('VCD 220', 'Virgo Doorskin VCD 220', '12', '1', 'VCD', 5, 'Barcodes/VCD 220.jpg'), " +
                        "('VCD 221', 'Virgo Doorskin VCD 221', '11', '5', 'VCD', 7, 'Barcodes/VCD 221.jpg'), " +
                        "('VCD 222', 'Virgo Doorskin VCD 222', '12', '4', 'VCD', 8, 'Barcodes/VCD 222.jpg'), " +
                        "('VCD 223', 'Virgo Doorskin VCD 223', '12', '1', 'VCD', 11, 'Barcodes/VCD 223.jpg'), " +
                        "('VPD 301', 'Virgo Doorskin VPD 301', '12', '3', 'VPD', 8, 'Barcodes/VPD 301.jpg'), " +
                        "('VPD 302', 'Virgo Doorskin VPD 302', '11', '4', 'VPD', 12, 'Barcodes/VPD 302.jpg'), " +
                        "('VPD 303', 'Virgo Doorskin VPD 303', '12', '4', 'VPD', 2, 'Barcodes/VPD 303.jpg'), " +
                        "('VPD 304', 'Virgo Doorskin VPD 304', '12', '5', 'VPD', 5, 'Barcodes/VPD 304.jpg'), " +
                        "('VPD 305', 'Virgo Doorskin VPD 305', '12', '3', 'VPD', 6, 'Barcodes/VPD 305.jpg'), " +
                        "('VPD 306', 'Virgo Doorskin VPD 306', '11', '4', 'VPD', 5, 'Barcodes/VPD 306.jpg'), " +
                        "('VPD 307', 'Virgo Doorskin VPD 307', '12', '6', 'VPD', 5, 'Barcodes/VPD 307.jpg'), " +
                        "('VPD 308', 'Virgo Doorskin VPD 308', '11', '1', 'VPD', 8, 'Barcodes/VPD 308.jpg'), " +
                        "('VED 401', 'Virgo Doorskin VED 401', '12', '1', 'VED', 2, 'Barcodes/VED 401.jpg'), " +
                        "('VED 402', 'Virgo Doorskin VED 402', '12', '2', 'VED', 9, 'Barcodes/VED 402.jpg'), " +
                        "('VED 403', 'Virgo Doorskin VED 403', '12', '1', 'VED', 18, 'Barcodes/VED 403.jpg'), " +
                        "('VED 404', 'Virgo Doorskin VED 404', '11', '4', 'VED', 14, 'Barcodes/VED 404.jpg'), " +
                        "('VED 405', 'Virgo Doorskin VED 405', '12', '3', 'VED', 15, 'Barcodes/VED 405.jpg'), " +
                        "('VED 406', 'Virgo Doorskin VED 406', '12', '2', 'VED', 6, 'Barcodes/VED 406.jpg'), " +
                        "('VED 407', 'Virgo Doorskin VED 407', '12', '2', 'VED', 8, 'Barcodes/VED 407.jpg'), " +
                        "('VED 408', 'Virgo Doorskin VED 408', '11', '1', 'VED', 4, 'Barcodes/VED 408.jpg'), " +
                        "('VED 409', 'Virgo Doorskin VED 409', '12', '3', 'VED', 1, 'Barcodes/VED 409.jpg'), " +
                        "('VED 410', 'Virgo Doorskin VED 410', '12', '3', 'VED', 7, 'Barcodes/VED 410.jpg'), " +
                        "('VED 411', 'Virgo Doorskin VED 411', '0', '0', 'VED', 0, 'Barcodes/VED 411.jpg'), " +
                        "('VED 412', 'Virgo Doorskin VED 412', '12', '4', 'VED', 9, 'Barcodes/VED 412.jpg'), " +
                        "('VED 413', 'Virgo Doorskin VED 413', '0', '0', 'VED', 0, 'Barcodes/VED 413.jpg'), " +
                        "('VED 414', 'Virgo Doorskin VED 414', '11', '5', 'VED', 0, 'Barcodes/VED 414.jpg'), " +
                        "('VED 415', 'Virgo Doorskin VED 415', '11', '5', 'VED', 5, 'Barcodes/VED 415.jpg'), " +
                        "('VED 416', 'Virgo Doorskin VED 416', '11', '4', 'VED', 1, 'Barcodes/VED 416.jpg'), " +
                        "('VED 417', 'Virgo Doorskin VED 417', '12', '4', 'VED', 11, 'Barcodes/VED 417.jpg'), " +
                        "('VED 418', 'Virgo Doorskin VED 418', '11', '1', 'VED', 13, 'Barcodes/VED 418.jpg'), " +
                        "('VED 419', 'Virgo Doorskin VED 419', '11', '2', 'VED', 13, 'Barcodes/VED 419.jpg'), " +
                        "('VED 420', 'Virgo Doorskin VED 420', '11', '1', 'VED', 9, 'Barcodes/VED 420.jpg'), " +
                        "('VED 421', 'Virgo Doorskin VED 421', '11', '5', 'VED', 9, 'Barcodes/VED 421.jpg'), " +
                        "('VED 422', 'Virgo Doorskin VED 422', '11', '3', 'VED', 7, 'Barcodes/VED 422.jpg'), " +
                        "('VED 423', 'Virgo Doorskin VED 423', '0', '0', 'VED', 0, 'Barcodes/VED 423.jpg')";
                System.err.println("Insert secind half : "+stmt.executeUpdate(query));
            }
            
            
            ResultSet rs = stmt.executeQuery("select * from STOCK");
            int size = 0;
            while (rs.next()) 
            {
                size++;
            }
            System.out.println("size = "+size);
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
}
