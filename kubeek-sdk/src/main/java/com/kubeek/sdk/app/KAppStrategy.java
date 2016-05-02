package com.kubeek.sdk.app;

import java.util.List;

public interface KAppStrategy {

     KApp getNextApp(List<KApp> kApps);
}
