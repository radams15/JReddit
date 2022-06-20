package uk.co.therhys.JReddit.Reddit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    public Post post;
    public String title, author, body, id, thumbnail, url;
    public List children;
    public long upvotes, downvotes;
    public More more;

    public static class More{
        List children;
        String id;
    }

    public Comment(Post post, JSONObject child){
        this.post = post;

        children = new ArrayList();

        JSONObject childData;
        String kind;
        try {
            kind = JsonUtil.getJsonString(child, "kind");
            childData = child.getJSONObject("data");
        }catch (JSONException e){
            e.printStackTrace();
            System.err.println("Could not get data!");
            return;
        }

        if(kind.equals("more")){
            try {
                more = new More();
                more.children = new ArrayList();
                JSONArray children = child.getJSONArray("children");
                for(int i=0 ; i<children.length() ; i++){
                    more.children.add(children.getString(i));
                }
                more.id = child.getString("id");
                System.out.println(child.toString(1));
            }catch (JSONException e){

            }
        }else {

            title = JsonUtil.getJsonString(childData, "title");
            url = JsonUtil.getJsonString(childData, "url");
            author = JsonUtil.getJsonString(childData, "author_fullname");
            body = JsonUtil.getJsonString(childData, "body_html");
            upvotes = JsonUtil.getJsonInt(childData, "ups");
            downvotes = JsonUtil.getJsonInt(childData, "downs");
            id = JsonUtil.getJsonString(childData, "id");

            body = urlDecode(body);

            try {
                JSONObject replies = childData.getJSONObject("replies");

                try {
                    JSONObject data = replies.getJSONObject("data");
                    JSONArray childrenObj = data.getJSONArray("children");

                    for (int x = 0; x < childrenObj.length(); x++) {
                        children.add(
                                new Comment(post, childrenObj.getJSONObject(x))
                        );
                    }

                } catch (JSONException e) {

                }

            } catch (JSONException e) {

            }

            String thumb = JsonUtil.getJsonString(childData, "thumbnail");
            if (thumb.equals("self")) {
                thumbnail = "";
            } else {
                thumbnail = thumb;
            }

        }
    }

    private static String replace(String subj, String data, String replacement){
        return subj.replaceAll(data, replacement);
    }

    public static String urlDecode(String value) {
        String out = replace(value, "&lt;", "<");
        out = replace(out, "&gt;", ">");
        out = replace(out, "&amp;quot;", "\"");
        out = replace(out, "&amp;#39;", "'");

        return out;
    }
}
