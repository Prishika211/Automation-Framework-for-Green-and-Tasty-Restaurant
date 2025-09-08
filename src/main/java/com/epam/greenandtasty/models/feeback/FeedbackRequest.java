package com.epam.greenandtasty.models.feeback;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackRequest {
    private String reservationId;
    private String serviceRating;
    private String serviceComment;
    private String cuisineRating;
    private String cuisineComment;

    public FeedbackRequest(FeedbackRequestBuilder builder) {
        this.reservationId = builder.reservationId;
        this.serviceRating = builder.serviceRating;
        this.serviceComment = builder.serviceComment;
        this.cuisineRating = builder.cuisineRating;
        this.cuisineComment = builder.cuisineComment;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getServiceRating() {
        return serviceRating;
    }

    public String getServiceComment() {
        return serviceComment;
    }

    public String getCuisineRating() {
        return cuisineRating;
    }

    public String getCuisineComment() {
        return cuisineComment;
    }

    public static class FeedbackRequestBuilder {
        private String reservationId;
        private String serviceRating;
        private String serviceComment;
        private String cuisineRating;
        private String cuisineComment;

        public FeedbackRequestBuilder setReservationId(String reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public FeedbackRequestBuilder setServiceRating(String serviceRating) {
            this.serviceRating = serviceRating;
            return this;
        }

        public FeedbackRequestBuilder setServiceComment(String serviceComment) {
            this.serviceComment = serviceComment;
            return this;
        }

        public FeedbackRequestBuilder setCuisineRating(String cuisineRating) {
            this.cuisineRating = cuisineRating;
            return this;
        }

        public FeedbackRequestBuilder setCuisineComment(String cuisineComment) {
            this.cuisineComment = cuisineComment;
            return this;
        }

        public FeedbackRequest build() {
            return new FeedbackRequest(this);
        }
    }
}