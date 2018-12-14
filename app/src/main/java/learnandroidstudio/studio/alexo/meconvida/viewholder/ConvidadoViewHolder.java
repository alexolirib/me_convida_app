package learnandroidstudio.studio.alexo.meconvida.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;
import learnandroidstudio.studio.alexo.meconvida.listener.OnConvidadoListenerInteractionListener;

public class ConvidadoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;
    private ImageView mImageRemove;
    private Context mContext;

    public ConvidadoViewHolder(View itemView, Context context) {
        super(itemView);

        this.mTextName = itemView.findViewById(R.id.text_name);
        this.mImageRemove = itemView.findViewById(R.id.img_remove);
        this.mContext = context;
    }

    public void bindData(final ConvidadoEntity convidadoEntity, final OnConvidadoListenerInteractionListener listener) {
        this.mTextName.setText(convidadoEntity.getName());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListClick(convidadoEntity.getId());
            }
        });

        this.mImageRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.remocao_convidado)
                        .setMessage("Deseja remover "+convidadoEntity.getName() +"?")
                        .setIcon(R.drawable.ic_menu_camera)
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeletedClick(convidadoEntity.getId());

                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
                return;
            }
        });

        this.mTextName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle(R.string.remocao_convidado)
                        .setMessage("Deseja remover "+convidadoEntity.getName() +"?")
                        .setIcon(R.drawable.ic_menu_camera)
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeletedClick(convidadoEntity.getId());

                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
                return true;
            }
        });
    }
}

