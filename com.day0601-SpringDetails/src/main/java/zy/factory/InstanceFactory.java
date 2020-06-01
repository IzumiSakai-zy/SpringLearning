package zy.factory;

import zy.service.ServiceImpl;

public class InstanceFactory {
    public ServiceImpl getServiceImpl(){
        return new ServiceImpl();
    }
}
