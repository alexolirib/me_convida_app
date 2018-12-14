package learnandroidstudio.studio.alexo.meconvida.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.adapter.ConvidadoListAdapter;
import learnandroidstudio.studio.alexo.meconvida.business.ConvidadoBusiness;
import learnandroidstudio.studio.alexo.meconvida.constants.ConvidadoConstants;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;
import learnandroidstudio.studio.alexo.meconvida.listener.OnConvidadoListenerInteractionListener;
import learnandroidstudio.studio.alexo.meconvida.view.activity.GuestFormActivity;


public class AllInvitedFragment extends Fragment {

    private ViewHolderFragAll mViewHolderFragAll = new ViewHolderFragAll();
    private ConvidadoBusiness mConvidadoBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);
        Context context = view.getContext();

        //Obter a recycler
        this.mViewHolderFragAll.mRvAllInvited = view.findViewById(R.id.frag_rv_all_invited);

        mConvidadoBusiness = new ConvidadoBusiness(context);


        List<ConvidadoEntity> listConvidados = this.mConvidadoBusiness.getConvidados();


        //Definir um adapter
        //delegar a função para o meu recyclerView
        ConvidadoListAdapter convidadoListAdapter = new ConvidadoListAdapter(listConvidados,
                new OnConvidadoListenerInteractionListener() {
            @Override
            public void onListClick(int id) {
                // abrir activity de formulário
                Bundle bundle = new Bundle();
                bundle.putInt(ConvidadoConstants.BundleConstants.CONVIDADO_ID,id);

                Intent intent = new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }

            @Override
            public void onDeletedClick(int id) {

            }
        });
        this.mViewHolderFragAll.mRvAllInvited.setAdapter(convidadoListAdapter);

        //Definit um layout
        this.mViewHolderFragAll.mRvAllInvited.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private static class ViewHolderFragAll {
        RecyclerView mRvAllInvited;
    }

}
