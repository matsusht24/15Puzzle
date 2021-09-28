package com.example.a15squares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainView mainView = (MainView) findViewById(R.id.mainView);
        MainController mainController = new MainController(mainView);
        mainController.randomizer();
        //create buttons
        Button resetButton = (Button) findViewById(R.id.resetButton);
        Button decreaseButton = (Button) findViewById(R.id.decreaseButton);
        Button increaseButton = (Button) findViewById(R.id.increaseButton);
        //set on click listener
        decreaseButton.setOnClickListener(mainController);
        increaseButton.setOnClickListener(mainController);
        resetButton.setOnClickListener(mainController);
        //set on touch listener
        mainView.setOnTouchListener(mainController);
    }
}