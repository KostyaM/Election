import Tools.DatabaseConnectionConfigs;
import Tools.DatabaseTableConfig;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * Created by admin on 24.04.2018.
 */
public class CreateDatabaseStructure extends Thread {
    private Sql2o sql2o;
    CreateDatabaseStructure(Sql2o sql2o){
        this.sql2o=sql2o;
    }
    @Override
    public void run() {
        super.run();
        try (Connection conn = sql2o.open()) {

            conn.createQuery("DROP TABLE IF EXISTS "+ DatabaseTableConfig.tableName+";").executeUpdate();

            conn.createQuery("CREATE TABLE "+DatabaseTableConfig.tableName+"("+DatabaseTableConfig.fioColumnName+" varchar(255), "+DatabaseTableConfig.percentColumnName+" varchar(255));").executeUpdate();

            conn.close();
            System.out.println("CREATE TABLE "+DatabaseTableConfig.tableName+"("+DatabaseTableConfig.fioColumnName+" varchar(255), "+DatabaseTableConfig.percentColumnName+" varchar(255));");
            System.out.println("Rebuild database sucesessfully");
        }
    }
}
