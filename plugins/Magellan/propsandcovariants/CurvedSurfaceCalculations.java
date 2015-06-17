///////////////////////////////////////////////////////////////////////////////
// AUTHOR:       Henry Pinkard, henry.pinkard@gmail.com
//
// COPYRIGHT:    University of California, San Francisco, 2015
//
// LICENSE:      This file is distributed under the BSD license.
//               License text is included with the source distribution.
//
//               This file is distributed in the hope that it will be useful,
//               but WITHOUT ANY WARRANTY; without even the implied warranty
//               of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//
//               IN NO EVENT SHALL THE COPYRIGHT OWNER OR
//               CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
//               INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.
//
package propsandcovariants;

import java.util.ArrayList;
import java.util.Arrays;
import misc.Log;

/**
 *
 * wrapper class that holds precalculated values of relative power based on
 * vertical distance, surface normal value, and radius of curvature
 */
public class CurvedSurfaceCalculations {

   //mean free paths for which vals have been calculated
   private static Integer[] MEAN_FREE_PATHS = new Integer[]{25, 30, 70, 80, 90, 200};
   private static Integer[] RADII_OF_CURVATURE = new Integer[]{400, 600, 800};
   //ponts at which relative power has been calculated
   private static int DISTANCE_INCREMENT = 20;
   private static int NORMAL_INCREMENT = 10;
   //[radius of curvature][mean free path][normal][vertical distance]
   private static final double[][][][] RELATIVE_POWERS = new double[][][][]{
      //radius = 400
      {
         //MFP 25
         null,
         //MFP 30 
         null,
         //MFP 70
         null,
         //MFP 80
         null,
         //MFP 90 
         null,
         //MFP 200
         null
      },
      //radius = 600
      {
         //MFP 25
         {{1.000000, 2.174786, 3.541065, 4.954333, 6.362246, 7.747367, 9.103893, 10.430151, 11.726072, 12.992232, 14.229470, 15.438688, 16.620785, 17.776623, 18.906996, 20.012631, 21.094191, 22.152270, 23.187400, 24.200058, 25.190663, 25.190663},
            {1.000000, 2.175716, 3.627874, 5.167263, 6.704800, 8.209957, 9.674738, 11.098561, 12.483040, 13.830283, 15.142370, 16.421179, 17.668389, 18.885474, 20.073725, 21.234277, 22.368133, 23.476172, 24.559164, 25.617797, 26.652667, 26.652667},
            {1.000000, 2.201904, 3.737349, 5.399660, 7.067488, 8.693679, 10.265752, 11.783590, 13.250619, 14.670816, 16.047848, 17.384860, 18.684529, 19.949115, 21.180535, 22.380430, 23.550205, 24.691071, 25.804077, 26.890130, 27.950014, 27.950014},
            {1.001226, 2.265090, 3.871861, 5.640161, 7.427717, 9.168857, 10.842880, 12.448023, 13.988782, 15.470949, 16.899968, 18.280520, 19.616493, 20.911127, 22.167106, 23.386671, 24.571710, 25.723805, 26.844300, 27.934335, 28.994815, 28.994815},
            {1.045289, 2.364991, 4.007222, 5.837545, 7.709689, 9.541586, 11.300083, 12.977555, 14.576981, 16.104715, 17.567486, 18.971368, 20.321540, 21.622271, 22.877104, 24.088944, 25.260164, 26.392721, 27.488166, 28.547861, 29.572795, 29.572795},
            {1.137428, 2.472289, 4.077402, 5.880016, 7.753117, 9.608894, 11.400912, 13.110555, 14.734211, 16.275092, 17.738847, 19.131554, 20.458787, 21.725375, 22.935278, 24.091735, 25.197238, 26.253767, 27.262697, 28.224776, 29.140406, 29.140406},
            {1.264403, 2.537961, 3.987980, 5.608103, 7.312692, 9.029158, 10.708146, 12.320924, 13.853230, 15.299594, 16.659260, 17.933608, 19.124592, 20.233936, 21.262453, 22.209840, 23.074280, 23.852082, 24.537142, 25.120133, 25.587203, 25.587203},
            {1.419380, 2.511839, 3.644292, 4.856682, 6.105746, 7.347461, 8.544639, 9.667713, 10.693122, 11.600893, 12.372075, 12.986218, 13.418782, 13.638386, 13.603617, 13.259714, 12.536111, 11.348395, 9.613354, 7.292032, 4.447669, 4.447669},
            {1.606216, 2.333508, 2.946511, 3.487644, 3.929590, 4.239715, 4.384894, 4.332988, 4.054204, 3.521579, 2.690510, 2.559137, 2.995353, 3.142031, 3.063054, 2.866097, 2.637940, 2.423681, 2.239480, 2.087547, 1.965129, 1.965129},
            {1.839451, 1.951261, 2.158980, 2.424089, 2.695474, 2.910300, 3.017094, 3.001328, 2.887038, 2.717558, 2.531728, 2.353895, 2.195489, 2.059955, 1.946914, 1.854750, 1.781952, 1.727872, 1.694872, 1.682941, 1.671122, 1.671122},},
         //MFP 30
         {{1.000000, 1.956162, 3.062142, 4.216545, 5.374376, 6.517946, 7.640519, 8.739681, 9.814802, 10.866015, 11.893783, 12.898704, 13.881424, 14.842566, 15.782737, 16.702493, 17.602353, 18.482784, 19.344208, 20.187003, 21.011502, 21.011502},
            {1.000000, 1.950023, 3.110374, 4.357411, 5.619177, 6.863912, 8.080023, 9.264354, 10.416970, 11.539036, 12.632013, 13.697350, 14.736393, 15.750337, 16.740252, 17.707082, 18.651661, 19.574721, 20.476908, 21.358791, 22.220873, 22.220873},
            {1.000000, 1.966496, 3.180014, 4.513878, 5.876455, 7.221045, 8.529173, 9.795817, 11.021378, 12.208090, 13.358577, 14.475337, 15.560593, 16.616258, 17.643980, 18.645173, 19.621051, 20.572659, 21.500896, 22.406548, 23.290287, 23.290287},
            {1.001054, 2.016391, 3.275710, 4.679902, 6.128979, 7.563386, 8.956008, 10.297985, 11.588848, 12.831348, 14.029123, 15.185747, 16.304413, 17.387873, 18.438446, 19.458099, 20.448486, 21.411004, 22.346834, 23.256977, 24.142277, 24.142277},
            {1.041797, 2.102914, 3.382635, 4.820073, 6.319741, 7.815045, 9.270050, 10.669672, 12.010239, 13.293391, 14.522800, 15.702598, 16.836704, 17.928586, 18.981219, 19.997085, 20.978272, 21.926504, 22.843196, 23.729500, 24.586329, 24.586329},
            {1.129420, 2.205711, 3.453736, 4.854500, 6.330995, 7.820226, 9.281346, 10.692252, 12.043354, 13.332318, 14.560496, 15.730846, 16.846783, 17.911619, 18.928334, 19.899431, 20.826964, 21.712479, 22.557123, 23.361591, 24.126163, 24.126163},
            {1.252754, 2.285866, 3.416732, 4.667086, 5.988107, 7.332982, 8.665574, 9.961297, 11.205035, 12.388376, 13.507174, 14.559705, 15.545389, 16.463942, 17.314786, 18.096620, 18.807017, 19.442089, 19.995798, 20.459449, 20.820267, 20.820267},
            {1.404789, 2.299721, 3.193515, 4.133527, 5.095156, 6.050359, 6.973618, 7.843070, 8.640009, 9.347708, 9.949977, 10.429617, 10.766757, 10.937082, 10.909909, 10.646308, 10.097939, 9.208464, 7.921477, 6.200134, 4.038882, 4.038882},
            {1.588230, 2.193760, 2.692854, 3.128359, 3.483098, 3.734408, 3.858244, 3.830203, 3.625858, 3.219281, 2.561777, 2.446069, 2.812881, 2.964467, 2.930784, 2.782121, 2.588508, 2.394500, 2.220900, 2.074248, 1.954539, 1.954539},
            {1.816562, 1.913781, 2.087211, 2.307682, 2.537495, 2.730143, 2.843585, 2.858217, 2.783470, 2.649416, 2.489111, 2.327180, 2.177752, 2.047021, 1.936574, 1.845929, 1.774132, 1.720760, 1.688237, 1.676589, 1.665034, 1.665034},},
         //MFP 70
         {{1.000000, 1.376873, 1.787457, 2.219775, 2.664243, 3.113765, 3.563373, 4.009741, 4.450724, 4.884995, 5.311774, 5.730638, 6.141391, 6.543978, 6.938428, 7.324820, 7.703258, 8.073856, 8.436726, 8.791980, 9.139718, 9.139718},
            {1.000000, 1.366904, 1.776624, 2.217871, 2.679961, 3.153834, 3.632400, 4.110451, 4.584352, 5.051699, 5.510982, 5.961319, 6.402260, 6.833629, 7.255429, 7.667769, 8.070818, 8.464769, 8.849827, 9.226195, 9.594062, 9.594062},
            {1.000000, 1.368372, 1.780993, 2.230077, 2.705795, 3.198646, 3.700422, 4.204564, 4.706155, 5.201708, 5.688889, 6.166235, 6.632915, 7.088537, 7.532999, 7.966382, 8.388879, 8.800747, 9.202262, 9.593710, 9.975363, 9.975363},
            {1.000495, 1.387236, 1.805090, 2.259329, 2.742529, 3.245987, 3.761438, 4.281768, 4.801278, 5.315658, 5.821832, 6.317726, 6.802044, 7.274062, 7.733458, 8.180185, 8.614372, 9.036252, 9.446113, 9.844264, 10.231013, 10.231013},
            {1.025821, 1.431679, 1.851194, 2.303090, 2.782668, 3.282749, 3.795902, 4.315332, 4.835275, 5.351114, 5.859337, 6.357398, 6.843548, 7.316660, 7.776069, 8.221451, 8.652705, 9.069882, 9.473117, 9.862585, 10.238471, 10.238471},
            {1.088091, 1.501804, 1.912475, 2.347475, 2.804613, 3.278593, 3.763482, 4.253577, 4.743807, 5.229885, 5.708336, 6.176431, 6.632089, 7.073754, 7.500280, 7.910818, 8.304723, 8.681472, 9.040588, 9.381589, 9.703920, 9.703920},
            {1.184674, 1.588519, 1.970675, 2.364244, 2.769140, 3.181880, 3.598144, 4.013558, 4.424010, 4.825772, 5.215522, 5.590305, 5.947449, 6.284480, 6.598996, 6.888550, 7.150512, 7.381913, 7.579256, 7.738285, 7.853696, 7.853696},
            {1.313062, 1.674683, 1.995293, 2.309214, 2.617387, 2.917213, 3.205030, 3.476769, 3.728154, 3.954723, 4.151768, 4.314204, 4.436391, 4.511906, 4.533253, 4.491499, 4.375789, 4.172616, 3.864419, 3.425443, 2.799080, 2.799080},
            {1.472853, 1.733097, 1.937007, 2.110751, 2.253736, 2.361859, 2.429346, 2.448914, 2.411098, 2.301763, 2.088925, 2.034546, 2.180012, 2.274927, 2.313898, 2.298900, 2.240800, 2.154909, 2.056355, 1.957258, 1.865950, 1.865950},
            {1.670585, 1.726493, 1.805491, 1.900235, 2.001403, 2.097725, 2.177045, 2.228448, 2.244833, 2.225295, 2.174909, 2.102574, 2.018325, 1.931215, 1.848253, 1.774318, 1.712601, 1.665424, 1.636653, 1.627149, 1.617591, 1.617591},},
         //MFP 80
         {{1.000000, 1.326725, 1.678791, 2.048126, 2.427964, 2.812991, 3.199231, 3.583826, 3.964790, 4.340786, 4.710946, 5.074736, 5.431844, 5.782113, 6.125488, 6.461975, 6.791619, 7.114489, 7.430664, 7.740223, 8.043247, 8.043247},
            {1.000000, 1.317517, 1.667019, 2.041051, 2.432355, 2.834496, 3.242142, 3.651106, 4.058246, 4.461297, 4.858694, 5.249398, 5.632764, 6.008421, 6.376197, 6.736046, 7.088004, 7.432166, 7.768651, 8.097594, 8.419130, 8.419130},
            {1.000000, 1.318424, 1.669108, 2.047515, 2.447308, 2.862053, 3.285891, 3.713841, 4.141901, 4.566994, 4.986862, 5.399916, 5.805098, 6.201746, 6.589496, 6.968187, 7.337803, 7.698421, 8.050173, 8.393224, 8.727754, 8.727754},
            {1.000437, 1.334778, 1.689120, 2.070305, 2.474061, 2.894792, 3.326853, 3.765097, 4.205121, 4.643347, 5.076998, 5.504005, 5.922897, 6.332681, 6.732734, 7.122710, 7.502464, 7.871981, 8.231345, 8.580692, 8.920189, 8.920189},
            {1.023553, 1.374573, 1.729962, 2.108364, 2.507681, 2.923517, 3.350969, 3.785286, 4.222200, 4.658073, 5.089931, 5.515438, 5.932820, 6.340785, 6.738428, 7.125145, 7.500561, 7.864464, 8.216757, 8.557414, 8.886449, 8.886449},
            {1.081539, 1.439157, 1.787239, 2.151637, 2.532067, 2.925419, 3.327829, 3.735370, 4.144368, 4.551554, 4.954123, 5.349740, 5.736495, 6.112854, 6.477594, 6.829728, 7.168450, 7.493070, 7.802964, 8.097514, 8.376069, 8.376069},
            {1.172665, 1.521906, 1.846924, 2.178038, 2.516354, 2.859879, 3.205744, 3.550868, 3.892224, 4.226957, 4.552420, 4.866171, 5.165931, 5.449532, 5.714838, 5.959665, 6.181681, 6.378286, 6.546474, 6.682657, 6.782445, 6.782445},
            {1.295395, 1.609032, 1.883899, 2.150814, 2.411406, 2.664091, 2.906269, 3.134897, 3.346686, 3.538135, 3.705497, 3.844691, 3.951174, 4.019763, 4.044403, 4.017856, 3.931253, 3.773376, 3.529203, 3.175776, 2.661272, 2.661272},
            {1.449588, 1.677141, 1.855184, 2.006932, 2.132405, 2.228492, 2.290552, 2.312542, 2.286382, 2.199691, 2.024152, 1.977933, 2.102376, 2.186742, 2.225758, 2.219350, 2.174593, 2.102999, 2.017089, 1.927821, 1.843439, 1.843439},
            {1.640895, 1.692753, 1.763331, 1.846784, 1.935653, 2.020928, 2.092799, 2.142138, 2.162419, 2.151746, 2.113109, 2.053106, 1.979999, 1.901890, 1.825576, 1.756187, 1.697375, 1.651944, 1.624132, 1.615130, 1.606042, 1.606042},},
         //MFP 90
         {{1.000000, 1.288286, 1.596022, 1.917581, 2.248076, 2.583494, 2.920679, 3.257234, 3.591393, 3.921892, 4.247849, 4.568671, 4.883972, 5.193519, 5.497184, 5.794910, 6.086690, 6.372547, 6.652524, 6.926671, 7.195045, 7.195045},
            {1.000000, 1.279782, 1.584051, 1.907682, 2.245574, 2.593059, 2.946107, 3.301400, 3.656308, 4.008818, 4.357439, 4.701103, 5.039073, 5.370861, 5.696170, 6.014837, 6.326795, 6.632045, 6.930628, 7.222614, 7.508086, 7.508086},
            {1.000000, 1.280337, 1.584774, 1.910662, 2.253768, 2.609614, 2.973957, 3.343030, 3.713645, 4.083215, 4.449714, 4.811607, 5.167775, 5.517434, 5.860062, 6.195336, 6.523085, 6.843241, 7.155813, 7.460860, 7.758471, 7.758471},
            {1.000391, 1.294774, 1.601857, 1.929096, 2.274045, 2.633006, 3.002048, 3.377421, 3.755768, 4.134216, 4.510401, 4.882442, 5.248895, 5.608689, 5.961059, 6.305486, 6.641641, 6.969340, 7.288502, 7.599123, 7.901241, 7.901241},
            {1.021647, 1.330817, 1.638549, 1.962861, 2.303118, 2.656584, 3.019945, 3.389837, 3.763099, 4.136910, 4.508848, 4.876899, 5.239436, 5.595183, 5.943163, 6.282648, 6.613109, 6.934173, 7.245584, 7.547168, 7.838803, 7.838803},
            {1.075882, 1.390636, 1.692120, 2.004540, 2.328664, 2.662676, 3.004003, 3.349880, 3.697611, 4.044701, 4.388919, 4.728320, 5.061241, 5.386275, 5.702237, 6.008127, 6.303086, 6.586354, 6.857228, 7.115027, 7.359046, 7.359046},
            {1.162046, 1.469396, 1.751611, 2.036510, 2.325840, 2.618509, 2.912570, 3.205795, 3.495907, 3.780685, 4.058003, 4.325844, 4.582275, 4.825420, 5.053404, 5.264299, 5.456048, 5.626376, 5.772683, 5.891910, 5.980358, 5.980358},
            {1.279375, 1.555930, 1.796141, 2.027862, 2.253082, 2.470869, 2.679327, 2.876122, 3.058658, 3.224121, 3.369461, 3.491328, 3.585975, 3.649112, 3.675717, 3.659760, 3.593797, 3.468281, 3.270145, 2.978849, 2.547214, 2.547214},
            {1.428077, 1.630085, 1.788114, 1.922932, 2.034926, 2.121670, 2.179338, 2.202806, 2.185071, 2.115186, 1.967916, 1.928465, 2.036838, 2.112551, 2.150651, 2.149975, 2.115150, 2.054921, 1.979648, 1.899094, 1.821163, 1.821163},
            {1.613192, 1.661777, 1.725845, 1.800619, 1.879974, 1.956519, 2.022148, 2.069124, 2.091543, 2.087048, 2.057277, 2.007141, 1.943421, 1.873277, 1.803122, 1.738129, 1.682230, 1.638585, 1.611745, 1.603232, 1.594603, 1.594603},},
         //MFP 200
         null
      },
      //radius = 800
      {
         //MFP 25
         null,
         //MFP 30
         null,
         //MFP 70
         null,
         //MFP 80
         null,
         //MFP 90
         null,
         //MFP 200
         {{1.000000, 1.126053, 1.255071, 1.386674, 1.520457, 1.656015, 1.792959, 1.930919, 2.069548, 2.208531, 2.347578, 2.486430, 2.624856, 2.762649, 2.899630, 3.035639, 3.170540, 3.304214, 3.436560, 3.567491, 3.696933, 3.824825, 3.951115, 4.075759, 4.198723, 4.319976, 4.439495, 4.557260, 4.673255, 4.787468, 4.899887, 5.010505, 5.119313, 5.226306, 5.331477, 5.434820, 5.536330, 5.636001, 5.733825, 5.829795, 5.923904, 5.923904},
            {1.000000, 1.121669, 1.247169, 1.376180, 1.508323, 1.643193, 1.780379, 1.919475, 2.060089, 2.201847, 2.344401, 2.487424, 2.630618, 2.773710, 2.916453, 3.058624, 3.200028, 3.340489, 3.479852, 3.617984, 3.754767, 3.890100, 4.023896, 4.156081, 4.286593, 4.415378, 4.542391, 4.667597, 4.790964, 4.912466, 5.032084, 5.149800, 5.265600, 5.379473, 5.491408, 5.601397, 5.709433, 5.815508, 5.919616, 6.021748, 6.121898, 6.121898},
            {1.000000, 1.121797, 1.246407, 1.374317, 1.505469, 1.639630, 1.776486, 1.915692, 2.056885, 2.199703, 2.343792, 2.488810, 2.634432, 2.780355, 2.926296, 3.071994, 3.217212, 3.361732, 3.505361, 3.647923, 3.789265, 3.929248, 4.067752, 4.204672, 4.339915, 4.473401, 4.605062, 4.734839, 4.862681, 4.988545, 5.112393, 5.234195, 5.353924, 5.471554, 5.587066, 5.700442, 5.811664, 5.920716, 6.027584, 6.132250, 6.234699, 6.234699},
            {1.000240, 1.129614, 1.255469, 1.383179, 1.513477, 1.646467, 1.782022, 1.919909, 2.059846, 2.201522, 2.344617, 2.488810, 2.633786, 2.779241, 2.924887, 3.070449, 3.215673, 3.360322, 3.504178, 3.647041, 3.788730, 3.929079, 4.067940, 4.205180, 4.340680, 4.474331, 4.606040, 4.735721, 4.863297, 4.988700, 5.111869, 5.232748, 5.351284, 5.467429, 5.581138, 5.692367, 5.801072, 5.907209, 6.010733, 6.111596, 6.209749, 6.209749},
            {1.014549, 1.152078, 1.278844, 1.405596, 1.533865, 1.664081, 1.796301, 1.930411, 2.066212, 2.203457, 2.341876, 2.481184, 2.621095, 2.761325, 2.901597, 3.041644, 3.181213, 3.320060, 3.457959, 3.594696, 3.730071, 3.863899, 3.996005, 4.126227, 4.254416, 4.380430, 4.504135, 4.625406, 4.744123, 4.860169, 4.973431, 5.083796, 5.191150, 5.295377, 5.396357, 5.493961, 5.588054, 5.678487, 5.765097, 5.847702, 5.926098, 5.926098},
            {1.053485, 1.193709, 1.318752, 1.441976, 1.565386, 1.689625, 1.814871, 1.941096, 2.068166, 2.195882, 2.324014, 2.452310, 2.580507, 2.708339, 2.835541, 2.961848, 3.087002, 3.210750, 3.332845, 3.453044, 3.571111, 3.686815, 3.799927, 3.910218, 4.017459, 4.121420, 4.221862, 4.318540, 4.411194, 4.499550, 4.583309, 4.662147, 4.735706, 4.803583, 4.865325, 4.920410, 4.968236, 5.008099, 5.039168, 5.060448, 5.070735, 5.070735},
            {1.117843, 1.254780, 1.373555, 1.488589, 1.602105, 1.714841, 1.827033, 1.938684, 2.049677, 2.159823, 2.268892, 2.376620, 2.482728, 2.586917, 2.688880, 2.788296, 2.884832, 2.978146, 3.067879, 3.153654, 3.235075, 3.311716, 3.383122, 3.448795, 3.508186, 3.560689, 3.605614, 3.642183, 3.669496, 3.686503, 3.691967, 3.684404, 3.662009, 3.622544, 3.563171, 3.480179, 3.368503, 3.220762, 3.024945, 2.756552, 2.292762, 2.292762},
            {1.208915, 1.332958, 1.437445, 1.536059, 1.630922, 1.722711, 1.811595, 1.897505, 1.980240, 2.059508, 2.134955, 2.206172, 2.272701, 2.334031, 2.389596, 2.438766, 2.480834, 2.515001, 2.540353, 2.555827, 2.560168, 2.551857, 2.529003, 2.489155, 2.428943, 2.343257, 2.222875, 2.042219, 1.972692, 2.006406, 2.012062, 1.995639, 1.962549, 1.918134, 1.867426, 1.814896, 1.764312, 1.718826, 1.681284, 1.656083, 1.644940, 1.644940},
            {1.328509, 1.421851, 1.496882, 1.563513, 1.623121, 1.675906, 1.721593, 1.759623, 1.789193, 1.809236, 1.818325, 1.814460, 1.794520, 1.752370, 1.680375, 1.731156, 1.774923, 1.811097, 1.838344, 1.855501, 1.862010, 1.857936, 1.844031, 1.821621, 1.792468, 1.758600, 1.722133, 1.685149, 1.649607, 1.617336, 1.590152, 1.570474, 1.563019, 1.557220, 1.551328, 1.545349, 1.539287, 1.533150, 1.526938, 1.520659, 1.514314, 1.514314},
            {1.480502, 1.507088, 1.537568, 1.571191, 1.607012, 1.643904, 1.680568, 1.715567, 1.747389, 1.774493, 1.795518, 1.809264, 1.815030, 1.812567, 1.802128, 1.784446, 1.760662, 1.732208, 1.700696, 1.667803, 1.635182, 1.604442, 1.577082, 1.554800, 1.540454, 1.536065, 1.531552, 1.526869, 1.522028, 1.517040, 1.511916, 1.506668, 1.501300, 1.495823, 1.490243, 1.484566, 1.478799, 1.472944, 1.467008, 1.460996, 1.454909, 1.454909}
         }
      }
   };

