package com.vedruna.proyectofinalmultimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.proyectofinalmultimedia.interfaces.CRUDInterfaces;
import com.vedruna.proyectofinalmultimedia.model.Player;
import com.vedruna.proyectofinalmultimedia.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * update class represents a Fragment for updating player information.
 */


public class actualizar extends Fragment {

    EditText nameText;
    EditText posicionText;
    EditText equipoText;

    Button button;
    EditText idText;  // Nuevo EditText para el ID
    private Retrofit retrofit;
    CRUDInterfaces crudInterfaces;

    public actualizar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_actualizar, container, false);

        // Inicializar los EditText
        idText = rootView.findViewById(R.id.editTextID);  // Nuevo EditText para el ID
        nameText = rootView.findViewById(R.id.editTextNombre);
        posicionText = rootView.findViewById(R.id.editTextPosicion);
        equipoText = rootView.findViewById(R.id.editTextEquipo);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonActualizarJugador);

        // Puedes agregar un listener al botón si deseas manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        return rootView;
    }

    private void actualizar() {
        String id = idText.getText().toString().trim();
        String nombre = nameText.getText().toString().trim();
        String posicion = posicionText.getText().toString().trim();
        String equipo = equipoText.getText().toString().trim();

        // Verificar si alguno de los campos está vacío
        if (id.isEmpty() || nombre.isEmpty() || posicion.isEmpty() || equipo.isEmpty()) {
            // Mostrar Toast indicando que todos los campos son obligatorios
            mostrarToast("Todos los campos son obligatorios fiera");
        } else {
            // Crear un jugador
            Player player = new Player(Integer.parseInt(id), nombre, posicion, equipo);

            crudInterfaces = retrofit.create(CRUDInterfaces.class);

            // Llamar al método actualizar
            Call<Player> call = crudInterfaces.actualizar(Integer.parseInt(id), player);

            call.enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Response err ", response.message());
                        return;
                    }
                    Player player1 = response.body();
                    mostrarToast("Jugador actualizado mastodonte: " + player1.getName());
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    Log.e("Throw err:", t.getMessage());
                    mostrarToast("Error al actualizar el jugador titan");
                }
            });
        }
    }

    // Método para mostrar un Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}