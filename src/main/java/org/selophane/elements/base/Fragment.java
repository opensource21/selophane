package org.selophane.elements.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.selophane.elements.factory.api.ElementFactory;

/**
 * An implementation of the Element interface. Delegates its work to an underlying WebElement instance for
 * custom functionality.
 */
public class Fragment extends ElementImpl {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public Fragment(final WebElement element, ElementLocator elementLocator, int pos) {
        super(element);
        ElementFactory.initElements(new ChainedElementLocatorFactory(elementLocator, pos), this);
    }
}
