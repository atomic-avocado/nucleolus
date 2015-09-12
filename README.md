#Nucleolus
Nucleolus is a simple Android library forked from Nucleus, which utilizes the [Model-View-Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) pattern to properly connect background tasks with visual parts of an application. Nucleolus is a simplification of Nucleus that provide some improvements in the life cycle of Activities and Fragments. Besides that, the Nucleolus presenter permits you get the context of your current attached view and check if the view from presenter is was detached.

### Include this library:

``` groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

``` groovy
dependencies {
  compile 'com.github.jackmiras:nucleolus:5.0.1'
}
```
![](https://github.com/konmik/konmik.github.io/blob/master/images/mvp_everything_is_connected_with_everything.png)
