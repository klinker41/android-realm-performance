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
import io.realm.RealmResults

/**
 * A test suite which tests functionality against a realm.
 */
@CompileStatic
public class RealmUpdateTestSuite extends TestSuite {

    public RealmUpdateTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        NUMBER_OF_TESTS.times {
            Realm realm = Realm.getInstance(context)
            long startTime = System.currentTimeMillis()

            // this is actually a pretty poor test... it is testing much more the speed of the
            // begin and commit ~5000 times than the insert, which doesn't do much good. Also,
            // the query takes time.
            OPERATIONS_PER_TEST.times { i ->
                realm.beginTransaction()
                realm.where(User.class).findFirst().firstName = i % 2 == 0 ? 'Aaron' : 'Luke'
                realm.commitTransaction()
            }

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
        return "Realm Update"
    }

}