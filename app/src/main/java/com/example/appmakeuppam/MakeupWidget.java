package com.example.appmakeuppam;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;


public class MakeupWidget extends AppWidgetProvider {
    private static final String SHARED_PREFERENCES_FILE =
            "com.example.android.appwidgetexemplo";
    private static final String COUNT_KEY = "contador";


    private void updateWidget(Context context,
                              AppWidgetManager appWidgetManager,
                              int appWidgetId) {

        // Retorna o contador.
        SharedPreferences preferencias =
                context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
        int count = preferencias.getInt(COUNT_KEY + appWidgetId, 0);
        count++;

        // Define o objeto RemoteView
        /*
        Uma classe que descreve uma hierarquia de visualização que pode ser exibida em outro processo.
        A hierarquia é inflada a partir de um arquivo de recurso de layout, e esta classe fornece algumas
        operações básicas para modificar o conteúdo da hierarquia inflada.
         */
        //int m = new UserActivity().rating();

        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        views.setProgressBar(R.id.avaliacaoWidget, 5, 3, false);
        // Salva a contagem em shared preferences
        SharedPreferences.Editor preferenciasEditor = preferencias.edit();
        preferenciasEditor.putInt(COUNT_KEY + appWidgetId, count);
        preferenciasEditor.apply();

        // configura 0 botão de update
        Intent intentUpdate = new Intent(context, MakeupWidget.class);

        // A ação da intent deve ser um widget update - intent Filter deve estar definido no manifesto
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // Inclui o id do widget para ser atualizado como extra da intent
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);


        // Envolve tudo em uma pending intent para enviar um brodcast.
        // Use o ID do widget do aplicativo como o código de solicitação (terceiro argumento) para que
        // cada intent seja único.
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        // Associa a pending intent ao clique do botão
        views.setOnClickPendingIntent(R.id.btnUpdate, pendingUpdate);

        // atualiza o widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // Atualiza todos os widgetes pendentes
        for (int appWidgetId : appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId);
        }
    }
}
