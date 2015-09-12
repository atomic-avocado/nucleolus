#Nucleolus
Nucleolus is a simple Android library forked from Nucleus, which utilizes the Model View Presenter (See the session after Include this library) pattern to properly connect background tasks with visual parts of an application. Nucleolus is a simplification of Nucleus that provide some improvements in the life cycle of Activities and Fragments. Besides that, the Nucleolus presenter permits you get the context of your current attached view and check if the view from presenter is was detached.

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
###Model View Presenter
This pattern is similar to MVC pattern in which controller has been replaced by the presenter. This design pattern splits an application into three main aspects: Model, View and Presenter.

![](https://d262ilb51hltx0.cloudfront.net/max/894/1*1P4n9JkHChEUVr5umQx4Zw.png)

####Model
The Model represents a set of classes that describes the business logic and data. It also defines business rules for data means how the data can be changed and manipulated.

####View
The View represents the UI components. It is only responsible for displaying the data that is received from the presenter as the result. This also transforms the model(s) into UI.

####Presenter
The Presenter is responsible for handling all UI events on behalf of the view. This receive input from users via the View, then process the user’s data with the help of Model and passing the results back to the View. Unlike view and controller, view and presenter are completely decoupled from each other’s and communicate to each other’s by an interface.
Also, presenter does not manage the incoming request traffic as controller.


Can you see a comparison of another project patterns [here](https://medium.com/android-news/android-architecture-2f12e1c7d4db)
