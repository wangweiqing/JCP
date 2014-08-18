package com.wwq.wthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.wwq.wthread.Preloader.ProductInfo;

public class Preloader {
    ProductInfo loadProductInfo() throws DataLoadException {
        return null;
    }

    @SuppressWarnings("unchecked")
	private final FutureTask<ProductInfo> future =
        new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
            public ProductInfo call() throws DataLoadException {
                return loadProductInfo();
            }
        });
    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get()
            throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw launderThrowable(cause);
        }
    }

    interface ProductInfo {
    }
    
    public static RuntimeException launderThrowable(Throwable t){
        if (t instanceof RuntimeException) 
        	return (RuntimeException) t;
        else if  ( t instanceof Error) 
        	throw (Error) t;
        else 
        	throw new IllegalStateException("Not unchecked",t);
    }
}

class DataLoadException extends Exception { }
