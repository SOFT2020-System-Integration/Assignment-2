package dk.dd.rmi.dbserver;

/**
 *
 * @author Dora Di
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class BankImplementation extends UnicastRemoteObject implements BankInterface {
    // public static String url = "jdbc:h2:mem:Bank";
    public static String url = "jdbc:h2:file:C:/Users/Andreas/Desktop/System Integration/Assignment_2/src/main/resources/db/bank";
    public static String user = "sa";
    public static String password = "";
    public static String driver = "org.h2.Driver";

    BankImplementation() throws RemoteException {
    }



    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/bank")
    public List<Customer> getMillionaires() {

        List<Customer> list = new ArrayList<Customer>();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from Customer where amount >= 100000;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
                System.out.println(c);
                list.add(c);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<Customer> findAllByName(String name) throws RemoteException {
        List<Customer> list = new ArrayList<Customer>();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from Customer where name ='" + name + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
               // System.out.println(c);
                list.add(c);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<Customer> getDatabaseSize() throws RemoteException {
        List<Customer> list = new ArrayList<Customer>();
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from Customer;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
                //System.out.println(c);
                list.add(c);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


    JSONParser parser = new JSONParser();
    @Override
    public int ReadFileToDatabase() throws RemoteException {
        try {
            JSONArray tempArr = (JSONArray) parser.parse(new FileReader("C:/Users/Andreas/Desktop/System Integration/Assignment_2/src/main/resources/newData.json"));
            for (Object obj : tempArr) {
                JSONObject tempObj = (JSONObject) obj;
                long accnum = (long) tempObj.get("accnum");
                String name = (String) tempObj.get("name");
                double amount = (double) tempObj.get("amount");
                Customer c = new Customer();
                c.setName(name);
                c.setAmount(amount);
                c.setAccnum(accnum);
                InsertCustomer(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DatabaseDiskUse();

    }
    public int DatabaseDiskUse(){
        int data_size = 0;
        try {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("CALL DISK_SPACE_USED('Customer');");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data_size = rs.getInt(1);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return data_size;

    }

    public void InsertCustomer(Customer customer){
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Customer (accnum, name, amount) VALUES ("+customer.getAccnum()+",'"+ customer.getName()+"',"+ customer.getAmount()+");");
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
