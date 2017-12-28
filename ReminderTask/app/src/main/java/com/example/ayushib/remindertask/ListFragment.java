package com.example.ayushib.remindertask;


import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;



public class ListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private ListFragment.Listener mListener;


    public ListFragment() {
    }


    public static ListFragment newInstance(int columnCount) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setHasFixedSize(true);
            if (mColumnCount <= -1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            Helper helper = Helper.getInstance();
            recyclerView.setAdapter(new RecycleAdapter <ListItem, ViewHolder>(ListItem.class, R.layout.fragment_item_view, ViewHolder.class, helper.getList().getReference()) {
                @Override
                protected void populateViewHolder(ViewHolder viewHolder, ListItem model, int position) {
                    viewHolder.setListItem(model);
                    viewHolder.setOnClickListener(new ViewHolder.OnClickListener() {
                        @Override
                        public void onClick(View view, LauncherActivity.ListItem listItem) {
                            mListener.onItemClick(ListItem);
                        }
                    });
                }

                @Override
                protected ListItem parseSnapshot(DataSnapshot snapshot) {
                    ListItem listItem = super.parseSnapshot(snapshot);
                    Log.d("Schedule_" + listItem.getTitle(), listItem.scheduleIfNotExist().toString());
                    return listItem;
                }
            });
        }
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListFragment.Listener) {
            mListener = (ListFragment.Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Listener {

        void onItemClick(ListItem listItem);


    }
}



