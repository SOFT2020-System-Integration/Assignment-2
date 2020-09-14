package dk.dd.rmi.dbserver;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry
{
        public static Registry registry;
        public RMIRegistry() throws RemoteException { }

        public static void main(String[] args) throws Exception
        {
            try
            {
                System.out.println("RMI server localhost starts");

                // Create a server registry at default port 1099
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("RMI registry created ");

                // Create engine of remote services, running on the server
                BankImplementation remoteEngine = new BankImplementation();

                // Give a name to this engine
                String engineName = "BankServices";

                // Register the engine by the name, which later will be given to the clients
                Naming.rebind("//localhost/" + engineName, remoteEngine);
                System.out.println("Engine " + engineName + " bound in registry");
            }
            catch (Exception e)
            {
                System.err.println("Exception:" + e);
            }
        }
}
