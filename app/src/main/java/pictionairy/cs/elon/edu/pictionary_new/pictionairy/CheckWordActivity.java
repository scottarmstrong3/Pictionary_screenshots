package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;

/**
 *
 *
 * @author Betsey McCarthy amd Colin Hiriak
 */
public class CheckWordActivity extends Activity {
    private ImageView view;
    private Line newLine;
    public final static int WIDTH_DIALOG = 1;
    public final static int COLOR_DIALOG = 2;
    private Point start;
    private Button clear;
    private Dialog saveDialog;
    private TextView timerView;
    private String player1Word;
    private String player1Name;
    private String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // checkWordView = (CheckWordView) findViewById(R.id.check_word_view);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_word);
        Bundle extras = getIntent().getExtras();
        player1Name = extras.getString("name1");
        player2Name = extras.getString("name2");
        player1Word = extras.getString("player1Word");
        byte[] byteArray = getIntent().getByteArrayExtra("drawing");
        Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Bitmap workingBitmap = Bitmap.createBitmap(b);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
        ImageView img = (ImageView)findViewById(R.id.image_view);
        img.setImageBitmap(mutableBitmap);
    }
    private void showCorrectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("BITCH U GUESSED IT " + player2Name)
                .setCancelable(false)
                .setPositiveButton("YOU WAS MOTHERFUCKING RIGHT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //start next activity
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void showWrongDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("YOU FUCKED UP " )
                .setCancelable(false)
                .setPositiveButton("YOU WAS MOTHERFUCKING RIGHT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //start next activity
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void check(View view) {
        Intent intent = new Intent(this, CheckWordActivity.class);
        EditText editText = (EditText) findViewById(R.id.player2_name);
        String input = editText.getText().toString();
        if(input.equalsIgnoreCase(player1Word)){
            showCorrectDialog();
            //increment score here
        }
        else{
            showWrongDialog();
        }
        startActivity(intent);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
