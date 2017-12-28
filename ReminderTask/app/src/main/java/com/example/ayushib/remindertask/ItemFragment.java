package com.example.ayushib.remindertask;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.app.LauncherActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;






public class ItemFragment extends Fragment<Helper> implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Listener mListener = null;

    public ItemFragment() {

    }

    public static ItemFragment newInstance() {
        ItemFragment fragment = new ItemFragment();
        return fragment;
    }

    private ListItem editR;

    public static ItemFragment newInstance(String key) {
        final ItemFragment fragment = newInstance();
        Helper helper = Helper.getInstance();
        helper.getList().getReference().child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ListItem listItem = snapshot.getValue(ListItem.class);
                    listItem.setKey(snapshot.getKey());
                    listItem.setPriority(snapshot.getPriority());
                    if (fragment.etTitle != null)
                        fragment.prepareEdit(listItem);
                    else
                        fragment.editR = listItem;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Helper.onError(databaseError);
            }
        });
        return fragment;
    }

        MenuItem delete;

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu.clear();
            inflater.inflate(R.menu.fragment_reminder_item, menu);
            delete = menu.findItem(R.id.delete);
            delete.setVisible((mode == MODE_UPDATE));
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.create)
                saveReminder();
            else if (item.getItemId() == R.id.delete)
                deleteReminder();
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        EditText etTitle;
        Button btAt;
        DatePicker dt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_manage, container, false);
        initialize(view);
        if (editR != null)
            prepareEdit(editR);
        return view;
    }

    public View initialize(View view) {
        etTitle = (EditText) view.findViewById(R.id.etTitle);
        btAt = (Button) view.findViewById(R.id.btAt);
        btAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dt.show();
            }
        });
        return view;
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        btAt.setText(DateUtils.getRelativeDateTimeString(getContext(), dt.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        btAt.setText(DateUtils.getRelativeDateTimeString(getContext(), dt.getTimestamp(), DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));
    }

    private ListItem listItem;
    private int mode;
    public static final int MODE_CREATE = 0;
    public static final int MODE_UPDATE = 1;
    public static final int MODE_DELETE = 2;

    public void setDateTimePicker(long timestamp) {
        dt = new DateTimePicker(getContext(), this, this, timestamp);
        Calendar c = dt.getCalendar();
        onDateSet(null, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        onTimeSet(null, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
    }

    public void prepareEdit(ListItem listItem) {
        this.listItem = listItem;
        mode = MODE_UPDATE;
        setDateTimePicker(this.listItem.getTimestamp());
        etTitle.setText(this.listItem.getTitle());
        if (delete != null)
            delete.setVisible(true);
    }

    public void prepareNew() {
        listItem = new ListItem();
        mode = MODE_CREATE;
        etTitle.setText("");
        setDateTimePicker(System.currentTimeMillis() + (1000 * 60 * 60 * 24) + (1000 * 60 * 10));
    }

    public void saveReminder() {
        listItem.setTitle(etTitle.getText().toString());
        listItem.setTimestamp(dt.getTimestamp());
        listItem.setStatus(ListItem.STATUS_ACTIVE);
        getHelper().getList().add(listItem, null);

        mListener.onSave(mode, listItem);
        prepareNew();
    }

    public void deleteReminder() {
        getHelper().getList().remove(listItem, null);
        mListener.onSave(MODE_DELETE, listItem);
        prepareNew();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Listener {
        void onSave(int mode, ListItem listItem);

        void onFragmentInteraction(Uri uri);
    }


}
