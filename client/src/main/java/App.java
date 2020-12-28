import examen.utils.LoadView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            LoadView.showView("CONSULTATION", "FormConsultation.fxml", 1);
        }
        catch(Exception e){
        e.printStackTrace();
    }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
