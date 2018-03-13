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