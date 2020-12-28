package examen.controller;

import examen.model.Consultation;
import examen.utils.Fabrique;
import examen.utils.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML
    private TextField txtService;

    @FXML
    private TableView<Consultation> tableConsultation;

    @FXML
    private TableColumn<Consultation, String> dateCol;

    @FXML
    private TableColumn<Consultation, String> medecinCol;

    @FXML
    private TableColumn<Consultation, String> patientCol;

    @FXML
    private TableColumn<Consultation, String> comCol;

    @FXML
    void refresh(ActionEvent event) {
        String service = txtService.getText().trim();
        if(!service.equals(""))
        {
            refreshTab(service);
        }
    }

    @FXML
    void search(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableConsultation.setVisible(false);
        //refreshTab();
    }

    public void refreshTab()
    {
        initCol();
        try
        {
            List<Consultation> consultations = Fabrique.getiConsultation().getAll();
            tableConsultation.setItems(FXCollections.observableArrayList(consultations));
        }
        catch (Exception e)
        {
            Utils.showMessage("Gestion des consultations","LISTE DES CONSULTATIONS","ERREUR "+e);
        }
    }

    public void refreshTab(String service)
    {
        initCol();
        try
        {
            List<Consultation> consultations = Fabrique.getiConsultation().searchConsultationsByService(service);
            tableConsultation.setItems(FXCollections.observableArrayList(consultations));
            if(consultations.size() > 0)
            {
                tableConsultation.setVisible(true);
            }
            else {
                tableConsultation.setVisible(false);
                if(Fabrique.getiConsultation().existService(service))
                {
                    Utils.showMessage("Gestion des consultations","LISTE DES CONSULTATIONS","LE SERVICE EXISTE MAIS PAS DE CONSULTATION ");
                }
                else
                {
                    Utils.showMessage("Gestion des consultations","LISTE DES CONSULTATIONS","LE SERVICE N'EXISTE PAS ");
                }
            }
        }
        catch (Exception e)
        {
            Utils.showMessage("Gestion des consultations","LISTE DES CONSULTATIONS","ERREUR "+e);
        }
    }

    public void initCol()
    {
        dateCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(Utils.convertDateToString(cellData.getValue().getDate())));
        medecinCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMedecin().getNom()));
        patientCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPatient().getNom()));
        comCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCommentaire()));
    }
}
