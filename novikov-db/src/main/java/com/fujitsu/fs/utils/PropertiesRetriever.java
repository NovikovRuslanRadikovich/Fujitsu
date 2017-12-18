package com.fujitsu.fs.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class PropertiesRetriever {

    private static String DRIVER;
    private static String CONNECTION_URL;
    private static String PASSWORD;
    private static String USERNAME;


    private static final String FILE_PATH = "D:\\Novikov\\WebApp\\src\\main\\resources\\database.json";


    private static JsonParser parser = new JsonParser();
    static Object object;
    static JsonObject obj;
    static {

        try {
            object = parser.parse(new FileReader(FILE_PATH));

            obj = (JsonObject) object;

        } catch(Exception ex) {
            ex.printStackTrace();
        }


    }

    public static String getDriver() {
        if(DRIVER == null) {
            DRIVER =  obj.get("DRIVER").getAsString();
        }
        return DRIVER;
    }

    public static String getConnection_URL() {
        if(CONNECTION_URL == null) {
            CONNECTION_URL = obj.get("CONNECTION_URL").getAsString();
        }
        return CONNECTION_URL;
    }

    public static String getPassword() {
        if(PASSWORD == null) {
            PASSWORD = obj.get("PASSWWORD").getAsString();
        }
        return PASSWORD;
    }

    public static String getUSERNAME() {
        if(USERNAME == null) {
            USERNAME = obj.get("USERNAME").getAsString();
        }
        return USERNAME;
    }
}
