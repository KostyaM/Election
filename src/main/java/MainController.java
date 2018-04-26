import Tools.DatabaseConnectionConfigs;
import Tools.DatabaseTableConfig;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;


public class MainController {
    static String subject;
    static private Sql2o sql2o;
    static List <Candidate>options;


    public  Route HTML = (Request request, Response response) -> {
        ConfigVoteDataReader cvdr=new ConfigVoteDataReader();
        cvdr.start();
        try {
            cvdr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.subject=cvdr.getSubject();
        this.options=cvdr.getOutput();
        sql2o = new Sql2o("jdbc:postgresql://" + DatabaseConnectionConfigs.dbHost + ":" + DatabaseConnectionConfigs.port + "/" + DatabaseConnectionConfigs.dbName, DatabaseConnectionConfigs.username, DatabaseConnectionConfigs.password, new PostgresQuirks() {{
            // make sure we use default UUID converter.
            converters.put(UUID.class, new UUIDConverter());
        }
        });

        String fio=request.queryParams("fio");
        String voteResult=request.queryParams("voteResult");
        if( fio!=null &&!fio.equals("") && voteResult!=null){
            try (Connection conn = sql2o.open()) {
                conn.createQuery("INSERT INTO "+DatabaseTableConfig.tableName+" VALUES (:fio,:percent)").addParameter("fio", fio).addParameter("percent", voteResult).executeUpdate();
                conn.close();
                System.out.println("Insert result sucesessfully");
            }
        }else{
            System.out.println("Голос не принят " +fio+" "+voteResult);
        }


        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(MainController.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);


        response.status(200);
        response.type("text/html");


        Map<String, Object> attributes = new HashMap<>();
        attributes.put("subject",subject);


        attributes.put("options", options);
        attributes.get("subject");
        System.out.println(options.get(1).getPercent());
        return freeMarkerEngine.render(new ModelAndView(attributes, "templates/index.ftl"));
    };

    public  Route getHTML() {
        return HTML;
    }
}
