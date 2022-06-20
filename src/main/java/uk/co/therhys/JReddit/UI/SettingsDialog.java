package uk.co.therhys.JReddit.UI;

import uk.co.therhys.JReddit.Reddit.Config;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {
    private final Config conf;

    private JCheckBox compactModeBox;

    private JTextField usernameBox;
    private JPasswordField passwordBox;
    private JTextField cidBox;
    private JTextField secretBox;

    private JPanel initLayoutPanel(){
        JPanel card2 = OsxUiFactory.getInstance().getClearPanel();

        JPanel layoutPanel = OsxUiFactory.getInstance().getClearPanel();
        layoutPanel.setLayout(new GridLayout(-1, 1));

        usernameBox = new JTextField(conf.username);
        layoutPanel.add(new JLabel("Username: "));
        layoutPanel.add(usernameBox);

        passwordBox = new JPasswordField(conf.password);
        layoutPanel.add(new JLabel("Password: "));
        layoutPanel.add(passwordBox);
        passwordBox.setEchoChar('*');

        cidBox = new JTextField(conf.cid);
        layoutPanel.add(new JLabel("API Client ID: "));
        layoutPanel.add(cidBox);

        secretBox = new JTextField(conf.secret);
        layoutPanel.add(new JLabel("API Secret: "));
        layoutPanel.add(secretBox);

        card2.add(layoutPanel);

        return card2;
    }

    private JPanel initLoginPanel(){
        JPanel card1 = OsxUiFactory.getInstance().getClearPanel();

        JPanel qualityBox = OsxUiFactory.getInstance().getClearPanel();
        compactModeBox = new JCheckBox();
        compactModeBox.setSelected(conf.useCompact);
        qualityBox.add(new JLabel("Use Compact Mode: "));
        qualityBox.add(compactModeBox);
        card1.add(qualityBox);

        return card1;
    }

    public void save(){
        conf.username = usernameBox.getText();
        conf.password = passwordBox.getText();
        conf.cid = cidBox.getText();
        conf.secret = secretBox.getText();

        conf.useCompact = compactModeBox.isSelected();
    }

    private void initUI(){
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Login", initLoginPanel());
        tabbedPane.addTab("Layout", initLayoutPanel());

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public SettingsDialog(JFrame parent, Config conf){
        super(parent, "Settings");
        setModal(true);
        this.conf = conf;

        setSize(600, 400);

        initUI();
    }
}
