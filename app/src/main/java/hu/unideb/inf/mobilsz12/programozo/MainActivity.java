package hu.unideb.inf.mobilsz12.programozo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;





public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private LightSensorEventListener sel = new LightSensorEventListener();
    private TextView tv;
    private Sensor mLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tv = findViewById(R.id.luxtext); //ide kell majd valami
        tv.setMovementMethod(new ScrollingMovementMethod());
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(sel, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        sel.setTv(tv);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sel, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sel);
    }
}