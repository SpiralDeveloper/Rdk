package fabric.bits.api.rdk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fabric.bits.api.rdk.annotations.RLayout;


public class RdkFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getClass().isAnnotationPresent(RLayout.class)) {
            RLayout rLayout = getClass().getAnnotation(RLayout.class);
            return inflater.inflate(rLayout.value(),container,false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RdkManager.bind(this,view);
    }


}
