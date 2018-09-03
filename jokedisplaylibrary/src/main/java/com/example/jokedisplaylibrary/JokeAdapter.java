package com.example.jokedisplaylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kundan on 03-09-2018.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    // Using material 700 colors
    private static String TYPE_COLOR = "700";

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView questionTV;
        TextView answerTV;
        ConstraintLayout parentCL;

        ViewHolder(View itemView) {
            super(itemView);
            questionTV = itemView.findViewById(R.id.question_tv);
            answerTV = itemView.findViewById(R.id.answer_tv);
            parentCL = itemView.findViewById(R.id.row_item);
        }
    }

    private Context context;
    private ArrayList<String> listJokes;

    JokeAdapter(Context context, ArrayList<String> listJokes) {
        this.context = context;
        this.listJokes = listJokes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.joke_row_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String joke = listJokes.get(position);

        String[] jokeParts = joke.split("\n");
        holder.questionTV.setText(jokeParts[0].trim());
        holder.answerTV.setText(jokeParts[1].trim());
        holder.parentCL.setBackgroundColor(getMatColor(TYPE_COLOR));
    }

    @Override
    public int getItemCount() {
        return listJokes.size();
    }

    /**
     * @param typeColor takes the pallete ID
     * @return a random color from a list of colors
     */
    private int getMatColor(String typeColor) {
        int returnColor = Color.BLACK;
        int arrayId = context.getResources().getIdentifier("mdcolor_" + typeColor, "array", context.getApplicationContext().getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }
}
