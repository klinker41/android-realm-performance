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

package com.klinker.android.database

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.klinker.android.database.suite.insert.RealmBulkInsertTestSuite
import com.klinker.android.database.suite.insert.RealmInsertTestSuite
import com.klinker.android.database.suite.TestSuite
import com.klinker.android.database.suite.insert.SQLBulkInsertTestSuite
import com.klinker.android.database.suite.insert.SQLRawInsertTestSuite
import com.klinker.android.database.suite.insert.SQLInsertTestSuite
import groovy.transform.CompileStatic

/**
 * The main activity where all tests will be run and the results will be displayed to the user.
 */
@CompileStatic
public class MainActivity extends AppCompatActivity {

    private Button runButton
    private ProgressBar progressBar
    private TextView results
    private Handler handler

    private List testSuites

    /**
     * Set up the functionality that will happen when the run button is pushed. IE, start all of the
     * test suites.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate savedInstanceState
        setContentView R.layout.activity_main

        initializeTestSuites()

        runButton = findViewById(R.id.run_test) as Button
        progressBar = findViewById(R.id.running_spinner) as ProgressBar
        results = findViewById(R.id.results) as TextView
        handler = new Handler()

        runButton.setOnClickListener {
            runButton.setVisibility View.GONE
            progressBar.setVisibility View.VISIBLE
            results.setText(null)

            startTest { TestSuite suite, long executionTime ->
                // post back to the UI thread
                handler.post {
                    updateResults(suite, executionTime)
                }
            }
        }
    }

    /**
     * Initialize all suites that will be run.
     */
    private void initializeTestSuites() {
        testSuites = []
        testSuites << new RealmInsertTestSuite(this)
        testSuites << new RealmBulkInsertTestSuite(this)
        testSuites << new SQLInsertTestSuite(this)
        testSuites << new SQLRawInsertTestSuite(this)
        testSuites << new SQLBulkInsertTestSuite(this)
    }

    /**
     * Start the running the tests and update the ui when appropriate.
     * @param onUpdate the closure to be executed when the test suite has finished a test.
     */
    private void startTest(Closure onUpdate) {
        new Thread({
            testSuites.each { ((TestSuite) it).runTests(onUpdate) }

            // post back to the UI thread
            handler.post {
                onTestsFinished()
            }
        }).start()
    }

    /**
     * Update the results in the text view displayed on the screen.
     * @param suite the suite that ran the tests.
     * @param executionTime the time taken to execute the tests.
     */
    private void updateResults(TestSuite suite, long executionTime) {
        suite.printResults(results, executionTime)
    }

    /**
     * Update the button states at the top of the screen when the tests have finished running.
     */
    private void onTestsFinished() {
        runButton.setVisibility View.VISIBLE
        progressBar.setVisibility View.GONE
    }

}