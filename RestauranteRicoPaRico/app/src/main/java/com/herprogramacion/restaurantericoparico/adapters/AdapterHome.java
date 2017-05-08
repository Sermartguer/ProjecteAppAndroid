package com.herprogramacion.restaurantericoparico.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Comida;
import com.herprogramacion.restaurantericoparico.models.Comidas;
import com.herprogramacion.restaurantericoparico.ui.activities.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sergio on 27/04/2017.
 */

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> implements ItemClickListener {
    private final Context context;
    private final List<Comida> items = Comidas.Upcoming;

    public static int position1;
    public AdapterHome(Context context) {
        this.context = context;

        //Comidas.Upcoming.clear();

    }

    public int getItemCount() {
        return items.size();
    }

    public AdapterHome.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_home, viewGroup, false);
        return new AdapterHome.ViewHolder(v, this);
    }

    @Override
    public void onItemClick(View view, int position) {
        View sharedImage = view.findViewById(R.id.image);

        DetailActivity.launch((Activity) context, position, sharedImage, items, 1);
    }

    public Context getContext() {
        return context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public TextView date;
        public ItemClickListener listener;

        public ViewHolder(View v, ItemClickListener listener) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
            date = (TextView) v.findViewById(R.id.date);
            v.setOnClickListener(this);
            this.listener = listener;
        }


        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }


    @Override
    public void onBindViewHolder(AdapterHome.ViewHolder viewHolder, int i) {
        Comida item = Comidas.Upcoming.get(i);

        Picasso.with(getContext()).load(Uri.parse("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + item.getIdDrawable())).error(R.drawable.ic_nocover).into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.date.setText(item.getDate() + "");
        viewHolder.precio.setText(item.getRating() + "/10");
    }

}