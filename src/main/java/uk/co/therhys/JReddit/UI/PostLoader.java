package uk.co.therhys.JReddit.UI;

import uk.co.therhys.CReddit.PostVector;
import uk.co.therhys.JReddit.UI.RedditClient;
import uk.co.therhys.CReddit.Subreddit;

public class PostLoader extends Thread{
    private RedditClient client;
    private Subreddit sub;
    private String after;
    private RedditClient.PostReceiver receiver;

    public PostLoader(RedditClient client, String after, RedditClient.PostReceiver receiver){
        this.client = client;
        if(after == null){
            after = "";
        }
        this.after = after;
        this.receiver = receiver;
    }

    public PostLoader(RedditClient client, Subreddit sub, String after, RedditClient.PostReceiver receiver){
        this(client, after, receiver);
        this.sub = sub;
    }

    public void run(){
        PostVector posts;
        if(sub == null){
            posts = client.reddit.get_posts_hot_list(1000, after);
        }else{
            posts = sub.get_posts_list("hot", 1000, after);
        }

        for(int i=0 ; i<posts.size() ; i++){
            receiver.onPost(posts.get(i));
        }
    }
}
