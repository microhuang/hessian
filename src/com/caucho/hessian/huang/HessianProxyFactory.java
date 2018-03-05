/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caucho.hessian.huang;

import com.caucho.hessian.io.HessianRemoteObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;

/**
 *
 * @author huang
 */
public class HessianProxyFactory extends com.caucho.hessian.client.HessianProxyFactory {
    @Override
    public Object create(Class<?> api, URL url, ClassLoader loader)
    {
        if (api == null) {
            throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
        }

        InvocationHandler handler = new HessianProxy(url, this, api);

        return Proxy.newProxyInstance(loader,
                new Class[]{api, HessianRemoteObject.class},
                handler);
    }
}
