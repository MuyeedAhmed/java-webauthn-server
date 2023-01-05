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
import java.net.URL;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;
import org.checkerframework.checker.returnsrcvr.qual.This;


/**
 * Used to supply additional Relying Party attributes when creating a new credential.
 *
 * @see <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#dictdef-publickeycredentialrpentity">§5.4.2. Relying
 * Party Parameters for Credential Generation (dictionary PublicKeyCredentialRpEntity)
 * </a>
 */
public class RelyingPartyIdentity implements PublicKeyCredentialEntity {

    /**
     * The human-palatable name of the Relaying Party.
     *
     * <p>
     * For example: "ACME Corporation", "Wonderful Widgets, Inc." or "ОАО Примертех".
     * </p>
     */
    private final String name;

    /**
     * A unique identifier for the Relying Party, which sets the <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#rp-id">RP
     * ID</a>.
     *
     * @see <a href="https://www.w3.org/TR/2019/PR-webauthn-20190117/#rp-id">RP ID</a>
     */
    private final String id;

    /**
     * A URL which resolves to an image associated with the entity. For example, this could be the Relying Party's
     * logo.
     *
     * <p>This URL MUST be an a priori authenticated URL. Authenticators MUST accept and store a
     * 128-byte minimum length for an icon member’s value. Authenticators MAY ignore an icon member’s value if its
     * length is greater than 128 bytes. The URL’s scheme MAY be "data" to avoid fetches of the URL, at the cost of
     * needing more storage.
     * </p>
     */
    private final Optional<URL> icon = Optional.empty();

    private RelyingPartyIdentity(
        String name,
        String id,
        URL icon
    ) {
        this(name, id, Optional.ofNullable(icon));
    }

    public static class RelyingPartyIdentityBuilder {
        /**
         * A URL which resolves to an image associated with the entity. For example, this could be the Relying Party's
         * logo.
         *
         * <p>This URL MUST be an a priori authenticated URL. Authenticators MUST accept and store a
         * 128-byte minimum length for an icon member’s value. Authenticators MAY ignore an icon member’s value if its
         * length is greater than 128 bytes. The URL’s scheme MAY be "data" to avoid fetches of the URL, at the cost of
         * needing more storage.
         * </p>
         */
        public RelyingPartyIdentityBuilder icon(Optional<URL> icon) {
            this.icon = icon;
            this.icon$set = true;
            return this;
        }

        /**
         * A URL which resolves to an image associated with the entity. For example, this could be the Relying Party's
         * logo.
         *
         * <p>This URL MUST be an a priori authenticated URL. Authenticators MUST accept and store a
         * 128-byte minimum length for an icon member’s value. Authenticators MAY ignore an icon member’s value if its
         * length is greater than 128 bytes. The URL’s scheme MAY be "data" to avoid fetches of the URL, at the cost of
         * needing more storage.
         * </p>
         */
        public RelyingPartyIdentityBuilder icon(URL icon) {
            return this.icon(Optional.of(icon));
        }
    }

}
