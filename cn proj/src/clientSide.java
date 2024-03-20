import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;

public class clientSide {

    public static void main(String[] args) {
        try {
            // Set the server's IP address and port
            String serverIp = "192.168.1.11"; // replace with your server's IP address
            int serverPort = 12345;         // replace with your server's port

            // Open the microphone for capturing audio
            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            // Create a socket connection to the server
            Socket socket = new Socket(serverIp, serverPort);
            OutputStream outputStream = socket.getOutputStream();

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
