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

package com.klinker.android.database.suite.insert

import android.content.Context
import com.klinker.android.database.model.User
import com.klinker.android.database.suite.TestSuite
import groovy.transform.CompileStatic
import io.realm.Realm

/**
 * A test suite which tests functionality against a realm and uses bulk inserts.
 */
@CompileStatic
public class RealmBulkInsertTestSuite extends TestSuite {

    public RealmBulkInsertTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        NUMBER_OF_TESTS.times {
            Realm.deleteRealmFile(context)
            Realm realm = Realm.getInstance(context)
            long startTime = System.currentTimeMillis()
            realm.beginTransaction()

            OPERATIONS_PER_TEST.times { i ->
                User user = realm.createObject(User.class)
                user.setFirstName("Jake")
                user.setLastName("Klinker")
                user.setAge(21)
            }

            realm.commitTransaction()
            realm.close()

            long result = System.currentTimeMillis() - startTime
            results << result
            onTestFinished this, result
        }
    }

    @Override
    public String getTestDescription() {
        return "Realm Bulk Insert"
    }

}