package fabric.bits.api.rdk;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fabric.bits.api.rdk.annotations.RClick;
import fabric.bits.api.rdk.annotations.RDefine;
import fabric.bits.api.rdk.annotations.RIntent;

public class RdkManager {


    public static void setContentView(Activity activity, @LayoutRes int layout) {
        activity.setContentView(layout);
        bind(activity);
    }

    public static void bind(Activity activity) {
       processIntent(activity);
       processDefine(activity);
       processClick(activity);
    }

    private static void processIntent(Activity activity) {
        for (Field m : activity.getClass().getDeclaredFields()) {
            if (m.isAnnotationPresent(RIntent.class)) {
                RIntent rIntent = m.getAnnotation(RIntent.class);
                m.setAccessible(true);
                try {
                    if (m.getType().isAssignableFrom(long.class))
                        m.set(activity, activity.getIntent().getLongExtra(m.getName(), 0));
                    else if (m.getType().isAssignableFrom(long[].class))
                        m.set(activity, activity.getIntent().getLongArrayExtra(m.getName()));
                    else if (m.getType().isAssignableFrom(String.class))
                        m.set(activity, activity.getIntent().getStringExtra(m.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void processDefine(Activity activity) {
        for (Field m : activity.getClass().getDeclaredFields()) {
            if (m.isAnnotationPresent(RDefine.class)) {
                RDefine rDefine = m.getAnnotation(RDefine.class);
                m.setAccessible(true);
                try {
                    m.set(activity, activity.findViewById(rDefine.value()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void processClick(final Activity activity) {
        for (final Method m : activity.getClass().getMethods()) {
            if (m.isAnnotationPresent(RClick.class)) {
                RClick rClick = m.getAnnotation(RClick.class);
                m.setAccessible(true);
                activity.findViewById(rClick.value()).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            m.invoke(activity);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
