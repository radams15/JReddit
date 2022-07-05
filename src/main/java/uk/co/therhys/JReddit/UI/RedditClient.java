package uk.co.therhys.JReddit.UI;

import cz.adamh.utils.NativeUtils;
import uk.co.therhys.CReddit.*;
import uk.co.therhys.JReddit.Reddit.Config;

import java.util.ArrayList;
import java.util.List;

public class RedditClient {
    static {
        try{
            System.load("/Volumes/share/scripts/java/JReddit/src/main/resources/libreddit.dylib");
        }catch (Exception e){
            e.printStackTrace();
            try {
                NativeUtils.loadLibraryFromJar("/libreddit.dylib");
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    ArrayList posts = new ArrayList();
    Reddit reddit;

    private String loginToken;
    private boolean authenticated = false;

    public interface PostReceiver{
        void onPost(Post post);
    }

    public void getHot(String after, PostReceiver receiver){
        getHot(after, 1000, receiver);
    }

    public void getHot(String after, int limit, PostReceiver receiver){
        PostVector posts = reddit.get_posts_hot_list(limit, after);
        for(int i=0 ; i<posts.size() ; i++){
            receiver.onPost(posts.get(i));
        }
    }

    public List getSubs(){
        List out = new ArrayList();

        SubredditVector subs = reddit.get_subbed_list();

        for(int i=0 ; i<subs.size() ; i++){
            out.add(subs.get(i));
        }

        return out;
    }


    public RedditClient(String username, String password, String clientId, String clientSecret){
        reddit = new Reddit(username, password, clientId, clientSecret);

        System.out.println("Logged in?: " + reddit.getAuthenticated());
    }

    public RedditClient(Config conf){
        this(conf.username, conf.password, conf.cid, conf.secret);
    }
}
