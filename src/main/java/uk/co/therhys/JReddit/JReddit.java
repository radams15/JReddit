package uk.co.therhys.JReddit;

import apple.dts.samplecode.osxadapter.OSXAdapter;
import uk.co.therhys.JReddit.Net.MacNet;
import uk.co.therhys.JReddit.Net.Net;
import uk.co.therhys.JReddit.Net.OS;
import uk.co.therhys.JReddit.Reddit.Client;
import uk.co.therhys.JReddit.Reddit.Config;
import uk.co.therhys.JReddit.Reddit.Post;
import uk.co.therhys.JReddit.UI.MainFrame;
import uk.co.therhys.JReddit.UI.PostFrame;
import uk.co.therhys.JReddit.UI.PostLoader;

import javax.swing.*;
import java.util.HashMap;

public class JReddit {
    private static boolean tryLookAndFeel(String className){
        try {
            UIManager.setLookAndFeel(className);

            return false;
        }catch(Exception e){
            e.printStackTrace();

            return true;
        }
    }

    public static void main(String[] args){
        if(OS.getOS() == OS.LINUX){
            tryLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            System.out.println("Using gtk theme");
        }else if(! tryLookAndFeel(UIManager.getSystemLookAndFeelClassName())){
            System.out.println("Using system theme");
        }else if(! tryLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())){
            System.out.println("Using cross platform theme");
        }else{
            System.out.println("Cannot get theme!");
        }

        OSXAdapter.init();

        if(OS.getOS() == OS.OSX) {
            try {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "JReddit");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Config conf = null;
        try {
            conf = new Config();
        }catch (Exception e){
            e.printStackTrace();
            //TODO show login dialogue!
        }

        Client client = new Client(conf);

        /*MainFrame frame = new MainFrame(client, conf);
        frame.setVisible(true);*/

        client.getHot(null, new Client.PostReceiver() {
            public void onPost(Post post) {
                System.out.println(post.title);
            }
        });

        conf.save();
    }
}
