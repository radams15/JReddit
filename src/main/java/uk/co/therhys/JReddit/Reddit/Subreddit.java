package uk.co.therhys.JReddit.Reddit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.therhys.JReddit.Net.UrlNet;
import uk.co.therhys.JReddit.Net.Result;

public class Subreddit {
    public String name;
    public String url;
    private final Client client;

    public Subreddit(Client client, String name, String url){
        this.name = name;
        this.url = url;
        this.client = client;
    }

    public Subreddit(Client client, JSONObject child){
        this.client = client;

        try {
            JSONObject childData = child.getJSONObject("data");

            name = childData.getString("display_name");
            url = childData.getString("url");
        }catch (Exception e){
            e.printStackTrace();
            name = "Unknown";
            url = "";
        }
    }

    public void getHot(String after, Client.PostReceiver receiver){
        String url = "https://oauth.reddit.com"+this.url+"hot";
        if(after != null){
            url += "?after="+after;
        }

        Result res = client.net.get(url, client.getHeaders());

        if(res.hadError){
            res.error.printStackTrace();
            return;
        }

        JSONObject obj = res.toJson();

        try{
            JSONArray array = obj.getJSONObject("data").getJSONArray("children");

            for(int i=0 ; i<array.length() ; i++){
                Post post = new Post(client, array.getJSONObject(i));
                receiver.onPost(post);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
