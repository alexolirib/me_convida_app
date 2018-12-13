package learnandroidstudio.studio.alexo.meconvida.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import learnandroidstudio.studio.alexo.meconvida.constants.DataBaseConstants;
import learnandroidstudio.studio.alexo.meconvida.entities.ConvidadoEntity;

public class ConvidadoRepository {
    //Nessa classe utilizando o padrão de projeto Singleton - previne diversas instâncias de uma classe
    // Esse padrão de projeto para um projeto android previne problemas, uso demasiado de memoria e mais desempenho


    private static ConvidadoRepository INSTANCE;
    //para poder utilizar o banco
    private ConvidadosDatabaseHelper mConvidadeDatabaseHelper;

    //Para não permitir que seja instanciado diversas vezes
    private ConvidadoRepository(Context context) {
        this.mConvidadeDatabaseHelper = new ConvidadosDatabaseHelper(context);
    }

    //para fazer a instancia
    //synchronized - multi thread para fazer gerenciamento do acesso ao método
    public static synchronized ConvidadoRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ConvidadoRepository(context);
        }
        return INSTANCE;
    }

    public Boolean insert(ConvidadoEntity convidadoEntity) {
        try {
            SQLiteDatabase sq = mConvidadeDatabaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, convidadoEntity.getName());
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, convidadoEntity.getCondirmed());

            sq.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<ConvidadoEntity> getConvidadoByQuery(String query) {

        Log.e("teste", "teste12");
        List<ConvidadoEntity> list = new ArrayList<>();

        try {
            SQLiteDatabase db = this.mConvidadeDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                //se existe registro
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        ConvidadoEntity convidadoEntity = new ConvidadoEntity();
                        convidadoEntity.setId(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID)));
                        convidadoEntity.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME)));
                        convidadoEntity.setCondirmed(cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)));


                        Log.e("nome", convidadoEntity.getName());

                        list.add(convidadoEntity);
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }

        } catch (Exception e) {
            return list;
        }


        return list;
    }
}
