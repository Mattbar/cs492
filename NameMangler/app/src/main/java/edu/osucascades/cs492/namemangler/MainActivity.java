package edu.osucascades.cs492.namemangler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String EXTRA_RESET_CODE = "edu.osucascades.cs492.namemangler.reset_code";
    private Button mMangleButton;
    private String mFirstName;
    private boolean mResetCode = false;
    private static final int REQUEST_CODE_MANGLE = 0;

    public static Intent newIntent(Context packageContext, boolean resetCode){
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_RESET_CODE, resetCode);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResetCode = getIntent().getBooleanExtra(EXTRA_RESET_CODE, false);
        if(mResetCode){
            resetName();
        }

        mMangleButton = findViewById(R.id.mangle_button);
        mMangleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText name = (EditText)findViewById(R.id.firstName_text_input);
                if(name.getText().length() == 0){
                    launchToast();
                    return;
                }

                mFirstName = name.getText().toString();
                Intent intent = MangleActivity.newIntent(MainActivity.this, mFirstName);
                startActivityForResult(intent, REQUEST_CODE_MANGLE);



            }
        });
    }

    private void resetName(){
        EditText name = findViewById(R.id.firstName_text_input);
        name.getText().clear();
    }

    private void launchToast(){
        Toast empty_name_toast = Toast.makeText(this, R.string.missing_name, Toast.LENGTH_LONG);
        empty_name_toast.setGravity(Gravity.TOP, 0,0);
        empty_name_toast.show();
    }
}
