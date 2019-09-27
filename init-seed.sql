INSERT INTO `isa_database`.`user` (`id`,`is_deleted`,`time_created`,`time_updated`,`first_name`,`last_name`,`role`,`email`,`password`,`phone_number`,`city`,`state`,`is_enabled`) VALUES ('0c16acf1-1393-4e81-b7e9-a9ea71ef26bc', 0,'2019-09-25 21:27:27','2019-09-25 21:27:27','Uros','Kojovic','AIRLINE_ADMIN','uroskojo96@gmail.com','$2a$10$b378ve/3lTEGWHHLypMcHeS/HGTUuWQvBKrxXe7TDEitaM/Zqx2gK','0605431869','Novi Sad','Srbija',1);
INSERT INTO `isa_database`.`user` (`id`,`is_deleted`,`time_created`,`time_updated`,`first_name`,`last_name`,`role`,`email`,`password`,`phone_number`,`city`,`state`,`is_enabled`) VALUES ('2a47c549-ebe1-4702-9d83-4e7f602bf945', 0,'2019-09-26 08:42:23','2019-09-26 08:42:23','Marta','Matovic','USER','marta@gmail.com','$2a$10$k7Ug8F5Vuy1eg7eKSZRxD.b.OJieAqvASBwKdB4wnU/tH92G6yDdG','0604569872','Cacak','Srbija',1);
INSERT INTO `isa_database`.`user` (`id`,`is_deleted`,`time_created`,`time_updated`,`first_name`,`last_name`,`role`,`email`,`password`,`phone_number`,`city`,`state`,`is_enabled`) VALUES ('2dca6591-d530-45ef-b3e1-e640c690d20a', 0,'2019-09-26 08:43:49','2019-09-27 13:11:02','Igor','Markovic','USER','igor@gmail.com','111','0605050123','Belgrade','Serbia',1);
INSERT INTO `isa_database`.`user` (`id`,`is_deleted`,`time_created`,`time_updated`,`first_name`,`last_name`,`role`,`email`,`password`,`phone_number`,`city`,`state`,`is_enabled`) VALUES ('8110d363-6576-4297-860d-b029b519a671', 0,'2019-09-17 14:44:44','2019-09-17 14:44:44','Petar','Djukic','AIRLINE_ADMIN','petar@gmail.com','111','0612070410','Beograd','Serbia',1);

insert into `isa_database`.`destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`)values("535a10ce-8021-4685-91ff-c81279e2f547",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Madrid", "Spain");

insert into `isa_database`.`destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`)values("44bf2fac-9aea-41c4-8ff1-913159aec6c0",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Tivat", "Montenegro");

insert into `isa_database`.`destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`)values("8823065a-6880-4065-9877-7eb6fa23a052",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Belgrade", "Serbia");


insert into `isa_database`.`address`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`,
`latitude`,
`longitude`,
`street`)values("642d32bb-7415-43d6-8bf4-c4e04d9fd7d8",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Belgrade", "Serbia" ,44.787,20.457,"Nemanjina");

insert into `isa_database`.`address`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`,
`latitude`,
`longitude`,
`street`)values("c5a617ef-006c-438a-ba4e-9391ff1d7f16",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Tivat", "Montenegro" ,42.43, 18.69,"Njegoseva");

insert into `isa_database`.`address`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`,
`latitude`,
`longitude`,
`street`)values("f6a0dedc-332c-44eb-9438-b0922d0bd2bd",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Madrid", "Spain" ,40.416, -3.7037,"Calle de prim");



insert into `isa_database`.`airline`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`name`,
`address_id`,
`description`,
`checking_in_suitcase_price`,
`hand_luggage_price`)
values("ef6db0d2-08d4-4155-8cd4-53983f67354b",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","AirMontenegro","c5a617ef-006c-438a-ba4e-9391ff1d7f16","Praesent rhoncus quam ante, nec aliquam libero.",120,20);

insert into `isa_database`.`airline`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`name`,
`address_id`,
`description`,
`checking_in_suitcase_price`,
`hand_luggage_price`)
values("e7cc51b2-8fc7-4333-8f9e-fc7cce4d458c",0,"2019-09-17 14:44:44","2019-09-17 14:44:44","AirSerbia","642d32bb-7415-43d6-8bf4-c4e04d9fd7d8","Praesent rhoncus quam ante, nec aliquam libero.",120,25);


insert into `isa_database`.`airline`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`name`,
`address_id`,
`description`,
`checking_in_suitcase_price`,
`hand_luggage_price`)
values("3997f762-9772-4b35-9a47-baf4c101ae7c",0,"2019-11-09 11:44:44","2019-11-09 12:44:44","AirSpain","f6a0dedc-332c-44eb-9438-b0922d0bd2bd","Praesent rhoncus quam ante, nec aliquam libero.",180,40);


insert into `isa_database`.`airline_destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`airline_id`,
`destination_id`)
 values ("6a66078a-5083-4ba0-9654-8bdb547065b0",0,"2019-11-09 12:50:02","2019-11-09 12:50:02","ef6db0d2-08d4-4155-8cd4-53983f67354b","44bf2fac-9aea-41c4-8ff1-913159aec6c0");
					
insert into `isa_database`.`airline_destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`airline_id`,
`destination_id`) 
 values ("b636eb2d-8c2b-4192-8730-4b42dbe675f6",0,"2019-11-09 12:50:02","2019-11-09 12:50:02","e7cc51b2-8fc7-4333-8f9e-fc7cce4d458c","8823065a-6880-4065-9877-7eb6fa23a052");

insert into `isa_database`.`airline_destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`airline_id`,
`destination_id`) values ("0dd530af-4832-4030-806f-fd5643595941",0,"2019-11-09 12:50:02","2019-11-09 12:50:02","3997f762-9772-4b35-9a47-baf4c101ae7c","535a10ce-8021-4685-91ff-c81279e2f547");
	
																																		
insert into `isa_database`.`airplane`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`mark`,
`number_of_rows`,
`number_of_columns_per_segment`,
`number_of_segments`,
`airline_id`)
values("c11fe8c8-3c41-4571-a7f6-6f6a5bcaf645",0,"2019-11-09 14:34:02","2019-11-09 14:34:02","A111",5,4,2,"ef6db0d2-08d4-4155-8cd4-53983f67354b");

