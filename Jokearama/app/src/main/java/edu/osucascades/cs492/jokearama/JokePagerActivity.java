package edu.osucascades.cs492.jokearama;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class JokePagerActivity extends AppCompatActivity {
    private static final String EXTRA_JOKE_ID = "edu.osucascades.cs492.jokearama.joke_id";

    private ViewPager mViewPager;
    private TextView mJokesTotal;
    private TextView mCompleted;
    private int mTotalCompleted = 0;
    private List<Joke> mJokes;

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, JokePagerActivity.class);
        intent.putExtra(EXTRA_JOKE_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_JOKE_ID);

        mViewPager = (ViewPager) findViewById(R.id.joke_view_pager);
        mJokes = JokeLab.get(this).getJokes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Joke joke = mJokes.get(position);
                return JokeFragment.newInstance(joke.getId());
            }

            @Override
            public int getCount() {
                return mJokes.size();
            }
        });



        for (int i = 0; i < mJokes.size(); i++){
            if(mJokes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateCompleted();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

        mJokesTotal = (TextView) findViewById(R.id.Jokes_total);
        mJokesTotal.append(mJokes.size() + " jokes");
         updateCompleted();


    }

    private void updateCompleted(){
        getTotalComplet();
        mCompleted = findViewById(R.id.Jokes_completed);
        mCompleted.setText(mTotalCompleted + " completed");
    }

    private void getTotalComplet() {
        mTotalCompleted = 0;
        for(int i = 0; i < mJokes.size(); i++){
            if (mJokes.get(i).isFinished()){
                mTotalCompleted++;
            }
        }
    }
}
