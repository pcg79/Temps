# Temps
First try at an android app

As an American living in the UK I'm still getting used to doing the conversion to and from Celcius.  In order to start
recognizing one for the other I want to be able to see them side by side.  But there aren't any existing apps I could
find that do that.  So I wrote one.

To use this yourself you'll need to get an API key from https://openweathermap.org/.  Then put that API key into
your own gradle.properties file (in wherever you have GRADLE_USER_HOME defined as) as the value to the key `Temps_OpenWeatherMapApiKey`.

E.g.:

```
~/.gradle
$ cat gradle.properties                                                                                                                                      ruby-2.3.6
Temps_OpenWeatherMapApiKey="REDACTED"
```
