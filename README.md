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