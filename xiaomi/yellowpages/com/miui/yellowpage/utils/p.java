package com.miui.yellowpage.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: MessagingTemplate */
public class p {
    private static String rK;

    static {
        rK = "content://msg-template/version";
    }

    public static long M(Context context) {
        InputStream openInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        IOException e;
        InputStream inputStream;
        BufferedReader bufferedReader2;
        Throwable th;
        NumberFormatException e2;
        InputStream inputStream2;
        InputStreamReader inputStreamReader2 = null;
        long j = 0;
        try {
            openInputStream = context.getContentResolver().openInputStream(Uri.parse(rK));
            try {
                inputStreamReader = new InputStreamReader(openInputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        Object readLine = bufferedReader.readLine();
                        if (!TextUtils.isEmpty(readLine)) {
                            j = (long) Integer.parseInt(readLine);
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        if (openInputStream != null) {
                            try {
                                openInputStream.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                    } catch (IOException e4) {
                        e322 = e4;
                        inputStream = openInputStream;
                        bufferedReader2 = bufferedReader;
                        try {
                            e322.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e3222) {
                                    e3222.printStackTrace();
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (IOException e32222) {
                                    e32222.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e322222) {
                                    e322222.printStackTrace();
                                }
                            }
                            return j;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            openInputStream = inputStream;
                            inputStreamReader2 = inputStreamReader;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (inputStreamReader2 != null) {
                                try {
                                    inputStreamReader2.close();
                                } catch (IOException e52) {
                                    e52.printStackTrace();
                                }
                            }
                            if (openInputStream != null) {
                                try {
                                    openInputStream.close();
                                } catch (IOException e522) {
                                    e522.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (NumberFormatException e6) {
                        e2 = e6;
                        inputStreamReader2 = inputStreamReader;
                        try {
                            e2.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3222222) {
                                    e3222222.printStackTrace();
                                }
                            }
                            if (inputStreamReader2 != null) {
                                try {
                                    inputStreamReader2.close();
                                } catch (IOException e32222222) {
                                    e32222222.printStackTrace();
                                }
                            }
                            if (openInputStream != null) {
                                try {
                                    openInputStream.close();
                                } catch (IOException e322222222) {
                                    e322222222.printStackTrace();
                                }
                            }
                            return j;
                        } catch (Throwable th3) {
                            th = th3;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader2 != null) {
                                inputStreamReader2.close();
                            }
                            if (openInputStream != null) {
                                openInputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        inputStreamReader2 = inputStreamReader;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (openInputStream != null) {
                            openInputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                    e322222222 = e7;
                    inputStream2 = openInputStream;
                    bufferedReader2 = null;
                    inputStream = inputStream2;
                    e322222222.printStackTrace();
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return j;
                } catch (NumberFormatException e8) {
                    e2 = e8;
                    bufferedReader = null;
                    inputStreamReader2 = inputStreamReader;
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader2 != null) {
                        inputStreamReader2.close();
                    }
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    return j;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedReader = null;
                    inputStreamReader2 = inputStreamReader;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader2 != null) {
                        inputStreamReader2.close();
                    }
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e322222222 = e9;
                inputStreamReader = null;
                inputStream2 = openInputStream;
                bufferedReader2 = null;
                inputStream = inputStream2;
                e322222222.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return j;
            } catch (NumberFormatException e10) {
                e2 = e10;
                bufferedReader = null;
                e2.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openInputStream != null) {
                    openInputStream.close();
                }
                return j;
            } catch (Throwable th6) {
                th = th6;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (openInputStream != null) {
                    openInputStream.close();
                }
                throw th;
            }
        } catch (IOException e11) {
            e322222222 = e11;
            inputStreamReader = null;
            bufferedReader2 = null;
            e322222222.printStackTrace();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return j;
        } catch (NumberFormatException e12) {
            e2 = e12;
            openInputStream = null;
            bufferedReader = null;
            e2.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            if (openInputStream != null) {
                openInputStream.close();
            }
            return j;
        } catch (Throwable th7) {
            th = th7;
            openInputStream = null;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            if (openInputStream != null) {
                openInputStream.close();
            }
            throw th;
        }
        return j;
    }
}
