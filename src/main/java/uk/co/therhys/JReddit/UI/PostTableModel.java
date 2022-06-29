package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Post;

import javax.swing.table.DefaultTableModel;

public class PostTableModel extends DefaultTableModel {
    public PostTableModel() {
        super(new Object[][] {},
                new String[] {"Post"});
    }

    Class[] columnTypes = new Class[] { Post.class };

    public Class getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    public void addRow(Post post){
        addRow(new Object[]{
                post
        });
    }

    public void clear(){
        getDataVector().removeAllElements();
        fireTableDataChanged();
    }
}
