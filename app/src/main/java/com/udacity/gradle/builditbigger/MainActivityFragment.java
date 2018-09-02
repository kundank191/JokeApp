package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

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
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.execute(new Pair<Context, String>(getActivity(), "Manfred"));

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

    static class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, ArrayList<String>> {
        Context context;

        @SafeVarargs
        @Override
        protected final ArrayList<String> doInBackground(Pair<Context, String>... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;
            try {
                return (ArrayList<String>) myApiService.getJoke().execute().getJokes();
            } catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> jokes) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putStringArrayListExtra(JokeActivity.INTENT_KEY_JOKES,jokes);
            context.startActivity(intent);
            context = null;
        }
    }
}
