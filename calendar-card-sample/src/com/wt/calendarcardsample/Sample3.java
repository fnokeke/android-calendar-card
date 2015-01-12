package com.wt.calendarcardsample;

import android.app.Activity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.wt.calendarcard.CalendarCardList;
import com.wt.calendarcard.model.Note;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Sample3 extends Activity {

    private CalendarCardList calendarList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample3);
        init();
    }

    private void init() {
        calendarList = (CalendarCardList) findViewById(R.id.calendarList);
        try {
            InputStream is = getAssets().open("notes.json");
            String jsonStr = parseStringFromInputStream(is);
            Note[] notes = new Gson().fromJson(jsonStr, Note[].class);
            calendarList.setDataSource(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseStringFromInputStream(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}

