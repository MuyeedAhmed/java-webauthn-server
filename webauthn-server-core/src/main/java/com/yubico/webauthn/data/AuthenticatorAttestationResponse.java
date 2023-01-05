// Copyright (c) 2018, Yubico AB
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this
//    list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice,
//    this list of conditions and the following disclaimer in the documentation
//    and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.yubico.webauthn.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yubico.webauthn.data.exception.Base64UrlException;
import java.io.IOException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;


/**
 * Represents the authenticator's response to a client's request for the creation of a new public key credential. It
 * contains information about the new credential that can be used to identify it for later use, and metadata that can be
 * used by the WebAuthn Relying Party to assess the characteristics of the credential during registration.
 *
 * @see <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#authenticatorattestationresponse">§5.2.1. Information
 * About Public Key Credential (interface AuthenticatorAttestationResponse)
 * </a>
 */
public class AuthenticatorAttestationResponse implements AuthenticatorResponse {

    /**
     * Contains an attestation object, which is opaque to, and cryptographically protected against tampering by, the
     * client. The attestation object contains both authenticator data and an attestation statement. The former contains
     * the AAGUID, a unique credential ID, and the credential public key. The contents of the attestation statement are
     * determined by the attestation statement format used by the authenticator. It also contains any additional
     * information that the Relying Party's server requires to validate the attestation statement, as well as to decode
     * and validate the authenticator data along with the JSON-serialized client data. For more details, see <a
     * href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#sctn-attestation">§6.4 Attestation</a>, <a
     * href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#generating-an-attestation-object">§6.4.4 Generating an
     * Attestation Object</a>, and <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#fig-attStructs">Figure
     * 5</a>.
     */
    private final ByteArray attestationObject;

    private final ByteArray clientDataJSON;

    /**
     * The {@link #attestationObject} parsed as a domain object.
     */
    private final transient AttestationObject attestation;

    private final transient CollectedClientData clientData;

    @Override
    public ByteArray getAuthenticatorData() {
        return attestation.getAuthenticatorData().getBytes();
    }

    //(toBuilder = true)
    private AuthenticatorAttestationResponse(
        ByteArray attestationObject,
        ByteArray clientDataJSON
    ) throws IOException, Base64UrlException {
        this.attestationObject = attestationObject;
        this.clientDataJSON = clientDataJSON;

        attestation = new AttestationObject(attestationObject);
        this.clientData = new CollectedClientData(clientDataJSON);
    }

    @SuppressWarnings("builder") // toBuilder's return type should respect the arguments to the constructor that builder is applied to: https://github.com/kelloggm/object-construction-checker/issues/83
    public AuthenticatorAttestationResponseBuilder toBuilder() {
        return new AuthenticatorAttestationResponseBuilder().attestationObject(this.attestationObject).clientDataJSON(this.clientDataJSON);
    }
}
