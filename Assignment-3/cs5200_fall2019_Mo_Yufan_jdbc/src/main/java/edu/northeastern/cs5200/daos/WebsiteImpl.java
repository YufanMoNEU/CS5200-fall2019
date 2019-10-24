package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Website;
import java.util.*;

public interface WebsiteImpl {
	void createWebsiteForDeveloper(int developerId, Website website);
	Collection<Website> findAllWebsites();
    Collection<Website> findWebsitesForDeveloper(int developerId);
    Website findWebsiteById(int websiteId);
    int updateWebsite(int websiteId, Website website);
    int deleteWebsite(int websiteId);
}
