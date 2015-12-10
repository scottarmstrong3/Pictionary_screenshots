package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Main activity for the Dot Painter.
 *
 * @author Betsey McCarthy amd Colin Hiriak
 */
public class DrawingActivity extends Activity {
    private ArrayList<Line> lines;
    private Line newLine;
    public final static int WIDTH_DIALOG = 1;
    public final static int COLOR_DIALOG = 2;
    private Point start;
    private DoodleView doodleView;
    private Button clear;
    private Dialog saveDialog;
    private TextView timerView;
    private String player2Name;
    private String player1Name;
    private String player1Word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        Bundle extras = getIntent().getExtras();
        player1Name = extras.getString("name1");
        player2Name = extras.getString("name2");
        player1Word = extras.getString("player1Word");
        doodleView = (DoodleView) findViewById(R.id.doodleview);
        clear = (Button) findViewById(R.id.clear_button);
        clear.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                doodleView.clearScreen();

            }
        });
        timerView = (TextView) findViewById(R.id.timer_text);
    }

    public void check(View view) {
        sendBitmap();
    }
private void sendBitmap(){
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    Bitmap b = screenShot(doodleView);
    b.compress(Bitmap.CompressFormat.PNG, 100, stream);
    byte[] byteArray = stream.toByteArray();
    Intent intent = new Intent(this, CheckWordActivity.class);
    intent.putExtra("name1", player1Name);
    intent.putExtra("name2", player2Name);
    intent.putExtra("player1Word", player1Word);
    intent.putExtra("drawing", byteArray);

    startActivity(intent);
}
    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Out of time!  Pass to " + player2Name)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        sendBitmap();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawing, menu);
        return true;
    }

    CountDownTimer timer = new CountDownTimer(30000, 1000) {

        public void onTick(long millisUntilFinished) {

            timerView.setText("seconds remaining: " + millisUntilFinished / 1000);
        }

        public void onFinish() {
           showAlertDialog();

        }
    }.start();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setwidth) {
            // start the Set Width dialog - pass the current pen width
            Intent intent = new Intent(this, SetWidthDialogActivity.class);
            intent.putExtra("width", doodleView.getPenWidth());
            startActivityForResult(intent, WIDTH_DIALOG);
            return true;
        }

        if (id == R.id.action_setcolor) {
            // start the Set Color dialog - pass the current pen width
            Intent intent = new Intent(this, SetColorDialogActivity.class);
            intent.putExtra("alpha", doodleView.getAlphaColor());
            intent.putExtra("red", doodleView.getRed());
            intent.putExtra("green", doodleView.getGreen());
            intent.putExtra("blue", doodleView.getBlue());
            startActivityForResult(intent, COLOR_DIALOG);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == WIDTH_DIALOG) {
            if (resultCode == RESULT_OK) {
                // get the new color and tell the DoodleView
                int width = data.getIntExtra("width", doodleView.getPenWidth());

                doodleView.setPenWidth(width);
            }
        }
        if (requestCode == COLOR_DIALOG) {
            if (resultCode == RESULT_OK) {
                int alpha = data.getIntExtra("alpha", doodleView.getAlphaColor());
                int red = data.getIntExtra("red", doodleView.getRed());
                int green = data.getIntExtra("green", doodleView.getGreen());
                int blue = data.getIntExtra("blue", doodleView.getBlue());
                doodleView.setAlpha(alpha);
                doodleView.setRed(red);
                doodleView.setGreen(green);
                doodleView.setBlue(blue);

            }
        }
    }
}
