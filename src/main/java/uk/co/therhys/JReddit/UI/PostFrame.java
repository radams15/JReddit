package uk.co.therhys.JReddit.UI;

import uk.co.therhys.CReddit.Comment;
import uk.co.therhys.CReddit.Post;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class PostFrame extends JFrame {
    private final Post post;
    private JTree commentTree;
    private Comment lastComment;

    public PostFrame(Post post){
        super();

        this.post = post;

        initUI();
    }

    private void initUI(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(new JScrollPane(mainPanel));

        JTextArea titleLbl = OsxUiFactory.getInstance().getSizedLabel(post.getTitle(), 24);
        mainPanel.add(titleLbl);

        /*if(post.is_img()){
            System.out.println("Image");
            File imgFile = post.getImageFile();

            if(imgFile != null) {
                JLabel viewer = OsxUiFactory.getInstance().getImageViewer(imgFile);

                mainPanel.add(viewer);
            }
        }*/

        if(! post.getText().equals("")) {
            JTextArea selftextLbl = OsxUiFactory.getInstance().getSizedLabel(post.getText());
            mainPanel.add(selftextLbl);
        }

        commentTree = new JTree();
        commentTree.setRowHeight(0);
        commentTree.setCellRenderer(new CommentsRenderer());
        mainPanel.add(commentTree);

        /*List comments = post.get_comments();
        addComments(comments);
        lastComment = (Comment) comments.get(comments.size()-1);
        commentTree.expandRow(0);*/

        mainPanel.add(commentTree);

        setSize(600, 400);

        //pack();
    }

    private void addComments(List comments, DefaultMutableTreeNode parent){
        for(int i=0 ; i<comments.size() ; i++){
            Comment comment = (Comment) comments.get(i);

            if(comment == null){
                continue;
            }

            DefaultMutableTreeNode node = new DefaultMutableTreeNode(comment);
            //addComments(comment.getChildren(), node);
            parent.add(node);
        }
    }

    private void addComments(List comments){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Comments");

        commentTree.setModel(new DefaultTreeModel(root));

        addComments(comments, root);
    }
}
