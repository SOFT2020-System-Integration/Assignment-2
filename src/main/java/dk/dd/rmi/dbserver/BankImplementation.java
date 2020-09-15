package dk.dd.rmi.dbserver;

/**
 *
 * @author Dora Di
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankImplementation extends UnicastRemoteObject implements BankInterface
{
    // public static String url = "jdbc:h2:mem:Bank";
    public static String url = "jdbc:h2:file:C:/Users/thoma/Documents/Cphbusiness-Lyngby-Softwareudvikling/System-Integration/Assignment-2/src/main/resources/db/bank";
    public static String user = "sa";
    public static String password = "";
    public static String driver = "org.h2.Driver";

    BankImplementation()throws RemoteException{}

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name)
    {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/bank")
    public List<Customer> getMillionaires()
    {

        List<Customer> list=new ArrayList<Customer>();
        try
        {
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url, user, password);
            PreparedStatement ps=con.prepareStatement("select * from Customer where amount >= 100000;");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Customer c=new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
                System.out.println(c);
                list.add(c);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<Customer> findAllByName(String name) throws RemoteException {
        List<Customer> list=new ArrayList<Customer>();
        try
        {
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url, user, password);
            PreparedStatement ps=con.prepareStatement("select * from Customer where name ='"+name+"';");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Customer c=new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
                System.out.println(c);
                list.add(c);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public List<Customer> getDatabaseSize() throws RemoteException {
        List<Customer> list=new ArrayList<Customer>();
        try
        {
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url, user, password);
            PreparedStatement ps=con.prepareStatement("select * from Customer;");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Customer c=new Customer();
                c.setAccnum(rs.getLong(1));
                c.setName(rs.getString(2));
                c.setAmount(rs.getDouble(3));
                System.out.println(c);
                list.add(c);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return list;
    }


}



