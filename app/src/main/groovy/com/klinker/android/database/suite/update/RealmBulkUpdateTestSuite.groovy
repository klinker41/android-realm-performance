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

package com.klinker.android.database.suite.update

import android.content.Context
import com.klinker.android.database.model.User
import com.klinker.android.database.suite.TestSuite
import groovy.transform.CompileStatic
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults

/**
 * A test suite which tests functionality against a realm and uses bulk updates.
 */
@CompileStatic
public class RealmBulkUpdateTestSuite extends TestSuite {

    public RealmBulkUpdateTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        NUMBER_OF_TESTS.times {
            Realm realm = Realm.getInstance(context)
            long startTime = System.currentTimeMillis()
            realm.beginTransaction()

            RealmResults<User> users = realm.where(User.class).findAll()

            // currently, there is a bug in realm that doesn't allow me to use iterators here, it
            // instead needs to be implemented as a normal for loop. Issue #640 on github.
            for (int i = 0; i < users.size(); i++) {
                users.get(i).firstName = 'Luke'
            }

            realm.commitTransaction()
            realm.close()

            long result = System.currentTimeMillis() - startTime
            results << result
            onTestFinished this, result

            // give the processor a break before starting again
            Thread.sleep 2000
        }
    }

    @Override
    public String getTestDescription() {
        return "Realm Bulk Update"
    }

}