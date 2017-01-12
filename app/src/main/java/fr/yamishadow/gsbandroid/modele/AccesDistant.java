package fr.yamishadow.gsbandroid.modele;


import android.util.Log;

import org.json.JSONArray;

import fr.yamishadow.gsbandroid.outils.AccesHTTP;
import fr.yamishadow.gsbandroid.outils.AsyncResponse;

/**
 * Created by Dell on 10/01/2017.
 */

public class AccesDistant implements AsyncResponse {

    String SERVERADRR = "http://*** A DEFINIR ***/GSB/serveurGsb.php";
    private String[] tabMessage = {"enreg","auth"};

    public AccesDistant(){
    }

    @Override
    public void processFinish(String output) {
        Log.d("***", "**** AccesDistant " + output);
        String[] message = output.split("%");
        if(message.length > 1) {
            // si taille du message superieur a 1
            if (tabMessage[1].equals(message[0])){
                Log.d("AUTHENTIFICATION", "*****" + message[0]);

                //action a realiser si le message contient auth
            }
            else if(tabMessage[0].equals(message[0])) {
                // si tab message n'est pas egal a message de 0
                Log.d("ELSE AUTHENTIFICATION", "****** tab=" + tabMessage[2] + " ******* message=" + message[0]);
            }
            else{
                Log.d("ELSE MESSAGE CONTIENT", "*****" + message[0]);
            }

        }
        else{
            Log.d("MESSAGE < 1", "*****" + message);
        }
    }

    /**
     * methode qui envoie le profil à la page php
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADRR);
    }
}