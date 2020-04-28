import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //create a move for the players
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

    //created one process to run javac RPS.JAVA in order to create the java file
    //which will be ran by other proccesses by using java RPS
    // once proccess is completed it will output the result
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter number of games");

        String count = myObj.nextLine();  // Read user input
        System.out.println("Number of games is: " + count);  // Output user input

        try {
            ProcessBuilder processBuilder4 = new ProcessBuilder("javac", "src/RPS.java");
            Process processCreateFile = processBuilder4.start();
            print("Procces is being created ", processCreateFile.getInputStream());
        } catch (Exception e) {

        }

        //intializes the procedure to execute the program
        //follow a player, a Move, set score to 0, then how many iterations to run

        String[] command1 = {"java", "-cp", "src/", "RPS", "player 1", CreateMove(), "0", count};
        String[] command2 = {"java", "-cp", "src/", "RPS", "player 2", CreateMove(), "0", count};
        String[] command3 = {"java", "-cp", "src/", "RPS", "player 3", CreateMove(), "0", count};
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

    //print the output of each implementation
    //once the proccess is completed it will output  the content from the file after it is
    //done executing
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
