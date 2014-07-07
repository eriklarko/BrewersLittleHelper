package org.blh.statistics.download;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import org.blh.statistics.download.db.DBManager;
import org.junit.Test;

public class UrlsToDownloadTest {

    @Test
    public void testDownloadUrl() throws SQLException, MalformedURLException {
        String dbName = "test.db";

        UrlsToDownload utd = new UrlsToDownload(dbName);
        DBManager.getDBC().setDebug(true);
        utd.encountered(new URL("http://www.google.com/a/larko.se?epic=true&foo=bar"));

        utd.dumpDb();

        new File(dbName).delete();
    }

    @Test
    public void testEncountered() throws MalformedURLException, SQLException {
        String dbName = "testT.db";

        UrlsToDownload utd = new UrlsToDownload(dbName);
        DBManager.getDBC().setDebug(true);
        utd.encountered(new URL("http://www.google.com/a/larko.se?epic=true&foo=bar"));

        utd.dumpDb();

        new File(dbName).delete();
    }
}
