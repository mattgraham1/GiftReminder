package org.graham.com.giftreminder;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.graham.com.giftreminder.models.Person;
import org.joda.time.LocalDate;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class GiftRecyclerAdapter extends RealmRecyclerViewAdapter<Person, GiftRecyclerAdapter.GiftViewHolder> {
    private Activity activity;

    public GiftRecyclerAdapter(MainActivity activity, @Nullable OrderedRealmCollection<Person> data) {
        super(data, true);
        this.activity = activity;
    }

    @Override
    public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new GiftViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GiftViewHolder holder, int position) {

    }

    class GiftViewHolder extends RecyclerView.ViewHolder {
        private String name;
        private LocalDate birthday;

        public GiftViewHolder(View itemView) {
            super(itemView);
        }
    }
}
