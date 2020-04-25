--createUser
insert into USER_ACCOUNT values(88,'du yifan','duyfian123',3,to_date('29-AUG-04','dd-mon-yy','NLS_DATE_LANGUAGE=AMERICAN'));
insert into USER_ACCOUNT values(89,'he yifan','heyifan123' ,3,to_date('29-AUG-04','dd-mon-yy','NLS_DATE_LANGUAGE=AMERICAN'));

--dropUser
delete from USER_ACCOUNT where user_id=88;

--createEvent
insert into EVENT values(?,1,1,2,to_date('10-JAN-20','dd-Mon-yy','NLS_DATE_LANGUAGE=AMERICAN'));

--addEventOutcome
insert into SCOREBOARD values(1,1,1,13,2,2);

--createTeam
insert into TEAM values(145,1,'duyifan',1,1,76);

--registerTeam
insert into EVENT_PARTICIPATION values(1,100,'e');

--addParticipant
insert into PARTICIPANT values(88,'du','yifan','China','Hefei',to_date('16-JUL-97','dd-Mon-yy','NLS_DATE_LANGUAGE=AMERICAN'));
insert into PARTICIPANT values(89,'he','yifan','Russia','Wuhu',to_date('16-JUL-97','dd-Mon-yy','NLS_DATE_LANGUAGE=AMERICAN'));

--addTeamMember
insert into TEAM_MEMBER values(1,89);

--dropTeamMember
delete from USER_ACCOUNT where user_id=89 and role_id=3;

--displaySport
select distinct esp.participant_id ,st.dob,esp.event_id,esp.gender ,esp.nationality
from SPORT st join
(
select e.event_id,e.sport_id,e.gender,sp.participant_id,sp.nationality,sp.medal_id,e.event_time
from EVENT e join(
select s.event_id,s.participant_id,p.nationality,s.medal_id
from  SCOREBOARD s join PARTICIPANT p on s.PARTICIPANT_ID = p.PARTICIPANT_ID
where medal_id is not null
order by medal_id asc) sp on e.event_id= sp.event_id order by event_time asc) esp on st.sport_id=esp.sport_id
where st.sport_name='3m men diving';

--displayEvent
select s.participant_id,s.position,s.MEDAL_ID
from SCOREBOARD s join (
select OLYMPIC_ID from OLYMPICS where host_city='Beijing') o on o.OLYMPIC_ID=s.OLYMPIC_ID
where event_id=6 and medal_id is not null;
select * from SCOREBOARD;

--countryRanking
select smeco.country_code, sum(smeco.points) as points,min(smeco.opening) as opening,
(select count(country_code) from ( select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 1 and smeco1.Olympic_id=1)gold ,
(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 2 and smeco1.Olympic_id=1)silver,
(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 3 and smeco1.Olympic_id=1)bronze
from(select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join
(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID
from COUNTRY c join
    (select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points
from TEAM e join
(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco
where smeco.Olympic_id=1
group by smeco.country_code
order by points desc;

--topkAthletes
select sm.participant_id,sum(sm.points) as points,
(select count(participant_id) from ( select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 1)gold ,
(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 2)silver,
(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 3)bronze
from  ( select * from SCOREBOARD natural join MEDAL) sm
where OLYMPIC_ID=1
group by sm.participant_id
order by points desc
fetch first 10 rows only;

--connectedAthletes
select distinct po1.participant_id from
(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id not in
(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id != 44 and s.event_id in
(select event_id from SCOREBOARD s where participant_id = 44 and olympic_id = 2))and s.event_id in
(select event_id from SCOREBOARD s where participant_id in
(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id != 44 and s.event_id in
(select event_id from SCOREBOARD s where participant_id = 44 and olympic_id = 2)) and olympic_id = 1))po1 cross join
(select participant_id from SCOREBOARD where participant_id = 44 and olympic_id = 2)po2;

--logout
update USER_ACCOUNT set last_login=to_date('29-AUG-20','dd-mon-yy','NLS_DATE_LANGUAGE=AMERICAN') where user_id=5;

commit;