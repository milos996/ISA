create schema if not exists isa_database;

create table address
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    city varchar(128) null,
	state varchar(128) null,
	latitude double null,
	longitude double null,
	street varchar(255) null,
	constraint UK_ADDRESS_CITY_STATE_STREET
        unique (state, city, street)
);

create table user
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    role varchar(64) not null,
    email varchar(128) not null,
    password varchar(64) not null,
    phone_number varchar(64) not null,
    city varchar(128) not null,
    state varchar(128) not null,
    constraint UK_USER_EMAIL
        unique (email)
);

create table friendship
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    user_sender_id varchar(255) not null,
    user_invited_id varchar(255) not null,
    invitation_status varchar(255) not null,
    constraint FK_FRIENDSHIP_SENDER
        foreign key ( user_sender_id ) references user (id),
    constraint FK_FRIENDSHIP_INVITED
        foreign key ( user_invited_id ) references user (id)
);

create table group_trip
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    name varchar(128) not null
);

create table hotel
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    name varchar(128) not null,
    address_id varchar(255) not null,
    description varchar(1024) null,
    rating double null,
    constraint FK_HOTEL_ADDRESS
        foreign key (address_id) references address (id),
    constraint UK_HOTEL_NAME
        unique (name)
);

create table hotel_admin
(
    id           varchar(255) not null
        primary key,
    is_deleted   bit          not null,
    time_created datetime     not null,
    time_updated datetime     null,
    user_id      varchar(255) not null,
    hotel_id     varchar(255) not null,
    constraint FK_HOTEL_ADMIN_HOTEL
        foreign key (hotel_id) references hotel (id)
);

create table service
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    name varchar(255) not null,
    constraint UK_SERVICE_NAME
        unique (name)
);

create table hotel_service
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    hotel_id varchar(255) not null,
    service_id varchar(255) not null,
    constraint FK_HOTEL_SERVICE_HOTEL
        foreign key (hotel_id) references hotel (id),
    constraint FK_HOTEL_SERVICE_SERVICE
        foreign key (service_id) references service (id)
);

create table room
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    number int not null,
    floor int not null,
    price_summer double not null,
    price_winter double not null,
    price_autumn double not null,
    price_spring double not null,
    number_of_people int not null,
    hotel_id varchar(255) not null,
    constraint ROOM_HOTEL
        foreign key (hotel_id) references hotel (id),
    constraint UK_ROOM_NUMBER_FLOOR
        unique (number, floor)
);

create table airline
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    name varchar(128) not null,
    address_id varchar(255) not null,
    description varchar (1024) null,
    checking_in_suitcase_price double not null,
    hand_luggage_price double  not null,
    constraint UK_NAME
        unique (name),
    constraint FK_AIRLINE_ADDRESS
        foreign key (address_id) references address(id)
);

create table destination
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    city varchar(128) not null,
    state varchar(128) not null,
    constraint UK_DESTINATION_CITY_STATE
        unique (state, city)
);

create table airline_destination
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    airline_id varchar(255) not null,
    destination_id varchar(255) not null,
    constraint FOREIGN_KEY_AIRLINE_DESTINATION_AIRLINE
        foreign key (airline_id) references airline (id),
    constraint FOREIGN_KEY_AIRLINE_DESTINATION_DESTINATION
        foreign key (destination_id) references destination (id)
);

create table airplane
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    mark varchar(128) not null,
    number_of_rows int not null,
    number_of_columns_per_segment int not null,
    number_of_segments int not null,
    airline_id varchar(255) not null,
    constraint FK_AIRPLANE_AIRLINE
        foreign key (airline_id) references airline (id),
    constraint UK_AIRPLANE_MARK
        unique (mark)
);

create table flight
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    departure_time datetime not null,
    arrival_time datetime not null,
    duration time not null,
    length double not null,
    price double not null,
    airline_destination_id varchar(255) not null,
    airplane_id varchar(255) not null,
    constraint FK_FLIGHT_AIRLINE_DESTINATION
        foreign key (airline_destination_id) references airline_destination (id),
    constraint FK_FLIGHT_AIRPLANE
        foreign key (airplane_id) references airplane (id)
);

