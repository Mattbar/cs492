package edu.osucascades.cs492.jokearama;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class JokeListFragment extends Fragment {
    private RecyclerView mJokeRecyclerView;
    private JokeAdapter mAdapter;
    private int mClickedItemPos;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_joke_list, container, false);

        mJokeRecyclerView = view.findViewById(R.id.joke_recycler_view);
        mJokeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        JokeLab jokeLab = JokeLab.get(getActivity());
        List<Joke> jokes = jokeLab.getJokes();
        if(mAdapter == null){
            mAdapter = new JokeAdapter(jokes);
            mJokeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyItemChanged(mClickedItemPos);
        }
    }

    private class JokeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Joke mJoke;

        public JokeHolder(LayoutInflater inflater, ViewGroup parent, int layoutID) {
            super(inflater.inflate(layoutID, parent, false));

            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.joke_title);

        }

        public void bind(Joke joke){
            mJoke = joke;
            mTitleTextView.setText(mJoke.getTitle());
            if(mJoke.isFinished()){
                itemView.setBackgroundColor(Color.GRAY);
            } else {
                itemView.setBackgroundColor(Color.WHITE);
            }

        }

        @Override
        public void onClick(View view){
            mClickedItemPos = getAdapterPosition();
            Intent intent = JokePagerActivity.newIntent(getActivity(), mJoke.getId());
            startActivity(intent);
        }
    }

    private class JokeAdapter extends RecyclerView.Adapter<JokeHolder> {
        private List<Joke> mJokes;

        public JokeAdapter(List<Joke> jokes){
            mJokes = jokes;
        }

        @Override
        public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JokeHolder(layoutInflater, parent, viewType);
        }

        @Override
        public void onBindViewHolder(JokeHolder holder, int position) {
            Joke crime = mJokes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mJokes.size();
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.list_item_joke;

        }
    }


}
