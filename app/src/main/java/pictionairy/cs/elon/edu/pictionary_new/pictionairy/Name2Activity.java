package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Name2Activity extends Activity {
private String player1Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name2);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

            player1Name = extras.getString("name1");

    }

    public void startGame(View view) {
        Intent intent = new Intent(this, Player1Word.class);
        EditText editText = (EditText) findViewById(R.id.player2_name);
        String message = editText.getText().toString();
        intent.putExtra("name1", player1Name);
        intent.putExtra("name2", message);

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_name2, menu);
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
