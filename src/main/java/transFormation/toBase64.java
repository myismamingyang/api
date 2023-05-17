package transFormation;

import org.apache.hadoop.hbase.io.hfile.HFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class toBase64 {
    public static void main(String[] args) throws IOException {

        String path = new String("C:\\Users\\14652\\Pictures\\Screenshots");
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < file.length(); i++) {
            String name = files[i].getName();
            //FileInputStream fi = new FileInputStream("C:\\Users\\14652\\Pictures\\Screenshots\\qwe.png");
            //FileOutputStream fo = new FileOutputStream("C:\\Users\\14652\\Pictures\\Screenshots\\out_qwe.png");
            FileInputStream fi = new FileInputStream(path + "\\" + name);
            FileOutputStream fo = new FileOutputStream(path + "\\" + "out_" + name);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fi.read(buf)) != -1)//如果不是-1就一直往里面进行装
            {
                fo.write(buf, 0, len);
            }
            fi.close();
            fo.close();
        }
    }
}
