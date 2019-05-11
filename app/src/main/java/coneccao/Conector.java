package coneccao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class Conector extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "";
    private static int DATABASE_VERSION = 1;

    private static final String TABELA_LIVROS = "Livros";
    private static final String ID = "Id";
    private static final String NOME = "Nome";
    private static final String AUTOR = "Autor";
    private static final String ANO = "Ano";
    private static final String[] COLUNAS = {ID, NOME, AUTOR, ANO};

    public Conector(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE Livros("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "titulo text,"+
                "autor text,"+
                "ano integer)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Livros");
    }

    public void addLivro(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOME, livro.getNome());
        values.put(AUTOR, livro.getAutor());
        values.put(ANO, livro.getAno());

        db.insert(TABELA_LIVROS, null,values);
        db.close();
    }

    public int updateLivro(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOME, livro.getNome());
        values.put(AUTOR, livro.getAutor());
        values.put(ANO, new Integer(livro.getAno()));

        int i = db.update(TABELA_LIVROS,
                values,
                ID+" = ?",new String[]{String.valueOf(livro.getId())});
        db.close();
        return i;
    }

    public int deleteLivro(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_LIVROS,
                ID+" = ?",new String[]{String.valueOf(livro.getId())});
        db.close();
        return i;
    }

    public Livro getLivro(int id){
        SQLiteDatabase db = this.getReadableDatabase(); // estancia a coneccao para leitura
        Cursor cursor = db.query( // realiza a consulta com uma query
                TABELA_LIVROS, // tabela a ser consultadas
                COLUNAS, // colunas que retornam
                "ID=?", // campo a ser consultado
                new String[]{String.valueOf(id)},null,null,null,null // parametros da consulta
        );
        if(cursor == null){
            return null; // se não encontrar a informação retorna nulo
        }else{
            cursor.moveToFirst(); // caso encontre a informação aponta para o inicio do registro
            Livro livro = cursorToLivro(cursor); //cria uma entidade livro com os dados
            return livro; // retorna a entidade
        }
    }

    private Livro cursorToLivro(Cursor cursor){
        Livro livro = new Livro();
        livro.setId(Integer.parseInt(cursor.getString(0)));
        livro.setNome(cursor.getString(1));
        livro.setAutor(cursor.getString(2));
        livro.setAno(Integer.parseInt(cursor.getString(3)));
        return livro;
    }

    public ArrayList<Livro> getAllLivros(){
        ArrayList<Livro> listaLivros = new ArrayList<Livro>();
        String query = "SELECT * FROM "+ TABELA_LIVROS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Livro livro = cursorToLivro (cursor);
                listaLivros.add(livro);
            }while
            (cursor.moveToNext());
        }
        return listaLivros;
    }


}
