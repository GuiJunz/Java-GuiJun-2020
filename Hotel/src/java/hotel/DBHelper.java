package hotel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBHelper {
    //1. connection
    //2.将 SQL传递给mysql
    //3.接收 mysql返回的 值，有2种，一、执行的insert update delete 等操作，返回一个状态；
    //4.close conn;
    //二.返回select的结果
    private static Connection conn;
    private final static String strUrl="jdbc:mysql://localhost:3306/hotel2";
    private static String userName,userPwd;
    //创建数据库connection
    public static void getConn()
    {
        userName="root";
        userPwd="971214";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(strUrl,userName,userPwd);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //2.将 SQL传递给mysql 执行查询select操作
    public ResultSet ExcuteQuery(String strSql)
    {
        ResultSet rs=null;
        if(conn==null)
            getConn();
        try {
            Statement stmt=conn.createStatement();
            //传递查询 sql语句
            rs=stmt.executeQuery(strSql);
            //stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    //2.将 SQL传递给mysql 执行更新操作（insert，update delete）
    public int ExcuteUpdate(String strSql)
    {
        int count=0;
        if(conn==null)
            getConn();
        try {
            Statement stmt=conn.createStatement();
            //传递更新 sql语句
            count=stmt.executeUpdate(strSql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    public static void Close()
    {
        try {
            if(conn!=null&&!conn.isClosed())
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


