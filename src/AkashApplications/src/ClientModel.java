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
public class ClientModel {
    String id, name, company, contact, address, favourite;

    public ClientModel(String id, String name, String company, String contact, String address, String favourite) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.contact = contact;
        this.address = address;
        this.favourite = favourite;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public String getFavourite() {
        return favourite;
    }
    
    
}
