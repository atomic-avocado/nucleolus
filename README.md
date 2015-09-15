#Nucleolus
Nucleolus is a simple Android library forked from Nucleus, which utilizes the Model View Presenter (See the session after Quick Start) pattern to properly decouple and integrate your tasks with the visual parts of an application. Nucleolus is a simplification of Nucleus and provides some improvements in the life cycle of Activities and Fragments. Besides that, the Nucleolus presenter allows you to get the context of your current attached view and check if the presenter's view was detached.

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
  compile 'com.github.jackmiras:nucleolus:5.0.2'
}
```
###Quick Start
-----------
####Step 1 - Your views should extend NucleolusActivity, NucleolusAppCompatActivity, NucleolusFragment or NucleolusSupportFragment and inform the presenter's type as a parameter.
```java
public class UsersActivity extends NucleolusAppCompatActivity<UsersPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```

####Step 2 - You also need inform that the presenter was passed as a parameter is required by UsersActivity.
```java
@RequiresPresenter(UsersPresenter.class)
public class UsersActivity extends NucleolusAppCompatActivity<UsersPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```

####Step 3 - Finally, we create our presenter, which should extend the Presenter class, passing as a parameter your view class created previously.
```java
public class UsersPresenter extends Presenter<UsersActivity> {
    //TODO Your presente code
}
```
You can see a small sample project here if you want [here](https://github.com/jackmiras/nucleolus/tree/master/app/src/main/java/io/github/jackmiras/nucleolus/sample) 

###Model View Presenter:
This pattern is similar to MVC pattern in which controller has been replaced by the presenter. This design pattern splits an application into three main aspects: Model, View and Presenter.

![](https://d262ilb51hltx0.cloudfront.net/max/894/1*1P4n9JkHChEUVr5umQx4Zw.png)

####Model
The Model represents a set of classes that describes the business logic and data. It also defines business rules for data, which means how data can be changed and manipulated.

####View
The View represents the UI components. It is only responsible for displaying data that is received from the presenter as the result. This also transforms the model(s) into UI.

####Presenter
The Presenter is responsible for handling all UI events on behalf of the view. It receives input from users by the View, then processes the user’s data with the help of Model while passing the results back to the View. Unlike view and controller, view and presenter are completely decoupled from each other and communicate to each between them by an interface.
Also, presenter does not manage the incoming request traffic as controller.


You can see a comparison between other project patterns [here](https://medium.com/android-news/android-architecture-2f12e1c7d4db)

## Pull Requests

I welcome and encourage all pull requests. It usually takes me around 48 hours to respond to any issue or request. Here are some basic rules to follow to ensure timely addition of your request:
  1. Match coding style (braces, spacing, etc.) This is best achieved using CRTL+ALT+L (Reformat code) on Linux and CMD+Option+L on Mac (not sure for Windows) with Android Studio defaults.
  2. If its a feature, bugfix, or anything else, please only change code where strictly necessary.
   **DO NOT** do this: Ex: Title "Fixes Crash Related to Bug" but includes other files that were changed without explanation or that weren't related to the bug you fixed. Another example is a non-descriptive title "Fixes Stuff".
  3. Pull requests must be made against ```develop``` branch.
  4. Have fun!


## Maintainers

[Jack Miras](https://github.com/jackmiras) ([@jackmiras](https://www.twitter.com/@jackmiras))
