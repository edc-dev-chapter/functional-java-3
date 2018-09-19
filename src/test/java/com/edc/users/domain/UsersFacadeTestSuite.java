package com.edc.users.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CompositionTest.class,
    LiftingTest.class,
    PartialApplicationTest.class,
    CurryingTest.class,
    MemoizationTest.class
})
public class UsersFacadeTestSuite {

}
