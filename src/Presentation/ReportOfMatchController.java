package Presentation;

import Service.RefereeController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ReportOfMatchController {

    @FXML
    public ComboBox<String> idMatches;

    @FXML
    private Service.RefereeController RefereeController = new RefereeController();

    private String userName;
    private LinkedList<String> matches;

    public void initPage(String userName, LinkedList<String> matches) {
        this.userName = userName;
        this.matches = matches;
    }


    @FXML
    public void chooseMatchBtn(MouseEvent event) throws ParseException {
        ObservableList<String> elements = FXCollections.observableArrayList(this.matches);
        idMatches.setItems(elements);
        idMatches.getSelectionModel().selectFirst();
    }

    @FXML
    public void startCreatReport() throws Exception {
        String match =  idMatches.getSelectionModel().getSelectedItem();

        LinkedList<String> report = this.RefereeController.createReportOfMatch(match,userName);

        TableView<String> tableView = new TableView<>();
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Match Report");
        stage.setWidth(300);
        stage.setHeight(500);

        tableView.getItems().addAll(report);

        TableColumn<String,String> column1= new TableColumn<>("Events");
        column1.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        tableView.getColumns().setAll(column1);

        ((Group)scene.getRoot()).getChildren().addAll(tableView);

        stage.setScene(scene);
        stage.show();

    }



}
