package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player1Word extends Activity {

    private Words words;
    private String player2Name;
    private String player1Name;
    private String player1Word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player1_word);
        Bundle extras = getIntent().getExtras();

        player1Name = extras.getString("name1");
        player2Name = extras.getString("name2");

        words = new Words(getBaseContext());

        TextView editText = (TextView) findViewById(R.id.player1_get_word);
        editText.setText(player1Name);

        TextView player1WordText =  (TextView)findViewById(R.id.player1_set_word);
        player1WordText.setText(words.getWord());
        player1Word = (String)player1WordText.getText();
        System.out.println(player1Word);
    }

    public void startDraw(View view) {
        Intent intent = new Intent(this, DrawingActivity.class);
        intent.putExtra("name1", player1Name);
        intent.putExtra("name2", player2Name);
        intent.putExtra("player1Word", player1Word);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player1_word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
