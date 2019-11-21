### What's for ?
This is the tiny sample code for testing Firebase Dynamiclinks issue, which has more detail description below in 
Issue Description part.

The app only have two activities. ```MainActivity``` is declared ```<action android:name="android.intent.action.MAIN" />``` and
```<category android:name="android.intent.category.LAUNCHER" />``` to simulate the app's entry point such as
"welcome page" or "launch transition". Every launching process should go into ```MainActivity```, which handles incoming intent
by Firebase SDK and pass the target link to ```RealHomePageActivity```. ```RealHomePageActivity``` just get the target link 
and show it on screen.

### Issue Description
https://github.com/firebase/firebase-android-sdk/issues/982

If the app is __NOT__ in histroy and open __NOT__ by dynamic links, anything looks fine.
But if the app is __NOT__ in histroy and open by dynamic link, this link will be failed until clearing histroy,
which "failed" means the open process not through the activity with 
```<action android:name="android.intent.action.MAIN" />``` tag and the data in getIntent() is still the old one.

With this app for example the flow will like:

* User open the app (not in history) by clicking FDL_Reddit which is target to website of Reddit </br>
-> you will see ```https://reddit.com``` on screen </br>
* User leave the app (not destroy it, like click devices home button) </br>
* User click the FDL_Google link </br>
-> you will see ```https://www.google.com``` on screen </br>
* Doing step 2 (leave app) again and click FDL_Reddit </br>
-> you will see app is open not through ```MainActivity``` and still show ```https://www.google.com``` on screen
* Close app and clear the histroy then re-open __NOT__ by dynamiclinks
-> you will see ```https//reddit.com``` on screen, but it should be `null` because I'm not open it by dynamic links

With the same logic, if you first open by FDL_Twitter, then you click FDL_Reddit, FDL_Facebook, FDL_Google, FDL_Others, they
are all re-launch the ```MainActivity``` and work find, just FDL_Twitter will never work again until clearing history.



### Testing FDL Links

FDL_Reddit   : https://bardxhongfdltest.page.link/REDDIT <br/>
FDL_Facebook : https://bardxhongfdltest.page.link/FACEBOOK <br/>
FDL_Google   : https://bardxhongfdltest.page.link/GOOGLE <br/>
FDL_Twitter  : https://bardxhongfdltest.page.link/TWITTER <br/>
