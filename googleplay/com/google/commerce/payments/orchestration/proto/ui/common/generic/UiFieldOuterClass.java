package com.google.commerce.payments.orchestration.proto.ui.common.generic;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface UiFieldOuterClass {

    public static final class UiField extends MessageNano {
        private static volatile UiField[] _emptyArray;
        public boolean isOptional;
        public String label;
        public String name;
        public TextField textField;

        public static final class TextField extends MessageNano {
            public String initialValue;
            public boolean isMasked;
            public int keyboardLayout;
            public int maxLength;
            public Validation[] validation;

            public static final class Validation extends MessageNano {
                private static volatile Validation[] _emptyArray;
                public String errorMessage;
                public String regex;

                public static com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation[] emptyArray() {
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
                    r0 = new com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation[r0];
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
                    throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.emptyArray():com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField$Validation[]");
                }

                public Validation() {
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
                    throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.<init>():void");
                }

                public com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation clear() {
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
                    r1 = this;
                    r0 = "";
                    r1.regex = r0;
                    r0 = "";
                    r1.errorMessage = r0;
                    r0 = -1;
                    r1.cachedSize = r0;
                    return r1;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.clear():com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField$Validation");
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
                    r0 = r2.regex;
                    r1 = "";
                    r0 = r0.equals(r1);
                    if (r0 != 0) goto L_0x0010;
                L_0x000a:
                    r0 = 1;
                    r1 = r2.regex;
                    r3.writeString(r0, r1);
                L_0x0010:
                    r0 = r2.errorMessage;
                    r1 = "";
                    r0 = r0.equals(r1);
                    if (r0 != 0) goto L_0x0020;
                L_0x001a:
                    r0 = 2;
                    r1 = r2.errorMessage;
                    r3.writeString(r0, r1);
                L_0x0020:
                    super.writeTo(r3);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
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
                    r3 = this;
                    r0 = super.computeSerializedSize();
                    r1 = r3.regex;
                    r2 = "";
                    r1 = r1.equals(r2);
                    if (r1 != 0) goto L_0x0016;
                L_0x000e:
                    r1 = 1;
                    r2 = r3.regex;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0016:
                    r1 = r3.errorMessage;
                    r2 = "";
                    r1 = r1.equals(r2);
                    if (r1 != 0) goto L_0x0028;
                L_0x0020:
                    r1 = 2;
                    r2 = r3.errorMessage;
                    r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
                    r0 = r0 + r1;
                L_0x0028:
                    return r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.computeSerializedSize():int");
                }

                public com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r3) throws java.io.IOException {
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
                L_0x0000:
                    r0 = r3.readTag();
                    switch(r0) {
                        case 0: goto L_0x000d;
                        case 10: goto L_0x000e;
                        case 18: goto L_0x0015;
                        default: goto L_0x0007;
                    };
                L_0x0007:
                    r1 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r3, r0);
                    if (r1 != 0) goto L_0x0000;
                L_0x000d:
                    return r2;
                L_0x000e:
                    r1 = r3.readString();
                    r2.regex = r1;
                    goto L_0x0000;
                L_0x0015:
                    r1 = r3.readString();
                    r2.errorMessage = r1;
                    goto L_0x0000;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField$Validation");
                }
            }

            public TextField() {
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.<init>():void");
            }

            public com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField clear() {
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
                r1 = 0;
                r2.maxLength = r1;
                r2.keyboardLayout = r1;
                r0 = com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation.emptyArray();
                r2.validation = r0;
                r0 = "";
                r2.initialValue = r0;
                r2.isMasked = r1;
                r0 = -1;
                r2.cachedSize = r0;
                return r2;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.clear():com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField");
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
                r2 = r4.maxLength;
                if (r2 == 0) goto L_0x000a;
            L_0x0004:
                r2 = 2;
                r3 = r4.maxLength;
                r5.writeInt32(r2, r3);
            L_0x000a:
                r2 = r4.keyboardLayout;
                if (r2 == 0) goto L_0x0014;
            L_0x000e:
                r2 = 4;
                r3 = r4.keyboardLayout;
                r5.writeInt32(r2, r3);
            L_0x0014:
                r2 = r4.validation;
                if (r2 == 0) goto L_0x0030;
            L_0x0018:
                r2 = r4.validation;
                r2 = r2.length;
                if (r2 <= 0) goto L_0x0030;
            L_0x001d:
                r1 = 0;
            L_0x001e:
                r2 = r4.validation;
                r2 = r2.length;
                if (r1 >= r2) goto L_0x0030;
            L_0x0023:
                r2 = r4.validation;
                r0 = r2[r1];
                if (r0 == 0) goto L_0x002d;
            L_0x0029:
                r2 = 5;
                r5.writeMessage(r2, r0);
            L_0x002d:
                r1 = r1 + 1;
                goto L_0x001e;
            L_0x0030:
                r2 = r4.initialValue;
                r3 = "";
                r2 = r2.equals(r3);
                if (r2 != 0) goto L_0x0040;
            L_0x003a:
                r2 = 6;
                r3 = r4.initialValue;
                r5.writeString(r2, r3);
            L_0x0040:
                r2 = r4.isMasked;
                if (r2 == 0) goto L_0x004b;
            L_0x0044:
                r2 = 8;
                r3 = r4.isMasked;
                r5.writeBool(r2, r3);
            L_0x004b:
                super.writeTo(r5);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
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
                r5 = this;
                r2 = super.computeSerializedSize();
                r3 = r5.maxLength;
                if (r3 == 0) goto L_0x0010;
            L_0x0008:
                r3 = 2;
                r4 = r5.maxLength;
                r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt32Size(r3, r4);
                r2 = r2 + r3;
            L_0x0010:
                r3 = r5.keyboardLayout;
                if (r3 == 0) goto L_0x001c;
            L_0x0014:
                r3 = 4;
                r4 = r5.keyboardLayout;
                r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt32Size(r3, r4);
                r2 = r2 + r3;
            L_0x001c:
                r3 = r5.validation;
                if (r3 == 0) goto L_0x003a;
            L_0x0020:
                r3 = r5.validation;
                r3 = r3.length;
                if (r3 <= 0) goto L_0x003a;
            L_0x0025:
                r1 = 0;
            L_0x0026:
                r3 = r5.validation;
                r3 = r3.length;
                if (r1 >= r3) goto L_0x003a;
            L_0x002b:
                r3 = r5.validation;
                r0 = r3[r1];
                if (r0 == 0) goto L_0x0037;
            L_0x0031:
                r3 = 5;
                r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r3, r0);
                r2 = r2 + r3;
            L_0x0037:
                r1 = r1 + 1;
                goto L_0x0026;
            L_0x003a:
                r3 = r5.initialValue;
                r4 = "";
                r3 = r3.equals(r4);
                if (r3 != 0) goto L_0x004c;
            L_0x0044:
                r3 = 6;
                r4 = r5.initialValue;
                r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r3, r4);
                r2 = r2 + r3;
            L_0x004c:
                r3 = r5.isMasked;
                if (r3 == 0) goto L_0x0059;
            L_0x0050:
                r3 = 8;
                r4 = r5.isMasked;
                r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeBoolSize(r3, r4);
                r2 = r2 + r3;
            L_0x0059:
                return r2;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.computeSerializedSize():int");
            }

            public com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r8) throws java.io.IOException {
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
                r7 = this;
                r5 = 0;
            L_0x0001:
                r3 = r8.readTag();
                switch(r3) {
                    case 0: goto L_0x000e;
                    case 16: goto L_0x000f;
                    case 32: goto L_0x0016;
                    case 42: goto L_0x0021;
                    case 50: goto L_0x0061;
                    case 64: goto L_0x0068;
                    default: goto L_0x0008;
                };
            L_0x0008:
                r6 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r8, r3);
                if (r6 != 0) goto L_0x0001;
            L_0x000e:
                return r7;
            L_0x000f:
                r6 = r8.readInt32();
                r7.maxLength = r6;
                goto L_0x0001;
            L_0x0016:
                r4 = r8.readInt32();
                switch(r4) {
                    case 0: goto L_0x001e;
                    case 1: goto L_0x001e;
                    case 2: goto L_0x001e;
                    case 3: goto L_0x001e;
                    default: goto L_0x001d;
                };
            L_0x001d:
                goto L_0x0001;
            L_0x001e:
                r7.keyboardLayout = r4;
                goto L_0x0001;
            L_0x0021:
                r6 = 42;
                r0 = com.google.protobuf.nano.WireFormatNano.getRepeatedFieldArrayLength(r8, r6);
                r6 = r7.validation;
                if (r6 != 0) goto L_0x004e;
            L_0x002b:
                r1 = r5;
            L_0x002c:
                r6 = r1 + r0;
                r2 = new com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.Validation[r6];
                if (r1 == 0) goto L_0x0037;
            L_0x0032:
                r6 = r7.validation;
                java.lang.System.arraycopy(r6, r5, r2, r5, r1);
            L_0x0037:
                r6 = r2.length;
                r6 = r6 + -1;
                if (r1 >= r6) goto L_0x0052;
            L_0x003c:
                r6 = new com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField$Validation;
                r6.<init>();
                r2[r1] = r6;
                r6 = r2[r1];
                r8.readMessage(r6);
                r8.readTag();
                r1 = r1 + 1;
                goto L_0x0037;
            L_0x004e:
                r6 = r7.validation;
                r1 = r6.length;
                goto L_0x002c;
            L_0x0052:
                r6 = new com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField$Validation;
                r6.<init>();
                r2[r1] = r6;
                r6 = r2[r1];
                r8.readMessage(r6);
                r7.validation = r2;
                goto L_0x0001;
            L_0x0061:
                r6 = r8.readString();
                r7.initialValue = r6;
                goto L_0x0001;
            L_0x0068:
                r6 = r8.readBool();
                r7.isMasked = r6;
                goto L_0x0001;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.TextField.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField");
            }
        }

        public static com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField[] emptyArray() {
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
            r0 = _emptyArray;
            if (r0 != 0) goto L_0x0011;
        L_0x0004:
            r1 = com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK;
            monitor-enter(r1);
            r0 = _emptyArray;
            if (r0 != 0) goto L_0x0010;
        L_0x000b:
            r0 = 0;
            r0 = new com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField[r0];
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.emptyArray():com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField[]");
        }

        public UiField() {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.<init>():void");
        }

        public com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField clear() {
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
            r0 = "";
            r1.name = r0;
            r0 = 0;
            r1.isOptional = r0;
            r0 = "";
            r1.label = r0;
            r0 = 0;
            r1.textField = r0;
            r0 = -1;
            r1.cachedSize = r0;
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.clear():com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField");
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
            r0 = r2.name;
            r1 = "";
            r0 = r0.equals(r1);
            if (r0 != 0) goto L_0x0010;
        L_0x000a:
            r0 = 1;
            r1 = r2.name;
            r3.writeString(r0, r1);
        L_0x0010:
            r0 = r2.isOptional;
            if (r0 == 0) goto L_0x001a;
        L_0x0014:
            r0 = 3;
            r1 = r2.isOptional;
            r3.writeBool(r0, r1);
        L_0x001a:
            r0 = r2.label;
            r1 = "";
            r0 = r0.equals(r1);
            if (r0 != 0) goto L_0x002a;
        L_0x0024:
            r0 = 4;
            r1 = r2.label;
            r3.writeString(r0, r1);
        L_0x002a:
            r0 = r2.textField;
            if (r0 == 0) goto L_0x0034;
        L_0x002e:
            r0 = 6;
            r1 = r2.textField;
            r3.writeMessage(r0, r1);
        L_0x0034:
            super.writeTo(r3);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
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
            r1 = r3.name;
            r2 = "";
            r1 = r1.equals(r2);
            if (r1 != 0) goto L_0x0016;
        L_0x000e:
            r1 = 1;
            r2 = r3.name;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
            r0 = r0 + r1;
        L_0x0016:
            r1 = r3.isOptional;
            if (r1 == 0) goto L_0x0022;
        L_0x001a:
            r1 = 3;
            r2 = r3.isOptional;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeBoolSize(r1, r2);
            r0 = r0 + r1;
        L_0x0022:
            r1 = r3.label;
            r2 = "";
            r1 = r1.equals(r2);
            if (r1 != 0) goto L_0x0034;
        L_0x002c:
            r1 = 4;
            r2 = r3.label;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r1, r2);
            r0 = r0 + r1;
        L_0x0034:
            r1 = r3.textField;
            if (r1 == 0) goto L_0x0040;
        L_0x0038:
            r1 = 6;
            r2 = r3.textField;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r1, r2);
            r0 = r0 + r1;
        L_0x0040:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.computeSerializedSize():int");
        }

        public com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r3) throws java.io.IOException {
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
                case 24: goto L_0x0015;
                case 34: goto L_0x001c;
                case 50: goto L_0x0023;
                default: goto L_0x0007;
            };
        L_0x0007:
            r1 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r3, r0);
            if (r1 != 0) goto L_0x0000;
        L_0x000d:
            return r2;
        L_0x000e:
            r1 = r3.readString();
            r2.name = r1;
            goto L_0x0000;
        L_0x0015:
            r1 = r3.readBool();
            r2.isOptional = r1;
            goto L_0x0000;
        L_0x001c:
            r1 = r3.readString();
            r2.label = r1;
            goto L_0x0000;
        L_0x0023:
            r1 = r2.textField;
            if (r1 != 0) goto L_0x002e;
        L_0x0027:
            r1 = new com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField$TextField;
            r1.<init>();
            r2.textField = r1;
        L_0x002e:
            r1 = r2.textField;
            r3.readMessage(r1);
            goto L_0x0000;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass$UiField");
        }
    }

    public static final class UiFieldValue extends MessageNano {
        private static volatile UiFieldValue[] _emptyArray;
        public String name;
        public String stringValue;

        public static UiFieldValue[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new UiFieldValue[0];
                    }
                }
            }
            return _emptyArray;
        }

        public UiFieldValue() {
            clear();
        }

        public UiFieldValue clear() {
            this.name = "";
            this.stringValue = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (!this.stringValue.equals("")) {
                output.writeString(2, this.stringValue);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.stringValue.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.stringValue);
        }

        public UiFieldValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.name = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.stringValue = input.readString();
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
}
