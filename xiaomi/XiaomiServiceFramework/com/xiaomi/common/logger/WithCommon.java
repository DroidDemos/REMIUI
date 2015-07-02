package com.xiaomi.common.logger;

import com.xiaomi.common.logger.thrift.Common;

public interface WithCommon {
    Common getCommon();

    WithCommon setCommon(Common common);
}
