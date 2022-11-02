package practice.lab8;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;


public class MailTest {

  static String head = "src/main/resources/";

  public static void main(String[] args) throws MessagingException, IOException {
    // read properties
    Properties props = new Properties();
    try (InputStream in = Files.newInputStream(Paths.get(head + "lab8/mail.properties"))) {
      props.load(in);
    }

    // read message info
    List<String> lines = Files.readAllLines(Paths.get(head + "lab8/message.txt"),
        StandardCharsets.UTF_8);

    String from = lines.get(0);
    String to = lines.get(1);
    String subject = lines.get(2);

    StringBuilder builder = new StringBuilder();
    for (int i = 3; i < lines.size(); i++) {
      builder.append(lines.get(i));
      builder.append("\n");
    }

    // read password for your email account
    System.out.println("Password: ");
    Scanner scanner = new Scanner(System.in);
    String password = scanner.next();

    Session mailSession = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(mailSession);
    // TODO 1: check the MimeMessage API to figure out how to set the sender, receiver, subject, and email body
    message.setFrom(from);
    message.setRecipients(RecipientType.TO, to);
    message.setSubject(subject);
    message.setText(builder.toString());
    // TODO 2: check the Session API to figure out how to connect to the mail server and send the message
    Transport transport = mailSession.getTransport();
    transport.connect(from, password);
    transport.sendMessage(message, message.getAllRecipients());
  }
}