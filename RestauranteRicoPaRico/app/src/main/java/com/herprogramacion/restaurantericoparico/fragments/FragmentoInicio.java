package com.herprogramacion.restaurantericoparico.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.herprogramacion.restaurantericoparico.R;
import com.herprogramacion.restaurantericoparico.adapters.AdapterHome;
import com.herprogramacion.restaurantericoparico.adapters.BookAdapter;
import com.herprogramacion.restaurantericoparico.net.BookClient;

/**
 * Fragmento para la sección de "Inicio"
 */
public class FragmentoInicio extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdapterHome adaptador;
    private BookAdapter bookAdapter;
    private BookClient client;
    private ProgressBar progress;

    public FragmentoInicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        adaptador = new AdapterHome(getContext());
        reciclador.setAdapter(adaptador);

        return view;
    }
}