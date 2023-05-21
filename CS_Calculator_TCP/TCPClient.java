package CS_Calculator_TCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    private static final String ADDRESS = "localhost";
    private static final int PORT = 4000;

    public static void main(String[] args){
        try (
            Socket con = new Socket(ADDRESS, PORT);
        ){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            PrintWriter out = new PrintWriter(con.getOutputStream(), true);

            String message, response;
            do {
                System.out.println("------------------------------------------------");
                System.out.println("\033[32m" + "\033[1m");
                System.out.println("Inter your mathematical expression like 23 + 43");
                System.out.println("Don't forget to use a space to separate each term, i.e numbers and operaor should be separated with space.");
                System.out.println("\033[0m");
                System.out.println("------------------------------------------------");
                System.out.print("Question: ");
                message = br.readLine();
                out.println(message);
                if(message.equals("stop")){
                    System.out.println("you have just exit the process!");
                    break;
                }

                response = in.readLine();
                System.out.println(response); // print the response red and bold
                System.out.print("\033[0m");  // reset the font of the console
            } while(true);
            br.close();
            in.close();
            out.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
