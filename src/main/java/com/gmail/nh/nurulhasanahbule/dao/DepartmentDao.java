/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.nh.nurulhasanahbule.dao;

import com.gmail.nh.nurulhasanahbule.entity.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    
    private Connection connection;

    public DepartmentDao() {
    }
    
    public DepartmentDao(Connection connection){
        this.connection = connection;
    }
    
    public void save(Department dep)throws SQLException {
        String sqlInsert = "insert into departments (department_id, department_name, location_id) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setInt(1, dep.getId());
        preparedStatement.setString(2, dep.getNama());
        preparedStatement.setInt(3, dep.getLocationId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void update(Department dep)throws SQLException {
        String sqlInsert = "update departments set department_name = ?, location_id = ?, manager_id = ? where department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setInt(4, dep.getId());
        preparedStatement.setString(3, dep.getNama());
        preparedStatement.setInt(2, dep.getLocationId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void delete(Integer departmentId)throws SQLException {
        String sqlInsert = "delete from departments where department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setInt(1, departmentId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public List<Department> findAll() throws SQLException {
        List<Department> values = new ArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");
    
        while (resultSet.next()) {
                Department dep = new Department(
                    resultSet.getInt("department_id"),
                    resultSet.getString("department_name"),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
                );
                values.add(dep);   //menambah nilai
    }
    //tutup koneksi untuk ambil data
        resultSet.close();
        resultSet.close();
        
    return values;    //mengembalikan nilai   
    }
}
