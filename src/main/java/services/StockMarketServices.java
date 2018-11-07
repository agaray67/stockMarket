package services;

import models.Stock;
import models.StockType;
import models.Symbol;
import models.Trade;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class StockMarketServices {

    private static final String COMMON = "Common";
    private static final String PREFERRED = "Preferred";

    public double calculateDividendYield(Stock s, double price){
        double dividendYield=-1;

        if(price!=0){
            if(s.getType().equals(StockType.COMMON)){
                dividendYield=s.getLastDividend()/price;
            }else if(s.getType().equals(StockType.PREFERRED)){
                dividendYield=(s.getFixedDividend()*s.getParValue())/price;
            }
        }

        return dividendYield;
    }

    public double calculatePERatio(double price, double dividend){
        double peRatio= -1;
        if(dividend!=0)
            peRatio = price / dividend;

        return peRatio;

    }

    public double calculateVolumeWeightedStockPrice(ArrayList<Trade> listTrade) {

        double vWSP = -1;
        double sumNumerador=0;
        double sumQuantity=0;

        for(Trade trade:listTrade) {
            long minutes = ChronoUnit.MINUTES.between(LocalDateTime.now(),trade.getTimestamp());
            if(minutes<=5 && minutes>=0){
                sumNumerador= sumNumerador + (trade.getPrice()*trade.getQuantity());
                sumQuantity= sumQuantity+trade.getQuantity();
            }
        }

        if(sumQuantity!= 0 && sumNumerador!=0)
            vWSP= sumNumerador/sumQuantity;

        return vWSP ;
    }

    public double calculateGBCE(ArrayList<Trade> listTrades) {
        double sumPrices = 0;
        double valGBCE = 0;

        for (Trade trade : listTrades)
            sumPrices = sumPrices + trade.getPrice();


        valGBCE = Math.pow(sumPrices, (1D / listTrades.size()));

        return valGBCE;
    }


    public ArrayList<Stock> initStock(){
        ArrayList<Stock> list = new ArrayList<Stock>();
        list.add(new Stock(Symbol.TEA, StockType.COMMON, 0, 0, 100));
        list.add(new Stock(Symbol.POP, StockType.COMMON, 8, 0, 100));
        list.add(new Stock(Symbol.ALE, StockType.COMMON, 23, 0, 60));
        list.add(new Stock(Symbol.GIN, StockType.PREFERRED, 8, 2, 100));
        list.add(new Stock(Symbol.JOE, StockType.COMMON, 13, 0, 250));

        return list;
    }




}
