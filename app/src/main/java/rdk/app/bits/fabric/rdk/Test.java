package rdk.app.bits.fabric.rdk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import fabric.bits.api.rdk.RdkDialogFragment;
import fabric.bits.api.rdk.annotations.RDefine;
import fabric.bits.api.rdk.annotations.RFullScreen;
import fabric.bits.api.rdk.annotations.RLayout;

@RFullScreen
@RLayout(R.layout.test)
public class Test extends RdkDialogFragment {

    @RDefine(R.id.textView)
    TextView nameText;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameText.setText("sssssss");
    }
}
