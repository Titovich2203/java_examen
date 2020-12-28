package examen;

import examen.service.IConsultation;
import examen.service.IConsultationImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.setSecurityManager(new SecurityManager());
            IConsultation iConsultation = new IConsultationImpl();
            Registry registry = LocateRegistry.createRegistry(5003);
            registry.bind("consultationRemote", iConsultation);
            System.out.println("SUCCES !!");
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.out.println(e);
        }
    }
}
