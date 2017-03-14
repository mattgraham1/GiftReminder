package org.graham.com.giftreminder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.graham.com.giftreminder.models.Gift;
import org.graham.com.giftreminder.models.Person;
import org.joda.time.LocalDate;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                listPersonsAndGifts(realm);
            }
        });

        // FIXME: Remove once develop is done and first version is ready to be deployed
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);

        initDatabaseWithDefaultInfo(realm);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        setUpRecyclerView();
    }

    private void listPersonsAndGifts(Realm realm) {
        RealmResults<Person> persons = realm.where(Person.class).findAll();
        for (Person person : persons) {
            Log.e("Gift", person.getName() + ", DOB: " + LocalDate.fromDateFields(person.getBithday()).toString() + ", num gifts: " + person.getGifts().size());
        }
    }

    private void initDatabaseWithDefaultInfo(Realm realm) {
        // TODO: remove once develop is done
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Person.class);
            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Gift birthdayGift = realm.createObject(Gift.class);
                birthdayGift.setName("Amazon Gift Card");
                birthdayGift.setCost(25.0f);
                birthdayGift.setGiftedToMe(true);
                birthdayGift.setOccasion("Birthday");

                Person person = realm.createObject(Person.class);
                person.setName("Damon Graham");
                person.setAge(43);
                person.setBithday(new LocalDate(1972, 2, 8).toDate());
                person.getGifts().add(birthdayGift);

                Person person2 = realm.createObject(Person.class);
                person2.setName("Aubry Hawkins");
                person2.setAge(36);
                person2.setBithday(new LocalDate(1980, 10, 22).toDate());
                person2.getGifts().add(birthdayGift);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void setUpRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GiftRecyclerAdapter(this, realm.where(Person.class).findAll()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void removePerson(Person person) {
        realm.beginTransaction();
        RealmResults<Person> results = realm.where(Person.class).equalTo("name", person.getName()).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }
}
