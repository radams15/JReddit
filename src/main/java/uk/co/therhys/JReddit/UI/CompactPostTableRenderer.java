package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Post;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CompactPostTableRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Post post = (Post) value;

        String text = post.title + " by " + post.author + " (" + post.subreddit.name + ")";

        return super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
    }
}