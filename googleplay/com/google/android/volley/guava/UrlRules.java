package com.google.android.volley.guava;

import android.content.ContentResolver;
import android.util.Log;
import com.google.android.gsf.Gservices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRules {
    private static final Pattern PATTERN_SPACE_PLUS;
    private static final Pattern RULE_PATTERN;
    private static UrlRules sCachedRules;
    private static Object sCachedVersionToken;
    private final Pattern mPattern;
    private final Rule[] mRules;

    public static class Rule implements Comparable {
        public static final Rule DEFAULT;
        public final boolean mBlock;
        public final String mName;
        public final String mPrefix;
        public final String mRewrite;

        static {
            DEFAULT = new Rule();
        }

        public Rule(String name, String rule) throws RuleFormatException {
            this.mName = name;
            String[] words = UrlRules.PATTERN_SPACE_PLUS.split(rule);
            if (words.length == 0) {
                throw new RuleFormatException("Empty rule");
            }
            this.mPrefix = words[0];
            String rewrite = null;
            boolean block = false;
            int pos = 1;
            while (pos < words.length) {
                String word = words[pos].toLowerCase();
                if (word.equals("rewrite") && pos + 1 < words.length) {
                    rewrite = words[pos + 1];
                    pos += 2;
                } else if (word.equals("block")) {
                    block = true;
                    pos++;
                } else {
                    throw new RuleFormatException("Illegal rule: " + rule);
                }
            }
            this.mRewrite = rewrite;
            this.mBlock = block;
        }

        private Rule() {
            this.mName = "DEFAULT";
            this.mPrefix = "";
            this.mRewrite = null;
            this.mBlock = false;
        }

        public String apply(String url) {
            if (this.mBlock) {
                return null;
            }
            if (this.mRewrite != null) {
                return this.mRewrite + url.substring(this.mPrefix.length());
            }
            return url;
        }

        public int compareTo(Object o) {
            return ((Rule) o).mPrefix.compareTo(this.mPrefix);
        }
    }

    public static class RuleFormatException extends Exception {
        public RuleFormatException(String msg) {
            super(msg);
        }
    }

    static {
        PATTERN_SPACE_PLUS = Pattern.compile(" +");
        RULE_PATTERN = Pattern.compile("\\W");
        sCachedRules = new UrlRules(new Rule[0]);
    }

    public UrlRules(Rule[] rules) {
        Arrays.sort(rules);
        StringBuilder pattern = new StringBuilder("(");
        for (int i = 0; i < rules.length; i++) {
            if (i > 0) {
                pattern.append(")|(");
            }
            pattern.append(RULE_PATTERN.matcher(rules[i].mPrefix).replaceAll("\\\\$0"));
        }
        this.mPattern = Pattern.compile(pattern.append(")").toString());
        this.mRules = rules;
    }

    public Rule matchRule(String url) {
        Matcher matcher = this.mPattern.matcher(url);
        if (matcher.lookingAt()) {
            for (int i = 0; i < this.mRules.length; i++) {
                if (matcher.group(i + 1) != null) {
                    return this.mRules[i];
                }
            }
        }
        return Rule.DEFAULT;
    }

    public static synchronized UrlRules getRules(ContentResolver resolver) {
        UrlRules urlRules;
        synchronized (UrlRules.class) {
            Object versionToken = Gservices.getVersionToken(resolver);
            if (versionToken == sCachedVersionToken) {
                if (Log.isLoggable("UrlRules", 2)) {
                    Log.v("UrlRules", "Using cached rules, versionToken: " + versionToken);
                }
                urlRules = sCachedRules;
            } else {
                if (Log.isLoggable("UrlRules", 2)) {
                    Log.v("UrlRules", "Scanning for Gservices \"url:*\" rules");
                }
                Map<String, String> urlRules2 = Gservices.getStringsByPrefix(resolver, "url:");
                ArrayList<Rule> rules = new ArrayList();
                for (Entry<String, String> e : urlRules2.entrySet()) {
                    try {
                        String name = ((String) e.getKey()).substring(4);
                        String value = (String) e.getValue();
                        if (!(value == null || value.length() == 0)) {
                            if (Log.isLoggable("UrlRules", 2)) {
                                Log.v("UrlRules", "  Rule " + name + ": " + value);
                            }
                            rules.add(new Rule(name, value));
                        }
                    } catch (RuleFormatException exc) {
                        Log.e("UrlRules", "Invalid rule from Gservices", exc);
                    }
                }
                sCachedRules = new UrlRules((Rule[]) rules.toArray(new Rule[rules.size()]));
                sCachedVersionToken = versionToken;
                if (Log.isLoggable("UrlRules", 2)) {
                    Log.v("UrlRules", "New rules stored, versionToken: " + versionToken);
                }
                urlRules = sCachedRules;
            }
        }
        return urlRules;
    }
}
