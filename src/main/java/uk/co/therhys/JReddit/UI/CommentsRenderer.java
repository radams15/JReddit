package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Comment;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentsRenderer extends DefaultTreeCellRenderer {
    final String chunkRegex = "[\\s\\S]{1,#a}<br>|[\\s\\S]{1,#b}";
    final Pattern chunkPattern;

    private static final int[] size = {600, 150};

    public CommentsRenderer(){
        this(150);
    }

    public CommentsRenderer(int lineLength){
        super();

        String regex = chunkRegex.replaceAll("#a", String.valueOf(lineLength-4)).replaceAll("#b", String.valueOf(lineLength));

        chunkPattern = Pattern.compile(regex, Pattern.MULTILINE);
    }

    private final int lineLength = 30;

    private int count(String subj, String substr){
        int out = 0;

        int lastIndex = 0;
        while(lastIndex != -1){
            lastIndex = subj.indexOf(substr, lastIndex);
            out++;
        }

        return out;
    }
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object obj = node.getUserObject();

        if(obj instanceof Comment){
            Comment comment = (Comment) obj;
            StringBuffer body = new StringBuffer();

            if(comment.body != null) {
                final Matcher matcher = chunkPattern.matcher(comment.body);

                while (matcher.find()) {
                    body.append(matcher.group(0));
                    body.append("<br>");
                }
            }

            setText("<html><body style='width: 600px'>"+body+"</html>");
            setSize(size[0], size[1]);
            setBackground(OsxUiFactory.backgroundColour);
        }

        return this;
    }
}
