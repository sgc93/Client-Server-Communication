package CS_Calculator_TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int PORT = 4000;
    public static void main(String[] args){
        try{
            try (ServerSocket server = new ServerSocket(PORT)) {
                System.out.println("waiting for client ... ");
                Socket con = server.accept();   // connecting
                System.out.println("connection created with client!");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // from the  client
                PrintWriter out = new PrintWriter(con.getOutputStream(), true); // to the clinet 
                // declare varivables
                String message, response;
                double num1, num2;
                char operator;

                do {
                    message = in.readLine();
                    if(message.equals("stop")){
                        System.out.println("server closed!");
                        break;
                    }
                    String[] splited = message.split(" ");
                    try {
                        num1 = Double.parseDouble(splited[0]);
                        num2 = Double.parseDouble(splited[2]);
                        operator = splited[1].charAt(0);  
                        switch(operator){
                            case '+':
                            response = Double.toString(num1 + num2);
                            break;
                            case '-':
                            response = Double.toString(num1 - num2);
                            break;
                            case '/':
                            response = Double.toString(num1 / num2);
                            break;
                            case '*':
                            response = Double.toString(num1 * num2);
                            break;
                            default:
                            response = "\033[31m" + "\033[1m" + "UNclear operator";
                            break;
                        }
                        out.println("\033[33m" + "\033[1m" + "Answer: " + response);
                    } catch(Exception e){
                        out.println("\033[31m" + "\033[1m" + "You have sent incorrect expression!");
                    }
                } while(true);
                in.close();
                out.close();
                con.close();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
