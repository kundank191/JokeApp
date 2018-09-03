package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kundan on 03-09-2018.
 */
public class JokeFetchAsyncTask extends AsyncTask<Pair<Context, String>, Void, ArrayList<String>> {

    private final String LOCAL_IP_ADDRESS = "http://10.0.2.2:8080/_ah/api/";
    private static MyApi myApiService = null;
    private OnCompletionListener listener;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    JokeFetchAsyncTask(Fragment fragment){
        try {
            listener = (OnCompletionListener) fragment;
        } catch (ClassCastException e){
            e.printStackTrace();
            throw new ClassCastException("Activity must implement OnCompletionListener");
        }
    }

    JokeFetchAsyncTask(OnCompletionListener listener){
        this.listener = listener;
    }

    @SafeVarargs
    @Override
    protected final ArrayList<String> doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(LOCAL_IP_ADDRESS)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        context = params[0].first;
        String jokeType = params[0].second;
        try {
            return (ArrayList<String>) myApiService.getJoke().execute().getJokes();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> jokes) {
        listener.onComplete(jokes);
        context = null;
        listener = null;
    }

    public interface OnCompletionListener{
        void onComplete(ArrayList<String> jokes);
    }
}
