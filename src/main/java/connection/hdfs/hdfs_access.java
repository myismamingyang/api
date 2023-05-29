package connection.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import javax.xml.ws.Action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class hdfs_access {
    public static void main(String[] args) throws URISyntaxException, IOException {
//        Configuration configuration = new Configuration();
//        //指定我们使用的文件系统类型:
//        configuration.set("fs.defaultFS", "hdfs://node1:8020/");
//        //获取指定的文件系统
//        FileSystem fileSystem = FileSystem.get(configuration);
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node1:8020"), new Configuration());
        //cat(fileSystem,"/hbase");
    }

    public static void hCat(FileSystem fileSystem,String path) throws IOException {
        RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(new Path(path), true);
        while (locatedFileStatusRemoteIterator.hasNext()){
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            System.out.println(next.getPath().toString());
        }
        fileSystem.close();
    }
    public static void hMkdirs(FileSystem fileSystem,String hdfsPath) throws IOException {
        boolean mkdirs = fileSystem.mkdirs(new Path(hdfsPath));
        System.out.println(mkdirs);
    }
    public static void hGet(FileSystem fileSystem,String hdfsPath,String localPath) throws IOException {
        FSDataInputStream inputStream = fileSystem.open(new Path(hdfsPath));
        FileOutputStream outputStream = new FileOutputStream(new File(localPath));
        IOUtils.copy(inputStream,outputStream );
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
        fileSystem.close();
    }
    public static void hPut(FileSystem fileSystem,String hdfsPath,String localPath) throws IOException {
        FSDataInputStream inputStream = fileSystem.open(new Path(hdfsPath));
        FileOutputStream outputStream = new FileOutputStream(new File(localPath));
        IOUtils.copy(inputStream,outputStream );
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
        fileSystem.close();
    }
}
