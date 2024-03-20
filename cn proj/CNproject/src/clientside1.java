import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;

public class clientside1 {

    public static void main(String[] args) {
        try {
            // Set the server's IP address and port
            String serverIp = "10.140.3.241"; // replace with your server's IP address
            int serverPort = 12345;         // replace with your server's port

            // Open the microphone for capturing audio
            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            System.out.println("Microphone opened. \nConnecting to the server...");
            System.out.println(" ");
            // Create a socket connection to the server
            Socket socket = new Socket(serverIp, serverPort);
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("Connected to the server. \nStarting audio transmission...");
            // Create a buffer for audio data
            byte[] buffer = new byte[1024];
            // Capture and send audio in a loop
            while (true) {
                int bytesRead = targetDataLine.read(buffer, 0, buffer.length);
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
