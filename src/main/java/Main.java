import models.Stock;
import models.StockType;
import models.Trade;
import models.TradeType;
import services.StockMarketServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String args[]) throws ParseException {
        StockMarketServices stockMarketServices = new StockMarketServices();
        List<Stock> list;
        list = stockMarketServices.initStock();

        for(Stock stock : list){

            //Calculate dividendYield
            double dividendYield = stockMarketServices.calculateDividendYield(stock,100);
            if(dividendYield==-1)
                System.out.println("No se pudo calcular el dividendYield, price es 0");
            else
                System.out.println("For symbol = " + stock.getSymbol() + " the dividend yield = " + dividendYield);

            //Calculate P/E Ratio
            double peRatio = stockMarketServices.calculatePERatio(100, dividendYield);
            if(dividendYield==-1)
                System.out.println("No se pudo calcular el P/E Ratio, dividendo es 0");
            else
                System.out.println("The value P/E Ratio is = " + peRatio);

            //Record Trade
            Trade trade = new Trade(LocalDateTime.now(), 10, TradeType.BUY, 100);

            //Volume Weighted Stock Price
            ArrayList<Trade> listTrade = new ArrayList<Trade>();
            listTrade.add(new Trade(LocalDateTime.of(2018, 11, 07, 12, 16, 45, 35), 10, TradeType.BUY, 100));
            listTrade.add(new Trade(LocalDateTime.of(2018, 11, 07, 12, 10, 45, 35), 10, TradeType.BUY, 1000));
            listTrade.add(new Trade(LocalDateTime.of(2018, 11, 07, 12, 15, 45, 35), 10, TradeType.SELL, 999.99));
            listTrade.add(new Trade(LocalDateTime.of(2018, 11, 07, 1, 04, 45, 35), 10, TradeType.BUY, 1000));
            listTrade.add(new Trade(LocalDateTime.of(2018, 11, 07, 11, 04, 45, 35), 10, TradeType.BUY, 1000));

            double valVWSP = stockMarketServices.calculateVolumeWeightedStockPrice(listTrade);
            if (valVWSP==-1)
                System.out.println("No se pudo calcular el VWSP");
            else
                System.out.println("The Value Weighted Stock Price is = " + valVWSP);

            // GBCE
            double valGBCE = stockMarketServices.calculateGBCE(listTrade);
            System.out.println("The Value GBCE is = " + valGBCE);

        }



    }


}
