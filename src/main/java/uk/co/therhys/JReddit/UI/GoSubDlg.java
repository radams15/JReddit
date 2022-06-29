package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Client;
import uk.co.therhys.JReddit.Reddit.Subreddit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GoSubDlg extends JDialog {
    private JComboBox choice;
    private Client client;
    private boolean complete = false;

    public GoSubDlg(Client client){
        this.client = client;
        choice = new JComboBox();

        List subs = client.getSubs();

        for(int i=0 ; i<subs.size() ; i++){
            choice.addItem(((Subreddit)subs.get(i)).name);
        }

        JButton ok = new JButton("OK");

        final GoSubDlg dlgRef = this;

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dlgRef.complete = true;
                dlgRef.setVisible(false);
            }
        });

        setLayout(new GridLayout(-1, 1));

        add(choice);
        add(ok);

        setSize(280, 120);
    }

    Subreddit getSelected(){
        if(!complete) { return null; }

        String name = (String) choice.getSelectedItem();
        return new Subreddit(client, name);
    }
}
