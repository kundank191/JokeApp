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
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeFetchAsyncTask.OnCompletionListener{

    private static MyApi myApiService = null;
    @BindView(R.id.loading_group)
    Group progressViewGroup;
    @BindView(R.id.content_group)
    Group contentViewGroup;
    @BindView(R.id.adView)
    AdView adView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this,root);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
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
        JokeFetchAsyncTask asyncTask = new JokeFetchAsyncTask(this);
        //noinspection unchecked
        asyncTask.execute(new Pair<Context, String>(getActivity(), "Manfred"));

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
