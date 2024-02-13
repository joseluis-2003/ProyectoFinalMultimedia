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
import com.vedruna.proyectofinalmultimedia.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Fragmento para eliminar un jugador.
 */
public class borrar extends Fragment {

    CRUDInterfaces crudInterfaces;
    Button button;
    EditText idEditText;
    /**
     * Constructor vacío requerido por Fragment.
     */
    public borrar() {

    }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);

        // Inicializar el EditText para el ID
        idEditText = view.findViewById(R.id.editTextID);

        // Configurar el botón de borrado con el clic
        setupDeleteButton(view);

        return view;
    }
    /**
     * Método para configurar el botón de borrado.
     * @param view La vista del fragmento.
     */
    private void setupDeleteButton(View view) {
        button = view.findViewById(R.id.buttonBorrarProducto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID ingresado por el usuario
                String idString = idEditText.getText().toString().trim();

                if (!idString.isEmpty()) {
                    int productId = Integer.parseInt(idString);
                    delete(productId);
                } else {
                    // Manejar el caso en el que el ID esté vacío
                    // Puedes mostrar un mensaje de error o realizar otras acciones
                    //Log.e("Error", "El ID no puede estar vacío");
                    mostrarToast("El Id no puede estar vacío máquina");
                }
            }
        });
    }
    /**
     * Método para eliminar un jugador.
     * @param id El ID del jugador a ser eliminado.
     */
    private void delete(int id) {
        // Construir la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear la interfaz CRUDInterface
        crudInterfaces = retrofit.create(CRUDInterfaces.class);

        // Llamar al método de borrado con el ID del producto
        Call<Void> call = crudInterfaces.delete(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                mostrarToast("Jugador eliminado");
                // Borrado exitoso, puedes realizar acciones adicionales si es necesario
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
            }
        });
    }
    /**
     * Método para mostrar un Toast.
     * @param mensaje El mensaje a ser mostrado en el Toast.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}