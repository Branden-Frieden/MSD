package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.w3c.dom.Text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Login_Activity extends AppCompatActivity {

    public static WebSocket ws_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            ws_ = new WebSocketFactory().createSocket("ws://10.0.2.2:8080/endpoint", 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ws_.addListener( new myWebSocketAdapter() );
        ws_.connectAsynchronously();
    }

    public void handleJoin(View view){
        TextView userView = findViewById(R.id.UserNameID);
        TextView roomView = findViewById(R.id.RoomNameID);

        String userName = userView.getText().toString();
        String roomName = roomView.getText().toString();

        String joinMessage = "join " + userName + " " + roomName;
        ws_.sendText( joinMessage );

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("roomName", roomName);
        startActivity( intent );
    }


}