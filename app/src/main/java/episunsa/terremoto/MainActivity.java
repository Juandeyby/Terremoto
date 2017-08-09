package episunsa.terremoto;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    ExpandableListView expandableListView;

    List<String> langs;
    Map<String, List<String>> topics;

    private RequestQueue rqt;
    private String url = "http://desastrererremoto.esy.es/proceso.php";
    private Context ctx;
    private StringRequest strq;

    private final int REQUEST_ACCES_FINE = 0;

    EditText imput;
    String datos_usuario = "";
    Date date = new Date();

    GoogleMap mGoogleMap;
    Marker marcador;
    MapView mapView;
    double lat = 0.0;
    double lng = 0.0;

    Toolbar mActionBarToolbar;

    ImageButton imgButton1;
    ImageButton imgButton2;
    ImageButton imgButton3;
    ImageButton imgButton4;
    ImageButton imgButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Datos Personales");
        builder.setIcon(R.mipmap.marcador);
        builder.setMessage("Ingrese su nombre por favor");

        ctx = MainActivity.this;
        rqt = Volley.newRequestQueue(ctx);

        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setIcon(R.mipmap.marcador);

        imgButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imgButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imgButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imgButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imgButton5 = (ImageButton) findViewById(R.id.imageButton5);
        mapView = (MapView) findViewById(R.id.mapView);
        imput = new EditText(this);
        builder.setView(imput);


        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!imput.getText().toString().equals("")){
                    datos_usuario = imput.getText().toString();
                    Toast.makeText(getApplicationContext(), "Gracias", Toast.LENGTH_LONG).show();
                    crear();
                } else {
                    Toast.makeText(getApplicationContext(), "No se ingreso datos, Reinicie la aplicacion para ingresar los datos", Toast.LENGTH_LONG).show();
                }
            }
        });


        AlertDialog ad = builder.create();
        ad.show();

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    private void crear() {

        strq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                    }
                },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();
                date = new Date();
                parametros.put("dato", datos_usuario);
                parametros.put("date", date + "");
                parametros.put("gps", lat + ", " + lng);
                parametros.put("id_campo", "1");
                parametros.put("operacion", "c");

                return parametros;
            }
        };

        rqt.add(strq);

    }

    private void actualizar() {

        StringRequest strq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("dato", datos_usuario);
                parametros.put("date", date + "");
                parametros.put("gps", lat + ", " + lng);
                parametros.put("id_campo", "1");
                parametros.put("operacion", "u");

                return parametros;
            }
        };

        rqt.add(strq);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume(){
        super.onResume();

        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAppIntent = new Intent(MainActivity.this, Terre1.class);
                startActivity(goToAppIntent);
            }
        });

        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAppIntent = new Intent(MainActivity.this, Erupcion1.class);
                startActivity(goToAppIntent);
            }
        });

        imgButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAppIntent = new Intent(MainActivity.this, Inundacion1.class);
                startActivity(goToAppIntent);
            }
        });

        imgButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAppIntent = new Intent(MainActivity.this, Deslizamiento1.class);
                startActivity(goToAppIntent);
            }
        });

        imgButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAppIntent = new Intent(MainActivity.this, Tsunami.class);
                startActivity(goToAppIntent);
            }
        });

    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miPosion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mGoogleMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi Posicion Actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marcador)));
        mGoogleMap.animateCamera(miPosion);
        if (!datos_usuario.equals(""))
            actualizar();
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener LocListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCES_FINE);
            }
        } else{
            // do something for phones running an SDK before lollipop
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 15000, 0, LocListener);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        miUbicacion();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent goToAppIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                goToAppIntent = new Intent(MainActivity.this, Ayuda.class);
                startActivity(goToAppIntent);
                return true;
            case R.id.mochila:
                goToAppIntent = new Intent(MainActivity.this, Mochila.class);
                startActivity(goToAppIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}