package br.com.aiker.carlist.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import br.com.aiker.carlist.R;
import br.com.aiker.carlist.adapter.CarroAdapter;
import br.com.aiker.carlist.api.RetrofitConfig;
import br.com.aiker.carlist.model.Carro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final Call<List<Carro>> eventosCall = new RetrofitConfig().getCarroService().listarCarros();

    private RecyclerView recyclerViewCarros;
    private CarroAdapter carroAdapter;
    private List<Carro> carros = new ArrayList<>();
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carroAdapter = new CarroAdapter(carros, this);
        recyclerViewCarros = findViewById(R.id.recycler_carro);


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1 );
        recyclerViewCarros.setLayoutManager( layoutManager );

        recyclerViewCarros.setAdapter(carroAdapter);

        recuperarEventosRetrofit();
    }

    private void recuperarEventosRetrofit() {

        eventosCall.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                if( response.isSuccessful() ){
                    carros.clear();
                    carros.addAll(response.body());
                    carroAdapter.notifyDataSetChanged();
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

}
