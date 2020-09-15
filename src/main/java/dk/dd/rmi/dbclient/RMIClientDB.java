package dk.dd.rmi.dbclient;
/**
 *
 * @author Dora Di
 */
import java.rmi.Naming;
import java.util.List;
import dk.dd.rmi.dbserver.*;

public class RMIClientDB 
{
    public static void main(String args[])throws Exception
    {  
            // name =  rmi:// + ServerIP +  /EngineName;
            String remoteEngine = "rmi://localhost/BankServices";
            
            // Create local stub, lookup in the registry searching for the remote engine - the interface with the methods we want to use remotely
            BankInterface obj = (BankInterface) Naming.lookup(remoteEngine);

        List<Customer> list=obj.findAllByName("Alice Wonderland");
        for(Customer c:list)
        {
            System.out.println(c.getAccnum()+ " " + c.getName() + " " + c.getAmount());
        }

        List<Customer> list2 = obj.getDatabaseSize();
        List<Customer> list3 = obj.getDatabaseSize();
        int data_size = obj.ReadFileToDatabase();

        System.out.println("Num of Customers before new data: " + list2.size());
        System.out.println("Num of Customers after new data: " + list3.size());
        System.out.println("Disk Space Used by Customer table: " + data_size);
    }
  
} 
