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
public class File {
    private int fId;
    private String fName;
    private String fSize;
    private String fOwner;
    private String privacy;

    public int getfId() {
        return fId;
    }

    public String getfName() {
        return fName;
    }

    public String getfSize() {
        return fSize;
    }

    public String getfOwner() {
        return fOwner;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setfSize(String fSize) {
        this.fSize = fSize;
    }

    public void setfOwner(String fOwner) {
        this.fOwner = fOwner;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public File(int fId, String fName, String fSize, String fOwner, String privacy) {
        this.fId = fId;
        this.fName = fName;
        this.fSize = fSize;
        this.fOwner = fOwner;
        this.privacy = privacy;
    }
    public File() {
    }
    
}
