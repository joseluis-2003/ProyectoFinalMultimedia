package com.vedruna.proyectofinalmultimedia;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.vedruna.proyectofinalmultimedia.adapter.PlayerAdapter;
import com.vedruna.proyectofinalmultimedia.interfaces.CRUDInterfaces;
import com.vedruna.proyectofinalmultimedia.model.Player;
import com.vedruna.proyectofinalmultimedia.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Fragmento para mostrar una lista de jugadores.
 */
public class inicio extends Fragment {

    List<Player> players;
    CRUDInterfaces crudInterface;
    ListView listView;

    /**
     * Constructor vacío requerido por Fragment.
     */
    public inicio(){}
    /**
     * Método llamado al crear el fragmento.
     * @param savedInstanceState El estado previamente guardado del fragmento, si existe.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /**
     * Método llamado para crear la vista que representa el fragmento.
     * @param inflater El LayoutInflater que se utiliza para inflar la vista.
     * @param container El ViewGroup en el que se infla la vista.
     * @param savedInstanceState El estado previamente guardado del fragmento, si existe.
     * @return La vista del fragmento inflada.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        listView = view.findViewById(R.id.lista); // Corrección aquí
        getAll();
        return view;
    }
    /**
     * Método para obtener todos los jugadores de la base de datos.
     */
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        crudInterface = retrofit.create(CRUDInterfaces.class);
        Call<List<Player>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Reponse error: " , response.message());
                    return;
                }
                players = response.body();
                PlayerAdapter playerAdapter = new PlayerAdapter(requireContext(), players);
                listView.setAdapter(playerAdapter);
                players.forEach(p-> Log.i("Jugadores: ",p.toString()));
            }
            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.e("throw error: " , t.getMessage());
            }
        });
    }
}