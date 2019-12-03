package com.basicmoon.expediaassessment.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

@Singleton
public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private final Executor ioExecutor;

    private final Executor networkIOExecutor;

    private final Executor mainThreadExecutor;

    public AppExecutors(Executor ioExecutor, Executor networkIOExecutor, Executor mainThreadExecutor) {
        this.ioExecutor = ioExecutor;
        this.networkIOExecutor = networkIOExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public Executor getIoExecutor() {
        return ioExecutor;
    }

    public Executor getNetworkIOExecutor() {
        return networkIOExecutor;
    }

    public Executor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public static class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }
}
