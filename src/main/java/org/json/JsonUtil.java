package org.json;

public class JsonUtil {
    public static Object getJsonObject(JSONObject obj, String key){
        try{
            return obj.get(key);
        }catch (JSONException e){
            return null;
        }
    }

    public static String getJsonString(JSONObject obj, String key){
        try{
            return obj.getString(key);
        }catch (JSONException e){
            return "null";
        }
    }

    public static int getJsonInt(JSONObject obj, String key){
        try{
            return obj.getInt(key);
        }catch (JSONException e){
            return 0;
        }
    }
}
