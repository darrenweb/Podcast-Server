package lan.dk.podcastserver.controller.ws;

import lan.dk.podcastserver.entity.Item;
import lan.dk.podcastserver.manager.ItemDownloadManager;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by kevin on 27/06/2014.
 */
@Controller
public class DownloadControllerWS {

    @Resource
    ItemDownloadManager itemDownloadManager;

    @SubscribeMapping("/waitingList")
//    @SendTo("/app/waitingList")
    private Collection<Item> getWaitingList() {
        return itemDownloadManager.getWaitingQueue();
    }
}