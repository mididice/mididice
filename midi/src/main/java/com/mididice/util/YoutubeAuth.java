package com.mididice.util;

import java.io.File;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class YoutubeAuth {
	/** Global instance of the HTTP transport. */
	  public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	  /** Global instance of the JSON factory. */
	  public static final JsonFactory JSON_FACTORY = new JacksonFactory();

	  /**
	   * Authorizes the installed application to access user's protected data.
	   *
	   * @param scopes list of scopes needed to run youtube upload.
	   */
	  public static Credential authorize(List<String> scopes) throws Exception {

	    // Load client secrets.
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
	        JSON_FACTORY, YoutubeAuth.class.getResourceAsStream("/client_secrets.json"));

	    // Checks that the defaults have been replaced (Default = "Enter X here").
	    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
	        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
	      System.out.println(
	          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=youtube"
	          + "into youtube-cmdline-uploadvideo-sample/src/main/resources/client_secrets.json");
	      System.exit(1);
	    }

	    // Set up file credential store.
	    FileCredentialStore credentialStore = new FileCredentialStore(
	        new File(System.getProperty("user.home"), ".credentials/youtube-api-uploadvideo.json"),
	        JSON_FACTORY);
	    
	    // Set up authorization code flow.
	    // 토큰 기간 만료 ( 1hours )
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setCredentialStore(credentialStore)
	        .build();
	    
	    
	    // Set up authorization code flow.
	    // 재사용 토큰으로 인한 인증 유지
	    /*GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes).setAccessType("offline")
	        		.setApprovalPrompt("force").setCredentialStore(credentialStore).build();*/

	    // Build the local server and bind it to port 9000
	    LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8099).build();

	    // Authorize.
	    return new AuthorizationCodeInstalledApp(flow, localReceiver).authorize("user");
	  }
}
