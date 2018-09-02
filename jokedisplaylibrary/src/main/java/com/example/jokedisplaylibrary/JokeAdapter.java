package com.example.jokedisplaylibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kundan on 03-09-2018.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView questionTV;
        TextView answerTV;
        public ViewHolder(View itemView) {
            super(itemView);
            questionTV = itemView.findViewById(R.id.question_tv);
            answerTV = itemView.findViewById(R.id.answer_tv);
        }
    }

    private Context context;
    private ArrayList<String> listJokes;

    public JokeAdapter(Context context, ArrayList<String> listJokes) {
        this.context = context;
        this.listJokes = listJokes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.joke_row_item,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String joke = listJokes.get(position);

        String[] jokeParts = joke.split("\n");
        holder.questionTV.setText(jokeParts[0].trim());
        holder.answerTV.setText(jokeParts[1].trim());
    }

    @Override
    public int getItemCount() {
        return listJokes.size();
    }
}
