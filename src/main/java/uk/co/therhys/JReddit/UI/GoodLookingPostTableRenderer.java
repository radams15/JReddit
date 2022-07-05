package uk.co.therhys.JReddit.UI;

import uk.co.therhys.CReddit.Post;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class GoodLookingPostTableRenderer implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Post post = (Post) value;

        PostPanel panel = new PostPanel(post);

        panel.setSelected(isSelected);

        return panel;
    }
}