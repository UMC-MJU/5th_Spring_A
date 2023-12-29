package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //중간 테이블은 양쪽의 매핑정보가 null이면 안됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'NOT_COMPLETE'")
    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

    public static UserMission createUserMission(User user, Mission mission){
        UserMission userMission = new UserMission();
        userMission.setUser(user);
        userMission.setMission(mission);
        userMission.setMissionStatus(MissionStatus.CHALLENGING);
        return userMission;
    }
    public void setMissionStatus(MissionStatus missionStatus){
        this.missionStatus = missionStatus;
    }
    public void setUser(User user){
        if(this.user != null)
            this.user.getUserMissionList().remove(this);
        this.user = user;
        user.getUserMissionList().add(this);
    }
    public void setMission(Mission mission){
        if(this.mission != null)
            this.mission.getUserMissionList().remove(this);
        this.mission = mission;
        mission.getUserMissionList().add(this);
    }
}
