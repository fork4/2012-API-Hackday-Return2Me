package com.fork4.return2me.twilio;

import com.twilio.sdk.TwilioRestResponse;

public class IvrResponse extends TwilioBase {
	/*
	
	header('Content-type: text/xml');
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	echo '<Response>';

	$user_pushed = (int) $_REQUEST['Digits'];

	if ($user_pushed == 1)
	{
		echo '<Say>Our store hours are 8 AM to 8 PM everyday.</Say>';
	}
	# @start snippet
	else if ($user_pushed == 2)
	{
		echo '<Gather action="handle-extension.php" numDigits="1">';
		echo "<Say>Please enter your party's extension.</Say>";
		echo '<Say>Press 0 to return to the main menu</Say>';
		echo '</Gather>';
		echo "<Say>Sorry, I didn't get your response.</Say>";
		echo '<Redirect method="GET">handle-user-input.php?Digits=2</Redirect>';
	}
	# @end snippet
	else {
		// We'll implement the rest of the functionality in the 
		// following sections.
		echo "<Say>Sorry, I can't do that yet.</Say>";
		echo '<Redirect>handle-incoming-call.php</Redirect>';
	}

	echo '</Response>';
	*/
	public static String callRedirect(String number) {
		StringBuilder response = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		response.append("<Response>");
		response.append("<Say>Connecting you to your representative.</Say>");
		response.append("<Dial>+1" + number.replaceAll("-", "") + "</Dial>");
		response.append("</Response>");
		
		return response.toString();
	
	}

	public static String badExtension() {
		StringBuilder response = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		response.append("<Response>");
		response.append("<Gather action=\"http://return2me.elasticbeanstalk.com/fetch\" numDigits=\"5\" timeout=\"10\" finishOnKey=\"#\">");
		response.append("<Say>Extension not found.</Say>");
		response.append("<Say>Enter the extension for your representative. Press pound to continue.</Say>");
		response.append("</Gather>");
		response.append("<Say>Sorry, I didn't get your response.</Say>");
		response.append("<Redirect>http://return2me.elasticbeanstalk.com/ivr/incoming.xml</Redirect>");
		response.append("</Response>");
		
		return response.toString();
	
	}

}
