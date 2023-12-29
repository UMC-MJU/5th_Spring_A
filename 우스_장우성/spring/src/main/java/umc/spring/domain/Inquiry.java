package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.InquiryStatus;
import umc.spring.domain.enums.InquiryType;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;

    @Column(length = 50)
    private String title;

    @Lob
    private String content;

    @Column(length = 100)
    private String imagePath;

    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'NOT_RECEIVE'")
    @Enumerated(EnumType.STRING)
    private InquiryStatus inquiryStatus;
}
