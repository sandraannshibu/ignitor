/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.9 : Database - ignitor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ignitor` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ignitor`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `details` varchar(1000) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `blog` */

insert  into `blog`(`blog_id`,`user_id`,`title`,`details`,`image`,`comments`) values (9,8,'The rising EV surge','the EV month is celebrated from 1st June to 30th Jube 2023','static/uploads/f5437f63-3316-4fe3-be39-b6d6190ef078abc.jpg','lets go for it');

/*Table structure for table `bunk` */

DROP TABLE IF EXISTS `bunk`;

CREATE TABLE `bunk` (
  `bunk_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bunk_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `bunk` */

insert  into `bunk`(`bunk_id`,`login_id`,`name`,`place`,`latitude`,`longitude`,`status`) values (3,35,'Kseb Puthencruz Station','Puthencruz ','10.1320859','76.5018363','open'),(4,37,'Kseb Chargepoint Kothamangalam ','Kothamangalam ','10.13207775','76.50182603','open'),(5,39,'Kseb Perumbavoor','Perumbavoor','10.13208341','76.50183271','open'),(6,41,'Kseb Muvattupuzha ','Muvattupuzha ','10.1320859','76.5018365','open'),(7,43,'Kseb Kuruppampady ','Kuruppampady ','10.1319754','76.50176559','open'),(8,44,'Kseb Thripunithara','Thripunithara','10.1320831','76.5018373','open');

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `company` */

insert  into `company`(`company_id`,`login_id`,`name`,`place`,`phone`,`email`) values (10,50,'Ather','Trivandrum','8281546987','aethergrid@gmail.com'),(9,49,'TVS','Edapally','8281625485','tvsedapall@gmail.com'),(8,48,'OLA','Kakkanad','7541265894','olaeklm@gmail.com'),(4,42,'TATA','Mumbai','8281755692','sandraannshibu555@gmail.com'),(5,45,'KIA','Delhi','8281785412','kiaindia@gmail.com'),(6,46,'MG','Noida','8281954763','mgnoida@gmail.com'),(7,47,'BMW','Palarivattom','7558432695','bmwplrvtm@gmail.com'),(11,51,'Bajaj','Kaloor','7985412635','bajajkaloor@gmail.com'),(12,52,'Simple one','Muvattupuzha','8254123658','simpleonekm@gmail.com');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`user_id`,`complaint`,`reply`,`date`) values (9,43,'cannot add amount','pending','2023-06-18 00:56:04'),(8,55,'payment pending for customer 135333','pending','2023-06-18 00:54:16'),(7,32,'the product was not of good quality .product order no 134554','pending','2023-06-18 00:52:11');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values (32,'Robert','Robooooo','User'),(60,'Kevin','keve1010','Deliveryboy'),(59,'parvaty','paru0000','Deliveryboy'),(58,'Uma','Umma0000','Deliveryboy'),(15,'admin','admin','admin'),(57,'Maya','Maya9876','Mechanic'),(56,'Harry','Harr1234','block'),(21,'ananthu','ananthu','Mechanic'),(55,'soyal','Soyal123','Mechanic'),(54,'firoz','Fire1234','Mechanic'),(53,'Justin','Just1234','Mechanic'),(28,'sandra','Ann@123','company'),(29,'hanna','Hanna@123','company'),(63,'sudakaran','suda@123','pending'),(33,'ameera','ameeeeee','User'),(34,'siddi','sidddddd','User'),(35,'kseb19','Ksb12','Bunk'),(36,'gautham','ga123456','User'),(37,'kskthn98','Kthmn981','Bunk'),(38,'asif','asif@123','User'),(39,'prmbvr19','prmbvr19','Bunk'),(40,'hazel','aabb@123','Deliveryboy'),(41,'mvtpzha98','mvtpzha@98','Bunk'),(42,'Tata','newTATA@1','company'),(43,'krpmdy56','krpmdy56','Bunk'),(44,'thrpntra45','thrpntra45','Bunk'),(45,'kiaindia','Kia@1234','company'),(46,'MG','Noidamg@1','company'),(47,'bmwindia','Palarivattom@bmw1','company'),(48,'olakakkanad','Ola@6654','company'),(49,'tvskochi','Tvs@edapally6','company'),(50,'aether1','Aether@tvm1','company'),(51,'bajaj','Kaloor@2345','company'),(52,'simpleone','Mutzha@23','company'),(61,'Mamita','mami0909','Pending'),(62,'Tim','Timi1203','Pending');

/*Table structure for table `mechanic` */

DROP TABLE IF EXISTS `mechanic`;

CREATE TABLE `mechanic` (
  `mechanic_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`mechanic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `mechanic` */

insert  into `mechanic`(`mechanic_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`,`image`) values (13,63,'sudakaran','menon','kakkanad','6543987654','sudakmen@gmail.com','9.9487187','76.6313654','static/imagec4d78784-c2ef-4ac6-a535-bf7eb01ec6daabc.jpg'),(8,53,'Justin','mathew','kaloor','8963670823','justee@gmail.com','10.1320858','76.501836','static/imageb926834d-a368-4739-8d1d-7ecdf09e41aaabc.jpg'),(9,54,'firoz','khan','perumbavoor','7839226790','firoz@gmail.com','10.1320858','76.5018359','static/image0e3483de-b66f-4b67-9b0c-804b7607fc96abc.jpg'),(10,55,'Soyal','Sunny','nellikuzhy','8976790052','soyalsunny@gmail.com','10.1320859','76.5018357','static/image764dc0ce-eb90-4441-a0d7-3192ecab0d49abc.jpg'),(11,56,'harry','styles','ernakulam','8973412654','harry@gmail.com','10.1320856','76.5018364','static/imagee989f439-9d20-4ef5-be15-91e82936a7f7abc.jpg'),(12,57,'Maya','Shivadas','Munnar','7890634217','maya@gmail.com','10.1320856','76.5018364','static/imagebac922fc-0361-4123-b727-b32774ca3e19abc.jpg');

/*Table structure for table `mechanicrequest` */

DROP TABLE IF EXISTS `mechanicrequest`;

CREATE TABLE `mechanicrequest` (
  `mrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `mechanic_id` int(11) DEFAULT NULL,
  `servicceamount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`mrequest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `mechanicrequest` */

insert  into `mechanicrequest`(`mrequest_id`,`user_id`,`mechanic_id`,`servicceamount`,`date`,`status`,`details`) values (12,8,13,'0','2023-06-19 21:38:57','Requested','needed wheel change'),(11,8,10,'3000','2023-06-18 00:51:00','Amountaddred','Needed routine checkup,last serviced on 2/5/2023'),(10,8,9,'0','2023-06-18 00:28:51','Requested','help me out');

/*Table structure for table `oderdetails` */

DROP TABLE IF EXISTS `oderdetails`;

CREATE TABLE `oderdetails` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `oderdetails` */

insert  into `oderdetails`(`detail_id`,`order_id`,`product_id`,`quantity`,`amount`,`date`) values (15,10,9,'2','9000','2023-06-18 16:28:40'),(14,10,8,'1','2800','2023-06-18 16:28:21'),(13,10,5,'7','234500','2023-06-18 09:46:41'),(12,10,11,'3','1800','2023-06-18 09:45:27'),(11,9,13,'2','52000','2023-06-18 09:43:29'),(10,8,14,'1','1600','2023-06-18 09:40:43'),(9,8,15,'3','11400','2023-06-18 09:40:18'),(16,10,15,'4','15200','2023-06-18 16:30:44'),(17,10,13,'2','52000','2023-06-18 16:33:26');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `sparepart_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `order` */

insert  into `order`(`order_id`,`sparepart_id`,`user_id`,`total`,`status`) values (10,7,8,'315300','Delivery'),(9,6,8,'52000','PickUp'),(8,5,8,'13000','PickUp');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `requested_id` int(11) DEFAULT NULL,
  `requestedfor` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`requested_id`,`requestedfor`,`amount`,`date`,`user_id`) values (21,11,'Vehicle request','7300000','2023-06-19 21:35:08',32),(20,10,'Product booking','315300','2023-06-18 16:47:17',32),(19,9,'Product booking','52000','2023-06-18 09:44:21',32),(18,9,'Product booking','52000','2023-06-18 09:44:02',32),(17,8,'Product booking','13000','2023-06-18 09:42:16',32),(16,8,'Product booking','13000','2023-06-18 09:41:42',32),(15,6,'Vehicle request','130000','2023-06-18 00:50:11',32);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `sparepart_id` int(11) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `stock` varchar(100) DEFAULT NULL,
  `rate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`sparepart_id`,`product_name`,`img`,`stock`,`rate`) values (7,8,'Motor Spare','static/uploads/df88ae54-9651-4d93-ab46-4796630c488dabc.jpg','10','5197'),(6,8,'Battery Charger','static/uploads/13be9199-9646-4e39-b577-7bb8f1eace4fabc.jpg','20','1950'),(5,8,'Battery Pack 60V','static/uploads/77b3096a-6a4b-4e09-812e-da63d9c6a759abc.jpg','6','33500'),(8,7,'EV conversion kit','static/uploads/9cc4c66d-5a6e-4c45-9a9b-f7ab545caf86abc.jpg','20','2800'),(9,7,'Battery Pack','static/uploads/8de38101-33ef-4d7c-9332-69cac8bfc5f4abc.jpg','24V','4500'),(10,7,'Battery Charger','static/uploads/5b5bfee3-7639-4ead-b921-2107b9b15039abc.jpg','20','1950'),(11,7,'Riding gloves','static/uploads/0c54c80e-2e42-46a2-957f-11227fdba2d6abc.jpg','200','600'),(12,7,'Rain Suit','static/uploads/6b2e261e-a1de-42d8-bc2a-cc96d9c63294abc.jpg','200','1000'),(13,6,'48V battery pack','static/uploads/4e4bd755-ddf8-434d-906f-aeff7756d2d6abc.jpg','20','26000'),(14,6,'24 V battery charger','static/uploads/1c1506c0-e06b-4448-aa9a-f7eb2ae08630abc.jpg','25','1600'),(15,5,'48V battery charger','static/uploads/5338f48c-5e38-458c-9001-bdf2f46bd4cdabc.jpg','30','3800'),(16,5,'Bike body cover','static/uploads/fe76b758-7c30-470f-80d7-7c0873b6c02cabc.jpg','30','350');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `requested_id` int(11) DEFAULT NULL,
  `rating` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`requested_id`,`rating`,`date`,`type`) values (6,11,10,'4.0','2023-06-18 01:05:13','bunk'),(5,11,11,'4.0','2023-06-18 01:05:01','bunk'),(4,8,10,'5.0','2023-06-18 00:40:07','mechanic');

