package fr.yamishadow.gsbandroid.vue;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.yamishadow.gsbandroid.R;

public class MainActivity extends Activity {

    Button connexion;
    EditText login,mdp;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.identifiant);
        mdp = (EditText) findViewById(R.id.password);
        connexion = (Button) findViewById(R.id.buttonConnexion);
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
                    //faire la connexion a la bdd
                }
            }
        });
    }
}

