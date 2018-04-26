import Tools.DatabaseConnectionConfigs;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by admin on 26.04.2018.
 */
class ConfigVoteDataReader extends Thread{
    String subject,question;
    List<Candidate> output=new LinkedList<>();

    public String getSubject() {
        return subject;
    }

    public String getQuestion() {
        return question;
    }

    public List<Candidate> getOutput() {
        return output;
    }

    @Override
    public void run() {
        List<String> namesOptions=new LinkedList<>();
        Map<String,Integer> candidateVotesCount=new HashMap<>();
        super.run();
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(new FileReader("C:\\Users\\admin\\Desktop\\spark\\election\\src\\main\\resources\\configVote.json"));
            subject= (String) config.get("subject");
            question= (String) config.get("question");
            JSONArray optionsJSONArray= (JSONArray) config.get("options");
            for (Object option : optionsJSONArray) {
                namesOptions.add((String) option);
                candidateVotesCount.put((String) option,0);
                System.out.println("PUUUUUUUT");
            }



            Sql2o sql2o = new Sql2o("jdbc:postgresql://" + DatabaseConnectionConfigs.dbHost + ":" + DatabaseConnectionConfigs.port + "/" + DatabaseConnectionConfigs.dbName, DatabaseConnectionConfigs.username, DatabaseConnectionConfigs.password, new PostgresQuirks() {{
                // make sure we use default UUID converter.
                converters.put(UUID.class, new UUIDConverter());
            }
            });
            VoteTableModel model = new VoteTableModel(sql2o);
            int votesCount=0;
            for(String candidate: model.getVotes()){
                candidateVotesCount.put(candidate,candidateVotesCount.get(candidate)+1);
                votesCount++;
            }
            System.out.println(votesCount);

            for(int i=0;i<namesOptions.size();i++){
                float percent=0;
                try {
                    percent= ((float) ((float)candidateVotesCount.get(namesOptions.get(i)) / (float) votesCount)) * 100;
                    System.out.println(percent);
                }catch (ArithmeticException ae){
                    percent=0;

                }
                Candidate candidate=new Candidate(namesOptions.get(i),Float.valueOf(percent).toString());
                candidate.createFtlValue();
                output.add(candidate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
