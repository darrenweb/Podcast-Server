package lan.dk.podcastserver.utils;

import lan.dk.podcastserver.entity.Item;
import lan.dk.podcastserver.entity.Podcast;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.time.format.DateTimeFormatter;


public class jDomUtils {

    private static Logger logger = LoggerFactory.getLogger(jDomUtils.class);

    public static String SERVEUR_URL;
    private static final String LINK_FORMAT = "%s/api/podcast/%d/rss";
    public static final Namespace ITUNES_NAMESPACE = Namespace.getNamespace("itunes", "http://www.itunes.com/dtds/podcast-1.0.dtd");
    public static final Namespace MEDIA_NAMESPACE = Namespace.getNamespace("media", "http://search.yahoo.com/mrss/");

    @Deprecated
    public static Podcast getPodcastFromURL(URL url) {
        Podcast podcast = new Podcast();
        podcast.setUrl(url.toString());
        Document podcastXML = null;
        try {
            podcastXML = jDomUtils.jdom2Parse(podcast.getUrl());
            if (podcastXML.getRootElement().getChild("channel").getChildText("title") != null) {
                podcast.setTitle(podcastXML.getRootElement().getChild("channel").getChildText("title"));
            }
            if (podcastXML.getRootElement().getChild("channel").getChild("image").getChildText("url") != null) {
                podcast.setCover(ImageUtils.getCoverFromURL(new URL(podcastXML.getRootElement().getChild("channel").getChild("image").getChildText("url"))));
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return podcast;
    }

    public static Document jdom2Parse(String urlasString) throws JDOMException, IOException {
        SAXBuilder sax = new SAXBuilder();
        Document doc = null;
        logger.debug("Begin Parsing of {}", urlasString);
        try {
            doc = sax.build(URLUtils.getStreamWithTimeOut(urlasString).getInputStream(), urlasString);
            logger.debug("End Parsing of {}", urlasString);
        } catch (JDOMException | IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw e;
        }

        return doc;

    }

    public static String podcastToXMLGeneric (Podcast podcast, String serveurURL) {

        Element channel = new Element("channel");

        String coverUrl = URLUtils.getAsciiURL(podcast.getCover().getUrl());
        
        Element title = new Element("title");
        title.addContent(new Text(podcast.getTitle()));

        Element url = new Element("link");
        url.addContent(new Text(String.format(LINK_FORMAT, SERVEUR_URL, podcast.getId())));

        Element lastUpdate = new Element("pubDate");
        lastUpdate.addContent(new Text(podcast.getLastUpdate().format(DateTimeFormatter.RFC_1123_DATE_TIME)));

        Element description = new Element("description");
        description.addContent(new Text(podcast.getDescription()));

        Element itunesSub = new Element("subtitle", ITUNES_NAMESPACE);
        itunesSub.addContent(new Text(podcast.getDescription()));

        Element itunesSummary = new Element("summary", ITUNES_NAMESPACE);
        itunesSummary.addContent(new Text(podcast.getDescription()));

        Element language = new Element("language");
        language.addContent(new Text("fr-fr"));

        //Element copyright = new Element("copyright");
        //copyright.addContent(new Text(podcast.getTitle()));

        Element itunesAuthor = new Element("author", ITUNES_NAMESPACE);
        itunesAuthor.addContent(new Text(podcast.getType()));

        Element itunesCategory = new Element("category", ITUNES_NAMESPACE);


        channel.addContent(url);
        channel.addContent(title);
        channel.addContent(lastUpdate);
        channel.addContent(description);
        channel.addContent(itunesSub);
        channel.addContent(itunesSummary);
        channel.addContent(language);
        //channel.addContent(copyright);
        channel.addContent(itunesAuthor);
        channel.addContent(itunesCategory);


        Element itunesImage = new Element("image", ITUNES_NAMESPACE);
        if (podcast.getCover() != null) {
            Element image = new Element("image");
            Element image_url = new Element("url");
            Element image_width = new Element("width");
            Element image_height = new Element("height");

            itunesImage.addContent(new Text(coverUrl));

            image_url.addContent(coverUrl);
            image_width.addContent(String.valueOf(podcast.getCover().getWidth()));
            image_height.addContent(String.valueOf(podcast.getCover().getHeight()));
            image.addContent(image_height);
            image.addContent(image_url);
            image.addContent(image_width);
            channel.addContent(image);
            channel.addContent(itunesImage);
        }

        for (Item item : podcast.getItems()) {
            Element xmlItem = new Element("item");

            Element item_title = new Element("title");
            item_title.addContent(new Text(item.getTitle()));
            xmlItem.addContent(item_title);

            Element item_description = new Element("description");
            item_description.addContent(new Text(item.getDescription()));
            xmlItem.addContent(item_description);

            xmlItem.addContent(itunesImage.clone());

            Element item_enclosure = new Element("enclosure");

            item_enclosure.setAttribute("url", serveurURL
                    .concat(item.getProxyURLWithoutExtention())
                    .concat((item.isDownloaded()) ? "."+FilenameUtils.getExtension(item.getFileName()) : MimeTypeUtils.getExtension(item)));
            
            if (item.getLength() != null) {
                item_enclosure.setAttribute("length", String.valueOf(item.getLength()));
            }

            if (StringUtils.isNotEmpty(item.getMimeType()))
                item_enclosure.setAttribute("type", item.getMimeType());

            xmlItem.addContent(item_enclosure);

            Element item_pubdate = new Element("pubDate");
            item_pubdate.addContent(new Text(item.getPubdate().format(DateTimeFormatter.RFC_1123_DATE_TIME)));
            xmlItem.addContent(item_pubdate);

            Element itunesExplicite = new Element("explicit", ITUNES_NAMESPACE);
            itunesExplicite.addContent(new Text("No"));
            xmlItem.addContent(itunesExplicite);

            Element itunesItemSub = new Element("subtitle", ITUNES_NAMESPACE);
            itunesItemSub.addContent(new Text(item.getTitle()));
            xmlItem.addContent(itunesItemSub);

            Element itunesItemSummary = new Element("summary", ITUNES_NAMESPACE);
            itunesItemSummary.addContent(new Text(item.getDescription()));
            xmlItem.addContent(itunesItemSummary);

            Element guid = new Element("guid");
            guid.addContent(new Text(serveurURL + item.getProxyURL()));
            xmlItem.addContent(guid);
            

            Element thumbnail = new Element("thumbnail", MEDIA_NAMESPACE);
            thumbnail.setAttribute("url", URLUtils.getAsciiURL(item.getCoverOfItemOrPodcast().getUrl()));
            xmlItem.addContent(thumbnail);

            channel.addContent(xmlItem);
        }

        Element rss = new Element("rss");
        rss.addNamespaceDeclaration(ITUNES_NAMESPACE);
        rss.addNamespaceDeclaration(MEDIA_NAMESPACE);
        rss.addContent(channel);

        XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
        Writer writer = new StringWriter();
        try {
            xout.output(new Document(rss), writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;

    }
}
