package lan.dk.podcastserver.controller.api;

import lan.dk.podcastserver.business.TagBusiness;
import lan.dk.podcastserver.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by kevin on 07/06/2014 for Podcast Server
 */
@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    final TagBusiness tagBusiness;

    @GetMapping("{id}")
    public Tag findById(@PathVariable UUID id) {
        return tagBusiness.findOne(id);
    }

    @GetMapping
    public List<Tag> findAll() {
        return tagBusiness.findAll();
    }

    @PostMapping
    public List<Tag> findByNameLike(@RequestParam String name) {
        return tagBusiness.findByNameLike(name);
    }
}
