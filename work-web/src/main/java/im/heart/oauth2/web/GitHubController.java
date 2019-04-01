package im.heart.oauth2.web;

import im.heart.oauth2.GitHubAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GitHubController{
    private Logger logger = LoggerFactory.getLogger(GitHubController.class);
    @Autowired
    private GitHubAuthService gitHubAuthService;
}
