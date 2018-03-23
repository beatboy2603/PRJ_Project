/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
public class Encode {
    private HashMap<String,String> encodeMap = new HashMap<>();
    private HashMap<String,String> decodeMap = new HashMap<>();
    
    public Encode(){
        encodeMap.put(":", "_");
        encodeMap.put("0", "z");
        encodeMap.put("1", "i");
        encodeMap.put("2", "n");
        encodeMap.put("3", "s");
        encodeMap.put("4", "y");
        encodeMap.put("5", "g");
        encodeMap.put("6", "r");
        encodeMap.put("7", "na");
        encodeMap.put("8", "h");
        encodeMap.put("9", "k");
        
        decodeMap.put(":", "_");
        decodeMap.put("z", "0");
        decodeMap.put("i", "1");
        decodeMap.put("n", "2");
        decodeMap.put("s", "3");
        decodeMap.put("y", "4");
        decodeMap.put("g", "5");
        decodeMap.put("r", "6");
        decodeMap.put("na", "7");
        decodeMap.put("h", "8");
        decodeMap.put("k", "9");
    }

    public HashMap<String, String> getEncodeMap() {
        return encodeMap;
    }

    public HashMap<String, String> getDecodeMap() {
        return decodeMap;
    }
    
    public String encode(String url){
        String encoded = "";
        String urlParts[] = url.split("");
        for(int i=0; i<urlParts.length; i++){
            encoded += encodeMap.get(urlParts[i]);
        }
        return encoded;
    }
    
    public String decode(String url){
        String decoded = "";
        String urlParts[] = url.split("");
        for(int i=0; i<urlParts.length; i++){
            decoded += decodeMap.get(urlParts[i]);
        }
        return decoded;
    }
}
