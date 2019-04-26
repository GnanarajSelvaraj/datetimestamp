package org.mahiti.time_premission.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.mahiti.time_premission.R;
import org.mahiti.time_premission.model.DataResponse;
import org.mahiti.time_premission.rest.ApiClient;
import org.mahiti.time_premission.rest.ApiInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.util.TimeZone.getTimeZone;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView txt_date, txt_date1;
    Calendar calendar;
    SimpleDateFormat simpledateformat;
    static String Date;
    TextView DisplayDateTime;
    Button btn;
    String dateServer, dateDevice;
    String dateServer1, dateDevice1;

    // TODO - insert your themoviedb.org API KEY here
    private final static String Api_Key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_date = findViewById(R.id.txt_date);
        txt_date1 = findViewById(R.id.txt_date1);
//        Calendar calendar=Calendar.getInstance();
//        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        txt_date1.setText(currentDate);
        DisplayDateTime = (TextView) findViewById(R.id.txt_date1);

        btn = (Button) findViewById(R.id.button);
        calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateDevice = simpledateformat.format(calendar.getTime());
        DisplayDateTime.setText(dateDevice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DataResponse> call = apiService.getCurrentDateTime();
        call.enqueue(new Callback<DataResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                dateServer = response.body().getCurrentDateTime();
                txt_date.setText(dateServer);

                String dateServer1 = dateServer.substring(0,10);
                String dateDevice1 = dateDevice.substring(0,10);
                comparedate(dateDevice1, dateServer1);

            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
        protected void onResume() {
            super.onResume();
        txt_date = findViewById(R.id.txt_date);
        txt_date1 = findViewById(R.id.txt_date1);

        calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateDevice = simpledateformat.format(calendar.getTime());
        txt_date1.setText(dateDevice);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<DataResponse> call1 = apiService.getCurrentDateTime();
            call1.enqueue(new Callback<DataResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    dateServer = response.body().getCurrentDateTime();
                    txt_date.setText(dateServer);
                    String dateServer2= dateServer.substring(0,10);
                    String dateDevice2 = dateDevice.substring(0,10);
                    compareResume(dateDevice2, dateServer2);
//                    Intent intent = getIntent();
//                    startActivity(intent);
                    showLog("Activity resumed");
                }
                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



    }

    private void showLog(String activity_resumed) {
        Log.d(TAG,activity_resumed);
    }

    private void comparedate(String deviceDate, String serverDate) {

//        try {
            String pattern = "dd-MM-yyyy HH:mm:ss";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
//            Date one = dateFormat.parse(deviceDate);
//            Date two = dateFormat.parse(serverDate);

                if (deviceDate.equalsIgnoreCase(serverDate)) {


                    Toast.makeText(MainActivity.this, "equal", Toast.LENGTH_SHORT).show();
                } else {
//                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                    alertDialog.setTitle("Alert");
//                    alertDialog.setMessage("date are not matching ");
//                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Go to setting ",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                    startActivity(new Intent(Settings.ACTION_SETTINGS));
//                                }
//                            });
//                    alertDialog.show();
                    Toast.makeText(MainActivity.this, "are not equal", Toast.LENGTH_SHORT).show();

                }

//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    public void compareResume(String deviceDate1, String serverDate1 ){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        if (deviceDate1.equalsIgnoreCase(serverDate1)) {
            Toast.makeText(MainActivity.this, "Date is Correct", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("date are not matching ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Go to setting ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    });
            alertDialog.show();
            Toast.makeText(MainActivity.this, "Date is not Correct", Toast.LENGTH_SHORT).show();
        }
    }
}
