package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by emccarthy3 on 9/14/2015.
 */
public class SetColorDialogActivity extends Activity {
    private SeekBar aSeekBar, rSeekBar, gSeekBar;
    private SeekBar bSeekBar;
    private int seekA, seekR, seekG, seekB;
    private ImageView imageView;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_color_dialog);
        Intent data = getIntent();
        int alpha = data.getIntExtra("alpha", DoodleView.DEFAULT_ALPHA);
        int red = data.getIntExtra("red", DoodleView.DEFAULT_RED);
        int green = data.getIntExtra("green", DoodleView.DEFAULT_GREEN);
        int blue = data.getIntExtra("blue", DoodleView.DEFAULT_BLUE);


        aSeekBar = (SeekBar) findViewById(R.id.alpha_seekbar);
        rSeekBar = (SeekBar) findViewById(R.id.red_seekbar);
        gSeekBar = (SeekBar) findViewById(R.id.green_seekbar);
        bSeekBar = (SeekBar) findViewById(R.id.blue_seekbar);
        rSeekBar.setProgress(red);
        aSeekBar.setProgress(alpha);
        gSeekBar.setProgress(green);
        bSeekBar.setProgress(blue);
        aSeekBar.setOnSeekBarChangeListener(aSeekBarListener);
        rSeekBar.setOnSeekBarChangeListener(rSeekBarListener);
        gSeekBar.setOnSeekBarChangeListener(gSeekBarListener);
        bSeekBar.setOnSeekBarChangeListener(bSeekBarListener);
        imageView = (ImageView) findViewById(R.id.color_imageView);
        updateImageView();

    }


    SeekBar.OnSeekBarChangeListener aSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateImageView();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    SeekBar.OnSeekBarChangeListener rSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateImageView();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    SeekBar.OnSeekBarChangeListener gSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateImageView();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    SeekBar.OnSeekBarChangeListener bSeekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateImageView();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
    void updateImageView() {
        // draw a white circle the size of the seekBar
        Paint p = new Paint();

        p.setARGB(aSeekBar.getProgress(), rSeekBar.getProgress(), gSeekBar.getProgress(), bSeekBar.getProgress());
        Bitmap bitmap = Bitmap.createBitmap(1000, 250, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0,0, 1000,1000, p);



        imageView.setImageBitmap(bitmap);
    }


    private void doSomethingWithColor() {
        //int color = Color.set(seekA, seekR, seekG, seekB);
        // Do something with color
    }

    public void onCancelClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onOKClick(View view) {
        // send back the new width size
        Intent data = new Intent();
        data.putExtra("alpha", aSeekBar.getProgress());
        data.putExtra("red", rSeekBar.getProgress());
        data.putExtra("green", gSeekBar.getProgress());
        data.putExtra("blue", bSeekBar.getProgress());
        setResult(RESULT_OK, data);
        finish();
    }
}

