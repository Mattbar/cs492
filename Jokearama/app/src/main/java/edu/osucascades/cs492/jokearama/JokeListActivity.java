package edu.osucascades.cs492.jokearama;

import androidx.fragment.app.Fragment;

public class JokeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new JokeListFragment();
    }
}
