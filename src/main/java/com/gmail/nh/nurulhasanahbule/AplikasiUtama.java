/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.nh.nurulhasanahbule;

import com.gmail.nh.nurulhasanahbule.dao.DepartmentDao;
import com.gmail.nh.nurulhasanahbule.entity.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class AplikasiUtama {
    
    public static void koneksi(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("hr");
        ds.setPassword("hr");
        ds.setUrl("jdbc:postgresql://localhost:5432/hr");
        ds.setDriverClassName("org.postgresql.Driver");
         
        Connection connection = null;
        
        try{
            // membuka koneksi ke database
            // Connection connection = ds.getConnection();
            
            //tambahan rollback dan commit
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            System.out.println("berhasil koneksi ke database");
            DepartmentDao dao = new DepartmentDao(connection);
           
            //save nilai department
        //    dao.save(new Department(null, "Sistem Analis", 1000, null));
            
        //    dao.save(new Department(null, "Sistem Analis", 1000, null));
            //error karena duplikate primary (dicopy yg sama datanya)
//          dao.update(new Department(3004, "Sistem Analis", 1000, null));
            
            //tambahan primary keys
            Department departmentBaru = dao.save(new Department(null, "Sistem Analis", 1000, null));
            System.out.println(departmentBaru.toString());
            
            dao.save(new Department(null, "Sistem Analis", 1000, null));
            
            dao.delete(3003);
            
            //untuk menambah nilai dari Dao
            List<Department> daftarDepartment = dao.findAll();
            for(Department d : daftarDepartment){
                System.out.println(d.toString());
            }
            
            //mengubah data pada posgresql 
          //String sqlInsert = "insert into departments (department_id, department_name) values (1, 'HRD')";
          //PreparedStatement preparedStatement = connection.prepareCall(sqlInsert);
            
            //mengubah data pada java
            //String sqlInsert = "insert into departments (department_id, department_name, location_id) values (?, ?, ?)";
            //PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            //preparedStatement.setInt(1, 1005);
            //preparedStatement.setString(2, "Publisher");
            //preparedStatement.setInt(3, 1000);
            
            //preparedStatement.executeUpdate();
            
            //Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select * from departments");
            
            //untuk mengambil data perbaris secara normal
            //while (resultSet.next()) {
            //    Integer departmentId = resultSet.getInt("department_id");
            //    String departmentName = resultSet.getString("department_name");
            //    Integer managerId = resultSet.getInt(3);
            //    Integer locationId = resultSet.getInt(4);
                
            //    System.out.println(
            //        String.format("{ departmentId : %s, departmentName : %s, managerId : %s, locationId : %s }",
            //                departmentId, departmentName, managerId, locationId));
            //}
            
            //Untuk memanggil get tostring
            //while (resultSet.next()) {
            //        Department dep = new Department(
            //        resultSet.getInt("department_id"),
            //        resultSet.getString("department_name"),
            //        resultSet.getInt(3),
            //        resultSet.getInt(4)
            //    );
            //    System.out.println(dep.toString());
            //}
            
            // menutup koneksi ke database
            //preparedStatement.close();
            //resultSet.close();
            //statement.close();
            connection.commit();
            connection.close();
        }catch(SQLException sqle){
            System.err.printf("tidak dapat koneksi ke database ;!");
            sqle.printStackTrace();
            //tambahan commit dan rollback
            try {
                if (connection != null) {
                    connection.rollback();;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Halo ini aplikasi maven pertama");
        
        koneksi();
    }

}
