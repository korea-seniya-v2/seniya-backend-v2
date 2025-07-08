package com.example.seniya_v2.common.constants;

public interface ApiMappingPattern {
    String AUTH_API = "/api/v2/auth";
    String EMAIL_API = "/email";
    String EMAIL_VERIFY_API = AUTH_API + "/emailVerify";
    String USER_API = "/api/v2/users";
    String ADMIN_USER_API = "/api/v2/admin/users";

    String ADMIN_COURSE_API = "/api/v2/admin/courses";

    String TRAINER_APPLY_API = "/api/v2/trainer-applications";
    String TRAINER_PROFILE_API = "/api/v2/trainer-profiles";

    String PAYMENT_API = "/api/v2/payments";
    String PASS_API = "/api/v2/passes";

    String USER_COURSE_API = "/api/v2/courses";
    String COURSE_FIlTER_API = "/api/v2/courses/filter";

    String PARTICIPATION_API = "/api/v2/participation";
    String HEALTH_DATA_API = "/api/v2/health-data";

    String POST_API = "/api/v2/posts";
    String COMMENT_API = POST_API + "/{postId}/comments";
    String NOTICE_API = "/api/v2/notices";
    String INQUIRY_API = "/api/v2/inquiries";
}
