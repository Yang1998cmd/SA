import com.amazonaws.services.sqs.model.Message;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class window extends JFrame {
    private JPanel pan;
    private JTextField tField;//消息输入
    //private JTextField tField1;//获取端口号
    public static JTextArea tarea;//消息获取

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window frame = new window();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        pan = new JPanel();
        pan.setBorder(new EmptyBorder(5, 5, 10, 5));
        setContentPane(pan);
        pan.setLayout(null);

        server s = new server();
        client cl=new client();

        JButton Start = new JButton("Send");
        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                        String txt = tField.getText();
                        s.sendmess(txt);
                        s.setqueue();
                };
        });
        Start.setBounds(330, 20, 80, 37);
        pan.add(Start);

        JButton St = new JButton("Get");
        St.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.getmess(s);
                List<Message> messages=cl.messllist();
                for (Message m : messages) {
                    tarea.append(m.getBody());
                }
                cl.deletemess(s);
            };
        });
        St.setBounds(330, 140, 80, 37);
        pan.add(St);

        tField = new JTextField();//消息发送
        tField.setBounds(100, 20, 200, 80);
        pan.add(tField);
        tField.setColumns(10);

//        tField1 = new JTextField();//消息接收
//        tField1.setBounds(100, 140, 200, 80);
//        pan.add(tField1);
//        tField1.setColumns(10);

        tarea = new JTextArea();
        tarea.setBounds(100, 140, 200, 80);
        pan.add(tarea);
        tarea.setColumns(10);

        JLabel label_1 = new JLabel("消息输入：");
        label_1.setBounds(10, 18, 94, 37);
        pan.add(label_1);

        JLabel label_2 = new JLabel("消息获取：");
        label_2.setBounds(10, 128, 94, 37);
        pan.add(label_2);


    }

}




