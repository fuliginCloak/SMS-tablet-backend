import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import static spark.Spark.get;
import static spark.Spark.post;

public class SMSBackend {
    public static void main(String[] args) {

        get("/", (request, response) -> "Fuck You, World!");


        TwilioRestClient client = new TwilioRestClient.Builder(("AC7fa7c9d9f5e6e89b43a563200288b71d"),
                ("3e197da809872501163853567856b80d")).build();


        post("/sms", (request, response) -> {
            String body = request.queryParams("Body");
            String to = request.queryParams("To");
            String from = "+12058585650";

            Message message = new MessageCreator(
                    new Phone(to),
                    new PhoneNumber(from),
                    body).create(client);

            return message.getSid();
        });
    }
}