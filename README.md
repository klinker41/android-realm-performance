# Realm Database Performance Assessment #

This application was created to test the performance of a Realm database against other common
databases in Android, namely a normal SQLite database and a SQLite database backed by a content
provider.

The application is written mainly in Groovy, because why not?

For each type of test, we run through it 5 times and perform 1000 operations each time, totaling
5000 at the end. The app will record the execution time for each run and then average them together
and display the results to you on the screen when finished.

Test Overview:
1) Realm inserts where we commit the transaction after each individual insertion completes
2) Realm inserts where we commit the transaction after all individual insertions are complete

---

## License

    Copyright (C) 2015 Jacob Klinker

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.