package bassiouny.ahmed.wasllabank.interfaces;

/**
 * Created by bassiouny on 12/04/18.
 */

public interface MyObserverInterface<T> {

    void register(ObserverInterface observer);
    void unregister(ObserverInterface observer);
    void notifyObservers(T t);
}
