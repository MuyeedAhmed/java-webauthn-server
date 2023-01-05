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

package com.yubico.webauthn;

import com.yubico.webauthn.data.AuthenticatorAttestationResponse;
import com.yubico.webauthn.data.ByteArray;
import com.yubico.webauthn.data.ClientRegistrationExtensionOutputs;
import com.yubico.webauthn.data.PublicKeyCredential;
import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;
import java.util.Optional;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.checkerframework.checker.returnsrcvr.qual.This;

/**
 * Parameters for {@link RelyingParty#finishRegistration(FinishRegistrationOptions)}.
 */
public class FinishRegistrationOptions {

    /**
     * The request that the {@link #getResponse() response} is a response to.
     */
    private final PublicKeyCredentialCreationOptions request;

    /**
     * The client's response to the {@link #getRequest() request}.
     *
     * <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#createCredential">navigator.credentials.create()</a>
     */
    private final PublicKeyCredential<AuthenticatorAttestationResponse, ClientRegistrationExtensionOutputs> response;

    /**
     * The <a href="https://tools.ietf.org/html/rfc8471#section-3.2">token binding ID</a> of the connection to the
     * client, if any.
     *
     * @see <a href="https://tools.ietf.org/html/rfc8471">The Token Binding Protocol Version 1.0</a>
     */
    private final Optional<ByteArray> callerTokenBindingId = Optional.empty();

    public static class FinishRegistrationOptionsBuilder {
        /**
         * The <a href="https://tools.ietf.org/html/rfc8471#section-3.2">token binding ID</a> of the connection to the
         * client, if any.
         *
         * @see <a href="https://tools.ietf.org/html/rfc8471">The Token Binding Protocol Version 1.0</a>
         */
        public FinishRegistrationOptionsBuilder callerTokenBindingId(Optional<ByteArray> callerTokenBindingId) {
            this.callerTokenBindingId = callerTokenBindingId;
            this.callerTokenBindingId$set = true;
            return this;
        }

        /**
         * The <a href="https://tools.ietf.org/html/rfc8471#section-3.2">token binding ID</a> of the connection to the
         * client, if any.
         *
         * @see <a href="https://tools.ietf.org/html/rfc8471">The Token Binding Protocol Version 1.0</a>
         */
        public FinishRegistrationOptionsBuilder callerTokenBindingId(ByteArray callerTokenBindingId) {
            return this.callerTokenBindingId(Optional.of(callerTokenBindingId));
        }
    }
}
