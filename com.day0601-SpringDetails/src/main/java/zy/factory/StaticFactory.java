package zy.factory;

import zy.service.ServiceImpl;

public class StaticFactory {
    public static ServiceImpl getServiceImpl(){
        return new ServiceImpl();
    }
}
