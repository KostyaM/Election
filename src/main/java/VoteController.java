import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 24.04.2018.
 */
public class VoteController {
    static String question;
    static List<Candidate> options;



    public  Route HTML = (Request request, Response response) -> {
        ConfigVoteDataReader cvdr=new ConfigVoteDataReader();
        cvdr.start();
        try {
            cvdr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.question=cvdr.getQuestion();
        this.options=cvdr.getOutput();
        Map<String, Object> model = new HashMap<>();


        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(MainController.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);


        response.status(200);
        response.type("text/html");


        Map<String, Object> attributes = new HashMap<>();
        attributes.put("question",question);


        attributes.put("options", options);


        return freeMarkerEngine.render(new ModelAndView(attributes, "templates/vote.ftl"));
        //return freeMarkerEngine.render(new ModelAndView(attributes, "posts.ftl"));

        //return ViewUtils.render(request, model, Path.Template.LOGIN);
    };
    public  Route getHTML() {
        return HTML;
    }
}
