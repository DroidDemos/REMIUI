package com.google.android.wallet.instrumentmanager.common.address;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DeviceAddressSource extends InMemoryAddressSource {
    private static final Pattern STREET_SEPARATORS;
    private final Context mContext;
    private boolean mHasReadContactsPermission;
    private boolean mQueriedReadContactsPermission;

    static {
        STREET_SEPARATORS = Pattern.compile("[\\r\\n]");
    }

    public DeviceAddressSource(Context context) {
        super("DeviceAddressSource");
        this.mHasReadContactsPermission = false;
        this.mQueriedReadContactsPermission = false;
        this.mContext = context;
    }

    private int getSizeAllowanceBytes() {
        int sizeAllowanceBytes = ((((ActivityManager) this.mContext.getSystemService("activity")).getMemoryClass() / 16) * 1024) * 1024;
        if (sizeAllowanceBytes == 0) {
            return 1048576;
        }
        return sizeAllowanceBytes;
    }

    protected ArrayList<PostalAddress> getAddresses() throws Throwable {
        if (!hasReadContactsPermission()) {
            return null;
        }
        int remainingSizeAllowanceBytes = getSizeAllowanceBytes();
        ContentResolver resolver = this.mContext.getContentResolver();
        Cursor cursor = getContactsCursor(resolver, new String[]{"contact_id", "data1"});
        try {
            int contactIdCol;
            int contactId;
            SparseArray<String> contacts = new SparseArray(cursor.getCount());
            if (cursor.getCount() > 0) {
                contactIdCol = cursor.getColumnIndexOrThrow("contact_id");
                int displayNamePrimaryCol = cursor.getColumnIndexOrThrow("data1");
                while (cursor.moveToNext()) {
                    contactId = cursor.getInt(contactIdCol);
                    String displayName = cursor.getString(displayNamePrimaryCol);
                    if (!TextUtils.isEmpty(displayName)) {
                        contacts.append(contactId, displayName);
                    }
                }
            }
            cursor.close();
            cursor = getAddressesCursor(resolver, new String[]{"contact_id", "data4", "data7", "data6", "data8", "data9", "data10", "data5"});
            try {
                PostalAddress address;
                String name;
                int i;
                ArrayList<PostalAddress> addresses = new ArrayList(cursor.getCount());
                SparseBooleanArray contactsWithAddresses = new SparseBooleanArray(contacts.size());
                if (cursor.getCount() > 0) {
                    contactIdCol = cursor.getColumnIndexOrThrow("contact_id");
                    int streetCol = cursor.getColumnIndexOrThrow("data4");
                    int cityCol = cursor.getColumnIndexOrThrow("data7");
                    int neighborhoodCol = cursor.getColumnIndexOrThrow("data6");
                    int regionCol = cursor.getColumnIndexOrThrow("data8");
                    int postcodeCol = cursor.getColumnIndexOrThrow("data9");
                    int countryCol = cursor.getColumnIndexOrThrow("data10");
                    int poboxCol = cursor.getColumnIndexOrThrow("data5");
                    while (cursor.moveToNext()) {
                        address = new PostalAddress();
                        contactId = cursor.getInt(contactIdCol);
                        name = trim((String) contacts.get(contactId));
                        if (!TextUtils.isEmpty(name)) {
                            address.recipientName = name;
                        }
                        if (!cursor.isNull(streetCol)) {
                            String[] streets = STREET_SEPARATORS.split(cursor.getString(streetCol));
                            ArrayList<String> streetsWithNameDefined = new ArrayList(streets.length);
                            for (String trim : streets) {
                                String street = trim(trim);
                                if (!TextUtils.isEmpty(street)) {
                                    streetsWithNameDefined.add(street);
                                }
                            }
                            address.addressLine = (String[]) streetsWithNameDefined.toArray(new String[streetsWithNameDefined.size()]);
                        }
                        String city = trim(cursor.getString(cityCol));
                        if (!TextUtils.isEmpty(city)) {
                            address.localityName = city;
                        }
                        String neighborhood = trim(cursor.getString(neighborhoodCol));
                        if (!TextUtils.isEmpty(neighborhood)) {
                            address.dependentLocalityName = neighborhood;
                        }
                        String region = trim(cursor.getString(regionCol));
                        if (!TextUtils.isEmpty(region)) {
                            address.administrativeAreaName = region;
                        }
                        String postcode = trim(cursor.getString(postcodeCol));
                        if (!TextUtils.isEmpty(postcode)) {
                            address.postalCodeNumber = postcode;
                        }
                        String country = trim(cursor.getString(countryCol));
                        if (!TextUtils.isEmpty(country)) {
                            address.countryNameCode = country;
                        }
                        String pobox = trim(cursor.getString(poboxCol));
                        if (!TextUtils.isEmpty(pobox)) {
                            address.postBoxNumber = pobox;
                        }
                        remainingSizeAllowanceBytes = checkRemainingSizeAllowance(remainingSizeAllowanceBytes, sizeOfPostalAddress(address));
                        addresses.add(address);
                        contactsWithAddresses.put(contactId, true);
                    }
                }
                cursor.close();
                int length = contacts.size();
                for (i = 0; i < length; i++) {
                    if (!contactsWithAddresses.get(contacts.keyAt(i))) {
                        name = (String) contacts.valueAt(i);
                        address = new PostalAddress();
                        address.recipientName = name;
                        remainingSizeAllowanceBytes = checkRemainingSizeAllowance(remainingSizeAllowanceBytes, sizeOfPostalAddress(address));
                        addresses.add(address);
                    }
                }
                return addresses;
            } catch (Throwable th) {
                cursor.close();
            }
        } catch (Throwable th2) {
            cursor.close();
        }
    }

    private static String trim(String str) {
        return str != null ? str.trim() : null;
    }

    private static int sizeOfPostalAddress(PostalAddress address) {
        if (address == null) {
            return 0;
        }
        return (((address.getCachedSize() * 2) + 45) / 8) * 8;
    }

    private static int checkRemainingSizeAllowance(int remainingSizeAllowanceBytes, int sizeBytes) throws OutOfMemoryError {
        remainingSizeAllowanceBytes -= sizeBytes;
        if (remainingSizeAllowanceBytes >= 0) {
            return remainingSizeAllowanceBytes;
        }
        throw new OutOfMemoryError("Device data exceeds allowed storage for source");
    }

    private static Cursor getContactsCursor(ContentResolver resolver, String[] projection) {
        return resolver.query(Data.CONTENT_URI, projection, "in_visible_group=1 AND mimetype=?", new String[]{"vnd.android.cursor.item/name"}, "contact_id");
    }

    private static Cursor getAddressesCursor(ContentResolver resolver, String[] projection) {
        return resolver.query(StructuredPostal.CONTENT_URI, projection, "in_visible_group=1", null, null);
    }

    private synchronized boolean hasReadContactsPermission() {
        boolean z = true;
        synchronized (this) {
            if (!this.mQueriedReadContactsPermission) {
                this.mQueriedReadContactsPermission = true;
                if (this.mContext.checkCallingOrSelfPermission("android.permission.READ_CONTACTS") != 0) {
                    z = false;
                }
                this.mHasReadContactsPermission = z;
            }
            z = this.mHasReadContactsPermission;
        }
        return z;
    }
}
