import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class SignUpLogIn {


    static boolean LogIn(String username, String password){
        try {
            if(Data.FindUser(username).getPassword().equals(User.hashPassword(password)))
                return true;
            else
                return false;
        }catch (NullPointerException e){
            return false;
        }
    }

    static void SignUpScene(){
        Label label1 = new Label("Welcome to HUCS Cinema Reservation System!\n" +
                "Fill the form below to create a new account\n" +
                "You can go to Log In page by clivking LOG IN Button.");
        Label username = new Label("Username:");
        Label password1 = new Label("Password:");
        Label password2 = new Label("Password:");
        TextField tUsername = new TextField();
        PasswordField pPassword1 = new PasswordField();
        PasswordField pPassword2 = new PasswordField();
        Label finalText = new Label();
        Button signUpButton  = new Button("SIGN UP");
        Button logInButton = new Button("LOG IN");


        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                File f = new File("assets\\effects\\error.mp3");
                Media m = new Media(f.toURI().toString());
                MediaPlayer mp = new MediaPlayer(m);
                String sentence;
                String uname = tUsername.getText().trim();
                String p1 = pPassword1.getText().trim();
                String p2 = pPassword2.getText().trim();
                if(Data.FindUser(uname) != null){
                    mp.play();
                    sentence =  "ERROR This username already exists!";
                    tUsername.clear(); pPassword1.clear(); pPassword2.clear();
                }
                else if(uname.isEmpty()){
                    mp.play();
                    sentence =  "ERROR Username cannot be empty!";
                    tUsername.clear(); pPassword1.clear(); pPassword2.clear();
                }
                else if(p1.isEmpty()){
                    mp.play();
                    sentence =  "ERROR Password cannot be empty!";
                    pPassword1.clear(); pPassword2.clear();
                }
                else if(!p1.equals(p2)){
                    mp.play();
                    sentence =  "ERROR Passwords do not match!";
                    tUsername.clear(); pPassword1.clear(); pPassword2.clear();
                }
                else {
                    Data.users.add(new User(uname, User.hashPassword(p1), false, false));
                    sentence =  "SUCCESS: You have successfully registered with your new credentials!";
                    tUsername.clear(); pPassword1.clear(); pPassword2.clear();
                }
                finalText.setText(sentence);
            }
        });


        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.primaryStage.setScene(LogInScene());
            }
        });


        GridPane layout = new GridPane();
        layout.setVgap(8);
        layout.setHgap(10);
        GridPane.setConstraints(label1,2,0);
        GridPane.setConstraints(username,1,1);
        GridPane.setConstraints(tUsername,2,1);
        GridPane.setConstraints(password1,1,2);
        GridPane.setConstraints(pPassword1,2,2);
        GridPane.setConstraints(password2,1,3);
        GridPane.setConstraints(pPassword2,2,3);
        GridPane.setConstraints(signUpButton,1,4);
        GridPane.setConstraints(logInButton,2,4);
        GridPane.setConstraints(finalText,2,5);
        layout.getChildren().addAll(label1, username, tUsername, password1, pPassword1,password2,pPassword2, signUpButton, logInButton,finalText);
        Scene scene  = new Scene(layout, 475,220);
        Main.primaryStage.setScene(scene);
    }

    static Scene LogInScene(){
        Label label1 = new Label("Welcome to HUCS Cinema Reservation System!\n" +
                "Please enter your credentials below and click LOGIN.\n" +
                "You can create a new account by clicking SIGN UP button.");
        Label username = new Label("Username:");
        Label password1 = new Label("Password:");
        TextField tUsername = new TextField();
        PasswordField pPassword1 = new PasswordField();
        Label finalText = new Label();
        Button signUpButton  = new Button("SIGN UP");
        Button logInButton = new Button("LOG IN");


        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File f = new File("assets\\effects\\error.mp3");
                Media m = new Media(f.toURI().toString());
                MediaPlayer mp = new MediaPlayer(m);


                if(!LogIn(tUsername.getText().trim(), pPassword1.getText().trim()) && Main.trying < (Properties.maxError - 1)){
                    mp.play();
                    pPassword1.setText("");
                    finalText.setText("ERROR: There is no such a credential!");
                    Main.trying += 1;
                }
                else if(Main.trying == 4) {
                    mp.play();
                    pPassword1.setText("");
                    finalText.setText("ERROR: Please wait 5 seconds to make a new operation!");
                    Main.trying += 1;
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    Main.trying = 0;
                                }
                            },(1000 *Properties.blockTime)
                    );}
                else if(Main.trying > 4){
                    mp.play();
                    pPassword1.setText("");
                    finalText.setText("ERROR: Please wait end of the 5 seconds to make a new operation!");
                    Main.trying += 1;
                }
                else {
                    Main.user = Data.FindUser(tUsername.getText());
                    Main.trying = 0;
                    if(Main.user.isAdmin())
                        LoggedIn.AdminLoggedInScene();
                    else
                        LoggedIn.normalLoggedInScene();
                    }}});


        signUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpScene();
            }});


        GridPane layout = new GridPane();
        layout.setVgap(8);
        layout.setHgap(10);
        GridPane.setConstraints(label1,2,0);
        GridPane.setConstraints(username,1,1);
        GridPane.setConstraints(tUsername,2,1);
        GridPane.setConstraints(password1,1,2);
        GridPane.setConstraints(pPassword1,2,2);
        GridPane.setConstraints(signUpButton,1,3);
        GridPane.setConstraints(logInButton,2,3);
        GridPane.setConstraints(finalText,2,4);
        layout.getChildren().addAll(label1, username, tUsername, password1, pPassword1, signUpButton, logInButton,finalText);
        Scene scene  = new Scene(layout, 475,220);
        return scene;

    }


}
