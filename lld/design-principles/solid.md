# SOLID

S - Single Responsibility
O - Open-closed
L - Liskov substitution
I - Interface segregation
D - Dependency Inversion

## Design a bird
> Case study for SOLID principles

| Bird                   |
|------------------------|
| name: String           |
| colour: ColourEnum     |
| weight: Double         |
| type: BirdTypeEnum     |
| size: Size             |
| fly()                  |
| eat()                  |
| sleep()                |

**Implementation**

```java
public class Bird {
    private String name;
    private ColourEnum color;
    private Double weight;
    private BirdTypeEnum type;
    private SizeEnum size;

    public void fly(){
        if(type == BirdTypeEnum.EAGLE){
            System.out.println("Eagle is flying");
        } else if(type == BirdTypeEnum.PARROT){
            System.out.println("Parrot is flying");
        } else if(type == BirdTypeEnum.PEACOCK){
            System.out.println("Peacock is flying");
        }
    }

    public void eat(){
        System.out.println("I'm hungry, Let's eat!")
    }

    public void sleep(){
        System.out.println("I'm sleepy, Let's sleep!")
    }
}

public enum ColorEnum {
    RED,
    BLUE,
    GREEN
}

public enum BirdTypeEnum {
    EAGLE,
    PARROT,
    PEACOCK,
    DOVE,
    SPARROw
}

public enum SizeEnum {
    SMALL,
    MEDIUM,
    BIG
}
```

**Problems of above code:**
1. Not readable - too many things in single method/class
2. Not easy to test as common method contains logics for all Bird types
3. Merge conflicts
4. Not reusable


## Single Responsibility principle (SRP)
> One single code unit (ie class, method, package) should have only one responsibility

**Monster methods & God Classes**

- `fly` method does many things aka *monster methods*
- `Bird` class takes care of implementations of all bird types aka *God class*

**How to find out its monster method or God class**

`Cyclomatic Complexity` - method used by linting libraries to find out Monster methods or God class.


## Open Closed Principle (OCP)
> Open for extension, closed for modification

- `fly` method need to be modified if there is an additional Bird type is being added
- To avoid, use Bird class as abstract for all Bird types as sub classes.
- This also institute SRP


```java
public abstract class Bird {
    private String name;
    private ColourEnum color;
    private Double weight;
    private BirdTypeEnum type;
    private SizeEnum size;

    public abstract void fly();

    public void eat(){
        System.out.println("I'm hungry, Let's eat!")
    }

    public void sleep(){
        System.out.println("I'm sleepy, Let's sleep!")
    }
}


public class Sparrow extends Bird {
    @Override
    public void fly(){
        System.out.println("Sparrow is flying");
    }
}

public class Parrot extends Bird {
    @Override
    public void fly(){
        System.out.println("Parrot is flying");
    }
}
```

### Subtyping Polymorphism
> Replacing child object in terms of its parent

```java
Eagle eagle = new Eagle("Eagle1");

// Subtyping or substitution aka coding to the interface
Bird eagle = new Eagle("Eagle1");
```
## Liskov's Substitution Principle
> When subtyping(substitution) there shouldn't be any special case ie it shouldn't affect the correctness of the program

- if Penguin is subtyped to Bird, method need to be made to throw exception as it doesn't fly
- Behaviours can't be abstracted using Interface

```java
public abstract class Bird {
    private String name;
    private ColourEnum color;
    private Double weight;
    private BirdTypeEnum type;
    private SizeEnum size;

    public void eat(){
        System.out.println("I'm hungry, Let's eat!")
    }

    public void sleep(){
        System.out.println("I'm sleepy, Let's sleep!")
    }
}

public interface Flyable {
    public void fly();
}

public interface Swimmable {
    public void swim();
}

public class Sparrow extends Bird implements Flyable {
    @Override
    public void fly(){
        System.out.println("Sparrow is flying");
    }
}

public class Parrot extends Bird implements Flyable {
    @Override
    public void fly(){
        System.out.println("Parrot is flying");
    }
}

public class Penquin extends Bird implements Swimmable {
    @Override
    public void swim(){
        System.out.println("Parrot is swimming");
    }
}
```


## Interface Segregation Principle
> Always create lean interfaces i.e specific interfaces are better than general-purpose interfaces

- High cohesion, Low coupling

## Dependency Inversion
> High-level modules shouldn't depend on low-level modules




