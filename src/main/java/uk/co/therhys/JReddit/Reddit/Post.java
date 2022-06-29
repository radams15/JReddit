package uk.co.therhys.JReddit.Reddit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JsonUtil;
import uk.co.therhys.JReddit.Net.FileUtils;
import uk.co.therhys.JReddit.Net.Net;
import uk.co.therhys.JReddit.Net.OS;
import uk.co.therhys.JReddit.Net.Result;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Post {
    public String title, author, text, url, id, type, thumbnail;
    public int upvotes, downvotes;

    public Subreddit subreddit;

    private Client client;
    public List getComments(){
        String url = "https://oauth.reddit.com/comments/"+id+"?limit=-1&raw_json=1";

        Result res = client.net.get(url, client.getHeaders());

        if(res.hadError){
            return null;
        }

        List out = new ArrayList();

        JSONArray listing = res.toJsonArray();
        JSONObject comment;

        for(int i=0 ; i<listing.length() ; i++){
            try {
                JSONObject data = listing.getJSONObject(i).getJSONObject("data");
                JSONArray children = data.getJSONArray("children");

                for(int x=0 ; x<children.length() ; x++) {
                    comment = children.getJSONObject(x);
                    if(comment != null) {
                        out.add(
                                new Comment(this, comment)
                        );
                    }
                }

            }catch (JSONException e){
                // Do nothing
            }
        }

        return out;
    }

    public boolean isImage(){
        return url.endsWith(".jpg")
                || url.endsWith(".png")
                || url.endsWith(".gif")
                || url.endsWith(".webp")
        ;
    }

    public boolean hasThumbnail(){
        return ! thumbnail.equals("");
    }

    public Post(Client client, JSONObject child){
        this.client = client;

        JSONObject childData;
        try {
            childData = child.getJSONObject("data");
        }catch (JSONException e){
            System.err.println("Could not get data!");
            return;
        }

        String subredditName = JsonUtil.getJsonString(childData, "subreddit");

        subreddit = new Subreddit(client, subredditName);

        title = JsonUtil.getJsonString(childData, "title");
        author = JsonUtil.getJsonString(childData, "author");
        text = JsonUtil.getJsonString(childData, "selftext");
        url = JsonUtil.getJsonString(childData, "url");
        upvotes = JsonUtil.getJsonInt(childData, "ups");
        downvotes = JsonUtil.getJsonInt(childData, "downs");
        id = JsonUtil.getJsonString(childData, "id");

        type = JsonUtil.getJsonString(child, "kind");

        String thumb = JsonUtil.getJsonString(childData, "thumbnail");
        if(thumb.equals("self")){
            thumbnail = "";
        }else{
            thumbnail = thumb;
        }
    }

    public File getImageFile(){
        if(isImage()) {
            String uid = id + "." + FileUtils.getExtension(url);

            File imgFile;
            switch (OS.getOS()) {
                case OS.OSX:
                    imgFile = new File("/Users/"+OS.getUsername()+"/Library/JReddit/cache/" + uid);
                    break;

                case OS.LINUX:
                    imgFile = new File("/home/"+OS.getUsername()+"/.cache/JReddit/" + uid);
                    break;

                default:
                    imgFile = new File("/JReddit/cache/" + uid);
                    break;
            }

            if (!imgFile.getParentFile().exists()) {
                boolean success = imgFile.getParentFile().mkdirs();

                if (!success) {
                    System.err.println("Could not create cache dir: " + imgFile.getParentFile().toString());
                } else {
                    System.out.println("Created: " + imgFile.getParentFile().toString());
                }
            }

            if (!imgFile.exists()) {
                boolean fail = Net.getInstance().download(url, new HashMap(), imgFile.toString());

                if (fail) {
                    System.err.println("Failed to download image: '" + url + "'");
                }
            }

            return imgFile;
        }else {
            return null;
        }
    }

    private String cacheDir(){
        switch (OS.getOS()) {
            case OS.OSX:
                return ("/Users/"+OS.getUsername()+"/Library/JReddit/cache/");

            case OS.LINUX:
                return("/home/"+OS.getUsername()+"/.cache/JReddit/");

            default:
                return("/JReddit/cache/");
        }
    }

    public File getThumbFile() {
        if (hasThumbnail()) {
            if(thumbnail.equals("nsfw")){
                return new File(cacheDir() + "nsfw.png");
            }else if(thumbnail.equals("spoiler")){
                return new File(cacheDir() + "spoiler.png");
            }else if(thumbnail.equals("default")){
                return new File(cacheDir() + "default.png");
            }

            String uid = id + "_thumb." + FileUtils.getExtension(thumbnail);

            File imgFile = new File(cacheDir() + uid);

            if (!imgFile.getParentFile().exists()) {
                boolean success = imgFile.getParentFile().mkdirs();

                if (!success) {
                    System.err.println("Could not create cache dir: " + imgFile.getParentFile().toString());
                } else {
                    System.out.println("Created: " + imgFile.getParentFile().toString());
                }
            }

            if (!imgFile.exists()) {
                boolean fail = Net.getInstance().download(thumbnail, new HashMap(), imgFile.toString());

                if (fail) {
                    System.err.println("Failed to download image: '" + thumbnail + "'");
                }
            }

            return imgFile;
        } else{
            return null;
        }
    }
}
