package fr.yamishadow.gsbandroid.vue;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.yamishadow.gsbandroid.R;
import fr.yamishadow.gsbandroid.outils.JSONParser;

public class MainActivity extends Activity {

    Button b;
    Button connexion;
    EditText login,mdp;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    /** url pour s'authentifier ,si vous utiliser EasyPHP ou
     Wamp et vous tester avec l'émulateur
     vous devez mettre 10.0.2.2 au lieu de localhost
     ou 127.0.0.1 dans l'url ,
     sinon si vous êtes connecté à un ordinateur distant,
     mettez l'url comme il est **/

    private static String SERVERADRR ="http://10.0.2.2/GSB/serveurGsb.php";
    //JSON Node names
    private static final String TAG_SUCCESS = "success";
    AlertDialog.Builder alert;
    JSONObject json;
    int success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(EditText) findViewById(R.id.identifiant);
        mdp=(EditText) findViewById(R.id.password);
        connexion=(Button) findViewById(R.id.buttonConnexion);
        connexion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(login.getText().toString().equals("")||mdp.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this,"Vous devez remplir tous les champs",Toast.LENGTH_LONG).show();

                }
                else
                {
                    CreateNewProduct user = new CreateNewProduct();
                    user.execute();
                }
            }
        });
    }

    public class CreateNewProduct extends AsyncTask<String, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Login");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            String user = login.getText().toString();
            String pass = mdp.getText().toString();


            List params = new ArrayList();
            params.add(new BasicNameValuePair("login", user));
            params.add(new BasicNameValuePair("mdp", pass));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = null;
            try {
                json = jsonParser.makeHttpRequest(SERVERADRR,"POST",params);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // check log cat fro response
            Log.d("Create Response", json.toString());
            // check for success tag
            try {
                success = json.getInt(TAG_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (success == 1) {
                // successfully created product
                Intent i = new Intent(MainActivity.this, MenuActivity.class);
                i.putExtra("login", user);
                finish();
                startActivity(i);

            } else {
                alert=new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Login ou mot de passe incorrect.Réessayer");
                alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();

            if (success==0)
            {
                alert.show();
            }
        }
    }

}

