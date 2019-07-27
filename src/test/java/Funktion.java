public abstract class Funktion {
    static int n=50;
    static float bord=3;
    abstract float eval(float x, float y);
    //nur über Rechtecke
    static float integrate(Funktion f, float minx, float maxx, float miny, float maxy, int xSegs, int ySegs) {
        float xSegSize = (maxx - minx) / xSegs; // length of an x segment.
        float ySegSize = (maxy - miny) / ySegs; // length of a y segment.
        float volume = 0; // volume under the surface.

        for (int i = 0; i < xSegs; i++) {
            for (int j = 0; j < ySegs; j++) {
                float height = f.eval(minx + (xSegSize * i), miny + (ySegSize * j));
                height += f.eval(minx + (xSegSize * (i + 1)), miny + (ySegSize * j));
                height += f.eval(minx + (xSegSize * (i + 1)), miny + (ySegSize * (j + 1)));
                height += f.eval(minx + (xSegSize * i), miny + (ySegSize * (j + 1)));
                height /= 4;

                // height is the average value of the corners of the current segment.
                // We can use the average value since a box of this height has the same volume as the original segment shape.

                // Add the volume of the box to the volume.
                volume += xSegSize * ySegSize * height;
            }
        }

        return volume;
    }

    static float fold(Funktion f, Funktion g, float x, float y){
        FProdukt fprod=new FProdukt(f,g, x, y);
        return Funktion.integrate(fprod, -bord,bord,-bord,bord, n, n);
    }
}
