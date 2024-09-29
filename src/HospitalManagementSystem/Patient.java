package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection conn;
    private Scanner sc;

    public Patient(Connection conn, Scanner sc) {
        this.conn = conn;
        this.sc = sc;
    }

    public void addPatient(){
        System.out.print("Enter Patient Name: ");
        String name = sc.next();
        System.out.print("Enter Patient's Age: ");
        int age = sc.nextInt();
        System.out.print("Enter Patient's Gender: ");
        String gender = sc.next();

        try{
            String query = "INSERT INTO patients(name,age,gender) values(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient added successfully!!");
            }
            else{
                System.out.println("Failed to add patient!!");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    public void viewPatient(){
        String query = "SELECT * FROM patients";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients:  ");
            System.out.println("+------------+----------------------+---------+----------------+");
            System.out.println("| Patient Id | Name                 | Age     | Gender         |");
            System.out.println("+------------+----------------------+---------+----------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-20s | %-7s | %-14s |\n",id,name,age,gender);
                System.out.println("+------------+----------------------+---------+----------------+");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "Select * from patients where id= ?";
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
