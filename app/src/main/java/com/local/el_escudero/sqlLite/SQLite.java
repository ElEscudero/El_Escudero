package com.local.el_escudero.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.local.el_escudero.R;
import com.local.el_escudero.constants.Constants;
import com.local.el_escudero.model.Carrito;
import com.local.el_escudero.model.Product;
import com.local.el_escudero.model.Usuarios;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by preto on 19/03/2018.
 */

public class SQLite extends SQLiteOpenHelper{

    public SQLiteDatabase db;

    String insertIntoProduct1;
    String insertIntoProduct2;
    String insertIntoProduct3;
    //Ahora creamos la tabla usuarios, no te olvides que debes dejar espacios y poner las comas
    //en lo que escribes dentro de las comillas
    public static final String CREATE_TABLE_USERS=" CREATE TABLE "+ Constants.TABLE_USERS + " ("+
            Constants.IDU_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Constants.NAME_FIELD+" TEXT NOT NULL, "+
            Constants.ADDRESS_FIELD+" TEXT NOT NULL, "+
            Constants.EMAIL_FIELD+" TEXT NOT NULL, "+
            Constants.PASSWORD_FIELD+" TEXT NOT NULL, "+
            Constants.NUMBER_FIELD+" TEXT NOT NULL)"
            ;

    private final String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + Constants.PRODUCTS_TABLE +
            " (" + Constants.ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Constants.DESCRIPTION_FIELD + " TEXT NOT NULL, " +
            Constants.CATEGORY_FIELD + " TEXT NOT NULL, " +
            Constants.PRODUCT_FIELD + " TEXT NOT NULL, " +
            Constants.PRICE_FIELD + " TEXT NOT NULL, " +
            Constants.PHOTO_FIELD + " TEXT NOT NULL)";

    private final String DELETE_TABLE_USERS = "DROP TABLE IF EXISTS "+ Constants.TABLE_USERS;

    private final String DELETE_DEVELOPERS_TABLE = "DROP TABLE IF EXISTS " + Constants.PRODUCTS_TABLE;


    public SQLite(Context context){
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);

