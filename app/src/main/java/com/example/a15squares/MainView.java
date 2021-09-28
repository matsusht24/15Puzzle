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
    private Paint textPaint;


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
        textPaint = new Paint(insidePaint);
        numPaint.setTextSize(90);
        numPaint.setFakeBoldText(true);
        textPaint.setTextSize(50);
        textPaint.setFakeBoldText(true);
        errorPaint.setTextSize(90);
        errorPaint.setFakeBoldText(true);
        winPaint.setTextSize(180);
        winPaint.setFakeBoldText(true);
        myModel = new MainModel();

    }
    //getter method for the model
    public MainModel getMyModel() {
        return myModel;
    }

    protected void onDraw(Canvas canvas){
        //if the they touched out of bounds - display on screen error
        if(myModel.error && !myModel.win){
            canvas.drawText("Error", canvas.getWidth() - 800, canvas.getHeight() - 500, errorPaint);
            myModel.error = false;
        }
        //When you win display on screen You Win
        if(myModel.win){
            canvas.drawText("You Win!", canvas.getWidth() - 800, canvas.getHeight() - 800, winPaint);

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
        //Displays for the user the current board size in the bottom left corner
        canvas.drawText(("The current board is a " + myModel.boardSize +"x" + myModel.boardSize), canvas.getWidth() - 800, canvas.getHeight() - 50, textPaint );


    }
}
