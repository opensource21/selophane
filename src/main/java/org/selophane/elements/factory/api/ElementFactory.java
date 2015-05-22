package org.selophane.elements.factory.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.selophane.elements.factory.internal.ElementDecorator;

/**
 * Element factory for wrapped elements. Similar to {@link org.openqa.selenium.support.PageFactory}
 */
public class ElementFactory {

    /**
     *  See {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.WebDriver driver, Class)}
     */
    public static <T> T initElements(WebDriver driver, Class<T> containerClassToProxy) {
        final T container = instantiatePage(driver, containerClassToProxy);
        return initElements(driver, container);
    }

    /**
     * As
     * {@link ElementFactory#initElements(org.openqa.selenium.WebDriver, Class)}
     * but will only replace the fields of an already instantiated container Object.
     * 
     * @param searchContext A search context that will be used to look up the elements
     * @param container The object with WebElement and List<WebElement> fields that should be proxied.
     * @return the initialized container-object.
     */
    public static <T> T initElements(SearchContext searchContext, T container) {
        initElements(new ElementDecorator(new DefaultElementLocatorFactory(searchContext)), container);
        return container;
    }

    /**
     * see {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.support.pagefactory.ElementLocatorFactory, Object)}
     */
    public static void initElements(ElementLocatorFactory factory, Object container) {
        final ElementLocatorFactory factoryRef = factory;
        initElements(new ElementDecorator(factoryRef), container);
    }

    /**
     * see {@link org.openqa.selenium.support.PageFactory#initElements(org.openqa.selenium.support.pagefactory.ElementLocatorFactory, Object)}
     */
    public static void initElements(FieldDecorator decorator, Object container) {
        PageFactory.initElements(decorator, container);
    }

    /**
     * Copy of {@link org.openqa.selenium.support.PageFactory#instantiatePage(org.openqa.selenium.WebDriver, Class)}
     */
    private static <T> T instantiatePage(WebDriver driver, Class<T> containerClassToProxy) {
        try {
            try {
                Constructor<T> constructor = containerClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return containerClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
