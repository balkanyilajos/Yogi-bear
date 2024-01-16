package sql;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author balka
 */

public class Score {
    
    private final Connection connection;
    
    public Score() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby://localhost/yogi-bear?serverTimezone=UTC", "user", "user");
    }
    
    public void add(String name, int score, int second) {
        try {
            Statement stmt = connection.createStatement();
            String sqlInsert = "insert into `yogi-bear`.score values ('"+name+"', "+score+", "+second+")";
            stmt.executeUpdate(sqlInsert);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<String> getScores() throws SQLException {
        ArrayList<String> data = new ArrayList<>();
        Statement stmt = connection.createStatement();
        
        String strSelect = "select * from score ORDER BY SCORE DESC";
        ResultSet rset = stmt.executeQuery(strSelect);
        int i = 0;
        
        while(rset.next() && i < 10) {
            i++;
            String name = rset.getString("name");
            int point = rset.getInt("point");
            int time = rset.getInt("time");
            String row = i + ". Név: " + name + ", Pont: " + point + ", idő: " + time + "s";
            data.add(row);
        }
        return data;
    }
}
