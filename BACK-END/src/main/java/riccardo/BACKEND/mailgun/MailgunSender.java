package riccardo.BACKEND.mailgun;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import riccardo.BACKEND.entities.User;

@Component
public class MailgunSender {

    private String apiKey;
    private String domainName;

    @Value("${mailgun.email}")
    private String email;

    public MailgunSender (@Value("${mailgun.apikey}") String apiKey, @Value("${mailgun.domainname}") String domainName){
        this.apiKey = apiKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail (User user){
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/"+ this.domainName + "/messages")
                    .basicAuth("api", this.apiKey)
                    .queryString("from", this.email)
                    .queryString("to", user.getEmail())
                    .queryString("subject", "Registration Complete!")
                    .queryString("text", "Congratulations " + user.getName() + " for registering!")
                    .asJson();
            System.out.println(response.getBody());
        } catch (Exception ex){
            System.out.println("An error occurred while sending the email: " + ex.getMessage());
        }

    }
}
