package ru.yandex.school.hlebushek.matchers;

import org.hamcrest.*;
import ru.yandex.school.hlebushek.models.Users;

/**
 * Created by Drak_kin on 25.01.2015.
 */
public class UsersMatcher extends TypeSafeMatcher<Users> {
    private static Users expected;
    private String describe;

    public static Matcher<Users> same(final Users user) {
        return new FeatureMatcher<Users, Users>(new UsersMatcher(user),"Users","Users") {
            @Override
            protected Users featureValueOf(Users user) {
                return user;
            }
        };
    }

    public UsersMatcher(Users expected){
        this.expected = expected;
    }
    @Override
    protected boolean matchesSafely(Users item) {
        describe = "";
        for(String i:Users.attributes()) {

            if (item.get(i) != null && expected.get(i) == null) {
                describe = "error in " + i + ": expected null but was " + item.get(i) + "\r\n";
                return false;
            }

            if (item.get(i).equals(expected.get(i))){
                return true;
            }else{
                describe = "error in " + i + ": expected "+expected.get(i)+" but was " + item.get(i) + "\r\n";
                return false;
            }

        }
        return false;
    }

    @Override
    protected void describeMismatchSafely(Users item, Description mismatchDescription) {
        mismatchDescription.appendText("User - ").appendValue(item.toString());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(describe);
    }

}
