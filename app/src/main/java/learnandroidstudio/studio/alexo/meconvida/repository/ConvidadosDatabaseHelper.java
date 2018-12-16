package learnandroidstudio.studio.alexo.meconvida.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import learnandroidstudio.studio.alexo.meconvida.constants.DataBaseConstants;

public class ConvidadosDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MeusConvidados.db";


    private static final String SQL_CREATE_TABLE_GUEST  = "CREATE TABLE "+ DataBaseConstants.GUEST.TABLE_NAME + "(" +
            " " + DataBaseConstants.GUEST.COLUMNS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " " + DataBaseConstants.GUEST.COLUMNS.NAME + " TEXT, " +
            " " + DataBaseConstants.GUEST.COLUMNS.DOCUMENT + " TEXT , " +
            " " + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " INTEGER);";

    private static final String DROP_TABLE_GUEST = "DROP TABLE IF EXISTS " + DataBaseConstants.GUEST.TABLE_NAME;

    //CursorFactory(terceiro parâmetro) - caso queira extender a função do banco
    public ConvidadosDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_GUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(DROP_TABLE_GUEST);
//        db.execSQL(SQL_CREATE_TABLE_GUEST);
    }
}
