package com.yubico.webauthn.data;

import java.util.Collections;
import java.util.Optional;
import org.junit.Test;

public class PublicKeyCredentialDescriptorTest {

    public void itHasTheseBuilderMethods() {
        PublicKeyCredentialDescriptor.builder()
            .id(null)
            .transports(Collections.emptySet())
            .transports(Optional.of(Collections.emptySet()))
        ;
    }

}
