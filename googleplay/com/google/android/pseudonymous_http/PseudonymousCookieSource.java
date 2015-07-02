package com.google.android.pseudonymous_http;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.message.BasicHeader;

public interface PseudonymousCookieSource {

    public static final class Helper {
        private static final Pattern COOKIE_PAIR;
        private static final int DOT;
        private static final String[] PSEUDONYMOUS_ID_DOMAINS;
        private static final Pattern TOKEN;
        private static final Pattern VALUE;

        static {
            DOT = ".".codePointAt(0);
            PSEUDONYMOUS_ID_DOMAINS = new String[]{"google.com", "googleapis.com"};
            TOKEN = Pattern.compile("[^()<>@,;:\\\"/\\[\\]\\?={}\\s]+");
            VALUE = Pattern.compile("[^,;\\s\"]+");
            COOKIE_PAIR = Pattern.compile("(^|[\\s;,]+)([^()<>@,;:\\\"/\\[\\]\\?={}\\s]+)\\s*=\\s*(\"[^\"]*\"|[^,;\\s\"]+)");
        }

        public static HttpUriRequest setRequestCookie(HttpUriRequest request, PseudonymousCookieSource cookieSource) throws IOException {
            if (cookieSourceApplies(request, cookieSource)) {
                String cookieName = cookieSource.getCookieName();
                String value = cookieSource.getCookieValue();
                boolean found = false;
                for (Header header : request.getHeaders("Cookie")) {
                    if (isCookiePresent(header.getValue(), cookieName)) {
                        if (!(found || (request instanceof RequestWrapper))) {
                            request = wrapRequest(request);
                        }
                        found = true;
                        request.removeHeader(header);
                        request.addHeader(new BasicHeader(header.getName(), replaceCookie(header.getValue(), cookieName, value)));
                    }
                }
                if (!found) {
                    request.addHeader(new BasicHeader("Cookie", cookieName + "=" + wrapInQuotesIfNeeded(value)));
                }
            }
            return request;
        }

        public static HttpResponse updateFromResponseCookie(HttpUriRequest request, HttpResponse response, PseudonymousCookieSource cookieSource) {
            if (cookieSourceApplies(request, cookieSource)) {
                String value;
                String cookieName = cookieSource.getCookieName();
                for (Header header : response.getHeaders("Set-Cookie")) {
                    value = getCookieValue(header.getValue(), cookieName);
                    if (value != null) {
                        cookieSource.setCookieValue(value);
                    }
                }
                for (Header header2 : response.getHeaders("Set-Cookie2")) {
                    value = getCookieValue(header2.getValue(), cookieName);
                    if (value != null) {
                        cookieSource.setCookieValue(value);
                    }
                }
            }
            return response;
        }

        public static boolean cookieSourceApplies(HttpUriRequest request, PseudonymousCookieSource cookieSource) {
            if (cookieSource == null) {
                return false;
            }
            for (String domain : PSEUDONYMOUS_ID_DOMAINS) {
                if (domainMatches(domain, request.getURI().getHost())) {
                    return true;
                }
            }
            return false;
        }

        private static RequestWrapper wrapRequest(HttpUriRequest request) throws IOException {
            try {
                RequestWrapper wrapped;
                if (request instanceof HttpEntityEnclosingRequest) {
                    wrapped = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest) request);
                } else {
                    wrapped = new RequestWrapper(request);
                }
                wrapped.resetHeaders();
                return wrapped;
            } catch (ProtocolException e) {
                throw new ClientProtocolException(e);
            }
        }

        public static boolean domainMatches(String domain, String host) {
            boolean z = true;
            if (domain.length() > host.length()) {
                return false;
            }
            if (domain.equalsIgnoreCase(host)) {
                return true;
            }
            int cOffset = host.length() - 1;
            for (int sOffset = domain.length() - 1; sOffset > -1; sOffset--) {
                if (Character.toLowerCase(host.codePointAt(cOffset)) != Character.toLowerCase(domain.codePointAt(sOffset))) {
                    return false;
                }
                cOffset--;
            }
            if (host.codePointAt(cOffset) != DOT) {
                z = false;
            }
            return z;
        }

        public static boolean isCookiePresent(String headerValue, String name) {
            Matcher matcher = COOKIE_PAIR.matcher(headerValue);
            while (matcher.find()) {
                if (matcher.group(2).equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public static String replaceCookie(String headerValue, String name, String value) {
            Matcher matcher = COOKIE_PAIR.matcher(headerValue);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                if (matcher.group(2).equals(name)) {
                    matcher.appendReplacement(sb, matcher.group(1) + name + "=" + wrapInQuotesIfNeeded(value));
                } else {
                    matcher.appendReplacement(sb, matcher.group());
                }
            }
            matcher.appendTail(sb);
            return sb.toString();
        }

        public static String getCookieValue(String headerValue, String name) {
            Matcher matcher = COOKIE_PAIR.matcher(headerValue);
            while (matcher.find()) {
                if (matcher.group(2).equals(name)) {
                    String cookiePairString = matcher.group();
                    String value = cookiePairString.substring(cookiePairString.indexOf("=") + 1).trim();
                    if (value.startsWith("\"")) {
                        return value.substring(1, value.length() - 1);
                    }
                    return value;
                }
            }
            return null;
        }

        public static String wrapInQuotesIfNeeded(String value) {
            if (value == null) {
                return "\"\"";
            }
            return !VALUE.matcher(value).matches() ? "\"" + value + "\"" : value;
        }
    }

    String getCookieName();

    String getCookieValue();

    void setCookieValue(String str);
}
