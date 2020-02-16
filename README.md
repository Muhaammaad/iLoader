# ILoader


The application shows the usage of ILoader module. It contains an activity with fragment to show fetched images from a url. Images and rest to url is being loaded and cached by ILoader.

**Things which were considered:** 
1. [x] Coding in "Kotlin"
2. [x] Architecture design pattern (MVVM)
3. [x] Dependency Injection (Koin) 
4. [x] Coroutines for tasks
5. [x] LruChache for persistence
6. [x] Data Binding 
7. [x] Networking using OkHttp 
8. [x] Easy to integrate into new Android project
9. [x] Multiple distinct resources may be requested in parallel
10. [x] The same image may be requested by multiple sources simultaneously (even before it has loaded), and if one of the sources cancels the load, it should not affect the remaining requests

**Note:** 
```
* Assumption taken that the same URL will always return the same resource
* On going at the end of the list, Same images are again loading just to show that images and responses are fetched from cache and handled. 
```

**Library Endpoints to be used:**
```
/**
     * Loads data from provided [source] and sets into provided [view]
     *
     * @param source can be url string or [DataRequest]
     * @param view image view in which source should be set
     * @param placeholder default image drawable for provided [view]
     * @param errorPlaceholder default image drawable for provided [view] in case of error
     */
ILoader.load(
        @NotNull source: Any,
        @NotNull view: ImageView,
        @Nullable placeholder: Drawable? = null,
        @Nullable errorPlaceholder: Drawable? = null
    )
```
```
/**
     * Loads data from provided [source], maps it accordingly into provided [mapper] and returns via [delegate]
     *
     * @param source can be url string or [DataRequest]
     * @param mapper maps the source response in the given [T]
     * @param delegate provides the callback data and status about source mapping and fetching
     */
   ILoader.load(
        @NotNull source: Any,
        @NotNull mapper: Mapper<ByteArray, T>,
        @NotNull delegate: CompletionCallback<T?, Boolean>
    )
```
```
/**
     * Loads data from provided [source] and returns via [delegate]
     *
     * @param source can be url string or [DataRequest]
     * @param delegate provides the callback data and status about source mapping and fetching
     */
    ILoader.load(
        @NotNull source: Any,
        @NotNull delegate: CompletionCallback<ByteArray?, Boolean>
    ) 
```
