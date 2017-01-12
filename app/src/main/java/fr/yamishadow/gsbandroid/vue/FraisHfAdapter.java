package fr.yamishadow.gsbandroid.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import fr.yamishadow.gsbandroid.R;
import fr.yamishadow.gsbandroid.controleur.Global;
import fr.yamishadow.gsbandroid.modele.FraisHf;
import fr.yamishadow.gsbandroid.outils.Serializer;

public class FraisHfAdapter extends BaseAdapter {

	ArrayList<FraisHf> lesFrais ; // liste des frais du mois
	LayoutInflater inflater ;
	Integer key ;  // annee et mois (clé dans la liste)
	Context context ; // contexte pour gérer la sérialisation
	
	/**
	 * Constructeur de l'adapter pour valoriser les propriétés
	 * @param context
	 * @param lesFrais
	 * @param key
	 */
	public FraisHfAdapter(Context context, ArrayList<FraisHf> lesFrais, Integer key) {
		inflater = LayoutInflater.from(context) ;
		this.lesFrais = lesFrais ;
		this.key = key ;
		this.context = context ;
	}
	
	/**
	 * retourne le nombre d'éléments de la listview
	 */
	@Override
	public int getCount() {
		return lesFrais.size() ;
	}

	/**
	 * retourne l'item de la listview à un index précis
	 */
	@Override
	public Object getItem(int index) {
		return lesFrais.get(index) ;
	}

	/**
	 * retourne l'index de l'élément actuel
	 */
	@Override
	public long getItemId(int index) {
		return index;
	}

	/**
	 * structure contenant les éléments d'une ligne
	 */
	private class ViewHolder {
		TextView txtListJour ;
		TextView txtListMontant ;
		TextView txtListMotif ;
        ImageButton imgSuppr;
	}
	
	/**
	 * Affichage dans la liste
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			holder = new ViewHolder() ;
            // la ligne est construite à partir de la structure de la ligne (récupéré dans layout_liste)
			convertView = inflater.inflate(R.layout.layout_liste, null) ;
			holder.txtListJour = (TextView)convertView.findViewById(R.id.txtListJour) ;
			holder.txtListMontant = (TextView)convertView.findViewById(R.id.txtListMontant) ;
			holder.txtListMotif = (TextView)convertView.findViewById(R.id.txtListMotif) ;
            holder.imgSuppr = (ImageButton)convertView.findViewById(R.id.buttonSuppr) ;
            // affecte un tag (un indice) à la ligne (qui permettra de la repérer facilement)
			convertView.setTag(holder) ;
		}else{
            // si la ligne existe déjà, holder récupère le contenu (à partir de son tag, donc son indice)
			holder = (ViewHolder)convertView.getTag();
		}
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
		holder.txtListJour.setText(lesFrais.get(position).getJour().toString()) ;
		holder.txtListMontant.setText(lesFrais.get(position).getMontant().toString()) ;
		holder.txtListMotif.setText(lesFrais.get(position).getMotif()) ;
        holder.imgSuppr.setTag(position);
        // gestion de l'événement clic sur le bouton de suppression
        holder.imgSuppr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // code a exécuter sur le clic d'un bouton suppr
                // récupère l'indice de la ligne concernée
                int position = (Integer)v.getTag() ;
                // supprime le frais HF de l'arrayList fraisHF (Grace a l'indice)
                lesFrais.remove(position);//Global.listFraisMois.get(key).supprFraisHf(position);
                // envoi la supression
				Serializer.serialize(Global.filename, Global.listFraisMois,context) ;
                // rafraichi la liste visuelle
                notifyDataSetChanged() ;
            }
        }) ;
		return convertView ;
	}
	
}
