package br.com.aiker.carlist.api;

import java.util.List;

import br.com.aiker.carlist.model.Carro;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarroService {

    @GET("/")
    Call<List<Carro>> listarCarros();

}
