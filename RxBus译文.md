[RxBus](https://github.com/AndroidKnife/RxBus) - An event bus by [ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)/[ReactiveX/RxAndroid](https://github.com/ReactiveX/RxAndroid)
=============================
这是一个事件总线设计，让您的应用程序有效地进行通信。

我已经在许多项目中使用它，现在我想也许有人会喜欢它，所以我发布它。
RxBus支持注解（@produce/@subscribe），他可以提供你在其它线程上的produce/subscribe 例如 MAIN_THREAD, NEW_THREAD, IO, COMPUTATION, TRAMPOLINE, IMMEDIATE, even the EXECUTOR and HANDLER thread，more in [EventThread](rxbus/src/main/java/com/hwangjr/rxbus/thread/EventThread.java)

RxBus提供了event的tag。方法的第一个参数和tag共同定义了event的类型。

**Thanks to:**

[square/otto](https://github.com/square/otto)

[greenrobot/EventBus](https://github.com/greenrobot/EventBus)

Usage
--------

Just 2 Steps:

**STEP 1**

Add dependency to your gradle file:
```groovy
compile 'com.hwangjr.rxbus:rxbus:1.0.4'
```
Or maven:
``` xml
<dependency>
  <groupId>com.hwangjr.rxbus</groupId>
  <artifactId>rxbus</artifactId>
  <version>1.0.4</version>
  <type>aar</type>
</dependency>
```

**TIP:** Maybe you also use the [JakeWharton/timber](https://github.com/JakeWharton/timber) to log your message, you may need to exclude the timber (from version 1.0.4, timber dependency update from [AndroidKnife/Utils/timber](https://github.com/AndroidKnife/Utils/tree/master/timber) to JakeWharton):
``` groovy
compile ('com.hwangjr.rxbus:rxbus:1.0.4') {
    exclude group: 'com.jakewharton.timber', module: 'timber'
}
```
en
Snapshots of the development version are available in [Sonatype's `snapshots` repository](https://oss.sonatype.org/content/repositories/snapshots/).

**STEP 2**

Just use the provided(Any Thread Enforce):
``` java
com.hwangjr.rxbus.RxBus
```
Or make RxBus instance is a better choice:
``` java
public static final class RxBus {
    private static Bus sBus;
    
    public static synchronized get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }
}
```

Add the code where you want to produce/subscribe events, and register and unregister the class.
``` java
public class MainActivity extends AppCompatActivity {
    ...
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        //注册
        RxBus.get().register(this);
        ...
    }
    
    @Override
    protected void onDestroy() {
        ...
        //注销
        RxBus.get().unregister(this);
        ...
    }
        
    @Subscribe
    public void eat(String food) {
        // purpose
    }
        
    @Subscribe(
        thread = EventThread.IO,
        tags = {
            @Tag(BusAction.EAT_MORE)
        }
    )
    public void eatMore(List<String> foods) {
        // purpose
    }
    
    @Produce
    public String produceFood() {
        return "This is bread!";
    }
    
    @Produce(
        thread = EventThread.IO,
        tags = {
            @Tag(BusAction.EAT_MORE)
        }
    )
    public List<String> produceMoreFood() {
        return Arrays.asList("This is breads!");
    }
    
    ...
}
```

**That is all done!**
##### tags可以定义多个 @Produce定义的方法会发送事件，事件类型为方法的Tags和return返回的类型共同决定
	@Produce(
        thread = EventThread.IO,
        tags = {
            @Tag,@Tag(BusAction.EAT_MORE)
        }
    )
```
##### tags可以定义多个 @Subscribe定义的方法会接受事件，事件类型为方法的Tags和方法的参数共同决定
	@Subscribe(
        thread = EventThread.IO,
        tags = {
            @Tag(BusAction.EAT_MORE)
        }
    )
    public void eatMore(List<String> foods) {
        // purpose
    }
Lint
--------

Features
--------
* JUnit test
* Docs

History
--------
Here is the [CHANGELOG](CHANGELOG.md).

FAQ
--------
**Q:** How to do pull requests?<br/>
**A:** Ensure good code quality and consistent formatting.
