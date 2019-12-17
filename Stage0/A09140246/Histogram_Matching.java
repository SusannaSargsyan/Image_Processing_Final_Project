import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;
import ij.*;


public class Histogram_Matching implements PlugInFilter {
    public int setup(String args, ImagePlus im) {
        return DOES_RGB;
    }
    public void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();

        float[] red = new float[256];
        float[] green = new float[256];
        float[] blue = new float[256];

        Color color;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
               color = new Color(ip.getPixel(j,i));
               red[color.getRed()] += 1;
               green[color.getGreen()] += 1;
               blue[color.getBlue()] += 1;
            }
        }
        for (int j = 1; j < red.length; j++) {
            red[j] = red[j - 1] + red[j];
            green[j] = green[j - 1] + green[j];
            blue[j] = blue[j - 1] + blue[j];
        } 
        for (int j = 0; j < red.length; j++) {
            red[j] = red[j] / (M * N);
            green[j] = green[j] / (M * N);
            blue[j] = blue[j] / (M * N);
        }
        double[] benchmark_histogram_red = { 0,0,0,0,0,2.14E-05,1.28E-04,3.42E-04,7.16E-04,0.001976496,0.003814103,0.006410256,0.009423077,0.013824786,0.019113248,0.024294872,0.029668803,0.034679487,0.040470085,0.04633547,0.053568376,0.060566239,0.067617521,0.074380342,0.080844017,0.08715812,0.09340812,0.100224359,0.106613248,0.112521368,0.118482906,0.124412393,0.129679487,0.134775641,0.139861111,0.144615385,0.148707265,0.153087607,0.157264957,0.160929487,0.165224359,0.169049145,0.173012821,0.176538462,0.180074786,0.183311966,0.186228632,0.189380342,0.192350427,0.195331197,0.19849359,0.20159188,0.204711538,0.207681624,0.210982906,0.213985043,0.216933761,0.219754274,0.222809829,0.225480769,0.228482906,0.231367521,0.234252137,0.237083333,0.240235043,0.243279915,0.246346154,0.249711538,0.252606838,0.255865385,0.259145299,0.262382479,0.266014957,0.269967949,0.273450855,0.277104701,0.281175214,0.285373932,0.289401709,0.293354701,0.297403846,0.301912393,0.306431624,0.311282051,0.317403846,0.323183761,0.328311966,0.333472222,0.339081197,0.344358974,0.350502137,0.357019231,0.363173077,0.369305556,0.375641026,0.382211538,0.388846154,0.395737179,0.402660256,0.409893162,0.417297009,0.423942308,0.431014957,0.437905983,0.44482906,0.451976496,0.459262821,0.467115385,0.475384615,0.484155983,0.494561966,0.506485043,0.517596154,0.528653846,0.540032051,0.55017094,0.56,0.569764957,0.57875,0.587863248,0.59625,0.604273504,0.611741453,0.619230769,0.626858974,0.634433761,0.642232906,0.650106838,0.658151709,0.666217949,0.674380342,0.681912393,0.688888889,0.69542735,0.702061966,0.709284188,0.716175214,0.72275641,0.729444444,0.734786325,0.740128205,0.744807692,0.749561966,0.754433761,0.758985043,0.76340812,0.767467949,0.771324786,0.775833333,0.779508547,0.78284188,0.785202991,0.78732906,0.789433761,0.792029915,0.795074786,0.798311966,0.802232906,0.806762821,0.811452991,0.816623932,0.823985043,0.83275641,0.843995726,0.858878205,0.876912393,0.894861111,0.91090812,0.926153846,0.940096154,0.948814103,0.955074786,0.961410256,0.969775641,0.980395299,0.99008547,0.995715812,0.998087607,0.999337607,0.99982906,0.99991453,0.999946581,0.999957265,0.999967949,0.999978632,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, };
	double[] benchmark_histogram_green = { 0,0,0,0,0,0,0,7.48E-05,3.21E-04,0.001292735,0.002873932,0.005886752,0.009273504,0.014294872,0.020480769,0.027478632,0.034358974,0.04224359,0.050940171,0.061891026,0.073194444,0.085491453,0.097222222,0.109102564,0.120138889,0.130630342,0.14,0.149145299,0.157617521,0.166111111,0.174284188,0.182521368,0.190459402,0.198611111,0.205876068,0.212735043,0.220021368,0.22659188,0.232574786,0.237948718,0.243814103,0.249594017,0.254647436,0.260395299,0.266271368,0.271912393,0.278215812,0.284326923,0.290673077,0.297083333,0.303643162,0.309839744,0.315940171,0.322510684,0.329220085,0.336282051,0.343685897,0.352147436,0.360149573,0.368685897,0.376143162,0.383814103,0.391805556,0.398910256,0.405459402,0.411730769,0.418301282,0.424252137,0.430096154,0.436228632,0.442232906,0.448536325,0.454871795,0.460641026,0.46667735,0.472457265,0.478589744,0.484529915,0.491014957,0.497788462,0.504412393,0.511741453,0.519540598,0.527863248,0.535576923,0.54366453,0.551452991,0.558931624,0.56599359,0.573151709,0.579412393,0.584935897,0.59099359,0.597382479,0.604059829,0.61090812,0.617467949,0.624262821,0.630929487,0.637040598,0.643258547,0.649252137,0.656143162,0.663205128,0.670833333,0.677275641,0.683611111,0.689358974,0.695566239,0.702361111,0.709797009,0.717104701,0.723696581,0.73017094,0.735235043,0.740096154,0.74417735,0.747799145,0.750790598,0.753429487,0.756207265,0.759134615,0.761784188,0.763974359,0.765673077,0.767638889,0.768888889,0.76965812,0.770373932,0.771068376,0.771698718,0.772318376,0.77292735,0.773589744,0.774273504,0.774957265,0.775630342,0.776004274,0.776816239,0.777350427,0.778002137,0.778504274,0.779145299,0.779754274,0.780448718,0.781100427,0.781752137,0.782297009,0.782884615,0.783547009,0.784155983,0.78474359,0.785534188,0.786239316,0.787019231,0.787905983,0.788824786,0.790128205,0.791752137,0.793717949,0.796175214,0.798685897,0.801858974,0.805309829,0.809220085,0.813397436,0.818183761,0.823547009,0.830587607,0.84099359,0.855972222,0.875192308,0.897820513,0.920865385,0.940245726,0.951773504,0.960961538,0.970929487,0.983130342,0.993824786,0.998589744,0.999754274,0.999925214,0.999957265,0.999967949,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, };
	double[] benchmark_histogram_blue = { 0,0,0,0,2.14E-05,2.46E-04,8.01E-04,0.001976496,0.004241453,0.007489316,0.011666667,0.017190171,0.024476496,0.032638889,0.041324786,0.051773504,0.062029915,0.07366453,0.08582265,0.098824786,0.112425214,0.124615385,0.136442308,0.147339744,0.158461538,0.170042735,0.182264957,0.194626068,0.205459402,0.216891026,0.227681624,0.238130342,0.247948718,0.257991453,0.268215812,0.277371795,0.287051282,0.297040598,0.306976496,0.31741453,0.327061966,0.336036325,0.34457265,0.353215812,0.362051282,0.371474359,0.380694444,0.389615385,0.398397436,0.407061966,0.415811966,0.423782051,0.432104701,0.440181624,0.447713675,0.455149573,0.462286325,0.470267094,0.478547009,0.486581197,0.494807692,0.503269231,0.511987179,0.520320513,0.528995726,0.537061966,0.545651709,0.553482906,0.560865385,0.567574786,0.573942308,0.579732906,0.585544872,0.592040598,0.598130342,0.603846154,0.60909188,0.614284188,0.618760684,0.623055556,0.627510684,0.631474359,0.63607906,0.640673077,0.646025641,0.651292735,0.657051282,0.66375,0.669401709,0.674935897,0.680405983,0.685876068,0.690801282,0.695117521,0.698365385,0.701239316,0.703418803,0.704700855,0.70599359,0.707029915,0.707660256,0.708397436,0.709049145,0.709722222,0.710502137,0.711495726,0.713012821,0.715384615,0.719508547,0.724690171,0.730790598,0.735876068,0.739700855,0.74349359,0.747222222,0.750694444,0.753237179,0.755651709,0.758440171,0.760865385,0.763012821,0.764754274,0.766314103,0.767361111,0.768376068,0.769113248,0.769882479,0.770299145,0.770705128,0.771185897,0.771709402,0.772318376,0.772884615,0.773450855,0.774102564,0.774732906,0.775224359,0.775844017,0.776367521,0.776955128,0.777446581,0.778023504,0.778611111,0.779059829,0.779594017,0.780160256,0.780566239,0.781239316,0.781762821,0.782350427,0.783151709,0.783621795,0.784273504,0.784935897,0.785544872,0.786260684,0.786976496,0.787638889,0.788333333,0.789369658,0.790801282,0.792286325,0.794326923,0.797061966,0.799636752,0.802681624,0.806463675,0.810705128,0.814626068,0.819722222,0.826111111,0.835181624,0.847435897,0.862970085,0.880534188,0.895042735,0.909145299,0.924326923,0.935235043,0.942275641,0.950021368,0.959305556,0.971217949,0.98349359,0.992094017,0.996068376,0.99832265,0.999508547,0.999903846,0.999946581,0.999978632,0.999978632,0.999978632,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, };

        int red_index;
        int green_index;
        int blue_index;
        double red_value;
        double green_value;
        double blue_value;
        double adj_red_value;
        int[] new_intensities = new int[3];
        for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    double diff_red = 2;
                    double diff_green = 2;
                    double diff_blue = 2;
                    color = new Color(ip.getPixel(j,i));
                    red_index = color.getRed();
                    green_index = color.getGreen();
                    blue_index = color.getBlue();
                    
                    red_value = red[red_index];
                    green_value = green[green_index];
                    blue_value = blue[blue_index];

                    for (int h = 0; h < 256; h++) {
                        if (Math.abs(benchmark_histogram_red[h] - red_value) < diff_red) {
                            diff_red = Math.abs(benchmark_histogram_red[h] - red_value);
                            new_intensities[0] = h;
                        }
                        if (Math.abs(benchmark_histogram_green[h] - green_value) < diff_green) {
                            diff_green = Math.abs(benchmark_histogram_green[h] - green_value);
                            new_intensities[1] = h;
                        }
                        if (Math.abs(benchmark_histogram_blue[h] - blue_value) < diff_blue) {
                            diff_blue = Math.abs(benchmark_histogram_blue[h] - blue_value);
                            new_intensities[2] = h;
                        }
                    }
                    ip.putPixel(j, i, new_intensities);
                }
        } 
    }
    
}