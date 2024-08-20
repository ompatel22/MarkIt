package com.techblax.techblaxemailsender;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.techblax.techblaxemailsender.util.Appdata;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name_,title_,text_;
    Button btn_send;

    String n_,t_,tt_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_ui();
    }

    private void init_ui() {
        name_ = findViewById(R.id.ed_name);
        title_ = findViewById(R.id.ed_title);
        text_ = findViewById(R.id.ed_text);
        btn_send = findViewById(R.id.btn_send_email);
        btn_send.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_email:
                n_=name_.getText().toString();
                t_=title_.getText().toString();
                tt_=text_.getText().toString();
                send_email(n_,t_,tt_);
                break;
            default:
        }
    }

    private void send_email(String n_, String t_, String tt_) {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", Appdata.Gmail_Host);
        properties.put("mail.smtp,port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Appdata.Sender_Email_Address,Appdata.Sender_Email_Password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(Appdata.Reciver_Email_Address));
            message.setSubject(t_);
            message.setText("From: "+n_+"\n"+"Text: "+tt_);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.start();


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}