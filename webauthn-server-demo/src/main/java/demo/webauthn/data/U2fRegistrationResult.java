package demo.webauthn.data;

import com.yubico.webauthn.attestation.Attestation;
import com.yubico.webauthn.data.ByteArray;
import com.yubico.webauthn.data.PublicKeyCredentialDescriptor;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

public class U2fRegistrationResult {

    private final PublicKeyCredentialDescriptor keyId;

    private final boolean attestationTrusted;

    private final ByteArray publicKeyCose;

    private final List<String> warnings = Collections.emptyList();

    private final Optional<Attestation> attestationMetadata = Optional.empty();
}
