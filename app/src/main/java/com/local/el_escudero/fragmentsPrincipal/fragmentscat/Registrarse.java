package com.local.el_escudero.fragmentsPrincipal.fragmentscat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.local.el_escudero.R;
import com.local.el_escudero.model.Usuarios;
import com.local.el_escudero.sqlLite.SQLite;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registrarse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registrarse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registrarse extends Fragment implements View.OnClickListener{

    EditText nombre,email,passw,dire,tel,apellido;
    Button registrar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Registrarse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registrarse.
     */
    // TODO: Rename and change types and number of parameters
    public static Registrarse newInstance(String param1, String param2) {
        Registrarse fragment = new Registrarse();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registrarse, container, false);
        nombre=v.findViewById(R.id.txtednombre_re);
        email=v.findViewById(R.id.txtedemail_re);
        passw=v.findViewById(R.id.txtedpasword_re);
        dire=v.findViewById(R.id.txteddireccion_re);
        tel=v.findViewById(R.id.txtednumber_re);
        apellido=v.findViewById(R.id.txtedapellido_re);
        registrar=v.findViewById(R.id.btnregistrar_re);
        registrar.setOnClickListener(this);
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
            Toast.makeText(getActivity(),"Ahora rellene los campos para registrarse",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void Registrarse(){
        String n =nombre.getText().toString();
        String a=apellido.getText().toString();
        String d=dire.getText().toString();
        String t=tel.getText().toString();
        String e=email.getText().toString();
        String p=passw.getText().toString();
        //pasamos los valores que conseguimos de los editext, y creamos un objeto usuario
        //el cual vamos a agregar a la base de datos
        Usuarios u = new Usuarios(n,d,e,p,t);
        //Creamos un objeto de tipo Basede datos para llamar al metodo agregarabasedatos,
        //donde agregamos el contacto.
        SQLite bd = new SQLite(getActivity());
        //Ahora llamamos al metodo agregar a la base de datos, con el objeto db
        bd.guardarContacto(u);
        bd.close();
        Toast.makeText(getActivity(),"Has sido registrado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnregistrar_re:
                Registrarse();
                break;

        }
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
