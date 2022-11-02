package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean firstClick_ = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void handleClick( View view ){
        Log.i( "Dd:MainActivity", "button was pressed");

        if( firstClick_ ) {
            TextView roomNameTV = findViewById(R.id.roomNameTxt);
            roomNameTV.setText("Enter a Room Name");
            firstClick_ = false;
        }
        else{
            // switch to 2nd activity
            Intent intent = new Intent( this, AnotherActivity.class);
            startActivity( intent );

        }


    }
}