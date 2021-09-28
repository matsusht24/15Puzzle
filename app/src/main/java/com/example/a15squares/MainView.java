package com.example.a15squares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class MainView extends SurfaceView {
    //creates instance variables
    private Paint insidePaint;
    private Paint outlinePaint;
    private Paint numPaint;
    private MainModel myModel;
    private Paint errorPaint;
    private Paint winPaint;
    //constructor to set values for instance variables
    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        insidePaint = new Paint();
        outlinePaint = new Paint();
        errorPaint = new Paint();
        winPaint = new Paint();
        insidePaint.setARGB(255,255,255,255);
        outlinePaint.setARGB(255,0,0,0);
        errorPaint.setARGB(255,255,0,0);
        winPaint.setARGB(255,0,255,0);
        numPaint = new Paint(outlinePaint);
        numPaint.setTextSize(90);
        numPaint.setFakeBoldText(true);
        myModel = new MainModel();

    }
    //getter method for the model
    public MainModel getMyModel() {
        return myModel;
    }

    protected void onDraw(Canvas canvas){
        //if the they touched out of bounds - flash red
        if(myModel.error){
            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), errorPaint);

            myModel.error = false;

        }
        if(myModel.win){
            canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), winPaint);

        }
        //This nested for loop will create the board
        for(int i = 1; i < myModel.boardSize+1; i++){
            for(int j = 1; j < myModel.boardSize+1; j++){
                canvas.drawRect(j*myModel.outerSquareSize, i*myModel.outerSquareSize,(j+1)*myModel.outerSquareSize, (i+1)*myModel.outerSquareSize, outlinePaint);
                canvas.drawRect(j*myModel.outerSquareSize + 10, i*myModel.outerSquareSize +10, (j+1)*myModel.outerSquareSize - 10, (i+1)*myModel.outerSquareSize - 10, insidePaint);
            }
        }
        //This nested for loop will fill the boxes with their corresponding number
        for(int i = 0; i< myModel.boardSize; i++){
            for(int j = 0; j < myModel.boardSize; j++){
                Numbers curNum = myModel.numberArray[i][j];
                if(curNum.getNum() != myModel.totalBoxes) {
                    canvas.drawText(curNum.getNumString(), curNum.getPosX(), curNum.getPosY(), numPaint);
                }

            }
        }


    }
}
