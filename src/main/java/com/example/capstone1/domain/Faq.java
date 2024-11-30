package com.example.capstone1.domain;

import com.example.capstone1.dto.DefaultDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Faq extends AuditingFields{
    @Setter
    Long userId;
    @Setter @Column(nullable = false)
    String title;
    @Setter
    String content;

    protected Faq(){}
    private Faq(Boolean deleted, Long userId, String title, String content){
        this.deleted = deleted;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }


    public static Faq of(Long userId, String title, String content){
        return new Faq(false, userId, title, content);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}

