package examen.service;

import examen.model.Consultation;
import examen.model.Service;
import examen.utils.HibernateUtil;
import org.hibernate.Session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class IConsultationImpl extends UnicastRemoteObject implements IConsultation {

    Session session;
    public IConsultationImpl() throws Exception
    {
        session = HibernateUtil.getSession();

    }


    @Override
    public List<Consultation> getAll() throws RemoteException {
        try
        {
            return session.createQuery("SELECT a FROM Consultation a",Consultation.class).list();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Consultation> searchConsultationsByService(String s) throws RemoteException {
        try
        {
            List<Consultation> consultations = session.createQuery("SELECT a FROM Consultation a",Consultation.class).list();
            if(!s.equals(""))
            {
                consultations = consultations.stream().filter(x -> x.getMedecin().getService().getLibelle().toUpperCase().contains(s.toUpperCase())).collect(Collectors.toList());
            }
            return consultations;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean existService(String s) throws RemoteException {
        try
        {
            Service service =  session.createQuery("SELECT a FROM Service a WHERE a.libelle = :ser ",Service.class)
                    .setParameter("ser", s)
                    .getSingleResult();
            if (service != null)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
