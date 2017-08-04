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
public class StockHtmlInfo {
        private String stockIndex;
        private String tradeableVol;
        private boolean canShort;
        private boolean canOption;
        private boolean canFuture;
        
    public StockHtmlInfo(String stockIndex, String tradeableVol, boolean canShort, boolean canOption, boolean canFuture) {
        this.stockIndex = stockIndex;
        this.tradeableVol = tradeableVol;
        this.canShort = canShort;
        this.canOption = canOption;
        this.canFuture = canFuture;
    }

    public String getTradeableVol() {
        return tradeableVol;
    }

    public void setTradeableVol(String tradeableVol) {
        this.tradeableVol = tradeableVol;
    }

    public boolean isCanShort() {
        return canShort;
    }

    public void setCanShort(boolean canShort) {
        this.canShort = canShort;
    }

    public boolean isCanOption() {
        return canOption;
    }

    public void setCanOption(boolean canOption) {
        this.canOption = canOption;
    }

    public boolean isCanFuture() {
        return canFuture;
    }

    public void setCanFuture(boolean canFuture) {
        this.canFuture = canFuture;
    }

    public String getStockIndex() {
        return stockIndex;
    }

    public void setStockIndex(String stockIndex) {
        this.stockIndex = stockIndex;
    }
}
