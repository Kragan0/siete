package utb.fai;

public class App {  

    public static void main(String[] args) {
        // TODO: Implement input parameter processing
        String stmp = args[0];
        int port = Integer.parseInt(args[1]);
        String from = args[2];
        String to = args[3];
        String subject = args[4];
        String text = args[5];
   
        try {
            EmailSender sender = new EmailSender(stmp, port);
            sender.send(from, to, subject, text);
            sender.close();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
