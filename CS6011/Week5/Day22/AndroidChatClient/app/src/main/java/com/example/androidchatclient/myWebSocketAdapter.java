package com.example.androidchatclient;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;

import java.util.List;
import java.util.Map;

public class myWebSocketAdapter extends WebSocketAdapter{


    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {

    }

    @Override
    public void onConnectError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
        //Login_Activity.ws_.sendText( "leave " + ChatActivity.userName_ + " " + ChatActivity.roomName_ );
        //ChatActivity.messages.add( "trouble connecting to server ");
    }

    @Override
    public void onTextMessage(WebSocket websocket, String s) throws Exception {

        String[] words = s.split(" ");
        String outputMessage = "";

        if(words[1].equals(":\"join\",")){
            int start = s.indexOf("\"user\" :") + 9;
            int end = s.indexOf("\",\"room\" :");
            String fullName = s.substring(start,end);
            outputMessage = fullName + " joined the room!";
        }
        else if(words[1].equals(":\"leave\",")){
            int start = s.indexOf("\"user\" :") + 9;
            int end = s.indexOf("\",\"room\" :");
            String fullName = s.substring(start,end);
            outputMessage = fullName + " left the room!";
        }
        else {
            int start = s.indexOf("\"user\" :") + 9;
            int end = s.indexOf("\",\"room\" :");
            String fullName = s.substring(start,end);

            start = s.indexOf("\"message\" :") + 12;
            end = s.length() - 1;
            String message = s.substring(start, end);

            outputMessage = fullName + ": " + message;
        }

        ChatActivity.messages.add( outputMessage );

        ChatActivity.lv_.post(new Runnable() {
            @Override
            public void run() {
                ChatActivity.chatListAdapter_.notifyDataSetChanged();
                ChatActivity.lv_.smoothScrollToPosition( ChatActivity.chatListAdapter_.getCount() );
            }
        });


    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    @Override
    public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {

    }

    @Override
    public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

    }
}
