package services;
import models.User;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;


public class Auth {
    //fields

    private JSONArray jsonArray = new JSONArray();

     private User currentUser = new User();


    //constructor
    public Auth() {}

    //get current user
    public User getCurrentUser(){
        return this.currentUser;
    }
    //get users arrayList length

    //fetch data
    public void fetchData (){
        JSONObject jsonObject = new JSONObject();
        Object object = null;
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader file = new FileReader("Users.json");
            object = jsonParser.parse(file);
            this.jsonArray = (JSONArray) object;
            file.close();

        } catch (Exception ex){
            System.out.println("error occured");
        }
    }
    //clear data
    public void clearData(){
        try {
            FileWriter fileWriter = new FileWriter("Users.json");
            this.jsonArray.clear();
            fileWriter.write(this.jsonArray.toJSONString());
            fileWriter.close();
        } catch (Exception ex){
            System.out.println("error occured");
        }
    }


    //REGISTER IN WITH EMAIL AND PASSWORD
    public void registerWithEmailAndPassword(String email, String password) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email: ",email);
        jsonObject.put("password: ",password);
        this.jsonArray.add(jsonObject);
        try {
            FileWriter fileWriter = new FileWriter("Users.json");
            fileWriter.write(this.jsonArray.toJSONString());
            fileWriter.close();
        } catch (Exception ex){
            System.out.println("error occurred");
        }
        //System.out.println(jsonArray);


    }

    //SIGN IN WITH EMAIL AND PASSWORD
    public boolean signInWithEmailAndPassword(String email,String password){


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email: ",email);
        jsonObject.put("password: ",password);
        if(email.equals("clear")&&password.equals("clear")){
            clearData();
            System.out.println("user data cleared");
            return false;
            //System.out.println(this.jsonArray);
        }
        else if(this.jsonArray.isEmpty()){
            System.out.println("There is no account in the system.");
        }
        else {
            for(int i=0;i<this.jsonArray.size();i++){
                if (jsonObject.equals(this.jsonArray.get(i))){
                    System.out.println("successfully logged in");
                    currentUser.setEmail(email);
                    currentUser.setPassword(password);
                    return true;
                }
                else if(i==this.jsonArray.size()-1){
                    System.out.println("Enter valid Email and Password");
                    //System.out.println(this.jsonArray.get(i));
                    return false;
                }

            }

        }
        return false;

    }






}





