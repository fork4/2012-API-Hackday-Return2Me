package com.fork4.return2me.twilio;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Sms;


public class SendExtension extends TwilioBase {
    public static void send(String extension, String customerNumber){
    	
        /* Instantiate a new Twilio Rest Client */
        TwilioRestClient client = new TwilioRestClient(ACCOUNTSID, AUTHTOKEN);

        // Get the account and call factory class
        Account acct = client.getAccount();
        SmsFactory smsFactory = acct.getSmsFactory();

    	
        //build map of post parameters 
        Map<String,String> params = new HashMap<String,String>();
        params.put("From", NUMBER);
        params.put("To", customerNumber);
        params.put("Body", "Your Return2Me extension is " + extension + ".  Dial " + NUMBER + 
        		" and enter this extension to be connected with your representative.");

        try {
            // send an sms a call  
            // ( This makes a POST request to the SMS/Messages resource)
            Sms sms = smsFactory.create(params);
            System.out.println("Success sending SMS: " + sms.getSid());
        }
        catch (TwilioRestException e) {
            e.printStackTrace();
        }
        
    }       
}
