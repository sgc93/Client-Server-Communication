package CS_calc_and_evenOdd_UDP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPclient {
    public static void main(String[] args){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String num, resp;
                do{
                    System.out.print("Enter your number: ");
                    num = br.readLine();
                    if(num.equals("exit")){
                        System.out.println( "\033[33m" + "\033[1m" + "you exit the process!");
                        System.out.println("\033[0m");
                        break;
                    }
                    try (
                    DatagramSocket ds = new DatagramSocket(10)
                    ) {
                    byte[] cl_message = new byte[1024];
                    cl_message = num.getBytes();

                    DatagramPacket dp = new DatagramPacket(cl_message, cl_message.length, InetAddress.getLocalHost(), 4000);
                        ds.send(dp);
                        // accept the message from the server
                        byte[] server_mes = new byte[1024];
                        DatagramPacket dp_r = new DatagramPacket(server_mes, server_mes.length);
                        ds.receive(dp_r);

                        resp = new String(dp_r.getData(), 0, dp_r.getLength());
                        // print the result
                        System.out.println(resp);
                        System.out.println("\033[0m");
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } while(true);
            } catch(Exception e) {
                e.printStackTrace();
            }
    }
}