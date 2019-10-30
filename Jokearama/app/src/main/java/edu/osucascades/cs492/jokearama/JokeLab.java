package edu.osucascades.cs492.jokearama;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JokeLab {
    private  static JokeLab sJokeLab;
    private Map<UUID,Joke> mJokes;

    public static JokeLab get(Context context){
        if (sJokeLab == null) {
            sJokeLab  = new JokeLab(context);
        }
        return sJokeLab;
    }

    private JokeLab(Context context){
        mJokes = new LinkedHashMap<>();
        for(int i = 0; i < 100; i++){
            Joke joke = new Joke();
            joke.setTitle("Joke #" + i);
            joke.setSetUp("Set Up " + i);
            joke.setPunchLine("PunchLine: " + i);
            mJokes.put(joke.getId(), joke);
        }
    }

    public List<Joke> getJokes(){
        return new ArrayList<>(mJokes.values());
    }

    public Joke getJoke(UUID id) {
        return mJokes.get(id);
    }
}
