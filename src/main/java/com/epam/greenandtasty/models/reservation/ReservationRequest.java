package com.epam.greenandtasty.models.reservation;

public class ReservationRequest {
    private String clientType;
    private String customerEmail;
    private String date;
    private String guestNumber;
    private String locationId;
    private String tableNumber;
    private String timeFrom;
    private String timeTo;
    public ReservationRequest(ReservationRequestBuilder builder){
        this.clientType = builder.clientType;
        this.tableNumber = builder.tableNumber;
        this.locationId = builder.locationId;
        this.date = builder.date;
        this.guestNumber = builder.guestNumber;
        this.timeTo = builder.timeTo;
        this.timeFrom = builder.timeFrom;
        this.customerEmail = builder.customerEmail;

    }

    public String getClientType() {
        return clientType;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public String getDate() {
        return date;
    }

    public String getGuestNumber() {
        return guestNumber;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }



    public static class ReservationRequestBuilder{
        private String clientType;
        private String customerEmail;
        private String date;
        private String guestNumber;
        private String locationId;
        private String tableNumber;
        private String timeFrom;
        private String timeTo;

        public ReservationRequestBuilder setClientType(String clientType){
            this.clientType = clientType;
            return this;
        }
        public ReservationRequestBuilder setCustomerEmail(String customerEmail){
            this.customerEmail = customerEmail;
            return this;
        }
        public ReservationRequestBuilder setDate(String date){
            this.date = date;
            return this;
        }
        public ReservationRequestBuilder setGuestNumber(String guestNumber){
            this.guestNumber = guestNumber;
            return this;
        }
        public ReservationRequestBuilder setLocationId(String locationId){
            this.locationId = locationId;
            return this;
        }
        public ReservationRequestBuilder setTimeFrom(String timeFrom){
            this.timeFrom = timeFrom;
            return this;
        }

        public ReservationRequestBuilder setTimeTo(String timeTo){
            this.timeTo = timeTo;
            return this;
        }
        public ReservationRequestBuilder setTableNumber(String tableNumber){
            this.tableNumber = tableNumber;
            return this;
        }
        
        public ReservationRequest builder(){
            return new ReservationRequest(this);
        }
    }
}
