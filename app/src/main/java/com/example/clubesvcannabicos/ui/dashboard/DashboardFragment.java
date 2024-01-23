package com.example.clubesvcannabicos.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.clubesvcannabicos.R;
import com.example.clubesvcannabicos.data.CannabisViewModel;
import com.example.clubesvcannabicos.data.ClubCannabico;
import com.example.clubesvcannabicos.data.Valoracion;
import com.example.clubesvcannabicos.databinding.FragmentDashboardBinding;
import com.example.clubesvcannabicos.ui.InsertData;
import com.example.clubesvcannabicos.ui.home.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private FirebaseUser authUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        CannabisViewModel sharedViewModel = new ViewModelProvider(
                requireActivity()
        ).get(CannabisViewModel.class);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        sharedViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            authUser = user;


            binding.buttonNotificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Valoracion valoracion = new Valoracion();
                    valoracion.setNombre(binding.txtName.getText().toString());
                    valoracion.setValoracion(binding.txtValoracion.getText().toString());

                    DatabaseReference base = FirebaseDatabase.getInstance("https://clubescannabicos-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

                    DatabaseReference users = base.child("users");
                    DatabaseReference uid = users.child(authUser.getUid());
                    DatabaseReference valoraciones = uid.child("valoraciones");


                    DatabaseReference reference = valoraciones.push();
                    reference.setValue(valoracion);



                }
            });

            binding.irInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    NavHostFragment.findNavController(DashboardFragment.this)
                            .navigate(R.id.action_navigation_dashboard_to_navigation_home);
                }
            });



        });


        return binding.getRoot();
    }
    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}