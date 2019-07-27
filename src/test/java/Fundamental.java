public class Fundamental extends Funktion {
    int t=1;//Überprüfe t=0 extra und negative t sind das BÖSE
    @Override
    float eval(float x, float y) {
        return (float)(1/(4*Math.PI*t)*Math.exp(-(x*x+y*y)/(4*t)));
    }
}
