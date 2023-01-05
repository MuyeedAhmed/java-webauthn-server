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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yubico.webauthn.data.exception.Base64UrlException;
import java.io.IOException;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.checkerframework.checker.objectconstruction.qual.CalledMethods;
import org.checkerframework.checker.returnsrcvr.qual.This;


/**
 * Represents an authenticator's response to a client’s request for generation of a new authentication assertion given
 * the WebAuthn Relying Party's {@linkplain PublicKeyCredentialRequestOptions#challenge challenge} and OPTIONAL
 * {@linkplain PublicKeyCredentialRequestOptions#allowCredentials list of credentials} it is aware of. This response
 * contains a cryptographic {@linkplain #signature} proving possession of the credential private key, and optionally
 * evidence of user consent to a specific transaction.
 *
 * @see <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#authenticatorassertionresponse">§5.2.2. Web
 * Authentication Assertion (interface AuthenticatorAssertionResponse)
 * </a>
 */
public class AuthenticatorAssertionResponse implements AuthenticatorResponse {

    private final ByteArray authenticatorData;

    private final ByteArray clientDataJSON;

    /**
     * The raw signature returned from the authenticator. See <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#op-get-assertion">§6.3.3
     * The authenticatorGetAssertion Operation</a>.
     */
    private final ByteArray signature;

    /**
     * The user handle returned from the authenticator, or empty if the authenticator did not return a user handle. See
     * <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#op-get-assertion">§6.3.3 The authenticatorGetAssertion
     * Operation</a>.
     */
    private final Optional<ByteArray> userHandle;

    private final transient CollectedClientData clientData;

    //(toBuilder = true)
    private AuthenticatorAssertionResponse(
        final ByteArray authenticatorData,
        final ByteArray clientDataJSON,
        final ByteArray signature,
        final Optional<ByteArray> userHandle
    ) throws IOException, Base64UrlException {
        this.authenticatorData = authenticatorData;
        this.clientDataJSON = clientDataJSON;
        this.signature = signature;
        this.userHandle = userHandle;
        this.clientData = new CollectedClientData(this.clientDataJSON);
    }

    private AuthenticatorAssertionResponse(
        final ByteArray authenticatorData,
        final ByteArray clientDataJSON,
        final ByteArray signature,
        final ByteArray userHandle
    ) throws IOException, Base64UrlException {
        this(
            authenticatorData,
            clientDataJSON,
            signature,
            Optional.ofNullable(userHandle)
        );
    }

    public static class AuthenticatorAssertionResponseBuilder {
        private Optional<ByteArray> userHandle = Optional.empty();

        /**
         * The user handle returned from the authenticator, or empty if the authenticator did not return a user handle. See
         * <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#op-get-assertion">§6.3.3 The authenticatorGetAssertion
         * Operation</a>.
         */
        public AuthenticatorAssertionResponseBuilder userHandle(Optional<ByteArray> userHandle) {
            this.userHandle = userHandle;
            return this;
        }

        /**
         * The user handle returned from the authenticator, or empty if the authenticator did not return a user handle. See
         * <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#op-get-assertion">§6.3.3 The authenticatorGetAssertion
         * Operation</a>.
         */
        public AuthenticatorAssertionResponseBuilder userHandle(ByteArray userHandle) {
            return this.userHandle(Optional.of(userHandle));
        }
    }

    @SuppressWarnings("builder") // toBuilder's return type should respect the arguments to the constructor that builder is applied to: https://github.com/kelloggm/object-construction-checker/issues/83
    public AuthenticatorAssertionResponseBuilder toBuilder() {
        return new AuthenticatorAssertionResponseBuilder().authenticatorData(this.authenticatorData).clientDataJSON(this.clientDataJSON).signature(this.signature).userHandle(this.userHandle);
    }
}
