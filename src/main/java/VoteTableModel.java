import Tools.DatabaseTableConfig;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


/**
 * Created by admin on 24.04.2018.
 */
public class VoteTableModel {
    private Sql2o sql2o;

    public VoteTableModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<String> getVotes(){
        try (Connection conn = sql2o.beginTransaction()) {
            System.out.println("SELECTION");
           return conn.createQuery("SELECT "+ DatabaseTableConfig.percentColumnName+" FROM "+ DatabaseTableConfig.tableName).executeAndFetch(String.class);
        }
    }
    public void recreateTables(){
        try (Connection conn = sql2o.beginTransaction()) {
           // conn.createQuery("DROP TABLE IF EXISTS "+DatabaseTableConfig.tableName+"; CREATE TABLE "+DatabaseTableConfig.tableName+" ("+DatabaseTableConfig.fioColumnName+","+DatabaseTableConfig.percentColumnName+");");
        }
    }
}
