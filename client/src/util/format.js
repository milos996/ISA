export function formatRoomDetails(room, numberOfNights) {
  return `Total Price for ${numberOfNights} days is  ${room.priceSummer *
    numberOfNights}$. Number of 
        of people per room are ${room.numberOfPeople}
    `;
}
