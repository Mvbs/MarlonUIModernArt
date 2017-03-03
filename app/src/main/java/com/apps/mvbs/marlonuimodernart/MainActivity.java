package com.apps.mvbs.marlonuimodernart;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv1,tv2,tv3,tv4,tv5;
    private int colorTv1,colorTv2,colorTv3,colorTv4,colorTv5;
    private SeekBar seekBar;

    //Menu Itens
    private int ALERT_MESSAGE_INFO = 0;
    public static final String MOMA_URL = "http://www.moma.org";

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializacao dos objetos da aplicacao
            //seekbar
            seekBar = (SeekBar) findViewById(R.id.id_seekbar);

            //Textview
            tv1 = (TextView) findViewById(R.id.tv1_coluna1);
            tv2 = (TextView) findViewById(R.id.tv2_coluna1);
            tv3 = (TextView) findViewById(R.id.tv1_coluna2);
            tv4 = (TextView) findViewById(R.id.tv2_coluna2);
            tv5 = (TextView) findViewById(R.id.tv3_coluna2);

            //Color Resources
            colorTv1 = getResources().getColor(R.color.colorC1Item1, getTheme());
            colorTv2 = getResources().getColor(R.color.colorC1Item2, getTheme());
            colorTv3 = getResources().getColor(R.color.colorC2Item1, getTheme());
            colorTv4 = getResources().getColor(R.color.colorC2Item2, getTheme());
            colorTv5 = getResources().getColor(R.color.colorC2Item3, getTheme());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mI = getMenuInflater();
        mI.inflate( R.menu.menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuOverFlow:
                //Make the Dialog
                final Dialog dialogo = new Dialog(this);
                dialogo.setTitle( getResources().getString(R.string.string_dialog_title) );
                dialogo.setContentView(R.layout.dialog_menu_item);

                //Configura os Botoes de Dialogo
                Button botaoOk = (Button) dialogo.findViewById( R.id.id_button_ok );
                botaoOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentWeb = new Intent( Intent.ACTION_VIEW, Uri.parse( MOMA_URL ));
                        startActivity(intentWeb);
                    }
                });

                Button botaoCancel = (Button) dialogo.findViewById( R.id.id_button_cancel );
                botaoCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo.dismiss();
                    }
                });

                //Apresenta o Dialog
                dialogo.show();
            return true;

            default:
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                configureRectangles(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){ /*...*/ }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                clearRectangles();
            }
        });
    }

    /**
     * Configure the Color and text properties for a textView from which represents a rectangale
     * @param progress
     */
    private void configureRectangles(int progress){

        tv1.setBackgroundColor( colorTv1*progress );
        //tv1.setText( getResources().getString(R.string.string_rect1) + " \nCor: " + Integer.toHexString(colorTv1*progress) );

        tv2.setBackgroundColor( colorTv2-(progress^3) );
        //tv2.setText( getResources().getString(R.string.string_rect2) + " \nCor: " + Integer.toHexString(colorTv2-(progress^3)) );

        tv3.setBackgroundColor( colorTv3+(progress*4) );
        //tv3.setText( getResources().getString(R.string.string_rect3) + " \nCor: " + Integer.toHexString(colorTv3+(progress*4)) );

        tv4.setBackgroundColor( colorTv4+progress );
        //tv4.setText( getResources().getString(R.string.string_rect4) + " \nCor: " + Integer.toHexString(colorTv4+(progress*progress)) );

        tv5.setBackgroundColor( colorTv5+(progress+25) );
        //tv5.setText( getResources().getString(R.string.string_rect5) + " \nCor: " + Integer.toHexString(colorTv5+(progress+32)) );
    }

    private void clearRectangles(){
        /*tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");*/
    }
}