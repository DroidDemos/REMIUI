package paymentfraud.mobile;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class DeviceFingerprinting extends MessageNano {
    public Parsed parsed;

    public static final class Parsed extends MessageNano {
        public Properties properties;
        public State state;

        public static final class Properties extends MessageNano {
            public String androidBuildBrand;
            public long androidId;
            public String buildFingerprint;
            public String deviceName;
            public String esn;
            public String imei;
            public String manufacturer;
            public String meid;
            public String modelName;
            public int operatingSystem;
            public String osVersion;
            public String phoneNumber;
            public String productName;

            public Properties() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.<init>();
                r0.clear();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties.<init>():void");
            }

            public paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties clear() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r2 = this;
                r0 = 100;
                r2.operatingSystem = r0;
                r0 = "";
                r2.imei = r0;
                r0 = "";
                r2.meid = r0;
                r0 = "";
                r2.esn = r0;
                r0 = "";
                r2.phoneNumber = r0;
                r0 = 0;
                r2.androidId = r0;
                r0 = "";
                r2.deviceName = r0;
                r0 = "";
                r2.productName = r0;
                r0 = "";
                r2.modelName = r0;
                r0 = "";
                r2.manufacturer = r0;
                r0 = "";
                r2.buildFingerprint = r0;
                r0 = "";
                r2.osVersion = r0;
                r0 = "";
                r2.androidBuildBrand = r0;
                r0 = -1;
                r2.cachedSize = r0;
                return r2;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties.clear():paymentfraud.mobile.DeviceFingerprinting$Parsed$Properties");
            }

            public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r5) throws java.io.IOException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r4 = this;
                r0 = r4.operatingSystem;
                r1 = 100;
                if (r0 == r1) goto L_0x000c;
            L_0x0006:
                r0 = 1;
                r1 = r4.operatingSystem;
                r5.writeInt32(r0, r1);
            L_0x000c:
                r0 = r4.imei;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x001c;
            L_0x0016:
                r0 = 2;
                r1 = r4.imei;
                r5.writeString(r0, r1);
            L_0x001c:
                r0 = r4.meid;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x002c;
            L_0x0026:
                r0 = 3;
                r1 = r4.meid;
                r5.writeString(r0, r1);
            L_0x002c:
                r0 = r4.esn;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x003c;
            L_0x0036:
                r0 = 5;
                r1 = r4.esn;
                r5.writeString(r0, r1);
            L_0x003c:
                r0 = r4.phoneNumber;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x004c;
            L_0x0046:
                r0 = 6;
                r1 = r4.phoneNumber;
                r5.writeString(r0, r1);
            L_0x004c:
                r0 = r4.androidId;
                r2 = 0;
                r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                if (r0 == 0) goto L_0x005a;
            L_0x0054:
                r0 = 7;
                r2 = r4.androidId;
                r5.writeInt64(r0, r2);
            L_0x005a:
                r0 = r4.deviceName;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x006b;
            L_0x0064:
                r0 = 9;
                r1 = r4.deviceName;
                r5.writeString(r0, r1);
            L_0x006b:
                r0 = r4.productName;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x007c;
            L_0x0075:
                r0 = 10;
                r1 = r4.productName;
                r5.writeString(r0, r1);
            L_0x007c:
                r0 = r4.modelName;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x008d;
            L_0x0086:
                r0 = 11;
                r1 = r4.modelName;
                r5.writeString(r0, r1);
            L_0x008d:
                r0 = r4.manufacturer;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x009e;
            L_0x0097:
                r0 = 12;
                r1 = r4.manufacturer;
                r5.writeString(r0, r1);
            L_0x009e:
                r0 = r4.buildFingerprint;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x00af;
            L_0x00a8:
                r0 = 13;
                r1 = r4.buildFingerprint;
                r5.writeString(r0, r1);
            L_0x00af:
                r0 = r4.osVersion;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x00c0;
            L_0x00b9:
                r0 = 15;
                r1 = r4.osVersion;
                r5.writeString(r0, r1);
            L_0x00c0:
                r0 = r4.androidBuildBrand;
                r1 = "";
                r0 = r0.equals(r1);
                if (r0 != 0) goto L_0x00d1;
            L_0x00ca:
                r0 = 21;
                r1 = r4.androidBuildBrand;
                r5.writeString(r0, r1);
            L_0x00d1:
                super.writeTo(r5);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
            }

            protected int computeSerializedSize() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r6 = this;
                r0 = super.computeSerializedSize();
                r1 = r6.operatingSystem;
                r2 = 100;
                if (r1 == r2) goto L_0x0012;
            L_0x000a:
                r1 = 1;
                r2 = r6.operatingSystem;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt32Size(r1, r2);
                r0 = r0 + r1;
            L_0x0012:
                r1 = r6.imei;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x0024;
            L_0x001c:
                r1 = 2;
                r2 = r6.imei;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x0024:
                r1 = r6.meid;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x0036;
            L_0x002e:
                r1 = 3;
                r2 = r6.meid;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x0036:
                r1 = r6.esn;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x0048;
            L_0x0040:
                r1 = 5;
                r2 = r6.esn;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x0048:
                r1 = r6.phoneNumber;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x005a;
            L_0x0052:
                r1 = 6;
                r2 = r6.phoneNumber;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x005a:
                r2 = r6.androidId;
                r4 = 0;
                r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r1 == 0) goto L_0x006a;
            L_0x0062:
                r1 = 7;
                r2 = r6.androidId;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt64Size(r1, r2);
                r0 = r0 + r1;
            L_0x006a:
                r1 = r6.deviceName;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x007d;
            L_0x0074:
                r1 = 9;
                r2 = r6.deviceName;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x007d:
                r1 = r6.productName;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x0090;
            L_0x0087:
                r1 = 10;
                r2 = r6.productName;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x0090:
                r1 = r6.modelName;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x00a3;
            L_0x009a:
                r1 = 11;
                r2 = r6.modelName;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x00a3:
                r1 = r6.manufacturer;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x00b6;
            L_0x00ad:
                r1 = 12;
                r2 = r6.manufacturer;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x00b6:
                r1 = r6.buildFingerprint;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x00c9;
            L_0x00c0:
                r1 = 13;
                r2 = r6.buildFingerprint;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x00c9:
                r1 = r6.osVersion;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x00dc;
            L_0x00d3:
                r1 = 15;
                r2 = r6.osVersion;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x00dc:
                r1 = r6.androidBuildBrand;
                r2 = "";
                r1 = r1.equals(r2);
                if (r1 != 0) goto L_0x00ef;
            L_0x00e6:
                r1 = 21;
                r2 = r6.androidBuildBrand;
                r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                r0 = r0 + r1;
            L_0x00ef:
                return r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties.computeSerializedSize():int");
            }

            public paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r5) throws java.io.IOException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r4 = this;
            L_0x0000:
                r0 = r5.readTag();
                switch(r0) {
                    case 0: goto L_0x000d;
                    case 8: goto L_0x000e;
                    case 18: goto L_0x0019;
                    case 26: goto L_0x0020;
                    case 42: goto L_0x0027;
                    case 50: goto L_0x002e;
                    case 56: goto L_0x0035;
                    case 74: goto L_0x003c;
                    case 82: goto L_0x0043;
                    case 90: goto L_0x004a;
                    case 98: goto L_0x0051;
                    case 106: goto L_0x0058;
                    case 122: goto L_0x005f;
                    case 170: goto L_0x0066;
                    default: goto L_0x0007;
                };
            L_0x0007:
                r2 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r5, r0);
                if (r2 != 0) goto L_0x0000;
            L_0x000d:
                return r4;
            L_0x000e:
                r1 = r5.readInt32();
                switch(r1) {
                    case 0: goto L_0x0016;
                    case 100: goto L_0x0016;
                    default: goto L_0x0015;
                };
            L_0x0015:
                goto L_0x0000;
            L_0x0016:
                r4.operatingSystem = r1;
                goto L_0x0000;
            L_0x0019:
                r2 = r5.readString();
                r4.imei = r2;
                goto L_0x0000;
            L_0x0020:
                r2 = r5.readString();
                r4.meid = r2;
                goto L_0x0000;
            L_0x0027:
                r2 = r5.readString();
                r4.esn = r2;
                goto L_0x0000;
            L_0x002e:
                r2 = r5.readString();
                r4.phoneNumber = r2;
                goto L_0x0000;
            L_0x0035:
                r2 = r5.readInt64();
                r4.androidId = r2;
                goto L_0x0000;
            L_0x003c:
                r2 = r5.readString();
                r4.deviceName = r2;
                goto L_0x0000;
            L_0x0043:
                r2 = r5.readString();
                r4.productName = r2;
                goto L_0x0000;
            L_0x004a:
                r2 = r5.readString();
                r4.modelName = r2;
                goto L_0x0000;
            L_0x0051:
                r2 = r5.readString();
                r4.manufacturer = r2;
                goto L_0x0000;
            L_0x0058:
                r2 = r5.readString();
                r4.buildFingerprint = r2;
                goto L_0x0000;
            L_0x005f:
                r2 = r5.readString();
                r4.osVersion = r2;
                goto L_0x0000;
            L_0x0066:
                r2 = r5.readString();
                r4.androidBuildBrand = r2;
                goto L_0x0000;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):paymentfraud.mobile.DeviceFingerprinting$Parsed$Properties");
            }
        }

        public static final class State extends MessageNano {
            public String cellOperator;
            public boolean devModeOn;
            public long gmtOffsetMillis;
            public PackageInfo[] installedPackages;
            public String[] ipAddr;
            public String language;
            public Location lastGpsLocation;
            public String locale;
            public boolean nonPlayInstallAllowed;
            public int percentBattery;
            public String simOperator;

            public static final class Location extends MessageNano {
                public float accuracy;
                public double altitude;
                public double latitude;
                public double longitude;
                public double timeInMs;

                public Location() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    r0.clear();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location.<init>():void");
                }

                public paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location clear() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r2 = this;
                    r0 = 0;
                    r2.altitude = r0;
                    r2.latitude = r0;
                    r2.longitude = r0;
                    r0 = 0;
                    r2.accuracy = r0;
                    r0 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
                    r2.timeInMs = r0;
                    r0 = -1;
                    r2.cachedSize = r0;
                    return r2;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location.clear():paymentfraud.mobile.DeviceFingerprinting$Parsed$State$Location");
                }

                public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r7) throws java.io.IOException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r6 = this;
                    r4 = 0;
                    r0 = r6.altitude;
                    r0 = java.lang.Double.doubleToLongBits(r0);
                    r2 = java.lang.Double.doubleToLongBits(r4);
                    r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                    if (r0 == 0) goto L_0x0016;
                L_0x0010:
                    r0 = 1;
                    r2 = r6.altitude;
                    r7.writeDouble(r0, r2);
                L_0x0016:
                    r0 = r6.latitude;
                    r0 = java.lang.Double.doubleToLongBits(r0);
                    r2 = java.lang.Double.doubleToLongBits(r4);
                    r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                    if (r0 == 0) goto L_0x002a;
                L_0x0024:
                    r0 = 2;
                    r2 = r6.latitude;
                    r7.writeDouble(r0, r2);
                L_0x002a:
                    r0 = r6.longitude;
                    r0 = java.lang.Double.doubleToLongBits(r0);
                    r2 = java.lang.Double.doubleToLongBits(r4);
                    r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                    if (r0 == 0) goto L_0x003e;
                L_0x0038:
                    r0 = 3;
                    r2 = r6.longitude;
                    r7.writeDouble(r0, r2);
                L_0x003e:
                    r0 = r6.accuracy;
                    r0 = java.lang.Float.floatToIntBits(r0);
                    r1 = 0;
                    r1 = java.lang.Float.floatToIntBits(r1);
                    if (r0 == r1) goto L_0x0051;
                L_0x004b:
                    r0 = 4;
                    r1 = r6.accuracy;
                    r7.writeFloat(r0, r1);
                L_0x0051:
                    r0 = r6.timeInMs;
                    r0 = java.lang.Double.doubleToLongBits(r0);
                    r2 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
                    r2 = java.lang.Double.doubleToLongBits(r2);
                    r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                    if (r0 == 0) goto L_0x0067;
                L_0x0061:
                    r0 = 5;
                    r2 = r6.timeInMs;
                    r7.writeDouble(r0, r2);
                L_0x0067:
                    super.writeTo(r7);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
                }

                protected int computeSerializedSize() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r8 = this;
                    r6 = 0;
                    r0 = super.computeSerializedSize();
                    r2 = r8.altitude;
                    r2 = java.lang.Double.doubleToLongBits(r2);
                    r4 = java.lang.Double.doubleToLongBits(r6);
                    r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                    if (r1 == 0) goto L_0x001c;
                L_0x0014:
                    r1 = 1;
                    r2 = r8.altitude;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeDoubleSize(r1, r2);
                    r0 = r0 + r1;
                L_0x001c:
                    r2 = r8.latitude;
                    r2 = java.lang.Double.doubleToLongBits(r2);
                    r4 = java.lang.Double.doubleToLongBits(r6);
                    r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                    if (r1 == 0) goto L_0x0032;
                L_0x002a:
                    r1 = 2;
                    r2 = r8.latitude;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeDoubleSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0032:
                    r2 = r8.longitude;
                    r2 = java.lang.Double.doubleToLongBits(r2);
                    r4 = java.lang.Double.doubleToLongBits(r6);
                    r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                    if (r1 == 0) goto L_0x0048;
                L_0x0040:
                    r1 = 3;
                    r2 = r8.longitude;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeDoubleSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0048:
                    r1 = r8.accuracy;
                    r1 = java.lang.Float.floatToIntBits(r1);
                    r2 = 0;
                    r2 = java.lang.Float.floatToIntBits(r2);
                    if (r1 == r2) goto L_0x005d;
                L_0x0055:
                    r1 = 4;
                    r2 = r8.accuracy;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeFloatSize(r1, r2);
                    r0 = r0 + r1;
                L_0x005d:
                    r2 = r8.timeInMs;
                    r2 = java.lang.Double.doubleToLongBits(r2);
                    r4 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
                    r4 = java.lang.Double.doubleToLongBits(r4);
                    r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                    if (r1 == 0) goto L_0x0075;
                L_0x006d:
                    r1 = 5;
                    r2 = r8.timeInMs;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeDoubleSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0075:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location.computeSerializedSize():int");
                }

                public paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r5) throws java.io.IOException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r4 = this;
                L_0x0000:
                    r0 = r5.readTag();
                    switch(r0) {
                        case 0: goto L_0x000d;
                        case 9: goto L_0x000e;
                        case 17: goto L_0x0015;
                        case 25: goto L_0x001c;
                        case 37: goto L_0x0023;
                        case 41: goto L_0x002a;
                        default: goto L_0x0007;
                    };
                L_0x0007:
                    r1 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r5, r0);
                    if (r1 != 0) goto L_0x0000;
                L_0x000d:
                    return r4;
                L_0x000e:
                    r2 = r5.readDouble();
                    r4.altitude = r2;
                    goto L_0x0000;
                L_0x0015:
                    r2 = r5.readDouble();
                    r4.latitude = r2;
                    goto L_0x0000;
                L_0x001c:
                    r2 = r5.readDouble();
                    r4.longitude = r2;
                    goto L_0x0000;
                L_0x0023:
                    r1 = r5.readFloat();
                    r4.accuracy = r1;
                    goto L_0x0000;
                L_0x002a:
                    r2 = r5.readDouble();
                    r4.timeInMs = r2;
                    goto L_0x0000;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.Location.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):paymentfraud.mobile.DeviceFingerprinting$Parsed$State$Location");
                }
            }

            public static final class PackageInfo extends MessageNano {
                private static volatile PackageInfo[] _emptyArray;
                public long firstInstallTime;
                public String installLocation;
                public long lastUpdateTime;
                public String name;
                public String versionCode;

                public static paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo[] emptyArray() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = _emptyArray;
                    if (r0 != 0) goto L_0x0011;
                L_0x0004:
                    r1 = com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK;
                    monitor-enter(r1);
                    r0 = _emptyArray;
                    if (r0 != 0) goto L_0x0010;
                L_0x000b:
                    r0 = 0;
                    r0 = new paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo[r0];
                    _emptyArray = r0;
                L_0x0010:
                    monitor-exit(r1);
                L_0x0011:
                    r0 = _emptyArray;
                    return r0;
                L_0x0014:
                    r0 = move-exception;
                    monitor-exit(r1);
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.emptyArray():paymentfraud.mobile.DeviceFingerprinting$Parsed$State$PackageInfo[]");
                }

                public PackageInfo() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    r0.clear();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.<init>():void");
                }

                public paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo clear() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r4 = this;
                    r2 = -1;
                    r0 = "";
                    r4.name = r0;
                    r0 = "";
                    r4.versionCode = r0;
                    r4.lastUpdateTime = r2;
                    r4.firstInstallTime = r2;
                    r0 = "";
                    r4.installLocation = r0;
                    r0 = -1;
                    r4.cachedSize = r0;
                    return r4;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.clear():paymentfraud.mobile.DeviceFingerprinting$Parsed$State$PackageInfo");
                }

                public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r7) throws java.io.IOException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r6 = this;
                    r4 = -1;
                    r0 = r6.name;
                    r1 = "";
                    r0 = r0.equals(r1);
                    if (r0 != 0) goto L_0x0012;
                L_0x000c:
                    r0 = 1;
                    r1 = r6.name;
                    r7.writeString(r0, r1);
                L_0x0012:
                    r0 = r6.versionCode;
                    r1 = "";
                    r0 = r0.equals(r1);
                    if (r0 != 0) goto L_0x0022;
                L_0x001c:
                    r0 = 2;
                    r1 = r6.versionCode;
                    r7.writeString(r0, r1);
                L_0x0022:
                    r0 = r6.lastUpdateTime;
                    r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
                    if (r0 == 0) goto L_0x002e;
                L_0x0028:
                    r0 = 3;
                    r2 = r6.lastUpdateTime;
                    r7.writeInt64(r0, r2);
                L_0x002e:
                    r0 = r6.firstInstallTime;
                    r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
                    if (r0 == 0) goto L_0x003a;
                L_0x0034:
                    r0 = 4;
                    r2 = r6.firstInstallTime;
                    r7.writeInt64(r0, r2);
                L_0x003a:
                    r0 = r6.installLocation;
                    r1 = "";
                    r0 = r0.equals(r1);
                    if (r0 != 0) goto L_0x004a;
                L_0x0044:
                    r0 = 5;
                    r1 = r6.installLocation;
                    r7.writeString(r0, r1);
                L_0x004a:
                    super.writeTo(r7);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
                }

                protected int computeSerializedSize() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r6 = this;
                    r4 = -1;
                    r0 = super.computeSerializedSize();
                    r1 = r6.name;
                    r2 = "";
                    r1 = r1.equals(r2);
                    if (r1 != 0) goto L_0x0018;
                L_0x0010:
                    r1 = 1;
                    r2 = r6.name;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0018:
                    r1 = r6.versionCode;
                    r2 = "";
                    r1 = r1.equals(r2);
                    if (r1 != 0) goto L_0x002a;
                L_0x0022:
                    r1 = 2;
                    r2 = r6.versionCode;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                    r0 = r0 + r1;
                L_0x002a:
                    r2 = r6.lastUpdateTime;
                    r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                    if (r1 == 0) goto L_0x0038;
                L_0x0030:
                    r1 = 3;
                    r2 = r6.lastUpdateTime;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt64Size(r1, r2);
                    r0 = r0 + r1;
                L_0x0038:
                    r2 = r6.firstInstallTime;
                    r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                    if (r1 == 0) goto L_0x0046;
                L_0x003e:
                    r1 = 4;
                    r2 = r6.firstInstallTime;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt64Size(r1, r2);
                    r0 = r0 + r1;
                L_0x0046:
                    r1 = r6.installLocation;
                    r2 = "";
                    r1 = r1.equals(r2);
                    if (r1 != 0) goto L_0x0058;
                L_0x0050:
                    r1 = 5;
                    r2 = r6.installLocation;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0058:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.computeSerializedSize():int");
                }

                public paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r5) throws java.io.IOException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r4 = this;
                L_0x0000:
                    r0 = r5.readTag();
                    switch(r0) {
                        case 0: goto L_0x000d;
                        case 10: goto L_0x000e;
                        case 18: goto L_0x0015;
                        case 24: goto L_0x001c;
                        case 32: goto L_0x0023;
                        case 42: goto L_0x002a;
                        default: goto L_0x0007;
                    };
                L_0x0007:
                    r1 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r5, r0);
                    if (r1 != 0) goto L_0x0000;
                L_0x000d:
                    return r4;
                L_0x000e:
                    r1 = r5.readString();
                    r4.name = r1;
                    goto L_0x0000;
                L_0x0015:
                    r1 = r5.readString();
                    r4.versionCode = r1;
                    goto L_0x0000;
                L_0x001c:
                    r2 = r5.readInt64();
                    r4.lastUpdateTime = r2;
                    goto L_0x0000;
                L_0x0023:
                    r2 = r5.readInt64();
                    r4.firstInstallTime = r2;
                    goto L_0x0000;
                L_0x002a:
                    r1 = r5.readString();
                    r4.installLocation = r1;
                    goto L_0x0000;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):paymentfraud.mobile.DeviceFingerprinting$Parsed$State$PackageInfo");
                }
            }

            public State() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.<init>();
                r0.clear();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.<init>():void");
            }

            public paymentfraud.mobile.DeviceFingerprinting.Parsed.State clear() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r4 = this;
                r3 = 0;
                r2 = -1;
                r0 = paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo.emptyArray();
                r4.installedPackages = r0;
                r4.percentBattery = r2;
                r0 = -86400000; // 0xfffffffffad9a400 float:-5.6502737E35 double:NaN;
                r4.gmtOffsetMillis = r0;
                r0 = 0;
                r4.lastGpsLocation = r0;
                r4.devModeOn = r3;
                r4.nonPlayInstallAllowed = r3;
                r0 = "";
                r4.language = r0;
                r0 = com.google.protobuf.nano.WireFormatNano.EMPTY_STRING_ARRAY;
                r4.ipAddr = r0;
                r0 = "";
                r4.locale = r0;
                r0 = "";
                r4.cellOperator = r0;
                r0 = "";
                r4.simOperator = r0;
                r4.cachedSize = r2;
                return r4;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.clear():paymentfraud.mobile.DeviceFingerprinting$Parsed$State");
            }

            public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r7) throws java.io.IOException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r6 = this;
                r2 = r6.installedPackages;
                if (r2 == 0) goto L_0x001c;
            L_0x0004:
                r2 = r6.installedPackages;
                r2 = r2.length;
                if (r2 <= 0) goto L_0x001c;
            L_0x0009:
                r1 = 0;
            L_0x000a:
                r2 = r6.installedPackages;
                r2 = r2.length;
                if (r1 >= r2) goto L_0x001c;
            L_0x000f:
                r2 = r6.installedPackages;
                r0 = r2[r1];
                if (r0 == 0) goto L_0x0019;
            L_0x0015:
                r2 = 1;
                r7.writeMessage(r2, r0);
            L_0x0019:
                r1 = r1 + 1;
                goto L_0x000a;
            L_0x001c:
                r2 = r6.percentBattery;
                r3 = -1;
                if (r2 == r3) goto L_0x0027;
            L_0x0021:
                r2 = 3;
                r3 = r6.percentBattery;
                r7.writeInt32(r2, r3);
            L_0x0027:
                r2 = r6.gmtOffsetMillis;
                r4 = -86400000; // 0xfffffffffad9a400 float:-5.6502737E35 double:NaN;
                r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
                if (r2 == 0) goto L_0x0036;
            L_0x0030:
                r2 = 4;
                r4 = r6.gmtOffsetMillis;
                r7.writeInt64(r2, r4);
            L_0x0036:
                r2 = r6.lastGpsLocation;
                if (r2 == 0) goto L_0x0040;
            L_0x003a:
                r2 = 6;
                r3 = r6.lastGpsLocation;
                r7.writeMessage(r2, r3);
            L_0x0040:
                r2 = r6.devModeOn;
                if (r2 == 0) goto L_0x004a;
            L_0x0044:
                r2 = 7;
                r3 = r6.devModeOn;
                r7.writeBool(r2, r3);
            L_0x004a:
                r2 = r6.nonPlayInstallAllowed;
                if (r2 == 0) goto L_0x0055;
            L_0x004e:
                r2 = 8;
                r3 = r6.nonPlayInstallAllowed;
                r7.writeBool(r2, r3);
            L_0x0055:
                r2 = r6.language;
                r3 = "";
                r2 = r2.equals(r3);
                if (r2 != 0) goto L_0x0066;
            L_0x005f:
                r2 = 9;
                r3 = r6.language;
                r7.writeString(r2, r3);
            L_0x0066:
                r2 = r6.ipAddr;
                if (r2 == 0) goto L_0x0083;
            L_0x006a:
                r2 = r6.ipAddr;
                r2 = r2.length;
                if (r2 <= 0) goto L_0x0083;
            L_0x006f:
                r1 = 0;
            L_0x0070:
                r2 = r6.ipAddr;
                r2 = r2.length;
                if (r1 >= r2) goto L_0x0083;
            L_0x0075:
                r2 = r6.ipAddr;
                r0 = r2[r1];
                if (r0 == 0) goto L_0x0080;
            L_0x007b:
                r2 = 10;
                r7.writeString(r2, r0);
            L_0x0080:
                r1 = r1 + 1;
                goto L_0x0070;
            L_0x0083:
                r2 = r6.locale;
                r3 = "";
                r2 = r2.equals(r3);
                if (r2 != 0) goto L_0x0094;
            L_0x008d:
                r2 = 11;
                r3 = r6.locale;
                r7.writeString(r2, r3);
            L_0x0094:
                r2 = r6.cellOperator;
                r3 = "";
                r2 = r2.equals(r3);
                if (r2 != 0) goto L_0x00a5;
            L_0x009e:
                r2 = 14;
                r3 = r6.cellOperator;
                r7.writeString(r2, r3);
            L_0x00a5:
                r2 = r6.simOperator;
                r3 = "";
                r2 = r2.equals(r3);
                if (r2 != 0) goto L_0x00b6;
            L_0x00af:
                r2 = 15;
                r3 = r6.simOperator;
                r7.writeString(r2, r3);
            L_0x00b6:
                super.writeTo(r7);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
            }

            protected int computeSerializedSize() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r10 = this;
                r4 = super.computeSerializedSize();
                r5 = r10.installedPackages;
                if (r5 == 0) goto L_0x0022;
            L_0x0008:
                r5 = r10.installedPackages;
                r5 = r5.length;
                if (r5 <= 0) goto L_0x0022;
            L_0x000d:
                r3 = 0;
            L_0x000e:
                r5 = r10.installedPackages;
                r5 = r5.length;
                if (r3 >= r5) goto L_0x0022;
            L_0x0013:
                r5 = r10.installedPackages;
                r2 = r5[r3];
                if (r2 == 0) goto L_0x001f;
            L_0x0019:
                r5 = 1;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r5, r2);
                r4 = r4 + r5;
            L_0x001f:
                r3 = r3 + 1;
                goto L_0x000e;
            L_0x0022:
                r5 = r10.percentBattery;
                r6 = -1;
                if (r5 == r6) goto L_0x002f;
            L_0x0027:
                r5 = 3;
                r6 = r10.percentBattery;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt32Size(r5, r6);
                r4 = r4 + r5;
            L_0x002f:
                r6 = r10.gmtOffsetMillis;
                r8 = -86400000; // 0xfffffffffad9a400 float:-5.6502737E35 double:NaN;
                r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
                if (r5 == 0) goto L_0x0040;
            L_0x0038:
                r5 = 4;
                r6 = r10.gmtOffsetMillis;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt64Size(r5, r6);
                r4 = r4 + r5;
            L_0x0040:
                r5 = r10.lastGpsLocation;
                if (r5 == 0) goto L_0x004c;
            L_0x0044:
                r5 = 6;
                r6 = r10.lastGpsLocation;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r5, r6);
                r4 = r4 + r5;
            L_0x004c:
                r5 = r10.devModeOn;
                if (r5 == 0) goto L_0x0058;
            L_0x0050:
                r5 = 7;
                r6 = r10.devModeOn;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeBoolSize(r5, r6);
                r4 = r4 + r5;
            L_0x0058:
                r5 = r10.nonPlayInstallAllowed;
                if (r5 == 0) goto L_0x0065;
            L_0x005c:
                r5 = 8;
                r6 = r10.nonPlayInstallAllowed;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeBoolSize(r5, r6);
                r4 = r4 + r5;
            L_0x0065:
                r5 = r10.language;
                r6 = "";
                r5 = r5.equals(r6);
                if (r5 != 0) goto L_0x0078;
            L_0x006f:
                r5 = 9;
                r6 = r10.language;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r5, r6);
                r4 = r4 + r5;
            L_0x0078:
                r5 = r10.ipAddr;
                if (r5 == 0) goto L_0x009d;
            L_0x007c:
                r5 = r10.ipAddr;
                r5 = r5.length;
                if (r5 <= 0) goto L_0x009d;
            L_0x0081:
                r0 = 0;
                r1 = 0;
                r3 = 0;
            L_0x0084:
                r5 = r10.ipAddr;
                r5 = r5.length;
                if (r3 >= r5) goto L_0x0099;
            L_0x0089:
                r5 = r10.ipAddr;
                r2 = r5[r3];
                if (r2 == 0) goto L_0x0096;
            L_0x008f:
                r0 = r0 + 1;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSizeNoTag(r2);
                r1 = r1 + r5;
            L_0x0096:
                r3 = r3 + 1;
                goto L_0x0084;
            L_0x0099:
                r4 = r4 + r1;
                r5 = r0 * 1;
                r4 = r4 + r5;
            L_0x009d:
                r5 = r10.locale;
                r6 = "";
                r5 = r5.equals(r6);
                if (r5 != 0) goto L_0x00b0;
            L_0x00a7:
                r5 = 11;
                r6 = r10.locale;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r5, r6);
                r4 = r4 + r5;
            L_0x00b0:
                r5 = r10.cellOperator;
                r6 = "";
                r5 = r5.equals(r6);
                if (r5 != 0) goto L_0x00c3;
            L_0x00ba:
                r5 = 14;
                r6 = r10.cellOperator;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r5, r6);
                r4 = r4 + r5;
            L_0x00c3:
                r5 = r10.simOperator;
                r6 = "";
                r5 = r5.equals(r6);
                if (r5 != 0) goto L_0x00d6;
            L_0x00cd:
                r5 = 15;
                r6 = r10.simOperator;
                r5 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r5, r6);
                r4 = r4 + r5;
            L_0x00d6:
                return r4;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.computeSerializedSize():int");
            }

            public paymentfraud.mobile.DeviceFingerprinting.Parsed.State mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r9) throws java.io.IOException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r8 = this;
                r4 = 0;
            L_0x0001:
                r3 = r9.readTag();
                switch(r3) {
                    case 0: goto L_0x000e;
                    case 10: goto L_0x000f;
                    case 24: goto L_0x004f;
                    case 32: goto L_0x0056;
                    case 50: goto L_0x005d;
                    case 56: goto L_0x006e;
                    case 64: goto L_0x0075;
                    case 74: goto L_0x007c;
                    case 82: goto L_0x0084;
                    case 90: goto L_0x00b9;
                    case 114: goto L_0x00c1;
                    case 122: goto L_0x00c9;
                    default: goto L_0x0008;
                };
            L_0x0008:
                r5 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r9, r3);
                if (r5 != 0) goto L_0x0001;
            L_0x000e:
                return r8;
            L_0x000f:
                r5 = 10;
                r0 = com.google.protobuf.nano.WireFormatNano.getRepeatedFieldArrayLength(r9, r5);
                r5 = r8.installedPackages;
                if (r5 != 0) goto L_0x003c;
            L_0x0019:
                r1 = r4;
            L_0x001a:
                r5 = r1 + r0;
                r2 = new paymentfraud.mobile.DeviceFingerprinting.Parsed.State.PackageInfo[r5];
                if (r1 == 0) goto L_0x0025;
            L_0x0020:
                r5 = r8.installedPackages;
                java.lang.System.arraycopy(r5, r4, r2, r4, r1);
            L_0x0025:
                r5 = r2.length;
                r5 = r5 + -1;
                if (r1 >= r5) goto L_0x0040;
            L_0x002a:
                r5 = new paymentfraud.mobile.DeviceFingerprinting$Parsed$State$PackageInfo;
                r5.<init>();
                r2[r1] = r5;
                r5 = r2[r1];
                r9.readMessage(r5);
                r9.readTag();
                r1 = r1 + 1;
                goto L_0x0025;
            L_0x003c:
                r5 = r8.installedPackages;
                r1 = r5.length;
                goto L_0x001a;
            L_0x0040:
                r5 = new paymentfraud.mobile.DeviceFingerprinting$Parsed$State$PackageInfo;
                r5.<init>();
                r2[r1] = r5;
                r5 = r2[r1];
                r9.readMessage(r5);
                r8.installedPackages = r2;
                goto L_0x0001;
            L_0x004f:
                r5 = r9.readInt32();
                r8.percentBattery = r5;
                goto L_0x0001;
            L_0x0056:
                r6 = r9.readInt64();
                r8.gmtOffsetMillis = r6;
                goto L_0x0001;
            L_0x005d:
                r5 = r8.lastGpsLocation;
                if (r5 != 0) goto L_0x0068;
            L_0x0061:
                r5 = new paymentfraud.mobile.DeviceFingerprinting$Parsed$State$Location;
                r5.<init>();
                r8.lastGpsLocation = r5;
            L_0x0068:
                r5 = r8.lastGpsLocation;
                r9.readMessage(r5);
                goto L_0x0001;
            L_0x006e:
                r5 = r9.readBool();
                r8.devModeOn = r5;
                goto L_0x0001;
            L_0x0075:
                r5 = r9.readBool();
                r8.nonPlayInstallAllowed = r5;
                goto L_0x0001;
            L_0x007c:
                r5 = r9.readString();
                r8.language = r5;
                goto L_0x0001;
            L_0x0084:
                r5 = 82;
                r0 = com.google.protobuf.nano.WireFormatNano.getRepeatedFieldArrayLength(r9, r5);
                r5 = r8.ipAddr;
                if (r5 != 0) goto L_0x00ab;
            L_0x008e:
                r1 = r4;
            L_0x008f:
                r5 = r1 + r0;
                r2 = new java.lang.String[r5];
                if (r1 == 0) goto L_0x009a;
            L_0x0095:
                r5 = r8.ipAddr;
                java.lang.System.arraycopy(r5, r4, r2, r4, r1);
            L_0x009a:
                r5 = r2.length;
                r5 = r5 + -1;
                if (r1 >= r5) goto L_0x00af;
            L_0x009f:
                r5 = r9.readString();
                r2[r1] = r5;
                r9.readTag();
                r1 = r1 + 1;
                goto L_0x009a;
            L_0x00ab:
                r5 = r8.ipAddr;
                r1 = r5.length;
                goto L_0x008f;
            L_0x00af:
                r5 = r9.readString();
                r2[r1] = r5;
                r8.ipAddr = r2;
                goto L_0x0001;
            L_0x00b9:
                r5 = r9.readString();
                r8.locale = r5;
                goto L_0x0001;
            L_0x00c1:
                r5 = r9.readString();
                r8.cellOperator = r5;
                goto L_0x0001;
            L_0x00c9:
                r5 = r9.readString();
                r8.simOperator = r5;
                goto L_0x0001;
                */
                throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.State.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):paymentfraud.mobile.DeviceFingerprinting$Parsed$State");
            }
        }

        public Parsed() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r0 = this;
            r0.<init>();
            r0.clear();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.<init>():void");
        }

        public paymentfraud.mobile.DeviceFingerprinting.Parsed clear() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = 0;
            r1.properties = r0;
            r1.state = r0;
            r0 = -1;
            r1.cachedSize = r0;
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.clear():paymentfraud.mobile.DeviceFingerprinting$Parsed");
        }

        public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r3) throws java.io.IOException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r2 = this;
            r0 = r2.properties;
            if (r0 == 0) goto L_0x000a;
        L_0x0004:
            r0 = 1;
            r1 = r2.properties;
            r3.writeMessage(r0, r1);
        L_0x000a:
            r0 = r2.state;
            if (r0 == 0) goto L_0x0014;
        L_0x000e:
            r0 = 2;
            r1 = r2.state;
            r3.writeMessage(r0, r1);
        L_0x0014:
            super.writeTo(r3);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
        }

        protected int computeSerializedSize() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r3 = this;
            r0 = super.computeSerializedSize();
            r1 = r3.properties;
            if (r1 == 0) goto L_0x0010;
        L_0x0008:
            r1 = 1;
            r2 = r3.properties;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r1, r2);
            r0 = r0 + r1;
        L_0x0010:
            r1 = r3.state;
            if (r1 == 0) goto L_0x001c;
        L_0x0014:
            r1 = 2;
            r2 = r3.state;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r1, r2);
            r0 = r0 + r1;
        L_0x001c:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.computeSerializedSize():int");
        }

        public paymentfraud.mobile.DeviceFingerprinting.Parsed mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r3) throws java.io.IOException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r2 = this;
        L_0x0000:
            r0 = r3.readTag();
            switch(r0) {
                case 0: goto L_0x000d;
                case 10: goto L_0x000e;
                case 18: goto L_0x001f;
                default: goto L_0x0007;
            };
        L_0x0007:
            r1 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r3, r0);
            if (r1 != 0) goto L_0x0000;
        L_0x000d:
            return r2;
        L_0x000e:
            r1 = r2.properties;
            if (r1 != 0) goto L_0x0019;
        L_0x0012:
            r1 = new paymentfraud.mobile.DeviceFingerprinting$Parsed$Properties;
            r1.<init>();
            r2.properties = r1;
        L_0x0019:
            r1 = r2.properties;
            r3.readMessage(r1);
            goto L_0x0000;
        L_0x001f:
            r1 = r2.state;
            if (r1 != 0) goto L_0x002a;
        L_0x0023:
            r1 = new paymentfraud.mobile.DeviceFingerprinting$Parsed$State;
            r1.<init>();
            r2.state = r1;
        L_0x002a:
            r1 = r2.state;
            r3.readMessage(r1);
            goto L_0x0000;
            */
            throw new UnsupportedOperationException("Method not decompiled: paymentfraud.mobile.DeviceFingerprinting.Parsed.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):paymentfraud.mobile.DeviceFingerprinting$Parsed");
        }
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.parsed != null) {
            output.writeMessage(2, this.parsed);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.parsed != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(2, this.parsed);
        }
        return size;
    }

    public DeviceFingerprinting mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    break;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    if (this.parsed == null) {
                        this.parsed = new Parsed();
                    }
                    input.readMessage(this.parsed);
                    continue;
                default:
                    if (!WireFormatNano.parseUnknownField(input, tag)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }
}
