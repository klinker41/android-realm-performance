/*
 * Copyright (C) 2015 Jacob Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klinker.android.database.suite

import android.content.Context
import android.widget.TextView
import groovy.transform.CompileStatic

/**
 * The abstract test suite which holds results and can calculate average run times for each suite
 */
@CompileStatic
public abstract class TestSuite {

    public static final Integer NUMBER_OF_TESTS = 5
    public static final Integer OPERATIONS_PER_TEST = 1000

    /**
     * The results of all executed tests.
     */
    List<Long> results

    /**
     * The context to run tests in.
     */
    Context context

    /**
     * Initializes a new TestSuite.
     */
    public TestSuite(Context context) {
        this.results = []
        this.context = context
    }

    /**
     * Gets the average run time of the current suite.
     * @return the average run time.
     */
    public long getAverage() {
        long sum = 0
        results.each { sum += (it as Long) }
        return (Long) (sum / results.size())
    }

    /**
     * Prints the results of the test, ie execution time, into the text view.
     * @param textView the textview to insert time into.
     * @param executionTime the execution time to insert.
     */
    public void printResults(TextView textView, long executionTime) {
        textView.setText("${textView.getText()}\n${getTestDescription()}: ${executionTime} ms")

        if (results.size() == NUMBER_OF_TESTS) {
            textView.setText("${textView.getText()}\n------------------------------------\n" +
                    "${getTestDescription()} Average: ${getAverage()}\n\n")
        }
    }

    /**
     * Run all tests in the test suite.
     * @param onTestFinished callback for when an individual test has finished.
     */
    abstract void runTests(Closure onTestFinished)

    /**
     * Gets the name of the test to be printed on the screen when looking at results
     * @return the name of the test
     */
    abstract String getTestDescription()

}
