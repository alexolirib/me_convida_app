package learnandroidstudio.studio.alexo.meconvida.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import learnandroidstudio.studio.alexo.meconvida.constants.DataBaseConstants;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;

public class ConvidadoRepository {
    //Nessa classe utilizando o padrão de projeto Singleton - previne diversas instâncias de uma classe
    // Esse padrão de projeto para um projeto android previne problemas, uso demasiado de memoria e mais desempenho


    private  static ConvidadoRepository INSTANCE;
    //para poder utilizar o banco
    private ConvidadosDatabaseHelper mConvidadeDatabaseHelper;

    //Para não permitir que seja instanciado diversas vezes
    private ConvidadoRepository(Context context){
        this.mConvidadeDatabaseHelper = new ConvidadosDatabaseHelper(context);
    }

    //para fazer a instancia
    //synchronized - multi thread para fazer gerenciamento do acesso ao método
    public static synchronized ConvidadoRepository getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new ConvidadoRepository(context);
        }
        return INSTANCE;
    }

    public Boolean insert(ConvidadoEntity convidadoEntity){
        try{
            SQLiteDatabase sq = mConvidadeDatabaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, convidadoEntity.getName());
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, convidadoEntity.getCondirmed());

            sq.insert(DataBaseConstants.GUEST.TABLE_NAME, null , contentValues);

            return true;
        }catch (Exception e){
            return false;
        }

    }
}
