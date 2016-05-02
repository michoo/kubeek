package com.kubeek.sdk.message;

import java.util.UUID;

public abstract class KMessage {
    private UUID uniqueID;

    public UUID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }
}
