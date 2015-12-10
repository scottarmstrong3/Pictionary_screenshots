package pictionairy.cs.elon.edu.pictionary_new.pictionairy;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by emccarthy3 on 11/19/15.
 */


public class Words {
    private ArrayList<String> words;
    public Words (Context context)  {


        words = new ArrayList<String>();

        // open the file and read the words into the list
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            context.getAssets().open("dictionary.txt"), "UTF-8"));

            // read the words
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    public String getWord(){
        String word = words.get((int)(Math.random()*words.size()));
        return word;
    }


}
