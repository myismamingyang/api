package Connection.Mysql;

import java.sql.*;

public class mysql {
    public static String ip = "node3";
    public static String user = "root";
    public static String password = "MMYqq123";
    public static String db = "my";
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
//        2.用户信息和url
        String url = "jdbc:mysql://" + ip + ":3306/" + db + "?&serverTimezone=UTC";  // + "?&useSSL=false&serverTimezone=UTC";
//        3.连接成功，数据库对象 Connection
        Connection connection = DriverManager.getConnection(url,user,password);
//        4.执行SQL对象Statement，执行SQL的对象
        Statement statement = connection.createStatement();

//        5.执行SQL的对象去执行SQL，返回结果集
        String sql = "SELECT * FROM test_name;";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.print("id="+resultSet.getString("id")+"\t");
            System.out.print("name="+resultSet.getString("name")+"\t");
            System.out.print("age="+resultSet.getString("age")+"\t");
            System.out.print("gender="+resultSet.getString("gender")+"\t");
            System.out.print("birth="+resultSet.getString("birth")+"\t");
            System.out.print("birthplace="+resultSet.getString("birthplace")+"\t");
            System.out.print("school="+resultSet.getString("school")+"\t");
            System.out.println("phone="+resultSet.getString("phone")+"\t");
        }
//        6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
