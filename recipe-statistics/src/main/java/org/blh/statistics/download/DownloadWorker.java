package org.blh.statistics.download;

import org.blh.statistics.download.UrlsToDownload;

/**
 *
 * @author eriklark
 */
@FunctionalInterface
public interface DownloadWorker {

    void work(UrlsToDownload supplier);
}
