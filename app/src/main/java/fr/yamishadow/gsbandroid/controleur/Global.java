package fr.yamishadow.gsbandroid.controleur;

import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;

import java.lang.reflect.Field;
import java.util.Hashtable;

import fr.yamishadow.gsbandroid.modele.FraisMois;

public abstract class Global {

	// tableau d'informations mémorisées
	public static Hashtable<Integer, FraisMois> listFraisMois = new Hashtable<Integer, FraisMois>() ;
	//public static ArrayList<FraisMois> listFraisMois2 = new ArrayList<>();

	// fichier contenant les informations sérialisées
	public static final String filename = new String("save.fic") ;

	/**
	 * Modification de l'affichage de la date (juste le mois et l'année, sans le jour)
	 */
	public static void changeAfficheDate(DatePicker datePicker) {
		//try {
				DatePicker dp_mes = (DatePicker) datePicker;

				int year    = dp_mes.getYear();
				int month   = dp_mes.getMonth();
				int day     = dp_mes.getDayOfMonth();

				dp_mes.init(year, month, day, new DatePicker.OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					}
				});

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
					int daySpinnerId = Resources.getSystem().getIdentifier("day", "id", "android");
					if (daySpinnerId != 0)
					{
						View daySpinner = dp_mes.findViewById(daySpinnerId);
						if (daySpinner != null)
						{
							daySpinner.setVisibility(View.GONE);
						}
					}

					int monthSpinnerId = Resources.getSystem().getIdentifier("month", "id", "android");
					if (monthSpinnerId != 0)
					{
						View monthSpinner = dp_mes.findViewById(monthSpinnerId);
						if (monthSpinner != null)
						{
							monthSpinner.setVisibility(View.VISIBLE);
						}
					}

					int yearSpinnerId = Resources.getSystem().getIdentifier("year", "id", "android");
					if (yearSpinnerId != 0)
					{
						View yearSpinner = dp_mes.findViewById(yearSpinnerId);
						if (yearSpinner != null)
						{
							yearSpinner.setVisibility(View.VISIBLE);
						}
					}
				} else { //Older SDK versions
					Field f[] = dp_mes.getClass().getDeclaredFields();
					for (Field field : f)
					{
						if(field.getName().equals("mDayPicker") || field.getName().equals("mDaySpinner"))
						{
							field.setAccessible(true);
							Object dayPicker = null;
							try {
								dayPicker = field.get(dp_mes);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							((View) dayPicker).setVisibility(View.GONE);
						}

						if(field.getName().equals("mMonthPicker") || field.getName().equals("mMonthSpinner"))
						{
							field.setAccessible(true);
							Object monthPicker = null;
							try {
								monthPicker = field.get(dp_mes);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							((View) monthPicker).setVisibility(View.VISIBLE);
						}

						if(field.getName().equals("mYearPicker") || field.getName().equals("mYearSpinner"))
						{
							field.setAccessible(true);
							Object yearPicker = null;
							try {
								yearPicker = field.get(dp_mes);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
							((View) yearPicker).setVisibility(View.VISIBLE);
						}
					}
				}
		//    }
		//} catch (SecurityException e) {
		//    Log.d("ERROR", e.getMessage());
		//} catch (IllegalArgumentException e) {
		 //   Log.d("ERROR", e.getMessage());
		//} catch (IllegalAccessException e) {
		 //   Log.d("ERROR", e.getMessage());
	//	}
	}
	
}
