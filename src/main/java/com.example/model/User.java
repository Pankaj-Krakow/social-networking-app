package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "user")
@ApiModel(description = "User Details")
public class User {

    @ApiModelProperty(notes = "The database generated ID")
    private long id;

    @ApiModelProperty(notes = "User ID")
    @Min(value = 1, message = "userId field value can not be 0")
    private long userId;

    @ApiModelProperty(notes = "Message Posted by User")
    @NotBlank(message = "'message' field value can not be null/blank")
    private String message;


    @ApiModelProperty(notes = "Message Posted Time")
    private Date timestamp;

    public User() {

    }

    public User(long userId, String message, Date timestamp) {
        this.userId = userId;
        this.message = message;
        this.timestamp = timestamp;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "message", nullable = false, length = 145)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Column(name = "UserId", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "CreateTime")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", timestamp=" + timestamp +
                '}';
    }
}




