package uk.co.therhys.JReddit.Net;

import java.io.File;
import java.util.HashMap;

public class Net {

    private static Net instance;

    public Result post_auth(String urlStr, String data, HashMap headers, String username, String password){
        throw new RuntimeException("Net unimplemented!");
    }

    public Result get(String urlStr, HashMap headers){
        throw new RuntimeException("Net unimplemented!");
    }

    public boolean download(String urlStr, HashMap headers, String outPath){
        Result data = get(urlStr, headers);

        if(data.hadError){
            data.error.printStackTrace();
            return true;
        }

        FileUtils.writeFile(new File(outPath), data.data);

        return false;
    }

    public static Net getInstance(){
        if(instance == null){
            instance = newNet();
        }

        return instance;
    }

    private static Net newNet(){
        if(OS.getOS() == OS.OSX && ! OS.versionAbove("10.6.0")){ // If OSX < snow leopard use the library (ppc)
            return new MacNet();
        }else{
            return new UrlNet();
        }
    }
}