/*Table structure for table `rechargerequest` */

DROP TABLE IF EXISTS `rechargerequest`;

CREATE TABLE `rechargerequest` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `bunk_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `rechargerequest` */

insert  into `rechargerequest`(`request_id`,`user_id`,`bunk_id`,`amount`,`date`,`status`) values (10,11,7,'0','2023-06-18 01:04:45','Requested'),(9,8,8,'0','2023-06-18 00:50:25','Requested'),(8,8,6,'0','2023-06-18 00:50:22','Requested'),(7,8,3,'0','2023-06-18 00:50:19','Requested'),(6,8,7,'0','2023-06-18 00:27:05','Requested'),(11,11,5,'0','2023-06-18 01:04:47','Requested'),(12,12,8,'0','2023-06-18 15:36:44','Requested'),(13,12,6,'0','2023-06-18 15:37:29','Requested'),(14,8,3,'0','2023-06-19 21:38:29','Requested');

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `request` */

insert  into `request`(`request_id`,`vehicle_id`,`user_id`,`amount`,`date`,`status`) values (9,7,8,'1200000','2023-06-19 21:24:05','Pending'),(8,7,8,'1200000','2023-06-18 17:01:04','Pending'),(7,6,11,'800000','2023-06-18 01:04:40','Pending'),(6,14,8,'130000','2023-06-18 00:48:08','deliver'),(5,9,8,'6000000','2023-06-18 00:23:51','Pending'),(10,11,8,'7300000','2023-06-19 21:30:35','Pending'),(11,11,8,'7300000','2023-06-19 21:31:22','Paid');

/*Table structure for table `sparepart` */

DROP TABLE IF EXISTS `sparepart`;

CREATE TABLE `sparepart` (
  `sparepart_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`sparepart_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `sparepart` */

insert  into `sparepart`(`sparepart_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`,`image`) values (5,40,'Hazel','george','odakally','8937548000','hazee@gmail.com','10.132099','76.501845','static/imagedae35292-b28c-476d-8f87-549fd5cc83afabc.jpg'),(6,58,'Uma','nambootiri','chertala','9836916580','uma@gmail.com','10.1320852','76.5018365','static/image2b666a09-edde-4abe-9028-b40ceb4f9ddaabc.jpg'),(7,59,'parvaty','prakash','piravam','9023671234','parvaty@gmail.com','10.1320859','76.5018362','static/image3b529234-78e3-49b4-bd0a-09a5c16a95c2abc.jpg'),(8,60,'Kevin','Lal','Kayamkulam','9458223450','kevin@gmail.com','10.1320859','76.5018362','static/imagea60d0b9a-1a12-49f3-9143-8dd8325030ebabc.jpg'),(9,61,'Mamita','babu','mavelikara','6729371103','mamita@gmail.com','10.1320859','76.5018363','static/imagef2698abf-bf0a-436d-8a99-2a1c157dfba6abc.jpg'),(10,62,'Taimur','Ali','Aluva','7292071209','taimur@gmail.com','10.1320859','76.5018365','static/image89bd37f8-cb71-40b8-969f-0dd0c1760c6eabc.jpg');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `vehicle` varchar(100) DEFAULT NULL,
  `detials` varchar(100) DEFAULT NULL,
  `Dateofpurchase` varchar(100) DEFAULT NULL,
  `servicedate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`first_name`,`last_name`,`place`,`phone`,`email`,`latitude`,`longitude`,`vehicle`,`detials`,`Dateofpurchase`,`servicedate`) values (8,32,'Robert','Thomas','perumbavoor','9472616370','robert@gmail.com','10.1320997','76.5018449','tiago','blue ',NULL,NULL),(9,33,'Ameera','Husain','Muvattupuzha','6798346254','ameera@gmail.com','10.1320996','76.5018443','kia EV','black',NULL,NULL),(10,34,'siddi','menon','aluva','7845627990','siddi@gmail.com','10.132076666666668','76.50172166666667','MG comet','ash',NULL,NULL),(11,36,'gautham','manoharan','pullinchodu','6892690913','gautham12@gmail.com','10.1321001','76.5018446','tata nexon','white',NULL,NULL),(12,38,'Muhammed','asif','kothamangalam','8946123470','asif00@gmail.com','10.1321011','76.5018444','TVS iQube','Na',NULL,NULL);

