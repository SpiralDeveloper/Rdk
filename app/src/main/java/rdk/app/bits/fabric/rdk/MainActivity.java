package rdk.app.bits.fabric.rdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fabric.bits.api.rdk.RdkActivity;
import fabric.bits.api.rdk.RdkDialogFragment;
import fabric.bits.api.rdk.RdkManager;
import fabric.bits.api.rdk.annotations.RClick;
import fabric.bits.api.rdk.annotations.RDefine;
import fabric.bits.api.rdk.annotations.RIntent;
import fabric.bits.api.rdk.annotations.RLayout;
import fabric.bits.api.rdk.annotations.RTextWatcher;


@RLayout(R.layout.activity_main)
public class MainActivity extends RdkActivity {

    @RDefine(R.id.nameText)
    TextView nameText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameText.setText("AAAAAAAAAAAAAAAAAAAA");

        RdkDialogFragment rdkDialogFragment =  new RdkDialogFragment();
        rdkDialogFragment.show(getSupportFragmentManager(),"");



    }



    @RTextWatcher(R.id.editText)
    void watch(EditText editText){
        Log.d("ED",editText.getText().toString());
    }





}
