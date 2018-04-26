import Tools.DatabaseConnectionConfigs;
import Tools.Path;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import java.util.Scanner;
import java.util.UUID;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by admin on 24.04.2018.
 */
public class Index {
    public static void main(String[] args) {
        System.out.println("ВВедите команду");
        System.out.println("1) Запуск сервиса без обновления базы данных");
        System.out.println("2) Полный сброс всех данных в базе данных и создание новых таблиц с последующим запуском");
        Scanner in=new Scanner(System.in);
        port(4567);
        switch (in.nextInt()){
            case 1:


                break;
            case 2:
                Sql2o sql2o = new Sql2o("jdbc:postgresql://" + DatabaseConnectionConfigs.dbHost + ":" + DatabaseConnectionConfigs.port + "/" + DatabaseConnectionConfigs.dbName, DatabaseConnectionConfigs.username, DatabaseConnectionConfigs.password, new PostgresQuirks() {{
                    // make sure we use default UUID converter.
                    converters.put(UUID.class, new UUIDConverter());
                }
                });
                CreateDatabaseStructure cds =new CreateDatabaseStructure(sql2o);
                cds.start();
                try {
                    cds.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // get(Path.Index,Controllers.getInstance().getMainController().getHTML());
                break;
            default:
                System.out.println("Ошибочная команда");
                break;
        }

        get(Path.Index, new Controllers().getMainController().getHTML());
        get(Path.Vote, new Controllers().getVoteController().getHTML());



    }
}
