package uk.co.therhys.JReddit.Net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Result {
    public byte[] data;
    public int length;
    public boolean hadError = false;
    public Exception error;

    public String toString(){
        return new String(data);
    }

    public JSONObject toJson(){
        try {
            return new JSONObject(toString());
        } catch (JSONException e){
            return null;
        }
    }

    public JSONArray toJsonArray(){
        try {
            return new JSONArray(toString());
        } catch (JSONException e){
            return null;
        }
    }
}