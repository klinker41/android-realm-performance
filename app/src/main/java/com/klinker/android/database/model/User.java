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

package com.klinker.android.database.model;

import io.realm.RealmObject;

/**
 * The Realm object which holds information about a user.
 */
public class User extends RealmObject {

    private String firstName;
    private String lastName;
    private int age;

    /**
     * Get the first name for the user.
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name for the user.
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the age of the user.
     * @return the age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the first name of the user.
     * @param firstName the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set the last name of the user.
     * @param lastName the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set the age of the user.
     * @param age the age.
     */
    public void setAge(int age) {
        this.age = age;
    }

}
