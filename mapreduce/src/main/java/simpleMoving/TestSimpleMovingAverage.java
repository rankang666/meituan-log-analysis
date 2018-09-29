package simpleMoving;

import java.util.logging.Logger;

public class TestSimpleMovingAverage {
    private static final Logger THE_LOGGER =
            Logger.getLogger("TestSimpleMovingAverage");

    public static void main(String[] args) {
        double[] testData = {10, 18, 20, 30, 24, 33, 27};
        int[] allWindowSizes = {3,4};

        //使用队列
        for (int windowSize : allWindowSizes){
            SimpleMovingAverage sma = new SimpleMovingAverage(windowSize);
            THE_LOGGER.info("winSize = " + windowSize);
            for(double x : testData){
                sma.addNewNumber(x);
                THE_LOGGER.info("Next number = " + x + ", SMA = "+ sma.getMovingAverage());
            }
            THE_LOGGER.info("---");
        }

        THE_LOGGER.info("----------------------------------------------");

        //使用数组
        for (int windowSize : allWindowSizes){
            SimpleMovingAverageUsingArray sma1 = new SimpleMovingAverageUsingArray(windowSize);
            THE_LOGGER.info("winSize = " + windowSize);
            for(double x : testData){
                sma1.addNewNumber(x);
                THE_LOGGER.info("Next number = " + x + ", SMA = "+ sma1.getMovingAverage());
            }
            THE_LOGGER.info("---");

        }


    }

}
