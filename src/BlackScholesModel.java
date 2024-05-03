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

        if (optionType.equals("call")) {
            return spotPrice * CND(d1) - strikePrice * Math.exp(-riskFreeRate * timeToExpiration) * CND(d2);
        } else if (optionType.equals("put")) {
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
}
