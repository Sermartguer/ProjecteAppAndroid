package com.herprogramacion.restaurantericoparico.fragments;

/**
 * Created by Sergio on 08/05/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.models.Comidas;
import com.herprogramacion.restaurantericoparico.adapters.AdaptadorSeries;

public class FragmentoVistoTab extends Fragment{
    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentFavoriteTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorSeries adaptador;

    public static FragmentFavoriteTab nuevaInstancia(int indiceSeccion) {
        FragmentFavoriteTab fragment = new FragmentFavoriteTab();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorSeries(getContext(), Comidas.getMoviesVis(),1);
                break;
            case 1:
                adaptador = new AdaptadorSeries(getContext(),Comidas.getSeriesVis(),2);
                break;
        }

        reciclador.setAdapter(adaptador);

        return view;
    }
}
