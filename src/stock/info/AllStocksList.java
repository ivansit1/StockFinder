/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.info;

import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class AllStocksList {
    private ArrayList<StockHtmlInfo> allStocksIndex = new ArrayList<StockHtmlInfo>();
    
    public ArrayList<StockHtmlInfo> getAllStockIndex(){
        return allStocksIndex;
    }
}
