package com.basicmoon.expediaassessment.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOThreadExecutor implements Executor {

    private final Executor mDiskIOExecutor;

    public DiskIOThreadExecutor() {

        mDiskIOExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        mDiskIOExecutor.execute(runnable);
    }
}
