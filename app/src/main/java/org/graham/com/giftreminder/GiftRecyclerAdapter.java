package org.graham.com.giftreminder;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.graham.com.giftreminder.models.Person;
import org.joda.time.LocalDate;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class GiftRecyclerAdapter extends RealmRecyclerViewAdapter<Person, GiftRecyclerAdapter.GiftViewHolder> {
    private MainActivity activity;

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
        final Person person = getData().get(position);
        holder.name.setText(person.getName());
        holder.dob.setText(LocalDate.fromDateFields(person.getBithday()).toString());
        holder.trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.removePerson(person);
            }
        });
    }

    class GiftViewHolder extends RecyclerView.ViewHolder {
        private TextView dob;
        private TextView name;
        private ImageButton trashButton;

        public GiftViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textView_name);
            dob = (TextView) itemView.findViewById(R.id.textView_dateOfBirth);
            trashButton = (ImageButton) itemView.findViewById(R.id.imageButton_trash);
        }
    }
}
