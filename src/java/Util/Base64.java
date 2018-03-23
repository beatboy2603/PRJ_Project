/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Think
 */
public class Base64 {
    private String ip;
    private int id;
    private long timestamp;

    public Base64() {
    }

    public Base64(String ip, int id, long timestamp) {
        this.ip = ip;
        this.id = id;
        this.timestamp = timestamp;
    }
    
    public Base64(String code){
        byte[] buffer = java.util.Base64.getDecoder().decode(code);
        reverse(buffer);
        int length = buffer.length;
        int offset=0;
        byte[] idByte = Arrays.copyOfRange(buffer, offset, Integer.BYTES);
        reverse(idByte);
        id = ByteBuffer.wrap(idByte).getInt();
        offset+=Integer.BYTES;
        int size = length - Integer.BYTES- Long.BYTES;
        byte[] b = new byte[size];ByteBuffer.wrap(buffer,4,size).get(b);
        ip = new String(b);
        offset+=size;
        timestamp = ByteBuffer.wrap(buffer, offset,Long.BYTES).getLong();
    }
    private void reverse(byte[] a){
        for(int i=0;i<a.length/2;i++){
            byte temp = a[i];
            a[i] = a[a.length-i-1];
            a[a.length-i-1]=temp;
        }
        
    }
    public String getEncoded(){
        byte[] idByte = ByteBuffer.allocate(Integer.BYTES).putInt(id).array();
        reverse(idByte);
        byte[] ipByte = ip.getBytes();
        byte[] timepstampByte = ByteBuffer.allocate(Long.BYTES).putLong(timestamp).array();
        java.io.ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            out.write(idByte);
            out.write(ipByte);
            out.write(timepstampByte);
        } catch (IOException ex) {
            Logger.getLogger(Base64.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] k = out.toByteArray();
        reverse(k);
        return new String(java.util.Base64.getEncoder().encode(k));
    }
    
    public String getIp() {
        return ip;
    }

    public int getId() {
        return id;
    }

   

    public long getTimestamp() {
        return timestamp;
    }

    public static void main(String[] args){
        int id = 3;
        java.util.Calendar timeout = java.util.Calendar.getInstance();
        timeout.add(Calendar.MINUTE, 15);
        long timestamp = timeout.getTimeInMillis();
        String ip = "147.0.0.1";
        Base64 encoder = new Base64(ip, id, timestamp);
        String encoded = encoder.getEncoded();
        System.out.println(encoded);
        Base64 decoder = new Base64(encoded);
        System.out.println(decoder.getId()+" "+decoder.getIp()+" "+(new java.util.Date(decoder.getTimestamp())));
        
    }
}
