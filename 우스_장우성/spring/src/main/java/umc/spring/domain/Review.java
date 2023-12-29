package umc.spring.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Rate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //리뷰는 항상 어떤 유저가 썼는지 어떤 식당에 대해 썼는지가 정해져 있어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'NOT_BAD'")
    @Enumerated(EnumType.STRING) //enum타입 수정
    private Rate rate;


    @Column(nullable = false)
    @Lob //65535바이트 이상 저장 가능, CLOB(Character Large Object)
    private String content;

    @Column(length = 100)
    private String imagePath;

    public void setUser(User user){
        if(this.user != null)
            this.user.getReviewList().remove(this);
        this.user = user;
        user.getReviewList().add(this);
    }

    public void setStore(Store store){
        if(this.store != null)
            this.store.getReviewList().remove(this);
        this.store = store;
        store.getReviewList().add(this);
    }

}
