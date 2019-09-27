import HttpBaseClient from "../HttpBaseClient";

const ENDPOINTS = {
  FAST_TICKET_RESERVATION: "/tickets/quickBooking",
  RESERVE: "/tickets/reservation"
};

class AirplaneTicketService extends HttpBaseClient {
  createFastTicketReservation = flightId => {
    return this.getApiClient()
      .post(ENDPOINTS.FAST_TICKET_RESERVATION, {
        flightId
      });
  };

  reserve = ticket => {
    return this.getApiClient().post(ENDPOINTS.RESERVE, ticket);
  };
}

export default new AirplaneTicketService();