/*Table structure for table `vehicles` */

DROP TABLE IF EXISTS `vehicles`;

CREATE TABLE `vehicles` (
  `vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL,
  `vehicle` varchar(100) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `Dateofpurchase` varchar(100) DEFAULT NULL,
  `servicedate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `vehicles` */

insert  into `vehicles`(`vehicle_id`,`company_id`,`vehicle`,`desc`,`amount`,`image`,`Dateofpurchase`,`servicedate`) values (7,4,'Tigor','Range 315km,battery capacity 26kWh,charging time 7.5h','1200000','static/image3bd80297-608e-4cdc-949a-ee49b9c20b7etigor.jpeg','2023-06-08','2023-06-10'),(6,4,'Tiago','Range 250km to 315 km,4 star global safety,battery capacity 19.2 to 24kWh','800000','static/image6ab02221-6441-4895-a6bd-28ebff87bc1btiago.jpeg','2023-06-17','2023-06-21'),(8,4,'Nexon','range 312km,charging time 60min,capacity 30.2kWh','1400000','static/image43892368-6536-44ff-ab93-2edb4f715defnexon.jpeg','2023-06-01','2023-06-04'),(9,5,'EV6','Range 708km,charging time 18min,capacity 77.4kWh','6000000','static/image9c427c03-c6eb-4743-8371-6b3c556cf5c6ev6.jpeg','2023-05-06','2023-05-27'),(10,6,'MG COMET','battery capacity 17.3kWh,Range 230km,charging time 7 hours','800000','static/image7ec4bb17-7ac1-4554-9851-acbd8db3f287comet.jpeg','2023-04-15','2023-06-30'),(11,6,'BMW i4','Range 590km,battery capacity 83.9kW,transmission ','7300000','static/imaged3a8a494-29f3-4987-ae9e-121095369196i4.jpeg','2023-05-06','2023-06-07'),(12,6,'OLA s1','Range 128km,battery capacity 3kWh,max speed 95kmph','130000','static/image5428e3a6-ed13-4b0f-8a75-7d37f17476f7olas1.jpeg','2023-04-28','2023-05-27'),(13,6,'iQube Electric','Range 100km,speed 78km/h,powered by 2 Li-ion batteries','100000','static/imagec2177c9e-1772-4d7b-898b-d0c99e29ed33TVS-iQube-Electric-Scooter.jpg','2023-05-13','2023-05-06'),(14,6,'Ather 450X','charging time 4hr30min,range 146km,battery capacity 3.7kWh','130000','static/image5f24886b-3330-4395-b483-3ddd87fcb573Ather-450X-170120221250.png','2023-05-13','2023-05-16'),(15,6,'Chetak','Range 90km,charging time 5hrs,power 3800W,with USB charging port','140000','static/image1e069082-4b03-4a37-b36d-3a3f9b6e4b9achetak.jpg','2023-05-13','2023-05-26'),(16,6,'Simple One','charging time 6hrs,range 212 km,capacity 5kWh','145000','static/image8938d1da-9e9e-4751-bc6a-bfb8ad1c1a51so.jpeg','2023-04-14','2023-05-16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
