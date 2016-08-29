package teste.jogodamemoria20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renato on 19/08/16.
 */
public class Database {
    private static final String DATABASE_NAME = "bd1";

    // Somente esta aplicação terá acesso ao BD
    private static final int DATABASE_ACCESS = 0;

    // Consultas SQL

    private static final String SQL_STRUCT = "CREATE TABLE IF NOT EXISTS usuario (nome VARCHAR(50), tentativas INTEGER);";

    private static final String SQL_INSERT = "INSERT INTO usuario (nome, tentativas) VALUES ('%s', '%d');";

    private static final String SQL_SELECT_ALL = "SELECT * FROM usuario ORDER BY tentativas;";

    private static final String SQL_CLEAR = "DROP TABLE IF EXISTS usuario;";

    private SQLiteDatabase database;
    private Cursor cursor;
    private int indexNome, indexTentativas;

    // Construtor
    public Database(Context context) {
        // Utiliza o contexto da activity que vai manipular o BD
        database = context.openOrCreateDatabase(DATABASE_NAME, DATABASE_ACCESS, null);
        database.execSQL(SQL_STRUCT);
    }

    public void celar() {
        database.execSQL(SQL_CLEAR);
    }

    public void close() {

        database.close();
    }

    public void insert(Usuario usuario) {

        try {
            String query = String.format(SQL_INSERT, usuario.getNome(), usuario.getTentativas());
            database.execSQL(query);

        }

        catch (Exception ex){
            System.out.print(ex.getMessage());

        }
    }

    public List<Usuario> all() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario usuario;

        cursor = database.rawQuery(SQL_SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            //indexID = cursor.getColumnIndex("id_");
            indexNome = cursor.getColumnIndex("nome");
            indexTentativas = cursor.getColumnIndex("tentativas");

            do {
                usuario = new Usuario(cursor.getString(indexNome), cursor.getInt(indexTentativas));

                usuario = new Usuario(cursor.getString(indexNome), cursor.getInt(indexTentativas));
                usuarios.add(usuario);
            }while(cursor.moveToNext());
        }


        cursor.close();

        return usuarios;
    }
}
