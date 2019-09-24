package sqs.client;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Client {

    public Client(){
        this.queueUrl = System.getenv("CLIENT_URL");
    }
    private String queueUrl;
    public boolean getMessages(){
        System.out.println(queueUrl);
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        for(int i = 0; i < messages.size(); i ++){
            Message message = messages.get(i);
            System.out.println(message);
            sqs.deleteMessage(queueUrl, message.getReceiptHandle());
        }
        return false;
    }
}
