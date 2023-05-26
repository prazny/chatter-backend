package com.sr.chatpanel.services;

import com.sr.chatpanel.models.Site;
import com.sr.chatpanel.models.User;
import com.sr.chatpanel.repositories.SiteRepository;
import com.sr.chatpanel.rest.exceptions.EntityNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    public Site findById(int id) throws EntityNotFound {
        return siteRepository.findById(id).orElseThrow(EntityNotFound::new);
    }


    public List<Site> getMany() {
        return siteRepository.findAll();
    }

    public Site add(Site site) {
        return siteRepository.save(site);
    }

    public Site update(int id, Site site) throws EntityNotFound {
        Site exSite = findById(id);
        exSite.setName(site.getName());
        exSite.setUri(site.getUri());
        return siteRepository.save(exSite);
    }

    public void delete(int id) throws EntityNotFound {
        Site site = findById(id);
        siteRepository.delete(site);
    }
}
