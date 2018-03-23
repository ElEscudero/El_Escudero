package com.local.el_escudero.fragmentsPrincipal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.local.el_escudero.R;
import com.local.el_escudero.fragmentsPrincipal.fragmentscat.Registrarse;
import com.local.el_escudero.model.Usuarios;
import com.local.el_escudero.sqlLite.SQLite;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link user.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnLogin;
    private Button btnRegister;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public user() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user.
     */
    // TODO: Rename and change types and number of parameters
    public static user newInstance(String param1, String param2) {
        user fragment = new user();
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

        View v = inflater.inflate(R.layout.fragment_user,container,false);

        editTextEmail = v.findViewById(R.id.editTextEmail);
        editTextPassword = v.findViewById(R.id.editTextPassword);

        btnLogin = v.findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextEmail.getText().toString().isEmpty() && !editTextPassword.getText().toString().isEmpty() &&
                        editTextEmail.getText().toString().contains("@")){
                    String email_buscado=editTextEmail.getText().toString();
                    String password_buscado = editTextPassword.getText().toString();
                    Usuarios u1 = null;
                    Usuarios u2 = null;
                    SQLite sq = new SQLite(getActivity());
                    u1=sq.buscarPorEmail(email_buscado);
                    u2=sq.buscarPorPassword(password_buscado);

                    if(u1 != null && u2 != null){
                        Toast.makeText(getActivity(),"Perfecto has podido iniciar sesion",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getActivity(),"No has podido iniciar sesion, comprueba bien tus datos ",Toast.LENGTH_LONG).show();
                    }
/*
                    if(editTextEmail.getText().toString().equals())
*/
                }
                else{
                    Toast.makeText(getActivity(),"Introduce correctamente el email o la password",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister = v.findViewById(R.id.buttonRegistro);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pushfragmentss(new Registrarse());
                // METODO AL HACER CLICK EN EL BOTON DE REGISTRO

            }
        });

        //btnCarrito=v.findViewById(R.id.buttonCarrito);
        // btnCarrito.setOnClickListener(this);

        return v;



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
            Log.d("LOGCAT:","Estamos en FRAGMENT USER");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public void pushfragmentss(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor,fragment).addToBackStack(null).commit();
    }



}
