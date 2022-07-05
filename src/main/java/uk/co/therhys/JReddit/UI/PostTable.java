package uk.co.therhys.JReddit.UI;

import uk.co.therhys.CReddit.Post;

import javax.swing.*;

public class PostTable extends JTable {
    private PostTableModel model;

    public PostTable(){
        this(false);
    }

    public void setCompact(boolean compact){
        if(compact) {
            getColumnModel().getColumn(0).setCellRenderer(new CompactPostTableRenderer());
            setRowHeight(20);
        }else{
            getColumnModel().getColumn(0).setCellRenderer(new GoodLookingPostTableRenderer());
            setRowHeight(100);
        }
    }

    public PostTable(boolean compactRenderer){
        super(new PostTableModel());
        model = (PostTableModel) getModel();

        setCompact(compactRenderer);

        setShowHorizontalLines(true);
    }

    public Post getPost(int row){
        return (Post) model.getValueAt(row, 0);
    }

    public void addPost(Post post){
        model.addRow(post);
    }
}
