package json;

import java.io.InterruptedIOException;

/**
 * Interfaccia per JSONCommand
 */
public interface JSONCommand<T> {
    
    public abstract T execute(String toDecode) throws InterruptedIOException;
    
}