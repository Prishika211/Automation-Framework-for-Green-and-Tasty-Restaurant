package com.epam.greenandtasty.models.reservation;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private String date;
    private String id;
    private String clientType;
    private String reservationId;
    private String timeSlot;
    private String customerEmail;
    private String message;
    private String guestNumber;
    private String status;
    private String locationId;
    private String tableNumber;
    private String timeFrom;
    private String timeTo;
    private String userId;
    private String waiterId;
    private String email;
    private String createdByWaiter;
    private String userInfo;
}