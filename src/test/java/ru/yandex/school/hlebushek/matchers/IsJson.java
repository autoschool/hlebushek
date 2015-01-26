package ru.yandex.school.hlebushek.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javax.ws.rs.core.MediaType;

/**
 * Created by ksenie on 23.01.15.
 */
public class IsJson extends TypeSafeMatcher<MediaType> {
    @Override
    public boolean matchesSafely(MediaType type) {
        return type.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    public void describeTo(Description description) {
        description.appendText("application/json");
    }

    @Factory
    public static <T> Matcher<MediaType> isJson() {
        return new IsJson();
    }
}
