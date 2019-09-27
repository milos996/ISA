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
      })
      .catch(error => {
        alert("Flight is filled");
      });
  };

  reserve = ticket => {
    return this.getApiClient()
      .post(ENDPOINTS.RESERVE, ticket)
      .catch(error => {
        alert("Seat is already reserved");
      });
  };
}

export default new AirplaneTicketService();
