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

package com.klinker.android.database.suite.delete

import android.content.Context
import com.klinker.android.database.model.UserDataSource
import com.klinker.android.database.suite.TestSuite
import groovy.transform.CompileStatic

/**
 * A test suite which tests functionality against raw SQL.
 */
@CompileStatic
public class SQLRawDeleteTestSuite extends TestSuite {

    public SQLRawDeleteTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        UserDataSource dataSource = new UserDataSource(context)

        NUMBER_OF_TESTS.times {
            dataSource.open()

            if (dataSource.findAll().getCount() == 0) {
                dataSource.createUserBulk(OPERATIONS_PER_TEST)
            }

            long startTime = System.currentTimeMillis()
            dataSource.deleteUsersRaw()
            dataSource.close()

            long result = System.currentTimeMillis() - startTime
            results << result
            onTestFinished this, result
        }
    }

    @Override
    public String getTestDescription() {
        return "SQLite Raw Delete"
    }

}