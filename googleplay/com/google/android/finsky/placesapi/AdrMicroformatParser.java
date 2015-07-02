package com.google.android.finsky.placesapi;

import android.content.Context;
import android.text.TextUtils;
import com.android.vending.R;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AdrMicroformatParser {
    private static final HashMap<String, PartType> ADR_TYPE_MAP;
    private Context mContext;

    static class AnnotatedPart {
        String text;
        PartType type;

        AnnotatedPart(String text, PartType type) {
            this.text = text;
            this.type = type;
        }

        public String toString() {
            return this.text;
        }
    }

    private static class MicroformatAdrHandler extends DefaultHandler {
        private StringBuilder mAggregatedText;
        private PartType mCurrentType;
        LinkedList<AnnotatedPart> parts;

        private MicroformatAdrHandler() {
            this.parts = Lists.newLinkedList();
            this.mCurrentType = PartType.SEPARATOR;
            this.mAggregatedText = new StringBuilder();
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (this.mCurrentType != PartType.SEPARATOR) {
                throw new SAXException("Invalid nested element.");
            }
            String clazz = attributes.getValue("class");
            if (clazz != null) {
                PartType newType = (PartType) AdrMicroformatParser.ADR_TYPE_MAP.get(clazz);
                if (newType == null) {
                    newType = PartType.UNKNOWN;
                }
                finishPart();
                this.mCurrentType = newType;
            }
        }

        public void endElement(String uri, String localName, String qName) {
            if (this.mCurrentType != PartType.SEPARATOR) {
                finishPart();
                this.mCurrentType = PartType.SEPARATOR;
            }
        }

        public void endDocument() {
            finishPart();
        }

        public void characters(char[] ch, int start, int length) {
            this.mAggregatedText.append(ch, start, length);
        }

        private void finishPart() {
            if (this.mAggregatedText.length() > 0) {
                this.parts.add(new AnnotatedPart(this.mAggregatedText.toString(), this.mCurrentType));
                this.mAggregatedText = new StringBuilder();
            }
        }
    }

    enum PartType {
        ADR_STREET_ADDRESS("street-address"),
        ADR_EXTENDED_ADDRESS("extended-address"),
        ADR_LOCALITY("locality"),
        ADR_REGION("region"),
        ADR_POSTAL_CODE("postal-code"),
        UNKNOWN(null),
        SEPARATOR(null);
        
        private final String adrClass;

        private PartType(String adrClass) {
            this.adrClass = adrClass;
        }

        public String getAdrClass() {
            return this.adrClass;
        }
    }

    static {
        ADR_TYPE_MAP = Maps.newHashMap();
        for (PartType type : PartType.values()) {
            if (type.getAdrClass() != null) {
                ADR_TYPE_MAP.put(type.getAdrClass(), type);
            }
        }
    }

    public AdrMicroformatParser(Context context) {
        this.mContext = context;
    }

    public Address parse(String microformattedAddress) throws AdrMicroformatParserException {
        Address address = new Address();
        LinkedList<AnnotatedPart> parts = split(microformattedAddress);
        removeAll(parts, PartType.UNKNOWN);
        String addressLine1 = getFirstAndRemove(parts, PartType.ADR_STREET_ADDRESS);
        if (!TextUtils.isEmpty(addressLine1)) {
            address.addressLine1 = addressLine1;
            address.hasAddressLine1 = true;
        }
        String city = getFirstAndRemove(parts, PartType.ADR_LOCALITY);
        if (!TextUtils.isEmpty(city)) {
            address.city = city;
            address.hasCity = true;
        }
        String postalCode = getFirstAndRemove(parts, PartType.ADR_POSTAL_CODE);
        if (!TextUtils.isEmpty(postalCode)) {
            address.postalCode = postalCode;
            address.hasPostalCode = true;
        }
        String state = getFirstAndRemove(parts, PartType.ADR_REGION);
        if (!TextUtils.isEmpty(state)) {
            address.state = state;
            address.hasState = true;
        }
        removeTrailingSeparators(parts);
        String addressLine2 = join(parts);
        if (!TextUtils.isEmpty(addressLine2)) {
            address.addressLine2 = addressLine2;
            address.hasAddressLine2 = true;
        }
        return address;
    }

    void removeAll(LinkedList<AnnotatedPart> parts, PartType type) {
        do {
        } while (getFirstAndRemove(parts, type) != null);
    }

    String join(LinkedList<AnnotatedPart> parts) {
        return TextUtils.join("", parts).replaceAll("\\n", this.mContext.getString(R.string.adr_microformat_newline_replacement));
    }

    void removeTrailingSeparators(LinkedList<AnnotatedPart> parts) {
        while (!parts.isEmpty() && ((AnnotatedPart) parts.getLast()).type == PartType.SEPARATOR) {
            parts.removeLast();
        }
    }

    String getFirstAndRemove(LinkedList<AnnotatedPart> parts, PartType type) {
        Iterator<AnnotatedPart> it = parts.iterator();
        while (it.hasNext()) {
            AnnotatedPart part = (AnnotatedPart) it.next();
            if (part.type == type) {
                it.remove();
                if (it.hasNext() && ((AnnotatedPart) it.next()).type == PartType.SEPARATOR) {
                    it.remove();
                }
                return part.text;
            }
        }
        return null;
    }

    LinkedList<AnnotatedPart> split(String text) throws AdrMicroformatParserException {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            MicroformatAdrHandler handler = new MicroformatAdrHandler();
            saxParser.parse(new InputSource(new StringReader(appendXmlHeader(text))), handler);
            return handler.parts;
        } catch (ParserConfigurationException e) {
            throw new AdrMicroformatParserException(e);
        } catch (SAXException e2) {
            throw new AdrMicroformatParserException(e2);
        } catch (IOException e3) {
            throw new AdrMicroformatParserException(e3);
        }
    }

    private String appendXmlHeader(String text) {
        return "<?xml version=\"1.0\"?><root>" + text + "</root>";
    }
}
