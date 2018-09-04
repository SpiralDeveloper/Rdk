package fabric.bits.api.rdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;

import fabric.bits.api.rdk.annotations.RLayout;


public class RdkActivity extends AppCompatActivity {


    private KProgressHUD progressHUD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getClass().isAnnotationPresent(RLayout.class)) {
            RLayout rLayout = getClass().getAnnotation(RLayout.class);
            setContentView(rLayout.value());
        }
        RdkManager.bind(this);
    }


    public void showProgress(boolean show) {
        if (show && !getProgressHUD().isShowing()) {
            getProgressHUD().show();
        } else if (!show && getProgressHUD().isShowing()) {
            getProgressHUD().dismiss();
        }
    }

    public KProgressHUD getProgressHUD(){
        if (progressHUD == null) {
            progressHUD = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f);
        }
        return progressHUD;
    }


}
