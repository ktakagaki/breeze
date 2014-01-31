package breeze.stats

/**
 * @author ktakagaki
 * @date 1/31/14.
 */
object CircularStats {

}


//
//package de.ifn_magdeburg.kazukazuj;
//
////import de.ifn_magdeburg.kazukazuj.K;
////import de.ifn_magdeburg.kazukazuj.Messages;
//
///**
// *
// * @author Kenta
// */
//public class Kc {
//
//  //<editor-fold defaultstate="collapsed" desc=" circularWrap ">
//  /**Takes any phase value and returns the wrapped value in the range of (-Pi, Pi].
//    */
//  public static double circularWrap(double ph){
//    return K.mod(ph + K.PI, 2*K.PI) - K.PI;
//  }
//  /** {@link #circularWrap(double)} threaded over array. */
//  public static double[] circularWrap(double[] ph){
//    double[] tempret = new double[ph.length];
//    for(int k=0; k<ph.length; k++){
//      tempret[k]=circularWrap(ph[k]);
//    }
//    return tempret;
//  }
//  /** {@link #circularWrap(double)} threaded over array. */
//  public static double[][] circularWrap(double[][] ph){
//    double[][] tempret = new double[ph.length][];
//    for(int k=0; k<ph.length; k++){
//      tempret[k]=circularWrap(ph[k]);
//    }
//    return tempret;
//  }
//  //</editor-fold>
//
//  //<editor-fold defaultstate="collapsed" desc=" circularMean ">
//  /**Takes a list of angles and returns the mean direction.
//    * Elements of the vector can exceed the range (-Pi, Pi].
//    */
//  public static double circularMean(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(vector[k]);
//      sinSum += K.sin(vector[k]);
//    }
//    return K.arcTan(cosSum, sinSum);
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularResultantLength ">
//  /**Takes a list of phases and returns the resultant length.
//    */
//  public static double circularResultantLength(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(vector[k]);
//      sinSum += K.sin(vector[k]);
//    }
//    return K.sqrt(cosSum*cosSum + sinSum*sinSum);
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularTrigonometricMoment ">
//  /**Returns the nth trigonometric moment of the distribution.
//    */
//  public static Complex circularTrigonometricMoment(double[] vector, int p){
//    double cosSum = 0d, sinSum = 0d;
//    double pD = (double)p;
//    double len = (double)vector.length;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(pD * vector[k]);
//      sinSum += K.sin(pD * vector[k]);
//    }
//    return new Complex(cosSum/len, sinSum/len);
//  }
//  /**Returns the (1st) trigonometric moment of the distribution.
//    */
//  public static Complex circularTrigonometricMoment(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    double len = (double)vector.length;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(vector[k]);
//      sinSum += K.sin(vector[k]);
//    }
//    return new Complex(cosSum/len, sinSum/len);
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularCenteredTrigonometricMoment ">
//  /**Returns the nth centered trigonometric moment of the distribution.
//    */
//  public static Complex circularCenteredTrigonometricMoment(double[] vector, int p){
//    if(p==1){
//      return circularCenteredTrigonometricMoment(vector);
//    }else{
//      double cosSum = 0d, sinSum = 0d;
//      double mean=Kc.circularMean(vector);
//      double pD = (double)p;
//      double len = (double)vector.length;
//      for(int k=0; k<vector.length; k++){
//        cosSum += K.cos(pD * (vector[k]- mean));//Kc.circularSubtract(vector[k], mean));
//        sinSum += K.sin(pD * (vector[k]- mean));//Kc.circularSubtract(vector[k], mean));
//      }
//      //return cosSum/len;
//      return new Complex(cosSum/len, sinSum/len);
//    }
//  }
//  /**Returns the (1st) centered trigonometric moment of the distribution.
//    */
//  public static Complex circularCenteredTrigonometricMoment(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    double mean=Kc.circularMean(vector);
//    double len = (double)vector.length;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(vector[k]- mean);
//      sinSum += K.sin(vector[k]- mean);
//    }
//    //return cosSum/len;
//    return new Complex(cosSum/len, sinSum/len);
//  }
//  //</editor-fold>
//
//  //<editor-fold defaultstate="collapsed" desc=" circularMeanResultantLength ">
//  /**Takes a list of phases and returns the mean resultant length.
//    */
//  public static double circularMeanResultantLength(double[] vector){
//    return circularResultantLength(vector)/(double)vector.length;
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularVariance ">
//  /**Takes a list of phases and returns the circular variance.
//    */
//  public static double circularVariance(double[] vector){
//    return 1d-circularMeanResultantLength(vector);
//  }
//  //</editor-fold>
//
//  //<editor-fold defaultstate="collapsed" desc=" circularStandardDeviation ">
//  /**Takes a list of phases and returns the circular standard deviation.
//    */
//  public static double circularStandardDeviation(double[] vector){
//    return K.sqrt(
//      -2 * K.log( circularMeanResultantLength(vector) )
//    );
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularDispersion ">
//  /**Returns the sample circular dispersion.
//    */
//  public static double circularDispersion(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    double cosSum2 = 0d, sinSum2 = 0d;
//    //double cosCentSum2 = 0d;
//    //double mean=Kc.circularMean(vector);
//    double len = (double)vector.length;
//    for(int k=0; k<vector.length; k++){
//      //cosCentSum2 += K.cos(2 * Kc.circularSubtract(vector[k], mean));
//      cosSum += K.cos(vector[k]);
//      sinSum += K.sin(vector[k]);
//      cosSum2 += K.cos(2*vector[k]);
//      sinSum2 += K.sin(2*vector[k]);
//    }
//
//    return (1-K.sqrt(cosSum2*cosSum2 + sinSum2*sinSum2)/len)
//    /2d /((cosSum*cosSum + sinSum*sinSum)/len/len);
//    //cosCentSum2 /= len;
//    // cosSum /= len; sinSum /= len;
//
//    //        double rho1squared = cosSum*cosSum+sinSum*sinSum;
//    //        double rho2 = cosCentSum2/len;
//    //
//    //        return (1-rho2)/2/rho1squared;
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularSkewness ">
//  /**Returns the sample skewness.
//    */
//  public static double circularSkewness(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    double cosSum2 = 0d, sinSum2 = 0d;
//    double len = (double)vector.length;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(vector[k]);
//      sinSum += K.sin(vector[k]);
//      cosSum2 += K.cos(2 * vector[k]);
//      sinSum2 += K.sin(2 * vector[k]);
//    }
//
//    double Rsq = (cosSum*cosSum+sinSum*sinSum)/len/len;
//    double R = K.sqrt(Rsq);
//    double R2 = K.sqrt(cosSum2*cosSum2+sinSum2*sinSum2)/len;
//    double theta1 = K.arcTan(cosSum, sinSum);
//    double theta2 = K.arcTan(cosSum2, sinSum2);
//
//    return (R2 * K.sin(theta2 - 2*theta1) ) / K.power( 1 - R, 1.5);
//  }
//  //    public static double circularSkewness(double[] vector){
//  //        double cosSum = 0d, sinSum = 0d;
//  //        double cosCentSum2 = 0d;
//  //        double cosSum2 = 0d, sinSum2 = 0d;
//  //        double mean=Kc.circularMean(vector);
//  //        double len = (double)vector.length;
//  //        for(int k=0; k<vector.length; k++){
//  //            cosCentSum2 += K.cos(2 * Kc.circularSubtract(vector[k], mean));
//  //            cosSum += K.cos(vector[k]);
//  //            sinSum += K.sin(vector[k]);
//  //            cosSum2 += K.cos(2 * vector[k]);
//  //            sinSum2 += K.sin(2 * vector[k]);
//  //        }
//  //        cosCentSum2 /= len;
//  //        cosSum /= len; sinSum /= len;
//  //
//  //        double rho1squared = cosSum*cosSum+sinSum*sinSum;
//  //        double rho1 = K.sqrt(rho1squared);
//  //        double rho2 = cosCentSum2;
//  //        double mu1 = K.arcTan(cosSum, sinSum);
//  //        double mu2 = K.arcTan(cosSum2, sinSum2);
//  //
//  //        return (rho2 * K.sin(Kc.circularSubtract(mu2, 2*mu1)) ) / K.power( 1 - rho1, 1.5);
//  //    }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularKurtosis ">
//  /**Returns the sample kurtosis.
//    */
//  public static double circularKurtosis(double[] vector){
//    double cosSum = 0d, sinSum = 0d;
//    double cosSum2 = 0d, sinSum2 = 0d;
//    double len = (double)vector.length;
//    for(int k=0; k<vector.length; k++){
//      cosSum += K.cos(vector[k]);
//      sinSum += K.sin(vector[k]);
//      cosSum2 += K.cos(2 * vector[k]);
//      sinSum2 += K.sin(2 * vector[k]);
//    }
//
//    double Rsq = (cosSum*cosSum+sinSum*sinSum)/len/len;
//    double R = K.sqrt(Rsq);
//    double R2 = K.sqrt(cosSum2*cosSum2+sinSum2*sinSum2)/len;
//    double theta1 = K.arcTan(cosSum, sinSum);
//    double theta2 = K.arcTan(cosSum2, sinSum2);
//
//    return ((R2 * K.cos(theta2 - 2*theta1)) - Rsq*Rsq)
//    / K.power( 1 - R, 2 );
//  }
//  //    public static double circularKurtosis(double[] vector){
//  //        double cosSum = 0d, sinSum = 0d;
//  //        //double cosCentSum = 0d;
//  //        double cosCentSum2 = 0d;
//  //        double cosSum2 = 0d, sinSum2 = 0d;
//  //        double mean=Kc.circularMean(vector);
//  //        double len = (double)vector.length;
//  //        for(int k=0; k<vector.length; k++){
//  //            //cosCentSum += K.cos(Kc.circularSubtract(vector[k], mean));
//  //            cosCentSum2 += K.cos(2 * Kc.circularSubtract(vector[k], mean));
//  //            cosSum += K.cos(vector[k]);
//  //            sinSum += K.sin(vector[k]);
//  //            cosSum2 += K.cos(2 * vector[k]);
//  //            sinSum2 += K.sin(2 * vector[k]);
//  //        }
//  //        //cosCentSum2 /= len;
//  ////        cosSum /= len; siSum /= len;
//  //
//  //        double rho1squared = cosSum*cosSum+sinSum*sinSum;
//  //        double rho1 = K.sqrt(rho1squared);
//  ////        double rho1 = cosCentSum / len;
//  //        double rho2 = cosCentSum2 / len;
//  //        double mu1 = K.arcTan(cosSum, sinSum);
//  //        double mu2 = K.arcTan(cosSum2, sinSum2);
//  //
//  //        return (rho2 * K.cos(Kc.circularSubtract(mu2, 2*mu1)) - K.pow(rho1, 4) )
//  //                / K.power( 1 - rho1, 2 );
//  ////        return (rho2 * K.cos(Kc.circularSubtract(mu2, 2*mu1)) - rho1squared*rho1squared )
//  ////                / K.power( 1 - rho1, 2 );
//  //    }
//  //</editor-fold>
//
//
//  //<editor-fold defaultstate="collapsed" desc=" circularSubtract ">
//  /**Takes a two phases and returns the circular distance.
//    * Returns a value (-Pi, Pi].
//    */
//  public static double circularSubtract(double ph1, double ph2){
//    return circularWrap(ph1-ph2);
//  }
//  /**Threads circularSubtract(double, double) over a vector.*/
//  public static double[] circularSubtract(double[] ph1, double[] ph2){
//    Messages.checkSameDimensions(ph1, ph2);
//    double[] tempret = new double[ph1.length];
//    for(int k=0; k<ph1.length; k++){
//      tempret[k]=circularSubtract(ph1[k], ph2[k]);
//    }
//    return tempret;
//  }
//  /**Threads circularSubtract(double, double) over a vector.*/
//  public static double[] circularSubtract(double[] ph1, double ph2){
//    double[] tempret = new double[ph1.length];
//    for(int k=0; k<ph1.length; k++){
//      tempret[k]=circularSubtract(ph1[k], ph2);
//    }
//    return tempret;
//  }
//  /**Threads circularSubtract(double, double) over a matrix.*/
//  public static double[][] circularSubtract(double[][] ph1, double[][] ph2){
//    Messages.checkSameDimensions1(ph1, ph2);
//    double[][] tempret = new double[ph1.length][];
//    for(int k=0; k<ph1.length; k++){
//      tempret[k]=circularSubtract(ph1[k], ph2[k]);
//    }
//    return tempret;
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularPlus ">
//  /**Takes a two phases and returns the circular sum.
//    * You may be looking for {@link #circularSum(double[])} instead.
//    * Returns a value (-Pi, Pi].
//    */
//  public static double circularPlus(double ph1, double ph2){
//    return circularWrap(ph1+ph2);
//  }
//  /**Threads circularPlus(double, double) over a vector.*/
//  public static double[] circularPlus(double[] ph1, double[] ph2){
//    Messages.checkSameDimensions(ph1, ph2);
//    double[] tempret = new double[ph1.length];
//    for(int k=0; k<ph1.length; k++){
//      tempret[k]=circularPlus(ph1[k], ph2[k]);
//    }
//    return tempret;
//  }
//  /**Threads circularPlus(double, double) over a vector.*/
//  public static double[] circularPlus(double[] ph1, double ph2){
//    double[] tempret = new double[ph1.length];
//    for(int k=0; k<ph1.length; k++){
//      tempret[k]=circularPlus(ph1[k], ph2);
//    }
//    return tempret;
//  }
//  /**Threads circularPlus(double, double) over a matrix.*/
//  public static double[][] circularPlus(double[][] ph1, double[][] ph2){
//    Messages.checkSameDimensions1(ph1, ph2);
//    double[][] tempret = new double[ph1.length][];
//    for(int k=0; k<ph1.length; k++){
//      tempret[k]=circularPlus(ph1[k], ph2[k]);
//    }
//    return tempret;
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularSum ">
//  /**Returns the cumulative phase of the data, which is equivalent to the
//    * results of summing the circular subtraction difference of each subsequent
//    * pair of elements (i.e., the phases will be summed along the unit circle,
//    * with no phase wrap).
//    * @param circData
//    */
//  public static double circularSum(double[] circData){
//    double tempret = circData[0];
//    if(circData.length>1){
//      for(int n=1; n<circData.length; n++){
//        tempret += circularSubtract(circData[n], circData[n-1]);
//      }
//    }
//    return tempret;
//  }
//  //</editor-fold>
//  //<editor-fold defaultstate="collapsed" desc=" circularSum ">
//  /**Accumulates the data circularly (i.e., the phases will be summed along the unit circle,
//    * with no phase wrap).
//    * @param circData
//    */
//  public static double[] circularAccumulate(double[] circData){
//    double[] tempret = new double[circData.length];
//    tempret[0]=circData[0];
//    double sum=circData[0];
//    if(circData.length>1){
//      for(int n=1; n<circData.length; n++){
//        sum+=circularSubtract(circData[n], circData[n-1]);
//        tempret[n] = sum;
//      }
//    }
//    return tempret;
//  }
//  //</editor-fold>
//}
