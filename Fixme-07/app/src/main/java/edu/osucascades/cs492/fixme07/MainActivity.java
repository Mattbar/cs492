package edu.osucascades.cs492.fixme07;

import androidx.fragment.app.Fragment;

public class MainActivity extends /*AppCompatActivity*/ SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
