package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokedisplaylibrary.JokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeFetchAsyncTask.OnCompletionListener{

    @BindView(R.id.loading_group)
    Group progressViewGroup;
    @BindView(R.id.content_group)
    Group contentViewGroup;
    @BindView(R.id.adView)
    AdView adView;
    private static String JOKE_TYPE = "random";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this,root);
        // Create an ad request.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        hideLoadingUi();
    }

    @OnClick(R.id.tell_joke_button)
    public void tellJoke(){
        displayLoadingUI();
        JokeFetchAsyncTask asyncTask = new JokeFetchAsyncTask((Fragment) this);
        //noinspection unchecked
        asyncTask.execute(new Pair<Context, String>(getActivity(), JOKE_TYPE));

    }

    @Override
    public void onComplete(ArrayList<String> jokes) {
        Intent intent = new Intent(getContext(), JokeActivity.class);
        intent.putStringArrayListExtra(JokeActivity.INTENT_KEY_JOKES,jokes);
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }

    /**
     * Shows a loading screen when jokes are being requested
     */
    public void displayLoadingUI(){
        progressViewGroup.setVisibility(View.VISIBLE);
        contentViewGroup.setVisibility(View.GONE);
    }

    /**
     * Hides the loading screen when more jokes has to be requested
     */
    public void hideLoadingUi(){
        progressViewGroup.setVisibility(View.GONE);
        contentViewGroup.setVisibility(View.VISIBLE);
    }
}
