package connection.Hive;

import java.sql.*;

public class hive {
    public static String ip = "node3";
    public static String prot = "10000";
    public static String user = "root";
    public static String password = "MMYqq123";
    public static String db = "myhive";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
/*//        1.加载驱动
        Class.forName("org.apache.hive.jdbc.HiveDriver");
//        2.用户信息和url
        String url = "jdbc:hive2://" + ip + ":" + prot + "/" + db;
//        3.连接成功，数据库对象 Connection
        Connection connection = DriverManager.getConnection(url, user, password);
//        4.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();

//        5.执行SQL的对象去执行SQL，返回结果集
        String sql = "SELECT * FROM myhive.mysql_test_data;";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("hash_value=" + resultSet.getString("hash_value" + "\t"));
            System.out.println("id=" + resultSet.getString("id" + "\t"));
            System.out.println("name=" + resultSet.getString("name" + "\t"));
            System.out.println("age=" + resultSet.getString("age" + "\t"));
            System.out.println("gender=" + resultSet.getString("gender" + "\t"));
            System.out.println("birth=" + resultSet.getString("birth" + "\t"));
            System.out.println("birthplace=" + resultSet.getString("birthplace" + "\t"));
            System.out.println("school=" + resultSet.getString("school" + "\t"));
            System.out.println("phone=" + resultSet.getString("phone" + "\t"));
            System.out.println("update_ymd=" + resultSet.getString("update_ymd" + "\t"));
            System.out.println("update_hms=" + resultSet.getString("update_hms" + "\t"));
            System.out.println("datamon=" + resultSet.getString("datamon" + "\t"));
        }
//        6.释放连接
        resultSet.close();
        statement.close();
        connection.close();*/

        String driverName="org.apache.hive.jdbc.HiveDriver";
        Class.forName(driverName);
        //连接对象，连接hive的本机ip,不指定端口，默认端口号是10000，数据库名是mydemo，连接hive的账号是hive，密码是123456
        Connection con = DriverManager.getConnection("jdbc:hive2://" + ip + ":" + prot + "/" + db);
        String sql = "select name,age from test";
        //执行预编译执行对象PreparedStatemen,只接受值类型参数
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        //返回结果集
        ResultSet rst = preparedStatement.executeQuery();
        //ResultSet 是一根指向某数据表的指针，读取数据是一行一行从数据表中读取的
        while (rst.next()){
            System.out.println(rst.getString("name")+"\t"+rst.getString("age"));
        }
        //关闭连接
        con.close();
    }
}
