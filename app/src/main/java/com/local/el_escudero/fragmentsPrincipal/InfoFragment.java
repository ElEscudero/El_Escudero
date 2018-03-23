package com.local.el_escudero.fragmentsPrincipal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.local.el_escudero.R;

/**
 * Created by preto on 05/03/2018.
 */

public class InfoFragment extends Fragment implements View.OnClickListener{

    Button mapa;
    public InfoFragment(){
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info,container,false);
        mapa=v.findViewById(R.id.btnmap_i);
        mapa.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //Intent intent = new Intent(getActivity().MapsActivity.class);
        pushfragmentss(new MapsFragment());

    }
    //Metodo para pasar de un fragment a otro
    public void pushfragmentss(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor,fragment).addToBackStack(null).commit();
    }
}
