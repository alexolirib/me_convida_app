package learnandroidstudio.studio.alexo.meconvida.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;
import learnandroidstudio.studio.alexo.meconvida.listener.OnConvidadoListenerInteractionListener;

public class ConvidadoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;

    public ConvidadoViewHolder(View itemView) {
        super(itemView);

        this.mTextName = itemView.findViewById(R.id.text_name);
    }

    public void bindData(final ConvidadoEntity convidadoEntity, final OnConvidadoListenerInteractionListener listener) {
        this.mTextName.setText(convidadoEntity.getName());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListClick(convidadoEntity.getId());
            }
        });
    }
}

