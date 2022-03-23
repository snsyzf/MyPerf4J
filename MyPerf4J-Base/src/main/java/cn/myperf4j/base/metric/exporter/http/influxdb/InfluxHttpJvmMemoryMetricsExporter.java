package cn.myperf4j.base.metric.exporter.http.influxdb;

import cn.myperf4j.base.influxdb.InfluxDbClient;
import cn.myperf4j.base.influxdb.InfluxDbClientFactory;
import cn.myperf4j.base.metric.JvmMemoryMetrics;
import cn.myperf4j.base.metric.formatter.JvmMemoryMetricsFormatter;
import cn.myperf4j.base.metric.formatter.influxdb.InfluxJvmMemoryMetricsFormatter;
import cn.myperf4j.base.metric.exporter.JvmMemoryMetricsExporter;
import cn.myperf4j.base.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by LinShunkang on 2020/05/23
 */
public class InfluxHttpJvmMemoryMetricsExporter implements JvmMemoryMetricsExporter {

    private static final JvmMemoryMetricsFormatter METRICS_FORMATTER = new InfluxJvmMemoryMetricsFormatter();

    private static final InfluxDbClient CLIENT = InfluxDbClientFactory.getClient();

    private final ConcurrentMap<Long, List<JvmMemoryMetrics>> metricsMap = new ConcurrentHashMap<>(8);

    @Override
    public void beforeProcess(long processId, long startMillis, long stopMillis) {
        metricsMap.put(processId, new ArrayList<JvmMemoryMetrics>(1));
    }

    @Override
    public void process(JvmMemoryMetrics metrics, long processId, long startMillis, long stopMillis) {
        final List<JvmMemoryMetrics> metricsList = metricsMap.get(processId);
        if (metricsList != null) {
            metricsList.add(metrics);
        } else {
            Logger.error("InfluxHttpJvmMemoryMetricsExporter.process(" + processId + ", " + startMillis + ", "
                    + stopMillis + "): metricsList is null!!!");
        }
    }

    @Override
    public void afterProcess(long processId, long startMillis, long stopMillis) {
        final List<JvmMemoryMetrics> metricsList = metricsMap.remove(processId);
        if (metricsList != null) {
            CLIENT.writeMetricsAsync(METRICS_FORMATTER.format(metricsList, startMillis, stopMillis));
        } else {
            Logger.error("InfluxHttpJvmMemoryMetricsExporter.afterProcess(" + processId + ", " + startMillis + ", "
                    + stopMillis + "): metricsList is null!!!");
        }
    }
}
