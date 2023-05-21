package CS_calc_and_evenOdd_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPserver{

    private static final int PORT = 4000;

    public static void main(String[] args){
        try(DatagramSocket ds = new DatagramSocket(PORT)){
            // server is on now
            do {
                byte[] b = new byte[1024];
                DatagramPacket dp = new DatagramPacket(b, b.length); 
                ds.receive(dp);  // recieve the client message
                String str = new String(dp.getData(), 0,dp.getLength());
                String response = "Even";
                try {
                    if( str.equals("exit")){
                        System.out.println("Server Closed!");
                        break;
                    }
                    int n = Integer.parseInt(str);
                    if(n % 2 == 0){
                        response = "\033[32m" + "\033[1m" + "your number is Even.";
                    } else {
                        response = "\033[32m" + "\033[1m" + "your number is Odd.";
                    }
                } catch(NumberFormatException e){
                    response = "\033[31m" + "\033[1m" + "You have a non-numeric charactor in your message.";                
                } 
                // sending the operated result to the client
                byte[] rb = new byte[1024];
                rb = response.getBytes();
                DatagramPacket dp_r= new DatagramPacket(rb, rb.length, InetAddress.getLocalHost(),10);
                ds.send(dp_r);
            } while(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}