        db = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_TABLE_USERS);
        // TODO: Añadimos tres productos
        insertSomeProducts(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_DEVELOPERS_TABLE);
        db.execSQL(DELETE_TABLE_USERS);
        onCreate(db);
    }

    //TODO: Creamos un metodo para guardar los contactos en la bd
    public long guardarContacto(Usuarios c ){
        ContentValues initialValues=new ContentValues();
        initialValues.put(Constants.NAME_FIELD, c.getNombre());
        initialValues.put(Constants.ADDRESS_FIELD, c.getDireccion());
        initialValues.put(Constants.NUMBER_FIELD, c.getTelefono());
        initialValues.put(Constants.EMAIL_FIELD, c.getEmail());
        initialValues.put(Constants.PASSWORD_FIELD, c.getPassword());
        return db.insert(Constants.TABLE_USERS, null, initialValues);
    }

    public void insertSomeProducts(SQLiteDatabase db){

        String products =  "INSERT INTO `PRODUCTOS` (NOMBRE,CAT,DESCRIPCION,IMAGEN,PRECIO) VALUES ('QUESO MANCHEGO D.O. AÑEJO GRAN RESERVA EL ESCUDERO','quesos','Este queso está elaborado con leche pasteurizada 100% de ovejas de pura raza manchega, con una curación natural de 10 a 12 meses en cueva. Durante el proceso de maduración, el queso es cepillado y untado con 6 a 8 manos de aceite de oliva virgen extra y volteado a mano uno a uno hasta que el queso alcanza su momento óptimo de curación. La cueva proporciona unas condiciones de temperatura y humedad ideales para la maduración de este queso, aportando una textura, aromas y sabor muy exclusivo por su curación natural. El auténtico sabor del queso manchego.  Corteza dura, pasta firme de color marfil.  Se saca a la venta con una curación total de 14 a 18 meses. ¡ La estrella de nuestros quesos ! Formatos 2 Kg y 3 Kg aprox.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15740807_222382018210880_3213232680370212581_n.jpg?oh=f1bec8e05e7db0e2a3d6d4e22a6ebe26&oe=5B2D456D',16.9),      "+
                "('QUESO MANCHEGO D.O. CURADO EL ESCUDERO (de 7 a 9 meses)','quesos','Este queso está elaborado con leche pasteurizada 100% de ovejas de pura raza manchega, con una curación natural de 3 a 4 meses en cueva. Durante el proceso de maduración, el queso es untado con 2 o 3 manos de aceite de oliva virgen extra, para favorecer su aroma y sabor muy exclusivo. Corteza dura, pasta firme de color marfil claro. Se saca a la venta con una curación total de 7 a 9 meses. Formatos 3 Kg y 2 Kg aprox.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15380671_211325489316533_9146845320518398404_n.jpg?_nc_cat=0&oh=a720cc3b88011d19d550d0e140d7a766&oe=5B4AFA65',14.9),                                                                                                                                                                                                                                                                                                                                                                             "+
                "('QUESO MANCHEGO D.O. SEMI CURADO EL ESCUDERO (de 4 a 6 meses)','quesos','Este queso está elaborado con leche pasteurizada 100% de ovejas de pura raza manchega, con una curación natural de 1 a 2 meses en cueva. Capaz de satisfacer a los paladares más exigentes. Sabor agradablemente suave. Color interior marfil blanco. Formatos 2kg y 3kg aproximadamente. Se saca a la venta con una curación total de 4 a 6 meses.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15338870_211325472649868_7839018586805196141_n.jpg?_nc_cat=0&oh=b3271611c8e60d81ca3c1416805b0d25&oe=5B3622DA',13.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                         "+
                "('QUESO PURO DE OVEJA SEMI CURADO','quesos','Queso puro de oveja, elaborado con leche pasteurizada y con un periodo de curación de 2 a 4 meses.  Capaz de satisfacer a los paladares más exigentes. Sabor : agradablemente suave. Aroma: característico de queso de oveja semicurado. Color: interior blanco marfil, corteza marrón. Consistencia: compacto al corte y textura firme. Formatos 1 Kg, 2 Kg y 3 Kg ','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15697479_222382024877546_2495548008120273700_n.jpg?_nc_cat=0&oh=3daaf37ae00e66ec58d58e495af8f518&oe=5B3D85E0',12.5),                                                                                                                                                                                                                                                                                                                                                                                                                                                                   "+
                "('QUESO PURO DE OVEJA TIERNO','quesos','Queso Puro de Oveja elaborado con leche pasteurizada y con un periodo de curación de hasta 20 días. Capaz de satisfacer los paladares más exigentes. Sabor: agradablemente suave. Aroma: característico de queso de oveja tierno. Color: interior blanco, corteza blanca. Consistencia: compacto al corte y textura firme. Formatos 1 Kg, 2 Kg y 3 Kg','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15698084_222382021544213_7477610045272731079_n.jpg?_nc_cat=0&oh=490f4880b00b3d040d5eb3c5fac58d3a&oe=5B3126E6',11.4),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "+
                "('QUESO DE OVEJA CURADO EN MANTECA EL ESCUDERO','quesos','Elaborado con leche pasteurizada de oveja, envejecido en cueva de 6 a 8 meses, untado en manteca de cerdo ibérico como se hacía desde la edad media. Puesto que no había cámaras para conservarlos, se usaba la manteca para favorecer la conservación del queso, lo que le aporta un sabor único y muy especial. Corteza blanda, pasta firme y mantecosa de color marfil intenso. Se saca a la venta con una curación total de 10 a 12 meses. Formatos de 1 Kg, 2 Kg y 3 Kg.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15349658_211326625983086_265375040782774482_n.jpg?_nc_cat=0&oh=d479cdc42dfa030debc588f82ac77852&oe=5B3F9860',15.9),                                                                                                                                                                                                                                                                                                                                                "+
                "('PedroHeras Tinto Tempranillo','vinos','Elegante, de color rojo rubí y reflejos violáceos que denotan su juventud. Voluptuosos aromas a bayas, frutas del bosque, regaliz y moras. Equilibrada acidez. Ideal para carnes rojas, estofados, pastas, pizzas, quesos y patés. Servir a 15 – 18 ºC.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/29496929_410505502731863_1412497840807527805_n.jpg?_nc_cat=0&oh=93741a25d14475a74f2cfde87c8702e0&oe=5B475113',2.8),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       "+
                "('Aique Blanco Macabeo','vinos','De color amarillo pajizo con reflejos verdes. Presenta aromas a frutas blancas y flores. Intenso y redondo en boca con una acidez muy bien integrada. Recomendado para tapas, pastas, pescados y mariscos. Servir a 6 – 8 ºC.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/29425430_410505456065201_3367102120882449018_n.jpg?_nc_cat=0&oh=d7ced52627ea509fcec1ece6e6413300&oe=5B43FFE3',2.4),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "+
                "('Aique Rosado Tempranillo','vinos','Vino rosado brillante y limpio, con aromas a flores blancas y frutas rojas. Tiene un sabor suave y fresco muy afrutado. Ideal para acompañar con verduras, ensaladas, carnes blancas, cremas frías, pasta, charcutería y tapas. Servir a 8 – 10 ºC.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/29388782_410505459398534_5775991690073582361_n.jpg?_nc_cat=0&oh=43d5ebbf4e59b77ecc080e7149af4adf&oe=5B2B1B6C',2.4),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               "+
                "('Infinitus Blanco Moscatel','vinos','Fresco y sabroso, presenta en su cata un color amarillo limón. Limpio, brillante y cristalino con densa lágrima y ligeramente carbónico. En nariz, intensidad aromática alta, con rasgos varietales claros. Destacan los aromas de pétalo de rosa, jazmín y flores blancas y frutas como el melocotón y el albaricoque. Muy complejo y agradable. Entrada suave en boca, con recorrido sedoso y dulce a la vez.  Ideal para acompañar cualquier tipo de pinchos y tapas donde predominen los quesos y los pates de ave, con comida china, japonesa e hindú y con cualquier postre.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/29386540_410505499398530_1791841009277094316_n.jpg?_nc_cat=0&oh=e76f2ae5edb6e42a7465e30fdd551ad4&oe=5B2E94D2',2.9),                                                                                                                                                                                                                                                               "+
                "('Finca Antigua Crianza','vinos','De color cereza y ribete azulado, se cría doce meses en barricas de roble americano y francés  y se elabora con tempranillo, cabernet sauvignon, merlot y syrah. En nariz tiene recuerdos cremosos, de pastelería y notas lácteas. Fondo mineral con recuerdos balsámicos. En boca muestra un desarrollo esférico de gran volumen, con un final largo y postgusto cremoso.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/29432416_410505552731858_5064422866978750904_n.jpg?oh=a8d42fbe30496df8ec660eaa3615747b&oe=5B48441F',7.5),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     "+
                "('Estola Gran Reserva 2004','vinos','De intenso color rubí. Complejo en nariz. Magnífico buqué de crianza con aromas especiados y tonos balsámicos. Pleno y bien estructurado. Paladar aterciopelado.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/29386326_410505526065194_262798372250128835_n.jpg?_nc_cat=0&oh=52b0d36ba088e2ef10ea93281c08f475&oe=5B466632',7.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   "+
                "('Lomo Ibérico Bellota','carnes','Elaborado de forma artesanal a partir de cerdos ibéricos, alimentados con bellota y cereales al aire libre.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15284994_212350985880650_8347293963119572460_n.jpg?_nc_cat=0&oh=ffdc8980b14fe95d0a61329e86f585dc&oe=5B38599A',39.8),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "+
                "('Salchichón Ibérico Bellota','carnes','Salchichón ibérico de bellota procedente de carnes de alta calidad del cerdo ibérico, alimentado con bellotas. Destaca por la naturalidad de todos los elementos que forman su adobo, sal, especias naturales y aditivos y la inclusión en la mezcla de carnes de algunas de las piezas más nobles del cerdo ibérico, especiada con pimienta y una combinación exclusiva de especias, para obtener el sabor intenso y exquisito.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15401015_212351022547313_4097130590333702722_n.jpg?_nc_cat=0&oh=3ce865057af65931a1b530a0abc80889&oe=5B41AB15',14.9),                                                                                                                                                                                                                                                                                                                                                                                                              "+
                "('Chorizo Ibérico Bellota','carnes','Chorizo ibérico de bellota procedente de carnes de alta calidad del cerdo ibérico, alimentado con bellotas. Destaca por la naturalidad de todos los elementos que forman su adobo, sal, especias naturales y aditivos y la inclusión en la mezcla de carnes de algunas de las piezas más nobles del cerdo ibérico, especiada con pimentón y una combinación exclusiva de especias, para obtener el sabor intenso y exquisito.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15220017_212350975880651_4310784210816332918_n.jpg?_nc_cat=0&oh=a9d432d4ead4767dbea526d534ee7d0a&oe=5B40DAD7',14.9),                                                                                                                                                                                                                                                                                                                                                                                                                    "+
                "('Cabezada Lomo','carnes','Pieza de la cabezada de la cinta de lomo elaborada bajo los mismos parámetros que el Lomo Embuchado, de gran aroma y sabor debido a la infiltración de grasa. Producto 100% natural.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15319049_212350982547317_3841080780365105848_n.jpg?oh=b509ac90c31ab0100c84aacef19639ed&oe=5B49BC42',15.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 "+
                "('Jamón Duroc Pezuña Negra','carnes','Este jamón se caracteriza por tener el punto justo de sal que necesita y es capaz de ofrecer un sabor muy interesante y característico. Posee una curación de más de 15 meses dándole un aroma especial.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15578446_215294595586289_8757929491895728693_n.jpg?_nc_cat=0&oh=67b77cdd1f7992807640f4b769e27abe&oe=5B4B2A16',9.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         "+
                "('Lomo Serrano Guijuelo','carnes','Lomo embuchado curado extra elaborado con cinta de lomo de cerdo blanco graso, alimentado con piensos naturales.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15390876_212351015880647_7943575433636063748_n.jpg?oh=8caf0761a586379a53df858dfb927816&oe=5B288ADB',15.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             "+
                "('Ajo Negro bolsa 2 cabezas','ajos','Ajo Negro El Escudero es solo ajo madurado 100% natural,sin conservantes, colorantes u otros aditivos químicos. Elaborado en España con Ajo Morado de Las Pedroñeras (I.G.P).  Mantiene todas las propiedades saludables del ajo fresco sin dejar mal aliento ni resultar fuerte al paladar. De sabor dulce con ligeros matices de bálsamo y regaliz.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15401139_211335565982192_5266208143214677971_n.jpg?oh=a86783bc977584545b8f03fb0fb11ad8&oe=5B3AAA4A',3.95),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      "+
                "('Ajo Negro bolsa 4 cabezas','ajos','Ajo Negro El Escudero es solo ajo madurado 100% natural,sin conservantes, colorantes u otros aditivos químicos. Elaborado en España con Ajo Morado de Las Pedroñeras (I.G.P).  Mantiene todas las propiedades saludables del ajo fresco sin dejar mal aliento ni resultar fuerte al paladar. De sabor dulce con ligeros matices de bálsamo y regaliz.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15284861_211336069315475_6342701234818788518_n.jpg?_nc_cat=0&oh=909b56d7373bf1b800280e06cd9cd49c&oe=5B731468',7.5),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             "+
                "('Tarrina Dientes Ajo Negro','ajos','Ajo Negro El Escudero es solo ajo madurado 100% natural,sin conservantes, colorantes u otros aditivos químicos. Elaborado en España con Ajo Morado de Las Pedroñeras (I.G.P).  Mantiene todas las propiedades saludables del ajo fresco sin dejar mal aliento ni resultar fuerte al paladar. De sabor dulce con ligeros matices de bálsamo y regaliz.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15284924_211336535982095_1785482883508984799_n.jpg?_nc_cat=0&oh=08ffd1bfe630267cf2f9549c5126aaaf&oe=5B32BA4B',5.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             "+
                "('Tarrina Crema Ajo Negro','ajos','Ajo Negro El Escudero es solo ajo madurado 100% natural,sin conservantes, colorantes u otros aditivos químicos. Elaborado en España con Ajo Morado de Las Pedroñeras (I.G.P).  Mantiene todas las propiedades saludables del ajo fresco sin dejar mal aliento ni resultar fuerte al paladar. De sabor dulce con ligeros matices de bálsamo y regaliz.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15355556_211338049315277_7585330231094075205_n.jpg?oh=c21be3622e01e5fda0395a28396552b0&oe=5B398E36',6.5),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       "+
                "('Ristra Ajo Morado','ajos','El ajo morado de Las Pedroñeras se diferencia de otras variedades por su color, sabor, intenso aroma y alto contenido en alicina. El ajo morado, es el mejor aderezo de la cocina mediterránea, trasmitiendo a los platos un sabor y aroma incomparables, marcando una gran diferencia con otras variedades de ajo.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15355567_211332615982487_5810399299854990916_n.jpg?_nc_cat=0&oh=c7f37376e112061a614328c9bfdb13e4&oe=5B434B5F',6.9),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       "+
                "('Bolsa Ajo Morado 1kg','ajos','El ajo morado de Las Pedroñeras se diferencia de otras variedades por su color, sabor, intenso aroma y alto contenido en alicina. El ajo morado, es el mejor aderezo de la cocina mediterránea, trasmitiendo a los platos un sabor y aroma incomparables, marcando una gran diferencia con otras variedades de ajo.','https://scontent-mad1-1.xx.fbcdn.net/v/t1.0-9/15747889_222112614904487_4580046316984243235_n.jpg?_nc_cat=0&oh=24565dec8d453952811098bc14648e8e&oe=5B358B90',3.4)";

        db.execSQL(products);

    }


    public ArrayList  listarProductosPorCat(ArrayList<Product> listProductos, String Cat) {
        /*
        parcel.writeString(photo);
         */
        Cursor c =  db.query("Productos",
                new String[]{"Descripcion, Cat, Nombre, Precio, Imagen"}, "Cat=?", new String[]{Cat}, null, null, null) ;
        if (c != null && c.getCount() > 0) {
            if (c.moveToFirst()) {
                do{
                    listProductos.add(new Product(c.getString(0),
                            c.getString(1),
                            c.getString(2),
                            c.getFloat(3),
                            c.getString(4)));
                } while (c.moveToNext());
            }
        }
        return listProductos;
    }
    public ArrayList productosCarrito(ArrayList<Carrito> listCarrito){
        return listCarrito;
    }

    /**
     *
     * Insert a new record
     *
     * @param context
     * @param name
     * @param position
     * @param age
     */
    public void insertDeveloper(Context context, EditText name, Spinner position, EditText age){

        if((TextUtils.isEmpty(name.getText()) ||
                TextUtils.isEmpty(position.getSelectedItem().toString()) ||
                TextUtils.isEmpty(age.getText()))){

            Toast.makeText(context, R.string.field_empty, Toast.LENGTH_LONG).show();

        }else {

            String sql = "INSERT INTO " + Constants.PRODUCTS_TABLE +
                    " (" + Constants.PRODUCT_FIELD + ", " +
                    Constants.PRICE_FIELD + ", " +
                    Constants.PHOTO_FIELD + ") " +
                    "VALUES('" + name.getText() + "', '" +
                    position.getSelectedItem().toString() + "', '" +
                    age.getText() + "')";

            db.execSQL(sql);

            Toast.makeText(context, R.string.saved_successfully, Toast.LENGTH_LONG).show();

        }

    }

    public boolean findDeveloper(Context context, EditText name){

        boolean found = false;

        if(TextUtils.isEmpty(name.getText())){
            Toast.makeText(context, R.string.field_empty, Toast.LENGTH_LONG).show();
        }else{

            try {

                // Seleccionamos todos los registros de la tabla developers
                String sql = "SELECT * FROM " + Constants.PRODUCTS_TABLE + " WHERE " + Constants.PRODUCT_FIELD + " LIKE '" + name.getText().toString() + "'";

                Cursor c = db.rawQuery(sql, null);

                if (c != null && c.getCount() > 0) {
                    if (c.moveToFirst()) {
                        do{


                            if(name.getText().toString().equals(c.getString(1))){
                                found = true;
                                Log.d("TAG", "Developer: " + c.getString(1) + " with email: " + c.getString(2));
                                break;
                            }else{
                                found = false;
                            }
                        } while (c.moveToNext());
                    }
                }

            }catch (SQLiteException e){
                // Si ocurre error lo mostramos en la consola
                Log.d("TAG", e.getMessage());
            }

        }

        return found;

    }



    public Usuarios buscarPorEmail(String email){
        Usuarios u = null;
        Cursor c =db.query("usuarios", new String[]{"email","password"},
                "email=?", new String[]{email}, null,null,null);

        if(c.moveToNext()){
            u=new Usuarios(c.getString(0),c.getString(1));
        }
        return u;
    }

    public Usuarios buscarPorPassword(String password){
        Usuarios u = null;
        Cursor c =db.query("usuarios", new String[]{"email","password"},
                "password=?", new String[]{password}, null,null,null);

        if(c.moveToNext()){
            u=new Usuarios(c.getString(0),c.getString(1));
        }
        return u;
    }

}
