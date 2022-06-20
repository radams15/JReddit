package uk.co.therhys.JReddit.Reddit;

import org.json.JSONException;
import org.json.JSONObject;
import uk.co.therhys.JReddit.Net.FileUtils;
import uk.co.therhys.JReddit.Net.OS;

import java.io.File;
import java.io.FileNotFoundException;

public class Config {
    public String username;
    public String password;
    public String cid;
    public String secret;

    public boolean useCompact;

    private File file;

    public static File getDefaultFile(){
        File file;
        switch (OS.getOS()) {
            case OS.OSX:
                file = new File("/Users/"+OS.getUsername()+"/Library/JReddit/conf.json");
                break;

            case OS.LINUX:
                file = new File("/home/"+OS.getUsername()+"/.config/JReddit/save.json");
                break;

            default:
                file = new File("/JReddit/save.json");
                break;
        }

        return file;
    }

    public Config() throws FileNotFoundException{
        this(getDefaultFile());
    }

    public Config(File newFile) throws FileNotFoundException {
        file = newFile;

        if(!file.exists()) {
            throw new FileNotFoundException("File '"+newFile+"' does not exist! Please run the login utility first!");
        }

        String data = FileUtils.readFile(file);

        try {
            JSONObject root = new JSONObject(data);

            username = root.getString("username");
            password = root.getString("password");
            cid = root.getString("cid");
            secret = root.getString("secret");
            useCompact = root.getBoolean("useCompact");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void save(){
        try {
            JSONObject root = new JSONObject();

            root.put("username", username);
            root.put("password", password);
            root.put("cid", cid);
            root.put("secret", secret);
            root.put("useCompact", useCompact);

            String data = root.toString(1);

            FileUtils.writeFile(file, data);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
