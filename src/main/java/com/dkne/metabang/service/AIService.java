package com.dkne.metabang.service;

import com.dkne.metabang.web.dto.AIRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class AIService {

    private String[] city= {"영등포본동","영등포동","여의도동","당산동","당산동","도림동","문래동","양평동",
    "양평동","신길동","신길동","신길동","신길동","신길동","신길동","대림동","대림동","대림동"};
    private HttpURLConnection tokenConnection;

    public String recommand(AIRequestDto requestDto)  throws IOException {
        String API_KEY = "OyMztamfZSWLJi6qAl2kTIETHTsGPApE6LxK1_bwvZEp";
        int gender = requestDto.getGender();
        int age = requestDto.getAge();
        int reason = requestDto.getReason();

        HttpURLConnection tokenConnection = null;
        HttpURLConnection scoringConnection = null;
        BufferedReader tokenBuffer = null;
        BufferedReader scoringBuffer = null;
        try {
            // Getting IAM token
            URL tokenUrl = new URL("https://iam.cloud.ibm.com/identity/token?grant_type=urn:ibm:params:oauth:grant-type:apikey&apikey=" + API_KEY);
            tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
            tokenConnection.setDoInput(true);
            tokenConnection.setDoOutput(true);
            tokenConnection.setRequestMethod("POST");
            tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            tokenConnection.setRequestProperty("Accept", "application/json");
            tokenBuffer = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = tokenBuffer.readLine()) != null) {
                jsonString.append(line);
            }
            // Scoring request
            URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/db488fab-ccba-4cc5-9f1e-4dce267c5740/predictions?version=2021-10-26&version=2021-10-26");
            String iam_token = "Bearer " + jsonString.toString().split(":")[1].split("\"")[1];
            scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
            scoringConnection.setDoInput(true);
            scoringConnection.setDoOutput(true);
            scoringConnection.setRequestMethod("POST");
            scoringConnection.setRequestProperty("Accept", "application/json");
            scoringConnection.setRequestProperty("Authorization", iam_token);
            scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");

            // NOTE: manually define and pass the array(s) of values to be scored in the next line

            String payload = "{\"input_data\":[{\"fields\":[\"gender\",\"age\",\"reason\"],\"values\":[["+gender+","+age+","+reason+"]]}]}";
            writer.write(payload);
            writer.close();


            scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
            StringBuffer jsonStringScoring = new StringBuffer();
            String lineScoring;
            while ((lineScoring = scoringBuffer.readLine()) != null) {
                jsonStringScoring.append(lineScoring);
            }
            System.out.println(jsonStringScoring);
            String objString = jsonStringScoring +"";

            JSONObject obj = new JSONObject(objString);
            String predicArray = obj.getJSONArray("predictions").getJSONObject(0).getJSONArray("values").getJSONArray(0).get(0).toString();
            System.out.println(predicArray);

            return city[Integer.valueOf(predicArray)];

        } catch (IOException e) {
            System.out.println("The URL is not valid.");
            System.out.println(e.getMessage());
            return "error : The URL is not valid.";
        }
        finally {
            if (tokenConnection != null) {
                tokenConnection.disconnect();
            }
            if (tokenBuffer != null) {
                tokenBuffer.close();
            }
            if (scoringConnection != null) {
                scoringConnection.disconnect();
            }
            if (scoringBuffer != null) {
                scoringBuffer.close();
            }
        }

    }
}