insert into `isa_database`.`airplane`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`mark`,
`number_of_rows`,
`number_of_columns_per_segment`,
`number_of_segments`,
`airline_id`)
values("4aa0b5ce-37a8-4aab-b9da-b1afcc861d94",0, curdate(), curdate(), "A116",4,4,2,"e7cc51b2-8fc7-4333-8f9e-fc7cce4d458c");


insert into `isa_database`.`airplane`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`mark`,
`number_of_rows`,
`number_of_columns_per_segment`,
`number_of_segments`,
`airline_id`)
values("158b19d5-68c0-4170-8bd5-63af76402403",0,"2019-11-09 14:34:02","2019-11-09 14:34:02","A112",5,3,2,"e7cc51b2-8fc7-4333-8f9e-fc7cce4d458c");

insert into `isa_database`.`airplane`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`mark`,
`number_of_rows`,
`number_of_columns_per_segment`,
`number_of_segments`,
`airline_id`)
values("199aab3d-52a2-4e2d-b772-8fffe3615b77",0,"2019-11-09 14:34:02","2019-11-09 14:34:02","A113",5,2,2,"3997f762-9772-4b35-9a47-baf4c101ae7c");

/*STIGAO DOVDE	------------------------------------------------------*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("3a005f77-2552-4a14-a257-9750e709fc08",0,"2019-11-09 14:34:02","2019-11-09 14:34:02","2019-10-01 14:30:00","2019-10-02 09:00:00","18:30:00.000",250.0,200,"0dd530af-4832-4030-806f-fd5643595941","158b19d5-68c0-4170-8bd5-63af76402403");

insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("d0d9eb0f-f0d8-476e-9bf5-540fa5d2c44d",0,"2019-11-09 14:34:02","2019-11-09 14:34:02","2019-10-04 10:00:00","2019-10-05 05:00:00","19:00:00.000",750.0,200,"6a66078a-5083-4ba0-9654-8bdb547065b0","158b19d5-68c0-4170-8bd5-63af76402403");

insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("276b7a65-e199-46d8-9b53-3c9c3f249120",0,"2019-11-09 14:34:02","2019-11-09 14:34:02","2019-10-07 16:00:00","2019-10-07 19:15:00","03:15:00.000",2500.0,220,"0dd530af-4832-4030-806f-fd5643595941","158b19d5-68c0-4170-8bd5-63af76402403");

insert into `isa_database`.`user`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`first_name`,
`last_name`,
`role`,
`email`,
`password`,
`phone_number`,
`city`,
`state`,
`is_enabled`)
values
("27ec39be-70d7-473e-9f13-293164f5f8f8", 0,"2019-09-17 14:44:44","2019-09-17 14:44:44","Marko","Mitic","USER","markom@gmail.com",
"111","0612070400","Novi Sad","Serbia",1);

insert into `isa_database`.`destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`)values("8fff45a3-d9f0-4be2-a640-abe717312d8b",0,curdate(), curdate(),"Budapest", "Hungary");

insert into `isa_database`.`address`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`,
`latitude`,
`longitude`,
`street`)values("52329ed9-465a-437e-97a7-9d53f94aa4db",0,curdate(), curdate(),"Budapest", "Hungary" ,47.49801, 19.03991,"Rakotzi");

insert into `isa_database`.`airline`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`name`,
`address_id`,
`description`,
`checking_in_suitcase_price`,
`hand_luggage_price`)
values("05cc6876-3a4f-466e-b4bc-01edee578856",0, curdate(), curdate(),"AirHungary","52329ed9-465a-437e-97a7-9d53f94aa4db","Lorem ipsum dolor sit amet, consectetur adipiscing elit.",120,10);

insert into `isa_database`.`airline_destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`airline_id`,
`destination_id`)
 values ("0a30d5fa-0b17-446e-b6dd-2f9911cb950b",0, curdate(), curdate(),"05cc6876-3a4f-466e-b4bc-01edee578856","8fff45a3-d9f0-4be2-a640-abe717312d8b");

insert into `isa_database`.`airplane`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`mark`,
`number_of_rows`,
`number_of_columns_per_segment`,
`number_of_segments`,
`airline_id`)
values("30b4ae1d-1d52-4455-bab3-52002d12a574",0, curdate(), curdate(),"A114",5,4,2,"05cc6876-3a4f-466e-b4bc-01edee578856");

/*air serbia -> air hungary*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("0f998aaf-60b3-4467-89c6-47930882c535",0, curdate(), curdate(),"2019-09-30 06:00:00","2019-09-30 06:40:00","00:40:00.000",300.0,40,"0a30d5fa-0b17-446e-b6dd-2f9911cb950b", "158b19d5-68c0-4170-8bd5-63af76402403");

/*air hungary -> air serbia*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("ead16c37-aafb-4905-8745-8d6f3b3c9571",0, curdate(), curdate(),"2019-10-01 12:00:00","2019-10-01 12:40:00","00:40:00.000",300.0,40, "b636eb2d-8c2b-4192-8730-4b42dbe675f6","30b4ae1d-1d52-4455-bab3-52002d12a574");


/*------------------------------------------SLEDECI LET--------------------------------------------*/

insert into `isa_database`.`destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`)values("eb3f536f-c351-43bc-8b89-ad21e67a11a6",0,curdate(), curdate(),"Moscow", "Russia");

insert into `isa_database`.`address`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`city`,
`state`,
`latitude`,
`longitude`,
`street`)values("d5016817-5b29-4cd2-8f11-0756fa5a88ad",0,curdate(), curdate(),"Moscow", "Russia" ,55.75222, 37.61556,"Tverskar ul.");

insert into `isa_database`.`airline`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`name`,
`address_id`,
`description`,
`checking_in_suitcase_price`,
`hand_luggage_price`)
values("ed1ae64e-276e-4901-937b-37555968566d",0, curdate(), curdate(),"AirRussia","d5016817-5b29-4cd2-8f11-0756fa5a88ad","Aenean tempus a massa quis tincidunt. Suspendisse nec lacus.",220,40);

