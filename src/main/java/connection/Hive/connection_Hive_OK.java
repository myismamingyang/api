package connection.Hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection_Hive_OK {
    public static String ip = "node3";
    public static String user = "root";
    public static String password = "MMYqq123";
    public static String db = "myhive";

    public static void main(String[] args) {
        Connection con;
        //jdbc驱动
        String driver = "org.apache.hive.jdbc.HiveDriver";
        //这行代码请注意：demo是你自己定义的数据库，若未定义，编译则会报错找不到demo
        String url = "jdbc:hive2://" + ip + ":10000/" + db;  // + "?&useSSL=false&serverTimezone=UTC";


        try {
            //注册JDBC驱动程序
            Class.forName(driver);
            //建立连接
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }

    }
}