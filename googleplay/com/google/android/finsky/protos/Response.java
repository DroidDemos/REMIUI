package com.google.android.finsky.protos;

import com.google.android.finsky.protos.AckNotification.AckNotificationResponse;
import com.google.android.finsky.protos.Browse.BrowseResponse;
import com.google.android.finsky.protos.Buy.BuyResponse;
import com.google.android.finsky.protos.Buy.PurchaseStatusResponse;
import com.google.android.finsky.protos.BuyInstruments.BillingProfileResponse;
import com.google.android.finsky.protos.BuyInstruments.CheckIabPromoResponse;
import com.google.android.finsky.protos.BuyInstruments.CheckInstrumentResponse;
import com.google.android.finsky.protos.BuyInstruments.CreateInstrumentResponse;
import com.google.android.finsky.protos.BuyInstruments.GetInitialInstrumentFlowStateResponse;
import com.google.android.finsky.protos.BuyInstruments.InstrumentSetupInfoResponse;
import com.google.android.finsky.protos.BuyInstruments.RedeemGiftCardResponse;
import com.google.android.finsky.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.protos.CarrierBilling.InitiateAssociationResponse;
import com.google.android.finsky.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.protos.ChallengeAction.ChallengeResponse;
import com.google.android.finsky.protos.CheckPromoOffer.CheckPromoOfferResponse;
import com.google.android.finsky.protos.ContentFlagging.FlagContentResponse;
import com.google.android.finsky.protos.DebugSettings.DebugSettingsResponse;
import com.google.android.finsky.protos.Delivery.DeliveryResponse;
import com.google.android.finsky.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.protos.Details.DetailsResponse;
import com.google.android.finsky.protos.DocList.ListResponse;
import com.google.android.finsky.protos.EarlyUpdate.EarlyUpdateResponse;
import com.google.android.finsky.protos.LibraryReplication.LibraryReplicationResponse;
import com.google.android.finsky.protos.Log.LogResponse;
import com.google.android.finsky.protos.ModifyLibrary.ModifyLibraryResponse;
import com.google.android.finsky.protos.MyAccount.MyAccountResponse;
import com.google.android.finsky.protos.Notifications.Notification;
import com.google.android.finsky.protos.PlusOne.PlusOneResponse;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.protos.Preloads.PreloadsResponse;
import com.google.android.finsky.protos.PromoCode.RedeemCodeResponse;
import com.google.android.finsky.protos.Purchase.CommitPurchaseResponse;
import com.google.android.finsky.protos.Purchase.PreparePurchaseResponse;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.finsky.protos.ResponseMessages.PreFetch;
import com.google.android.finsky.protos.ResponseMessages.ServerCommands;
import com.google.android.finsky.protos.ResponseMessages.ServerMetadata;
import com.google.android.finsky.protos.Restore.GetBackupDeviceChoicesResponse;
import com.google.android.finsky.protos.Restore.GetBackupDocumentChoicesResponse;
import com.google.android.finsky.protos.Rev.ReviewResponse;
import com.google.android.finsky.protos.Search.SearchResponse;
import com.google.android.finsky.protos.SearchSuggest.SearchSuggestResponse;
import com.google.android.finsky.protos.SelfUpdate.SelfUpdateResponse;
import com.google.android.finsky.protos.Targeting.Targets;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.protos.Tos.AcceptTosResponse;
import com.google.android.finsky.protos.UploadDeviceConfig.UploadDeviceConfigResponse;
import com.google.android.finsky.protos.UserActivity.RecordUserActivityResponse;
import com.google.android.finsky.protos.UserActivity.UserActivitySettingsResponse;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Response {

    public static final class Payload extends MessageNano {
        public AcceptTosResponse acceptTosResponse;
        public AckNotificationResponse ackNotificationResponse;
        public GetBackupDeviceChoicesResponse backupDeviceChoicesResponse;
        public GetBackupDocumentChoicesResponse backupDocumentChoicesResponse;
        public BillingProfileResponse billingProfileResponse;
        public BrowseResponse browseResponse;
        public BulkDetailsResponse bulkDetailsResponse;
        public BuyResponse buyResponse;
        public ChallengeResponse challengeResponse;
        public CheckIabPromoResponse checkIabPromoResponse;
        public CheckInstrumentResponse checkInstrumentResponse;
        public CheckPromoOfferResponse checkPromoOfferResponse;
        public CommitPurchaseResponse commitPurchaseResponse;
        public ConsumePurchaseResponse consumePurchaseResponse;
        public CreateInstrumentResponse createInstrumentResponse;
        public DebugSettingsResponse debugSettingsResponse;
        public DeliveryResponse deliveryResponse;
        public DetailsResponse detailsResponse;
        public EarlyUpdateResponse earlyUpdateResponse;
        public FlagContentResponse flagContentResponse;
        public GetInitialInstrumentFlowStateResponse getInitialInstrumentFlowStateResponse;
        public InitiateAssociationResponse initiateAssociationResponse;
        public InstrumentSetupInfoResponse instrumentSetupInfoResponse;
        public LibraryReplicationResponse libraryReplicationResponse;
        public ListResponse listResponse;
        public LogResponse logResponse;
        public ModifyLibraryResponse modifyLibraryResponse;
        public MyAccountResponse myAccountResponse;
        public PlusOneResponse plusOneResponse;
        public PlusProfileResponse plusProfileResponse;
        public PreloadsResponse preloadsResponse;
        public PreparePurchaseResponse preparePurchaseResponse;
        public PurchaseStatusResponse purchaseStatusResponse;
        public RateSuggestedContentResponse rateSuggestedContentResponse;
        public RecordUserActivityResponse recordUserActivityResponse;
        public RedeemCodeResponse redeemCodeResponse;
        public RedeemGiftCardResponse redeemGiftCardResponse;
        public ResolvedLink resolveLinkResponse;
        public ReviewResponse reviewResponse;
        public RevokeResponse revokeResponse;
        public SearchResponse searchResponse;
        public SearchSuggestResponse searchSuggestResponse;
        public SelfUpdateResponse selfUpdateResponse;
        public TocResponse tocResponse;
        public UpdateInstrumentResponse updateInstrumentResponse;
        public UploadDeviceConfigResponse uploadDeviceConfigResponse;
        public UserActivitySettingsResponse userActivitySettingsResponse;
        public VerifyAssociationResponse verifyAssociationResponse;

        public Payload() {
            clear();
        }

        public Payload clear() {
            this.listResponse = null;
            this.detailsResponse = null;
            this.reviewResponse = null;
            this.buyResponse = null;
            this.searchResponse = null;
            this.tocResponse = null;
            this.browseResponse = null;
            this.purchaseStatusResponse = null;
            this.updateInstrumentResponse = null;
            this.logResponse = null;
            this.checkInstrumentResponse = null;
            this.plusOneResponse = null;
            this.flagContentResponse = null;
            this.ackNotificationResponse = null;
            this.initiateAssociationResponse = null;
            this.verifyAssociationResponse = null;
            this.libraryReplicationResponse = null;
            this.revokeResponse = null;
            this.bulkDetailsResponse = null;
            this.resolveLinkResponse = null;
            this.deliveryResponse = null;
            this.acceptTosResponse = null;
            this.rateSuggestedContentResponse = null;
            this.checkPromoOfferResponse = null;
            this.instrumentSetupInfoResponse = null;
            this.redeemGiftCardResponse = null;
            this.modifyLibraryResponse = null;
            this.uploadDeviceConfigResponse = null;
            this.plusProfileResponse = null;
            this.consumePurchaseResponse = null;
            this.billingProfileResponse = null;
            this.preparePurchaseResponse = null;
            this.commitPurchaseResponse = null;
            this.debugSettingsResponse = null;
            this.checkIabPromoResponse = null;
            this.userActivitySettingsResponse = null;
            this.recordUserActivityResponse = null;
            this.redeemCodeResponse = null;
            this.selfUpdateResponse = null;
            this.searchSuggestResponse = null;
            this.getInitialInstrumentFlowStateResponse = null;
            this.createInstrumentResponse = null;
            this.challengeResponse = null;
            this.backupDeviceChoicesResponse = null;
            this.backupDocumentChoicesResponse = null;
            this.earlyUpdateResponse = null;
            this.preloadsResponse = null;
            this.myAccountResponse = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.listResponse != null) {
                output.writeMessage(1, this.listResponse);
            }
            if (this.detailsResponse != null) {
                output.writeMessage(2, this.detailsResponse);
            }
            if (this.reviewResponse != null) {
                output.writeMessage(3, this.reviewResponse);
            }
            if (this.buyResponse != null) {
                output.writeMessage(4, this.buyResponse);
            }
            if (this.searchResponse != null) {
                output.writeMessage(5, this.searchResponse);
            }
            if (this.tocResponse != null) {
                output.writeMessage(6, this.tocResponse);
            }
            if (this.browseResponse != null) {
                output.writeMessage(7, this.browseResponse);
            }
            if (this.purchaseStatusResponse != null) {
                output.writeMessage(8, this.purchaseStatusResponse);
            }
            if (this.updateInstrumentResponse != null) {
                output.writeMessage(9, this.updateInstrumentResponse);
            }
            if (this.logResponse != null) {
                output.writeMessage(10, this.logResponse);
            }
            if (this.checkInstrumentResponse != null) {
                output.writeMessage(11, this.checkInstrumentResponse);
            }
            if (this.plusOneResponse != null) {
                output.writeMessage(12, this.plusOneResponse);
            }
            if (this.flagContentResponse != null) {
                output.writeMessage(13, this.flagContentResponse);
            }
            if (this.ackNotificationResponse != null) {
                output.writeMessage(14, this.ackNotificationResponse);
            }
            if (this.initiateAssociationResponse != null) {
                output.writeMessage(15, this.initiateAssociationResponse);
            }
            if (this.verifyAssociationResponse != null) {
                output.writeMessage(16, this.verifyAssociationResponse);
            }
            if (this.libraryReplicationResponse != null) {
                output.writeMessage(17, this.libraryReplicationResponse);
            }
            if (this.revokeResponse != null) {
                output.writeMessage(18, this.revokeResponse);
            }
            if (this.bulkDetailsResponse != null) {
                output.writeMessage(19, this.bulkDetailsResponse);
            }
            if (this.resolveLinkResponse != null) {
                output.writeMessage(20, this.resolveLinkResponse);
            }
            if (this.deliveryResponse != null) {
                output.writeMessage(21, this.deliveryResponse);
            }
            if (this.acceptTosResponse != null) {
                output.writeMessage(22, this.acceptTosResponse);
            }
            if (this.rateSuggestedContentResponse != null) {
                output.writeMessage(23, this.rateSuggestedContentResponse);
            }
            if (this.checkPromoOfferResponse != null) {
                output.writeMessage(24, this.checkPromoOfferResponse);
            }
            if (this.instrumentSetupInfoResponse != null) {
                output.writeMessage(25, this.instrumentSetupInfoResponse);
            }
            if (this.redeemGiftCardResponse != null) {
                output.writeMessage(26, this.redeemGiftCardResponse);
            }
            if (this.modifyLibraryResponse != null) {
                output.writeMessage(27, this.modifyLibraryResponse);
            }
            if (this.uploadDeviceConfigResponse != null) {
                output.writeMessage(28, this.uploadDeviceConfigResponse);
            }
            if (this.plusProfileResponse != null) {
                output.writeMessage(29, this.plusProfileResponse);
            }
            if (this.consumePurchaseResponse != null) {
                output.writeMessage(30, this.consumePurchaseResponse);
            }
            if (this.billingProfileResponse != null) {
                output.writeMessage(31, this.billingProfileResponse);
            }
            if (this.preparePurchaseResponse != null) {
                output.writeMessage(32, this.preparePurchaseResponse);
            }
            if (this.commitPurchaseResponse != null) {
                output.writeMessage(33, this.commitPurchaseResponse);
            }
            if (this.debugSettingsResponse != null) {
                output.writeMessage(34, this.debugSettingsResponse);
            }
            if (this.checkIabPromoResponse != null) {
                output.writeMessage(35, this.checkIabPromoResponse);
            }
            if (this.userActivitySettingsResponse != null) {
                output.writeMessage(36, this.userActivitySettingsResponse);
            }
            if (this.recordUserActivityResponse != null) {
                output.writeMessage(37, this.recordUserActivityResponse);
            }
            if (this.redeemCodeResponse != null) {
                output.writeMessage(38, this.redeemCodeResponse);
            }
            if (this.selfUpdateResponse != null) {
                output.writeMessage(39, this.selfUpdateResponse);
            }
            if (this.searchSuggestResponse != null) {
                output.writeMessage(40, this.searchSuggestResponse);
            }
            if (this.getInitialInstrumentFlowStateResponse != null) {
                output.writeMessage(41, this.getInitialInstrumentFlowStateResponse);
            }
            if (this.createInstrumentResponse != null) {
                output.writeMessage(42, this.createInstrumentResponse);
            }
            if (this.challengeResponse != null) {
                output.writeMessage(43, this.challengeResponse);
            }
            if (this.backupDeviceChoicesResponse != null) {
                output.writeMessage(44, this.backupDeviceChoicesResponse);
            }
            if (this.backupDocumentChoicesResponse != null) {
                output.writeMessage(45, this.backupDocumentChoicesResponse);
            }
            if (this.earlyUpdateResponse != null) {
                output.writeMessage(46, this.earlyUpdateResponse);
            }
            if (this.preloadsResponse != null) {
                output.writeMessage(47, this.preloadsResponse);
            }
            if (this.myAccountResponse != null) {
                output.writeMessage(48, this.myAccountResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.listResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.listResponse);
            }
            if (this.detailsResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.detailsResponse);
            }
            if (this.reviewResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.reviewResponse);
            }
            if (this.buyResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.buyResponse);
            }
            if (this.searchResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.searchResponse);
            }
            if (this.tocResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.tocResponse);
            }
            if (this.browseResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.browseResponse);
            }
            if (this.purchaseStatusResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.purchaseStatusResponse);
            }
            if (this.updateInstrumentResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.updateInstrumentResponse);
            }
            if (this.logResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.logResponse);
            }
            if (this.checkInstrumentResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.checkInstrumentResponse);
            }
            if (this.plusOneResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.plusOneResponse);
            }
            if (this.flagContentResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.flagContentResponse);
            }
            if (this.ackNotificationResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.ackNotificationResponse);
            }
            if (this.initiateAssociationResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(15, this.initiateAssociationResponse);
            }
            if (this.verifyAssociationResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(16, this.verifyAssociationResponse);
            }
            if (this.libraryReplicationResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.libraryReplicationResponse);
            }
            if (this.revokeResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.revokeResponse);
            }
            if (this.bulkDetailsResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(19, this.bulkDetailsResponse);
            }
            if (this.resolveLinkResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(20, this.resolveLinkResponse);
            }
            if (this.deliveryResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(21, this.deliveryResponse);
            }
            if (this.acceptTosResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(22, this.acceptTosResponse);
            }
            if (this.rateSuggestedContentResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(23, this.rateSuggestedContentResponse);
            }
            if (this.checkPromoOfferResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(24, this.checkPromoOfferResponse);
            }
            if (this.instrumentSetupInfoResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(25, this.instrumentSetupInfoResponse);
            }
            if (this.redeemGiftCardResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(26, this.redeemGiftCardResponse);
            }
            if (this.modifyLibraryResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(27, this.modifyLibraryResponse);
            }
            if (this.uploadDeviceConfigResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(28, this.uploadDeviceConfigResponse);
            }
            if (this.plusProfileResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(29, this.plusProfileResponse);
            }
            if (this.consumePurchaseResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(30, this.consumePurchaseResponse);
            }
            if (this.billingProfileResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(31, this.billingProfileResponse);
            }
            if (this.preparePurchaseResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(32, this.preparePurchaseResponse);
            }
            if (this.commitPurchaseResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(33, this.commitPurchaseResponse);
            }
            if (this.debugSettingsResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(34, this.debugSettingsResponse);
            }
            if (this.checkIabPromoResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(35, this.checkIabPromoResponse);
            }
            if (this.userActivitySettingsResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(36, this.userActivitySettingsResponse);
            }
            if (this.recordUserActivityResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(37, this.recordUserActivityResponse);
            }
            if (this.redeemCodeResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(38, this.redeemCodeResponse);
            }
            if (this.selfUpdateResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(39, this.selfUpdateResponse);
            }
            if (this.searchSuggestResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(40, this.searchSuggestResponse);
            }
            if (this.getInitialInstrumentFlowStateResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(41, this.getInitialInstrumentFlowStateResponse);
            }
            if (this.createInstrumentResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(42, this.createInstrumentResponse);
            }
            if (this.challengeResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(43, this.challengeResponse);
            }
            if (this.backupDeviceChoicesResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(44, this.backupDeviceChoicesResponse);
            }
            if (this.backupDocumentChoicesResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(45, this.backupDocumentChoicesResponse);
            }
            if (this.earlyUpdateResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(46, this.earlyUpdateResponse);
            }
            if (this.preloadsResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(47, this.preloadsResponse);
            }
            if (this.myAccountResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(48, this.myAccountResponse);
            }
            return size;
        }

        public Payload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.listResponse == null) {
                            this.listResponse = new ListResponse();
                        }
                        input.readMessage(this.listResponse);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.detailsResponse == null) {
                            this.detailsResponse = new DetailsResponse();
                        }
                        input.readMessage(this.detailsResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.reviewResponse == null) {
                            this.reviewResponse = new ReviewResponse();
                        }
                        input.readMessage(this.reviewResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.buyResponse == null) {
                            this.buyResponse = new BuyResponse();
                        }
                        input.readMessage(this.buyResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.searchResponse == null) {
                            this.searchResponse = new SearchResponse();
                        }
                        input.readMessage(this.searchResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.tocResponse == null) {
                            this.tocResponse = new TocResponse();
                        }
                        input.readMessage(this.tocResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.browseResponse == null) {
                            this.browseResponse = new BrowseResponse();
                        }
                        input.readMessage(this.browseResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.purchaseStatusResponse == null) {
                            this.purchaseStatusResponse = new PurchaseStatusResponse();
                        }
                        input.readMessage(this.purchaseStatusResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.updateInstrumentResponse == null) {
                            this.updateInstrumentResponse = new UpdateInstrumentResponse();
                        }
                        input.readMessage(this.updateInstrumentResponse);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.logResponse == null) {
                            this.logResponse = new LogResponse();
                        }
                        input.readMessage(this.logResponse);
                        continue;
                    case 90:
                        if (this.checkInstrumentResponse == null) {
                            this.checkInstrumentResponse = new CheckInstrumentResponse();
                        }
                        input.readMessage(this.checkInstrumentResponse);
                        continue;
                    case 98:
                        if (this.plusOneResponse == null) {
                            this.plusOneResponse = new PlusOneResponse();
                        }
                        input.readMessage(this.plusOneResponse);
                        continue;
                    case 106:
                        if (this.flagContentResponse == null) {
                            this.flagContentResponse = new FlagContentResponse();
                        }
                        input.readMessage(this.flagContentResponse);
                        continue;
                    case 114:
                        if (this.ackNotificationResponse == null) {
                            this.ackNotificationResponse = new AckNotificationResponse();
                        }
                        input.readMessage(this.ackNotificationResponse);
                        continue;
                    case 122:
                        if (this.initiateAssociationResponse == null) {
                            this.initiateAssociationResponse = new InitiateAssociationResponse();
                        }
                        input.readMessage(this.initiateAssociationResponse);
                        continue;
                    case 130:
                        if (this.verifyAssociationResponse == null) {
                            this.verifyAssociationResponse = new VerifyAssociationResponse();
                        }
                        input.readMessage(this.verifyAssociationResponse);
                        continue;
                    case 138:
                        if (this.libraryReplicationResponse == null) {
                            this.libraryReplicationResponse = new LibraryReplicationResponse();
                        }
                        input.readMessage(this.libraryReplicationResponse);
                        continue;
                    case 146:
                        if (this.revokeResponse == null) {
                            this.revokeResponse = new RevokeResponse();
                        }
                        input.readMessage(this.revokeResponse);
                        continue;
                    case 154:
                        if (this.bulkDetailsResponse == null) {
                            this.bulkDetailsResponse = new BulkDetailsResponse();
                        }
                        input.readMessage(this.bulkDetailsResponse);
                        continue;
                    case 162:
                        if (this.resolveLinkResponse == null) {
                            this.resolveLinkResponse = new ResolvedLink();
                        }
                        input.readMessage(this.resolveLinkResponse);
                        continue;
                    case 170:
                        if (this.deliveryResponse == null) {
                            this.deliveryResponse = new DeliveryResponse();
                        }
                        input.readMessage(this.deliveryResponse);
                        continue;
                    case 178:
                        if (this.acceptTosResponse == null) {
                            this.acceptTosResponse = new AcceptTosResponse();
                        }
                        input.readMessage(this.acceptTosResponse);
                        continue;
                    case 186:
                        if (this.rateSuggestedContentResponse == null) {
                            this.rateSuggestedContentResponse = new RateSuggestedContentResponse();
                        }
                        input.readMessage(this.rateSuggestedContentResponse);
                        continue;
                    case 194:
                        if (this.checkPromoOfferResponse == null) {
                            this.checkPromoOfferResponse = new CheckPromoOfferResponse();
                        }
                        input.readMessage(this.checkPromoOfferResponse);
                        continue;
                    case 202:
                        if (this.instrumentSetupInfoResponse == null) {
                            this.instrumentSetupInfoResponse = new InstrumentSetupInfoResponse();
                        }
                        input.readMessage(this.instrumentSetupInfoResponse);
                        continue;
                    case 210:
                        if (this.redeemGiftCardResponse == null) {
                            this.redeemGiftCardResponse = new RedeemGiftCardResponse();
                        }
                        input.readMessage(this.redeemGiftCardResponse);
                        continue;
                    case 218:
                        if (this.modifyLibraryResponse == null) {
                            this.modifyLibraryResponse = new ModifyLibraryResponse();
                        }
                        input.readMessage(this.modifyLibraryResponse);
                        continue;
                    case 226:
                        if (this.uploadDeviceConfigResponse == null) {
                            this.uploadDeviceConfigResponse = new UploadDeviceConfigResponse();
                        }
                        input.readMessage(this.uploadDeviceConfigResponse);
                        continue;
                    case 234:
                        if (this.plusProfileResponse == null) {
                            this.plusProfileResponse = new PlusProfileResponse();
                        }
                        input.readMessage(this.plusProfileResponse);
                        continue;
                    case 242:
                        if (this.consumePurchaseResponse == null) {
                            this.consumePurchaseResponse = new ConsumePurchaseResponse();
                        }
                        input.readMessage(this.consumePurchaseResponse);
                        continue;
                    case 250:
                        if (this.billingProfileResponse == null) {
                            this.billingProfileResponse = new BillingProfileResponse();
                        }
                        input.readMessage(this.billingProfileResponse);
                        continue;
                    case 258:
                        if (this.preparePurchaseResponse == null) {
                            this.preparePurchaseResponse = new PreparePurchaseResponse();
                        }
                        input.readMessage(this.preparePurchaseResponse);
                        continue;
                    case 266:
                        if (this.commitPurchaseResponse == null) {
                            this.commitPurchaseResponse = new CommitPurchaseResponse();
                        }
                        input.readMessage(this.commitPurchaseResponse);
                        continue;
                    case 274:
                        if (this.debugSettingsResponse == null) {
                            this.debugSettingsResponse = new DebugSettingsResponse();
                        }
                        input.readMessage(this.debugSettingsResponse);
                        continue;
                    case 282:
                        if (this.checkIabPromoResponse == null) {
                            this.checkIabPromoResponse = new CheckIabPromoResponse();
                        }
                        input.readMessage(this.checkIabPromoResponse);
                        continue;
                    case 290:
                        if (this.userActivitySettingsResponse == null) {
                            this.userActivitySettingsResponse = new UserActivitySettingsResponse();
                        }
                        input.readMessage(this.userActivitySettingsResponse);
                        continue;
                    case 298:
                        if (this.recordUserActivityResponse == null) {
                            this.recordUserActivityResponse = new RecordUserActivityResponse();
                        }
                        input.readMessage(this.recordUserActivityResponse);
                        continue;
                    case 306:
                        if (this.redeemCodeResponse == null) {
                            this.redeemCodeResponse = new RedeemCodeResponse();
                        }
                        input.readMessage(this.redeemCodeResponse);
                        continue;
                    case 314:
                        if (this.selfUpdateResponse == null) {
                            this.selfUpdateResponse = new SelfUpdateResponse();
                        }
                        input.readMessage(this.selfUpdateResponse);
                        continue;
                    case 322:
                        if (this.searchSuggestResponse == null) {
                            this.searchSuggestResponse = new SearchSuggestResponse();
                        }
                        input.readMessage(this.searchSuggestResponse);
                        continue;
                    case 330:
                        if (this.getInitialInstrumentFlowStateResponse == null) {
                            this.getInitialInstrumentFlowStateResponse = new GetInitialInstrumentFlowStateResponse();
                        }
                        input.readMessage(this.getInitialInstrumentFlowStateResponse);
                        continue;
                    case 338:
                        if (this.createInstrumentResponse == null) {
                            this.createInstrumentResponse = new CreateInstrumentResponse();
                        }
                        input.readMessage(this.createInstrumentResponse);
                        continue;
                    case 346:
                        if (this.challengeResponse == null) {
                            this.challengeResponse = new ChallengeResponse();
                        }
                        input.readMessage(this.challengeResponse);
                        continue;
                    case 354:
                        if (this.backupDeviceChoicesResponse == null) {
                            this.backupDeviceChoicesResponse = new GetBackupDeviceChoicesResponse();
                        }
                        input.readMessage(this.backupDeviceChoicesResponse);
                        continue;
                    case 362:
                        if (this.backupDocumentChoicesResponse == null) {
                            this.backupDocumentChoicesResponse = new GetBackupDocumentChoicesResponse();
                        }
                        input.readMessage(this.backupDocumentChoicesResponse);
                        continue;
                    case 370:
                        if (this.earlyUpdateResponse == null) {
                            this.earlyUpdateResponse = new EarlyUpdateResponse();
                        }
                        input.readMessage(this.earlyUpdateResponse);
                        continue;
                    case 378:
                        if (this.preloadsResponse == null) {
                            this.preloadsResponse = new PreloadsResponse();
                        }
                        input.readMessage(this.preloadsResponse);
                        continue;
                    case 386:
                        if (this.myAccountResponse == null) {
                            this.myAccountResponse = new MyAccountResponse();
                        }
                        input.readMessage(this.myAccountResponse);
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

    public static final class ResponseWrapper extends MessageNano {
        public ServerCommands commands;
        public Notification[] notification;
        public Payload payload;
        public PreFetch[] preFetch;
        public ServerMetadata serverMetadata;
        public Targets targets;

        public ResponseWrapper() {
            clear();
        }

        public ResponseWrapper clear() {
            this.payload = null;
            this.commands = null;
            this.preFetch = PreFetch.emptyArray();
            this.notification = Notification.emptyArray();
            this.serverMetadata = null;
            this.targets = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.payload != null) {
                output.writeMessage(1, this.payload);
            }
            if (this.commands != null) {
                output.writeMessage(2, this.commands);
            }
            if (this.preFetch != null && this.preFetch.length > 0) {
                for (PreFetch element : this.preFetch) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.notification != null && this.notification.length > 0) {
                for (Notification element2 : this.notification) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            if (this.serverMetadata != null) {
                output.writeMessage(5, this.serverMetadata);
            }
            if (this.targets != null) {
                output.writeMessage(6, this.targets);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.payload != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.payload);
            }
            if (this.commands != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.commands);
            }
            if (this.preFetch != null && this.preFetch.length > 0) {
                for (PreFetch element : this.preFetch) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.notification != null && this.notification.length > 0) {
                for (Notification element2 : this.notification) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            if (this.serverMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.serverMetadata);
            }
            if (this.targets != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.targets);
            }
            return size;
        }

        public ResponseWrapper mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.payload == null) {
                            this.payload = new Payload();
                        }
                        input.readMessage(this.payload);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.commands == null) {
                            this.commands = new ServerCommands();
                        }
                        input.readMessage(this.commands);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.preFetch == null) {
                            i = 0;
                        } else {
                            i = this.preFetch.length;
                        }
                        PreFetch[] newArray = new PreFetch[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.preFetch, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PreFetch();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PreFetch();
                        input.readMessage(newArray[i]);
                        this.preFetch = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.notification == null) {
                            i = 0;
                        } else {
                            i = this.notification.length;
                        }
                        Notification[] newArray2 = new Notification[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.notification, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Notification();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Notification();
                        input.readMessage(newArray2[i]);
                        this.notification = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.serverMetadata == null) {
                            this.serverMetadata = new ServerMetadata();
                        }
                        input.readMessage(this.serverMetadata);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.targets == null) {
                            this.targets = new Targets();
                        }
                        input.readMessage(this.targets);
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

        public static ResponseWrapper parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResponseWrapper) MessageNano.mergeFrom(new ResponseWrapper(), data);
        }
    }
}