insert into `isa_database`.`airline_destination`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`airline_id`,
`destination_id`)
 values ("44d75aca-b97b-4c40-b87d-e2ce513b4486",0, curdate(), curdate(),"ed1ae64e-276e-4901-937b-37555968566d","eb3f536f-c351-43bc-8b89-ad21e67a11a6");
		
insert into `isa_database`.`airplane`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`mark`,
`number_of_rows`,
`number_of_columns_per_segment`,
`number_of_segments`,
`airline_id`)
values("a2960067-0af5-4161-83f5-d048bb116c2c",0, curdate(), curdate(),"A115",5,4,3,"ed1ae64e-276e-4901-937b-37555968566d");

/*air serbia -> air russia*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("0e378cee-3560-45ac-996f-7957b7d2ac47",0, curdate(), curdate(),"2019-10-05 09:00:00","2019-10-05 10:15:00","01:15:00.000",2500.0, 140, "44d75aca-b97b-4c40-b87d-e2ce513b4486", "158b19d5-68c0-4170-8bd5-63af76402403");

/*air russia -> air serbia*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("ad49e3d3-3113-46f3-a611-a0e2fc4f71f5",0, curdate(), curdate(),"2019-10-06 12:00:00","2019-10-06 13:15:00","01:15:00.000",2500.0, 140, "b636eb2d-8c2b-4192-8730-4b42dbe675f6", "a2960067-0af5-4161-83f5-d048bb116c2c");


/*air hungary -> air montenegro*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("b455b0ce-32ce-48c6-859c-8f3ef190480b",0, curdate(), curdate(),"2019-10-02 12:00:00","2019-10-04 13:00:00","01:00:00.000",600.0,60, "6a66078a-5083-4ba0-9654-8bdb547065b0","30b4ae1d-1d52-4455-bab3-52002d12a574");

/*air hungary -> air montenegro*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("57205498-fbe0-4cb6-b7ad-f02147bfad26",0, curdate(), curdate(),"2019-10-02 16:00:00","2019-10-06 17:00:00","01:00:00.000",600.0,60, "6a66078a-5083-4ba0-9654-8bdb547065b0","30b4ae1d-1d52-4455-bab3-52002d12a574");

/*air hungary -> air spain*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("ae74c211-ede8-4d6b-acd5-fceb048b330c",0, curdate(), curdate(),"2019-10-05 16:00:00","2019-10-09 10:00:00","18:00:00.000",2540.0, 300, "0dd530af-4832-4030-806f-fd5643595941", "30b4ae1d-1d52-4455-bab3-52002d12a574");

/*air hungary -> air russia*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("84f98fdc-8621-417c-a8b0-cadae552791c",0, curdate(), curdate(),"2019-10-07 15:00:00","2019-10-12 07:00:00","16:00:00.000",1400.0, 350, "44d75aca-b97b-4c40-b87d-e2ce513b4486", "30b4ae1d-1d52-4455-bab3-52002d12a574");

/*air hungary -> air serbia*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("bf24e443-d5fe-4340-a3d1-e0be9aead8c1",0, curdate(), curdate(),"2019-10-15 15:00:00","2019-10-16 15:40:00","00:40:00.000",280.0, 35, "b636eb2d-8c2b-4192-8730-4b42dbe675f6", "30b4ae1d-1d52-4455-bab3-52002d12a574");

/*air spain -> air moscow */
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("70197ab2-2516-4070-a823-753877f68066",0, curdate(), curdate(),"2019-10-11 13:00:00","2019-10-13 17:00:00","04:00:00.000",3560.0, 290, "44d75aca-b97b-4c40-b87d-e2ce513b4486", "199aab3d-52a2-4e2d-b772-8fffe3615b77");

