/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ADMIN
 */
public class Permit {
    private int Id;
    private String username;

    public Permit(){}
    
    public Permit(int Id, String username) {
        this.Id = Id;
        this.username = username;
    }

    public int getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
