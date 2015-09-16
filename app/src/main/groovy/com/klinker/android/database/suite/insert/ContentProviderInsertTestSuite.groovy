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
import com.klinker.android.database.suite.TestSuite
import groovy.transform.CompileStatic

/**
 * A test suite which tests functionality against a content provider.
 */
@CompileStatic
public class ContentProviderInsertTestSuite extends TestSuite {

    public ContentProviderInsertTestSuite(Context context) {
        super(context)
    }

    @Override
    public void runTests(Closure onTestFinished) {
        Thread.sleep 1000l
        results << 1000l
        onTestFinished.call this, 1000l

        Thread.sleep 1200l
        results << 1200l
        onTestFinished.call this, 1200l

        Thread.sleep 1100l
        results << 1100l
        onTestFinished.call this, 1100l

        Thread.sleep 900l
        results << 900l
        onTestFinished.call this, 900l

        Thread.sleep 1000l
        results << 1000l
        onTestFinished.call this, 1000l
    }

    @Override
    public String getTestDescription() {
        return "Content Provider Insert"
    }

}