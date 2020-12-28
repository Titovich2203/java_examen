package examen.utils;


import examen.service.IConsultation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fabrique {
    private static IConsultation iConsultation;

    private static void init() throws Exception {
        try {
            Registry registry = LocateRegistry.getRegistry(5003);
            iConsultation = (IConsultation) registry.lookup("consultationRemote");
        } catch (Exception e) {
            throw e;
        }
    }

    public static IConsultation getiConsultation() throws Exception {
        try {
            if (iConsultation == null) {
                init();
            }
            return iConsultation;
        } catch (Exception e) {
            throw e;
        }
    }
}
