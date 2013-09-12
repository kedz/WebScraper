package edu.columbia.cs.article.classifier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationEventListener implements WebDriverEventListener {
    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
