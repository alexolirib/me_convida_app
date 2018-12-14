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


public class PresentFragment extends Fragment {


    private ViewHolderPresentFrag mViewHolderPresentFrag = new ViewHolderPresentFrag();
    ConvidadoBusiness mConvidadoBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_present, container, false);
        Context context = view.getContext();
        mConvidadoBusiness = new ConvidadoBusiness(context);

        //obter recyclerview
        this.mViewHolderPresentFrag.rv_present = view.findViewById(R.id.frag_rv_present_id);

        List<ConvidadoEntity> convidadoEntityList = mConvidadoBusiness.getConvidadoByPresence(ConvidadoConstants.CONFIRMARTION.CONFIRMADO);

        //definir adapter
        ConvidadoListAdapter adapter = new ConvidadoListAdapter(convidadoEntityList, new OnConvidadoListenerInteractionListener() {
            @Override
            public void onListClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(ConvidadoConstants.BundleConstants.CONVIDADO_ID, id);
                Intent intent = new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onDeletedClick(int id) {

            }
        });
        mViewHolderPresentFrag.rv_present.setAdapter(adapter);


        //definirLayout
        this.mViewHolderPresentFrag.rv_present.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    private static class ViewHolderPresentFrag{
        RecyclerView rv_present;
    }
}
