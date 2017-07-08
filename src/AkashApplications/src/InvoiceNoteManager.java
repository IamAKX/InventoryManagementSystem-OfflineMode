/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author akash
 */
public class InvoiceNoteManager {
    public static final String fileName = "invoice.properties";
    public InvoiceNoteManager() {
        File f = new File(fileName);
        if(!f.exists()) { 
            createInvoiceProperty();
        }
    }

    private void createInvoiceProperty() {
        Properties prop = new Properties();
	OutputStream output = null;
        try {

		output = new FileOutputStream(fileName);
		// set the properties value
		prop.setProperty("note", "1");
		
		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
    }
    
    public int getProperty() throws IOException
    {
        Properties prop = new Properties();
    	InputStream input = null;

    	try {
            try {
                    File f = new File(fileName);
                    input = new FileInputStream( f );
                }
                catch ( Exception e ) { input = null; }

    		//load a properties file from class path, inside static method
    		prop.load(input);

                //get the property value and print it out
                return Integer.parseInt(prop.getProperty("note"));

    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally{
        	if(input!=null){
                    input.close();
        	}
        }
        return 1;
    }
    
    public void setProperty() throws IOException
    {
        Properties prop = new Properties();
	OutputStream output = null;
        try {
                int noteNo = getProperty() + 1; 
		output = new FileOutputStream(fileName);
		// set the properties value
                prop.setProperty("note", String.valueOf(noteNo));
		
		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
    }
    
}
