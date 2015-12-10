package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;



/**
 * Draw lines to the screen on touches.
 *
 * @author Betsey McCarthy and Colin Hiriak - Fall 2015
 */
public class DoodleView extends View {
    private Path path;

    private Canvas canvas;
    private Paint paint;
    public final static int DEFAULT_WIDTH = 25;
    public final static int DEFAULT_RED = 200;
    public final static int DEFAULT_ALPHA = 255;
    public final static int DEFAULT_GREEN = 25;
    public final static int DEFAULT_BLUE = 25;
    private ArrayList<Line> lines;

    private int penWidth = DEFAULT_WIDTH;
    private int alpha = DEFAULT_ALPHA;
    private int red = DEFAULT_RED;
    private int green = DEFAULT_GREEN;
    private int blue = DEFAULT_BLUE;
    private float x0, y0, x1, y1;
    private Line newLine;

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        lines = new ArrayList<Line>();
        paint = new Paint();
        path = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x0 = event.getX();
            y0 = event.getY();

        } else  {
            x1 = event.getX();
            y1 = event.getY();
            paint.setARGB(alpha, red, green, blue);
            newLine = new Line(x0,y0,x1,y1, paint, penWidth);
            lines.add(newLine);
            x0= x1;
            y0=y1;
        }

        return true;

    }

public ArrayList<Line> getLines(){
    return this.lines;
}
    @Override
    protected void onDraw(Canvas canvas) {

        for(Line line : lines){

            line.draw(canvas);
        }
        invalidate();

    }

    public int getAlphaColor(){
        return alpha;
    }
    public int getRed()
    {
        return red;
    }
    public int getGreen() {

        return green;
    }
    public int getBlue()
    {
        return blue;
    }
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    public void setPenWidth(int penWidth) {
        this.penWidth = penWidth;
    }

    public void clearScreen(){

        lines.clear();
    }
    public int getPenWidth() {

        return penWidth;
    }
}