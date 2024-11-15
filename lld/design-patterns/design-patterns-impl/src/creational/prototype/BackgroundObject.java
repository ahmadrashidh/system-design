package creational.prototype;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Step 2: Create a class to prototype and implement interface (Shallow Copy)
public class BackgroundObject implements GraphicalObject{

    private Double x;
    private Double y;
    private Double z;
    private Double width;
    private Double height;

    private BackgroundType type;

    // don't create a setter to not allow to change pixels
    private List<Double> pixels = new ArrayList<>();

    public BackgroundObject(Double x, Double y, Double width, Double height, BackgroundType type ){
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.type = type;
        this.pixels = this.generatePixels();
    }

    public BackgroundObject(BackgroundObject ref) {
        this.x = ref.x;
        this.y = ref.y;
        this.z = ref.z;
        this.width = ref.width;
        this.height = ref.height;
        this.type = ref.type;
        this.pixels = ref.pixels;
    }

    public BackgroundType getType() {
        return type;
    }

    private List<Double> generatePixels() {
        return Collections.emptyList();
    }

    @Override
    public GraphicalObject cloneObject() {
        return new BackgroundObject(this);
    }
}
