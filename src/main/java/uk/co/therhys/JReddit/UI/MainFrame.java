package uk.co.therhys.JReddit.UI;

import apple.dts.samplecode.osxadapter.OSXAdapter;
import uk.co.therhys.JReddit.Net.OS;
import uk.co.therhys.JReddit.Reddit.Client;
import uk.co.therhys.JReddit.Reddit.Config;
import uk.co.therhys.JReddit.Reddit.Post;
import uk.co.therhys.JReddit.Reddit.Subreddit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MainFrame extends JFrame implements Client.PostReceiver {
    private final Client client;
    private final Config conf;
    private JPanel mainPanel;
    private PostTable postTable;
    private PostTableModel postModel;
    private Subreddit currentSub = null;

    private ActionListener goHomeListener;
    private ActionListener goSubListener;
    private ActionListener postViewListener;
    private ActionListener morePostListener;
    private ActionListener aboutListener;
    private ActionListener quitListener;
    private ActionListener preferencesListener;

    private static ImageIcon getIcon(String img){
        ImageIcon icon;
        try{
            URL url = ClassLoader.getSystemClassLoader().getResource(img);
            icon = new ImageIcon(url);
        }catch(Exception e){
            icon = new ImageIcon("src/main/resources/"+img);
        }

        return icon;
    }

    private static JButton newToolButton(String title, String img, ActionListener listener){
        JButton btn = OsxUiFactory.getInstance().getUnifiedToolBtn(title);

        btn.setIcon(getIcon(img));

        btn.addActionListener(listener);

        return btn;
    }

    private void createToolbar(){
        JToolBar toolbar = new JToolBar();
        mainPanel.add(toolbar, BorderLayout.NORTH);
        toolbar.setFloatable(false);

        toolbar.add(newToolButton("View", "postView.png", postViewListener));
        toolbar.add(newToolButton("Get More", "postMore.png", morePostListener));
    }

    private void createMenubar(){
        JMenuBar menubar = getJMenuBar();
        if(menubar == null){
            menubar = new JMenuBar();
            setJMenuBar(menubar);
        }

        JMenu fileMenu = new JMenu("File");
        fileMenu.add("Quit").addActionListener(quitListener);
        fileMenu.add("Preferences").addActionListener(preferencesListener);
        menubar.add(fileMenu);

        JMenu subMenu = new JMenu("Sub");
        subMenu.add("Go").addActionListener(goSubListener);
        subMenu.add("Home").addActionListener(goHomeListener);
        menubar.add(subMenu);

        JMenu postMenu = new JMenu("Post");
        postMenu.add("View").addActionListener(postViewListener);
        postMenu.add("Get New").addActionListener(morePostListener);
        menubar.add(postMenu);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add("About").addActionListener(aboutListener);
        menubar.add(helpMenu);
    }

    private void createActions(){
        final MainFrame frameRef = this;

        postViewListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                onTableClick();
            }
        };

        goHomeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                postModel.clear();

                new PostLoader(client, null, frameRef).start();
            }
        };

        goSubListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GoSubDlg dlg = new GoSubDlg(client);
                dlg.setModal(true);
                dlg.setVisible(true);

                Subreddit sub = dlg.getSelected();

                if(sub != null) {
                    postModel.clear();

                    new PostLoader(client, sub, null, frameRef).start();
                }
            }
        };

        morePostListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Post prev = ((Post) postModel.getValueAt(postModel.getRowCount()-1, postModel.getColumnCount()-1));

                new PostLoader(client, prev.id, frameRef).start();
            }
        };

        aboutListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog dialog = new JDialog();
                dialog.setSize(300, 150);

                dialog.add(new JLabel("JReddit - by Rhys Adams"));
                dialog.setVisible(true);
            }
        };

        quitListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                frameRef.dispose();
            }
        };

        preferencesListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SettingsDialog dlg = new SettingsDialog(frameRef, conf);
                dlg.setModal(true);
                dlg.setVisible(true);
                dlg.save();

                postTable.setCompact(conf.useCompact);

                conf.save();
            }
        };

        if(OS.getOS() == OS.OSX){
            OSXAdapter.setQuitHandler(this, quitListener);
            OSXAdapter.setAboutHandler(this, aboutListener);
            OSXAdapter.setPreferencesHandler(this, preferencesListener);
        }
    }

    void onTableClick(){
        int row = postTable.getSelectedRow();
        Post post = postTable.getPost(row);

        PostFrame postFrame = new PostFrame(post);
        postFrame.setVisible(true);
    }

    private void setupUI(){
        setTitle("JReddit");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getRootPane().putClientProperty("apple.awt.brushMetalLook", Boolean.TRUE);

        mainPanel = OsxUiFactory.getInstance().getUnifiedToolbarPanel(new BorderLayout());
        setContentPane(mainPanel);

        setSize(800, 600);

        postTable = new PostTable(conf.useCompact);
        postModel = (PostTableModel) postTable.getModel();

        JScrollPane postPane = new JScrollPane(postTable);
        mainPanel.add(postPane, BorderLayout.CENTER);

        createActions();

        postTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    onTableClick();
                }
            }
        });

        /*postPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent event) {
                JScrollBar scrollBar = (JScrollBar) event.getAdjustable();
                int extent = scrollBar.getModel().getExtent();

                if(((scrollBar.getValue() + extent))/(scrollBar.getMaximum()) == 1){
                    morePostListener.actionPerformed(new ActionEvent(this, 1, ""));
                }

            }
        });*/

        createMenubar();
        createToolbar();

        new PostLoader(client, null, this).start();
    }

    public MainFrame(Client client, Config conf){
        this.client = client;
        this.conf = conf;

        Image appIcon = getIcon("JReddit.png").getImage();
        setIconImage(appIcon);
        if(OS.getOS() == OS.OSX){
            OSXAdapter.setApplicationIcon(this, appIcon);
        }

        setupUI();
    }

    public void onPost(Post post) {
        postTable.addPost(post);
    }
}