/*air spain -> air serbia*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("73885207-dde6-4440-bc88-50208eea096d",0, curdate(), curdate(),"2019-10-01 12:00:00","2019-10-03 15:00:00","03:00:00.000",2400.0, 230, "b636eb2d-8c2b-4192-8730-4b42dbe675f6", "199aab3d-52a2-4e2d-b772-8fffe3615b77");

/*air spain -> air montenegro*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("4203fd3f-7ef3-4bfa-b300-d28592efba31",0, curdate(), curdate(),"2019-10-02 11:00:00","2019-10-04 17:00:00","06:00:00.000",2100.0, 300, "6a66078a-5083-4ba0-9654-8bdb547065b0", "199aab3d-52a2-4e2d-b772-8fffe3615b77");

/*air spain -> air hungary*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("ca1ce1d6-5c7f-45c2-84c9-d7f37dc967fd",0, curdate(), curdate(),"2019-10-10 09:00:00","2019-10-14 12:00:00","06:00:00.000",2320.0, 310, "0a30d5fa-0b17-446e-b6dd-2f9911cb950b", "199aab3d-52a2-4e2d-b772-8fffe3615b77");

/*air russia -> air spain*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("25601c79-f7c1-4334-87f4-0e2c17a41847",0, curdate(), curdate(),"2019-10-09 08:00:00","2019-10-16 13:15:00","05:15:00.000",3400.0, 140, "0dd530af-4832-4030-806f-fd5643595941", "a2960067-0af5-4161-83f5-d048bb116c2c");

/*air russia -> air hungary*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("44d2fb98-1b92-49e5-bf53-fdcf8b567436",0, curdate(), curdate(),"2019-10-18 10:00:00","2019-10-22 12:30:00","02:30:00.000",1600.0, 90, "0a30d5fa-0b17-446e-b6dd-2f9911cb950b", "a2960067-0af5-4161-83f5-d048bb116c2c");

/*air montenegro -> air hungary*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("0107a671-588e-4420-93ba-b86075767f4a",0, curdate(), curdate(),"2019-10-15 16:00:00","2019-10-23 17:00:00","01:00:00.000",600.0,60, "0a30d5fa-0b17-446e-b6dd-2f9911cb950b","c11fe8c8-3c41-4571-a7f6-6f6a5bcaf645");

/*air montenegro -> air spain*/
insert into `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
values ("510aa337-8809-42fa-9af2-571b16ad8486",0, curdate(), curdate(),"2019-10-05 16:00:00","2019-10-07 17:00:00","01:00:00.000",600.0,60, "0dd530af-4832-4030-806f-fd5643595941", "c11fe8c8-3c41-4571-a7f6-6f6a5bcaf645");

/*AIRLINES*/
insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("2b1a7fb5-ca66-4861-8014-9f2318bf7645",
"05cc6876-3a4f-466e-b4bc-01edee578856",
"AIRLINE",
9,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("46fdf841-db42-4f6f-bbad-6197c7f14715",
"3997f762-9772-4b35-9a47-baf4c101ae7c",
"AIRLINE",
9,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("ae776f1d-4d6c-4346-b77f-57eb41fa1fc3",
"e7cc51b2-8fc7-4333-8f9e-fc7cce4d458c",
"AIRLINE",
9,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("010afa56-fa51-4d9a-b43c-f9eeec20b6dd",
"ed1ae64e-276e-4901-937b-37555968566d",
"AIRLINE",
9,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("7fac45df-6e4c-41bf-9e06-caeb2bea5ae4",
"ef6db0d2-08d4-4155-8cd4-53983f67354b",
"AIRLINE",
9,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");
/*-----------------------------FLIGHTS-----------------------------*/
insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("28fa1623-31d0-494f-824d-29f6b8d3fca4",
"0e378cee-3560-45ac-996f-7957b7d2ac47",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("0f50595a-85bb-4848-b9e8-1d9709ec93e3",
"0f998aaf-60b3-4467-89c6-47930882c535",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");
insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("5f24b0ec-db57-4400-aef1-5dec170a5ca3",
"25601c79-f7c1-4334-87f4-0e2c17a41847",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("c1f3c6b7-9bb6-4c2b-91a9-d382259df0df",
"276b7a65-e199-46d8-9b53-3c9c3f249120",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("a7b6ae4c-081f-4f63-a865-e2c22e030797",
"3a005f77-2552-4a14-a257-9750e709fc08",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("f7ad2398-dd50-4afe-89fc-9e0c6fd05515",
"44d2fb98-1b92-49e5-bf53-fdcf8b567436",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("22bb9bf9-0587-4c30-9168-08b857578f5e",
"57205498-fbe0-4cb6-b7ad-f02147bfad26",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("8ee9c265-2f9f-417d-a8ed-0544fc0027ef",
"70197ab2-2516-4070-a823-753877f68066",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("b64e8607-063d-4ced-bd71-3fbec64ff789",
"73885207-dde6-4440-bc88-50208eea096d",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("4ea6cd82-2c82-4dff-8889-2a572cb17cdc",
"84f98fdc-8621-417c-a8b0-cadae552791c",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("4c24d1b2-990b-432b-8dbf-f13706264da2",
"ad49e3d3-3113-46f3-a611-a0e2fc4f71f5",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("1357e55e-7e6f-4ead-8bbb-6a7ca6d79882",
"ae74c211-ede8-4d6b-acd5-fceb048b330c",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("3f6ff283-69c1-40f2-bb0f-927cda4a87bf",
"b455b0ce-32ce-48c6-859c-8f3ef190480b",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("ca402617-23c0-42d6-9ca1-6aa4c0fd540c",
"bf24e443-d5fe-4340-a3d1-e0be9aead8c1",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("9bfd4c32-36e7-4518-b682-8af15b9bc999",
"ca1ce1d6-5c7f-45c2-84c9-d7f37dc967fd",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("96606706-ee0b-48a0-a086-b36b30a3869d",
"d0d9eb0f-f0d8-476e-9bf5-540fa5d2c44d",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("dfe62f31-fab3-458e-9b44-aac17012dec6",
"ead16c37-aafb-4905-8745-8d6f3b3c9571",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("04cd8bed-18f9-4a69-be07-e51075cb7d9c",
"4203fd3f-7ef3-4bfa-b300-d28592efba31",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("9510d13b-f6a1-405c-83c0-0a687be7548c",
"0107a671-588e-4420-93ba-b86075767f4a",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");

insert into `isa_database`.`rating`
(`id`,
`entity_id`,
`entity_type`,
`mark`,
`is_deleted`,
`time_created`,
`time_updated`,
`user_id`)
values
("38f6b88d-31aa-4e88-b214-9106b88df1b0",
"510aa337-8809-42fa-9af2-571b16ad8486",
"FLIGHT",
8,
0,
curdate(), curdate(), "27ec39be-70d7-473e-9f13-293164f5f8f8");


/*air hungary -> air montenegro*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("b23475fd-559f-46ea-9a11-970b3c9da616",0, curdate(), curdate(),"2019-09-30 12:00:00","2019-10-03 13:00:00","01:00:00.000",600.0,60, "6a66078a-5083-4ba0-9654-8bdb547065b0","30b4ae1d-1d52-4455-bab3-52002d12a574");
/*air hungary -> air montenegro*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("2d94918b-7d16-45b5-9be4-3cf3d7fceefa",0, curdate(), curdate(),"2019-09-30 16:00:00","2019-10-04 17:00:00","01:00:00.000",600.0,60, "6a66078a-5083-4ba0-9654-8bdb547065b0","30b4ae1d-1d52-4455-bab3-52002d12a574");
/*air hungary -> air spain*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("9e5f39c0-ce88-49f0-9f66-29625c0fa919",0, curdate(), curdate(),"2019-09-30 16:00:00","2019-10-05 10:00:00","18:00:00.000",2540.0, 300, "0dd530af-4832-4030-806f-fd5643595941", "30b4ae1d-1d52-4455-bab3-52002d12a574");
/*air hungary -> air russia*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("11efa8b8-f4db-402a-a5be-7e39c5d3c5b6",0, curdate(), curdate(),"2019-09-30 15:00:00","2019-10-06 07:00:00","16:00:00.000",1400.0, 350, "44d75aca-b97b-4c40-b87d-e2ce513b4486", "30b4ae1d-1d52-4455-bab3-52002d12a574");
/*air spain -> air moscow */
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("e7b171c2-2f6f-45eb-a406-5922770e7b87",0, curdate(), curdate(),"2019-09-30 13:00:00","2019-10-02 17:00:00","04:00:00.000",3560.0, 290, "44d75aca-b97b-4c40-b87d-e2ce513b4486", "199aab3d-52a2-4e2d-b772-8fffe3615b77");
/*air spain -> air serbia*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("b520262c-d694-4ea8-9dd7-815e5025aa70",0, curdate(), curdate(),"2019-09-30 12:00:00","2019-10-04 15:00:00","03:00:00.000",2400.0, 230, "b636eb2d-8c2b-4192-8730-4b42dbe675f6", "199aab3d-52a2-4e2d-b772-8fffe3615b77");
/*air spain -> air montenegro*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("f730c7be-8798-4b05-9caf-cab1785dd2e6",0, curdate(), curdate(),"2019-09-30 11:00:00","2019-10-06 17:00:00","06:00:00.000",2100.0, 300, "6a66078a-5083-4ba0-9654-8bdb547065b0", "199aab3d-52a2-4e2d-b772-8fffe3615b77");
/*air russia -> air spain*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("2767e072-ee3b-4dc9-bb23-f3352cad55d6",0, curdate(), curdate(),"2019-09-30 08:00:00","2019-10-05 13:15:00","05:15:00.000",3400.0, 140, "0dd530af-4832-4030-806f-fd5643595941", "a2960067-0af5-4161-83f5-d048bb116c2c");

/*air russia -> air hungary*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("3dec2fd6-2d7b-4cdb-9761-32c745d3c7a8",0, curdate(), curdate(),"2019-09-30 10:00:00","2019-10-05 12:30:00","02:30:00.000",1600.0, 90, "0a30d5fa-0b17-446e-b6dd-2f9911cb950b", "a2960067-0af5-4161-83f5-d048bb116c2c");

/*air serbia -> air hungary*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("dbb62c21-72fd-4988-9906-3e74ef37a468",0, curdate(), curdate(),"2019-09-30 06:00:00","2019-10-07 06:40:00","00:40:00.000",300.0,40,"0a30d5fa-0b17-446e-b6dd-2f9911cb950b", "158b19d5-68c0-4170-8bd5-63af76402403");

/*air serbia -> air hungary*/
INSERT INTO `isa_database`.`flight`
(`id`,
`is_deleted`,
`time_created`,
`time_updated`,
`departure_time`,
`arrival_time`,
`duration`,
`length`,
`price`,
`airline_destination_id`,
`airplane_id`)
VALUES ("6c48d1eb-81dd-4e1c-8350-a9dce51cafda",0, curdate(), curdate(),"2019-09-30 09:00:00","2019-10-06 09:40:00","00:40:00.000",300.0,40,"0a30d5fa-0b17-446e-b6dd-2f9911cb950b", "158b19d5-68c0-4170-8bd5-63af76402403");

INSERT INTO `isa_database`.`address` (`id`, `is_deleted`, `time_created`, `time_updated`, `city`, `state`, `latitude`, `longitude`, `street`) VALUES ('eaf6b7f4-f693-4049-b705-cbf3eb33817e', b'0', '2019-09-09', '2019-09-09', 'Novi Sad', 'Serbia', '45.26', '19.83', 'Stevana Sremca');
INSERT INTO `isa_database`.`address` (`id`, `is_deleted`, `time_created`, `time_updated`, `city`, `state`, `latitude`, `longitude`, `street`) VALUES ('1a20cb38-5d83-41ab-bb35-726a3b29a444', b'0', '2019-09-09', '2019-09-09', 'Beograd', 'Serbia', '44.78', '20.44', 'Bulevar Kralja Aleksandra');
INSERT INTO `isa_database`.`address` (`id`, `is_deleted`, `time_created`, `time_updated`, `city`, `state`, `latitude`, `longitude`, `street`) VALUES ('b827d6a1-2dfb-4df8-856b-b28860816c17', b'0', '2019-09-09', '2019-09-09', 'Niš', 'Serbia', '43.32', '21.89', 'Bulevar Revolucije');

INSERT INTO `isa_database`.`user` (`id`, `is_deleted`, `time_created`, `time_updated`, `first_name`, `last_name`, `role`, `email`, `password`, `phone_number`, `city`, `state`, `is_enabled`) VALUES ('0843bca9-7dbf-4da0-8b5b-49afe9c002a4', b'0', '2019-09-09', '2019-09-09', 'Marko', 'Markovic', 'RENT_A_CAR_ADMIN', 'marko@gmail.com', '$2a$10$tHlpHYZmzDlo4ojfMl1qseYE84PF9Bu0WdTvh.w79uV2yQGldNC92', '06851111', 'Sremska Mitrovica', 'Srbija', b'1');
INSERT INTO `isa_database`.`user` (`id`, `is_deleted`, `time_created`, `time_updated`, `first_name`, `last_name`, `role`, `email`, `password`, `phone_number`, `city`, `state`, `is_enabled`) VALUES ('d97b7fa5-705b-4ec0-a494-91266ec2b62a', b'0', '2019-09-09', '2019-09-09', 'Petar', 'Petrovic', 'RENT_A_CAR_ADMIN', 'petar@gmail.com', '$2a$10$tHlpHYZmzDlo4ojfMl1qseYE84PF9Bu0WdTvh.w79uV2yQGldNC92', '0681281', 'Sremska Kamenica', 'Srbija', b'1');
INSERT INTO `isa_database`.`user` (`id`, `is_deleted`, `time_created`, `time_updated`, `first_name`, `last_name`, `role`, `email`, `password`, `phone_number`, `city`, `state`, `is_enabled`) VALUES ('5b3091f8-d0a1-4918-9b89-470880133a6f', b'0', '2019-09-09', '2019-09-09', 'Lazar', 'Lazovic', 'USER', 'lazar@gmail.com', '$2a$10$tHlpHYZmzDlo4ojfMl1qseYE84PF9Bu0WdTvh.w79uV2yQGldNC92', '06891234', 'Beograd', 'Srbija', b'1');
INSERT INTO `isa_database`.`user` (`id`, `is_deleted`, `time_created`, `time_updated`, `first_name`, `last_name`, `role`, `email`, `password`, `phone_number`, `city`, `state`, `is_enabled`) VALUES ('a49e6bc5-d105-4d9b-951a-5d35abfe53c2', b'0', '2019-09-09', '2019-09-09', 'Nikola', 'Nikolica', 'USER', 'nikola@gmail.com', '$2a$10$tHlpHYZmzDlo4ojfMl1qseYE84PF9Bu0WdTvh.w79uV2yQGldNC92', '06891234', 'Beograd', 'Srbija', b'1');

INSERT INTO `isa_database`.`rent_a_car` (`id`, `is_deleted`, `time_created`, `time_updated`, `name`, `description`, `address_id`, `rating`, `version`) VALUES ('059cd705-f75a-40d4-9dba-8a5b17e514e7', b'0', '2019-09-09', '2019-09-09', 'Kodak', 'Brza vozila', 'eaf6b7f4-f693-4049-b705-cbf3eb33817e', '10', '0');
INSERT INTO `isa_database`.`rent_a_car` (`id`, `is_deleted`, `time_created`, `time_updated`, `name`, `description`, `address_id`, `rating`, `version`) VALUES ('16411f00-4500-479f-8b04-ba21b9954a4d', b'0', '2019-09-09', '2019-09-09', 'Index', 'Povoljna vozila', '1a20cb38-5d83-41ab-bb35-726a3b29a444', '9', '0');
INSERT INTO `isa_database`.`rent_a_car` (`id`, `is_deleted`, `time_created`, `time_updated`, `name`, `description`, `address_id`, `rating`, `version`) VALUES ('16e4fc4d-1807-4d73-99ef-33addc4125b0', b'0', '2019-09-09', '2019-09-09', 'Deluxe', 'Eksluzivna vozila', 'b827d6a1-2dfb-4df8-856b-b28860816c17', '10', '0');

INSERT INTO `isa_database`.`rent_a_car_admin` (`id`, `is_deleted`, `time_created`, `time_updated`, `user_id`, `rent_a_car_id`, `is_not_first_login`) VALUES ('52592a9a-4034-4b53-8fd3-c15be4c6be9c', b'0', '2019-09-09', '2019-09-09', '0843bca9-7dbf-4da0-8b5b-49afe9c002a4', '059cd705-f75a-40d4-9dba-8a5b17e514e7', b'0');
INSERT INTO `isa_database`.`rent_a_car_admin` (`id`, `is_deleted`, `time_created`, `time_updated`, `user_id`, `rent_a_car_id`, `is_not_first_login`) VALUES ('b0210d8a-ea57-4069-a8ab-0b51200791f2', b'0', '2019-09-09', '2019-09-09', '0843bca9-7dbf-4da0-8b5b-49afe9c002a4', '16411f00-4500-479f-8b04-ba21b9954a4d', b'0');
INSERT INTO `isa_database`.`rent_a_car_admin` (`id`, `is_deleted`, `time_created`, `time_updated`, `user_id`, `rent_a_car_id`, `is_not_first_login`) VALUES ('d01da035-796c-430e-9d47-54dcbf26cfd3', b'0', '2019-09-09', '2019-09-09', 'd97b7fa5-705b-4ec0-a494-91266ec2b62a', '16e4fc4d-1807-4d73-99ef-33addc4125b0', b'0');

INSERT INTO `isa_database`.`agency_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `state`, `city`) VALUES ('1ee0ded5-3f6b-4a24-b267-e5de16deec34', b'0', '2019-09-09', '2019-09-09', 'Serbia', 'Novi Sad');
INSERT INTO `isa_database`.`agency_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `state`, `city`) VALUES ('dae5e978-6456-49aa-b534-45f094bbb155', b'0', '2019-09-09', '2019-09-09', 'Serbia', 'Sremska Mitrovica');
INSERT INTO `isa_database`.`agency_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `state`, `city`) VALUES ('1b8b01f8-0a27-4176-bc12-e80653197d51', b'0', '2019-09-09', '2019-09-09', 'Serbia', 'Niš');
INSERT INTO `isa_database`.`agency_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `state`, `city`) VALUES ('92b34c06-b5a0-4a4e-9f84-0de52ef93aa8', b'0', '2019-09-09', '2019-09-09', 'Serbia', 'Sremska Kamenica');
INSERT INTO `isa_database`.`agency_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `state`, `city`) VALUES ('7632544b-612d-48e9-91ee-11ef20cf9d88', b'0', '2019-09-09', '2019-09-09', 'France', 'Paris');

INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('42e513ce-dc88-43da-9349-da3424620e14', b'0', '2019-09-09','2019-09-09', '059cd705-f75a-40d4-9dba-8a5b17e514e7', '1ee0ded5-3f6b-4a24-b267-e5de16deec34');
INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('b303e5f2-ce41-4d06-b85b-ed752e23b432', b'0', '2019-09-09','2019-09-09', '059cd705-f75a-40d4-9dba-8a5b17e514e7', 'dae5e978-6456-49aa-b534-45f094bbb155');
INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('167fec56-ea1a-4c89-ae2b-af8f31842bc8', b'0', '2019-09-09','2019-09-09', '059cd705-f75a-40d4-9dba-8a5b17e514e7', '1b8b01f8-0a27-4176-bc12-e80653197d51');
INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('c2a79845-135c-47eb-8c87-9d0f7908622e', b'0', '2019-09-09','2019-09-09', '16411f00-4500-479f-8b04-ba21b9954a4d', '1b8b01f8-0a27-4176-bc12-e80653197d51');
INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('5cbd588a-5177-4cfd-ba4a-d638d77e1257', b'0', '2019-09-09','2019-09-09', '16411f00-4500-479f-8b04-ba21b9954a4d', 'dae5e978-6456-49aa-b534-45f094bbb155');
INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('80c131a1-f17f-40a4-8b0e-d0786637988a', b'0', '2019-09-09','2019-09-09', '16e4fc4d-1807-4d73-99ef-33addc4125b0', 'dae5e978-6456-49aa-b534-45f094bbb155');
INSERT INTO `isa_database`.`rent_a_car_location` (`id`, `is_deleted`, `time_created`, `time_updated`, `rent_a_car_id`, `agency_location_id`) VALUES ('a1acd6f4-46b3-40ba-8d0b-b3d5edb26313', b'0', '2019-09-09','2019-09-09', '16e4fc4d-1807-4d73-99ef-33addc4125b0', '7632544b-612d-48e9-91ee-11ef20cf9d88');

INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('9b812e4f-e8b2-4add-9823-4171c9c4e06a', b'0', '2019-09-09', '2019-09-09', 'BMW', 'M7', '5', '1200', 'Automobile', '2015', '9', '059cd705-f75a-40d4-9dba-8a5b17e514e7', '0');
INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('1393cc9e-a6cc-47c1-9216-ba73ca6f6125', b'0', '2019-09-09', '2019-09-09', 'Mercedes', 'E200', '4', '1200', 'Automobile', '2015', '10', '059cd705-f75a-40d4-9dba-8a5b17e514e7', '0');
INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('c63ae9da-470f-4670-9e4c-999bdd2f1824', b'0', '2019-09-09', '2019-09-09', 'BMX', 'Y6', '4', '150', 'Bicycle', '2013', '9', '059cd705-f75a-40d4-9dba-8a5b17e514e7', '0');
INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('a31b07cc-d9e4-4f31-9c9e-bf69116213ff', b'0', '2019-09-09', '2019-09-09', 'Toyota', 'Yaris', '4', '250', 'Automobile', '2019', '9', '16411f00-4500-479f-8b04-ba21b9954a4d', '0');
INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('9e5f64e2-386f-457a-a17e-a255abfc2e85', b'0', '2019-09-09', '2019-09-09', 'Honda', 'Accord', '4', '500', 'Automobile', '2017', '10', '16411f00-4500-479f-8b04-ba21b9954a4d', '0');
INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('0177cce1-4ff6-4ccf-a9cb-9a1a0b1fed91', b'0', '2019-09-09', '2019-09-09', 'Honda', 'Civic', '4', '350', 'Automobile', '2017', '7', '16e4fc4d-1807-4d73-99ef-33addc4125b0', '0');
INSERT INTO `isa_database`.`vehicle` (`id`, `is_deleted`, `time_created`, `time_updated`, `brand`, `model`, `number_of_people`, `price_per_day`, `type`, `year_of_production`, `rating`, `rent_a_car_id`, `version`) VALUES ('ef150743-a05d-4e65-9512-ca09e5d2ce04', b'0', '2019-09-09', '2019-09-09', 'Fiat', 'Multipla', '6', '800', 'Automobile', '2016', '7', '16e4fc4d-1807-4d73-99ef-33addc4125b0', '0');

