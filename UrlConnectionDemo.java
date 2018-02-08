import java.net.*;
import java.io.*;
import java.util.regex.*;


class UrlConnectionDemo
{
    public static void main(String[] args) throws Exception {
        URL url  = new URL("http://redisdoc.com/key/del.html");
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String str = null;

        String regex = "\\w+@\\w+(\\.\\w+)+"; 
        Pattern pattern = Pattern.compile(regex);

        while ((str = in.readLine()) != null ) {
            // System.out.println(str);
            Matcher m = pattern.matcher(str);
            while (m.find()) {
                System.out.println(m.group());
            }
        }
        in.close();
    }
}