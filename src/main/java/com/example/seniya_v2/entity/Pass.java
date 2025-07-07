package com.example.seniya_v2.entity;

import com.example.seniya_v2.common.enums.pass.CouponType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "passes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Pass {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_id", nullable = false)
    private Long passId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type")
    private CouponType couponType;

    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    @Builder.Default
    private boolean used = false;

    private LocalDateTime usedAt;
}
