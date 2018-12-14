package learnandroidstudio.studio.alexo.meconvida.business;

import android.content.Context;
import android.util.Log;

import java.util.List;

import learnandroidstudio.studio.alexo.meconvida.constants.DataBaseConstants;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoCount;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;
import learnandroidstudio.studio.alexo.meconvida.repository.ConvidadoRepository;

public class ConvidadoBusiness {

    private ConvidadoRepository mConvidadoRepository;

    public ConvidadoBusiness(Context context){
        mConvidadoRepository = ConvidadoRepository.getInstance(context);
    }
    
    public Boolean insert(ConvidadoEntity convidadoEntity){
        return this.mConvidadoRepository.insert(convidadoEntity);
    }

    public List<ConvidadoEntity> getConvidados() {

        return this.mConvidadoRepository.getConvidadoByQuery("select * from " + DataBaseConstants.GUEST.TABLE_NAME);
    }

    public ConvidadoEntity load(int id) {

        return mConvidadoRepository.load(id);
    }

    public List<ConvidadoEntity> getConvidadoByPresence(int presence) {
        return mConvidadoRepository.getConvidadoByQuery("select * from "+ DataBaseConstants.GUEST.TABLE_NAME + "" +
                " where " + DataBaseConstants.GUEST.COLUMNS.PRESENCE+ " = "+ presence);
    }

    public boolean update(ConvidadoEntity convidadoEntity) {
        return this.mConvidadoRepository.update(convidadoEntity);
    }

    public Boolean remove(int id) {
        return mConvidadoRepository.remove(id);
    }

    public ConvidadoCount loadDashBoard() {
        return mConvidadoRepository.loadDashBorad();
    }
}
