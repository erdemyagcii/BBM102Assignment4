import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {

    static Stage primaryStage = new Stage();
    static int trying = 0;
    static User user = new User();
    static Film film = new Film();

    public static void main(String[] args) throws IOException {
        Properties.createProperties();
        Data.createObjects();
        if(Data.users.isEmpty())
            Data.users.add(new User("admin", User.hashPassword("password"), true,true));
	    launch(args);
        Data.updateData();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Main.primaryStage.setTitle(Properties.title);
        Main.primaryStage.getIcons().add(new Image("assets\\icons\\logo.png"));//buraya tikkat
        Main.primaryStage.setScene(SignUpLogIn.LogInScene());
        Main.primaryStage.show();
    }
}
