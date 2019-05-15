package pl.eti.pg;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerMain {

    private final static int port = 55000;

    public static void main(String[]args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ExecutorService service = Executors.newCachedThreadPool();
            while (true) {
                final Socket clientSocket = serverSocket.accept();
                Future<Integer> future = service.submit(() -> save(clientSocket));
//                if(future.isDone()){
//                    System.out.println("Completed!");
//                }
//                Runnable task = ()
//                Thread thread = new Thread(task);
//                thread.start();
            }
        }

//        //in - strumień wejściowy (np. z gniazda sieciowego)
//        //out - strumień wyjściowy (np. do pliku)
//        byte[] buffer = new byte[4096]; //bufor 4KB
//        int readSize;
//        while ((readSize = in.read(buffer)) != -1) {
//            out.write(buffer, 0, readSize)
//       }
    }

    private static int save(Socket clientSocket) {
        try
        {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            File file = new File(String.format("D:\\%s", in.readUTF()));
            file.createNewFile();

            FileOutputStream out = new FileOutputStream(file);

            byte[] serverBuffer = new byte[4096];
            int writeSize = 0;
            System.out.println("Saving");
            while((writeSize = in.read(serverBuffer)) > 0)
            {
                out.write(serverBuffer, 0, writeSize);
            }
            out.close();
            System.out.println("Completed!");
            return 1;


        } catch (Exception e) {
            System.out.println("Saving error");
            return 0;
        }
    }
}
