package cn.myperf4j.base.metric.formatter.standard;

import cn.myperf4j.base.metric.JvmMemoryMetrics;
import cn.myperf4j.base.metric.formatter.JvmMemoryMetricsFormatter;
import cn.myperf4j.base.util.text.DateFormatUtils;

import java.util.List;

import static cn.myperf4j.base.util.SysProperties.LINE_SEPARATOR;

/**
 * Created by LinShunkang on 1919/8/21
 */
public class StdJvmMemoryMetricsFormatter implements JvmMemoryMetricsFormatter {

    private static final String TITLE_FORMAT = "%-14s%21s%12s%17s%12s%19s%12s%17s%13s%19s%13s%20s%15s%22s%15s%22s%n";

    private static final String DATA_FORMAT = "%-14d%21.2f%12d%17.2f%12d%19.2f%12d%17.2f%13d%19.2f%13d%20.2f%15d" +
            "%22.2f%15d%22.2f%n";

    @Override
    public String format(List<JvmMemoryMetrics> metricsList, long startMillis, long stopMillis) {
        final StringBuilder sb = new StringBuilder((metricsList.size() + 2) * (9 * 19 + 64));
        sb.append("MyPerf4J JVM Memory Metrics [").append(DateFormatUtils.format(startMillis)).append(", ")
                .append(DateFormatUtils.format(stopMillis)).append(']').append(LINE_SEPARATOR);
        sb.append(String.format(TITLE_FORMAT,
                "SurvivorUsed", "SurvivorUsedPercent",
                "EdenUsed", "EdenUsedPercent",
                "OldGenUsed", "OldGenUsedPercent",
                "HeapUsed", "HeapUsedPercent",
                "NonHeapUsed", "NoHeapUsedPercent",
                "PermGenUsed", "PermGenUsedPercent",
                "MetaspaceUsed", "MetaspaceUsedPercent",
                "CodeCacheUsed", "CodeCacheUsedPercent"));
        if (metricsList.isEmpty()) {
            return sb.toString();
        }

        for (int i = 0; i < metricsList.size(); ++i) {
            final JvmMemoryMetrics metrics = metricsList.get(i);
            sb.append(
                    String.format(DATA_FORMAT,
                            metrics.getSurvivorUsed(),
                            metrics.getSurvivorUsedPercent(),
                            metrics.getEdenUsed(),
                            metrics.getEdenUsedPercent(),
                            metrics.getOldGenUsed(),
                            metrics.getOldGenUsedPercent(),
                            metrics.getHeapUsed(),
                            metrics.getHeapUsedPercent(),
                            metrics.getNonHeapUsed(),
                            metrics.getNonHeapUsedPercent(),
                            metrics.getPermGenUsed(),
                            metrics.getPermGenUsedPercent(),
                            metrics.getMetaspaceUsed(),
                            metrics.getMetaspaceUsedPercent(),
                            metrics.getCodeCacheUsed(),
                            metrics.getCodeCacheUsedPercent()
                    )
            );
        }
        return sb.toString();
    }
}
