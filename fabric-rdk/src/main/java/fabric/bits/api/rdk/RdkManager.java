package fabric.bits.api.rdk;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fabric.bits.api.rdk.annotations.RClick;
import fabric.bits.api.rdk.annotations.RDefine;
import fabric.bits.api.rdk.annotations.RIntent;
import fabric.bits.api.rdk.annotations.RLayout;
import fabric.bits.api.rdk.annotations.RTextWatcher;

public class RdkManager {


    public static void setContentView(Activity activity, @LayoutRes int layout) {
        activity.setContentView(layout);
        bind(activity);
    }

    public static void bind(Activity activity) {
        processLayout(activity);
        processIntent(activity);
        processDefine(activity);
        processClick(activity);
        processTextWatcher(activity);
    }

    public static void bind(Fragment fragment,View view) {
        processDefine(fragment,view);
        processClick(fragment,view);
        processTextWatcher(fragment,view);
    }

    private static void processDefine(Fragment fragment,View view) {
        for (Field m : fragment.getClass().getDeclaredFields()) {
            if (m.isAnnotationPresent(RDefine.class)) {
                RDefine rDefine = m.getAnnotation(RDefine.class);
                m.setAccessible(true);
                try {
                    m.set(fragment, view.findViewById(rDefine.value()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void processLayout(Activity activity) {
        if(activity.getClass().isAnnotationPresent(RLayout.class)){
            RLayout layout=activity.getClass().getAnnotation(RLayout.class);
            activity.setContentView(layout.value());
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

    private static void processTextWatcher(final Activity activity) {
        for (final Method m : activity.getClass().getMethods()) {
            if (m.isAnnotationPresent(RTextWatcher.class)) {
                RTextWatcher rTextWatcher = m.getAnnotation(RTextWatcher.class);
                m.setAccessible(true);

                final EditText editText=activity.findViewById(rTextWatcher.value());
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try {
                            m.invoke(activity,editText,charSequence.toString());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }
    }

    private static void processTextWatcher(final Fragment fragment,View view) {
        for (final Method m : fragment.getClass().getMethods()) {
            if (m.isAnnotationPresent(RTextWatcher.class)) {
                RTextWatcher rTextWatcher = m.getAnnotation(RTextWatcher.class);
                m.setAccessible(true);

                final EditText editText=view.findViewById(rTextWatcher.value());
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        try {
                            m.invoke(fragment,editText,charSequence.toString());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
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

    private static void processClick(final Fragment fragment,View view) {
        for (final Method m : fragment.getClass().getMethods()) {
            if (m.isAnnotationPresent(RClick.class)) {
                RClick rClick = m.getAnnotation(RClick.class);
                m.setAccessible(true);
                view.findViewById(rClick.value()).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            m.invoke(fragment);
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
