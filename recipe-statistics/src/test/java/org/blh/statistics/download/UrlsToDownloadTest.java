package org.blh.statistics.download;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import org.blh.statistics.download.db.DBManager;
import org.junit.Test;

public class UrlsToDownloadTest {

    @Test
    public void testDownloadUrl() throws SQLException, MalformedURLException  {
        UrlsToDownload utd = new UrlsToDownload("test.db");
        DBManager.getDBC().setDebug(true);
        utd.encountered(new URL("http://www.google.com/a/larko.se?epic=true&foo=bar"));

        utd.dumpDb();
    }

    @Test
    public void testEncountered() throws MalformedURLException, SQLException {
        UrlsToDownload utd = new UrlsToDownload("testT.db");
        DBManager.getDBC().setDebug(true);
        utd.encountered(new URL("http://www.google.com/a/larko.se?epic=true&foo=bar"));

        utd.dumpDb();
    }
}
