package com.local.el_escudero.fragmentsPrincipal.fragmentscat;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.local.el_escudero.R;
import com.local.el_escudero.adapters.ProductAdapter;
import com.local.el_escudero.fragmentsPrincipal.CarritoFragment2;
import com.local.el_escudero.model.Carrito;
import com.local.el_escudero.model.Product;


import java.util.ArrayList;
import java.util.List;


public class Productos extends Fragment {


    // TODO: Abrimos conexion instanciando un objeto de tipo SQLiteDatabase
    SQLiteDatabase db = null;

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    LinearLayoutManager linearLayoutManager;

    Button btnAgregarCarrito;

    TextView pName;
    TextView pPrice;


    ArrayList<Product> productList;
    ArrayList<Carrito> carritoList;
    String Cat;

    public Productos() {
        // Requerido en blanco
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnAgregarCarrito = view.findViewById(R.id.buttonBuy);
        btnAgregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datos="";
                carritoList=new ArrayList<Carrito>();
                Toast.makeText(getActivity(),"Agregado al carrito",Toast.LENGTH_LONG).show();

                for (int i = 0; i < recyclerView.getChildCount(); i++) {

                    //ProductAdapter.ProductViewHolder holder = (ProductAdapter.ProductViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
//            TextView product = recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.product_name);
//            product.getText();

                    CheckBox product_selected = recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.product_select);

                    if (product_selected.isChecked()){
                        TextView product = recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.product_name);
                        String producto = product.getText().toString();
                        TextView precio = recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.product_price);
                        String valor = precio.getText().toString();
                        Carrito c =new Carrito(producto,valor);
                        carritoList.add(c);
                        //Para comprobar si lo guarda en el Array
                        for(Carrito c2: carritoList){
                            datos+="Producto: "+c2.getNombre()+"Precio:"+c2.getPrecio()+"\n";
                            Log.d("TAG:","Datos del Array carritolist: "+ datos);
                        }
                    }
                }
                CarritoFragment2 fragJamon = new CarritoFragment2() ;
                Bundle bundleJamon = new Bundle();
                bundleJamon.putParcelableArrayList("array",carritoList);
                fragJamon.setArguments(bundleJamon);
                Log.d("TAG:","Bundle: "+ bundleJamon);
            }
        });
        // Obtener el componente de las vista para poder manejarlo
        recyclerView = view.findViewById(R.id.rv);
        Bundle bundle = getArguments();

        if (bundle == null){
            productList = new ArrayList<Product>();
            // populate books from remote server or local json
        }else{
            productList = bundle.getParcelableArrayList("products");
        }

        // Usamos un LinearLayoutManager par manejar el posicionamiento de los elementos
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        // Para que no cambie de tamañp
        recyclerView.setHasFixedSize(true);


        // TODO: Usamos el constructor de nuestro adaptador "ProductAdapter"
        productAdapter = new ProductAdapter(productList, getActivity());
        recyclerView.setAdapter(productAdapter);


        return view;
    }

    private void initProductList() {

        productList = new ArrayList<>();


        String img1 = "https://i.pinimg.com/originals/1e/25/6e/1e256e3278a358d65ef33e9ab0e53472.png";
        String img2 = "http://www.elescudero.es/gestorweb/data/productosWeb/2/img/QUESO%20MANCHEGO%20A%C3%91EJO%20GRAN%20RESERVA%20EL%20ESCUDERO.JPG";
        String img3 = "http://www.elescudero.es/gestorweb/data/productosWeb/6/img/QUESO%20PURO%20DE%20OVEJA%20SEMI%20CURADO.JPG";
        String img4 = "http://www.elescudero.es/gestorweb/data/productosWeb/1/img/QUESO%20DE%20OVEJA%20CURADO%20EN%20MANTECA%20EL%20ESCUDERO.JPG";
        String img5 = "http://www.elescudero.es/gestorweb/data/productosWeb/1/img/QUESO%20DE%20OVEJA%20CURADO%20EN%20MANTECA%20EL%20ESCUDERO.JPG";
        String img6 = "http://www.elescudero.es/gestorweb/data/productosWeb/1/img/QUESO%20DE%20OVEJA%20CURADO%20EN%20MANTECA%20EL%20ESCUDERO.JPG";

       /* productList.add(new Product("Product1", (float) 12.4, img1));
        productList.add(new Product("Product2", (float) 3.7, img2));
        productList.add(new Product("Product3", (float) 6.6, img3));
        productList.add(new Product("Product4", (float) 12.4, img4));
        productList.add(new Product("Product5", (float) 3.7, img5));
        productList.add(new Product("Product6", (float) 6.6, img6));*/

    }
}
