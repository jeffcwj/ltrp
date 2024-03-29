
CREATE TABLE players
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(24) DEFAULT 'Kas' NOT NULL,
  password VARCHAR(128) DEFAULT 'klaida' NOT NULL,
  secret_question VARCHAR(128) NOT NULL,
  secret_answer VARCHAR(128) NOT NULL,
  level INT DEFAULT 1 NOT NULL,
  admin_level INT DEFAULT 0 NOT NULL,
  mod_level TINYINT DEFAULT 0 NOT NULL,
  hours_online INT DEFAULT 0 NOT NULL,
  minutes_online_since_payday TINYINT UNSIGNED NOT NULL ,
  box_style TINYINT UNSIGNED DEFAULT 4 NOT NULL,
  sex VARCHAR(11) DEFAULT 'Vyras' NOT NULL,
  age INT DEFAULT 0 NOT NULL,
  origin VARCHAR(24) DEFAULT 'Nenustatyta' NOT NULL,
  disease INT DEFAULT 0 NOT NULL,
  respect INT DEFAULT 0 NOT NULL,
  money INT DEFAULT 320 NOT NULL,
  deaths INT DEFAULT 0 NOT NULL,
  wanted_level INT DEFAULT 0 NOT NULL,
  #job_id INT NULL,
  #job_rank INT NULL,
  leader INT DEFAULT 0 NOT NULL,
  member INT DEFAULT 0 NOT NULL,
  pJobCar INT DEFAULT 0 NOT NULL,
  rank INT DEFAULT 0 NOT NULL,
  skin INT DEFAULT 212 NOT NULL,
  #job_contract TINYINT DEFAULT 0 NOT NULL,
  phonenumber INT DEFAULT 0 NOT NULL,
  House INT DEFAULT 0 NOT NULL,
  CarLic INT DEFAULT 0 NOT NULL,
  FlyLic INT DEFAULT 0 NOT NULL,
  BoatLic INT DEFAULT 0 NOT NULL,
  MotoLic INT DEFAULT 0 NOT NULL,
  GunLic INT DEFAULT 0 NOT NULL,
  warnings INT DEFAULT 0 NOT NULL,
  virtual_world INT DEFAULT 0 NOT NULL,
  locked INT DEFAULT 0 NOT NULL,
  ucpuser INT DEFAULT 0 NOT NULL,
  inventory VARCHAR(80) DEFAULT '0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/0/' NOT NULL,
  weapons VARCHAR(56) DEFAULT '0/0/0/0/0/0/0/0/' NOT NULL,
  pDubKey INT DEFAULT 0 NOT NULL,
  LeftTime INT DEFAULT 0 NOT NULL,
  DriverWarn INT DEFAULT 0 NOT NULL,
  pFines INT DEFAULT 0 NOT NULL,
  pPFines INT DEFAULT 0 NOT NULL,
  pDutyT INT DEFAULT 0 NOT NULL,
  Donator INT DEFAULT 0 NOT NULL,
  Accepted VARCHAR(255) NOT NULL,
  WalkStyle INT NOT NULL,
  TalkStyle INT NOT NULL,
  TelNrKeite INT DEFAULT 0 NOT NULL,
  MasinosNrKeite INT DEFAULT 0 NOT NULL,
  DuziusKeite INT DEFAULT 0 NOT NULL,
  VardaKeite INT DEFAULT 0 NOT NULL,
  SenasVardas VARCHAR(255) DEFAULT 'Nera' NOT NULL,
  HeroineAddict SMALLINT DEFAULT 0 NOT NULL,
  AmfaAddict SMALLINT DEFAULT 0 NOT NULL,
  MetamfaAddict SMALLINT DEFAULT 0 NOT NULL,
  CocaineAddict SMALLINT DEFAULT 0 NOT NULL,
  playerIP VARCHAR(32) DEFAULT '127.0.0.1' NOT NULL,
  playerLastLogOn TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  spawn_type SMALLINT NOT NULL DEFAULT '0',
  spawn_ui INT NOT NULL DEFAULT '0',
  Card VARCHAR(256) DEFAULT 'Tuščia' NOT NULL,
  forum_name VARCHAR(256) DEFAULT 'Neivesta' NOT NULL,
  aDutyTime INT DEFAULT 0 NOT NULL,
  DonatorDate TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
  HaveNameChange INT DEFAULT 0 NOT NULL,
  HavePhNrChange INT DEFAULT 0 NOT NULL,
  HaveVehPlatesChange INT DEFAULT 0 NOT NULL,
  HaveVehDestr INT DEFAULT 0 NOT NULL,
  ExtazyAddict SMALLINT DEFAULT 0 NOT NULL,
  PCPAddict SMALLINT DEFAULT 0 NOT NULL,
  CrackAddict SMALLINT DEFAULT 0 NOT NULL,
  OpiumAddict SMALLINT DEFAULT 0 NOT NULL,
  Points SMALLINT DEFAULT 0 NOT NULL,
  HealthLevel SMALLINT DEFAULT 0 NOT NULL,
  StrengthLevel SMALLINT DEFAULT 0 NOT NULL,
  ISP VARCHAR(128) DEFAULT '' NOT NULL,
  Hunger TINYINT UNSIGNED DEFAULT 0 NOT NULL,
  total_paycheck INT UNSIGNED NOT NULL ,
  PRIMARY KEY (id)
  #FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE SET NULL,
  #FOREIGN KEY (job_rank) REFERENCES job_ranks(id) ON DELETE SET NULL
)  ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS player_job_levels
(
  id INT AUTO_INCREMENT NOT NULL,
  player_id INT NOT NULL,
  job_id INT NOT NULL,
  `level` INT NOT NULL DEFAULT '1',
  hours INT NOT NULL DEFAULT '0',
  xp INT NOT NULL DEFAULT '0',
  PRIMARY KEY(id),
  INDEX(player_id),
  INDEX(hours),
  INDEX(job_id),
  FOREIGN KEY(job_id) REFERENCES jobs(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE IF NOT EXISTS player_jailtime
(
  id INT AUTO_INCREMENT NOT NULL,
  player_id INT NOT NULL,
  remaining_time INT NOT NULL,
  type VARCHAR(16) NOT NULL,
  jailer_name VARCHAR(24) NOT NUlL,
  jail_date DATETIME NOT NULL,
  PRIMAry KEY(id),
  INDEX(player_id)
) ENGINE=INNODB DEFAULT COLLATE=utf8_unicode_ci CHARSET=utf8;

ALTER TABLE player_jailtime ADD FOREIGN KEY(player_id) REFERENCES players(id) ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS player_crashes
(
  player_id INT(11) NOT NULL,
  timestamp DATETIME NOT NULL,
  pos_x FLOAT NOT NULL,
  pos_y FLOAT NOT NULL,
  pos_z FLOAT NOT NULL,
  interior INT NOT NULL,
  virtual_world INT NOT NULL,
  PRIMARY KEY(player_id)
);
ALTER TABLE player_crashes ADD FOREIGN KEY(player_id) REFERENCES players(id) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS player_settings (
  player_id INT NOT NULL,
  setting VARCHAR(16) NOT NULL,
  value VARCHAR(64) NOT NULL,
  PRIMARY KEY (player_id, setting),
  FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `ltrp-java`.logs_admin
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  admin_name VARCHAR(24) NOT NULL,
  user_id INT NULL,
  date DATETIME NOT NULL,
  action VARCHAR(255) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS logs_admin_duty
(
  id INT NOT NULL AUTO_INCREMENT,
  admin_name VARCHAR(24) NOT NULL,
  user_id INT NULL,
  first_watch DATETIME NULL,
  last_watch DATETIME NULL,
  longest_watch INT NOT NULL DEFAULT 0,
  total_watch_time INT NOT NULL DEFAULT 0,
  reports_accepted INT NOT NULL DEFAULT 0,
  reports_rejected INT NOT NULL DEFAULT 0,
  PRIMARY KEY(id),
  FOREIGN KEY (user_id) REFERENCES players (id) ON DELETE SET NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS logs_player (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(24) NOT NULL,
  user_id INT NUlL,
  date DATETIME NOT NULL,
  type VARCHAR(64) NOT NULL,
  action VARCHAR(255) NOT NULL ,
  PRIMARY KEY(id),
  INDEX(user_id),
  FOREIGN KEY (user_id) REFERENCES players(id) ON DELETE SET NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS logs_player_event (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(24) NOT NULL,
  user_id INT NUlL,
  date DATETIME NOT NULL,
  event_name VARCHAR(64) NOT NULL,
  event_data VARCHAR(255) NOT NULL ,
  PRIMARY KEY(id),
  INDEX(user_id),
  FOREIGN KEY (user_id) REFERENCES players(id) ON DELETE SET NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS phone_sms (
  id INT AUTO_INCREMENT NOT NULL,
  sender_number INT NOT NULL,
  recipient_number INT NOT NULL,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `text` VARCHAR(128) NOT NULL,
  `read` BOOLEAN NOT NULL DEFAULT '0',
  PRIMARY KEY(id),
  INDEX(sender_number),
  INDEX(recipient_number)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS phone_contacts (
  id INT AUTO_INCREMENT NOT NULL,
  number int(11) NOT NULL,
  contact_number int(11) NOT NULL,
  name varchar(24) NOT NULL,
  entry_date TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  INDEX(number),
  INDEX(contact_number)
) ENGINE=InnoDB DEFAULT CHARSET=cp1257;

 -- The main parent big super important table
CREATE TABLE IF NOT EXISTS items
(
  id INT AUTO_INCREMENT NOT NULL,
  type INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  type_class VARCHAR(64) NOT NULL,
  stackable BOOLEAN NOT NULL,
  amount INT NOT NULL,
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_items (
  item_id INT NOT NULL,
  player_id INT NOT NULL,
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
  FOREIGN KEY (player_id) REFERENCES players(id)  ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS vehicle_items (
  item_id INT NOT NULL,
  vehicle_id INT NOT NULL,
  FOREIGN KEY (item_id) REFERENCES items(id) ) ON DELETE CASCADE,
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS property_items (
  item_id INT NOT NULL,
  property_id INT NOT NULL,
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
  FOREIGN KEY (property_id) REFERENCES properties(id)) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE IF NOT EXISTS items_durable
(
  item_id INT NOT NULL,
  durability INT NOT NULL DEFAULT '0',
  max_durability INT NOT NULL DEFAULT '0',
  PRIMARY KEY(item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS items_container
(
  item_id INT NOT NULL,
  items INT NOT NULL DEFAULT '0',
  size INT NOT NULL DEFAULT '0',
  PRIMARY KEY(item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS items_clothing
(
  item_id INT NOT NULL,
  model SMALLINT NOT NULL DEFAULT  '0',
  bone TINYINT UNSIGNED NOT NULL DEFAULT  '0',
  worn BOOLEAN NOT NULL DEFAULT '0',
  PRIMARY KEY (item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS items_weapon
(
  item_id INT NOT NULL,
  weapon_id TINYINT NOT NULL DEFAULT '0',
  ammo SMALLINT UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS items_consumable
(
  item_id INT NOT NULL,
  doses INT NOT NULL DEFAULT '0',
  PRIMARY KEY (item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS items_drink
(
  item_id INT NOT NULL,
  special_action TINYINT UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS items_phone
(
  item_id INT NOT NULL,
  number INT NOT NULL DEFAULT '86000000',
  PRIMARY KEY (item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS items_radio
(
  item_id INT NOT NULL,
  frequency FLOAT NOT NULL DEFAULT '0.0',
  PRIMARY KEY (item_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_crimes
(
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(24) NOT NULL,
  crime VARCHAR(128) NOT NULL,
  reporter VARCHAR(24) NOT NULL,
  date DATETIME NOT NULL,
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_licenses
(
  id INT AUTO_INCREMENT NOT NULL,
  player_id INT NOT NULL,
  type VARCHAR(24) NOT NULL,
  stage TINYINT UNSIGNED NOT NULL,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(player_id) REFERENCES players(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_license_warnings
(
  id INT AUTO_INCREMENT NOT NULL,
  license_id INT NOT NULL,
  warning VARCHAR(512) NOT NULL,
  issued_by VARCHAR(24) NOT NULL,
  created_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(license_id) REFERENCES player_licenses(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_addictions (
  player_id INT NOT NULL,
  drug VARCHAR(64) NOT NULL,
  level TINYINT UNSIGNED NOT NULL,
  last_dose TIMESTAMP NOT NULL ,
  PRIMARY KEY (player_id, drug),
  FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS dmv (
  id INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  type VARCHAR(24) NOT NULL,
  x FLOAT NOT NULL,
  y FLOAT NOT NULL,
  z FLOAT NOT NULL,
  interior INT NOT NULL,
  virtual_world INT NOT NULL,
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS dmv_checkpoint
(
  id INT AUTO_INCREMENT NOT NULL,
  dmv_id INT NOT NULL,
  x FLOAT NOT NULL,
  y FLOAT NOT NULL,
  z FLOAT NOT NULL,
  radius FLOAT NOT NULL,
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS dmv_vehicle
(
  id INT AUTO_INCREMENT NOT NULL,
  dmv_id INT NOT NULL,
  modelid SMALLINT UNSIGNED,
  x FLOAT NOT NULL,
  y FLOAT NOT NULL,
  z FLOAT NOT NULL,
  angle FLOAT NOT NULL,
  color1 TINYINT UNSIGNED NOT NULL ,
  color2 TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS dmv_question
(
  id INT AUTO_INCREMENT NOT NULL,
  dmv_id INT NOT NULL,
  question VARCHAR(1024) NOT NULL,
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS dmv_question_answer
(
  id INT AUTO_INCREMENT NOT NULL,
  question_id INT NOT NULL,
  answer VARCHAR(512) NOT NULL,
  correct BOOLEAN NOT NULL,
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS player_vehicle_permissions
(
  id INT AUTO_INCREMENT NOT NULL,
  player_id INT NOT NULL,
  vehicle_id INT NOT NULL,
  permission VARCHAR(64) NOT NULL,
  PRIMARY KEY  (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_vehicles
(
  id INT NOT NULL,
  owner_id INT NOT NULL,
  deaths INT NOT NULL,
  alarm VARCHAR(30) NULL,
  lock_name VARCHAR(100) NULL,
  lock_cracktime INT NULL,
  lock_price INT NULL,
  insurance TINYINT NOT NULL,
  doors INT NOT NULL,
  panels INT NOT NULL,
  lights INT NOT NULL,
  tires INT NOT NULL,
  health FLOAT NOT NULL DEFAULT 1000,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES vehicles(id) ON DELETE CASCADE,
  FOREIGN KEY (owner_id) REFERENCES players(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_vehicle_arrests (
  id INT AUTO_INCREMENT NOT NULL ,
  vehicle_id INT NOT NULL,
  arrested_by INT NOT NULL,
  reason VARCHAR(128) NOT NULL ,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  deleted_at TIMESTAMP NULL,
  PRIMARY KEY (id),
  INDEX (vehicle_id),
  INDEX(arrested_by),
  FOREIGN KEY (vehicle_id) REFERENCES player_vehicles(id) ON DELETE CASCADE ,
  FOREIGN KEY (arrested_by) REFERENCES players(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS player_vehicle_crimes
(
  id INT AUTO_INCREMENT NOT NULL,
  license_plate VARCHAR(24) NOT NULL,
  crime VARCHAR(128) NOT NULL,
  reporter VARCHAR(24) NOT NULL,
  date DATETIME NOT NULL,
  fine INT UNSIGNED NOT NULL,
  PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS radio_stations (
  id int auto_increment not null,
  name varchar(64) not null,
  url varchar(255) not null,
  primary key(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS vehicles (
  id INT AUTO_INCREMENT NOT NULL ,
  model SMALLINT UNSIGNED NOT NULL ,
  x FLOAT NOT NULL ,
  y FLOAT NOT NULL ,
  z FLOAT NOT NULL ,
  angle FLOAT NOT NULL,
  license VARCHAR(16) NOT NULL DEFAULT 'AAA-000',
  interior tinyint unsigned not null default '0',
  virtual_world smallint unsigned not null default '0',
  color1 TINYINT UNSIGNED NOT NULL ,
  color2 TINYINT UNSIGNED NOT NULL ,
  mileage FLOAT NOT NULL ,
  fuel FLOAT NOT NULL ,
  PRIMARY KEY (id),
  INDEX (model)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS jobs (
  id int auto_increment not null,
  name varchar(255) not null,
  x float not null,
  y float not null,
  z float not null,
  interior tinyint unsigned not null default '0',
  virtual_world smallint unsigned not null default '0',
  paycheck int not null default '0',
  primary key(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create TABLE IF NOT EXISTS job_properties (
  job_id int not null,
  `key` text not null,
  value text not null,
  primary KEY (job_id, `key`),
  FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS job_leaders (
  job_id INT NOT NULL ,
  player_id INT NOT NULL ,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (job_id, player_id),
  FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE ,
  FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS job_ranks (
  id INT AUTO_INCREMENT NOT NULL,
  job_id INT NOT NULL ,
  name VARCHAR(65) NOT NULL ,
  salary INT UNSIGNED NOT NULL ,
  PRIMARY KEY (id, job_id),
  FOREIGN KeY(job_id) REFERENCES jobs(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS job_ranks_contract (
  id int NOT NULL ,
  job_id INT NOT NULL,
  xp_needed SMALLINT UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (id, job_id),
  FOREIGN KEY (id, job_id) REFERENCES job_ranks(id, job_id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS job_vehicles (
  id INT NOT NULL,
  job_id INT NOT NULL,
  rank_id INT NOT NULL ,
  PRIMARY KEY(id),
  FOREIGN KEY (id) REFERENCES vehicles(id) ON DELETE CASCADE ,
  FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE ,
  FOREIGN KEY (rank_id) REFERENCES job_ranks(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS garbageman_missions (
  id INT AUTO_INCREMENT NOT NULL ,
  name VARCHAR(64) NOT NULL ,
  PRIMARY KEY (id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS garbageman_mission_garbage (
  mission_id INT NOT NULL ,
  x FLOAT NOT NULL ,
  y FLOAT NOT NULL ,
  z FLOAT NOT NULL ,
  PRIMARY KEY (mission_id),
  FOREIGN KEY (mission_id) REFERENCES garbageman_missions(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS properties (
  id INT AUTO_INCREMENT NOT NULL,
  owner INT NULL,
  name VARCHAR(100) NOT NULL,
  price INT NOT NULL,
  entrance_x REAL NOT NULL,
  entrance_y REAL NOT NULL,
  entrance_z REAL NOT NULL,
  entrance_interior INT NOT NULL,
  entrance_virtual INT NOT NULL,
  exit_x REAL NULL,
  exit_y REAL NULL,
  exit_z REAL NULL,
  exit_interior INT NULL,
  exit_virtual INT NULL,
  locked TINYINT NOT NULL,
  label_color INT NOT NULL,
  pickup_model SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (owner) REFERENCES players(id) ON DELETE SET NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS houses (
  id INT NOT NULL,
  money INT NOT NULL,
  rent_price INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES properties(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE house_weed
(
  id INT NOT NULL AUTO_INCREMENT,
  house_id INT NOT NULL,
  x REAL NOT NULL,
  y REAL NOT NULL,
  z REAL NOT NULL,
  plant_timestamp INT NOT NULL,
  planted_by INT NOT NULL,
  growth_timestamp INT DEFAULT 0 NOT NULL,
  growth_stage TINYINT UNSIGNED DEFAULT 0 NOT NULL,
  harvested_by INT NULL,
  yield TINYINT UNSIGNED NULL,
  PRIMARY KEY(id),
  INDEX(planted_by),
  INDEX(harvested_by),
  FOREIGN KEY (house_id) REFERENCES houses(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS house_upgrades (
  house_id INT NOT NULL,
  upgrade_id TINYINT NOT NULL,
  PRIMARY KEY (house_id, upgrade_id),
  FOREIGN KEY (house_id) REFERENCES houses(id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS garages (
  id INT NOT NULL,
  vehicle_entrance_x FLOAT NOT NULL,
  vehicle_entrance_y FLOAT NOT NULL,
  vehicle_entrance_z FLOAT NOT NULL,
  vehicle_entrance_interior INT NOT NULL,
  vehicle_entrance_virtual INT NOT NULL,
  vehicle_entrance_angle FLOAT NOT NULL,
  vehicle_exit_x FLOAT NULL,
  vehicle_exit_y FLOAT NULL,
  vehicle_exit_z FLOAT NULL,
  vehicle_exit_interior INT NULL,
  vehicle_exit_virtual INT NULL,
  vehicle_exit_angle FLOAT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES properties(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




CREATE TABLE businesses_available_commodities
(
  id INT AUTO_INCREMENT NOT NULL,
  business_type INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  item_type INT NULL,
  health_addition FLOAT NULL,
  special_action TINYINT UNSIGNED NULL,
  drunk_level_per_sip INT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS businesses
(
  id INT NOT NULL,
  entrance_price INT NOT NULL,
  money INT NOT NULL,
  type SMALLINT NOT NULL,
  resources INT NOT NULL,
  commodity_limit SMALLINT UNSIGNED NOT NULL,
  resources_price INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES properties(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS businesses_commodities
(
  business_id INT NOT NULL,
  commodity_id INT NOT NULL,
  no INT NOT NULL,
  price INT NOT NULL,
  PRIMARY KEY (business_id, no),
  FOREIGN KEY (business_id) REFERENCES businesses(id) ON DELETE CASCADE,
  FOREIGN KEY (commodity_id) REFERENCES businesses_available_commodities(id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
