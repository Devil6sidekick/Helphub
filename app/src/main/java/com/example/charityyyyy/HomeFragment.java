package com.example.charityyyyy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {
    public static List<Event> eventList = new ArrayList<>();
    private RecyclerView homeRecyclerView;
    private EventAdapter eventAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_home, container, false);

        homeRecyclerView=rootView.findViewById(R.id.Homerecyclerview);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventAdapter= new EventAdapter(eventList);
        homeRecyclerView.setAdapter(eventAdapter);
        return rootView;
    }




    public static void generateEvents(Event event){
        eventList.add(event);
    }
}