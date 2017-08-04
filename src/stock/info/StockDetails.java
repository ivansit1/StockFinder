/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.info;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ivan
 */
public class StockDetails {
    private String stockWebPageDetailCode = "";
    private String index = "";
    private String price = "";
    private String name = "";
    private String marketCap = "";
    
    private boolean stockExists = false;
    public StockDetails() {
    }
    
    public StockDetails(String webpageFullCode, String stockIndex) {
        stockWebPageDetailCode = webpageFullCode;
//System.out.println(stockWebPageDetailCode);        
        this.index = stockIndex;
        getPriceFromCode();
        getNameFromCode();
        getMarketCapFromCode();
        if(name != null && name.length() > 0){
            stockExists = true;
        }
    }
    
     private String getValueWithPattern(String pattern){
        String value = "";
        Pattern p = Pattern.compile(pattern);   
         Matcher m = p.matcher(stockWebPageDetailCode);
        
        if (m.find()) {
           value = m.group(0);
        }        
        return value;
    }
    
    public void getPriceFromCode(){  
           price = getValueWithPattern("(?<=即時</li--><li class=\"quote\">).*?(?=</li><li class=\"change\"><span class=')");  
    }
    
    public void getNameFromCode(){
          name = getValueWithPattern("(?<=<hgroup class=\"cp_title\"><h2>).*?(?=</h2>)");        
          String[] tempName = name.split(" ", 2);
          if ( tempName.length == 2){
          name = tempName[1];
          }
    }
     public void getMarketCapFromCode(){
          marketCap = getValueWithPattern("(?<=<th>市價總值.百萬.</th><td>).*?(?=</td>)");      
          marketCap = marketCap.replaceAll(",", "");
            
          //System.out.println("MARKET CAP YO =" + marketCap);
          if (marketCap != null && marketCap.length() > 0 && !"-".equals(marketCap)){
            try{
                float intMarketCap = Integer.parseInt(marketCap);
                intMarketCap = intMarketCap * 1000000;
                intMarketCap = intMarketCap / 100000000;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);             
                marketCap = df.format(intMarketCap);
                marketCap = marketCap + "億";
            } catch (Exception e) {
                System.out.println(e);
            }
          }
    }
    
    
    public String getIndex(){
        return index;
    }
    
    public String getPrice(){
        return price;
    }
    
    public String getName(){
        return name;
    }    
    
     public String getMarketCap(){
        return marketCap;
    }    
    
    public boolean isStockExists(){
        return stockExists;
    }
}
