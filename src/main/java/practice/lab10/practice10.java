package practice.lab10;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class practice10 {
    public static void main(String[] args) {
        String data = "";
        String nameOrId = "pikachu";
        String url = String.format("https://pokeapi.co/api/v2/pokemon/%s/", nameOrId);
        try {
            URL restUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) restUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                data = reader.readLine();
            }
//            System.out.println(data);
            JSONObject jsObject = (JSONObject) JSON.parse(data);
            String name = (String) jsObject.get("name");
            Integer height = (Integer) jsObject.get("height");
            Integer weight = (Integer) jsObject.get("weight");
            JSONArray abilities = (JSONArray) jsObject.get("abilities");

            System.out.printf("""
                    Name: %s
                    Height: %s
                    Weight: %s
                    Abilities: %s""", name, height, weight, abilities);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
