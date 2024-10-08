package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection conn;


    public Doctor(Connection conn) {
        this.conn = conn;
    }


    public void viewDoctor(){
        String query = "SELECT * FROM doctors";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors:  ");
            System.out.println("+------------+----------------------+--------------------------+");
            System.out.println("| Doctor Id  | Name                 | Specialization           |");
            System.out.println("+------------+----------------------+--------------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-20s | %-24s |\n",id,name,specialization);
                System.out.println("+------------+----------------------+--------------------------+");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "Select * from doctors where id= ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
