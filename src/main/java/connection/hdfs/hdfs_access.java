package connection.hdfs;
import  org.apache.hadoop.conf.Configuration;
import  org.apache.hadoop.fs.FileSystem;
import  org.apache.hadoop.fs.Path;

import java.io.IOException;

public class hdfs_access {
    public static void main(String[] args) throws IOException {

        Configuration  configuration  =  new  Configuration();
        configuration.set("fs.defaultFS",  "hdfs://localhost:9000");

        try  {
            FileSystem  fileSystem  =  FileSystem.get(configuration);
            Path  path  =  new  Path("/");
            Path[]  listOfFiles  =  fileSystem.listPaths(path);
            for(Path  file  :  listOfFiles){
                System.out.println(file);
            }
        }  catch  (IOException  e)  {
            e.printStackTrace();
        }
    }
}
