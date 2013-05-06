/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

/**
 *
 * @author thinner
 */
public interface Builder<T> {

    public Builder<T> set(String tagName, String value);

    public T create();
}
