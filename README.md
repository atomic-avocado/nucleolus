#Nucleolus
Nucleolus is a simple Android library forked from Nucleus, which utilizes the Model View Presenter (See the session after Quick Start) pattern to properly uncouple and integrate your tasks with visual parts of an application. Nucleolus is a simplification of your forked project and provide some improvements in the life cycle of Activities and Fragments. Besides that, the Nucleolus presenter permits you get the context of your current attached view and check if the view from presenter is was detached.

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
###Quick Start
-----------
####Step 1 - Your views should extends NucleolusActivity, NucleolusAppCompatActivity, NucleolusFragment or NucleolusSupportFragment passing the corresponding presenter as parameter.
```java
public class UsersActivity extends NucleolusAppCompatActivity<UsersPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```

####Step 2 - So now you need inform that presenter passed as parameter into Nucleolus views is required to be used on UsersActivity.
```java
@RequiresPresenter(UsersPresenter.class)
public class UsersActivity extends NucleolusAppCompatActivity<UsersPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```

####Step 3 - Lastly we create our presenter that should extend Presenter class passing as parameter your view class create previously.
```java
public class UsersPresenter extends Presenter<UsersActivity> {
    //TODO Your presente code
}
```
If you want can you see a small sample project [here](https://github.com/jackmiras/nucleolus/tree/master/app/src/main/java/io/github/jackmiras/nucleolus/sample) 

###Model View Presenter:
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

## Pull Requests

I welcome and encourage all pull requests. It usually will take me within 24 hours to respond to any issue or request. Here are some basic rules to follow to ensure timely addition of your request:
  1. Match coding style (braces, spacing, etc.) This is best achieved using CRTL+ALT+L (Reformat code) on Linux and CMD+Option+L on Mac (not sure for Windows) with Android Studio defaults.
  2. If its a feature, bugfix, or anything please only change code to what you specify.
   **DO NOT** do this: Ex: Title "Fixes Crash Related to Bug" includes other files that were changed without explanation or doesn't relate to the bug you fixed. Or another example is a non-descriptive title "Fixes Stuff".
  3. Pull requests must be made against ```develop``` branch.
  4. Have fun!


## Maintainers

[Jack Miras](https://github.com/jackmiras) ([@jackmiras](https://www.twitter.com/@jackmiras))
