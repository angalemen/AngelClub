package com.example.clubesvcannabicos.ui;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clubesvcannabicos.data.CannabisViewModel;
import com.example.clubesvcannabicos.data.ClubCannabico;
import com.example.clubesvcannabicos.databinding.FragmentInsertDataBinding;
import com.example.clubesvcannabicos.ui.notifications.NotificationsFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InsertData extends Fragment {

    FragmentInsertDataBinding binding;

    NotificationsFragment mapa;
    FirebaseUser authUser;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInsertDataBinding.inflate(inflater, container, false);


        mAuth = FirebaseAuth.getInstance();
        authUser = mAuth.getCurrentUser();





        CannabisViewModel cannabisViewModel = new ViewModelProvider(getActivity()).get(CannabisViewModel.class);

        binding.btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authUser != null) {
                    ClubCannabico clubCannabico = new ClubCannabico();
                    clubCannabico.setNombre(binding.nombreClub.getText().toString());
                    clubCannabico.setLatitud(binding.txtLatitud.getText().toString());
                    clubCannabico.setLongitud(binding.txtLongitud.getText().toString());

                    DatabaseReference base = FirebaseDatabase.getInstance("https://clubescannabicos-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
                    DatabaseReference users = base.child("users");
                    DatabaseReference uid = users.child(authUser.getUid());
                    DatabaseReference clubes = uid.child("clubes");

                    DatabaseReference reference = clubes.push();
                    reference.setValue(clubCannabico);


                    ///intento del marker para poner puntos pero no me ha dado tiempo
/*
                    clubes.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            ClubCannabico insertData = dataSnapshot.getValue(ClubCannabico.class);

                            LatLng aux = new LatLng(
                                    Double.valueOf(insertData.getLatitud()),
                                    Double.valueOf(insertData.getLongitud())
                            );

                            map.addMarker(new MarkerOptions()
                                    .title(insertData.getNombre())
                                    .snippet(insertData.getLatitud())
                                    .snippet(insertData.getLongitud())
                                    .position(aux));
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });*/








                } else {

                    Log.e("InsertData", "Usuario no autenticado");
                }
            }
        });


        return binding.getRoot();
    }
}
