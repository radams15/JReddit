package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Client;
import uk.co.therhys.JReddit.Reddit.Subreddit;

public class PostLoader extends Thread{
    private Client client;
    private Subreddit sub;
    private String after;
    private Client.PostReceiver receiver;

    public PostLoader(Client client, String after, Client.PostReceiver receiver){
        this.client = client;
        this.after = after;
        this.receiver = receiver;
    }

    public PostLoader(Client client, Subreddit sub, String after, Client.PostReceiver receiver){
        this(client, after, receiver);
        this.sub = sub;
    }

    public void run(){
        if(sub == null){
            client.getHot(after, receiver);
        }else{
            sub.getHot(after, receiver);
        }
    }
}
