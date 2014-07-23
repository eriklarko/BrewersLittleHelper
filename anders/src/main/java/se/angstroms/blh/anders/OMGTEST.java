/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.angstroms.blh.anders;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.angstroms.blh.anders.view.recipe.details.ingredientslist.MaltListItem;

/**
 *
 * @author eriklark
 */
public class OMGTEST extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group(MaltListItem.test()));

        stage.setTitle("Welcome to JavaFX!");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
