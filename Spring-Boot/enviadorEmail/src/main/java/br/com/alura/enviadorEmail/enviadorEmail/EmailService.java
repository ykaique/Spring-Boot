package br.com.alura.enviadorEmail.enviadorEmail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviar(String nome, String emailDestinatario) {

        try{

            Email email = new SimpleEmail();
            email.setHostName("smtp:gmail.com");
            email.setSmtpPort(587);
            email.setAuthentication("ygorkaique@gmail.com", "Yp9357**");
            email.setSSLOnConnect(true);

            email.setFrom("ygorkaique@gmail.com");
            email.setSubject("Voce foi convidado pelo ListaVIP");
            email.setMsg("Ola " + nome + ". Voce acaba de ser conviado pelo ListaVIP");
            email.addTo(emailDestinatario);
            email.send();

        } catch (EmailException e){
            e.printStackTrace();
        }

    }

}
