/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.src;

import com.businessrefinery.barcode.Barcode;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;
/**
 *
 * @author akash
 */
public class GenerateBarcode {
    String data,fileName;
    String filePath = "Barcodes";
    
    public GenerateBarcode(String data, String fileName) {
        this.data = data;
        this.fileName = fileName;
        createLoacalDirectory();
        
    }
    
    public boolean encode() throws Exception
    {
        Barcode barcode = new Barcode();
        barcode.setSymbology(Barcode.CODE128);
        barcode.setCode(data);
        System.out.println(filePath + File.separator + fileName + ".jpg");
        
        return barcode.drawImage2File(filePath + File.separator + fileName + ".jpg");


    }

    private void createLoacalDirectory() {
        File f = new File(filePath);
        if(!f.exists())
        {
            f.mkdir();
        }
    }
    
    public static String convertToBase64(String barcodePath )
    {
        String type = "jpg";
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(barcodePath));
            } catch (IOException e) {}
            
        
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(img, type, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
    
    public static BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
