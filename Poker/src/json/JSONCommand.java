package json;

/**
 * Interfaccia per JSONCommand
 */
public interface JSONCommand<T> {
    
    public abstract T execute(String toDecode);
    
}