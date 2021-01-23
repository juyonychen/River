
package com.river.common.log;


import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class SampleLogTest {

    @Mock
    private Logger mockLogger;

    @Test
    void logWithPercentage() {
        SampleLog sampleLog = new SampleLogImpl(mockLogger, 1, true);

        for (int i = 0; i < 100; i++) {
            sampleLog.info("");
        }

        verify(mockLogger, times(100)).info(anyString());
    }

    @Test
    void logWithSample() {
        SampleLog sampleLog = new SampleLogImpl(mockLogger, 0, false);

        for (int i = 0; i < 100; i++) {
            sampleLog.debug("");
        }

        verify(mockLogger, times(0)).debug(anyString());
    }

    @Test
    void autoRefreshTrigger() {
        SampleLog sampleLog = new SampleLogImpl(mockLogger, 1, true);

        IllegalStateException exception = assertThrows(IllegalStateException.class, sampleLog::refresh);
        assertThat(exception).hasMessage("No need to call refresh for a auto refresh printer");
    }

    @Test
    void logCouldBeSerialized() {
        SampleLog log = SampleLogFactory.getLogger("SomeLog");
        byte[] bytes = SerializationUtils.serialize(log);
        SampleLog deserializeLog = SerializationUtils.deserialize(bytes);

        assertThat(deserializeLog)
            .isNotNull()
            .isInstanceOf(SampleLogImpl.class)
            .hasFieldOrPropertyWithValue("name", "SomeLog");
    }
}
