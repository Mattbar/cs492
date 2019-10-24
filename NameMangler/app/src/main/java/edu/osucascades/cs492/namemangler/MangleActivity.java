package edu.osucascades.cs492.namemangler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MangleActivity extends AppCompatActivity {
    private static final String EXTRA_FIRST_NAME = "edu.osucascades.cs492.namemangler.first_name";
    private TextView mMangledNameTextView;
    private String mFirstName;
    private String mLastName;
    private Button mReMangleButton;
    private Button mResetButton;
    private static int REQUEST_CODE_RESET = 0;

    private String[] mLastNameBank = new String[] {
            "Esta",
            "Frier",
            "Gladis",
            "Tyger",
            "Vaider"
    };
    private int mCurrentIndex = 0;

    public static Intent newIntent(Context packageContext, String firstName){
        Intent intent = new Intent(packageContext, MangleActivity.class);
        intent.putExtra(EXTRA_FIRST_NAME, firstName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangled);



        mReMangleButton = findViewById(R.id.re_mangle_button);
        mReMangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int randIndex = rand.nextInt(mLastNameBank.length + 1);
                int lastIndex = mCurrentIndex;
                mCurrentIndex = (randIndex) % mLastNameBank.length;

                updateName();
            }
        });

        mResetButton = findViewById((R.id.reset_button));
        mResetButton. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.newIntent(MangleActivity.this, true);
                startActivityForResult(intent, REQUEST_CODE_RESET);

            }
        });


        updateName();


    }

    private void updateName(){
        mFirstName = getIntent().getStringExtra(EXTRA_FIRST_NAME);
        mMangledNameTextView = findViewById(R.id.mangled_name_text_view);
        mLastName = mLastNameBank[mCurrentIndex];
        String fullName = mFirstName + " " + mLastName;
        mMangledNameTextView.setText(fullName);
    }
}
