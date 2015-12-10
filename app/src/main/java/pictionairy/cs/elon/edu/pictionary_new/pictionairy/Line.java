package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by emccarthy3 on 12/1/2015.
 */
public class Line {
    private float x0, y0, x1, y1;
    private float width;
    private Paint paint;
    public Line (float x0, float y0, float x1, float y1, Paint p, float width){
        this.x0 = x0;
        this.y0 =y0;
        this.x1 = x1;
        this.y1 = y1;
        this.paint = new Paint();
        this.paint.setColor(p.getColor());
        this.width = width;
    }

    public void setX0(float x0){
        this.x0 =x0;

    }
    public void setY0(float y0){
        this.y0 =y0;

    }
    public void setX1(float x1){
        this.x1 =x1;

    }
    public void setY1(float y1){
        this.y1 =y1;

    }
    public void draw(Canvas canvas) {

        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(width);

        canvas.drawLine(x0,y0,x1,y1,paint);

    }

}

