# Map
> a collection that maps keys to values

- Map cannot contain duplicate keys
- Three general-purpose Map implementations - `HashMap`,`TreeMap`,`LinkedHashMap`
- All general-purpose Map implementations contains constructor with generic Map as input

## Operations

**Basic** - `put`,`get`,`containsKey`,`containsValue`,`size` and `isEmpty`
**Bulk** - `putAll`, `removeAll`, `retainAll`, `containsAll`
**CollectionViews** - `keySet`, `valueSet`, `entrySet`


## Multimaps
> Map but it can map each key to multiple values

- Uses `List` instance for implementing multimap
