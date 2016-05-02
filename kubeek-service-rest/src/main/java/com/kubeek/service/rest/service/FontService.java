package com.kubeek.service.rest.service;


import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.service.rest.domain.Font;
import com.kubeek.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FontService {

    @Autowired
    private KScreenParam kScreenParam;

    public List<Font> findAll() {
        List<Font> kubeekDataEntries = Utils.getFilesList(kScreenParam.getFontDirectory(),"bdf");
        return kubeekDataEntries;
    }

}
