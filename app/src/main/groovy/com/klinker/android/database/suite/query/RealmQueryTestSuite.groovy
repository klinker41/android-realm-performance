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
import android.util.Log
import com.klinker.android.database.model.User
import com.klinker.android.database.suite.TestSuite
import groovy.transform.CompileStatic
import io.realm.Realm
import io.realm.RealmResults

/**
 * A test suite which tests functionality against a realm.
 */
@CompileStatic
public class RealmQueryTestSuite extends TestSuite {

    private static final String TAG = "RealmQueryTestSuite"

    public RealmQueryTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        Realm realm = Realm.getInstance(context)

        NUMBER_OF_TESTS.times {
            long startTime = System.currentTimeMillis()

            RealmResults<User> users = realm.where(User.class).findAll()
            Log.v(TAG, "Number of results: ${users.size()}")

            long result = System.currentTimeMillis() - startTime
            results << result

            onTestFinished this, result
        }

        realm.close()
    }

    @Override
    public String getTestDescription() {
        return "Realm Query"
    }

}