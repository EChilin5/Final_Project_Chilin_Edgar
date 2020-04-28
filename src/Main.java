import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    private static String CreateMove() {
        String result = " ";
        Random random = new Random();
        int randomInt = random.nextInt(3);
        if (randomInt == 0) {
            result = "paper";
        } else if (randomInt == 1) {
            result = "scissors";
        } else if (randomInt == 2) {
            result = "rock";
        }
        return result;
    }

    public static void main(String[] args) throws IOException {


        try {
            ProcessBuilder processBuilder4 = new ProcessBuilder("javac", "src/RPS.java");
            Process processCreateFile = processBuilder4.start();
            print("Procces is being created ", processCreateFile.getInputStream());
        } catch (Exception e) {

        }

        //intializes the procedure to execute the program
        //follow a player, a Move, set score to 0, then how many iterations to run

        String[] command1 = {"java", "-cp", "src/", "RPS", "player 1", CreateMove(), "0", "10"};
        String[] command2 = {"java", "-cp", "src/", "RPS", "player 2", CreateMove(), "0", "10"};
        String[] command3 = {"java", "-cp", "src/", "RPS", "player 3", CreateMove(), "0", "10"};
        ProcessBuilder processBuilder = new ProcessBuilder(command1);
        Process process = processBuilder.start();

        print("Output One ", process.getInputStream());


        ProcessBuilder processBuilder2 = new ProcessBuilder(command2);
        Process process2 = processBuilder2.start();

        print("Output Two ", process2.getInputStream());

        ProcessBuilder processBuilder3 = new ProcessBuilder(command3);
        Process process3 = processBuilder3.start();

        print("Output Three ", process3.getInputStream());
    }

    private static void print(String status, InputStream input) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        System.out.println("************* " + status + "***********************");
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }

}