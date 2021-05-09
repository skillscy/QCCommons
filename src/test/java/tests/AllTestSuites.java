package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        QcUtilTests.class,
        ValidatorTests.class
})
public class AllTestSuites {
}
