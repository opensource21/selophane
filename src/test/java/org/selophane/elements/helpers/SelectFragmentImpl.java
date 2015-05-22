/**
 * 
 */
package org.selophane.elements.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.selophane.elements.base.Fragment;
import org.selophane.elements.widget.Select;

/**
 * Implementation of {@link SelectFragment}.
 * @author niels
 *
 */
public class SelectFragmentImpl extends Fragment implements SelectFragment {

    
    @FindBy(id = "option1")
    private Select option1;
    


    public SelectFragmentImpl(WebElement element,
            ElementLocator elementLocator, int pos) {
        super(element, elementLocator, pos);
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
