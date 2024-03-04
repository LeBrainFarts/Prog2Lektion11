package opgave01.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import opgave01.controller.Controller;
import opgave01.models.Person;
import opgave01.models.Role;

import java.util.List;

public class Gui extends Application {
    private Controller controller;


    @Override
    public void start(Stage stage) throws Exception {
        this.controller = new Controller();
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        // List view for displaying persons
        ListView<Person> personListView = new ListView<>();
        personListView.setPrefHeight(200);

        // Text fields for adding a new person
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        ComboBox<Role> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll(Role.values());
        roleComboBox.setValue(Role.STUDENT);

        // Buttons for adding and filtering
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            Role role = roleComboBox.getValue();
            Person person = new Person(name, role);
            controller.addPerson(person);
            refreshPersonList(personListView);
        });

        Button filterButton = new Button("Filter");
        filterButton.setOnAction(e -> {
            Role selectedRole = roleComboBox.getValue();
            List<Person> filteredPersons = this.controller.filter(selectedRole);
            refreshPersonList(personListView, filteredPersons);
        });

        HBox addBox = new HBox(10);
        addBox.getChildren().addAll(nameField, roleComboBox, addButton, filterButton);

        refreshPersonList(personListView);

        vbox.getChildren().addAll(personListView, addBox);
        pane.add(vbox, 0, 0);
    }

    private void refreshPersonList(ListView<Person> listView) {
        listView.getItems().clear();
        listView.getItems().addAll(controller.getPeople());
    }

    private void refreshPersonList(ListView<Person> listView, List<Person> filteredPersons) {
        listView.getItems().clear();
        listView.getItems().addAll(filteredPersons);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
