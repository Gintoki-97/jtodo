package cn.gin.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jtodo")
public class JTodo {

    /**
     * Identifier
     */
    private Integer id;

    /**
     * Created timestamp
     */
    private Date created;

    /**
     * Updated timestamp
     */
    private Date updated;

    /**
     * JTodo item content
     */
    private String title;

    /**
     * Whether the jtodo item is completed
     */
    private Boolean completed;

    /**
     * Whether the jtodo item is removed
     */
    private Boolean removed;

    /**
     * JTodo item creator
     */
    private User creator;

    /**
     * JTodo item badges
     */
    private List<Badge> badges;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}
