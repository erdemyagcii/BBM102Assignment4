import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.io.File;

public class TrailerScene {

    static void normalTrailerScene(){
        File f = new File("assets\\trailers\\" + Main.film.getPath());
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);
        mv.setFitWidth(600);
        mv.setFitHeight(600);

        ComboBox<String> comboBox = new ComboBox<>();
        for(Hall i:Main.film.halls)
            comboBox.getItems().add(i.getName());
        Label welcome = new Label(Main.film.getName() + " (" + Main.film.getDuration() + " minutes)");

        Button play = new Button(">");
        Button backflux = new Button("<<");
        Button forwardflux = new Button(">>");
        Button allback = new Button("|<<");
        Button ok = new Button("OK");
        Button back = new Button("< BACK");

        Slider slider = new Slider();
        slider.setOrientation(Orientation.VERTICAL);
        slider.setValue(mp.getVolume()*100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(slider.getValue() / 100);
            }
        });
        slider.setValue(50);

        Slider slider2 = new Slider(0,1,0);
        slider2.setOrientation(Orientation.HORIZONTAL);
        slider2.setPrefWidth(600);
        slider2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(Duration.millis(slider2.getValue() * mp.getTotalDuration().toMillis()) );
            }
        });
        mp.currentTimeProperty().addListener(l -> {
            slider2.setValue((mp.getCurrentTime().toMillis() / mp.getTotalDuration().toMillis()));
        });

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.normalLoggedInScene();
            }
        });

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(play.getText().equals(">")){
                    mp.play();
                    play.setText("||");
                }
                else{
                    mp.pause();
                    play.setText(">");
                }
            }
        });

        forwardflux.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration time = mp.getCurrentTime().add(Duration.millis(5000));
                mp.seek(time);
            }
        });

        backflux.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration time = mp.getCurrentTime().add(Duration.millis(-5000));
                mp.seek(time);
            }
        });

        allback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mp.seek(Duration.millis(0));
            }
        });


        BorderPane borderPane = new BorderPane();
        GridPane layout5 = new GridPane();
        GridPane.setConstraints(play,0,1);
        GridPane.setConstraints(backflux,0,2);
        GridPane.setConstraints(forwardflux,0,3);
        GridPane.setConstraints(allback,0,4);
        GridPane.setConstraints(slider,1,0 );
        layout5.getChildren().addAll(play, backflux, forwardflux, allback, slider);
        GridPane layout6 = new GridPane();
        GridPane.setConstraints(back, 0,1);
        GridPane.setConstraints(comboBox, 1,1);
        GridPane.setConstraints(ok, 2,1);
        GridPane.setConstraints(slider2,0,0);
        layout6.getChildren().addAll(back, comboBox, ok, slider2);
        borderPane.setRight(layout5);
        borderPane.setCenter(mv);
        borderPane.setBottom(layout6);
        borderPane.setTop(welcome);
        Scene scene = new Scene(borderPane, 700, 400);
        Main.primaryStage.setScene(scene);

    }




    static void AdminTrailerScene(){
        File f = new File("assets\\trailers\\" + Main.film.getPath());
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);
        mv.setFitWidth(600);
        mv.setFitHeight(600);

        ComboBox<String> comboBox = new ComboBox<>();
        for(Hall i:Main.film.halls)
            comboBox.getItems().add(i.getName());
        Label welcome = new Label(Main.film.getName() + " (" + Main.film.getDuration() + " minutes)");

        Button play = new Button(">");
        Button backflux = new Button("<<");
        Button forwardflux = new Button(">>");
        Button allback = new Button("|<<");
        Button addHall = new Button("Add Hall");
        Button removeHall = new Button("Remove Hall");
        Button ok = new Button("OK");
        Button back = new Button("< BACK");

        Slider slider = new Slider();
        slider.setOrientation(Orientation.VERTICAL);
        slider.setValue(mp.getVolume()*100);
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(slider.getValue() / 100);
            }
        });
        slider.setValue(50);

        Slider slider2 = new Slider(0,1,0);
        slider2.setOrientation(Orientation.HORIZONTAL);
        slider2.setPrefWidth(600);
        slider2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(Duration.millis(slider2.getValue() * mp.getTotalDuration().toMillis()) );
            }
        });
        mp.currentTimeProperty().addListener(l -> {
            slider2.setValue((mp.getCurrentTime().toMillis() / mp.getTotalDuration().toMillis()));
        });


        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoggedIn.AdminLoggedInScene();
            }
        });

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(play.getText().equals(">")){
                    mp.play();
                    play.setText("||");
                }
                else{
                    mp.pause();
                    play.setText(">");
                }
            }
        });

        forwardflux.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration time = mp.getCurrentTime().add(Duration.millis(5000));
                mp.seek(time);
            }
        });

        backflux.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration time = mp.getCurrentTime().add(Duration.millis(-5000));
                mp.seek(time);
            }
        });

        allback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mp.seek(Duration.millis(0));
            }
        });

        addHall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addHallScene();
            }
        });

        removeHall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeHallScene();
            }
        });


        BorderPane borderPane = new BorderPane();
        GridPane layout5 = new GridPane();
        GridPane.setConstraints(play,0,1);
        GridPane.setConstraints(backflux,0,2);
        GridPane.setConstraints(forwardflux,0,3);
        GridPane.setConstraints(allback,0,9);
        GridPane.setConstraints(slider,1,0 );
        layout5.getChildren().addAll(play, backflux, forwardflux, allback, slider);
        GridPane layout6 = new GridPane();

        GridPane.setConstraints(addHall,1,0);
        GridPane.setConstraints(removeHall,2,0);
        GridPane.setConstraints(back, 0,1);
        GridPane.setConstraints(comboBox, 1,1);
        GridPane.setConstraints(ok, 2,1);
        GridPane.setConstraints(slider2,0,0);
        layout6.getChildren().addAll(back, comboBox, ok, addHall, removeHall, slider2);

        borderPane.setRight(layout5);
        borderPane.setCenter(mv);
        borderPane.setBottom(layout6);
        borderPane.setTop(welcome);
        Scene scene = new Scene(borderPane, 800, 400);
        Main.primaryStage.setScene(scene);
    }

    static void addHallScene() {
        Label firstText = new Label(Main.film.getName() + "(" + Main.film.getDuration() + " minutes)");
        Label row = new Label("Row:");
        Label column = new Label("Column:");
        Label name = new Label("Name:");
        Label price = new Label("Price:");
        Label finalText = new Label();

        ChoiceBox<Integer> choiceBox1 = new ChoiceBox<>();
        choiceBox1.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        choiceBox1.getSelectionModel().select(2);
        ChoiceBox<Integer> choiceBox2 = new ChoiceBox<>();
        choiceBox2.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        choiceBox2.getSelectionModel().select(2);
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        Button back = new Button("< BACK");
        Button ok = new Button("OK");

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TrailerScene.AdminTrailerScene();
            }
        });


        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean key = true;
                File f = new File("assets\\effects\\error.mp3");
                Media m = new Media(f.toURI().toString());
                MediaPlayer mp = new MediaPlayer(m);
                if(textField1.getText().isEmpty()){
                    mp.play();
                    finalText.setText("ERROR: Hall name could not be empty!");
                }
                else if(textField2.getText().isEmpty()){
                    mp.play();
                    finalText.setText("ERROR: Price could not be empty!");
                }
                else if(Main.film.FindHall(textField1.getText()) != null){
                    mp.play();
                    finalText.setText("ERROR: There is already a hall with the same name!");
                }
                else{
                    try {
                        if(Integer.parseInt(textField2.getText()) <= 0){
                            mp.play();
                            finalText.setText("ERROR: Price must be positive integer!");
                            key = false;
                        }
                        if(key){
                        Hall hall = new Hall(Main.film, textField1.getText(), Integer.parseInt(textField2.getText()), Integer.parseInt(String.valueOf(choiceBox1.getValue())),Integer.parseInt(String.valueOf(choiceBox2.getValue())));
                        finalText.setText("SUCCESS: Hall successfully created!");
                        }
                    }catch (NumberFormatException e){
                        mp.play();
                        finalText.setText("ERROR: Price must be positive integer!");
                    }
                }
            }
        });


        GridPane layout = new GridPane();
        GridPane.setConstraints(firstText,1 ,1);
        GridPane.setConstraints(row,0,2);
        GridPane.setConstraints(column,0,3);
        GridPane.setConstraints(name,0,4);
        GridPane.setConstraints(price,0,5);
        GridPane.setConstraints(choiceBox1,1,2);
        GridPane.setConstraints(choiceBox2,1,3);
        GridPane.setConstraints(textField1,1,4);
        GridPane.setConstraints(textField2,1,5);
        GridPane.setConstraints(back,0,6);
        GridPane.setConstraints(ok,2,6);
        GridPane.setConstraints(finalText,0,7);

        layout.getChildren().addAll(firstText, row, column, name, price, choiceBox1, choiceBox2, textField1, textField2, back, ok, finalText);
        Scene scene = new Scene(layout, 600, 200);
        Main.primaryStage.setScene(scene);



    }

    static void removeHallScene(){
        Label label = new Label("Select the hall that you desire to remove from " + Main.film.getName() + " and then click OK.");
        ComboBox<String> comboBox = new ComboBox<>();
        for(Hall i:Main.film.halls)
            comboBox.getItems().add(i.getName());
        comboBox.getSelectionModel().selectFirst();
        Button back = new Button("< BACK");
        Button ok = new Button("OK");

        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TrailerScene.AdminTrailerScene();
            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Hall hall = Main.film.FindHall(comboBox.getValue());
                Main.film.halls.remove(hall);
                Data.seats.removeIf(i -> i.getHall().equals(hall));
                Data.halls.remove(hall);
                comboBox.getItems().clear();
                for(Hall i:Main.film.halls)
                    comboBox.getItems().add(i.getName());
                comboBox.getSelectionModel().selectFirst();
            }
        });



        GridPane layout = new GridPane();
        GridPane.setConstraints(label,1 ,0);
        GridPane.setConstraints(comboBox,1 ,1);
        GridPane.setConstraints(back,0 ,2);
        GridPane.setConstraints(ok,2 ,2);
        layout.getChildren().addAll(label, comboBox, back, ok);
        Scene scene = new Scene(layout, 700, 100);
        Main.primaryStage.setScene(scene);


    }


}
