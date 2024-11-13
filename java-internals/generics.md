# Generics

```java
List list = new LinkedList();
list.add(new Integer(1)); 
Integer i = list.iterator().next();
```

## Need

- The defined list could carry any datatype, only guaranteeing that it is an `Object`
- There is no contract that the type is an `Integer`
- The compiler doesn't know what it is the datatype in the last line and requires explicit casting.
- This might cause type-related runtime errors.

```java
List<Integer> list= new LinkedList<>(); 
```

- By adding diamond operator `<>` containing the type (known as `Type Parameter`), type is narrowed to Integer type. This add significant robustness and make easier to read.
- Therefore, Generics is also known as `parameteric polymorphism`

## Generic Methods

- Generic methods has `type parameter` before return type.
- Can have multiple type parameters separated by commas in the method signature

```java
public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
    return Arrays.stream(a)
      .map(mapperFunction)
      .collect(Collectors.toList());
}
```
## Bounded Generics

- Type Parameter can be bounded - means restricted
- A method accepts a type and all its subclasses (upper bound) or a type and all its superclasses (lower bound) <br />

To declare an upper-bounded type T upperbounded Number class, 
```java
public <T extends Number> List<T> fromArrayToList(T[] a) {
}
```
- T is whatever class that extends Number
`implements` in case of interface

To declare an lower-bounded type T upperbounded Number class, 
```java
public <T super Number> List<T> fromArrayToList(T[] a) {
}
```
- Type is all parent class of Number

## Wildcards in Generics

- `Object` is the supertype of all Java classes, but collection of Object is not the supertype of any collection. 
- For example, `List<Object>` is not the supertype of `List<String>` and assigning a variable of type `List<Object>` to a variable of `List<String>` will cause a compiler error.

<br />
To define generics that accepts subtype of a class, define it with wildcard(known as `upper-bounded wildcard`)

```java
public static void paintAllBuildings(List<? extends Building> buildings) {}
```

## Type Erasure

- To avoid overhead at runtime, compiler applies process called Type Erasure at compile time.

**What it does**
- Replaces all type parameters with their bounded type or `Object` if its unbounded such that byte code only contains normal classes, interfaces, methods.
- Proper casting added at the compile time.

**What does this solve then**
- Compile-time Type Safety Checks
- Code reusability and flexibility
- Backward Compatibility

## Using the class of generic type at runtime
- can pass the class of generic type

```java
private Class<T> clazz;
```

## Primitives in Generics

- Type parameter cannot be primitive datatype
  - Because generics are compile-time feature wherein types are erased and are replaced with `Object`.
  - Since primitives don't extend Object, it can't be used.


