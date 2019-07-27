public class u0 extends Funktion {
    @Override
    float eval(float x, float y) {
        /*float r = x*x+y*y;
        if (r == 0 ) return 1f;
        return (float) ( Math.sin(r)/(r));*/
        //return 1;
        if(x<3&&x>-1&&y<2&&y>0)
            return 5;
        else return 0;
        //return (float)(Math.sin(x*y));
    }
}
