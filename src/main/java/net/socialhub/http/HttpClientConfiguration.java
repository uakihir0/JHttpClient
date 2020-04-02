/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.socialhub.http;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public interface HttpClientConfiguration {

    String getHttpProxyHost();

    int getHttpProxyPort();

    String getHttpProxyUser();

    String getHttpProxyPassword();

    int getHttpConnectionTimeout();

    int getHttpReadTimeout();

    int getHttpRetryCount();

    int getHttpRetryIntervalSeconds();

    int getHttpMaxTotalConnections();

    int getHttpDefaultMaxPerRoute();

    String getFormTextContentType();

    boolean isPrettyDebugEnabled();

    boolean isGZIPEnabled();


    public static class HttpClientDefaultConfiguration implements HttpClientConfiguration {

        @Override
        public String getHttpProxyHost() {
            return null;
        }

        @Override
        public int getHttpProxyPort() {
            return -1;
        }

        @Override
        public String getHttpProxyUser() {
            return null;
        }

        @Override
        public String getHttpProxyPassword() {
            return null;
        }

        @Override
        public int getHttpConnectionTimeout() {
            return 20000;
        }

        @Override
        public int getHttpReadTimeout() {
            return 120000;
        }

        @Override
        public int getHttpRetryCount() {
            return 0;
        }

        @Override
        public int getHttpRetryIntervalSeconds() {
            return 5;
        }

        @Override
        public int getHttpMaxTotalConnections() {
            return 20;
        }

        @Override
        public int getHttpDefaultMaxPerRoute() {
            return 2;
        }

        @Override
        public String getFormTextContentType() {
            return "text/plain";
        }

        @Override
        public boolean isPrettyDebugEnabled() {
            return true;
        }

        @Override
        public boolean isGZIPEnabled() {
            return true;
        }
    }
}
