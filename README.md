The purpose of this project is to explain how to make code testable.

This project is a simple application which is supposed to send greetings to users 
on International Man's or Women's Day depending on the gender of the person.

Unfortunately, it does not work as expected. 
It does not send greetings at all.
There are even more problems in the code, like performance and duplications.
It's not the most beautiful one but similar to what happens sometimes in legacy software.

Let's fix the errors and write unit tests.

We will use Mockito library for mocking.

But wait.. the date is initialized inside the `SimpleUsersManager.processUsers` method so we cannot 
wait for Men's or Woman's Day. We need to be able to pass the date to the method in test.

Ouch!! the `SimpleUsersManager` is calling external REST service to check the gender.
We cannot depend on it in unit tests. What if it was down or our Continuous Integration did not have connection outside?
We need to mock it in the test.

So we wrote the test method (`shouldSendGreetingsToWoman`), but I'm not a fan of changing method protection just for
the purpose of testing from private to protected and spying.
We will extract the `sendGreeting` and `isMale` methods to separate services later on.
The `SimpleUsersManager` definitely does much more than just managing user greetings.
It should not connect to external API and print greetings (for simplicity, in real case it would most likely send emails).

Even though we were able to test it, the test still fails with
```
Wanted but not invoked:
simpleUsersManager.sendGreeting(
    "Marry Poppins",
    "Happy Women's Day"
);
-> at com.consulner.demo.SimpleUsersManagerTest.shouldSendGreetingsToWoman(SimpleUsersManagerTest.java:30)

```

Let's fix it next.

Taking advantage of Java 8 we could use better date implementation than `java.util.Date` so let's do it.

If we look at the `SimpleUsersManager.processUsers` we can still see that we call REST API twice.
We can make it better.
We will make test to fail again by checking exactly one invocation of the method like this:
```
 verify(managerSpy, times(1)).isMale(user);
```

Then we could make `SimpleUsersManager.processUsers` even more readable by adding methods:
 - `isWomenDay`
 - `isMenDay`
 
 This is an example where having unit tests rocks, cause I will initially make a mistake when refactoring
 and test will catch it.
 
 As we have added more tests for men and women cases we see that they are similar.
 We could replace all of them with parametrized test.



