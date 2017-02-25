package com.haynhanh.troll;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private RecyclerView recyclerView;

    private static final String KEY = "domain_id";

    public static Fragment getInstance(int domainId) {

        Bundle bundle = new Bundle();
        bundle.putInt(KEY, domainId);

        Fragment fragment = new ImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);

        Bundle bundle = getArguments();
        String domainId = bundle.getString(KEY);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        return view;
    }



}
