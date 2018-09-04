package fabric.bits.api.rdk;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class RdkAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<T> data;

    public RdkAdapter(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


}
