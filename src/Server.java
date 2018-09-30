import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Server {
    private static final int port = 4444;
    private static ServerSocket ss = null;
    private static DataInputStream  input   = null;
    private static DataOutputStream out     = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        runServer();
    }

    private static void  runServer() throws IOException, ClassNotFoundException{
        ss = new ServerSocket(port);
        System.out.println("System is ready to accept connections");
        Socket socket = ss.accept();
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

        // sends output to the socket
        out    = new DataOutputStream(socket.getOutputStream());
        input  = new DataInputStream(System.in);

        order o = (order) is.readObject();
//        doSomething(m);
        Semaphore sem = new Semaphore(1);
        Shared shared_obj = new Shared();

        // creating two threads with name A and B
        // Note that thread A will increment the count
        // and thread B will decrement the count
        MyThread mt1 = new MyThread(sem, "A",o,shared_obj);
        mt1.start();
        shared_obj = mt1.shared_obj;
        String out = mt1.output;
        System.out.println(out);
//        out.writeUTF(mt1.output);
////        os.writeObject(o);
//        socket.close();

        os.writeObject(out);
        socket.close();
    }

}