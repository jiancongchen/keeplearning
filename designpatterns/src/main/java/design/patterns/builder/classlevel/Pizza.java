package design.patterns.builder.classlevel;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author : jiancongchen on 2019-08-26
 * Builder模式也适用于类层次结构
 **/
public abstract class Pizza {

    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE};

    final Set<Topping> toppings = null;

    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder){
    }
}
