public class FProdukt extends Funktion {
    Funktion f, g;
    float xf,yf;

    public FProdukt(Funktion f, Funktion g, float xf, float yf) {
        this.f = f;
        this.g = g;
        this.xf = xf;
        this.yf = yf;
    }

    float eval(float x, float y){
        return f.eval(xf-x,yf-y)*g.eval(x,y);
    }
}
