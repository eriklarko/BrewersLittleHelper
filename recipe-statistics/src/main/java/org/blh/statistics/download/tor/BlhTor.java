package org.blh.statistics.download.tor;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import org.silvertunnel_ng.netlib.adapter.url.NetlibURLStreamHandlerFactory;
import org.silvertunnel_ng.netlib.api.NetFactory;
import org.silvertunnel_ng.netlib.api.NetLayer;
import org.silvertunnel_ng.netlib.api.NetLayerIDs;

public class BlhTor {

    public static void main(String[] args) throws URISyntaxException, IOException {
            URI source = new URI("http://www.konsult.st/ip-adress/index.php");
            System.out.println(new BlhTor().downloadUri(source));
    }

    private NetLayer netLayer;
    private NetlibURLStreamHandlerFactory factory;

    public void init() {
        netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
        netLayer.waitUntilReady();

        // prepare URL handling on top of the lowerNetLayer
        factory = new NetlibURLStreamHandlerFactory(false);

        // the following method could be called multiple times
        // to change layer used by the factory over the time:
        factory.setNetLayerForHttpHttpsFtp(netLayer);
    }

    public String downloadUri(URI source) throws IOException, MalformedURLException {
        if (netLayer == null || factory == null) {
            init();
        }

        URL url = buildUrl(source);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        urlConnection.setAllowUserInteraction(false);
        urlConnection.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
        urlConnection.connect();

        try (InputStream is = urlConnection.getInputStream()) {
            return CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        }
    }

    private URL buildUrl(URI source) throws MalformedURLException {
        URLStreamHandler handler = factory.createURLStreamHandler(source.getScheme());
        URL context = null;
        URL url = new URL(context, source.toString(), handler);
        return url;
    }

    public void useNewExitNodeOnNextRequest() throws IOException {
        if (netLayer != null) {
            netLayer.clear(); // Make sure a new exit node is used
        }
    }
}
