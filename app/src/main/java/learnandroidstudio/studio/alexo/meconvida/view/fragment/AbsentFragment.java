package learnandroidstudio.studio.alexo.meconvida.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


public class AbsentFragment extends Fragment {

    private ViewHolderAbsentFrag mViewHolderAbsentFrag = new ViewHolderAbsentFrag();
    private ConvidadoBusiness mConvidadoBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_absent, container, false);
        Context context = view.getContext();

        mViewHolderAbsentFrag.mRvAbsent = view.findViewById(R.id.frag_rv_absent);
        mConvidadoBusiness = new ConvidadoBusiness(context);


        List<ConvidadoEntity> convidadoBusinessList = mConvidadoBusiness.getConvidadoByPresence(ConvidadoConstants.CONFIRMARTION.AUSENTE);
        ConvidadoListAdapter adapter = new ConvidadoListAdapter(convidadoBusinessList, new OnConvidadoListenerInteractionListener() {
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

        mViewHolderAbsentFrag.mRvAbsent.setAdapter(adapter);

        mViewHolderAbsentFrag.mRvAbsent.setLayoutManager(new LinearLayoutManager(context));

        return  view;
    }

    private static class ViewHolderAbsentFrag{
        RecyclerView mRvAbsent;
    }

}
