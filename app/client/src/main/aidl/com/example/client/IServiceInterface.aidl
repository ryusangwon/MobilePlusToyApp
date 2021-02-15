// IServiceInterface.aidl
package com.example.client;

// Declare any non-default types here with import statements
import com.example.server.IServiceInterface;

interface IServiceInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}