create table airline_admin
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    user_id varchar (255) not null,
    airline_id varchar (255) not null,
    constraint FK_AIRLINE_ADMIN_USER
        foreign key (user_id) references user (id),
    constraint FK_AIRLINE_ADMIN_AIRLINE
        foreign key (airline_id) references airline (id)
);

create table airplane_ticket
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    user_id varchar(255) not null,
    number_of_row int not null,
    number_of_column int not null,
    number_of_segment int not null,
    flight_id varchar(255) not null,
    group_trip_id varchar(255) null,
    group_trip_status varchar(255) null,
    constraint FK_AIRPLANE_TICKET_FLIGHT
        foreign key (flight_id) references flight (id),
    constraint FK_AIRPLANE_TICKET_USER
        foreign key (user_id) references user (id),
    constraint FK_AIRPLANE_TICKET_GROUP_TRIP
        foreign key (group_trip_id) references group_trip (id)
);

create table hotel_reservation
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    price double not null,
    room_id varchar(255) not null,
    start_date date not null,
    end_date date not null,
    airplane_ticket_id varchar(255) not null,
    constraint FK_HOTEL_RESERVATION_ROOM
        foreign key (room_id) references room (id),
    constraint FK_HOTEL_RESERVATION_AIRPLANE_TICKET
        foreign key (airplane_ticket_id) references airplane_ticket (id)
);

create table hotel_reservation_additional_services
(
    id                   varchar(255) not null
        primary key,
    is_deleted           bit          not null,
    time_created         datetime     not null,
    time_updated         datetime     null,
    hotel_reservation_id varchar(255) not null,
    hotel_service_id     varchar(255) not null,
    constraint FK_HOTEL_ADDITIONAL_SERVICES_RESERVATION
        foreign key (hotel_reservation_id) references hotel_reservation (id),
    constraint FK_HOTEL_ADDITIONAL_SERVICES_SERVICE
        foreign key (hotel_service_id) references hotel_service (id)
);

create table rent_a_car
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    name varchar(128) not null,
    description varchar(1024) null,
    address_id varchar(255) not null,
    constraint FK_RENT_A_CAR_ADDRESS
        foreign key (address_id) references address (id),
    constraint UK_RENT_A_CAR_NAME
        unique (name)
);

create table rent_a_car_admin
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    user_id varchar (255) not null,
    rent_a_car_id varchar (255) not null,
    constraint FK_RENT_A_CAR_ADMIN_USER
        foreign key (user_id) references user (id),
    constraint FK_RENT_A_CAR_ADMIN_RENT_CAR
        foreign key (rent_a_car_id) references rent_a_car (id)
);

create table vehicle
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    brand varchar(128) not null,
    model varchar(128) not null,
    number_of_people int not null,
    price_per_day double not null,
    type varchar(128) not null,
    year_of_production int not null,
    rating double null,
    rent_a_car_id varchar(255) not null,
    constraint FK_VEHICLE_RENT_A_CAR
        foreign key (rent_a_car_id) references rent_a_car (id)
);

create table agency_location
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    state varchar(128) not null,
    city varchar(128) not null,
    constraint UK_AGENCY_LOCATION_CITY_STATE
        unique (state, city)
);

create table rent_a_car_location
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    rent_a_car_id varchar(255) not null,
    agency_location_id varchar(255) not null,
    constraint FK_RENT_A_CAR_LOCATION_RENT
        foreign key (rent_a_car_id) references rent_a_car (id),
    constraint FK_RENT_A_CAR_LOCATION_LOCATION
        foreign key (agency_location_id) references agency_location (id)

);

create table vehicle_reservation
(
    id varchar(255) not null
        primary key,
    is_deleted bit not null,
    time_created datetime not null,
    time_updated datetime null,
    start_date date not null,
    end_date date not null,
    price double not null,
    airplane_ticket_id varchar(255) not null,
    vehicle_id varchar(255) not null,
    constraint FK_VEHICLE_RESERVATION_AIRPLANE_TICKET
        foreign key (airplane_ticket_id) references airplane_ticket (id),
    constraint FK_VEHICLE_RESERVATION_VEHICLE
        foreign key (vehicle_id) references vehicle (id)
);

create table rating
(
    id varchar(255) not null
        primary key,
    entity_id varchar(255) not null,
    entity_type varchar(255) not null,
    mark int not null


);









