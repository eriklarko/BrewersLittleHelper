package se.angstroms.blh.anders;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DragAndDropTable extends Application {

  @Override
  public void start(Stage primaryStage) {
    final BorderPane root = new BorderPane();
    final TableView<Person> table = new TableView<Person>();
    table.setItems(createData());
    final TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
    final TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

    final Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory = new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
      @Override
      public TableCell<Person, String> call(TableColumn<Person, String> col) {
        return new TableCell<Person, String>() {
          {
            setOnDragDetected(createDragDetectedHandler(this));
            setOnDragOver(createDragOverHandler(this, table));
            setOnDragDropped(createDragDroppedHandler(this, table));
          }

          @Override
          public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setText(null);
            } else {
              setText(item);
            }
          }
        };
      }
    };

    firstNameColumn.setCellFactory(cellFactory);
    lastNameColumn.setCellFactory(cellFactory);

    table.getColumns().addAll(firstNameColumn, lastNameColumn);

    root.setCenter(table);
    primaryStage.setScene(new Scene(root, 400, 600));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private ObservableList<Person> createData() {
    return FXCollections.observableArrayList(new Person("Hugo", "Lloris"),
        new Person("Benoit", "Assou-Ekotto"), new Person("Jan", "Vertonghen"),
        new Person("Michael", "Dawson"), new Person("Kyle", "Walker"),
        new Person("Scott", "Parker"), new Person("Mousa", "Dembele"),
        new Person("Gylfi", "Sigurdsson"), new Person("Gareth", "Bale"),
        new Person("Aaron", "Lennon"), new Person("Leandro", "Damiao"));
  }

  private EventHandler<MouseEvent> createDragDetectedHandler(
      final TableCell<Person, String> cell) {
    return new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(cell.getIndex()));
        db.setContent(content);
      }
    };
  }

  private EventHandler<DragEvent> createDragOverHandler(
      final TableCell<Person, String> cell, final TableView<Person> table) {
    return new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        final String INTEGER_REGEX = ("-?\\d+");
        final Dragboard dragboard = event.getDragboard();
        if (dragboard.hasString()) {
          final String value = dragboard.getString();
          if (Pattern.matches(INTEGER_REGEX, value)) {
            try {
              final int index = Integer.parseInt(value);
              if (index != cell.getIndex()
                  && index != -1
                  && (index < table.getItems().size() - 1 || cell.getIndex() != -1)) {
                event.acceptTransferModes(TransferMode.MOVE);
              }
            } catch (NumberFormatException exc) {
              // shouldn't get here, but just don't accept the transfer
            }
          }
        }
      }
    };
  }

  private EventHandler<DragEvent> createDragDroppedHandler(
      final TableCell<Person, String> cell, final TableView<Person> table) {
    return new EventHandler<DragEvent>() {
      @Override
      public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        int myIndex = cell.getIndex();
        if (myIndex < 0 || myIndex >= table.getItems().size()) {
          myIndex = table.getItems().size()-1;
        }
        int incomingIndex = Integer.parseInt(db.getString());
        table.getItems().add(myIndex, table.getItems().remove(incomingIndex));
        event.setDropCompleted(true);
      }
    };
  }

  public static class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;

    Person(String firstName, String lastName) {
      this.firstName = new SimpleStringProperty(this, "firstName", firstName);
      this.lastName = new SimpleStringProperty(this, "lastName", lastName);
    }

    public String getFirstName() {
      return firstName.get();
    }

    public void setFirstName(String firstName) {
      this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
      return firstName;
    }

    public String getLastName() {
      return lastName.get();
    }

    public void setLastName(String lastName) {
      this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
      return lastName;
    }

    @Override
    public String toString() {
      return firstName.get() + " " + lastName.get();
    }

  }
}