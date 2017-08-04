/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.util;

import java.util.Comparator;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;

/**
 *
 * @author Ivan
 */
public class TableFilters {
    public static RowFilter<TableModel, Object> setStockMarkeCap(String stockCap, String type){
        RowFilter<TableModel, Object> filter = new RowFilter<TableModel, Object>() {
                public boolean include(RowFilter.Entry entry) {
                  
                      double d1 = 0;
                        try {
                    d1 = Double.parseDouble(entry.getValue(3).toString().replace(",", "").split("億")[0]);
                } catch(Exception e) {
                    System.out.println("d1 unable to convert="+entry.getValue(3));
                }
                  if("L".equals(type)){
                    return d1 > Integer.parseInt(stockCap);
                  } else {
                      return d1 < Integer.parseInt(stockCap);
                  }
                  
                }
              };
        return filter;
    }
}
