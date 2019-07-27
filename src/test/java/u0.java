public class u0 extends Funktion {
    @Override
    float eval(float x, float y) {
        float r = x*x+y*y;

        if (r == 0 ) return 1f;
        return (float) ( Math.sin(r)/(r));
    }
}
