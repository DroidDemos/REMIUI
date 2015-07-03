package com.android.ex.editstyledtext;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.NoCopySpan.Concrete;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.ArrowKeyMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.common.speech.LoggingEvents;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class EditStyledText extends EditText {
    private static final boolean DBG = true;
    public static final int DEFAULT_FOREGROUND_COLOR = -16777216;
    public static final int DEFAULT_TRANSPARENT_COLOR = 16777215;
    public static final int HINT_MSG_BIG_SIZE_ERROR = 5;
    public static final int HINT_MSG_COPY_BUF_BLANK = 1;
    public static final int HINT_MSG_END_COMPOSE = 7;
    public static final int HINT_MSG_END_PREVIEW = 6;
    public static final int HINT_MSG_NULL = 0;
    public static final int HINT_MSG_PUSH_COMPETE = 4;
    public static final int HINT_MSG_SELECT_END = 3;
    public static final int HINT_MSG_SELECT_START = 2;
    private static final int ID_CLEARSTYLES = 16776962;
    private static final int ID_COPY = 16908321;
    private static final int ID_CUT = 16908320;
    private static final int ID_HIDEEDIT = 16776964;
    private static final int ID_HORIZONTALLINE = 16776961;
    private static final int ID_PASTE = 16908322;
    private static final int ID_SELECT_ALL = 16908319;
    private static final int ID_SHOWEDIT = 16776963;
    private static final int ID_START_SELECTING_TEXT = 16908328;
    private static final int ID_STOP_SELECTING_TEXT = 16908329;
    public static final char IMAGECHAR = '\ufffc';
    private static final int MAXIMAGEWIDTHDIP = 300;
    public static final int MODE_ALIGN = 6;
    public static final int MODE_BGCOLOR = 16;
    public static final int MODE_CANCEL = 18;
    public static final int MODE_CLEARSTYLES = 14;
    public static final int MODE_COLOR = 4;
    public static final int MODE_COPY = 1;
    public static final int MODE_CUT = 7;
    public static final int MODE_END_EDIT = 21;
    public static final int MODE_HORIZONTALLINE = 12;
    public static final int MODE_IMAGE = 15;
    public static final int MODE_MARQUEE = 10;
    public static final int MODE_NOTHING = 0;
    public static final int MODE_PASTE = 2;
    public static final int MODE_PREVIEW = 17;
    public static final int MODE_RESET = 22;
    public static final int MODE_SELECT = 5;
    public static final int MODE_SELECTALL = 11;
    public static final int MODE_SHOW_MENU = 23;
    public static final int MODE_SIZE = 3;
    public static final int MODE_START_EDIT = 20;
    public static final int MODE_STOP_SELECT = 13;
    public static final int MODE_SWING = 9;
    public static final int MODE_TELOP = 8;
    public static final int MODE_TEXTVIEWFUNCTION = 19;
    private static final int PRESSED = 16777233;
    private static final Concrete SELECTING;
    public static final int STATE_SELECTED = 2;
    public static final int STATE_SELECT_FIX = 3;
    public static final int STATE_SELECT_OFF = 0;
    public static final int STATE_SELECT_ON = 1;
    private static CharSequence STR_CLEARSTYLES = null;
    private static CharSequence STR_HORIZONTALLINE = null;
    private static CharSequence STR_PASTE = null;
    private static final String TAG = "EditStyledText";
    public static final char ZEROWIDTHCHAR = '\u2060';
    private StyledTextConverter mConverter;
    private Drawable mDefaultBackground;
    private StyledTextDialog mDialog;
    private ArrayList<EditStyledTextNotifier> mESTNotifiers;
    private InputConnection mInputConnection;
    private EditorManager mManager;
    private float mPaddingScale;

    public static class ColorPaletteDrawable extends ShapeDrawable {
        private Rect mRect;

        public ColorPaletteDrawable(int color, int width, int height, int mergin) {
            super(new RectShape());
            this.mRect = new Rect(mergin, mergin, width - mergin, height - mergin);
            getPaint().setColor(color);
        }

        public void draw(Canvas canvas) {
            canvas.drawRect(this.mRect, getPaint());
        }
    }

    public class EditModeActions {
        private static final boolean DBG = true;
        private static final String TAG = "EditModeActions";
        private HashMap<Integer, EditModeActionBase> mActionMap;
        private AlignAction mAlignAction;
        private BackgroundColorAction mBackgroundColorAction;
        private CancelAction mCancelEditAction;
        private ClearStylesAction mClearStylesAction;
        private ColorAction mColorAction;
        private CopyAction mCopyAction;
        private CutAction mCutAction;
        private StyledTextDialog mDialog;
        private EditStyledText mEST;
        private EndEditAction mEndEditAction;
        private HorizontalLineAction mHorizontalLineAction;
        private ImageAction mImageAction;
        private EditorManager mManager;
        private MarqueeDialogAction mMarqueeDialogAction;
        private int mMode;
        private NothingAction mNothingAction;
        private PasteAction mPasteAction;
        private PreviewAction mPreviewAction;
        private ResetAction mResetAction;
        private SelectAction mSelectAction;
        private SelectAllAction mSelectAllAction;
        private ShowMenuAction mShowMenuAction;
        private SizeAction mSizeAction;
        private StartEditAction mStartEditAction;
        private StopSelectionAction mStopSelectionAction;
        private SwingAction mSwingAction;
        private TelopAction mTelopAction;
        private TextViewAction mTextViewAction;

        public class EditModeActionBase {
            private Object[] mParams;

            protected boolean canOverWrap() {
                return false;
            }

            protected boolean canSelect() {
                return false;
            }

            protected boolean canWaitInput() {
                return false;
            }

            protected boolean needSelection() {
                return false;
            }

            protected boolean isLine() {
                return false;
            }

            protected boolean doNotSelected() {
                return false;
            }

            protected boolean doStartPosIsSelected() {
                return doNotSelected();
            }

            protected boolean doEndPosIsSelected() {
                return doStartPosIsSelected();
            }

            protected boolean doSelectionIsFixed() {
                return doEndPosIsSelected();
            }

            protected boolean doSelectionIsFixedAndWaitingInput() {
                return doEndPosIsSelected();
            }

            protected boolean fixSelection() {
                EditModeActions.this.mEST.finishComposingText();
                EditModeActions.this.mManager.setSelectState(EditStyledText.STATE_SELECT_FIX);
                return EditModeActions.DBG;
            }

            protected void addParams(Object[] o) {
                this.mParams = o;
            }

            protected Object getParam(int num) {
                if (this.mParams != null && num <= this.mParams.length) {
                    return this.mParams[num];
                }
                Log.d(EditModeActions.TAG, "--- Number of the parameter is out of bound.");
                return null;
            }
        }

        public class SetSpanActionBase extends EditModeActionBase {
            public SetSpanActionBase() {
                super();
            }

            protected boolean doNotSelected() {
                if (EditModeActions.this.mManager.getEditMode() == 0 || EditModeActions.this.mManager.getEditMode() == EditStyledText.MODE_SELECT) {
                    EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                    EditModeActions.this.mManager.setInternalSelection(EditModeActions.this.mEST.getSelectionStart(), EditModeActions.this.mEST.getSelectionEnd());
                    fixSelection();
                    EditModeActions.this.doNext();
                    return EditModeActions.DBG;
                } else if (EditModeActions.this.mManager.getEditMode() == EditModeActions.this.mMode) {
                    return false;
                } else {
                    Log.d(EditModeActions.TAG, "--- setspanactionbase" + EditModeActions.this.mManager.getEditMode() + "," + EditModeActions.this.mMode);
                    if (EditModeActions.this.mManager.isWaitInput()) {
                        EditModeActions.this.mManager.setEditMode(EditStyledText.STATE_SELECT_OFF);
                        EditModeActions.this.mManager.setSelectState(EditStyledText.STATE_SELECT_OFF);
                    } else {
                        EditModeActions.this.mManager.resetEdit();
                        EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                    }
                    EditModeActions.this.doNext();
                    return EditModeActions.DBG;
                }
            }

            protected boolean doStartPosIsSelected() {
                if (EditModeActions.this.mManager.getEditMode() != 0 && EditModeActions.this.mManager.getEditMode() != EditStyledText.MODE_SELECT) {
                    return doNotSelected();
                }
                EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                EditModeActions.this.onSelectAction();
                return EditModeActions.DBG;
            }

            protected boolean doEndPosIsSelected() {
                if (EditModeActions.this.mManager.getEditMode() != 0 && EditModeActions.this.mManager.getEditMode() != EditStyledText.MODE_SELECT) {
                    return doStartPosIsSelected();
                }
                EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                fixSelection();
                EditModeActions.this.doNext();
                return EditModeActions.DBG;
            }

            protected boolean doSelectionIsFixed() {
                if (doEndPosIsSelected()) {
                    return EditModeActions.DBG;
                }
                EditModeActions.this.mEST.sendHintMessage(EditStyledText.STATE_SELECT_OFF);
                return false;
            }
        }

        public class AlignAction extends SetSpanActionBase {
            public AlignAction() {
                super();
            }

            protected boolean doSelectionIsFixed() {
                if (!super.doSelectionIsFixed()) {
                    EditModeActions.this.mDialog.onShowAlignAlertDialog();
                }
                return EditModeActions.DBG;
            }
        }

        public class BackgroundColorAction extends EditModeActionBase {
            public BackgroundColorAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mDialog.onShowBackgroundColorAlertDialog();
                return EditModeActions.DBG;
            }
        }

        public class CancelAction extends EditModeActionBase {
            public CancelAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mEST.cancelViewManagers();
                return EditModeActions.DBG;
            }
        }

        public class ClearStylesAction extends EditModeActionBase {
            public ClearStylesAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.clearStyles();
                return EditModeActions.DBG;
            }
        }

        public class ColorAction extends SetSpanActionBase {
            public ColorAction() {
                super();
            }

            protected boolean doSelectionIsFixed() {
                if (!super.doSelectionIsFixed()) {
                    EditModeActions.this.mDialog.onShowForegroundColorAlertDialog();
                }
                return EditModeActions.DBG;
            }

            protected boolean doSelectionIsFixedAndWaitingInput() {
                if (!super.doSelectionIsFixedAndWaitingInput()) {
                    int size = EditModeActions.this.mManager.getSizeWaitInput();
                    EditModeActions.this.mManager.setItemColor(EditModeActions.this.mManager.getColorWaitInput(), false);
                    if (EditModeActions.this.mManager.isWaitInput()) {
                        fixSelection();
                        EditModeActions.this.mDialog.onShowForegroundColorAlertDialog();
                    } else {
                        EditModeActions.this.mManager.setItemSize(size, false);
                        EditModeActions.this.mManager.resetEdit();
                    }
                }
                return EditModeActions.DBG;
            }
        }

        public class TextViewActionBase extends EditModeActionBase {
            public TextViewActionBase() {
                super();
            }

            protected boolean doNotSelected() {
                if (EditModeActions.this.mManager.getEditMode() != 0 && EditModeActions.this.mManager.getEditMode() != EditStyledText.MODE_SELECT) {
                    return false;
                }
                EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                EditModeActions.this.onSelectAction();
                return EditModeActions.DBG;
            }

            protected boolean doEndPosIsSelected() {
                if (EditModeActions.this.mManager.getEditMode() == 0 || EditModeActions.this.mManager.getEditMode() == EditStyledText.MODE_SELECT) {
                    EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                    fixSelection();
                    EditModeActions.this.doNext();
                    return EditModeActions.DBG;
                } else if (EditModeActions.this.mManager.getEditMode() == EditModeActions.this.mMode) {
                    return false;
                } else {
                    EditModeActions.this.mManager.resetEdit();
                    EditModeActions.this.mManager.setEditMode(EditModeActions.this.mMode);
                    EditModeActions.this.doNext();
                    return EditModeActions.DBG;
                }
            }
        }

        public class CopyAction extends TextViewActionBase {
            public CopyAction() {
                super();
            }

            protected boolean doEndPosIsSelected() {
                if (!super.doEndPosIsSelected()) {
                    EditModeActions.this.mManager.copyToClipBoard();
                    EditModeActions.this.mManager.resetEdit();
                }
                return EditModeActions.DBG;
            }
        }

        public class CutAction extends TextViewActionBase {
            public CutAction() {
                super();
            }

            protected boolean doEndPosIsSelected() {
                if (!super.doEndPosIsSelected()) {
                    EditModeActions.this.mManager.cutToClipBoard();
                    EditModeActions.this.mManager.resetEdit();
                }
                return EditModeActions.DBG;
            }
        }

        public class EndEditAction extends EditModeActionBase {
            public EndEditAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.endEdit();
                return EditModeActions.DBG;
            }
        }

        public class HorizontalLineAction extends EditModeActionBase {
            public HorizontalLineAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.insertHorizontalLine();
                return EditModeActions.DBG;
            }
        }

        public class ImageAction extends EditModeActionBase {
            public ImageAction() {
                super();
            }

            protected boolean doNotSelected() {
                Object param = getParam(EditStyledText.STATE_SELECT_OFF);
                if (param == null) {
                    EditModeActions.this.mEST.showInsertImageSelectAlertDialog();
                } else if (param instanceof Uri) {
                    EditModeActions.this.mManager.insertImageFromUri((Uri) param);
                } else if (param instanceof Integer) {
                    EditModeActions.this.mManager.insertImageFromResId(((Integer) param).intValue());
                }
                return EditModeActions.DBG;
            }
        }

        public class MarqueeDialogAction extends SetSpanActionBase {
            public MarqueeDialogAction() {
                super();
            }

            protected boolean doSelectionIsFixed() {
                if (!super.doSelectionIsFixed()) {
                    EditModeActions.this.mDialog.onShowMarqueeAlertDialog();
                }
                return EditModeActions.DBG;
            }
        }

        public class NothingAction extends EditModeActionBase {
            public NothingAction() {
                super();
            }
        }

        public class PasteAction extends EditModeActionBase {
            public PasteAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.pasteFromClipboard();
                EditModeActions.this.mManager.resetEdit();
                return EditModeActions.DBG;
            }
        }

        public class PreviewAction extends EditModeActionBase {
            public PreviewAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mEST.showPreview();
                return EditModeActions.DBG;
            }
        }

        public class ResetAction extends EditModeActionBase {
            public ResetAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.resetEdit();
                return EditModeActions.DBG;
            }
        }

        public class SelectAction extends EditModeActionBase {
            public SelectAction() {
                super();
            }

            protected boolean doNotSelected() {
                if (EditModeActions.this.mManager.isTextSelected()) {
                    Log.e(EditModeActions.TAG, "Selection is off, but selected");
                }
                EditModeActions.this.mManager.setSelectStartPos();
                EditModeActions.this.mEST.sendHintMessage(EditStyledText.STATE_SELECT_FIX);
                return EditModeActions.DBG;
            }

            protected boolean doStartPosIsSelected() {
                if (EditModeActions.this.mManager.isTextSelected()) {
                    Log.e(EditModeActions.TAG, "Selection now start, but selected");
                }
                EditModeActions.this.mManager.setSelectEndPos();
                EditModeActions.this.mEST.sendHintMessage(EditStyledText.MODE_COLOR);
                if (EditModeActions.this.mManager.getEditMode() != EditStyledText.MODE_SELECT) {
                    EditModeActions.this.doNext(EditModeActions.this.mManager.getEditMode());
                }
                return EditModeActions.DBG;
            }

            protected boolean doSelectionIsFixed() {
                return false;
            }
        }

        public class SelectAllAction extends EditModeActionBase {
            public SelectAllAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.selectAll();
                return EditModeActions.DBG;
            }
        }

        public class ShowMenuAction extends EditModeActionBase {
            public ShowMenuAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mEST.showMenuAlertDialog();
                return EditModeActions.DBG;
            }
        }

        public class SizeAction extends SetSpanActionBase {
            public SizeAction() {
                super();
            }

            protected boolean doSelectionIsFixed() {
                if (!super.doSelectionIsFixed()) {
                    EditModeActions.this.mDialog.onShowSizeAlertDialog();
                }
                return EditModeActions.DBG;
            }

            protected boolean doSelectionIsFixedAndWaitingInput() {
                if (!super.doSelectionIsFixedAndWaitingInput()) {
                    int color = EditModeActions.this.mManager.getColorWaitInput();
                    EditModeActions.this.mManager.setItemSize(EditModeActions.this.mManager.getSizeWaitInput(), false);
                    if (EditModeActions.this.mManager.isWaitInput()) {
                        fixSelection();
                        EditModeActions.this.mDialog.onShowSizeAlertDialog();
                    } else {
                        EditModeActions.this.mManager.setItemColor(color, false);
                        EditModeActions.this.mManager.resetEdit();
                    }
                }
                return EditModeActions.DBG;
            }
        }

        public class StartEditAction extends EditModeActionBase {
            public StartEditAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.startEdit();
                return EditModeActions.DBG;
            }
        }

        public class StopSelectionAction extends EditModeActionBase {
            public StopSelectionAction() {
                super();
            }

            protected boolean doNotSelected() {
                EditModeActions.this.mManager.fixSelectionAndDoNextAction();
                return EditModeActions.DBG;
            }
        }

        public class SwingAction extends SetSpanActionBase {
            public SwingAction() {
                super();
            }

            protected boolean doSelectionIsFixed() {
                if (!super.doSelectionIsFixed()) {
                    EditModeActions.this.mManager.setSwing();
                }
                return EditModeActions.DBG;
            }
        }

        public class TelopAction extends SetSpanActionBase {
            public TelopAction() {
                super();
            }

            protected boolean doSelectionIsFixed() {
                if (!super.doSelectionIsFixed()) {
                    EditModeActions.this.mManager.setTelop();
                }
                return EditModeActions.DBG;
            }
        }

        public class TextViewAction extends TextViewActionBase {
            public TextViewAction() {
                super();
            }

            protected boolean doEndPosIsSelected() {
                if (!super.doEndPosIsSelected()) {
                    Object param = getParam(EditStyledText.STATE_SELECT_OFF);
                    if (param != null && (param instanceof Integer)) {
                        EditModeActions.this.mEST.onTextContextMenuItem(((Integer) param).intValue());
                    }
                    EditModeActions.this.mManager.resetEdit();
                }
                return EditModeActions.DBG;
            }
        }

        EditModeActions(EditStyledText est, EditorManager manager, StyledTextDialog dialog) {
            this.mMode = EditStyledText.STATE_SELECT_OFF;
            this.mActionMap = new HashMap();
            this.mNothingAction = new NothingAction();
            this.mCopyAction = new CopyAction();
            this.mPasteAction = new PasteAction();
            this.mSelectAction = new SelectAction();
            this.mCutAction = new CutAction();
            this.mSelectAllAction = new SelectAllAction();
            this.mHorizontalLineAction = new HorizontalLineAction();
            this.mStopSelectionAction = new StopSelectionAction();
            this.mClearStylesAction = new ClearStylesAction();
            this.mImageAction = new ImageAction();
            this.mBackgroundColorAction = new BackgroundColorAction();
            this.mPreviewAction = new PreviewAction();
            this.mCancelEditAction = new CancelAction();
            this.mTextViewAction = new TextViewAction();
            this.mStartEditAction = new StartEditAction();
            this.mEndEditAction = new EndEditAction();
            this.mResetAction = new ResetAction();
            this.mShowMenuAction = new ShowMenuAction();
            this.mAlignAction = new AlignAction();
            this.mTelopAction = new TelopAction();
            this.mSwingAction = new SwingAction();
            this.mMarqueeDialogAction = new MarqueeDialogAction();
            this.mColorAction = new ColorAction();
            this.mSizeAction = new SizeAction();
            this.mEST = est;
            this.mManager = manager;
            this.mDialog = dialog;
            this.mActionMap.put(Integer.valueOf(EditStyledText.STATE_SELECT_OFF), this.mNothingAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.STATE_SELECT_ON), this.mCopyAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.STATE_SELECTED), this.mPasteAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_SELECT), this.mSelectAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_CUT), this.mCutAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_SELECTALL), this.mSelectAllAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_HORIZONTALLINE), this.mHorizontalLineAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_STOP_SELECT), this.mStopSelectionAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_CLEARSTYLES), this.mClearStylesAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_IMAGE), this.mImageAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_BGCOLOR), this.mBackgroundColorAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_PREVIEW), this.mPreviewAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_CANCEL), this.mCancelEditAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_TEXTVIEWFUNCTION), this.mTextViewAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_START_EDIT), this.mStartEditAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_END_EDIT), this.mEndEditAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_RESET), this.mResetAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_SHOW_MENU), this.mShowMenuAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_ALIGN), this.mAlignAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_TELOP), this.mTelopAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_SWING), this.mSwingAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_MARQUEE), this.mMarqueeDialogAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.MODE_COLOR), this.mColorAction);
            this.mActionMap.put(Integer.valueOf(EditStyledText.STATE_SELECT_FIX), this.mSizeAction);
        }

        public void addAction(int modeId, EditModeActionBase action) {
            this.mActionMap.put(Integer.valueOf(modeId), action);
        }

        public void onAction(int newMode, Object[] params) {
            getAction(newMode).addParams(params);
            this.mMode = newMode;
            doNext(newMode);
        }

        public void onAction(int newMode, Object param) {
            Object[] objArr = new Object[EditStyledText.STATE_SELECT_ON];
            objArr[EditStyledText.STATE_SELECT_OFF] = param;
            onAction(newMode, objArr);
        }

        public void onAction(int newMode) {
            onAction(newMode, null);
        }

        public void onSelectAction() {
            doNext(EditStyledText.MODE_SELECT);
        }

        private EditModeActionBase getAction(int mode) {
            if (this.mActionMap.containsKey(Integer.valueOf(mode))) {
                return (EditModeActionBase) this.mActionMap.get(Integer.valueOf(mode));
            }
            return null;
        }

        public boolean doNext() {
            return doNext(this.mMode);
        }

        public boolean doNext(int mode) {
            Log.d(TAG, "--- do the next action: " + mode + "," + this.mManager.getSelectState());
            EditModeActionBase action = getAction(mode);
            if (action == null) {
                Log.e(TAG, "--- invalid action error.");
                return false;
            }
            switch (this.mManager.getSelectState()) {
                case EditStyledText.STATE_SELECT_OFF /*0*/:
                    return action.doNotSelected();
                case EditStyledText.STATE_SELECT_ON /*1*/:
                    return action.doStartPosIsSelected();
                case EditStyledText.STATE_SELECTED /*2*/:
                    return action.doEndPosIsSelected();
                case EditStyledText.STATE_SELECT_FIX /*3*/:
                    if (this.mManager.isWaitInput()) {
                        return action.doSelectionIsFixedAndWaitingInput();
                    }
                    return action.doSelectionIsFixed();
                default:
                    return false;
            }
        }
    }

    public interface EditStyledTextNotifier {
        void cancelViewManager();

        boolean isButtonsFocused();

        void onStateChanged(int i, int i2);

        void sendHintMsg(int i);

        boolean sendOnTouchEvent(MotionEvent motionEvent);

        boolean showInsertImageSelectAlertDialog();

        boolean showMenuAlertDialog();

        boolean showPreview();
    }

    public static class EditStyledTextSpans {
        private static final String LOG_TAG = "EditStyledTextSpan";

        public static class HorizontalLineDrawable extends ShapeDrawable {
            private static boolean DBG_HL;
            private Spannable mSpannable;
            private int mWidth;

            static {
                DBG_HL = false;
            }

            public HorizontalLineDrawable(int color, int width, Spannable spannable) {
                super(new RectShape());
                this.mSpannable = spannable;
                this.mWidth = width;
                renewColor(color);
                renewBounds(width);
            }

            public void draw(Canvas canvas) {
                renewColor();
                canvas.drawRect(new Rect(EditStyledText.STATE_SELECT_OFF, EditStyledText.MODE_SWING, this.mWidth, EditStyledText.MODE_SELECTALL), getPaint());
            }

            public void renewBounds(int width) {
                if (DBG_HL) {
                    Log.d(EditStyledTextSpans.LOG_TAG, "--- renewBounds:" + width);
                }
                if (width > EditStyledText.MODE_START_EDIT) {
                    width -= EditStyledText.MODE_START_EDIT;
                }
                this.mWidth = width;
                setBounds(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_OFF, width, EditStyledText.MODE_START_EDIT);
            }

            private void renewColor(int color) {
                if (DBG_HL) {
                    Log.d(EditStyledTextSpans.LOG_TAG, "--- renewColor:" + color);
                }
                getPaint().setColor(color);
            }

            private void renewColor() {
                HorizontalLineSpan parent = getParentSpan();
                Spannable text = this.mSpannable;
                ForegroundColorSpan[] spans = (ForegroundColorSpan[]) text.getSpans(text.getSpanStart(parent), text.getSpanEnd(parent), ForegroundColorSpan.class);
                if (DBG_HL) {
                    Log.d(EditStyledTextSpans.LOG_TAG, "--- renewColor:" + spans.length);
                }
                if (spans.length > 0) {
                    renewColor(spans[spans.length - 1].getForegroundColor());
                }
            }

            private HorizontalLineSpan getParentSpan() {
                Spannable text = this.mSpannable;
                HorizontalLineSpan[] images = (HorizontalLineSpan[]) text.getSpans(EditStyledText.STATE_SELECT_OFF, text.length(), HorizontalLineSpan.class);
                if (images.length > 0) {
                    HorizontalLineSpan[] arr$ = images;
                    int len$ = arr$.length;
                    for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                        HorizontalLineSpan image = arr$[i$];
                        if (image.getDrawable() == this) {
                            return image;
                        }
                    }
                }
                Log.e(EditStyledTextSpans.LOG_TAG, "---renewBounds: Couldn't find");
                return null;
            }
        }

        public static class HorizontalLineSpan extends DynamicDrawableSpan {
            HorizontalLineDrawable mDrawable;

            public HorizontalLineSpan(int color, int width, Spannable spannable) {
                super(EditStyledText.STATE_SELECT_OFF);
                this.mDrawable = new HorizontalLineDrawable(color, width, spannable);
            }

            public Drawable getDrawable() {
                return this.mDrawable;
            }

            public void resetWidth(int width) {
                this.mDrawable.renewBounds(width);
            }

            public int getColor() {
                return this.mDrawable.getPaint().getColor();
            }
        }

        public static class MarqueeSpan extends CharacterStyle {
            public static final int ALTERNATE = 1;
            public static final int NOTHING = 2;
            public static final int SCROLL = 0;
            private int mMarqueeColor;
            private int mType;

            public MarqueeSpan(int type, int bgc) {
                this.mType = type;
                checkType(type);
                this.mMarqueeColor = getMarqueeColor(type, bgc);
            }

            public MarqueeSpan(int type) {
                this(type, EditStyledText.DEFAULT_TRANSPARENT_COLOR);
            }

            public int getType() {
                return this.mType;
            }

            public void resetColor(int bgc) {
                this.mMarqueeColor = getMarqueeColor(this.mType, bgc);
            }

            private int getMarqueeColor(int type, int bgc) {
                int a = Color.alpha(bgc);
                int r = Color.red(bgc);
                int g = Color.green(bgc);
                int b = Color.blue(bgc);
                if (a == 0) {
                    a = 128;
                }
                switch (type) {
                    case EditStyledText.STATE_SELECT_OFF /*0*/:
                        if (r <= 128) {
                            r = (255 - r) / NOTHING;
                            break;
                        }
                        r /= NOTHING;
                        break;
                    case ALTERNATE /*1*/:
                        if (g <= 128) {
                            g = (255 - g) / NOTHING;
                            break;
                        }
                        g /= NOTHING;
                        break;
                    case NOTHING /*2*/:
                        return EditStyledText.DEFAULT_TRANSPARENT_COLOR;
                    default:
                        Log.e(EditStyledText.TAG, "--- getMarqueeColor: got illigal marquee ID.");
                        return EditStyledText.DEFAULT_TRANSPARENT_COLOR;
                }
                return Color.argb(a, r, g, b);
            }

            private boolean checkType(int type) {
                if (type == 0 || type == ALTERNATE) {
                    return EditStyledText.DBG;
                }
                Log.e(EditStyledTextSpans.LOG_TAG, "--- Invalid type of MarqueeSpan");
                return false;
            }

            public void updateDrawState(TextPaint tp) {
                tp.bgColor = this.mMarqueeColor;
            }
        }

        public static class RescalableImageSpan extends ImageSpan {
            private final int MAXWIDTH;
            Uri mContentUri;
            private Context mContext;
            private Drawable mDrawable;
            public int mIntrinsicHeight;
            public int mIntrinsicWidth;

            public RescalableImageSpan(Context context, Uri uri, int maxwidth) {
                super(context, uri);
                this.mIntrinsicWidth = -1;
                this.mIntrinsicHeight = -1;
                this.mContext = context;
                this.mContentUri = uri;
                this.MAXWIDTH = maxwidth;
            }

            public RescalableImageSpan(Context context, int resourceId, int maxwidth) {
                super(context, resourceId);
                this.mIntrinsicWidth = -1;
                this.mIntrinsicHeight = -1;
                this.mContext = context;
                this.MAXWIDTH = maxwidth;
            }

            public Drawable getDrawable() {
                if (this.mDrawable != null) {
                    return this.mDrawable;
                }
                if (this.mContentUri != null) {
                    System.gc();
                    try {
                        Bitmap bitmap;
                        InputStream is = this.mContext.getContentResolver().openInputStream(this.mContentUri);
                        Options opt = new Options();
                        opt.inJustDecodeBounds = EditStyledText.DBG;
                        BitmapFactory.decodeStream(is, null, opt);
                        is.close();
                        is = this.mContext.getContentResolver().openInputStream(this.mContentUri);
                        int width = opt.outWidth;
                        int height = opt.outHeight;
                        this.mIntrinsicWidth = width;
                        this.mIntrinsicHeight = height;
                        if (opt.outWidth > this.MAXWIDTH) {
                            width = this.MAXWIDTH;
                            height = (this.MAXWIDTH * height) / opt.outWidth;
                            bitmap = BitmapFactory.decodeStream(is, new Rect(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_OFF, width, height), null);
                        } else {
                            bitmap = BitmapFactory.decodeStream(is);
                        }
                        this.mDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap);
                        this.mDrawable.setBounds(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_OFF, width, height);
                        is.close();
                    } catch (Exception e) {
                        Log.e(EditStyledTextSpans.LOG_TAG, "Failed to loaded content " + this.mContentUri, e);
                        return null;
                    } catch (OutOfMemoryError e2) {
                        Log.e(EditStyledTextSpans.LOG_TAG, "OutOfMemoryError");
                        return null;
                    }
                }
                this.mDrawable = super.getDrawable();
                rescaleBigImage(this.mDrawable);
                this.mIntrinsicWidth = this.mDrawable.getIntrinsicWidth();
                this.mIntrinsicHeight = this.mDrawable.getIntrinsicHeight();
                return this.mDrawable;
            }

            public boolean isOverSize() {
                return getDrawable().getIntrinsicWidth() > this.MAXWIDTH ? EditStyledText.DBG : false;
            }

            public Uri getContentUri() {
                return this.mContentUri;
            }

            private void rescaleBigImage(Drawable image) {
                Log.d(EditStyledTextSpans.LOG_TAG, "--- rescaleBigImage:");
                if (this.MAXWIDTH >= 0) {
                    int image_width = image.getIntrinsicWidth();
                    int image_height = image.getIntrinsicHeight();
                    Log.d(EditStyledTextSpans.LOG_TAG, "--- rescaleBigImage:" + image_width + "," + image_height + "," + this.MAXWIDTH);
                    if (image_width > this.MAXWIDTH) {
                        image_width = this.MAXWIDTH;
                        image_height = (this.MAXWIDTH * image_height) / image_width;
                    }
                    image.setBounds(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_OFF, image_width, image_height);
                }
            }
        }
    }

    private class EditorManager {
        private static final String LOG_TAG = "EditStyledText.EditorManager";
        private EditModeActions mActions;
        private int mBackgroundColor;
        private int mColorWaitInput;
        private BackgroundColorSpan mComposingTextMask;
        private SpannableStringBuilder mCopyBuffer;
        private int mCurEnd;
        private int mCurStart;
        private EditStyledText mEST;
        private boolean mEditFlag;
        private boolean mKeepNonLineSpan;
        private int mMode;
        private int mSizeWaitInput;
        private SoftKeyReceiver mSkr;
        private boolean mSoftKeyBlockFlag;
        private int mState;
        private boolean mTextIsFinishedFlag;
        private boolean mWaitInputFlag;

        EditorManager(EditStyledText est, StyledTextDialog dialog) {
            this.mEditFlag = false;
            this.mSoftKeyBlockFlag = false;
            this.mKeepNonLineSpan = false;
            this.mWaitInputFlag = false;
            this.mTextIsFinishedFlag = false;
            this.mMode = EditStyledText.STATE_SELECT_OFF;
            this.mState = EditStyledText.STATE_SELECT_OFF;
            this.mCurStart = EditStyledText.STATE_SELECT_OFF;
            this.mCurEnd = EditStyledText.STATE_SELECT_OFF;
            this.mColorWaitInput = EditStyledText.DEFAULT_TRANSPARENT_COLOR;
            this.mSizeWaitInput = EditStyledText.STATE_SELECT_OFF;
            this.mBackgroundColor = EditStyledText.DEFAULT_TRANSPARENT_COLOR;
            this.mEST = est;
            this.mActions = new EditModeActions(this.mEST, this, dialog);
            this.mSkr = new SoftKeyReceiver(this.mEST);
        }

        public void addAction(int mode, EditModeActionBase action) {
            this.mActions.addAction(mode, action);
        }

        public void onAction(int mode) {
            onAction(mode, EditStyledText.DBG);
        }

        public void onAction(int mode, boolean notifyStateChanged) {
            this.mActions.onAction(mode);
            if (notifyStateChanged) {
                this.mEST.notifyStateChanged(this.mMode, this.mState);
            }
        }

        private void startEdit() {
            resetEdit();
            showSoftKey();
        }

        public void onStartSelect(boolean notifyStateChanged) {
            Log.d(LOG_TAG, "--- onClickSelect");
            this.mMode = EditStyledText.MODE_SELECT;
            if (this.mState == 0) {
                this.mActions.onSelectAction();
            } else {
                unsetSelect();
                this.mActions.onSelectAction();
            }
            if (notifyStateChanged) {
                this.mEST.notifyStateChanged(this.mMode, this.mState);
            }
        }

        public void onCursorMoved() {
            Log.d(LOG_TAG, "--- onClickView");
            if (this.mState == EditStyledText.STATE_SELECT_ON || this.mState == EditStyledText.STATE_SELECTED) {
                this.mActions.onSelectAction();
                this.mEST.notifyStateChanged(this.mMode, this.mState);
            }
        }

        public void onStartSelectAll(boolean notifyStateChanged) {
            Log.d(LOG_TAG, "--- onClickSelectAll");
            handleSelectAll();
            if (notifyStateChanged) {
                this.mEST.notifyStateChanged(this.mMode, this.mState);
            }
        }

        public void onStartShowMenuAlertDialog() {
            this.mActions.onAction(EditStyledText.MODE_SHOW_MENU);
        }

        public void onFixSelectedItem() {
            Log.d(LOG_TAG, "--- onFixSelectedItem");
            fixSelectionAndDoNextAction();
            this.mEST.notifyStateChanged(this.mMode, this.mState);
        }

        public void onInsertImage(Uri uri) {
            this.mActions.onAction((int) EditStyledText.MODE_IMAGE, (Object) uri);
            this.mEST.notifyStateChanged(this.mMode, this.mState);
        }

        public void onInsertImage(int resId) {
            this.mActions.onAction((int) EditStyledText.MODE_IMAGE, Integer.valueOf(resId));
            this.mEST.notifyStateChanged(this.mMode, this.mState);
        }

        private void insertImageFromUri(Uri uri) {
            insertImageSpan(new RescalableImageSpan(this.mEST.getContext(), uri, this.mEST.getMaxImageWidthPx()), this.mEST.getSelectionStart());
        }

        private void insertImageFromResId(int resId) {
            insertImageSpan(new RescalableImageSpan(this.mEST.getContext(), resId, this.mEST.getMaxImageWidthDip()), this.mEST.getSelectionStart());
        }

        private void insertHorizontalLine() {
            int curpos;
            Log.d(LOG_TAG, "--- onInsertHorizontalLine:");
            int curpos2 = this.mEST.getSelectionStart();
            if (curpos2 > 0 && this.mEST.getText().charAt(curpos2 - 1) != '\n') {
                curpos = curpos2 + EditStyledText.STATE_SELECT_ON;
                this.mEST.getText().insert(curpos2, "\n");
                curpos2 = curpos;
            }
            curpos = curpos2 + EditStyledText.STATE_SELECT_ON;
            insertImageSpan(new HorizontalLineSpan(EditStyledText.DEFAULT_FOREGROUND_COLOR, this.mEST.getWidth(), this.mEST.getText()), curpos2);
            curpos2 = curpos + EditStyledText.STATE_SELECT_ON;
            this.mEST.getText().insert(curpos, "\n");
            this.mEST.setSelection(curpos2);
            this.mEST.notifyStateChanged(this.mMode, this.mState);
        }

        private void clearStyles(CharSequence txt) {
            Log.d(EditStyledText.TAG, "--- onClearStyles");
            int len = txt.length();
            if (txt instanceof Editable) {
                Editable editable = (Editable) txt;
                Object[] arr$ = editable.getSpans(EditStyledText.STATE_SELECT_OFF, len, Object.class);
                int len$ = arr$.length;
                for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                    Object style = arr$[i$];
                    if ((style instanceof ParagraphStyle) || (style instanceof QuoteSpan) || ((style instanceof CharacterStyle) && !(style instanceof UnderlineSpan))) {
                        if ((style instanceof ImageSpan) || (style instanceof HorizontalLineSpan)) {
                            editable.replace(editable.getSpanStart(style), editable.getSpanEnd(style), LoggingEvents.EXTRA_CALLING_APP_NAME);
                        }
                        editable.removeSpan(style);
                    }
                }
            }
        }

        public void onClearStyles() {
            this.mActions.onAction(EditStyledText.MODE_CLEARSTYLES);
        }

        public void onCancelViewManagers() {
            this.mActions.onAction(EditStyledText.MODE_CANCEL);
        }

        private void clearStyles() {
            Log.d(LOG_TAG, "--- onClearStyles");
            clearStyles(this.mEST.getText());
            this.mEST.setBackgroundDrawable(this.mEST.mDefaultBackground);
            this.mBackgroundColor = EditStyledText.DEFAULT_TRANSPARENT_COLOR;
            onRefreshZeoWidthChar();
        }

        public void onRefreshZeoWidthChar() {
            Editable txt = this.mEST.getText();
            int i = EditStyledText.STATE_SELECT_OFF;
            while (i < txt.length()) {
                if (txt.charAt(i) == EditStyledText.ZEROWIDTHCHAR) {
                    txt.replace(i, i + EditStyledText.STATE_SELECT_ON, LoggingEvents.EXTRA_CALLING_APP_NAME);
                    i--;
                }
                i += EditStyledText.STATE_SELECT_ON;
            }
        }

        public void onRefreshStyles() {
            int i$;
            Log.d(LOG_TAG, "--- onRefreshStyles");
            Editable txt = this.mEST.getText();
            int len = txt.length();
            int width = this.mEST.getWidth();
            HorizontalLineSpan[] lines = (HorizontalLineSpan[]) txt.getSpans(EditStyledText.STATE_SELECT_OFF, len, HorizontalLineSpan.class);
            HorizontalLineSpan[] arr$ = lines;
            int len$ = arr$.length;
            for (i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                arr$[i$].resetWidth(width);
            }
            MarqueeSpan[] arr$2 = (MarqueeSpan[]) txt.getSpans(EditStyledText.STATE_SELECT_OFF, len, MarqueeSpan.class);
            len$ = arr$2.length;
            for (i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                arr$2[i$].resetColor(this.mEST.getBackgroundColor());
            }
            if (lines.length > 0) {
                txt.replace(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_ON, LoggingEvents.EXTRA_CALLING_APP_NAME + txt.charAt(EditStyledText.STATE_SELECT_OFF));
            }
        }

        public void setBackgroundColor(int color) {
            this.mBackgroundColor = color;
        }

        public void setItemSize(int size, boolean reset) {
            Log.d(LOG_TAG, "--- setItemSize");
            if (isWaitingNextAction()) {
                this.mSizeWaitInput = size;
            } else if (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) {
                if (size > 0) {
                    changeSizeSelectedText(size);
                }
                if (reset) {
                    resetEdit();
                }
            }
        }

        public void setItemColor(int color, boolean reset) {
            Log.d(LOG_TAG, "--- setItemColor");
            if (isWaitingNextAction()) {
                this.mColorWaitInput = color;
            } else if (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) {
                if (color != EditStyledText.DEFAULT_TRANSPARENT_COLOR) {
                    changeColorSelectedText(color);
                }
                if (reset) {
                    resetEdit();
                }
            }
        }

        public void setAlignment(Alignment align) {
            if (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) {
                changeAlign(align);
                resetEdit();
            }
        }

        public void setTelop() {
            if (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) {
                addTelop();
                resetEdit();
            }
        }

        public void setSwing() {
            if (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) {
                addSwing();
                resetEdit();
            }
        }

        public void setMarquee(int marquee) {
            if (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) {
                addMarquee(marquee);
                resetEdit();
            }
        }

        public void setTextComposingMask(int start, int end) {
            int foregroundColor;
            Log.d(EditStyledText.TAG, "--- setTextComposingMask:" + start + "," + end);
            int min = Math.min(start, end);
            int max = Math.max(start, end);
            if (!isWaitInput() || this.mColorWaitInput == EditStyledText.DEFAULT_TRANSPARENT_COLOR) {
                foregroundColor = this.mEST.getForegroundColor(min);
            } else {
                foregroundColor = this.mColorWaitInput;
            }
            int backgroundColor = this.mEST.getBackgroundColor();
            Log.d(EditStyledText.TAG, "--- fg:" + Integer.toHexString(foregroundColor) + ",bg:" + Integer.toHexString(backgroundColor) + "," + isWaitInput() + "," + "," + this.mMode);
            if (foregroundColor == backgroundColor) {
                int maskColor = Integer.MIN_VALUE | ((EditStyledText.DEFAULT_FOREGROUND_COLOR | backgroundColor) ^ -1);
                if (this.mComposingTextMask == null || this.mComposingTextMask.getBackgroundColor() != maskColor) {
                    this.mComposingTextMask = new BackgroundColorSpan(maskColor);
                }
                this.mEST.getText().setSpan(this.mComposingTextMask, min, max, 33);
            }
        }

        private void setEditMode(int mode) {
            this.mMode = mode;
        }

        private void setSelectState(int state) {
            this.mState = state;
        }

        public void unsetTextComposingMask() {
            Log.d(EditStyledText.TAG, "--- unsetTextComposingMask");
            if (this.mComposingTextMask != null) {
                this.mEST.getText().removeSpan(this.mComposingTextMask);
                this.mComposingTextMask = null;
            }
        }

        public boolean isEditting() {
            return this.mEditFlag;
        }

        public boolean isStyledText() {
            Editable txt = this.mEST.getText();
            int len = txt.length();
            if (((ParagraphStyle[]) txt.getSpans(EditStyledText.STATE_SELECT_OFF, len, ParagraphStyle.class)).length > 0 || ((QuoteSpan[]) txt.getSpans(EditStyledText.STATE_SELECT_OFF, len, QuoteSpan.class)).length > 0 || ((CharacterStyle[]) txt.getSpans(EditStyledText.STATE_SELECT_OFF, len, CharacterStyle.class)).length > 0 || this.mBackgroundColor != EditStyledText.DEFAULT_TRANSPARENT_COLOR) {
                return EditStyledText.DBG;
            }
            return false;
        }

        public boolean isSoftKeyBlocked() {
            return this.mSoftKeyBlockFlag;
        }

        public boolean isWaitInput() {
            return this.mWaitInputFlag;
        }

        public int getBackgroundColor() {
            return this.mBackgroundColor;
        }

        public int getEditMode() {
            return this.mMode;
        }

        public int getSelectState() {
            return this.mState;
        }

        public int getSelectionStart() {
            return this.mCurStart;
        }

        public int getSelectionEnd() {
            return this.mCurEnd;
        }

        public int getSizeWaitInput() {
            return this.mSizeWaitInput;
        }

        public int getColorWaitInput() {
            return this.mColorWaitInput;
        }

        private void setInternalSelection(int curStart, int curEnd) {
            this.mCurStart = curStart;
            this.mCurEnd = curEnd;
        }

        public void updateSpanPreviousFromCursor(Editable txt, int start, int before, int after) {
            Log.d(LOG_TAG, "updateSpanPrevious:" + start + "," + before + "," + after);
            int end = start + after;
            int min = Math.min(start, end);
            int max = Math.max(start, end);
            Object[] arr$ = txt.getSpans(min, min, Object.class);
            int len$ = arr$.length;
            for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                Object span = arr$[i$];
                int spanstart;
                int spanend;
                if ((span instanceof ForegroundColorSpan) || (span instanceof AbsoluteSizeSpan) || (span instanceof MarqueeSpan) || (span instanceof AlignmentSpan)) {
                    spanstart = txt.getSpanStart(span);
                    spanend = txt.getSpanEnd(span);
                    Log.d(LOG_TAG, "spantype:" + span.getClass() + "," + spanstart);
                    int tempmax = max;
                    if ((span instanceof MarqueeSpan) || (span instanceof AlignmentSpan)) {
                        tempmax = findLineEnd(this.mEST.getText(), max);
                    } else if (this.mKeepNonLineSpan) {
                        tempmax = spanend;
                    }
                    if (spanend < tempmax) {
                        Log.d(LOG_TAG, "updateSpanPrevious: extend span");
                        txt.setSpan(span, spanstart, tempmax, 33);
                    }
                } else if (span instanceof HorizontalLineSpan) {
                    spanstart = txt.getSpanStart(span);
                    spanend = txt.getSpanEnd(span);
                    if (before > after) {
                        txt.replace(spanstart, spanend, LoggingEvents.EXTRA_CALLING_APP_NAME);
                        txt.removeSpan(span);
                    } else if (spanend == end && end < txt.length() && this.mEST.getText().charAt(end) != '\n') {
                        this.mEST.getText().insert(end, "\n");
                    }
                }
            }
        }

        public void updateSpanNextToCursor(Editable txt, int start, int before, int after) {
            Log.d(LOG_TAG, "updateSpanNext:" + start + "," + before + "," + after);
            int end = start + after;
            int min = Math.min(start, end);
            int max = Math.max(start, end);
            Object[] arr$ = txt.getSpans(max, max, Object.class);
            int len$ = arr$.length;
            for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                Object span = arr$[i$];
                if ((span instanceof MarqueeSpan) || (span instanceof AlignmentSpan)) {
                    int spanstart = txt.getSpanStart(span);
                    int spanend = txt.getSpanEnd(span);
                    Log.d(LOG_TAG, "spantype:" + span.getClass() + "," + spanend);
                    int tempmin = min;
                    if ((span instanceof MarqueeSpan) || (span instanceof AlignmentSpan)) {
                        tempmin = findLineStart(this.mEST.getText(), min);
                    }
                    if (tempmin < spanstart && before > after) {
                        txt.removeSpan(span);
                    } else if (spanstart > min) {
                        txt.setSpan(span, min, spanend, 33);
                    }
                } else if ((span instanceof HorizontalLineSpan) && txt.getSpanStart(span) == end && end > 0 && this.mEST.getText().charAt(end - 1) != '\n') {
                    this.mEST.getText().insert(end, "\n");
                    this.mEST.setSelection(end);
                }
            }
        }

        public boolean canPaste() {
            return (this.mCopyBuffer == null || this.mCopyBuffer.length() <= 0 || removeImageChar(this.mCopyBuffer).length() != 0) ? false : EditStyledText.DBG;
        }

        private void endEdit() {
            Log.d(LOG_TAG, "--- handleCancel");
            this.mMode = EditStyledText.STATE_SELECT_OFF;
            this.mState = EditStyledText.STATE_SELECT_OFF;
            this.mEditFlag = false;
            this.mColorWaitInput = EditStyledText.DEFAULT_TRANSPARENT_COLOR;
            this.mSizeWaitInput = EditStyledText.STATE_SELECT_OFF;
            this.mWaitInputFlag = false;
            this.mSoftKeyBlockFlag = false;
            this.mKeepNonLineSpan = false;
            this.mTextIsFinishedFlag = false;
            unsetSelect();
            this.mEST.setOnClickListener(null);
            unblockSoftKey();
        }

        private void fixSelectionAndDoNextAction() {
            Log.d(LOG_TAG, "--- handleComplete:" + this.mCurStart + "," + this.mCurEnd);
            if (!this.mEditFlag) {
                return;
            }
            if (this.mCurStart == this.mCurEnd) {
                Log.d(LOG_TAG, "--- cancel handle complete:" + this.mCurStart);
                resetEdit();
                return;
            }
            if (this.mState == EditStyledText.STATE_SELECTED) {
                this.mState = EditStyledText.STATE_SELECT_FIX;
            }
            this.mActions.doNext(this.mMode);
            EditStyledText.stopSelecting(this.mEST, this.mEST.getText());
        }

        private SpannableStringBuilder removeImageChar(SpannableStringBuilder text) {
            SpannableStringBuilder buf = new SpannableStringBuilder(text);
            DynamicDrawableSpan[] arr$ = (DynamicDrawableSpan[]) buf.getSpans(EditStyledText.STATE_SELECT_OFF, buf.length(), DynamicDrawableSpan.class);
            int len$ = arr$.length;
            for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                DynamicDrawableSpan style = arr$[i$];
                if ((style instanceof HorizontalLineSpan) || (style instanceof RescalableImageSpan)) {
                    buf.replace(buf.getSpanStart(style), buf.getSpanEnd(style), LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
            }
            return buf;
        }

        private void copyToClipBoard() {
            this.mCopyBuffer = (SpannableStringBuilder) this.mEST.getText().subSequence(Math.min(getSelectionStart(), getSelectionEnd()), Math.max(getSelectionStart(), getSelectionEnd()));
            SpannableStringBuilder clipboardtxt = removeImageChar(this.mCopyBuffer);
            ((ClipboardManager) EditStyledText.this.getContext().getSystemService("clipboard")).setText(clipboardtxt);
            dumpSpannableString(clipboardtxt);
            dumpSpannableString(this.mCopyBuffer);
        }

        private void cutToClipBoard() {
            copyToClipBoard();
            this.mEST.getText().delete(Math.min(getSelectionStart(), getSelectionEnd()), Math.max(getSelectionStart(), getSelectionEnd()));
        }

        private boolean isClipBoardChanged(CharSequence clipboardText) {
            Log.d(EditStyledText.TAG, "--- isClipBoardChanged:" + clipboardText);
            if (this.mCopyBuffer == null) {
                return EditStyledText.DBG;
            }
            int len = clipboardText.length();
            CharSequence removedClipBoard = removeImageChar(this.mCopyBuffer);
            Log.d(EditStyledText.TAG, "--- clipBoard:" + len + "," + removedClipBoard + clipboardText);
            if (len != removedClipBoard.length()) {
                return EditStyledText.DBG;
            }
            for (int i = EditStyledText.STATE_SELECT_OFF; i < len; i += EditStyledText.STATE_SELECT_ON) {
                if (clipboardText.charAt(i) != removedClipBoard.charAt(i)) {
                    return EditStyledText.DBG;
                }
            }
            return false;
        }

        private void pasteFromClipboard() {
            int min = Math.min(this.mEST.getSelectionStart(), this.mEST.getSelectionEnd());
            int max = Math.max(this.mEST.getSelectionStart(), this.mEST.getSelectionEnd());
            Selection.setSelection(this.mEST.getText(), max);
            ClipboardManager clip = (ClipboardManager) EditStyledText.this.getContext().getSystemService("clipboard");
            this.mKeepNonLineSpan = EditStyledText.DBG;
            this.mEST.getText().replace(min, max, clip.getText());
            if (!isClipBoardChanged(clip.getText())) {
                Log.d(EditStyledText.TAG, "--- handlePaste: startPasteImage");
                DynamicDrawableSpan[] arr$ = (DynamicDrawableSpan[]) this.mCopyBuffer.getSpans(EditStyledText.STATE_SELECT_OFF, this.mCopyBuffer.length(), DynamicDrawableSpan.class);
                int len$ = arr$.length;
                for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                    DynamicDrawableSpan style = arr$[i$];
                    int start = this.mCopyBuffer.getSpanStart(style);
                    if (style instanceof HorizontalLineSpan) {
                        insertImageSpan(new HorizontalLineSpan(EditStyledText.DEFAULT_FOREGROUND_COLOR, this.mEST.getWidth(), this.mEST.getText()), min + start);
                    } else if (style instanceof RescalableImageSpan) {
                        insertImageSpan(new RescalableImageSpan(this.mEST.getContext(), ((RescalableImageSpan) style).getContentUri(), this.mEST.getMaxImageWidthPx()), min + start);
                    }
                }
            }
        }

        private void handleSelectAll() {
            if (this.mEditFlag) {
                this.mActions.onAction(EditStyledText.MODE_SELECTALL);
            }
        }

        private void selectAll() {
            Selection.selectAll(this.mEST.getText());
            this.mCurStart = this.mEST.getSelectionStart();
            this.mCurEnd = this.mEST.getSelectionEnd();
            this.mMode = EditStyledText.MODE_SELECT;
            this.mState = EditStyledText.STATE_SELECT_FIX;
        }

        private void resetEdit() {
            endEdit();
            this.mEditFlag = EditStyledText.DBG;
            this.mEST.notifyStateChanged(this.mMode, this.mState);
        }

        private void setSelection() {
            Log.d(LOG_TAG, "--- onSelect:" + this.mCurStart + "," + this.mCurEnd);
            if (this.mCurStart < 0 || this.mCurStart > this.mEST.getText().length() || this.mCurEnd < 0 || this.mCurEnd > this.mEST.getText().length()) {
                Log.e(LOG_TAG, "Select is on, but cursor positions are illigal.:" + this.mEST.getText().length() + "," + this.mCurStart + "," + this.mCurEnd);
            } else if (this.mCurStart < this.mCurEnd) {
                this.mEST.setSelection(this.mCurStart, this.mCurEnd);
                this.mState = EditStyledText.STATE_SELECTED;
            } else if (this.mCurStart > this.mCurEnd) {
                this.mEST.setSelection(this.mCurEnd, this.mCurStart);
                this.mState = EditStyledText.STATE_SELECTED;
            } else {
                this.mState = EditStyledText.STATE_SELECT_ON;
            }
        }

        private void unsetSelect() {
            Log.d(LOG_TAG, "--- offSelect");
            EditStyledText.stopSelecting(this.mEST, this.mEST.getText());
            int currpos = this.mEST.getSelectionStart();
            this.mEST.setSelection(currpos, currpos);
            this.mState = EditStyledText.STATE_SELECT_OFF;
        }

        private void setSelectStartPos() {
            Log.d(LOG_TAG, "--- setSelectStartPos");
            this.mCurStart = this.mEST.getSelectionStart();
            this.mState = EditStyledText.STATE_SELECT_ON;
        }

        private void setSelectEndPos() {
            if (this.mEST.getSelectionEnd() == this.mCurStart) {
                setEndPos(this.mEST.getSelectionStart());
            } else {
                setEndPos(this.mEST.getSelectionEnd());
            }
        }

        public void setEndPos(int pos) {
            Log.d(LOG_TAG, "--- setSelectedEndPos:" + pos);
            this.mCurEnd = pos;
            setSelection();
        }

        private boolean isWaitingNextAction() {
            Log.d(LOG_TAG, "--- waitingNext:" + this.mCurStart + "," + this.mCurEnd + "," + this.mState);
            if (this.mCurStart == this.mCurEnd && this.mState == EditStyledText.STATE_SELECT_FIX) {
                waitSelection();
                return EditStyledText.DBG;
            }
            resumeSelection();
            return false;
        }

        private void waitSelection() {
            Log.d(LOG_TAG, "--- waitSelection");
            this.mWaitInputFlag = EditStyledText.DBG;
            if (this.mCurStart == this.mCurEnd) {
                this.mState = EditStyledText.STATE_SELECT_ON;
            } else {
                this.mState = EditStyledText.STATE_SELECTED;
            }
            EditStyledText.startSelecting(this.mEST, this.mEST.getText());
        }

        private void resumeSelection() {
            Log.d(LOG_TAG, "--- resumeSelection");
            this.mWaitInputFlag = false;
            this.mState = EditStyledText.STATE_SELECT_FIX;
            EditStyledText.stopSelecting(this.mEST, this.mEST.getText());
        }

        private boolean isTextSelected() {
            return (this.mState == EditStyledText.STATE_SELECTED || this.mState == EditStyledText.STATE_SELECT_FIX) ? EditStyledText.DBG : false;
        }

        private void setStyledTextSpan(Object span, int start, int end) {
            Log.d(LOG_TAG, "--- setStyledTextSpan:" + this.mMode + "," + start + "," + end);
            int min = Math.min(start, end);
            int max = Math.max(start, end);
            this.mEST.getText().setSpan(span, min, max, 33);
            Selection.setSelection(this.mEST.getText(), max);
        }

        private void setLineStyledTextSpan(Object span) {
            int min = Math.min(this.mCurStart, this.mCurEnd);
            int max = Math.max(this.mCurStart, this.mCurEnd);
            int current = this.mEST.getSelectionStart();
            int start = findLineStart(this.mEST.getText(), min);
            int end = findLineEnd(this.mEST.getText(), max);
            if (start == end) {
                this.mEST.getText().insert(end, "\n");
                setStyledTextSpan(span, start, end + EditStyledText.STATE_SELECT_ON);
            } else {
                setStyledTextSpan(span, start, end);
            }
            Selection.setSelection(this.mEST.getText(), current);
        }

        private void changeSizeSelectedText(int size) {
            if (this.mCurStart != this.mCurEnd) {
                setStyledTextSpan(new AbsoluteSizeSpan(size), this.mCurStart, this.mCurEnd);
            } else {
                Log.e(LOG_TAG, "---changeSize: Size of the span is zero");
            }
        }

        private void changeColorSelectedText(int color) {
            if (this.mCurStart != this.mCurEnd) {
                setStyledTextSpan(new ForegroundColorSpan(color), this.mCurStart, this.mCurEnd);
            } else {
                Log.e(LOG_TAG, "---changeColor: Size of the span is zero");
            }
        }

        private void changeAlign(Alignment align) {
            setLineStyledTextSpan(new Standard(align));
        }

        private void addTelop() {
            addMarquee(EditStyledText.STATE_SELECT_ON);
        }

        private void addSwing() {
            addMarquee(EditStyledText.STATE_SELECT_OFF);
        }

        private void addMarquee(int marquee) {
            Log.d(LOG_TAG, "--- addMarquee:" + marquee);
            setLineStyledTextSpan(new MarqueeSpan(marquee, this.mEST.getBackgroundColor()));
        }

        private void insertImageSpan(DynamicDrawableSpan span, int curpos) {
            Log.d(LOG_TAG, "--- insertImageSpan:");
            if (span == null || span.getDrawable() == null) {
                Log.e(LOG_TAG, "--- insertImageSpan: null span was inserted");
                this.mEST.sendHintMessage(EditStyledText.MODE_SELECT);
                return;
            }
            this.mEST.getText().insert(curpos, "\ufffc");
            this.mEST.getText().setSpan(span, curpos, curpos + EditStyledText.STATE_SELECT_ON, 33);
            this.mEST.notifyStateChanged(this.mMode, this.mState);
        }

        private int findLineStart(Editable text, int current) {
            int pos = current;
            while (pos > 0 && text.charAt(pos - 1) != '\n') {
                pos--;
            }
            Log.d(LOG_TAG, "--- findLineStart:" + current + "," + text.length() + "," + pos);
            return pos;
        }

        private int findLineEnd(Editable text, int current) {
            int pos = current;
            while (pos < text.length()) {
                if (text.charAt(pos) == '\n') {
                    pos += EditStyledText.STATE_SELECT_ON;
                    break;
                }
                pos += EditStyledText.STATE_SELECT_ON;
            }
            Log.d(LOG_TAG, "--- findLineEnd:" + current + "," + text.length() + "," + pos);
            return pos;
        }

        private void dumpSpannableString(CharSequence txt) {
            if (txt instanceof Spannable) {
                Spannable spannable = (Spannable) txt;
                int len = spannable.length();
                Log.d(EditStyledText.TAG, "--- dumpSpannableString, txt:" + spannable + ", len:" + len);
                Object[] arr$ = spannable.getSpans(EditStyledText.STATE_SELECT_OFF, len, Object.class);
                int len$ = arr$.length;
                for (int i$ = EditStyledText.STATE_SELECT_OFF; i$ < len$; i$ += EditStyledText.STATE_SELECT_ON) {
                    Object style = arr$[i$];
                    Log.d(EditStyledText.TAG, "--- dumpSpannableString, class:" + style + "," + spannable.getSpanStart(style) + "," + spannable.getSpanEnd(style) + "," + spannable.getSpanFlags(style));
                }
            }
        }

        public void showSoftKey() {
            showSoftKey(this.mEST.getSelectionStart(), this.mEST.getSelectionEnd());
        }

        public void showSoftKey(int oldSelStart, int oldSelEnd) {
            Log.d(LOG_TAG, "--- showsoftkey");
            if (this.mEST.isFocused() && !isSoftKeyBlocked()) {
                this.mSkr.mNewStart = Selection.getSelectionStart(this.mEST.getText());
                this.mSkr.mNewEnd = Selection.getSelectionEnd(this.mEST.getText());
                if (((InputMethodManager) EditStyledText.this.getContext().getSystemService("input_method")).showSoftInput(this.mEST, EditStyledText.STATE_SELECT_OFF, this.mSkr) && this.mSkr != null) {
                    Selection.setSelection(EditStyledText.this.getText(), oldSelStart, oldSelEnd);
                }
            }
        }

        public void hideSoftKey() {
            Log.d(LOG_TAG, "--- hidesoftkey");
            if (this.mEST.isFocused()) {
                this.mSkr.mNewStart = Selection.getSelectionStart(this.mEST.getText());
                this.mSkr.mNewEnd = Selection.getSelectionEnd(this.mEST.getText());
                ((InputMethodManager) this.mEST.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mEST.getWindowToken(), EditStyledText.STATE_SELECT_OFF, this.mSkr);
            }
        }

        public void blockSoftKey() {
            Log.d(LOG_TAG, "--- blockSoftKey:");
            hideSoftKey();
            this.mSoftKeyBlockFlag = EditStyledText.DBG;
        }

        public void unblockSoftKey() {
            Log.d(LOG_TAG, "--- unblockSoftKey:");
            this.mSoftKeyBlockFlag = false;
        }
    }

    private class MenuHandler implements OnMenuItemClickListener {
        private MenuHandler() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            return EditStyledText.this.onTextContextMenuItem(item.getItemId());
        }
    }

    public static class SavedStyledTextState extends BaseSavedState {
        public int mBackgroundColor;

        SavedStyledTextState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.mBackgroundColor);
        }

        public String toString() {
            return "EditStyledText.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " bgcolor=" + this.mBackgroundColor + "}";
        }
    }

    private static class SoftKeyReceiver extends ResultReceiver {
        EditStyledText mEST;
        int mNewEnd;
        int mNewStart;

        SoftKeyReceiver(EditStyledText est) {
            super(est.getHandler());
            this.mEST = est;
        }

        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode != EditStyledText.STATE_SELECTED) {
                Selection.setSelection(this.mEST.getText(), this.mNewStart, this.mNewEnd);
            }
        }
    }

    private static class StyledTextArrowKeyMethod extends ArrowKeyMovementMethod {
        String LOG_TAG;
        EditorManager mManager;

        StyledTextArrowKeyMethod(EditorManager manager) {
            this.LOG_TAG = "StyledTextArrowKeyMethod";
            this.mManager = manager;
        }

        public boolean onKeyDown(TextView widget, Spannable buffer, int keyCode, KeyEvent event) {
            Log.d(this.LOG_TAG, "---onkeydown:" + keyCode);
            this.mManager.unsetTextComposingMask();
            if (this.mManager.getSelectState() == EditStyledText.STATE_SELECT_ON || this.mManager.getSelectState() == EditStyledText.STATE_SELECTED) {
                return executeDown(widget, buffer, keyCode);
            }
            return super.onKeyDown(widget, buffer, keyCode, event);
        }

        private int getEndPos(TextView widget) {
            if (widget.getSelectionStart() == this.mManager.getSelectionStart()) {
                return widget.getSelectionEnd();
            }
            return widget.getSelectionStart();
        }

        protected boolean up(TextView widget, Spannable buffer) {
            Log.d(this.LOG_TAG, "--- up:");
            Layout layout = widget.getLayout();
            int end = getEndPos(widget);
            int line = layout.getLineForOffset(end);
            if (line > 0) {
                int to;
                if (layout.getParagraphDirection(line) == layout.getParagraphDirection(line - 1)) {
                    to = layout.getOffsetForHorizontal(line - 1, layout.getPrimaryHorizontal(end));
                } else {
                    to = layout.getLineStart(line - 1);
                }
                this.mManager.setEndPos(to);
                this.mManager.onCursorMoved();
            }
            return EditStyledText.DBG;
        }

        protected boolean down(TextView widget, Spannable buffer) {
            Log.d(this.LOG_TAG, "--- down:");
            Layout layout = widget.getLayout();
            int end = getEndPos(widget);
            int line = layout.getLineForOffset(end);
            if (line < layout.getLineCount() - 1) {
                int to;
                if (layout.getParagraphDirection(line) == layout.getParagraphDirection(line + EditStyledText.STATE_SELECT_ON)) {
                    to = layout.getOffsetForHorizontal(line + EditStyledText.STATE_SELECT_ON, layout.getPrimaryHorizontal(end));
                } else {
                    to = layout.getLineStart(line + EditStyledText.STATE_SELECT_ON);
                }
                this.mManager.setEndPos(to);
                this.mManager.onCursorMoved();
            }
            return EditStyledText.DBG;
        }

        protected boolean left(TextView widget, Spannable buffer) {
            Log.d(this.LOG_TAG, "--- left:");
            this.mManager.setEndPos(widget.getLayout().getOffsetToLeftOf(getEndPos(widget)));
            this.mManager.onCursorMoved();
            return EditStyledText.DBG;
        }

        protected boolean right(TextView widget, Spannable buffer) {
            Log.d(this.LOG_TAG, "--- right:");
            this.mManager.setEndPos(widget.getLayout().getOffsetToRightOf(getEndPos(widget)));
            this.mManager.onCursorMoved();
            return EditStyledText.DBG;
        }

        private boolean executeDown(TextView widget, Spannable buffer, int keyCode) {
            Log.d(this.LOG_TAG, "--- executeDown: " + keyCode);
            switch (keyCode) {
                case EditStyledText.MODE_TEXTVIEWFUNCTION /*19*/:
                    return false | up(widget, buffer);
                case EditStyledText.MODE_START_EDIT /*20*/:
                    return false | down(widget, buffer);
                case EditStyledText.MODE_END_EDIT /*21*/:
                    return false | left(widget, buffer);
                case EditStyledText.MODE_RESET /*22*/:
                    return false | right(widget, buffer);
                case EditStyledText.MODE_SHOW_MENU /*23*/:
                    this.mManager.onFixSelectedItem();
                    return EditStyledText.DBG;
                default:
                    return false;
            }
        }
    }

    private class StyledTextConverter {
        private EditStyledText mEST;
        private StyledTextHtmlConverter mHtml;

        public StyledTextConverter(EditStyledText est, StyledTextHtmlConverter html) {
            this.mEST = est;
            this.mHtml = html;
        }

        public void setStyledTextHtmlConverter(StyledTextHtmlConverter html) {
            this.mHtml = html;
        }

        public String getHtml(boolean escapeFlag) {
            this.mEST.clearComposingText();
            this.mEST.onRefreshZeoWidthChar();
            String htmlBody = this.mHtml.toHtml(this.mEST.getText(), escapeFlag);
            Log.d(EditStyledText.TAG, "--- getHtml:" + htmlBody);
            return htmlBody;
        }

        public String getPreviewHtml() {
            this.mEST.clearComposingText();
            this.mEST.onRefreshZeoWidthChar();
            String html = this.mHtml.toHtml(this.mEST.getText(), EditStyledText.DBG, EditStyledText.this.getMaxImageWidthDip(), EditStyledText.this.getPaddingScale());
            int bgColor = this.mEST.getBackgroundColor();
            Object[] objArr = new Object[EditStyledText.MODE_COLOR];
            objArr[EditStyledText.STATE_SELECT_OFF] = Integer.valueOf(Color.red(bgColor));
            objArr[EditStyledText.STATE_SELECT_ON] = Integer.valueOf(Color.green(bgColor));
            objArr[EditStyledText.STATE_SELECTED] = Integer.valueOf(Color.blue(bgColor));
            objArr[EditStyledText.STATE_SELECT_FIX] = html;
            html = String.format("<body bgcolor=\"#%02X%02X%02X\">%s</body>", objArr);
            Log.d(EditStyledText.TAG, "--- getPreviewHtml:" + html + "," + this.mEST.getWidth());
            return html;
        }

        public void getUriArray(ArrayList<Uri> uris, Editable text) {
            uris.clear();
            Log.d(EditStyledText.TAG, "--- getUriArray:");
            int len = text.length();
            int i = EditStyledText.STATE_SELECT_OFF;
            while (i < text.length()) {
                int next = text.nextSpanTransition(i, len, ImageSpan.class);
                ImageSpan[] images = (ImageSpan[]) text.getSpans(i, next, ImageSpan.class);
                for (int j = EditStyledText.STATE_SELECT_OFF; j < images.length; j += EditStyledText.STATE_SELECT_ON) {
                    Log.d(EditStyledText.TAG, "--- getUriArray: foundArray" + images[j].getSource());
                    uris.add(Uri.parse(images[j].getSource()));
                }
                i = next;
            }
        }

        public void SetHtml(String html) {
            this.mEST.setText(this.mHtml.fromHtml(html, new ImageGetter() {
                public Drawable getDrawable(String src) {
                    Exception e;
                    Drawable drawable;
                    Log.d(EditStyledText.TAG, "--- sethtml: src=" + src);
                    if (!src.startsWith("content://")) {
                        return null;
                    }
                    Uri uri = Uri.parse(src);
                    try {
                        Bitmap bitmap;
                        System.gc();
                        InputStream is = StyledTextConverter.this.mEST.getContext().getContentResolver().openInputStream(uri);
                        Options opt = new Options();
                        opt.inJustDecodeBounds = EditStyledText.DBG;
                        BitmapFactory.decodeStream(is, null, opt);
                        is.close();
                        is = StyledTextConverter.this.mEST.getContext().getContentResolver().openInputStream(uri);
                        int width = opt.outWidth;
                        int height = opt.outHeight;
                        if (opt.outWidth > EditStyledText.this.getMaxImageWidthPx()) {
                            width = EditStyledText.this.getMaxImageWidthPx();
                            height = (EditStyledText.this.getMaxImageWidthPx() * height) / opt.outWidth;
                            bitmap = BitmapFactory.decodeStream(is, new Rect(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_OFF, width, height), null);
                        } else {
                            bitmap = BitmapFactory.decodeStream(is);
                        }
                        Drawable drawable2 = new BitmapDrawable(StyledTextConverter.this.mEST.getContext().getResources(), bitmap);
                        try {
                            drawable2.setBounds(EditStyledText.STATE_SELECT_OFF, EditStyledText.STATE_SELECT_OFF, width, height);
                            is.close();
                            return drawable2;
                        } catch (Exception e2) {
                            e = e2;
                            drawable = drawable2;
                        } catch (OutOfMemoryError e3) {
                            drawable = drawable2;
                            Log.e(EditStyledText.TAG, "OutOfMemoryError");
                            StyledTextConverter.this.mEST.setHint(EditStyledText.MODE_SELECT);
                            return null;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        Log.e(EditStyledText.TAG, "--- set html: Failed to loaded content " + uri, e);
                        return null;
                    } catch (OutOfMemoryError e5) {
                        Log.e(EditStyledText.TAG, "OutOfMemoryError");
                        StyledTextConverter.this.mEST.setHint(EditStyledText.MODE_SELECT);
                        return null;
                    }
                }
            }, null));
        }
    }

    private static class StyledTextDialog {
        private static final int TYPE_BACKGROUND = 1;
        private static final int TYPE_FOREGROUND = 0;
        private AlertDialog mAlertDialog;
        private CharSequence[] mAlignNames;
        private CharSequence mAlignTitle;
        private Builder mBuilder;
        private CharSequence mColorDefaultMessage;
        private CharSequence[] mColorInts;
        private CharSequence[] mColorNames;
        private CharSequence mColorTitle;
        private EditStyledText mEST;
        private CharSequence[] mMarqueeNames;
        private CharSequence mMarqueeTitle;
        private CharSequence[] mSizeDisplayInts;
        private CharSequence[] mSizeNames;
        private CharSequence[] mSizeSendInts;
        private CharSequence mSizeTitle;

        public StyledTextDialog(EditStyledText est) {
            this.mEST = est;
        }

        public void setBuilder(Builder builder) {
            this.mBuilder = builder;
        }

        public void setColorAlertParams(CharSequence colortitle, CharSequence[] colornames, CharSequence[] colorInts, CharSequence defaultColorMessage) {
            this.mColorTitle = colortitle;
            this.mColorNames = colornames;
            this.mColorInts = colorInts;
            this.mColorDefaultMessage = defaultColorMessage;
        }

        public void setSizeAlertParams(CharSequence sizetitle, CharSequence[] sizenames, CharSequence[] sizedisplayints, CharSequence[] sizesendints) {
            this.mSizeTitle = sizetitle;
            this.mSizeNames = sizenames;
            this.mSizeDisplayInts = sizedisplayints;
            this.mSizeSendInts = sizesendints;
        }

        public void setAlignAlertParams(CharSequence aligntitle, CharSequence[] alignnames) {
            this.mAlignTitle = aligntitle;
            this.mAlignNames = alignnames;
        }

        public void setMarqueeAlertParams(CharSequence marqueetitle, CharSequence[] marqueenames) {
            this.mMarqueeTitle = marqueetitle;
            this.mMarqueeNames = marqueenames;
        }

        private boolean checkColorAlertParams() {
            Log.d(EditStyledText.TAG, "--- checkParams");
            if (this.mBuilder == null) {
                Log.e(EditStyledText.TAG, "--- builder is null.");
                return false;
            } else if (this.mColorTitle == null || this.mColorNames == null || this.mColorInts == null) {
                Log.e(EditStyledText.TAG, "--- color alert params are null.");
                return false;
            } else if (this.mColorNames.length == this.mColorInts.length) {
                return EditStyledText.DBG;
            } else {
                Log.e(EditStyledText.TAG, "--- the length of color alert params are different.");
                return false;
            }
        }

        private boolean checkSizeAlertParams() {
            Log.d(EditStyledText.TAG, "--- checkParams");
            if (this.mBuilder == null) {
                Log.e(EditStyledText.TAG, "--- builder is null.");
                return false;
            } else if (this.mSizeTitle == null || this.mSizeNames == null || this.mSizeDisplayInts == null || this.mSizeSendInts == null) {
                Log.e(EditStyledText.TAG, "--- size alert params are null.");
                return false;
            } else if (this.mSizeNames.length == this.mSizeDisplayInts.length || this.mSizeSendInts.length == this.mSizeDisplayInts.length) {
                return EditStyledText.DBG;
            } else {
                Log.e(EditStyledText.TAG, "--- the length of size alert params are different.");
                return false;
            }
        }

        private boolean checkAlignAlertParams() {
            Log.d(EditStyledText.TAG, "--- checkAlignAlertParams");
            if (this.mBuilder == null) {
                Log.e(EditStyledText.TAG, "--- builder is null.");
                return false;
            } else if (this.mAlignTitle != null) {
                return EditStyledText.DBG;
            } else {
                Log.e(EditStyledText.TAG, "--- align alert params are null.");
                return false;
            }
        }

        private boolean checkMarqueeAlertParams() {
            Log.d(EditStyledText.TAG, "--- checkMarqueeAlertParams");
            if (this.mBuilder == null) {
                Log.e(EditStyledText.TAG, "--- builder is null.");
                return false;
            } else if (this.mMarqueeTitle != null) {
                return EditStyledText.DBG;
            } else {
                Log.e(EditStyledText.TAG, "--- Marquee alert params are null.");
                return false;
            }
        }

        private void buildDialogue(CharSequence title, CharSequence[] names, OnClickListener l) {
            this.mBuilder.setTitle(title);
            this.mBuilder.setIcon(EditStyledText.STATE_SELECT_OFF);
            this.mBuilder.setPositiveButton(null, null);
            this.mBuilder.setNegativeButton(17039360, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    StyledTextDialog.this.mEST.onStartEdit();
                }
            });
            this.mBuilder.setItems(names, l);
            this.mBuilder.setView(null);
            this.mBuilder.setCancelable(EditStyledText.DBG);
            this.mBuilder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    Log.d(EditStyledText.TAG, "--- oncancel");
                    StyledTextDialog.this.mEST.onStartEdit();
                }
            });
            this.mBuilder.show();
        }

        private void buildAndShowColorDialogue(int type, CharSequence title, int[] colors) {
            int BUTTON_SIZE = this.mEST.dipToPx(50);
            int BUTTON_MERGIN = this.mEST.dipToPx(EditStyledText.STATE_SELECTED);
            int BUTTON_PADDING = this.mEST.dipToPx(EditStyledText.MODE_IMAGE);
            this.mBuilder.setTitle(title);
            this.mBuilder.setIcon(EditStyledText.STATE_SELECT_OFF);
            this.mBuilder.setPositiveButton(null, null);
            this.mBuilder.setNegativeButton(17039360, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    StyledTextDialog.this.mEST.onStartEdit();
                }
            });
            this.mBuilder.setItems(null, null);
            LinearLayout verticalLayout = new LinearLayout(this.mEST.getContext());
            verticalLayout.setOrientation(TYPE_BACKGROUND);
            verticalLayout.setGravity(TYPE_BACKGROUND);
            verticalLayout.setPadding(BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING, BUTTON_PADDING);
            LinearLayout horizontalLayout = null;
            for (int i = EditStyledText.STATE_SELECT_OFF; i < colors.length; i += TYPE_BACKGROUND) {
                if (i % EditStyledText.MODE_SELECT == 0) {
                    horizontalLayout = new LinearLayout(this.mEST.getContext());
                    verticalLayout.addView(horizontalLayout);
                }
                Button button = new Button(this.mEST.getContext());
                button.setHeight(BUTTON_SIZE);
                button.setWidth(BUTTON_SIZE);
                button.setBackgroundDrawable(new ColorPaletteDrawable(colors[i], BUTTON_SIZE, BUTTON_SIZE, BUTTON_MERGIN));
                button.setDrawingCacheBackgroundColor(colors[i]);
                if (type == 0) {
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            StyledTextDialog.this.mEST.setItemColor(view.getDrawingCacheBackgroundColor());
                            if (StyledTextDialog.this.mAlertDialog != null) {
                                StyledTextDialog.this.mAlertDialog.setView(null);
                                StyledTextDialog.this.mAlertDialog.dismiss();
                                StyledTextDialog.this.mAlertDialog = null;
                                return;
                            }
                            Log.e(EditStyledText.TAG, "--- buildAndShowColorDialogue: can't find alertDialog");
                        }
                    });
                } else if (type == TYPE_BACKGROUND) {
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            StyledTextDialog.this.mEST.setBackgroundColor(view.getDrawingCacheBackgroundColor());
                            if (StyledTextDialog.this.mAlertDialog != null) {
                                StyledTextDialog.this.mAlertDialog.setView(null);
                                StyledTextDialog.this.mAlertDialog.dismiss();
                                StyledTextDialog.this.mAlertDialog = null;
                                return;
                            }
                            Log.e(EditStyledText.TAG, "--- buildAndShowColorDialogue: can't find alertDialog");
                        }
                    });
                }
                horizontalLayout.addView(button);
            }
            if (type == TYPE_BACKGROUND) {
                this.mBuilder.setPositiveButton(this.mColorDefaultMessage, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        StyledTextDialog.this.mEST.setBackgroundColor(EditStyledText.DEFAULT_TRANSPARENT_COLOR);
                    }
                });
            } else if (type == 0) {
                this.mBuilder.setPositiveButton(this.mColorDefaultMessage, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        StyledTextDialog.this.mEST.setItemColor(EditStyledText.DEFAULT_FOREGROUND_COLOR);
                    }
                });
            }
            this.mBuilder.setView(verticalLayout);
            this.mBuilder.setCancelable(EditStyledText.DBG);
            this.mBuilder.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    StyledTextDialog.this.mEST.onStartEdit();
                }
            });
            this.mAlertDialog = this.mBuilder.show();
        }

        private void onShowForegroundColorAlertDialog() {
            Log.d(EditStyledText.TAG, "--- onShowForegroundColorAlertDialog");
            if (checkColorAlertParams()) {
                int[] colorints = new int[this.mColorInts.length];
                for (int i = EditStyledText.STATE_SELECT_OFF; i < colorints.length; i += TYPE_BACKGROUND) {
                    colorints[i] = Integer.parseInt((String) this.mColorInts[i], EditStyledText.MODE_BGCOLOR) - 16777216;
                }
                buildAndShowColorDialogue(EditStyledText.STATE_SELECT_OFF, this.mColorTitle, colorints);
            }
        }

        private void onShowBackgroundColorAlertDialog() {
            Log.d(EditStyledText.TAG, "--- onShowBackgroundColorAlertDialog");
            if (checkColorAlertParams()) {
                int[] colorInts = new int[this.mColorInts.length];
                for (int i = EditStyledText.STATE_SELECT_OFF; i < colorInts.length; i += TYPE_BACKGROUND) {
                    colorInts[i] = Integer.parseInt((String) this.mColorInts[i], EditStyledText.MODE_BGCOLOR) - 16777216;
                }
                buildAndShowColorDialogue(TYPE_BACKGROUND, this.mColorTitle, colorInts);
            }
        }

        private void onShowSizeAlertDialog() {
            Log.d(EditStyledText.TAG, "--- onShowSizeAlertDialog");
            if (checkSizeAlertParams()) {
                buildDialogue(this.mSizeTitle, this.mSizeNames, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(EditStyledText.TAG, "mBuilder.onclick:" + which);
                        StyledTextDialog.this.mEST.setItemSize(StyledTextDialog.this.mEST.dipToPx(Integer.parseInt((String) StyledTextDialog.this.mSizeDisplayInts[which])));
                    }
                });
            }
        }

        private void onShowAlignAlertDialog() {
            Log.d(EditStyledText.TAG, "--- onShowAlignAlertDialog");
            if (checkAlignAlertParams()) {
                buildDialogue(this.mAlignTitle, this.mAlignNames, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Alignment align = Alignment.ALIGN_NORMAL;
                        switch (which) {
                            case EditStyledText.STATE_SELECT_OFF /*0*/:
                                align = Alignment.ALIGN_NORMAL;
                                break;
                            case StyledTextDialog.TYPE_BACKGROUND /*1*/:
                                align = Alignment.ALIGN_CENTER;
                                break;
                            case EditStyledText.STATE_SELECTED /*2*/:
                                align = Alignment.ALIGN_OPPOSITE;
                                break;
                            default:
                                Log.e(EditStyledText.TAG, "--- onShowAlignAlertDialog: got illigal align.");
                                break;
                        }
                        StyledTextDialog.this.mEST.setAlignment(align);
                    }
                });
            }
        }

        private void onShowMarqueeAlertDialog() {
            Log.d(EditStyledText.TAG, "--- onShowMarqueeAlertDialog");
            if (checkMarqueeAlertParams()) {
                buildDialogue(this.mMarqueeTitle, this.mMarqueeNames, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(EditStyledText.TAG, "mBuilder.onclick:" + which);
                        StyledTextDialog.this.mEST.setMarquee(which);
                    }
                });
            }
        }
    }

    public interface StyledTextHtmlConverter {
        Spanned fromHtml(String str);

        Spanned fromHtml(String str, ImageGetter imageGetter, TagHandler tagHandler);

        String toHtml(Spanned spanned);

        String toHtml(Spanned spanned, boolean z);

        String toHtml(Spanned spanned, boolean z, int i, float f);
    }

    private class StyledTextHtmlStandard implements StyledTextHtmlConverter {
        private StyledTextHtmlStandard() {
        }

        public String toHtml(Spanned text) {
            return Html.toHtml(text);
        }

        public String toHtml(Spanned text, boolean escapeNonAsciiChar) {
            return Html.toHtml(text);
        }

        public String toHtml(Spanned text, boolean escapeNonAsciiChar, int width, float scale) {
            return Html.toHtml(text);
        }

        public Spanned fromHtml(String source) {
            return Html.fromHtml(source);
        }

        public Spanned fromHtml(String source, ImageGetter imageGetter, TagHandler tagHandler) {
            return Html.fromHtml(source, imageGetter, tagHandler);
        }
    }

    public static class StyledTextInputConnection extends InputConnectionWrapper {
        EditStyledText mEST;

        public StyledTextInputConnection(InputConnection target, EditStyledText est) {
            super(target, EditStyledText.DBG);
            this.mEST = est;
        }

        public boolean commitText(CharSequence text, int newCursorPosition) {
            Log.d(EditStyledText.TAG, "--- commitText:");
            this.mEST.mManager.unsetTextComposingMask();
            return super.commitText(text, newCursorPosition);
        }

        public boolean finishComposingText() {
            Log.d(EditStyledText.TAG, "--- finishcomposing:");
            if (!(this.mEST.isSoftKeyBlocked() || this.mEST.isButtonsFocused() || this.mEST.isEditting())) {
                this.mEST.onEndEdit();
            }
            return super.finishComposingText();
        }
    }

    static {
        SELECTING = new Concrete();
    }

    public EditStyledText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mPaddingScale = 0.0f;
        init();
    }

    public EditStyledText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaddingScale = 0.0f;
        init();
    }

    public EditStyledText(Context context) {
        super(context);
        this.mPaddingScale = 0.0f;
        init();
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean superResult;
        if (event.getAction() == STATE_SELECT_ON) {
            cancelLongPress();
            boolean editting = isEditting();
            if (!editting) {
                onStartEdit();
            }
            int oldSelStart = Selection.getSelectionStart(getText());
            int oldSelEnd = Selection.getSelectionEnd(getText());
            superResult = super.onTouchEvent(event);
            if (isFocused() && getSelectState() == 0) {
                if (editting) {
                    this.mManager.showSoftKey(Selection.getSelectionStart(getText()), Selection.getSelectionEnd(getText()));
                } else {
                    this.mManager.showSoftKey(oldSelStart, oldSelEnd);
                }
            }
            this.mManager.onCursorMoved();
            this.mManager.unsetTextComposingMask();
        } else {
            superResult = super.onTouchEvent(event);
        }
        sendOnTouchEvent(event);
        return superResult;
    }

    public Parcelable onSaveInstanceState() {
        SavedStyledTextState ss = new SavedStyledTextState(super.onSaveInstanceState());
        ss.mBackgroundColor = this.mManager.getBackgroundColor();
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedStyledTextState) {
            SavedStyledTextState ss = (SavedStyledTextState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            setBackgroundColor(ss.mBackgroundColor);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mManager != null) {
            this.mManager.onRefreshStyles();
        }
    }

    public boolean onTextContextMenuItem(int id) {
        boolean selection;
        if (getSelectionStart() != getSelectionEnd()) {
            selection = DBG;
        } else {
            selection = false;
        }
        switch (id) {
            case ID_HORIZONTALLINE /*16776961*/:
                onInsertHorizontalLine();
                return DBG;
            case ID_CLEARSTYLES /*16776962*/:
                onClearStyles();
                return DBG;
            case ID_SHOWEDIT /*16776963*/:
                onStartEdit();
                return DBG;
            case ID_HIDEEDIT /*16776964*/:
                onEndEdit();
                return DBG;
            case ID_SELECT_ALL /*16908319*/:
                onStartSelectAll();
                return DBG;
            case ID_CUT /*16908320*/:
                if (selection) {
                    onStartCut();
                    return DBG;
                }
                this.mManager.onStartSelectAll(false);
                onStartCut();
                return DBG;
            case ID_COPY /*16908321*/:
                if (selection) {
                    onStartCopy();
                    return DBG;
                }
                this.mManager.onStartSelectAll(false);
                onStartCopy();
                return DBG;
            case ID_PASTE /*16908322*/:
                onStartPaste();
                return DBG;
            case ID_START_SELECTING_TEXT /*16908328*/:
                onStartSelect();
                this.mManager.blockSoftKey();
                break;
            case ID_STOP_SELECTING_TEXT /*16908329*/:
                onFixSelectedItem();
                break;
        }
        return super.onTextContextMenuItem(id);
    }

    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
        MenuHandler handler = new MenuHandler();
        if (STR_HORIZONTALLINE != null) {
            menu.add(STATE_SELECT_OFF, ID_HORIZONTALLINE, STATE_SELECT_OFF, STR_HORIZONTALLINE).setOnMenuItemClickListener(handler);
        }
        if (isStyledText() && STR_CLEARSTYLES != null) {
            menu.add(STATE_SELECT_OFF, ID_CLEARSTYLES, STATE_SELECT_OFF, STR_CLEARSTYLES).setOnMenuItemClickListener(handler);
        }
        if (this.mManager.canPaste()) {
            menu.add(STATE_SELECT_OFF, ID_PASTE, STATE_SELECT_OFF, STR_PASTE).setOnMenuItemClickListener(handler).setAlphabeticShortcut('v');
        }
    }

    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        if (this.mManager != null) {
            this.mManager.updateSpanNextToCursor(getText(), start, before, after);
            this.mManager.updateSpanPreviousFromCursor(getText(), start, before, after);
            if (after > before) {
                this.mManager.setTextComposingMask(start, start + after);
            } else if (before < after) {
                this.mManager.unsetTextComposingMask();
            }
            if (this.mManager.isWaitInput()) {
                if (after > before) {
                    this.mManager.onCursorMoved();
                    onFixSelectedItem();
                } else if (after < before) {
                    this.mManager.onAction(MODE_RESET);
                }
            }
        }
        super.onTextChanged(text, start, before, after);
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        this.mInputConnection = new StyledTextInputConnection(super.onCreateInputConnection(outAttrs), this);
        return this.mInputConnection;
    }

    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            onStartEdit();
        } else if (!isButtonsFocused()) {
            onEndEdit();
        }
    }

    private void init() {
        this.mConverter = new StyledTextConverter(this, new StyledTextHtmlStandard());
        this.mDialog = new StyledTextDialog(this);
        this.mManager = new EditorManager(this, this.mDialog);
        setMovementMethod(new StyledTextArrowKeyMethod(this.mManager));
        this.mDefaultBackground = getBackground();
        requestFocus();
    }

    public void setStyledTextHtmlConverter(StyledTextHtmlConverter html) {
        this.mConverter.setStyledTextHtmlConverter(html);
    }

    public void addEditStyledTextListener(EditStyledTextNotifier estInterface) {
        if (this.mESTNotifiers == null) {
            this.mESTNotifiers = new ArrayList();
        }
        this.mESTNotifiers.add(estInterface);
    }

    public void removeEditStyledTextListener(EditStyledTextNotifier estInterface) {
        if (this.mESTNotifiers != null) {
            int i = this.mESTNotifiers.indexOf(estInterface);
            if (i > 0) {
                this.mESTNotifiers.remove(i);
            }
        }
    }

    private void sendOnTouchEvent(MotionEvent event) {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                ((EditStyledTextNotifier) i$.next()).sendOnTouchEvent(event);
            }
        }
    }

    public boolean isButtonsFocused() {
        boolean retval = false;
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                retval |= ((EditStyledTextNotifier) i$.next()).isButtonsFocused();
            }
        }
        return retval;
    }

    private void showPreview() {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                if (((EditStyledTextNotifier) i$.next()).showPreview()) {
                    return;
                }
            }
        }
    }

    private void cancelViewManagers() {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                ((EditStyledTextNotifier) i$.next()).cancelViewManager();
            }
        }
    }

    private void showInsertImageSelectAlertDialog() {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                if (((EditStyledTextNotifier) i$.next()).showInsertImageSelectAlertDialog()) {
                    return;
                }
            }
        }
    }

    private void showMenuAlertDialog() {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                if (((EditStyledTextNotifier) i$.next()).showMenuAlertDialog()) {
                    return;
                }
            }
        }
    }

    private void sendHintMessage(int msgId) {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                ((EditStyledTextNotifier) i$.next()).sendHintMsg(msgId);
            }
        }
    }

    private void notifyStateChanged(int mode, int state) {
        if (this.mESTNotifiers != null) {
            Iterator i$ = this.mESTNotifiers.iterator();
            while (i$.hasNext()) {
                ((EditStyledTextNotifier) i$.next()).onStateChanged(mode, state);
            }
        }
    }

    public void onStartEdit() {
        this.mManager.onAction(MODE_START_EDIT);
    }

    public void onEndEdit() {
        this.mManager.onAction(MODE_END_EDIT);
    }

    public void onResetEdit() {
        this.mManager.onAction(MODE_RESET);
    }

    public void onStartCopy() {
        this.mManager.onAction(STATE_SELECT_ON);
    }

    public void onStartCut() {
        this.mManager.onAction(MODE_CUT);
    }

    public void onStartPaste() {
        this.mManager.onAction(STATE_SELECTED);
    }

    public void onStartSize() {
        this.mManager.onAction(STATE_SELECT_FIX);
    }

    public void onStartColor() {
        this.mManager.onAction(MODE_COLOR);
    }

    public void onStartBackgroundColor() {
        this.mManager.onAction(MODE_BGCOLOR);
    }

    public void onStartAlign() {
        this.mManager.onAction(MODE_ALIGN);
    }

    public void onStartTelop() {
        this.mManager.onAction(MODE_TELOP);
    }

    public void onStartSwing() {
        this.mManager.onAction(MODE_SWING);
    }

    public void onStartMarquee() {
        this.mManager.onAction(MODE_MARQUEE);
    }

    public void onStartSelect() {
        this.mManager.onStartSelect(DBG);
    }

    public void onStartSelectAll() {
        this.mManager.onStartSelectAll(DBG);
    }

    public void onStartShowPreview() {
        this.mManager.onAction(MODE_PREVIEW);
    }

    public void onStartShowMenuAlertDialog() {
        this.mManager.onStartShowMenuAlertDialog();
    }

    public void onStartAction(int mode, boolean notifyStateChanged) {
        this.mManager.onAction(mode, notifyStateChanged);
    }

    public void onFixSelectedItem() {
        this.mManager.onFixSelectedItem();
    }

    public void onInsertImage() {
        this.mManager.onAction(MODE_IMAGE);
    }

    public void onInsertImage(Uri uri) {
        this.mManager.onInsertImage(uri);
    }

    public void onInsertImage(int resId) {
        this.mManager.onInsertImage(resId);
    }

    public void onInsertHorizontalLine() {
        this.mManager.onAction(MODE_HORIZONTALLINE);
    }

    public void onClearStyles() {
        this.mManager.onClearStyles();
    }

    public void onBlockSoftKey() {
        this.mManager.blockSoftKey();
    }

    public void onUnblockSoftKey() {
        this.mManager.unblockSoftKey();
    }

    public void onCancelViewManagers() {
        this.mManager.onCancelViewManagers();
    }

    private void onRefreshStyles() {
        this.mManager.onRefreshStyles();
    }

    private void onRefreshZeoWidthChar() {
        this.mManager.onRefreshZeoWidthChar();
    }

    public void setItemSize(int size) {
        this.mManager.setItemSize(size, DBG);
    }

    public void setItemColor(int color) {
        this.mManager.setItemColor(color, DBG);
    }

    public void setAlignment(Alignment align) {
        this.mManager.setAlignment(align);
    }

    public void setBackgroundColor(int color) {
        if (color != DEFAULT_TRANSPARENT_COLOR) {
            super.setBackgroundColor(color);
        } else {
            setBackgroundDrawable(this.mDefaultBackground);
        }
        this.mManager.setBackgroundColor(color);
        onRefreshStyles();
    }

    public void setMarquee(int marquee) {
        this.mManager.setMarquee(marquee);
    }

    public void setHtml(String html) {
        this.mConverter.SetHtml(html);
    }

    public void setBuilder(Builder builder) {
        this.mDialog.setBuilder(builder);
    }

    public void setColorAlertParams(CharSequence colortitle, CharSequence[] colornames, CharSequence[] colorints, CharSequence transparent) {
        this.mDialog.setColorAlertParams(colortitle, colornames, colorints, transparent);
    }

    public void setSizeAlertParams(CharSequence sizetitle, CharSequence[] sizenames, CharSequence[] sizedisplayints, CharSequence[] sizesendints) {
        this.mDialog.setSizeAlertParams(sizetitle, sizenames, sizedisplayints, sizesendints);
    }

    public void setAlignAlertParams(CharSequence aligntitle, CharSequence[] alignnames) {
        this.mDialog.setAlignAlertParams(aligntitle, alignnames);
    }

    public void setMarqueeAlertParams(CharSequence marqueetitle, CharSequence[] marqueenames) {
        this.mDialog.setMarqueeAlertParams(marqueetitle, marqueenames);
    }

    public void setContextMenuStrings(CharSequence horizontalline, CharSequence clearstyles, CharSequence paste) {
        STR_HORIZONTALLINE = horizontalline;
        STR_CLEARSTYLES = clearstyles;
        STR_PASTE = paste;
    }

    public boolean isEditting() {
        return this.mManager.isEditting();
    }

    public boolean isStyledText() {
        return this.mManager.isStyledText();
    }

    public boolean isSoftKeyBlocked() {
        return this.mManager.isSoftKeyBlocked();
    }

    public int getEditMode() {
        return this.mManager.getEditMode();
    }

    public int getSelectState() {
        return this.mManager.getSelectState();
    }

    public String getHtml() {
        return this.mConverter.getHtml(DBG);
    }

    public String getHtml(boolean escapeFlag) {
        return this.mConverter.getHtml(escapeFlag);
    }

    public String getHtml(ArrayList<Uri> uris, boolean escapeFlag) {
        this.mConverter.getUriArray(uris, getText());
        return this.mConverter.getHtml(escapeFlag);
    }

    public String getPreviewHtml() {
        return this.mConverter.getPreviewHtml();
    }

    public int getBackgroundColor() {
        return this.mManager.getBackgroundColor();
    }

    public EditorManager getEditStyledTextManager() {
        return this.mManager;
    }

    public int getForegroundColor(int pos) {
        if (pos < 0 || pos > getText().length()) {
            return DEFAULT_FOREGROUND_COLOR;
        }
        ForegroundColorSpan[] spans = (ForegroundColorSpan[]) getText().getSpans(pos, pos, ForegroundColorSpan.class);
        if (spans.length > 0) {
            return spans[STATE_SELECT_OFF].getForegroundColor();
        }
        return DEFAULT_FOREGROUND_COLOR;
    }

    private void finishComposingText() {
        if (this.mInputConnection != null && !this.mManager.mTextIsFinishedFlag) {
            this.mInputConnection.finishComposingText();
            this.mManager.mTextIsFinishedFlag = DBG;
        }
    }

    private float getPaddingScale() {
        if (this.mPaddingScale <= 0.0f) {
            this.mPaddingScale = getContext().getResources().getDisplayMetrics().density;
        }
        return this.mPaddingScale;
    }

    private int dipToPx(int dip) {
        if (this.mPaddingScale <= 0.0f) {
            this.mPaddingScale = getContext().getResources().getDisplayMetrics().density;
        }
        return (int) (((double) (((float) dip) * getPaddingScale())) + 0.5d);
    }

    private int getMaxImageWidthDip() {
        return MAXIMAGEWIDTHDIP;
    }

    private int getMaxImageWidthPx() {
        return dipToPx(MAXIMAGEWIDTHDIP);
    }

    public void addAction(int mode, EditModeActionBase action) {
        this.mManager.addAction(mode, action);
    }

    public void addInputExtra(boolean create, String extra) {
        Bundle bundle = super.getInputExtras(create);
        if (bundle != null) {
            bundle.putBoolean(extra, DBG);
        }
    }

    private static void startSelecting(View view, Spannable content) {
        content.setSpan(SELECTING, STATE_SELECT_OFF, STATE_SELECT_OFF, PRESSED);
    }

    private static void stopSelecting(View view, Spannable content) {
        content.removeSpan(SELECTING);
    }
}
