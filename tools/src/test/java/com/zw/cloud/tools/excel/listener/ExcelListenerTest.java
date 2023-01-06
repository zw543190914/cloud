package com.zw.cloud.tools.excel.listener;

import com.zw.cloud.tools.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ExcelListenerTest extends BaseListenerTest {
    @InjectMocks
    ExcelListener listener;

    @Test
    void doAfterAllAnalysed() {
        User data = new User();
        listener.invoke(data, getAnalysisContext());
        listener.doAfterAllAnalysed(getAnalysisContext());
        listener.invoke(data, getAnalysisContext());
        Assertions.assertDoesNotThrow(() -> listener.doAfterAllAnalysed(getAnalysisContext()));

    }
}
