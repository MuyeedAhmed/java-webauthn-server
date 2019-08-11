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

import com.yubico.webauthn.data.AuthenticatorSelectionCriteria;
import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;
import com.yubico.webauthn.data.RegistrationExtensionInputs;
import com.yubico.webauthn.data.UserIdentity;
import java.util.Optional;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.checkerframework.checker.returnsrcvr.qual.This;

/**
 * Parameters for {@link RelyingParty#startRegistration(StartRegistrationOptions)}.
 */
@Value
@Builder(toBuilder = true)
public class StartRegistrationOptions {

    /**
     * Identifiers for the user creating a credential.
     */
    @NonNull
    private final UserIdentity user;

    /**
     * Constraints on what kind of authenticator the user is allowed to use to create the credential.
     */
    @NonNull
    @Builder.Default
    private final Optional<AuthenticatorSelectionCriteria> authenticatorSelection = Optional.empty();

    /**
     * Extension inputs for this registration operation.
     */
    @NonNull
    @Builder.Default
    private final RegistrationExtensionInputs extensions = RegistrationExtensionInputs.builder().build();

    /**
     * The value for {@link PublicKeyCredentialCreationOptions#getTimeout()} for this registration operation.
     * <p>
     * This library does not take the timeout into account in any way, other than passing it through to the {@link
     * PublicKeyCredentialCreationOptions} so it can be used as an argument to
     * <code>navigator.credentials.create()</code> on the client side.
     * </p>
     * <p>
     * The default is empty.
     * </p>
     */
    @NonNull
    @Builder.Default
    private final Optional<Long> timeout = Optional.empty();

    public static class StartRegistrationOptionsBuilder {
        /**
         * Constraints on what kind of authenticator the user is allowed to use to create the credential.
         */
        public @This StartRegistrationOptionsBuilder authenticatorSelection(@NonNull Optional<AuthenticatorSelectionCriteria> authenticatorSelection) {
            this.authenticatorSelection = authenticatorSelection;
            this.authenticatorSelection$set = true;
            return this;
        }

        /**
         * Constraints on what kind of authenticator the user is allowed to use to create the credential.
         */
        public @This StartRegistrationOptionsBuilder authenticatorSelection(@NonNull AuthenticatorSelectionCriteria authenticatorSelection) {
            return this.authenticatorSelection(Optional.of(authenticatorSelection));
        }

        /**
         * The value for {@link PublicKeyCredentialCreationOptions#getTimeout()} for this registration operation.
         * <p>
         * This library does not take the timeout into account in any way, other than passing it through to the {@link
         * PublicKeyCredentialCreationOptions} so it can be used as an argument to
         * <code>navigator.credentials.create()</code> on the client side.
         * </p>
         * <p>
         * The default is empty.
         * </p>
         */
        public @This StartRegistrationOptionsBuilder timeout(@NonNull Optional<Long> timeout) {
            if (timeout.isPresent() && timeout.get() <= 0) {
                throw new IllegalArgumentException("timeout must be positive, was: " + timeout.get());
            }
            this.timeout = timeout;
            this.timeout$set = true;
            return this;
        }

        /**
         * The value for {@link PublicKeyCredentialCreationOptions#getTimeout()} for this registration operation.
         * <p>
         * This library does not take the timeout into account in any way, other than passing it through to the {@link
         * PublicKeyCredentialCreationOptions} so it can be used as an argument to
         * <code>navigator.credentials.create()</code> on the client side.
         * </p>
         * <p>
         * The default is empty.
         * </p>
         */
        public @This StartRegistrationOptionsBuilder timeout(long timeout) {
            return this.timeout(Optional.of(timeout));
        }
    }

}
