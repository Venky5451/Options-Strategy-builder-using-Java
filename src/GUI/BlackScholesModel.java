package GUI;
public class BlackScholesModel {

    public static double calculateOptionPrice(
            double spotPrice, // Current price of the underlying asset
            double strikePrice, // Strike price of the option
            double timeToExpiration, // Time to expiration of the option (in years)
            double riskFreeRate, // Risk-free interest rate (annualized)
            double volatility, // Volatility of the underlying asset (annualized)
            String optionType // "call" or "put"
    ) {

        double d1 = (Math.log(spotPrice / strikePrice) + (riskFreeRate + (volatility * volatility) / 2) * timeToExpiration) / (volatility * Math.sqrt(timeToExpiration));
        double d2 = d1 - volatility * Math.sqrt(timeToExpiration);

        if (optionType.equals("Call")) {
            return spotPrice * CND(d1) - strikePrice * Math.exp(-riskFreeRate * timeToExpiration) * CND(d2);
        } else if (optionType.equals("Put")) {
            return strikePrice * Math.exp(-riskFreeRate * timeToExpiration) * CND(-d2) - spotPrice * CND(-d1);
        } else {
            throw new IllegalArgumentException("Invalid option type. Please use 'call' or 'put'.");
        }
    }

    // Cumulative normal distribution function
    private static double CND(double x) {
        return 0.5 * (1 + erf(x / Math.sqrt(2)));
    }

    // Error function
    private static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                        t * (0.37409196 +
                                t * (0.09678418 +
                                        t * (-0.18628806 +
                                                t * (0.27886807 +
                                                        t * (-1.13520398 +
                                                                t * (1.48851587 +
                                                                        t * (-0.82215223 +
                                                                                t * (0.17087277))))))))));
        if (z >= 0) return ans;
        else return -ans;
    }
//    public static void main(String args[]){
//    	double ans1 =calculateOptionPrice(
//                21500,
//                21500, // Strike price of the option
//                0.016, // Time to expiration of the option (in years)
//                0.02, // Risk-free interest rate (annualized)
//                0.1, // Volatility of the underlying asset (annualized)
//                "Call" // "call" or "put"
//        );
//    	
//    	System.out.println(ans1);
//
//	}
    // Method to calculate Total Margin Requirement
    public static double cumulativeDistributionFunction(double x) {
        return 0.5 * (1 + erf(x / Math.sqrt(2)));
    }


    public static double calculateDelta(double spotPrice, double strikePrice, double riskFreeRate, double volatility, double expiryTime,String callPut) {
        double d1 = calculateOptionPrice(spotPrice, strikePrice, riskFreeRate, volatility, expiryTime,callPut);
        return cumulativeDistributionFunction(d1);
    }

    public static double calculateGamma(double spotPrice, double riskFreeRate, double volatility, double expiryTime,String callPut) {
        double d1 =calculateOptionPrice(spotPrice, spotPrice, riskFreeRate, volatility, expiryTime,callPut);
        return Math.exp(-Math.pow(d1, 2) / 2) / (spotPrice * volatility * Math.sqrt(expiryTime)) / Math.sqrt(2 * Math.PI);
    }

    public static double calculateVega(double spotPrice, double riskFreeRate, double volatility, double expiryTime,String callPut) {
        double d1 = calculateOptionPrice(spotPrice, spotPrice, riskFreeRate, volatility, expiryTime,callPut);
        return spotPrice * Math.exp(-Math.pow(d1, 2) / 2) * Math.sqrt(expiryTime) / Math.sqrt(2 * Math.PI);
    }

    public static double calculateTheta(double spotPrice, double strikePrice, double riskFreeRate, double volatility, double expiryTime,String callPut) {
        double d1 = calculateOptionPrice(spotPrice, strikePrice, riskFreeRate, volatility, expiryTime,callPut);
        double d2 = d1 - volatility * Math.sqrt(expiryTime);
        return (-spotPrice * Math.exp(-Math.pow(d1, 2) / 2) * volatility) / (2 * Math.sqrt(expiryTime))
                - riskFreeRate * strikePrice * Math.exp(-riskFreeRate * expiryTime) * cumulativeDistributionFunction(d2);
    }

    
}
