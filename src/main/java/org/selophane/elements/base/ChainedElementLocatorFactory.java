package org.selophane.elements.base;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * {@link ElementLocatorFactory} which use a {@link ElementLocator} instead of 
 * a {@link SearchContext}.
 * @author niels
 *
 */
//TODO AJAX-Variant.
public class ChainedElementLocatorFactory implements ElementLocatorFactory {

    private final ElementLocator elementLocator;
    
    /**
     * {@link ElementLocator} could be used for list, so pos defined which 
     * object in the list should be the base of the search.
     */
    private final int pos;
    
    /**
     * New instance.
     * @param elementLocator {@link ElementLocator} which defines the {@link WebElement} as base for a search.
     * @param pos the position which elements of the {@link ElementLocator} should be used.
     */
    public ChainedElementLocatorFactory(ElementLocator elementLocator, int pos) {
        this.elementLocator = elementLocator;
        this.pos = pos;
    }



    @Override
    public ElementLocator createLocator(Field field) {
        return new ChainedElementLocator(elementLocator, pos,  field);
    }
    
    private final static class ChainedElementLocator implements ElementLocator {
        private final ElementLocator elementLocator;
        private final boolean shouldCache;
        private final By by;
        private WebElement cachedElement;
        private List<WebElement> cachedElementList;
        private final int index;
        
        /**
         * Creates a new element locator.
         * 
         * @param elementLocator a elementLocator
         * @param field The field on the Page Object that will hold the located value
         */
        public ChainedElementLocator(ElementLocator elementLocator, int index, Field field) {
          this.elementLocator= elementLocator;
          this.index= index;
          Annotations annotations = new Annotations(field);
          shouldCache = annotations.isLookupCached();
          by = annotations.buildBy();
        }

        /**
         * Find the element.
         */
        public WebElement findElement() {
          if (cachedElement != null && shouldCache) {
            return cachedElement;
          }

          WebElement element = elementLocator.findElements().get(index).findElement(by);
          if (shouldCache) {
            cachedElement = element;
          }

          return element;
        }

        /**
         * Find the element list.
         */
        public List<WebElement> findElements() {
          if (cachedElementList != null && shouldCache) {
            return cachedElementList;
          }

          List<WebElement> elements = elementLocator.findElements().get(index).findElements(by);
          if (shouldCache) {
            cachedElementList = elements;
          }

          return elements;
        }

    }
    
    

}
