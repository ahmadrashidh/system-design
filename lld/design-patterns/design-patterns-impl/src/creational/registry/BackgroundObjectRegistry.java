package creational.registry;

import creational.prototype.BackgroundObject;
import creational.prototype.BackgroundType;

import java.util.HashMap;
import java.util.Map;

public class BackgroundObjectRegistry {

    Map<BackgroundType, BackgroundObject> registry = new HashMap<>();
    public void addPrototype(BackgroundObject bgObj){
        registry.put(bgObj.getType(), bgObj);
    }

    public void removePrototype(BackgroundType type){
        registry.remove(type);
    }

    public BackgroundObject getPrototype(BackgroundType type){
        return registry.get(type);
    }
}