INSERT INTO `isa_database`.`vehicle_reservation` (`id`, `is_deleted`, `time_created`, `time_updated`, `start_date`, `end_date`, `price`, `vehicle_id`,`user_id`, `version`) VALUES ('ce816f13-8c8b-4b6c-8f31-e56fe05af67f', b'0', '2019-09-09', '2019-09-09', '2019-09-09', '2019-09-12', '4800', '9b812e4f-e8b2-4add-9823-4171c9c4e06a','a49e6bc5-d105-4d9b-951a-5d35abfe53c2', '0');
INSERT INTO `isa_database`.`vehicle_reservation` (`id`, `is_deleted`, `time_created`, `time_updated`, `start_date`, `end_date`, `price`, `vehicle_id`,`user_id`, `version`) VALUES ('78107987-67ec-4f59-a405-71ec005955e7', b'0', '2019-09-09', '2019-09-09', '2019-09-19', '2019-09-22', '4800', '9b812e4f-e8b2-4add-9823-4171c9c4e06a','a49e6bc5-d105-4d9b-951a-5d35abfe53c2', '0');
INSERT INTO `isa_database`.`vehicle_reservation` (`id`, `is_deleted`, `time_created`, `time_updated`, `start_date`, `end_date`, `price`, `vehicle_id`,`user_id`, `version`) VALUES ('bfa0f6ee-ab35-4422-9705-c41543b29bb9', b'0', '2019-09-09', '2019-09-09', '2019-11-11', '2019-11-15', '2500', 'c63ae9da-470f-4670-9e4c-999bdd2f1824','a49e6bc5-d105-4d9b-951a-5d35abfe53c2', '0');
INSERT INTO `isa_database`.`vehicle_reservation` (`id`, `is_deleted`, `time_created`, `time_updated`, `start_date`, `end_date`, `price`, `vehicle_id`,`user_id`, `version`) VALUES ('f71ecf47-7347-4970-a4dd-3e6b5faec2e0', b'0', '2019-09-09', '2019-09-09', '2019-11-19', '2019-11-23', '2500', '9e5f64e2-386f-457a-a17e-a255abfc2e85','a49e6bc5-d105-4d9b-951a-5d35abfe53c2', '0');

INSERT INTO `isa_database`.`discount` (`id`, `is_deleted`, `time_created`, `time_updated`, `end_date`, `entity_id`, `start_date`, `entity_type`, `rate`) VALUES ('203e5b05-2cc7-4d35-b8da-34a31d4ade5e', b'0', '2019-09-09', '2019-09-09', '2019-09-12', '9b812e4f-e8b2-4add-9823-4171c9c4e06a', '2019-09-15', 'VEHICLE', '50');
INSERT INTO `isa_database`.`discount` (`id`, `is_deleted`, `time_created`, `time_updated`, `end_date`, `entity_id`, `start_date`, `entity_type`, `rate`) VALUES ('cd684174-976d-4d89-9d1a-404612568181', b'0', '2019-09-09', '2019-09-09', '2019-09-12', '1393cc9e-a6cc-47c1-9216-ba73ca6f6125', '2019-09-15', 'VEHICLE', '45');
INSERT INTO `isa_database`.`discount` (`id`, `is_deleted`, `time_created`, `time_updated`, `end_date`, `entity_id`, `start_date`, `entity_type`, `rate`) VALUES ('bcc50dac-5c9d-4468-905a-436496186393', b'0', '2019-09-09', '2019-11-01', '2019-11-12', '1393cc9e-a6cc-47c1-9216-ba73ca6f6125', '2019-09-15', 'VEHICLE', '35');

 
 
