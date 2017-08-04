/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import stock.finder.MainPage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;


/**
 *
 * @author Ivan
 */
public class HtmlConnectUtil {

    public static String createLinkToWebpage(String url) {
        String webpageFullCode = "";
        try {
            URL urlLink = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlLink.openStream()));
            
            StringBuffer builder = new StringBuffer();
            String aux = "";
            while ((aux = in.readLine()) != null) {
                builder.append(aux.trim());
            }
            webpageFullCode = builder.toString();           
            in.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return webpageFullCode;
    }
}
