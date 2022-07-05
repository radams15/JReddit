package uk.co.therhys.JReddit.Reddit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.therhys.JReddit.Net.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client {
    private String loginToken;
    private boolean authenticated = false;

    public final Net net;

    HashMap getHeaders(){
        HashMap headers = new HashMap();
        headers.put("User-Agent", "JReddit/0.1");

        if(authenticated){
            headers.put("Authorization", "bearer "+loginToken);
        }

        return headers;
    }

    public interface PostReceiver{
        void onPost(Post post);
    }

    public void getHot(String after, PostReceiver receiver){
        getHot(after, 1000, receiver);
    }

    public void getHot(String after, int limit, PostReceiver receiver){
        String url = "https://oauth.reddit.com/hot?limit="+limit;
        if(after != null){
            url += "&after=t3_"+after;
        }

        Result res = net.get(url, getHeaders());

        if(res.hadError){
            res.error.printStackTrace();
            return;
        }

        JSONObject obj = res.toJson();

        try{
            JSONArray array = obj.getJSONObject("data").getJSONArray("children");

            for(int i=0 ; i<array.length() ; i++){
                Post post = new Post(this, array.getJSONObject(i));
                receiver.onPost(post);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void login(String username, String password, String clientId, String clientSecret){
        Result res = net.post_auth(
                "https://www.reddit.com/api/v1/access_token",
                "grant_type=password&username="  + username + "&password=" + password,
                getHeaders(),
                clientId,
                clientSecret
        );

        try {
            loginToken = (String) res.toJson().get("access_token");
            authenticated = true;
        }catch (Exception e){
            authenticated = false;
            e.printStackTrace();
        }
    }

    public List getSubs(){
        List out = new ArrayList();

        Result res = net.get("https://oauth.reddit.com/subreddits/mine/subscriber?limit=500", getHeaders());

        if(res.hadError){
            res.error.printStackTrace();
            return out;
        }

        try{
            JSONArray children = res.toJson().getJSONObject("data").getJSONArray("children");

            for(int i=0 ; i<children.length() ; i++){
                JSONObject child = children.getJSONObject(i);
                out.add(new Subreddit(this, child));
            }
        }catch (JSONException e){
            e.printStackTrace();
            return out;
        }

        return out;
    }

    public Client(String token){
        net = Net.getInstance();

        loginToken = token;
        authenticated = true;

        System.out.println("Logged in with token: " + loginToken);
    }

    public Client(String username, String password, String clientId, String clientSecret){
        net = Net.getInstance();

        login(username, password, clientId, clientSecret);

        System.out.println("Logged in with token: " + loginToken);
    }

    public Client(Config conf){
        this(conf.username, conf.password, conf.cid, conf.secret);
    }
}
