package com.alkesh;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class Test {

    private static HttpClient getClient() {
        return HttpClients.createDefault();
    }

    private static String prompt() {
        String respString = null;
        HttpGet httpGet = null;
        try {
            HttpClient httpclient = getClient();
            httpGet = new HttpGet("http://localhost:9000/");
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            respString = IOUtils.toString(entity.getContent(), "UTF-8");
            System.out.println("Prompt. Status: " + response.getStatusLine().getStatusCode()
                    + " Response: " + respString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return respString;
    }

    private static void correctResponse() {
        String numbersStr = Test.prompt();
        // Parse the numbers out of the string - after the colon
        numbersStr = numbersStr.substring(numbersStr.lastIndexOf(":"));
        int sum = Numbers.getSum(Numbers.stringToArray(numbersStr));

        HttpPost httpPost = null;
        try {
            HttpClient httpclient = getClient();
            httpPost = new HttpPost("http://localhost:9000");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("question", numbersStr));
            nvps.add(new BasicNameValuePair("answer", String.valueOf(sum)));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println("Correct response test. Status: " + response.getStatusLine().getStatusCode()
                    + " Response: " + IOUtils.toString(entity.getContent(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    private static void inCorrectResponse() {
        String numbersStr = Test.prompt();
        // Parse the numbers out of the string - after the colon
        numbersStr = numbersStr.substring(numbersStr.lastIndexOf(":"));
        int sum = Numbers.getSum(Numbers.stringToArray(numbersStr)) + 100;

        HttpPost httpPost = null;
        try {
            HttpClient httpclient = getClient();
            httpPost = new HttpPost("http://localhost:9000");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("question", numbersStr));
            nvps.add(new BasicNameValuePair("answer", String.valueOf(sum)));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println("Incorrect response test. Status: " + response.getStatusLine().getStatusCode()
                    + " Response: " + IOUtils.toString(entity.getContent(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    public static void main(String... args) {
        Test.prompt();
        Test.correctResponse();
        Test.inCorrectResponse();
    }
}
