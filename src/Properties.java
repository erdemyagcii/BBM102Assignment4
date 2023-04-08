import java.io.*;

public class Properties {

    static int maxError;
    static String title;
    static int percentege;
    static int blockTime;

    static void createProperties() throws IOException {
        File file = new File("assets\\data\\properties.dat");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine(); bufferedReader.readLine();
        maxError = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
        title = bufferedReader.readLine().split("=")[1];
        percentege = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
        blockTime = Integer.parseInt(bufferedReader.readLine().split("=")[1]);
    }
}
