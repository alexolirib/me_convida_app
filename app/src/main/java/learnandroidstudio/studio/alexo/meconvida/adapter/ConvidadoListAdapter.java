package learnandroidstudio.studio.alexo.meconvida.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;
import learnandroidstudio.studio.alexo.meconvida.listener.OnConvidadoListenerInteractionListener;
import learnandroidstudio.studio.alexo.meconvida.viewholder.ConvidadoViewHolder;

public class ConvidadoListAdapter extends RecyclerView.Adapter<ConvidadoViewHolder>{

    private List<ConvidadoEntity> mConvidadoList;

    private OnConvidadoListenerInteractionListener listener;

    public ConvidadoListAdapter(List<ConvidadoEntity> mConvidadoList, OnConvidadoListenerInteractionListener listener) {
        this.mConvidadoList = mConvidadoList;
        this.listener = listener;
    }

    @Override
    public ConvidadoViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_guest_list, parent, false);
        return new ConvidadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ConvidadoViewHolder convidadoViewHolder, int position) {
        ConvidadoEntity convidadoEntity = this.mConvidadoList.get(position);
        convidadoViewHolder.bindData(convidadoEntity, listener);
        
    }

    @Override
    public int getItemCount() {
        //if (!mConvidadoList.isEmpty()) {
            return mConvidadoList.size();
        //}
        //return 0;
    }
}
