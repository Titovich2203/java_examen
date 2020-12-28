package examen.service;

import examen.model.Consultation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IConsultation extends Remote {

    public List<Consultation> getAll() throws RemoteException;

    public List<Consultation> searchConsultationsByService(String libelle) throws RemoteException;

    public boolean existService(String service) throws RemoteException;

}
