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

package com.klinker.android.database.suite.query

import android.content.Context
import android.database.Cursor
import android.util.Log
import com.klinker.android.database.model.UserDataSource
import com.klinker.android.database.suite.TestSuite
import groovy.transform.CompileStatic

/**
 * A test suite which tests functionality against raw SQL.
 */
@CompileStatic
public class SQLQueryTestSuite extends TestSuite {

    public static final String TAG = "SQLQueryTestSuite"

    public SQLQueryTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        UserDataSource dataSource = new UserDataSource(context)
        dataSource.open()

        NUMBER_OF_TESTS.times {
            long startTime = System.currentTimeMillis()

            Cursor cursor = dataSource.findAll()
            Log.v(TAG, "Number of results: ${cursor.getCount()}")

            long result = System.currentTimeMillis() - startTime
            results << result
            onTestFinished this, result
        }

        dataSource.close()
    }

    @Override
    public String getTestDescription() {
        return "SQLite Query"
    }

}