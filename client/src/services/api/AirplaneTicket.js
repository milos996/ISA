import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
    FAST_TICKET_RESERVATION: "/tickets/quickBooking"
}

class AirplaneTicketService extends HttpBaseClient{

    createFastTicketReservation = flightId => {
        return this.getApiClient().post(ENDPOINTS.FAST_TICKET_RESERVATION, flightId);
    }
}

export default new AirplaneTicketService();