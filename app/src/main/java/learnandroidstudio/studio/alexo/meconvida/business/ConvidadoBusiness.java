package learnandroidstudio.studio.alexo.meconvida.business;

import android.content.Context;

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
}
