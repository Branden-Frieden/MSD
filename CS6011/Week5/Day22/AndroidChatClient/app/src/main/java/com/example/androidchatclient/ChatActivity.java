package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    public static ArrayList<String> messages = new ArrayList<>();
    public static ListView lv_;
    public static ArrayAdapter chatListAdapter_;
    public static String userName_ = "";
    public static String roomName_ = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            userName_ = extras.getString( "userName");
            roomName_ = extras.getString( "roomName");
        }

        TextView roomLabel = findViewById(R.id.roomLabelID);
        roomLabel.setText( roomName_ );

        chatListAdapter_ = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);

        lv_ = findViewById( R.id.ChatLV );
         lv_.setAdapter( chatListAdapter_ );
    }

    public void handleSend(View view){

        TextView userMessageView = findViewById(R.id.userMessageID);
        String userMessage = userMessageView.getText().toString();

        Login_Activity.ws_.sendText( userName_ + " " + userMessage );
    }

    public void leaveRoom(View view){
        Login_Activity.ws_.sendText( "leave " + userName_ + " " + roomName_);
        Login_Activity.ws_.disconnect();

        Intent intent = new Intent(this, Login_Activity.class);
        startActivity( intent );

    }
}