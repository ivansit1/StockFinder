/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

import java.util.Comparator;

/**
 *
 * @author Ivan
 */
public class TableSorters {
    public  static Comparator<String> setStockMarketCapSort(){
        Comparator<String> marketCapSort =  new Comparator<String>() { 
            @Override
            public int compare(String name1, String name2) {
                double d1 = 0;
                double d2 = 0;
                try {
                    d1 = Double.parseDouble(name1.replace(",", "").split("億")[0]);
                } catch(Exception e) {
                    System.out.println("d1 unable to convert="+name1);
                }
                 try {
                    d2 = Double.parseDouble(name2.replace(",", "").split("億")[0]);
                } catch(Exception e) {
                    System.out.println("d2 unable to convert="+name1);
                }
                return Double.compare(d1, d2);            
            }
        };
         return marketCapSort;
    }
}