INSERT INTO `isa_database`.`airline_admin` (`id`,`is_deleted`,`time_created`,`time_updated`,`user_id`,`airline_id`,`is_first_login`) VALUES ('8940fd87-2a0e-4796-bee0-7ef88e930f79',0,'2019-09-25 00:00:00','2019-09-25 00:00:00','0c16acf1-1393-4e81-b7e9-a9ea71ef26bc','e7cc51b2-8fc7-4333-8f9e-fc7cce4d458c',0);
INSERT INTO address (id, is_deleted, time_created, city, state, latitude, longitude, street) 
	VALUES ('eb8beed3-c584-4dc9-b4e0-95f1ea93d325', 0, curdate(), 'Beograd', 'Srbija', 29.2, 23.2, 'Ulica');
INSERT INTO hotel (id, is_deleted, time_created, time_updated, name, address_id, description, rating, version) 
	VALUES ('dceb8123-3456-40a0-b396-f705ac0c5738', 0, curdate(), null, 'Hotel1', 'eb8beed3-c584-4dc9-b4e0-95f1ea93d325', 'DES', 0.5, 1);
INSERT INTO `isa_database`.`room` (`id`, `is_deleted`, `time_created`, `number`, `floor`, `price_summer`, `price_winter`, `price_autumn`, `price_spring`, `number_of_people`, `hotel_id`, `rating`, `version`) 
VALUES ('a5e239ea-b0a4-40af-9b39-4b906e85d176', 0 , curdate(), 1, 1, 123.1, 1.1, 14.1, 12.1, 3, 'dceb8123-3456-40a0-b396-f705ac0c5738', 3, 1);
INSERT INTO `isa_database`.`room` (`id`, `is_deleted`, `time_created`, `number`, `floor`, `price_summer`, `price_winter`, `price_autumn`, `price_spring`, `number_of_people`, `hotel_id`, `rating`, `version`) 
VALUES ('97884efa-4303-46bd-b7f0-75fb549e9578', 0 , curdate(), 2, 2, 3.1, 1.1, 14.1, 12.1, 3, 'dceb8123-3456-40a0-b396-f705ac0c5738', 2, 1);
INSERT INTO `isa_database`.`room` (`id`, `is_deleted`, `time_created`, `number`, `floor`, `price_summer`, `price_winter`, `price_autumn`, `price_spring`, `number_of_people`, `hotel_id`, `rating`, `version`) 
VALUES ('84dd64df-bce5-4147-b081-2675c8df604b', 0 , curdate(), 2, 3, 1.1, 1.1, 14.1, 12.1, 3, 'dceb8123-3456-40a0-b396-f705ac0c5738', 2, 1);
INSERT INTO `isa_database`.`room` (`id`, `is_deleted`, `time_created`, `number`, `floor`, `price_summer`, `price_winter`, `price_autumn`, `price_spring`, `number_of_people`, `hotel_id`, `rating`, `version`) 
VALUES ('45c10228-761e-4e44-8bc2-2252dd9bdaac', 0 , curdate(), 2, 4, 2.1, 1.1, 14.1, 12.1, 3, 'dceb8123-3456-40a0-b396-f705ac0c5738', 6, 1);
INSERT INTO `isa_database`.`room` (`id`, `is_deleted`, `time_created`, `number`, `floor`, `price_summer`, `price_winter`, `price_autumn`, `price_spring`, `number_of_people`, `hotel_id`, `rating`, `version`) 
VALUES ('a30a926f-af67-43d2-9d18-831f1f64372d', 0 , curdate(), 5, 2, 13.1, 1.1, 14.1, 12.1, 3, 'dceb8123-3456-40a0-b396-f705ac0c5738', 9, 1);
INSERT INTO `service` (`id`, `is_deleted`, `time_created`, `name`) VALUES ('f13a0aae-0711-4626-8b18-ec819b768aca', b'0', curdate(), 'WIFI');
INSERT INTO `service` (`id`, `is_deleted`, `time_created`, `name`) VALUES ('6b7e70cc-05af-4b0d-871b-cfbedeeb9d93', b'0', curdate(), 'IRON');
INSERT INTO `service` (`id`, `is_deleted`, `time_created`, `name`) VALUES ('81a425ef-9bb5-4104-be00-d37d442dc3e8', b'0', curdate(), 'Massage');
INSERT INTO `service` (`id`, `is_deleted`, `time_created`, `name`) VALUES ('715bd76a-2e3d-407c-bfc1-68c8d4f1e249', b'0', curdate(), 'Dish');

INSERT INTO `hotel_service` (`id`, `is_deleted`, `time_created`, `hotel_id`, `service_id`, `price`) VALUES ('715bd76a-2e3d-407c-bfc1-68c8d4f1e249', b'0', curdate(), 'dceb8123-3456-40a0-b396-f705ac0c5738', 'f13a0aae-0711-4626-8b18-ec819b768aca', 2.0);
INSERT INTO `hotel_service` (`id`, `is_deleted`, `time_created`, `hotel_id`, `service_id`, `price`) VALUES ('dceb8123-3456-40a0-b396-f705ac0c5738', b'0', curdate(), 'dceb8123-3456-40a0-b396-f705ac0c5738', '6b7e70cc-05af-4b0d-871b-cfbedeeb9d93', 3.0);
INSERT INTO `hotel_service` (`id`, `is_deleted`, `time_created`, `hotel_id`, `service_id`, `price`) VALUES ('81a425ef-9bb5-4104-be00-d37d442dc3e8', b'0', curdate(), 'dceb8123-3456-40a0-b396-f705ac0c5738', '715bd76a-2e3d-407c-bfc1-68c8d4f1e249', 2.0);
