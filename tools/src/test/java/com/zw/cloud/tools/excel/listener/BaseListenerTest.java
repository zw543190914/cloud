package com.zw.cloud.tools.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.holder.ReadHolder;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import com.alibaba.excel.read.metadata.holder.ReadWorkbookHolder;
import com.alibaba.excel.read.processor.AnalysisEventProcessor;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author liuzhen
 * @since 2022/5/23
 */
public class BaseListenerTest {

    AnalysisContext getAnalysisContext() {
        return new AnalysisContext() {
            @Override
            public void currentSheet(ReadSheet readSheet) {

            }

            @Override
            public ReadWorkbookHolder readWorkbookHolder() {
                return null;
            }

            @Override
            public ReadSheetHolder readSheetHolder() {
                return null;
            }

            @Override
            public void readRowHolder(ReadRowHolder readRowHolder) {

            }

            @Override
            public ReadRowHolder readRowHolder() {
                return null;
            }

            @Override
            public ReadHolder currentReadHolder() {
                return null;
            }

            @Override
            public Object getCustom() {
                return null;
            }

            @Override
            public AnalysisEventProcessor analysisEventProcessor() {
                return null;
            }

            @Override
            public List<ReadSheet> readSheetList() {
                return null;
            }

            @Override
            public void readSheetList(List<ReadSheet> readSheetList) {

            }

            @Override
            public Sheet getCurrentSheet() {
                return null;
            }

            @Override
            public ExcelTypeEnum getExcelType() {
                return null;
            }

            @Override
            public InputStream getInputStream() {
                return null;
            }

            @Override
            public Integer getCurrentRowNum() {
                return null;
            }

            @Override
            public Integer getTotalCount() {
                return null;
            }

            @Override
            public Object getCurrentRowAnalysisResult() {
                return null;
            }

            @Override
            public void interrupt() {

            }
        };
    }

}
