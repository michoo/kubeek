package com.kubeek.service.rest.service;

import com.kubeek.sdk.app.KAppManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private KAppManager kAppManager;

    public List<String> findAll() {
        List<String> kubeekDataEntries = kAppManager.getAppList();
        return kubeekDataEntries;
    }

}
