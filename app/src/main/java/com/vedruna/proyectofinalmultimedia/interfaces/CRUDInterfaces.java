package com.vedruna.proyectofinalmultimedia.interfaces;

import com.vedruna.proyectofinalmultimedia.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * CRUDInterfaces is an interface defining CRUD operations for managing Player objects.
 * It specifies methods for retrieving, creating, updating, and deleting Player objects.
 */
public interface CRUDInterfaces {

    /**
     * Retrieves all Player objects from the server.
     *
     * @return A Call object representing the asynchronous request to retrieve all Player objects.
     */
    @GET("player")
    Call<List<Player>> getAll();

    /**
     * Creates a new Player object on the server.
     *
     * @param player The Player object to be created.
     * @return A Call object representing the asynchronous request to create a Player object.
     */
    @POST("player")
    Call<Player>create(@Body Player player);
    /**
     * Updates an existing Player object on the server.
     *
     * @param id     The id of the Player object to be updated.
     * @param player The updated Player object.
     * @return A Call object representing the asynchronous request to update a Player object.
     */
    @PUT("player/{id}")
    Call<Player> actualizar(@Path("id") int id, @Body Player player);

    /**
     * Deletes a Player object from the server.
     *
     * @param id The id of the Player object to be deleted.
     * @return A Call object representing the asynchronous request to delete a Player object.
     */
    @DELETE("player/{id}")
    Call<Void>delete(@Path("id")int id);
}
