import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.ArrayList;
import java.util.List;

public class client {
    private  List<Message> messages;
    public client(){
       messages=new ArrayList<Message>();
    };
    public void getmess(server s)
    {
        ReceiveMessageRequest receive_request = new ReceiveMessageRequest()
                .withQueueUrl(s.getQueueUrl())
                .withWaitTimeSeconds(1)
                .withMaxNumberOfMessages(5);
        messages = s.getsqs().receiveMessage(receive_request).getMessages();
    }

    public List<Message> messllist()
    {
        return messages;
    }

    public void deletemess(server s)
    {
        for (Message m : messages) {
//            System.out.println(m.getBody());
            s.getsqs().deleteMessage(s.getQueueUrl(), m.getReceiptHandle());
        }
    }

    public static void main(String[] args)
    {
        client one=new client();
    }

}
