package design.patterns.singleton.Lazymode;

/**
 * @author : jiancongchen on 2019-08-22
 **/
public class LazyModeSafe {

    private static LazyModeSafe lazyModeSafe;

    private LazyModeSafe(){};

    public static synchronized LazyModeSafe getInstance(){
        if(lazyModeSafe == null){
            lazyModeSafe = new LazyModeSafe();
        }
        return lazyModeSafe;
    }
}
