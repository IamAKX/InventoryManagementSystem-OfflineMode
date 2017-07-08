/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.src;

/**
 *
 * @author akash
 */
public class ProductModel {
    String pid, design, rack, subrack, texture, qty, barcode;

    public ProductModel(String pid, String design, String rack, String subrack, String texture, String qty, String barcode) {
        this.pid = pid;
        this.design = design;
        this.rack = rack;
        this.subrack = subrack;
        this.texture = texture;
        this.qty = qty;
        this.barcode = barcode;
    }

    public String getPid() {
        return pid;
    }

    public String getDesign() {
        return design;
    }

    public String getRack() {
        return rack;
    }

    public String getSubrack() {
        return subrack;
    }

    public String getTexture() {
        return texture;
    }

    public String getQty() {
        return qty;
    }

    public String getBarcode() {
        return barcode;
    }
    
    
    
}
