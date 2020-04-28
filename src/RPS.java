import java.net.*;
import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RPS{



    private static int Validate(String playerMove , String opponentMove){
        int score = 0;
        if((playerMove.contains( "rock")) && (opponentMove.contains("scissors"))){
            score = 1;
        }else if((playerMove.contains("paper") ) && (opponentMove.contains("rock") )){
            score = 1;
        }else if((playerMove.contains("scissors") ) && (opponentMove.contains("paper"))){
            score = 1;
        }
        return score;
    }

    private static String CreateMove(String id){
        String result = " ";
        Random random = new Random();
        int randomInt = random.nextInt(3);
        if(randomInt == 0){
            result = "paper";
        }else if(randomInt == 1){
            result = "scissors";
        }else if(randomInt == 2){
            result = "rock";
        }
        System.out.println("Sending player " + id + " move is " + result);
        return result;
    }





    private static int FinalScore(String MainP, String OppP1, String OppP2){
        int score = 0;
        if(OppP1.contains( OppP2)){
            int result = Validate(MainP, OppP1);
            result = result *2;
            score += result;

        }else {
            if(Validate(MainP, OppP1) != 0){
                score += 1;
            }else if((Validate(MainP, OppP2) != 0)){
                score += 1;
            }
        }
        return score;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> infomation = new ArrayList<>(2);
        infomation.add(args[0]); // player
        infomation.add(args[1]); // move
        infomation.add(args[2]); // score
        infomation.add(args[3]); // iterations


        ServerSocket mainServer  =  new ServerSocket( 4939, 4);
        ServerSocket mainServer2  =  new ServerSocket( 5050, 4);
        ServerSocket mainServer3  =  new ServerSocket( 9090, 4);
        Socket mainPlayerSocket = new Socket("localhost",4939);
        Socket opponenet2Socket = new Socket("localhost",5050);
        Socket opponenet3Socket = new Socket("localhost",9090);
        System.out.println("Client is connected ");

        ObjectOutputStream mainSend = new ObjectOutputStream(mainPlayerSocket.getOutputStream());
        ObjectOutputStream op2Send = new ObjectOutputStream(opponenet2Socket.getOutputStream());
        ObjectOutputStream op3Send = new ObjectOutputStream(opponenet3Socket.getOutputStream());
        ObjectInputStream mainRecieve = new ObjectInputStream(mainServer.accept().getInputStream());
        ObjectInputStream op2Recieve = new ObjectInputStream(mainServer2.accept().getInputStream());
        ObjectInputStream op3Recieve = new ObjectInputStream(mainServer3.accept().getInputStream());

        int mainResult = 0;
        int opponet2Result = 0;
        int opponet3Result = 0;
        for(int i = 0; i < Integer.parseInt(infomation.get(3)); i++){
            System.out.println("*************" + " Round " + (i+1) + " ***********************");
            mainSend.writeObject(CreateMove("1"));
            op2Send.writeObject(CreateMove("2"));
            op3Send.writeObject(CreateMove("3"));
            String main = (String) mainRecieve.readObject();

            System.out.println("Recieving Player 1 move " + main);
            String opponent2 = (String) op2Recieve.readObject();
            System.out.println("Recieving Player 2 move " + opponent2);
            String opponent3 = (String) op3Recieve.readObject();
            System.out.println("Recieving Player 3 move " + opponent2);
            mainResult += FinalScore(main, opponent2,  opponent3);
            opponet2Result += FinalScore(opponent3, main,  opponent2);
            opponet3Result += FinalScore(opponent2, opponent3,  main);

        }
        System.out.println("************* " + "final Scores " + "***********************");
        System.out.println("Player 1" + " " +mainResult);
        System.out.println("Player 2" + " " +opponet2Result);
        System.out.println("Player 3" + " " + opponet3Result);


        mainPlayerSocket.close();
        opponenet2Socket.close();
        opponenet3Socket.close();
        mainServer.close();


    }

}