package com.local.el_escudero.fragmentsPrincipal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.local.el_escudero.R;
import com.local.el_escudero.fragmentsPrincipal.fragmentscat.Productos;
import com.local.el_escudero.model.Product;
import com.local.el_escudero.sqlLite.SQLite;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Homefr.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Homefr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Homefr extends Fragment implements View.OnClickListener{


    //Agregamos los imagebutton para implemtearles el onclick
    //luego los agregamos en el oncreate
    ImageButton jamon;
    ImageButton queso;
    ImageButton vino;
    ImageButton ajo;

    ArrayList<Product> productList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Homefr() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Homefr.
     */
    // TODO: Rename and change types and number of parameters
    public static Homefr newInstance(String param1, String param2) {
        Homefr fragment = new Homefr();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Creamos el root con lo del inflater, para mostar el layout que hace referencia al layout
        //que le corresponde al Homefr
        View root = inflater.inflate(R.layout.fragment_homefr, container, false);



        //agregamos el imagebutton que declaramos al principio
        jamon=root.findViewById(R.id.jamonbtn);
        jamon.setOnClickListener(this);
        ajo=root.findViewById(R.id.ajobtn);
        ajo.setOnClickListener(this);
        queso=root.findViewById(R.id.quesobtn);
        queso.setOnClickListener(this);
        vino=root.findViewById(R.id.vinobtn);
        vino.setOnClickListener(this);
        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Log.d("LOGCAT:","Estamos en FRAGMENT HOME");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    //Creamos un switch para abrir un fragment distinto dependiendo de la imagen que seleccione
    @Override
    public void onClick(View v) {

        productList = new ArrayList<>();

        SQLite datos = new SQLite(getActivity());

        switch (v.getId()){
            case R.id.jamonbtn:

                Productos fragJamon = new Productos() ;
                Bundle bundleJamon = new Bundle();
                bundleJamon.putParcelableArrayList("products",  datos.listarProductosPorCat(productList, "carnes"));
                fragJamon.setArguments(bundleJamon);

                pushfragmentss(fragJamon);

                break;
            case R.id.ajobtn:
                Productos fragAjo = new Productos() ;
                Bundle bundleAjo = new Bundle();
                bundleAjo.putParcelableArrayList("products",  datos.listarProductosPorCat(productList, "ajos"));
                fragAjo.setArguments(bundleAjo);

                pushfragmentss(fragAjo);
                break;

            case R.id.quesobtn:
                Productos fragQueso = new Productos() ;
                Bundle bundleQueso = new Bundle();
                bundleQueso.putParcelableArrayList("products",  datos.listarProductosPorCat(productList, "quesos"));
                fragQueso.setArguments(bundleQueso);

                pushfragmentss(fragQueso);
                break;
            case R.id.vinobtn:
                Productos fragVino = new Productos() ;
                Bundle bundleVino = new Bundle();
                bundleVino.putParcelableArrayList("products",  datos.listarProductosPorCat(productList, "vinos"));
                fragVino.setArguments(bundleVino);

                pushfragmentss(fragVino);
                break;
        }
        //aqui vamos a agregar lo que debe ocurrir al pulsar el boton para abrir el otro fragment
        //creamos un objeto de tipo fragment
        /*Fragment fragmento = new Productos();
        android.app.FragmentManager fragmentManager =getActivity().getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,fragmento).commit();*/

    }

    //Metodo para pasar de un fragment a otro
    public void pushfragmentss(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor,fragment).addToBackStack(null).commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
