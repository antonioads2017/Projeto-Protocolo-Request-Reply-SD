import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServer {

    //variável estatica ServerSocket
    private static ServerSocket server;
    //porta que o server vai utilizar
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //objeto de serversocket instanciado
        server = new ServerSocket(port);
        //repeticao para quando receber o comando de "exit"
        while(true){
            System.out.println("Esperando a requisição do cliente");
            //criando e esperando a requisicao do cliente
            Socket socket = server.accept();
            //ler do socket para o objeto ObjectInputStream
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //converter ObjectInputStream para String
            String message = (String) ois.readObject();
            System.out.println("Messagem Recebida: " + message);
            //criando objeto ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //escrevendo o objeto no Socket
            oos.writeObject("Olá Cliente "+message);
            //fechando recursos
            ois.close();
            oos.close();
            socket.close();
            //encerre o servidor se o cliente enviar um comando de exit
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Desligando o servidor SocketServer!!");
        //fechando o objeto ServerSocket
        server.close();
    }

}