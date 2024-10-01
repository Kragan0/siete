package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {

    private Socket socket;
    private InputStream input;
    private OutputStream output;
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        this.socket = new Socket(host, port);
        
        input = this.socket.getInputStream();
        output = this.socket.getOutputStream();

    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException, InterruptedException {
        

        int len;
        String message;
        byte[] buffer;
        final byte[] response = new byte[1024];
        message = "EHLO a_geleta@utb.cz\r\n";
        buffer = message.getBytes(); 
        output.write(buffer, 0, buffer.length);
        output.flush();
        
        Thread.sleep(500);

        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "MAIL FROM:<" + from + ">\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);

        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "RCPT TO:<"+ to + ">\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);

        if (input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "DATA\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);

        if (input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "Subject: " + subject + "\r\n" + "\r\n" + text + "\r\n.\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);

        if (input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
    }
}

    /*
     * Sends QUIT and closes the socket
     */
    public void close() throws IOException, InterruptedException {
        
        String message;
        byte[] buffer;
        final byte[] response = new byte[1024];
        
        int len;
        message = "QUIT\r\n" ;        
        buffer = message.getBytes();

        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if (input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
    }
        this.socket.close();
    }
}
