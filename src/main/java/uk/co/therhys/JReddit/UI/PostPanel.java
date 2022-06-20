package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Post;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PostPanel extends JPanel {
    private Post post;

    private final static int thumbSize = 90;

    private void initUI(){
        GridLayout layout = new GridLayout(-1, 2);
        setLayout(layout);

        //Video.Thumbnail thumb = video.videoThumbnails[video.videoThumbnails.length-1];
        /*for(int i=0 ; i<video.videoThumbnails.length ; i++){
            if(video.videoThumbnails[i].width <= 480){
                thumb = video.videoThumbnails[i];
                break;
            }
        }*/

        JTextArea titleLbl = OsxUiFactory.getInstance().getSizedLabel(post.title, 20);
        JTextArea authorLbl = OsxUiFactory.getInstance().getSizedLabel("By: " + post.author, 15);
        JTextArea subLbl = OsxUiFactory.getInstance().getSizedLabel("r/" + post.subreddit.name, 15);
        File file = post.getThumbFile();

        add(titleLbl);

        if (file != null && file.exists()) {
            JLabel thumbnailImg = OsxUiFactory.getInstance().getImageViewer(file, thumbSize, thumbSize);
            add(thumbnailImg);
        }

        add(authorLbl);
        add(subLbl);
    }

    public void setSelected(boolean selected){
        Color colour;

        if(selected){
            colour = UIManager.getColor("Table.selectionBackground");
        }else{
            colour = UIManager.getColor("Table.background");
        }

        setBackground(colour);
    }

    public PostPanel(Post post){
        super();

        this.post = post;

        initUI();
    }
}
