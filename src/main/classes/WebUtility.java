package main.classes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebUtility{
    //THIS CLASS HANDLES ALL THE WEB PARSING NEEDED, USING JSOUP


    //FETCHES THE URL OF THE CURRENT LEAGUE VIDEO AS PRESENTED IN www.pathofexile.com AND STORES IT FOR LATER USE IN THE WebView OF THE user_profile.fxml
    public URL currentLeagueVideo(){
        URL videoLink = null;
        try{
            Document doc = Jsoup.connect("https://www.pathofexile.com/").get();
            Elements link = doc.select("iframe").addClass("video");
            videoLink = new URL("https:"+link.attr("src"));
            //System.out.println(videoLink);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
            System.out.println("Error retrieving from URL: "+ e.getMessage());
        }
        return  videoLink;
    }



    public List<String> TitleReader(URL forumlink) {  //Loads a list with guide titles as String
        List<String> titleboard = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(String.valueOf(forumlink)).get();   //Connects to the given (forumlink) webpage
            Elements titles = doc.getElementsByClass("title");      //Parses the HTML code focusing on the specific classname 'title'
            for (Element title : titles) {
                titleboard.add(title.text());
            }  //Fills  a Lists with title Elements
        }
        catch(Exception e){
            System.out.println("Error retrieving from URL: "+ e.getMessage());
        }
        return titleboard;
    }//works accordingly


    public List<String> ViewsReader(URL forumlink){ //Loads a list with guide views as String
        List<String> viewboard = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(String.valueOf(forumlink)).get();
            Elements views = doc.getElementsByClass("post-stat"); //Parses the HTML code focusing on the specific classname 'post-stat'
            for (Element view : views) {
                viewboard.add(view.text());
            }  //Fills  a Lists with view Elements
        }
        catch(Exception e){
            System.out.println("Error retrieving from URL: "+ e.getMessage());
        }
        return viewboard;
    }//works accordingly


    public List<String> BuildLink(URL forumlink){  //Loads a list with guide links as String
        List<String> linkBoard = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(String.valueOf(forumlink)).get();
            Elements links = doc.select("div.title > a");         //Parses the HTML code focusing on the 'a' hrefs under the div class 'title'
            for (Element link : links) {
                linkBoard.add(link.attr("href"));
            }  //Fills  a Lists with url link Elements
        }
        catch(Exception e){
            System.out.println("Error retrieving from URL: "+ e.getMessage());
        }
        return linkBoard;
    }//works accordingly


    public List<Integer> ViewBoardConverter(List<String> viewboard){  //Converts views from string to integers and sorts view List with desc order
        List<Integer> viewsnumber = new ArrayList<>();
        for(int i=0; i<=(viewboard.size()-1); i++){
            viewsnumber.add(Integer.parseInt(viewboard.get(i).replaceAll("[\\D]","")));  //replaces non-number chars with ""
        }
        viewsnumber.sort(Collections.reverseOrder()); //can be replaced by -> Collections.sort(viewsnumber, Collections.reverseOrder());
        return viewsnumber;
    }//works accordingly


    public void BuildShorter(List<String> titleboard, List<String> viewboard, List<String> BuildLink, List<Integer> viewsnumber){  //Shorts rest
        //final int numberOfResults = 10;
        for (int i = 0; i <titleboard.size(); i++){
            for (int j = 0; j <titleboard.size(); j++) {
                if (viewsnumber.get(i) == Integer.parseInt(viewboard.get(j).replaceAll("[\\D]",""))){
                    //Shorting titles
                    String temp = viewboard.get(i);
                    viewboard.set(i,viewboard.get(j));
                    viewboard.set(j, temp);
                    //Shorting views
                    String temp2 = titleboard.get(i);
                    titleboard.set(i,titleboard.get(j));
                    titleboard.set(j, temp2);
                    //Shorting URL links
                    String temp3 = BuildLink.get(i);
                    BuildLink.set(i, BuildLink.get(j));
                    BuildLink.set(j, temp3);
                }
            }
        }  //this double loop shorts all Lists based on the previously shorted Views List
        for(int i=0; i<BuildLink.size();i++){
            String temp = BuildLink.get(i);
            BuildLink.set(i,"https://www.pathofexile.com"+temp);
        }
    }//works accordingly


    public String forumLink(String ascChoice) throws Exception{
        URL link;
        String stringLink;
        switch (ascChoice) {
            case "Slayer":
                link = new URL("https://www.pathofexile.com/forum/view-forum/40/tag/Slayer");
                break;
            case "Gladiator":
                link = new URL("https://www.pathofexile.com/forum/view-forum/40/tag/Gladiator");
                break;
            case "Champion":
                link = new URL("https://www.pathofexile.com/forum/view-forum/40/tag/Champion");
                break;
            case "Juggernaught":
                link = new URL("https://www.pathofexile.com/forum/view-forum/23/tag/Juggernaut");
                break;
            case "Berserker":
                link = new URL("https://www.pathofexile.com/forum/view-forum/23/tag/Berserker");
                break;
            case "Chieftain":
                link = new URL("https://www.pathofexile.com/forum/view-forum/23/tag/Chieftain");
                break;
            case "Deadeye":
                link = new URL("https://www.pathofexile.com/forum/view-forum/24/tag/Deadeye");
                break;
            case "Raider":
                link = new URL("https://www.pathofexile.com/forum/view-forum/24/tag/Raider");
                break;
            case "Pathfinder":
                link = new URL("https://www.pathofexile.com/forum/view-forum/24/tag/Pathfinder");
                break;
            case "Ascendant":
                link = new URL("https://www.pathofexile.com/forum/view-forum/436/tag/Ascendant");
                break;
            case "Assassin":
                link = new URL("https://www.pathofexile.com/forum/view-forum/303/tag/Assassin");
                break;
            case "Saboteur":
                link = new URL("https://www.pathofexile.com/forum/view-forum/303/tag/Saboteur");
                break;
            case "Trickster":
                link = new URL("https://www.pathofexile.com/forum/view-forum/303/tag/Trickster");
                break;
            case "Inquisitor":
                link = new URL("https://www.pathofexile.com/forum/view-forum/41/tag/Inquisitor");
                break;
            case "Hierophant":
                link = new URL("https://www.pathofexile.com/forum/view-forum/41/tag/Hierophant");
                break;
            case "Guardian":
                link = new URL("https://www.pathofexile.com/forum/view-forum/41/tag/Guardian");
                break;
            case "Necromancer":
                link = new URL("https://www.pathofexile.com/forum/view-forum/22/tag/Necromancer");
                break;
            case "Occultist":
                link = new URL("https://www.pathofexile.com/forum/view-forum/22/tag/Occultist");
                break;
            default: //(ascChoice.equals("Elementalist"))
                link = new URL("https://www.pathofexile.com/forum/view-forum/22/tag/Elementalist");
                break;
        }
        stringLink = link.toString();


        return stringLink;
    }

}
