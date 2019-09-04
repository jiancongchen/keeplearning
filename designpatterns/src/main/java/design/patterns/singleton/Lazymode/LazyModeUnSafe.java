package design.patterns.singleton.Lazymode;

/**
 * @author : jiancongchen on 2019-08-22
 **/
public class LazyModeUnSafe {

    private static LazyModeUnSafe lazyModeUnSafe;

    private LazyModeUnSafe(){};

    public static LazyModeUnSafe getInstance(){
        if(lazyModeUnSafe == null){
            lazyModeUnSafe = new LazyModeUnSafe();
        }
        return lazyModeUnSafe;
    }
}
