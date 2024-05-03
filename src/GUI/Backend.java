package GUI;
import java.util.ArrayList;

import GUI.BlackScholesModel;
import GUI.Home;

public class Backend {

	static String[] generatePriceList(int start, int end, int interval) {
        int size = (end - start) / interval + 1;
        String[] prices = new String[size];
        for (int i = 0; i < size; i++) {
            prices[i] = String.valueOf(start + (i * interval));
        }
        return prices;
    }
   
	static double calculateTotalMargin(ArrayList<String> cp,ArrayList<String> bs,ArrayList<Double> oprice2) {
        // Logic to calculate total margin requirement based on the positions in the list model
        // You need to implement this based on your specific requirements
		double margin=0;
		for(int a=0;a<oprice2.size();a++) {
			margin+=oprice2.get(a);
		}
        return margin; // Placeholder value
    }

    // Method to calculate Profit/Loss
    static double calculateProfitLoss(double strikePrice,double spotPrice,ArrayList<String> cp,ArrayList<String> bs,ArrayList<Double> oprice2) {
        int pnl=0;
        for (int i = 0; i < oprice2.size(); i++) {
        	double priceAtExpiry=BlackScholesModel.calculateOptionPrice(spotPrice,strikePrice,0.027,0.02,0.1,cp.get(i));
        	if(bs.get(i)=="Buy") {
			pnl+=priceAtExpiry-oprice2.get(i);
        	}
        	else {
        		pnl+=oprice2.get(i)-priceAtExpiry;
        	}
		}
        return pnl; // Placeholder value
    }

    // Method to calculate RoI (Return on Investment)
     static double calculateROI(double strikePrice,double spotPrice,ArrayList<String> cp,ArrayList<String> bs,ArrayList<Double> oprice2) {
    	double margin=0;
 		for(int a=0;a<oprice2.size();a++) {
 			margin+=oprice2.get(a);
 		}
 		int pnl=0;
        for (int i = 0; i < oprice2.size(); i++) {
        	double priceAtExpiry=BlackScholesModel.calculateOptionPrice(spotPrice,strikePrice,0.027,0.02,0.1,cp.get(i));
			pnl+=priceAtExpiry-oprice2.get(i);
		}
        
        double roi=((double) pnl/margin)*100;
        
        
        
 		
        return roi; // Placeholder value
    }

    // Method to calculate Risk-Reward ratio
    static double calculateRiskReward(ArrayList<String> cp,ArrayList<String> bs,ArrayList<Double> oprice2) {
        
        return 0.0; // Placeholder value
    }
	
	
}
