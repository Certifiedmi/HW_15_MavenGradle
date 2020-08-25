package com.yurchyk.executor;

import com.yurchyk.workwithfile.WorkWithFile;

import java.io.IOException;

public class Executor {

    public void execute() throws IOException {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.wordCounter();
        workWithFile.smallWordsCounter();
        workWithFile.frequentWordsCounter();
        workWithFile.badWordsRemover();
    }
}