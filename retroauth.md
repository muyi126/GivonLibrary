# A simple way of calling authenticated requests using retrofit in android
[![Build Status](https://travis-ci.org/andretietz/retroauth.svg?branch=master)](https://travis-ci.org/andretietz/retroauth)
## Dependencies
* [Retrofit](https://github.com/square/retrofit) 2.0.2
* appcompat-v7 23.4.0

## What does it do?
如果你调用一个请求方法, annotated with the authenticated annotation（带有经过authenticated的注解的注释）, 它会做一下步骤:
* Step 1: 检查如果他们在Android AccountManager已经有一个账户Checks if there already is an account in the Android AccountManager. If not如果没有, it'll open a LoginActivity (you choose which)它会打开一个LoginActivity（你选择的）. If there already is an account如果已经有一个账户, go on with step 2, If there's more than one account open an Dialog to pick an account如果不止一个账户将打开一个Dialog选择一个账户.
* Step 2: Tries to get the authentication token from the (choosen) account for authorizing the request试着从选择的认证账户去获取authentication token. If there is no valid token如果没有有效的token（ex：token过期）, your LoginActivity will open 就会打开LoginActivity. After login go to Step 1 登录后跳到 Step 1.
* Step 3: Sends the actual request 发送实际的请求
* Step 4: By implementing a Provider you can check the response (i.e. a 401 you will be able to refresh the token) and decide if you want to retry the request or not.通过一个Provider你可以检查返回的结果（401 你可以刷新Token不可用）并且你可以重新请求

### 1. You need to deal with at least 3 different strings你需要处理至少3个不同的String
1. An action string which will be used to start your Login action string这将用于你启动Login 
 * (recommended: use your applicationId for example and add: ".ACTION")推荐：用你的applicationId加上 .Action。
2. An Account-Type string. This should be a unique string! 一个Account-Type。这应该是一个独一无二的String
 * (recommended: use your applicationId for example and add: ".ACCOUNT")推荐：用你的applicationId加上 .ACCOUNT。
3. A Token-Type string. It should be a unique string too4. .  一个Token-Type string。它也是一个独一无二的String
 * (recommended: use your applicationId for example and add: ".TOKEN")推荐：用你的applicationId加上 .TOKEN。
4. (Optional) Create as many Token-Type Strings as you need.（可选）根据你的需要创建多个Token-Type String
 
### 2. Create an Activity (or use one you already have) where the user can login. This Activity must extend from AuthenticationActivity and call finalizeAuthentication when the authentication finished 创建一个Activity（或者已有的Activity）能让用户登录。这个Activity必须继承AuthenticationActivity和在验证完成时调用finalizeAuthentication
 i.e. (see Demo for an example)
 
```java
//继承AuthenticationActivity
public class LoginActivity extends AuthenticationActivity {
...
private void someLoginMethod() {
     String user;
     String token;
     ... 
     // do login work here and make sure, that you 		provide at least a user and a token String
     登录并且确认，你需要提供至少一个user和一个token String
     ...
     Account account = createOrGetAccount(user);
     ...
     //这里填入step1你定义的Token-Type 相当于一个key用于映射	 token
     ...
     storeToken(account, "<your-TOKEN-string-defined-in-step-1>", token);
     // or optional（或者可选）
     storeToken(account, "<your-TOKEN-string-defined-in-step-1>", token, refreshToken);
     // add multiple tokens: storeToken(account, "<your-TOKEN-string-defined-in-step-X>", token2);
     // store some additional userdata (optionally)
     storeUserData(account, "key_for_some_user_data", "very-important-userdata");
     // finishes the activity and set this account to the "current-active" one
     finalizeAuthentication(account);
}
...
}
```
 Make sure your LoginActivity has the intent filter in the manifest 确保你的LoginActivity在manifest有intent filter:
 ```xml
 <?xml version="1.0" encoding="utf-8"?>
 <manifest>
 ...
        <activity android:name=".LoginActivity">
            <intent-filter>
            //这个actionString和下一步AuthenticationService中的一样
                <action android:name="<your-ACTION-string-defined-in-1>"/>
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
        </activity>
 ...
 </manifest>
 ```
### 3. Setup an AuthenticationService（设置一个AuthenticationService）
There are two ways of doing that:（有两个方法这样做）
 
* Option 1:
Extend the AuthenticationService and provide the ACTION-string.继承AuthenticationService并且提供Action-string
 
```java
public class SomeAuthenticationService extends AuthenticationService {
@Override
public String getLoginAction(Context context) {
    // this is used only to provide the action for the LoginActivity to open
    return "<your-ACTION-string-defined-in-step-1>"; // use context.getString instead if you like
}
}
```
* Option 2.  
Instead of creating you own Service feel free to use the "RetroauthAuthenticationService"
Make sure you define a new string:
```xml
<string name="com.andretietz.retroauth.authentication.ACTION" translatable="false"><your-ACTION-string-defined-in-step-1></string>
```
the key of your ACTION-string defined in step 1 is: "com.andretietz.retroauth.authentication.ACTION"
 
In both cases:
Provide a authenticator.xml:
```xml
<account-authenticator xmlns:android="http://schemas.android.com/apk/res/android"
                   android:accountType="<your-ACCOUNT-string-defined-in-step-1>"
                   android:icon="@mipmap/ic_launcher"
                   android:smallIcon="@mipmap/ic_launcher"
                   android:label="@string/app_name" />
```
 
Add the Service to the Manifest:
If you choose

* Option 1: provide the Service you created in the manifest
```xml
     ...
     <service
         android:name=".SomeAuthenticationService"
         android:process=":auth"
         android:exported="false">
         <intent-filter>
             <action android:name="android.accounts.AccountAuthenticator"/>
         </intent-filter>
         <meta-data
             android:name="android.accounts.AccountAuthenticator"
             android:resource="@xml/authenticator"/>
     </service>
     ...
 </application>
</manifest>
```
* Option 2: provide the RetroauthAuthenticationService in the manifest
```xml
      ...
      <service
          android:name="com.andretietz.retroauth.RetroauthAuthenticationService"
          android:process=":auth"
          android:exported="false">
          <intent-filter>
              <action android:name="android.accounts.AccountAuthenticator"/>
          </intent-filter>
          <meta-data
              android:name="android.accounts.AccountAuthenticator"
              android:resource="@xml/authenticator"/>
      </service>
      ...
  </application>
</manifest>
```
### 4. Create a Provider implementation
Since every Provider may have a different way of authenticating their request, you have to tell how this should work由于每一个Provider可能有不不同的方式验证他们的请求，你必须告诉他们怎么工作
 
```java
public class MyProvider implements Provider<Account, AndroidTokenType, AndroidToken> {

 @Override
 public Request authenticateRequest(Request request, AndroidToken androidToken) {
     // this is an example of adding the token to the header of a request 这个例子是包token添加到请求的头部
     return request.newBuilder()
             .header("Authorization", "Bearer " + androidToken.token)
             .build();
 }

 @Override
 public boolean retryRequired(int count, Response response, TokenStorage<Account, AndroidTokenType, AndroidToken> tokenStorage, Account account, AndroidTokenType androidTokenType, AndroidToken androidToken) {
        // this is an optional (sample) implementation
        if (!response.isSuccessful()) {
            if (response.code() == 401) {
            //账号密码错误失效
                tokenStorage.removeToken(account, androidTokenType, androidToken);
                ...
                // refresh your token using androidToken.refreshToken
                ...
                // store the refreshed token
                tokenStorage.storeToken(account, androidTokenType, new AndroidToken(newAccessToken, newRefreshToken));
                // retry
                return true;
            }
        }
        return false;
 }
}
```
 
### 5. Create your REST interface
 * Add authentication information to it:
 
```java
public interface SomeAuthenticatedService {
 @GET("/some/path")
 Call<ResultObject> someUnauthenticatedCall();

 @Authenticated({"<your-account-type>", "<token-required-for-this-call>"})
 @GET("/some/other/path")
 Call<ResultObject> someAuthenticatedCall();
}
```
 
 * Create the Retrofit object and instantiate it
```java
Retrofit retrofit = new Retroauth.Builder<>(new AndroidAuthenticationHandler(new MyProvider()))
        .baseUrl("https://api.awesome.com/")
        .client(httpClient)
        // add whatever you used to do with retrofit2
        // i.e.:
        .addConverterFactory(GsonConverterFactory.create())
        
        .build();
// create your services
SomeAuthenticatedService service = retrofit.create(SomeAuthenticatedService.class);
// use them
service.someAuthenticatedCall().execute();
```