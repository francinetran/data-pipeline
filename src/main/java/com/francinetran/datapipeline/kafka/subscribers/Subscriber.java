package com.francinetran.datapipeline.kafka.subscribers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;

public class Subscriber {

    @KafkaListener(id = "subscribe", topics = "FHIR-topic" ,groupId = "fhirGrpSimulate")
    public void subscribe(String in) throws JSONException {
    	JSONObject obj = new JSONObject(in);
    	String name = "";
		try {
			name = obj.getJSONObject("Patient").getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(name);
    }

}