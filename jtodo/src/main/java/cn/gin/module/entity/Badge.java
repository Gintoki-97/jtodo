package cn.gin.module.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "badge")
public class Badge {


    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Created timestamp
     */
    private Date created;

    /**
     * Updated timestamp
     */
    private Date updated;

    private String content;

    private String color;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}