package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Kundan on 03-09-2018.
 * Test to see if the Async task returns a non null result or not
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskText {

    private static final String JOKE_TYPE = "random";

    @Test
    public void testVerifyJoke() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        Context context = InstrumentationRegistry.getContext();
        JokeFetchAsyncTask testTask = new JokeFetchAsyncTask(new JokeFetchAsyncTask.OnCompletionListener() {
            @Override
            public void onComplete(ArrayList<String> jokes) {
                assertNotNull(jokes);
                assertTrue(jokes.size() > 0);
                latch.countDown();
            }
        }) {

        };
        //noinspection unchecked
        testTask.execute(new Pair<Context, String>(context, JOKE_TYPE));
        latch.await();
    }
}
