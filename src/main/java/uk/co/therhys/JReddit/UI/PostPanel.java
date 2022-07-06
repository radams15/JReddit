package uk.co.therhys.JReddit.UI;

import uk.co.therhys.CReddit.Post;

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

        JTextArea titleLbl = OsxUiFactory.getInstance().getSizedLabel(post.getTitle(), 20);
        JTextArea authorLbl = OsxUiFactory.getInstance().getSizedLabel("By: " + post.getAuthor(), 15);
        JTextArea subLbl = OsxUiFactory.getInstance().getSizedLabel("r/" + post.getSubreddit().getName(), 15);

        add(titleLbl);

        String thumbPath = post.get_thumb_path();

        if (! thumbPath.equals("")) {
            File thumbFile = new File(thumbPath);

            if(thumbFile.exists()) {
                JLabel thumbnailImg = OsxUiFactory.getInstance().getImageViewer(thumbFile, thumbSize, thumbSize);
                add(thumbnailImg);
            }
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