   public static double getRelativePower(int meanFreePath, double vertDistance, double normal, int radiusOfCurvature) {
      int mfpIndex = Arrays.asList(MEAN_FREE_PATHS).indexOf(meanFreePath);
      int radiusIndex = Arrays.asList(RADII_OF_CURVATURE).indexOf(radiusOfCurvature);
      if (mfpIndex == -1) {
         Log.log("Couldn't find mean free path in precalculated values");
         throw new RuntimeException();
      }
      if (radiusIndex == -1) {
         Log.log("Couldn't find radius of curvature in precalculated values");
         throw new RuntimeException();
      }
      double indexedDistance = vertDistance / DISTANCE_INCREMENT;
      int normalIndex = (int) Math.round(normal / NORMAL_INCREMENT); //this one should never exceed 90 degrees so we can round
      double[] distanceVec = RELATIVE_POWERS[radiusIndex][mfpIndex][normalIndex];
      if (indexedDistance > distanceVec.length - 1) {
         return distanceVec[distanceVec.length - 1];
      } else {
         double weight = indexedDistance % 1;
         return (1 - weight) * distanceVec[(int) Math.floor(indexedDistance)] + weight * distanceVec[(int) Math.ceil(indexedDistance)];
      }
   }

   public static String[] getAvailableMeanFreePathLengths(int radius) {
      ArrayList<String> mfps = new ArrayList<String>();
      for (int radiusIndex = 0; radiusIndex < RADII_OF_CURVATURE.length; radiusIndex++) {
         if (radius == RADII_OF_CURVATURE[radiusIndex]) {
            for (int mfpIndex = 0; mfpIndex < MEAN_FREE_PATHS.length; mfpIndex++) {
               if (RELATIVE_POWERS[radiusIndex][mfpIndex] != null) {
                  mfps.add(MEAN_FREE_PATHS[mfpIndex] + "");
               }
            }
            String[] arr = new String[mfps.size()];
            return mfps.toArray(arr);
         }
      }
      throw new RuntimeException("Radius of curvature not found");
   }

   public static String[] getAvailableRadiiOfCurvature() {
      String[] vals = new String[RADII_OF_CURVATURE.length];
      for (int i = 0; i < vals.length; i++) {
         vals[i] = RADII_OF_CURVATURE[i] + "";
      }
      return vals;
   }
}
