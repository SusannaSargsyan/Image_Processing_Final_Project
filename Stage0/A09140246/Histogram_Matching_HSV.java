import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;
import ij.*;

public class Histogram_Matching_HSV implements PlugInFilter {
    public int setup(String args, ImagePlus im) {
        return DOES_RGB;
    }
    public void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();
        Color color;
        float[] hsv  = new float[3];
        float[] h = new float[360];
        float[] s = new float[101];
        float[] v = new float[101];

        int red, green, blue;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) { 
                color = new Color(ip.getPixel(j,i));
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();
                Color.RGBtoHSB (red, green, blue, hsv);
                h[(int) (hsv[0]*360)] += 1;
                s[(int) (hsv[1]*100)] += 1;
                v[(int) (hsv[2]*100)] += 1;
            }
        }

        for (int j = 1; j < h.length; j++) {
            h[j] = h[j - 1] + h[j];
        } 
        for (int j = 1; j < s.length; j++) {
            s[j] = s[j - 1] + s[j];
            v[j] = v[j - 1] + v[j];
        }
        for (int j = 0; j < h.length; j++) {
            h[j] = h[j] / (M * N);
        }
        for (int j = 0; j < s.length; j++) {
            s[j] = s[j] / (M * N);
            v[j] = v[j] / (M * N);
        }
        double[] benchmark_histogram_h = {0.013151709,0.014166667,0.016367521,0.019455127,0.021292735,0.024476497,0.028824786,0.036399573,0.04337607,0.047179487,0.073226497,0.099177353,0.107457265,0.132083327,0.139348283,0.17483975,0.214230776,0.236420944,0.280833334,0.331036329,0.358504266,0.410619646,0.4505983,0.485630333,0.499348283,0.535534203,0.575427353,0.582104683,0.592735052,0.59732908,0.603344023,0.606047034,0.608418822,0.609465837,0.610042751,0.610523522,0.610566258,0.616677344,0.616688013,0.639337599,0.639337599,0.639583349,0.640085459,0.640106857,0.640213668,0.640491426,0.640491426,0.640491426,0.6574893,0.659797013,0.659797013,0.659861088,0.659882486,0.659882486,0.659882486,0.659882486,0.659882486,0.659882486,0.659882486,0.659882486,0.703632474,0.703632474,0.703632474,0.703632474,0.703632474,0.703632474,0.703632474,0.703739345,0.703739345,0.703739345,0.705213666,0.705213666,0.718707263,0.718707263,0.718707263,0.718707263,0.718717933,0.718717933,0.718717933,0.718771338,0.718771338,0.718771338,0.718771338,0.718771338,0.722980797,0.723504245,0.723504245,0.723504245,0.723504245,0.723504245,0.723504245,0.723504245,0.723504245,0.723504245,0.723504245,0.723525643,0.723525643,0.723525643,0.723525643,0.723525643,0.74060899,0.74060899,0.740619659,0.740619659,0.740619659,0.740619659,0.740619659,0.740619659,0.740619659,0.740619659,0.741581202,0.741581202,0.741581202,0.741581202,0.741581202,0.741581202,0.741581202,0.741581202,0.741581202,0.741581202,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.741698742,0.745630324,0.745630324,0.745630324,0.745630324,0.745630324,0.745630324,0.745630324,0.746185899,0.746185899,0.746185899,0.746185899,0.746185899,0.746185899,0.746185899,0.746260703,0.746260703,0.746260703,0.746260703,0.751655996,0.751655996,0.751655996,0.751655996,0.751655996,0.751655996,0.751655996,0.751655996,0.751762807,0.751762807,0.753108978,0.753108978,0.753108978,0.753108978,0.753108978,0.756111085,0.756111085,0.756111085,0.756143153,0.756143153,0.756228626,0.756228626,0.756228626,0.759679496,0.760288477,0.760288477,0.760288477,0.760288477,0.760288477,0.760288477,0.767211556,0.767211556,0.767211556,0.767222226,0.767222226,0.771143138,0.771153867,0.771153867,0.771153867,0.772767067,0.772767067,0.799091876,0.799091876,0.799091876,0.799091876,0.799113274,0.812318385,0.812329054,0.812393188,0.812425196,0.812425196,0.813173056,0.863376081,0.863397419,0.863429487,0.8758654,0.920544863,0.922350407,0.922403872,0.922403872,0.922542751,0.922542751,0.936698735,0.936698735,0.936794877,0.93699789,0.93699789,0.957628191,0.957628191,0.957628191,0.959797025,0.971987188,0.971987188,0.972008526,0.972126067,0.973151684,0.973151684,0.973151684,0.975192308,0.975192308,0.978333354,0.978408098,0.978408098,0.978408098,0.978408098,0.978408098,0.978408098,0.978408098,0.978408098,0.978408098,0.982393146,0.982393146,0.982393146,0.982393146,0.982393146,0.982393146,0.982393146,0.982564092,0.982564092,0.982788444,0.982799172,0.982799172,0.983440161,0.983440161,0.983440161,0.983440161,0.983440161,0.983440161,0.983440161,0.983440161,0.98345083,0.98345083,0.98345083,0.98345083,0.983749986,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98381412,0.98480767,0.98480767,0.98480767,0.984850407,0.984850407,0.984850407,0.984850407,0.984850407,0.984850407,0.984850407,0.984850407,0.984903872,0.984903872,0.98492521,0.98492521,0.98492521,0.98492521,0.98492521,0.98492521,0.98492521,0.98492521,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.984957278,0.985331178,0.985331178,0.985331178,0.985331178,0.985341907,0.985341907,0.985352576,0.985352576,0.985491455,0.985491455,0.985491455,0.985491455,0.985491455,0.985491455,0.985512793,0.98554486,0.98554486,0.98554486,0.990138888,0.990138888,0.990138888,0.990138888,0.990138888,0.990138888,0.990138888,0.990138888,0.990309834,0.990309834,0.99098289,0.990993619,0.990993619,0.990993619,0.990993619,0.992767096,0.992767096,0.992799163,0.992884636,0.992884636,0.993119657,0.99320513,0.993482888,0.995192289,0.996271372,0.996976495,0.997820497,0.99852562,0.99988246,1,};
		double[] benchmark_histogram_s = {0,0,6.41E-05,0.002542735,0.008279915,0.022927351,0.033589743,0.052542735,0.06713675,0.086965814,0.106463678,0.118429489,0.134722218,0.144519225,0.157200858,0.165117517,0.176367521,0.183141023,0.19213675,0.198290601,0.207478628,0.216613248,0.222446576,0.230972216,0.236720085,0.245929495,0.252126068,0.261901706,0.269433767,0.280555546,0.292628199,0.301282048,0.316720098,0.327713668,0.343717963,0.356132478,0.374797016,0.388002127,0.408942312,0.423002124,0.443717957,0.465576917,0.482051283,0.512959421,0.533803403,0.563600421,0.580480754,0.605950832,0.622799158,0.647553444,0.671570539,0.68614316,0.706591904,0.719807684,0.736773491,0.745961547,0.759487152,0.766997874,0.778247893,0.78186965,0.785427332,0.787895322,0.790491462,0.796858966,0.80248934,0.814252138,0.825235069,0.862339735,0.894839764,0.935235023,0.959305584,0.983493567,0.998322666,0.999903858,0.999978662,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		double[] benchmark_histogram_v = {0.007094017,0.034134615,0.055096153,0.074337609,0.185608968,0.258365393,0.294017106,0.300566226,0.305555552,0.310053408,0.316025645,0.322670937,0.32880342,0.336976498,0.343867511,0.351784199,0.358194441,0.362916678,0.3690705,0.371816248,0.380416662,0.386762828,0.393344015,0.400256425,0.403942317,0.415769219,0.424476504,0.43181625,0.439668804,0.447991461,0.455811977,0.464220077,0.473856837,0.484615386,0.495277792,0.506933749,0.5198825,0.533514977,0.548707247,0.564989328,0.583985031,0.602809846,0.620106816,0.63759613,0.657660246,0.678707242,0.698814094,0.720181644,0.74395299,0.761356831,0.792852581,0.816346169,0.842542708,0.865929484,0.889027774,0.910427332,0.928119659,0.943279922,0.95639956,0.966260672,0.975598276,0.982040584,0.98702991,0.990331173,0.992660284,0.994487166,0.995747864,0.996741474,0.997500002,0.998034179,0.99852562,0.998867512,0.999284208,0.999444425,0.999594033,0.999722242,0.999850452,0.999925196,0.999957263,0.999978662,0.999989331,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

        int h_index;
        int s_index;
        int v_index;
        double h_value;
        double s_value;
        double v_value;
        double adj_h_value;
        float[] new_intensities = new float[3];

        for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    double diff_h = 2;
                    double diff_s = 2;
                    double diff_v = 2;
                    color = new Color(ip.getPixel(j,i));
                    red = color.getRed();
                    green = color.getGreen();
                    blue = color.getBlue();
                    
                    Color.RGBtoHSB (red, green, blue, hsv);
                    h_value = h[(int) (hsv[0]*360)];
                    s_value = s[(int) (hsv[1]*100)];
                    v_value = v[(int) (hsv[2]*100)];

                    for (int k = 0; k < 360; k++) {
                        if (Math.abs(benchmark_histogram_h[k] - h_value) < diff_h) {
                            diff_h = Math.abs(benchmark_histogram_h[k] - h_value);
                            new_intensities[0] = (float) k / 360;
                        }
                    }
                    for (int l = 0; l < 101; l++) {
                        if (Math.abs(benchmark_histogram_s[l] - s_value) < diff_s) {
                            diff_s = Math.abs(benchmark_histogram_s[l] - s_value);
                            new_intensities[1] = (float) l / 101;
                        }
                        if (Math.abs(benchmark_histogram_v[l] - v_value) < diff_v) {
                            diff_v = Math.abs(benchmark_histogram_v[l] - v_value);
                            new_intensities[2] = (float) l / 101;
                        }
                    }
                    ip.putPixel(j, i, Color.HSBtoRGB(new_intensities[0], new_intensities[1], new_intensities[2]));
                }
        } 
    }
    
}