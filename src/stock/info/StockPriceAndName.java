/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.info;

/**
 *
 * @author Ivan
 */
public class StockPriceAndName {    
    private String index;
    private String name;
    
     public StockPriceAndName(String index , String name) {
        this.index = index;
        this.name = name;
        
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getIndex() {
        return index;
    }
 
    public void setIndex(String index) {
        this.index = index;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
