import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import java.util.Date;
import java.util.List;

public class server
{
    private String queuename = "yymess";
    private AmazonSQS sqs;


    public server()
    {
        sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
    }

    public void changequeuename(String s)
    {
        queuename=s;
    }

    public  void createqueue()
    {
                try {
            CreateQueueResult create_result = sqs.createQueue(queuename);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
    }

    public String getQueueUrl()
    {
        String queueUrl;
        queueUrl = sqs.getQueueUrl(queuename).getQueueUrl();
        return queueUrl ;
    }

    public AmazonSQS getsqs()
    {
        return sqs ;
    }

    public void sendmess(String s)
    {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(getQueueUrl())
                .withMessageBody(s)
                .withDelaySeconds(1);
        sqs.sendMessage(send_msg_request);
    }

    public void sendmessgs(String a,String b)
    {
        SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest()
                .withQueueUrl(getQueueUrl())
                .withEntries(
                        new SendMessageBatchRequestEntry(
                                "msg_1", a),
                        new SendMessageBatchRequestEntry(
                                "msg_2", b)
                                .withDelaySeconds(10));
        sqs.sendMessageBatch(send_batch_request);
    }

    public void setqueue()
    {
        SetQueueAttributesRequest set_attrs_request = new SetQueueAttributesRequest()
                .withQueueUrl(getQueueUrl())
                .addAttributesEntry("ReceiveMessageWaitTimeSeconds", "1");
        sqs.setQueueAttributes(set_attrs_request);
    }


    public static void main(String[] args)
    {
        server one=new server();
        one.sendmess("你好！！！");

    }
}
