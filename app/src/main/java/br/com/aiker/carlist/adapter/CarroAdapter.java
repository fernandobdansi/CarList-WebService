package br.com.aiker.carlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.aiker.carlist.R;
import br.com.aiker.carlist.api.RetrofitConfig;
import br.com.aiker.carlist.model.Carro;

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.MyViewHolder> {

    private List<Carro> carros;
    private Context context;

    public CarroAdapter(List<Carro> carros, Context context) {
        this.carros = carros;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_carros, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Carro carro = carros.get(position);

        Picasso.get()
                .load(carro.getImagem())
                .error(R.drawable.ic_error)
                .into(holder.imageViewCarro);

        holder.textDescription.setText(carro.getDescription());
        holder.textLocation.setText(carro.getLocation());
        holder.textMotor.setText(carro.getMotor());
        holder.textName.setText(carro.getName());
        holder.textValue.setText(carro.getValue());

    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewCarro;
        private TextView textDescription;
        private TextView textLocation;
        private TextView textMotor;
        private TextView textName;
        private TextView textValue;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewCarro = itemView.findViewById(R.id.image_carro);
            textDescription = itemView.findViewById(R.id.text_description);
            textLocation = itemView.findViewById(R.id.text_location);
            textMotor = itemView.findViewById(R.id.text_motor);
            textName = itemView.findViewById(R.id.text_name);
            textValue = itemView.findViewById(R.id.text_value);

        }
    }

}

