package com.hsl.bohe.webmagic;

import com.hsl.bohe.entity.Food;
import com.hsl.bohe.entity.FoodType;

import java.sql.*;

public class UtilDao {
    private Connection conn = null;
    private Statement stmt = null;

    public UtilDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bohe?"
                    + "user=root&password=123&useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int add(FoodType foodType) {
        try {
            String sql = "INSERT INTO t_foodtype(imgurl, name) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, foodType.getImgurl());
            ps.setString(2, foodType.getName());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addFood(Food food) {
        try {
            String sql = "INSERT INTO t_food(name, heat, imgurl,tid) VALUES (?, ?, ?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,food.getName());
            ps.setDouble(2,food.getHeat());
            ps.setString(3,food.getImgurl());
            ps.setInt(4,food.getTid());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
