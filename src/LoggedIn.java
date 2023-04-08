import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class LoggedIn {
    static void normalLoggedInScene() {
        Label label = new Label("Welcome " + Main.user.getName() + "!\nSelect a film and then click OK to continue.");
        ComboBox<String> comboBox = new ComboBox<>();
        for (Film i : Data.films)
            comboBox.getItems().add(i.getName());
        comboBox.getSelectionModel().selectFirst();
        Button ok = new Button("OK");
        Button logOut = new Button("LOG OUT");

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.primaryStage.setScene(SignUpLogIn.LogInScene());
            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.film = Data.FindFilm(comboBox.getValue());
                TrailerScene.normalTrailerScene();
            }
        });

        GridPane layout2 = new GridPane();
        layout2.setVgap(8);
        layout2.setHgap(10);
        GridPane.setConstraints(label, 1, 0);
        GridPane.setConstraints(comboBox, 1, 2);
        GridPane.setConstraints(ok, 2, 2);
        GridPane.setConstraints(logOut, 2, 3);
        layout2.getChildren().addAll(label, comboBox, ok, logOut);
        Scene scene = new Scene(layout2, 400, 150);
        Main.primaryStage.setScene(scene);
    }
















    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    static void AdminLoggedInScene() {
        Label label = new Label();
        if(!Main.user.isClubMember())
            label.setText("Welcome " + Main.user.getName() + " (Admin)"  + "!\nSelect a film and then click OK to continue.");
        else
            label.setText("Welcome " + Main.user.getName() + " (Admin - Club Member)" + "!\nSelect a film and then click OK to continue.");


        ComboBox<String> comboBox = new ComboBox<>();
        for (Film i : Data.films)
            comboBox.getItems().add(i.getName());
        comboBox.getSelectionModel().selectFirst();

        Button ok = new Button("OK");
        Button logOut = new Button("LOG OUT");
        Button addFilm = new Button("Add Film");
        Button removeFilm = new Button("Remove Film");
        Button editUsers = new Button("Edit Users");

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.primaryStage.setScene(SignUpLogIn.LogInScene());
            }
        });

        removeFilm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.removeFilmScene();
            }
        });

        addFilm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.addFilmScene();
            }
        });

        editUsers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.editUsersScene();
            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.film = Data.FindFilm(comboBox.getValue());
                TrailerScene.AdminTrailerScene();
            }
        });


        GridPane layout2 = new GridPane();
        layout2.setVgap(8);
        layout2.setHgap(10);
        GridPane.setConstraints(label, 1, 0);
        GridPane.setConstraints(comboBox, 1, 2);
        GridPane.setConstraints(ok, 2, 2);
        GridPane.setConstraints(logOut, 2, 4);
        GridPane.setConstraints(addFilm, 0, 3);
        GridPane.setConstraints(removeFilm, 1, 3);
        GridPane.setConstraints(editUsers, 2, 3);
        layout2.getChildren().addAll(label, comboBox, ok, logOut, addFilm, removeFilm, editUsers);
        Scene scene = new Scene(layout2, 430, 150);
        Main.primaryStage.setScene(scene);
    }

    static void removeFilmScene() {
        Label label = new Label("Select the film that you desire to remove and then click OK.");
        ComboBox<String> comboBox = new ComboBox<>();
        for (Film i : Data.films)
            comboBox.getItems().add(i.getName());
        comboBox.getSelectionModel().selectFirst();

        Button back = new Button("< BACK");
        Button ok = new Button("OK");

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.AdminLoggedInScene();
            }
        });
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Film film = Data.FindFilm(comboBox.getValue());
                Data.films.remove(film);
                Data.halls.removeIf(i -> i.getFilm().equals(film));
                Data.seats.removeIf(i -> i.getFilm().getName().equals(film.getName()));

                comboBox.getItems().clear();
                for (Film i : Data.films)
                    comboBox.getItems().add(i.getName());
                comboBox.getSelectionModel().selectFirst();
            }
        });

        GridPane layout = new GridPane();
        layout.setVgap(8);
        layout.setHgap(10);
        GridPane.setConstraints(label, 1, 2);
        GridPane.setConstraints(comboBox, 1, 3);
        GridPane.setConstraints(back, 0, 4);
        GridPane.setConstraints(ok, 2, 4);
        layout.getChildren().addAll(label, comboBox, back, ok);
        Scene scene = new Scene(layout, 425, 100);
        Main.primaryStage.setScene(scene);
    }

    static void addFilmScene() {
        Label label = new Label("Please give name, relative path of the trailer and duration of the film.");
        Label name = new Label("Name:");
        Label trailer = new Label("Trailer (Path):");
        Label duration = new Label("Duration (m):");
        Label finalText = new Label();
        TextField tName = new TextField();
        TextField tTrailer = new TextField();
        TextField tDuration = new TextField();
        Button back = new Button("< BACK");
        Button ok = new Button("OK");

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.AdminLoggedInScene();
            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File f1 = new File("assets\\effects\\error.mp3");
                Media m1 = new Media(f1.toURI().toString());
                MediaPlayer mp1 = new MediaPlayer(m1);

                boolean key = true;
                if(tName.getText().isEmpty()){
                    mp1.play();
                    finalText.setText("ERROR Film name could not be empty!");
                    key = false;
                }
                else if(tTrailer.getText().isEmpty()){
                    mp1.play();
                    finalText.setText("ERROR Trailer path could not be empty!");
                    key = false;
                }
                else {
                    try {
                        Integer.parseInt(tDuration.getText());
                    } catch (NumberFormatException e) {
                        mp1.play();
                        finalText.setText("ERROR Duration has to be positive integer!");
                        key = false;
                    }
                }
                if(key) {
                    try {
                        File f = new File("assets\\trailers\\" + tTrailer.getText());
                        new Media(f.toURI().toString());
                        if(Data.FindFilm(tName.getText()) == null){
                            Data.films.add(new Film(tName.getText(), tTrailer.getText(), Integer.parseInt(tDuration.getText())));
                            finalText.setText("SUCCESS: Film added succesfully!");
                        }
                        else{
                            mp1.play();
                            finalText.setText("ERROR: There is already a film with the same name");
                        }
                    }catch (Exception e){
                        mp1.play();
                        finalText.setText("ERROR There is no such a trailer!");
                    }
                }
            }
        });


        GridPane layout4 = new GridPane();
        layout4.setVgap(8);
        layout4.setHgap(10);
        GridPane.setConstraints(label, 2, 0);
        GridPane.setConstraints(name, 1, 1);
        GridPane.setConstraints(tName, 2, 1);
        GridPane.setConstraints(trailer, 1, 2);
        GridPane.setConstraints(tTrailer, 2, 2);
        GridPane.setConstraints(duration, 1, 3);
        GridPane.setConstraints(tDuration, 2, 3);
        GridPane.setConstraints(back, 1, 4);
        GridPane.setConstraints(ok, 3, 4);
        GridPane.setConstraints(finalText,2,5);
        layout4.getChildren().addAll(label, name, tName, trailer, tTrailer, duration, tDuration, back, ok, finalText);
        Scene scene = new Scene(layout4, 500, 175);
        Main.primaryStage.setScene(scene);
    }


    static void editUsersScene() {
        Data.users2.clear();
        TableView<User> table = new TableView<>();
        Data.users2.addAll(Data.users);
        Data.users2.remove(Data.FindUser("admin"));
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        TableColumn<User, String> nameColumn = new TableColumn<>("Username");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, Boolean> clubColumn = new TableColumn<>("Club Member");
        clubColumn.setMinWidth(200);
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("clubMember"));

        TableColumn<User, Boolean> adminColumn = new TableColumn<>("Admin");
        adminColumn.setMinWidth(200);
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));

        table.setItems(Data.users2);
        table.getColumns().addAll(nameColumn, clubColumn, adminColumn);

        Button back = new Button("< BACK");
        Button proMember = new Button("Promote/Demote Club Member");
        Button proAdmin = new Button("Promote/Demote Admin");

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminLoggedInScene();
            }
        });

        proAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = table.getSelectionModel().getSelectedItem();
                user.setAdmin(!user.isAdmin());
                table.refresh();
            }
        });

        proMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = table.getSelectionModel().getSelectedItem();
                user.setClubMember(!user.isClubMember());
                table.refresh();
            }
        });


        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, back, proAdmin, proMember);
        Scene scene = new Scene(vBox);
        Main.primaryStage.setScene(scene);
    }


}
