package com.example.relaxdreamv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenu() {
        // Required empty public constructor
    }

    public static MainMenu newInstance(String param1, String param2) {
        MainMenu fragment = new MainMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle category = new Bundle();

        ImageButton goToSongs = view.findViewById(R.id.songs);
        ImageButton goToMelodies = view.findViewById(R.id.melodies);
        ImageButton goToSounds = view.findViewById(R.id.sounds);

        goToSounds.setOnClickListener(v -> {
            category.putInt("category", 0);
            Navigation.findNavController(view).navigate(R.id.action_mainMenu_to_listener, category);
        });

        goToSongs.setOnClickListener(v -> {
            category.putInt("category", 1);
            Navigation.findNavController(view).navigate(R.id.action_mainMenu_to_listener, category);
        });

        goToMelodies.setOnClickListener(v -> {
            category.putInt("category", 2);
            Navigation.findNavController(view).navigate(R.id.action_mainMenu_to_listener, category);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_menu, container, false);

        return view;
    }
}