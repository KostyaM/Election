import Tools.DatabaseConnectionConfigs;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import javax.xml.crypto.Data;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by admin on 24.04.2018.
 */
public class Controllers {
    MainController mainController;
    VoteController voteController;





    public Controllers() {
      mainController=new MainController();
        voteController=new VoteController();
    }

    public MainController getMainController() {
        return mainController;
    }

    public VoteController getVoteController() {
        return voteController;
    }

}
