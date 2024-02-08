package utils;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor,
                            Method testMethod)
    {
        IRetryAnalyzer retry = null;
        try {
            retry = testAnnotation.getRetryAnalyzerClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if(retry == null)
        {
            testAnnotation.setRetryAnalyzer(FailRetry.class);
        }
    }
}
