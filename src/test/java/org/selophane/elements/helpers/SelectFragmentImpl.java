/**
 * 
 */
package org.selophane.elements.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selophane.elements.base.ElementImpl;
import org.selophane.elements.base.UniqueElementLocator;
import org.selophane.elements.factory.api.ChainedElementLocatorFactory;
import org.selophane.elements.factory.api.ElementFactory;
import org.selophane.elements.widget.Select;

/**
 * Implementation of {@link SelectFragment}.
 * @author niels
 *
 */
public class SelectFragmentImpl extends ElementImpl implements SelectFragment {

    
    @FindBy(id = "option1")
    private Select option1;
    /**
     * A Select-Fragment
     * @param elementLocator the locator of the webelement.
     */
   public SelectFragmentImpl(final UniqueElementLocator elementLocator) {
       super(elementLocator);
       //TODO in such a case like this it would be performanter to have the parent searchcontext, because id is unique.
       ElementFactory.initElements(new ChainedElementLocatorFactory(elementLocator), this); 
   }
    @Override
    public Select getOption1() {
        return option1;
    }

    @Override
    public WebElement getSubElement(By by) {
        return findElement(by);
    }
    
    

}
