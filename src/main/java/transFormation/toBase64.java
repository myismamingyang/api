package transFormation;

import java.io.*;
import java.util.Base64;


public class toBase64 {
    public static void main(String[] args) throws IOException {
        String path = "F:\\asd";
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            String[] split = name.split("\\.", 2);
            FileInputStream fi = new FileInputStream(path + "\\" + name);
            FileWriter fw = new FileWriter(path + "\\" + "outBase64_" + split[0] + ".txt");
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fi.read(buf)) != -1) {
                String base64 = Base64.getEncoder().encodeToString(buf);
                fw.write(base64);
                fw.flush();
            }
            fw.close();
            fi.close();
        }
    }
}
