package uk.co.therhys.JReddit.Net;

public class OS {
    public final static int OSX = 0;
    public final static int LINUX = 1;
    public final static int WINDOWS = 2;
    public final static int OTHER = 3;

    public static int getOS(){
        if(System.getProperty("os.name").toLowerCase().startsWith("mac os x")){
            return OSX;
        }else if(System.getProperty("os.name").toLowerCase().startsWith("linux")){
            return LINUX;
        }else{
            return WINDOWS;
        }
    }

    public static String getUsername(){
        return "rhys";
    }

    public static String getVersion(){
        return System.getProperty("os.version");
    }

    public static boolean versionAbove(String version){
        String actual = getVersion();
        int actuali = Integer.parseInt(actual.replaceAll("\\.", ""));
        int versioni = Integer.parseInt(version.replaceAll("\\.", ""));

        return actuali > versioni;
    }
}