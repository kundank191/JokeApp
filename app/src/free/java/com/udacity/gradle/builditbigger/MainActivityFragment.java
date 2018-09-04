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
import android.widget.Toast;

import com.example.jokedisplaylibrary.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeFetchAsyncTask.OnCompletionListener {

    @BindView(R.id.loading_group)
    Group progressViewGroup;
    @BindView(R.id.content_group)
    Group contentViewGroup;
    private static final String JOKE_TYPE = "random";
    private String INTERSTITIAL_AD_ID;
    private InterstitialAd interstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        INTERSTITIAL_AD_ID = getString(R.string.interstitial_ad_unit_id);
        initAds();
        return root;
    }

    /**
     * Initialize ads
     */
    void initAds() {
        // Initialize interstitial ad
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId(INTERSTITIAL_AD_ID);
        // make adRequest
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                showContent();
            }

            @Override
            public void onAdClosed() {
                //when ad is closed user will be shown jokes
                super.onAdClosed();
                showContent();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        hideLoadingUi();
    }

    @OnClick(R.id.tell_joke_button)
    public void tellJoke() {
        //If ad is loaded it will be shown
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            //if ad didn't load user will be shown jokes
            showContent();
        }

    }

    private void showContent() {
        displayLoadingUI();
        JokeFetchAsyncTask asyncTask = new JokeFetchAsyncTask((Fragment) this);
        //noinspection unchecked
        asyncTask.execute(new Pair<Context, String>(getActivity(), JOKE_TYPE));
    }

    @Override
    public void onComplete(ArrayList<String> jokes) {
        if (jokes != null && jokes.size() > 0) {
            Intent intent = new Intent(getContext(), JokeActivity.class);
            intent.putStringArrayListExtra(JokeActivity.INTENT_KEY_JOKES, jokes);
            Objects.requireNonNull(getActivity()).startActivity(intent);
        } else {
            hideLoadingUi();
            Toast.makeText(getContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Shows a loading screen when jokes are being requested
     */
    void displayLoadingUI() {
        progressViewGroup.setVisibility(View.VISIBLE);
        contentViewGroup.setVisibility(View.GONE);
    }

    /**
     * Hides the loading screen when more jokes has to be requested
     */
    void hideLoadingUi() {
        progressViewGroup.setVisibility(View.GONE);
        contentViewGroup.setVisibility(View.VISIBLE);
    }
}
