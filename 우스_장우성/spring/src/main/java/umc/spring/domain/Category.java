package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;
// 카테고리는 수정이 많이 일어나지 않으므로 양방향 x
//   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Inquiry> inquiryList = new ArrayList<>();
}
