package com.example.clubesvcannabicos.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.clubesvcannabicos.data.CannabisViewModel;
import com.example.clubesvcannabicos.R;
import com.example.clubesvcannabicos.databinding.FragmentHomeBinding;
import com.example.clubesvcannabicos.ui.InsertData;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.io.IOException;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private MediaPlayer mediaPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }





        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playSong(view,"https://firebasestorage.googleapis.com/v0/b/clubescannabicos.appspot.com/o/sonidobt.mp3?alt=media&token=3225b224-3b62-413f-bed9-79403207c31c");

                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_navigation_home_to_navigation_notifications);
            }
        });

        binding.btAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_navigation_home_to_insertData);
            }
        });

        return root;
    }




    public void playSong(View view, String url) {
        MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
