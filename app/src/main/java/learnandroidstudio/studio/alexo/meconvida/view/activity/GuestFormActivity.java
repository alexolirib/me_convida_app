package learnandroidstudio.studio.alexo.meconvida.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import learnandroidstudio.studio.alexo.meconvida.R;
import learnandroidstudio.studio.alexo.meconvida.business.ConvidadoBusiness;
import learnandroidstudio.studio.alexo.meconvida.constants.ConvidadoConstants;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;

public class GuestFormActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolderForm mViewHolderForm = new ViewHolderForm();
    private ConvidadoBusiness mConvidadoBusiness;
    private  int mConvidadoId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        this.mViewHolderForm.mTxtNome = findViewById(R.id.txt_nome);
        this.mViewHolderForm.mRadioNaoConfirmado = findViewById(R.id.radio_nao_confirmado);
        this.mViewHolderForm.mRadioConfirmado = findViewById(R.id.radio_confirmado);
        this.mViewHolderForm.mRadioAusente = findViewById(R.id.radio_ausente);
        this.mViewHolderForm.mBtnSalvar = findViewById(R.id.btn_salvar);
        this.mViewHolderForm.mTxtDocument = findViewById(R.id.txt_documento);

        mConvidadoBusiness = new ConvidadoBusiness(this);

        this.setListener();

        //se existe algum bundle passado
        loadDataFromActivity();

    }

    private void loadDataFromActivity() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mConvidadoId = bundle.getInt(ConvidadoConstants.BundleConstants.CONVIDADO_ID);

            ConvidadoEntity convidadoEntity = mConvidadoBusiness.load(mConvidadoId);
            this.mViewHolderForm.mTxtNome.setText(convidadoEntity.getName());
            this.mViewHolderForm.mTxtDocument.setText(convidadoEntity.getDocument());
            if(convidadoEntity.getCondirmed() == ConvidadoConstants.CONFIRMARTION.CONFIRMADO){
                this.mViewHolderForm.mRadioConfirmado.setChecked(true);
            }else if(convidadoEntity.getCondirmed() == ConvidadoConstants.CONFIRMARTION.NAO_CONFIRMADO){
                this.mViewHolderForm.mRadioNaoConfirmado.setChecked(true);
            }else{
                this.mViewHolderForm.mRadioAusente.setChecked(true);
            }
        }
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
        if(!this.validateSave()){
            return;
        }

        ConvidadoEntity convidadoEntity = new ConvidadoEntity();
        convidadoEntity.setName(this.mViewHolderForm.mTxtNome.getText().toString());
        convidadoEntity.setDocument(this.mViewHolderForm.mTxtDocument.getText().toString());
        if(mViewHolderForm.mRadioConfirmado.isChecked()){
            convidadoEntity.setCondirmed(ConvidadoConstants.CONFIRMARTION.CONFIRMADO);
        }else if(mViewHolderForm.mRadioNaoConfirmado.isChecked()){
            convidadoEntity.setCondirmed(ConvidadoConstants.CONFIRMARTION.NAO_CONFIRMADO);
        }else{
            convidadoEntity.setCondirmed(ConvidadoConstants.CONFIRMARTION.AUSENTE);
        }

        if(mConvidadoId == 0){
            inserirConvidado(convidadoEntity);
        } else{
            convidadoEntity.setId(mConvidadoId);
            atualizarConvidade(convidadoEntity);
        }


        finish();

    }

    private void atualizarConvidade(ConvidadoEntity convidadoEntity) {
        if(this.mConvidadoBusiness.update(convidadoEntity)){
            Toast.makeText(this,this.getString(R.string.salvo_com_sucesso), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, getString(R.string.erro_ao_salvar), Toast.LENGTH_SHORT).show();
        }
    }

    private void inserirConvidado(ConvidadoEntity convidadoEntity) {

        if(this.mConvidadoBusiness.insert(convidadoEntity)){
            Toast.makeText(this,this.getString(R.string.salvo_com_sucesso), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, getString(R.string.erro_ao_salvar), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateSave() {
        if(this.mViewHolderForm.mTxtNome.getText().toString().equals("")){
            this.mViewHolderForm.mTxtNome.setError(getString(R.string.nome_obrigatorio));
            return false;
        }
        return true;
    }

    private static class ViewHolderForm{
        EditText mTxtNome;
        EditText mTxtDocument;
        RadioButton mRadioNaoConfirmado;
        RadioButton mRadioConfirmado;
        RadioButton mRadioAusente;
        Button mBtnSalvar;
    }
}
