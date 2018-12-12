package learnandroidstudio.studio.alexo.meconvida.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.business.ConvidadoBusiness;
import learnandroidstudio.studio.alexo.meconvida.constants.ConvidadoConstants;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolderForm mViewHolderForm = new ViewHolderForm();
    private ConvidadoBusiness mConvidadoBusiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        this.mViewHolderForm.mTxtNome = findViewById(R.id.txt_nome);
        this.mViewHolderForm.mRadioNaoConfirmado = findViewById(R.id.radio_nao_confirmado);
        this.mViewHolderForm.mRadioConfirmado = findViewById(R.id.radio_confirmado);
        this.mViewHolderForm.mRadioAusente = findViewById(R.id.radio_ausente);
        this.mViewHolderForm.mBtnSalvar = findViewById(R.id.btn_salvar);

        mConvidadoBusiness = new ConvidadoBusiness(this);

        this.setListener();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_salvar){
            this.handleSave();
        }

    }

    private void setListener() {
        this.mViewHolderForm.mBtnSalvar.setOnClickListener(this);
    }

    private void handleSave() {
        ConvidadoEntity convidadoEntity = new ConvidadoEntity();
        convidadoEntity.setName(this.mViewHolderForm.mTxtNome.getText().toString());
        if(mViewHolderForm.mRadioConfirmado.isChecked()){
            convidadoEntity.setCondirmed(ConvidadoConstants.CONFIRMARTION.CONFIRMADO);
        }
        if(mViewHolderForm.mRadioNaoConfirmado.isChecked()){
            convidadoEntity.setCondirmed(ConvidadoConstants.CONFIRMARTION.NAO_CONFIRMADO);
        }else{
            convidadoEntity.setCondirmed(ConvidadoConstants.CONFIRMARTION.AUSENTE);
        }

        this.mConvidadoBusiness.insert(convidadoEntity);

    }

    private static class ViewHolderForm{
        EditText mTxtNome;
        RadioButton mRadioNaoConfirmado;
        RadioButton mRadioConfirmado;
        RadioButton mRadioAusente;
        Button mBtnSalvar;
    }
}
