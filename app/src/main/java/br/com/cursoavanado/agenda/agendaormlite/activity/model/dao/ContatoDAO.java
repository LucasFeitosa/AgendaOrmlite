package br.com.cursoavanado.agenda.agendaormlite.activity.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import br.com.cursoavanado.agenda.agendaormlite.activity.model.bean.Contato;

/**
 * Created by lucas on 26/12/14.
 */
public class ContatoDAO extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";


    private static final int DATABASE_VERSION = 1;

    private Dao<Contato, Long> dao = null;




    public ContatoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * @param database
     * @param source
     */

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource source) {
       try{
           Log.i(ContatoDAO.class.getSimpleName(), "onCreate()");
           TableUtils.createTable(source, Contato.class);


           Contato contato = new Contato();
           contato.setNome("Administrador");
           contato.setEmail("teste@androidavancado.com.br");
           contato.setTelefone("9900-8877");
           cadastrar(contato);
       } catch(SQLException ex){
           Log.e(ContatoDAO.class.getSimpleName(), "onCreate(): Falha ao criar tabelas", ex);
       }

    }

    /**
     *
     * @param database
     * @param source
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource source, int oldVersion, int newVersion) {

        try{
            Log.i(ContatoDAO.class.getSimpleName(), "onUpgrade()");

            TableUtils.dropTable(source, Contato.class, true);
            onCreate(database, source);
        } catch (SQLException ex){
            Log.e(ContatoDAO.class.getSimpleName(), "onUpgrade(): Falha na atualização", ex);
        }
    }

    /**
     *
     * @param contato
     * @throws SQLException
     */

    public void cadastrar(Contato contato) throws SQLException{
        getDao().create(contato);
    }

    /**
     *
     * @param contato
     * @throws SQLException
     */
    public void alterar(Contato contato) throws SQLException{
        getDao().update(contato);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Contato> listar() throws SQLException{
        return getDao().queryForAll();
    }

    /**
     *
     * @param contato
     * @throws SQLException
     */

    public void excluir(Contato contato) throws SQLException{
        getDao().delete(contato);
    }

    /**
     *
     * @return
     */

    public Dao<Contato, Long> getDao(){
        if(dao == null){
            try {
                dao = getDao(Contato.class);
            }catch (SQLException ex){
                Log.e(ContatoDAO.class.getSimpleName(), "getDao(): Falha ao criar DAO", ex);
            }

        }
        return dao;


    }

    public void close(){
        super.close();
        dao = null;
    }

}
