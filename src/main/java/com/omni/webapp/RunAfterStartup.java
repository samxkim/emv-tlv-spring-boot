package com.omni.webapp;

import com.omni.webapp.models.InitialEMVTags;
import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class RunAfterStartup {

    private static final Logger LOG = LoggerFactory.getLogger(RunAfterStartup.class);

    final TagRepository tagRepository;

    public RunAfterStartup(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void insertInitialTags() {
        List<Tag> tags = new ArrayList<>();

        // Iterates through each static constant in the specified class
        for (Field f : InitialEMVTags.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Tag currentTag = (Tag) f.get(null);
                if (!tagRepository.existsByName(currentTag.getName())) {
                    tags.add(currentTag);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        tagRepository.saveAll(tags);
        LOG.debug("Initial Tags created/updated.");
    }
}
