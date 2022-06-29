package uk.co.therhys.JReddit.Net;

import java.lang.reflect.Method;

public class Base64 {
    public static String encode(byte[] input) throws Exception {
        try{
            Class.forName("sun.misc.BASE64Encoder");
            return new sun.misc.BASE64Encoder().encode(input);
        }catch (ClassNotFoundException e){
            Class base64 = Class.forName("java.util.Base64");
            Method encodeMethod = base64.getMethod("encode", new Class[]{byte[].class});

            return (String) encodeMethod.invoke(base64, new Object[]{input});
        }
    }
}
