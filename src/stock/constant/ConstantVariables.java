/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.constant;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import stock.info.AllStocksList;
import stock.info.StockHtmlInfo;
import stock.util.HtmlConnectUtil;

/**
 *
 * @author Ivan
 */
public final class ConstantVariables {
    private static AllStocksList allStocks = new AllStocksList();
    private static String stockListUrl = "https://www.hkex.com.hk/eng/market/sec_tradinfo/stockcode/eisdeqty.htm";    
    private static MessageFormat stockDetailsUrl = new MessageFormat("http://www.dbpower.com.hk/ch/quote/quote-stock/code/{0}");    
    private static MessageFormat stockBollingerGraphUrl = new MessageFormat("http://charts.aastocks.com/servlet/Charts?fontsize=12&15MinDelay=T&lang=1&titlestyle=1&vol=1&Indicator=9&indpara1=20&indpara2=2&indpara3=0&indpara4=0&indpara5=0&scheme=1&com=100&chartwidth=673&chartheight=480&stockid={0}&period={1}&type=1&logoStyle=1&");
    private static MessageFormat stockSMAGraphUrl = new MessageFormat("http://charts.aastocks.com/servlet/Charts?fontsize=12&15MinDelay=T&lang=1&titlestyle=1&vol=1&Indicator=1&indpara1=10&indpara2=20&indpara3=50&indpara4=200&indpara5=0&scheme=1&com=100&chartwidth=673&chartheight=480&stockid={0}&period={1}&type=1&logoStyle=1&");
    private ConstantVariables() { // private constructor      
    }
    
    public static void Init(){
        try{
            Document doc = Jsoup.connect(stockListUrl).maxBodySize(0).get();
            Elements links = doc.select("tr");
            for (Element link : links) {
                System.out.println(link.text());
                
                String[] stockData = link.text().split(" ");
                try{
                    Integer.parseInt(stockData[0]);
                    String tradeableVol = stockData[1];
                    boolean canShort = false;
                    boolean canOption = false;
                    boolean canFuture = false;
        //abcc                     
                    for(String s : stockData){
                        if("H".equals(s)){
                            canShort = true;
                        } else if("O".equals(s)){
                            canOption = true;
                        } else if("F".equals(s)){
                            canFuture = true;
                        }
                    }
                    allStocks.getAllStockIndex().add(new StockHtmlInfo(stockData[0], tradeableVol, canShort, canOption, canFuture));           
                } catch (Exception e){
                    System.out.println(e);
                }            
                
            }
        }  catch (Exception e){
                    System.out.println(e);
        }       

          //Pattern p = Pattern.compile("(?<=<td class=\"verd_black12\" width=\"18%\">).*?(?=</td>)");   
        /*
        Pattern p = Pattern.compile("(?<=<tr class=\"tr_normal\">).*?(?=</tr>)");   
          
             Matcher m = p.matcher(HtmlConnectUtil.createLinkToWebpage(stockListUrl));
             System.out.println(HtmlConnectUtil.createLinkToWebpage(stockListUrl));
        while (m.find()){    
            try {                
                
                System.out.println(m.group(0));
                allStocks.getAllStockIndex().add(Integer.toString(Integer.parseInt(m.group(0))));           
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
                
        }       
                */
    }
    
    /*
    private String getStockTd(String stockHtml){
        Pattern p = Pattern.compile("(?<=<tr class=\"tr_normal\"><td class=\"verd_black12\" width=\"18%\">).*?(?=</tr>)");   
        
    }
    */
    public static AllStocksList getAllStocks(){
        return allStocks;
    }
    
    public static String getStockListUrl(){
        return stockListUrl;
    }
    
    public static MessageFormat getStockDetailsUrl(){
        return stockDetailsUrl ;
    }    
    
    public static MessageFormat getStockGraphUrl(String type){
        if("Bollinger".equals(type)){
            return stockBollingerGraphUrl ;
        } else if("SMA".equals(type)) {
            return stockSMAGraphUrl;
        } else {
            return stockSMAGraphUrl;
        }
    }    
}
