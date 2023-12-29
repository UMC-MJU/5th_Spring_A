package umc.spring.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.UserMission;
import umc.spring.domain.mapping.UserPrefer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@DynamicUpdate  //update, insert 시에 지정하지 않은 것에 대해 Null로 채워 보내지 않음
@DynamicInsert  // 값이 지장되지 않았을 때 동작하는 ColumnDefault가 동작하도록 할 수 있음
@Builder        // 모두 NULL로 채우면 일관성을 얻을 순 있음 -> 로딩 시점에 쿼리를 미리 생성해두고 재사용할 수 있습니다.
            //                                        데이터베이스에 동일한 쿼리를 보내면 데이터베이스는 이전에 한 번 파싱된 쿼리를 재사용할 수 있습니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 20)
    private String nickName;

    @Column(columnDefinition ="VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(length = 50)
    private String address;

    @Column(length = 100)
    private String specAddress;

    @Column(length = 11)
    private String phoneNumber;

    @Column(length = 40)
    private String email;

    @Column(nullable = true)
    @ColumnDefault("0")
    private Integer point;

    private LocalDate inactiveDate;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPrefer> userPreferList = new ArrayList<>();
}
