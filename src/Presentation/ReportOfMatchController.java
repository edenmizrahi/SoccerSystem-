package Presentation;

import Service.RefereeApplication;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReportOfMatchController {

    @FXML
    public ComboBox<String> idMatches;

    @FXML
    public TableView reportTable;


    @FXML
    private RefereeApplication refereeApplication = new RefereeApplication();

    private String userName;
    private List<String> matches;

    public void initPage(String userName, List<String> matches) {
        this.userName = userName;
        this.matches = matches;

        reportTable.setVisible(false);
    }

    @FXML
    public void BackToReferee(ActionEvent actionEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RefereePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        RefereePageController controller = loader.getController();
        controller.initUser(userName,"Referee");
        stageTheEventSourceNodeBelongs.setScene(scene);
    }


    @FXML
    public void chooseMatchBtn(MouseEvent event) {
        ObservableList<String> elements = FXCollections.observableArrayList(this.matches);
        idMatches.setItems(elements);
        idMatches.getSelectionModel().selectFirst();
    }


    @FXML
    public void createReportInline() {
        reportTable.setVisible(true);
        String match =  idMatches.getSelectionModel().getSelectedItem();
        if(idMatches.getSelectionModel().getSelectedIndex()==-1){
            Alert chooseFile = new Alert(Alert.AlertType.ERROR);
            chooseFile.setHeaderText("Error");
            chooseFile.setContentText("No match selected. please try again");
            chooseFile.show();
        }
        else{
            String reportStr = this.refereeApplication.createReportOfMatch(match,userName);
            //String reportStr = ClientController.connectToServer("RefereeApplication", "createReportOfMatch", match, userName);
            //check for error
            if(reportStr.contains("error")){
                Alert chooseFile = new Alert(Alert.AlertType.ERROR);
                chooseFile.setHeaderText("Error");
                chooseFile.setContentText(reportStr);
                chooseFile.show();
            }
            else{//else no problem
                List<String> report = Arrays.asList(reportStr.split(";"));

                reportTable.getItems().addAll(report);

                TableColumn<String,String> column1= new TableColumn<>("EventsAdapter");
                column1.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

                reportTable.getColumns().setAll(column1);
            }
        }


    }

    public void closeHandling(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent calcRoot = loader.load();
        HomePageController controller = loader.getController();
        controller.closeHandling(mouseEvent);
    }


//    @FXML
//    public void startCreatReport() throws Exception {
//        String match =  idMatches.getSelectionModel().getSelectedItem();
//        LinkedList<String> report = this.RefereeApplication.createReportOfMatch(match,userName);
//
//        TableView<String> tableView = new TableView<>();
//        Stage stage = new Stage();
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Match Report");
//        stage.setWidth(300);
//        stage.setHeight(500);
//
//        tableView.getItems().addAll(report);
//
//        TableColumn<String,String> column1= new TableColumn<>("EventsAdapter");
//        column1.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
//
//        tableView.getColumns().setAll(column1);
//
//        ((Group)scene.getRoot()).getChildren().addAll(tableView);
//
//        stage.setScene(scene);
//        stage.show();
//    }



}
