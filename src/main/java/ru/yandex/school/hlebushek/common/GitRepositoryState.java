package ru.yandex.school.hlebushek.common;

import java.io.Serializable;
import java.util.Properties;

public class GitRepositoryState implements Serializable {

    private String branch;
    private String describe;
    private String commitId;
    private String commitIdAbbrev;
    private String buildUserName;
    private String buildTime;
    private String commitUserName;
    private String commitMessageShort;
    private String commitTime;

    public String getBranch() {
        return branch;
    }

    public String getDescribe() {
        return describe;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getCommitIdAbbrev() {
        return commitIdAbbrev;
    }

    public String getBuildUserName() {
        return buildUserName;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public String getCommitUserName() {
        return commitUserName;
    }

    public String getCommitMessageShort() {
        return commitMessageShort;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public GitRepositoryState(Properties properties) {
        this.branch = properties.get("git.branch").toString();
        this.describe = properties.get("git.commit.id.describe").toString();
        this.commitId = properties.get("git.commit.id").toString();
        this.commitIdAbbrev = properties.get("git.commit.id.abbrev").toString();
        this.buildUserName = properties.get("git.build.user.name").toString();
        this.buildTime = properties.get("git.build.time").toString();
        this.commitUserName = properties.get("git.commit.user.name").toString();
        this.commitMessageShort = properties.get("git.commit.message.short").toString();
        this.commitTime = properties.get("git.commit.time").toString();
    }
}
