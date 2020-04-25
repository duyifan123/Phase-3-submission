package phase2;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Driver {
	private static final String username = "yid33";
	 private static final String password = "4377744";
	 private static final String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
	 private static Connection connection=null;//
	 private static PreparedStatement ps=null;
	 private static Statement st=null;
	 private static Scanner j = new Scanner(System.in);
	 public static Connection getConnection() {
			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				connection=(Connection) DriverManager.getConnection(url, username, password);
			
			} catch (Exception e) {
				e.printStackTrace();//
			}
			return connection;
		}

	 public static void createUser(){     //////OK
		 
		 try {
		     st = connection.createStatement();
			 String query1="select * from USER_ACCOUNT";
			 ResultSet rst1 = st.executeQuery(query1);
			 String username1,passkey1;
			 java.sql.Date last_login1;
			 int user_id1,role_id1;
			 while (rst1.next()) {
				  user_id1= rst1.getInt("user_id");
				  username1 = rst1.getString("username");
				  passkey1= rst1.getString("passkey");
				  role_id1=rst1.getInt("role_id");
				  last_login1=rst1.getDate("last_login");
		          System.out.println(user_id1+" "+username1+" "+passkey1+" "+role_id1+" "+last_login1);
		        }
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//set the initial format of time stamp
	 		 java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime());  
			 String query2="insert into USER_ACCOUNT values(88,'du yifan','duyfian123',3,?)";
			 String query3="insert into USER_ACCOUNT values(89,'he yifan','heyifan123' ,3,?)";
			 ps=connection.prepareStatement(query2);  
			 ps.setDate(1, time);
			 ps.executeUpdate();
			 ps=connection.prepareStatement(query3);    
			 ps.setDate(1, time);
			 ps.executeUpdate();
			 System.out.println("update finished");
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 String username2,passkey2;
			 java.sql.Date last_login2;
			 int user_id2,role_id2;
			 while (rst2.next()) {
				  user_id2= rst2.getInt("user_id");
				  username2 = rst2.getString("username");
				  passkey2= rst2.getString("passkey");
				  role_id2=rst2.getInt("role_id");
				  last_login2=rst2.getDate("last_login");
		          System.out.println(user_id2+" "+username2+" "+passkey2+" "+role_id2+" "+last_login2);
		        }
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void dropUser(){
		 try {
			 st = connection.createStatement();
			 String query1="select * from USER_ACCOUNT";
			 ResultSet rst1 = st.executeQuery(query1);
			 String username1,passkey1;
			 java.sql.Date last_login1;
			 int user_id1,role_id1;
			 while (rst1.next()) {
				  user_id1= rst1.getInt("user_id");
				  username1 = rst1.getString("username");
				  passkey1= rst1.getString("passkey");
				  role_id1=rst1.getInt("role_id");
				  last_login1=rst1.getDate("last_login");
		          System.out.println(user_id1+" "+username1+" "+passkey1+" "+role_id1+" "+last_login1);
		        }
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//set the initial format of time stamp
	 		 java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime()); 
			 
			 String String="delete from USER_ACCOUNT where user_id=88";
			 ps=connection.prepareStatement(String);
           	 ps.executeUpdate();
           	 System.out.println("delete finished!");
           	 
           	 ResultSet rst2 = st.executeQuery(query1);
			 String username2,passkey2;
			 java.sql.Date last_login2;
			 int user_id2,role_id2;
			 while (rst2.next()) {
				  user_id2= rst2.getInt("user_id");
				  username2 = rst2.getString("username");
				  passkey2= rst2.getString("passkey");
				  role_id2=rst2.getInt("role_id");
				  last_login2=rst2.getDate("last_login");
		          System.out.println(user_id2+" "+username2+" "+passkey2+" "+role_id2+" "+last_login2);
		        }

			 ps.close();
			 st.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void createEvent(){   ///////////OK
	
		 try {
			 st = connection.createStatement();
			 String query1="select * from EVENT";
			 ResultSet rst1 = st.executeQuery(query1);
			 java.sql.Date event_time1;
			 int event_id1,sport_id1,venue_id1,gender1;
			 while (rst1.next()) {
				 event_id1= rst1.getInt("event_id");
				 sport_id1 = rst1.getInt("sport_id");
				 venue_id1= rst1.getInt("venue_id");
				 gender1=rst1.getInt("gender");
				 event_time1=rst1.getDate("event_time");
		          System.out.println(event_id1+" "+sport_id1+" "+venue_id1+" "+gender1+" "+event_time1);
		        }
			
			 String query = "select max(event_id) from EVENT";
			 ResultSet res1 = st.executeQuery(query);
			 res1.next();
			 int event_id = res1.getInt(1) + 1;
			 String String2="insert into EVENT values(?,1,1,2,to_date('10-JAN-20','dd-Mon-yy','NLS_DATE_LANGUAGE=AMERICAN'))";
			 ps=connection.prepareStatement(String2);  
			 ps.setInt(1,event_id);                    
			 ps.executeUpdate();
			 System.out.println("update finished!");
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 java.sql.Date event_time2;
			 int event_id2,sport_id2,venue_id2,gender2;
			 while (rst2.next()) {
				 event_id2= rst2.getInt("event_id");
				 sport_id2 = rst2.getInt("sport_id");
				 venue_id2= rst2.getInt("venue_id");
				 gender2=rst2.getInt("gender");
				 event_time2=rst2.getDate("event_time");
		          System.out.println(event_id2+" "+sport_id2+" "+venue_id2+" "+gender2+" "+event_time2);
		        }
			 
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void addEventOutcome(){    ///////Ok
		 //getConnection();
		 try {
			 st = connection.createStatement();
			 String query1="select * from SCOREBOARD";
			 ResultSet rst1 = st.executeQuery(query1);
			 int event_id1,olympic_id1,team_id1,participant_id1,position1,medal_id1;
			 while (rst1.next()) {
				 olympic_id1= rst1.getInt("olympic_id");
				 event_id1 = rst1.getInt("event_id");
				 team_id1= rst1.getInt("team_id");
				 participant_id1=rst1.getInt("participant_id");
				 position1=rst1.getInt("position");
				 medal_id1=rst1.getInt("medal_id");
		          System.out.println(olympic_id1+" "+ event_id1 +" "+team_id1+" "+participant_id1+" "+participant_id1+" "+ medal_id1);
		        }
			 
			 String String2="insert into SCOREBOARD values(1,1,1,13,2,2)";
			 ps=connection.prepareStatement(String2);
			 ps.executeUpdate();
			 System.out.println("update finished!");
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 int event_id2,olympic_id2,team_id2,participant_id2,position2,medal_id2;
			 while (rst2.next()) {
				 olympic_id2= rst2.getInt("olympic_id");
				 event_id2 = rst2.getInt("event_id");
				 team_id2= rst2.getInt("team_id");
				 participant_id2=rst2.getInt("participant_id");
				 position2=rst2.getInt("position");
				 medal_id2=rst2.getInt("medal_id");
		          System.out.println(olympic_id2+" "+ event_id2 +" "+team_id2+" "+participant_id2+" "+participant_id2+" "+ medal_id2);
			 }
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void createTeam(){  /////////////OK
		 //getConnection();
		 try {
			 st = connection.createStatement();
			 String query1="select * from TEAM";
			 ResultSet rst1 = st.executeQuery(query1);
			 int team_id1,olympic_id1,country_id1,sport_id1,coach_id1;
			 String team_name1;
			 while (rst1.next()) {
				 team_id1= rst1.getInt("team_id");
				 olympic_id1= rst1.getInt("olympic_id");
				 team_name1 = rst1.getString("team_name");
				 country_id1=rst1.getInt("country_id");
				 sport_id1=rst1.getInt("sport_id");
				 coach_id1=rst1.getInt("coach_id");
		          System.out.println(team_id1+" "+olympic_id1+" "+ team_name1 +" "+country_id1+" "+sport_id1+" "+ coach_id1);
		        }
			 
			 String query = "select max(team_id) from TEAM";
			 ResultSet res3 = st.executeQuery(query);
			 res3.next();
			 int team_id = res3.getInt(1) + 1;
			 String String3="insert into TEAM values(?,1,'duyifan',1,1,76)";
			 ps=connection.prepareStatement(String3);
			 ps.setInt(1, team_id);
			 ps.executeUpdate();
			 System.out.println("update finished");
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 int team_id2,olympic_id2,country_id2,sport_id2,coach_id2;
			 String team_name2;
			 while (rst2.next()) {
				 team_id2= rst2.getInt("team_id");
				 olympic_id2= rst2.getInt("olympic_id");
				 team_name2 = rst2.getString("team_name");
				 country_id2=rst2.getInt("country_id");
				 sport_id2=rst2.getInt("sport_id");
				 coach_id2=rst2.getInt("coach_id");
		          System.out.println(team_id2+" "+olympic_id2+" "+ team_name2 +" "+country_id2+" "+sport_id2+" "+ coach_id2);
		        }
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void registerTeam(){    //////////////OK
		 //getConnection();
		 try {
			 Statement st = connection.createStatement();
			 String query1="select * from EVENT_PARTICIPATION";
			 ResultSet rst1 = st.executeQuery(query1);
			 int team_id1,event_id1;
			 String status1;
			 while (rst1.next()) {
				 event_id1= rst1.getInt("event_id");
				 team_id1= rst1.getInt("team_id");
				 status1= rst1.getString("status");
				 
		          System.out.println(event_id1+" "+team_id1+" "+ status1);
		        }
			 
			 String String2="insert into EVENT_PARTICIPATION values(1,100,'e')";
			 ps=connection.prepareStatement(String2);  
			 ps.executeUpdate();
			 System.out.println("update finished");
			 
	
			 ResultSet rst2 = st.executeQuery(query1);
			 int team_id2,event_id2;
			 String status2;
			 while (rst2.next()) {
				 event_id2= rst2.getInt("event_id");
				 team_id2= rst2.getInt("team_id");
				 status2= rst2.getString("status");
				 
		          System.out.println(event_id2+" "+team_id2+" "+ status2);
		        }
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void addParticipant(){    ///////OK
		 //getConnection();
		 try {
			 Statement st = connection.createStatement();
			 String query1="select * from PARTICIPANT";
			 ResultSet rst1 = st.executeQuery(query1);
			 int participant_id1;
			 java.sql.Date dob1;
			 String fname1,lname1,nationality1,birthplace1;
			 while (rst1.next()) {
				 participant_id1= rst1.getInt("participant_id");
				 fname1= rst1.getString("fname");
				 lname1 = rst1.getString("lname");
				 nationality1=rst1.getString("nationality");
				 birthplace1=rst1.getString("birth_place");
				 dob1=rst1.getDate("dob");
		          System.out.println(participant_id1+" "+fname1+" "+ lname1 +" "+nationality1+" "+birthplace1+" "+ dob1);
		        }
			 

			 String String="insert into PARTICIPANT values(88,'du','yifan','China','Hefei',to_date('16-JUL-97','dd-Mon-yy','NLS_DATE_LANGUAGE=AMERICAN'))";
			 ps=connection.prepareStatement(String);  
	         ps.executeUpdate();
	         String String2="insert into PARTICIPANT values(89,'he','yifan','Russia','Wuhu',to_date('16-JUL-97','dd-Mon-yy','NLS_DATE_LANGUAGE=AMERICAN'))";
			 ps=connection.prepareStatement(String2);  
	         ps.executeUpdate();
			 System.out.println("update finished");
			 
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 int participant_id2;
			 java.sql.Date dob2;
			 String fname2,lname2,nationality2,birthplace2;
			 while (rst2.next()) {
				 participant_id2= rst2.getInt("participant_id");
				 fname2= rst2.getString("fname");
				 lname2 = rst2.getString("lname");
				 nationality2=rst2.getString("nationality");
				 birthplace2=rst2.getString("birth_place");
				 dob2=rst2.getDate("dob");
		          System.out.println(participant_id2+" "+fname2+" "+ lname2+" "+nationality2+" "+birthplace2+" "+ dob2);
		        }
			 st.close();
			 ps.close();
		
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 	
	 public static void addTeamMember(){     /////OK
		 //getConnection();
		 try {
			 Statement st = connection.createStatement();
			 String query1="select * from TEAM_MEMBER";
			 ResultSet rst1 = st.executeQuery(query1);
			 int team_id1,participant_id1;
			 while (rst1.next()) {
				 team_id1= rst1.getInt("team_id");
				 participant_id1= rst1.getInt("participant_id");
		          System.out.println(team_id1+" "+participant_id1);
		        }
			 
			 String String="insert into TEAM_MEMBER values(1,89)";
			 ps=connection.prepareStatement(String);  
			 ps.executeUpdate();
			 System.out.println("update finished");
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 int team_id2,participant_id2;
			 while (rst2.next()) {
				 team_id2= rst2.getInt("team_id");
				 participant_id2= rst2.getInt("participant_id");
		          System.out.println(team_id2+" "+participant_id2);
		        }
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void dropTeamMember(){
		 //getConnection();
		 try {
			 Statement st = connection.createStatement();
			 String query1="select * from TEAM_MEMBER";
			 ResultSet rst1 = st.executeQuery(query1);
			 int team_id1,participant_id1;
			 while (rst1.next()) {
				 team_id1= rst1.getInt("team_id");
				 participant_id1= rst1.getInt("participant_id");
		          System.out.println(team_id1+" "+participant_id1);
		        }
			 
			 String String="delete from USER_ACCOUNT where user_id=89 and role_id=3";
			 ps=connection.prepareStatement(String);
			 ps.executeUpdate();
			 System.out.println("update finished");
			 
			 ResultSet rst2 = st.executeQuery(query1);
			 int team_id2,participant_id2;
			 while (rst2.next()) {
				 team_id2= rst2.getInt("team_id");
				 participant_id2= rst2.getInt("participant_id");
		          System.out.println(team_id2+" "+participant_id2);
		        }
			 
			 st.close();
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
	 
	 public static void displaySport(){   /////////////OK
		 //getConnection();
		 try {
			
			 String String="select distinct esp.participant_id ,st.dob,esp.event_id,esp.gender ,esp.nationality\r\n" + 
			 		"from SPORT st join\r\n" + 
			 		"(\r\n" + 
			 		"select e.event_id,e.sport_id,e.gender,sp.participant_id,sp.nationality,sp.medal_id,e.event_time\r\n" + 
			 		"from EVENT e join(\r\n" + 
			 		"select s.event_id,s.participant_id,p.nationality,s.medal_id\r\n" + 
			 		"from  SCOREBOARD s join PARTICIPANT p on s.PARTICIPANT_ID = p.PARTICIPANT_ID\r\n" + 
			 		"where medal_id is not null\r\n" + 
			 		"order by medal_id asc) sp on e.event_id= sp.event_id order by event_time asc) esp on st.sport_id=esp.sport_id\r\n" + 
			 		"where st.sport_name='3m men diving'";
			 ps=connection.prepareStatement(String);
			 ResultSet rst = ps.executeQuery();
			 int revent_id,rparticipant_id,rgender;
			 String rdob,rnationality;
			 while (rst.next()) {
		            rdob = rst.getString("dob");
		            revent_id = rst.getInt("event_id");
		            rgender = rst.getInt("gender");
		            rparticipant_id = rst.getInt("participant_id");
		            rnationality = rst.getString("nationality");
		            System.out.println(rdob+ " " + revent_id+ " " + rgender+ " " +rparticipant_id+ " " +rnationality);
		        }

			 ps.close();
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
	 public static void displayEvent(){    ////////OK
		 //getConnection();
		 try {
			 String String="select s.olympic_id,s.participant_id,s.position,s.MEDAL_ID\r\n" + 
			 		"from SCOREBOARD s join (\r\n" + 
			 		"select OLYMPIC_ID from OLYMPICS where host_city='Beijing') o on o.OLYMPIC_ID=s.OLYMPIC_ID\r\n" + 
			 		"where event_id=6 and medal_id is not null";
			 ps=connection.prepareStatement(String);
			 ResultSet rst = ps.executeQuery();
			 int rolympic_id,rparticipant_id,rposition,rmedal_id;
			 while (rst.next()) {
				  rolympic_id= rst.getInt("olympic_id");
				  rparticipant_id = rst.getInt("participant_id");
				  rposition= rst.getInt("position");
				  rmedal_id = rst.getInt("medal_id");
				  //System.out.println("olympic_id:"+ " " + "participant_id"+ " " + "position"+ " " +"medal_id");
		          System.out.println("olympic_id:"+rolympic_id+ " " +"participant_id:"+ rparticipant_id+ " " + "position:"+rposition+ " " +"medal_id:"+rmedal_id);
		        }
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void countryRanking(){  /////////////OK
		 //getConnection();
		 try {

			 String String="select smeco.country_code, sum(smeco.points) as points,min(smeco.opening) as opening,\r\n" + 
			 		"(select count(country_code) from ( select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 1 and smeco1.Olympic_id=1)gold ,\r\n" + 
			 		"(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 2 and smeco1.Olympic_id=1)silver,\r\n" + 
			 		"(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 3 and smeco1.Olympic_id=1)bronze\r\n" + 
			 		"from(select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join\r\n" + 
			 		"(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID\r\n" + 
			 		"from COUNTRY c join\r\n" + 
			 		"    (select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points\r\n" + 
			 		"from TEAM e join\r\n" + 
			 		"(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco\r\n" + 
			 		"where smeco.Olympic_id=1\r\n" + 
			 		"group by smeco.country_code\r\n" + 
			 		"order by points desc";           /////////////
			 ps=connection.prepareStatement(String);
			 ResultSet rst = ps.executeQuery();
			 String rcountry_abbreviation,ropening;
			 int rpoints,rgold,rsilver,rbronze;
			 while (rst.next()) {
				  rcountry_abbreviation= rst.getString("country_code");
				  ropening= rst.getString("opening");
				  rpoints = rst.getInt("points");
				  rgold= rst.getInt("gold");
				  rsilver = rst.getInt("silver");
				  rbronze = rst.getInt("bronze");
		          System.out.println( rcountry_abbreviation+ " "  +rpoints + " "+ropening+  " " + rgold+ " " +rsilver+ " " +rbronze);
		        }
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void topkAthletes(){  ////////////OK
		 //getConnection();
		 try {			 
			 String String="select sm.participant_id,sum(sm.points) as points,\r\n" + 
			 		"(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 1)gold ,\r\n" + 
			 		"(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 2)silver,\r\n" + 
			 		"(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 3)bronze\r\n" + 
			 		"from  ( select * from SCOREBOARD natural join MEDAL) sm\r\n" + 
			 		"where OLYMPIC_ID=1 \r\n" + 
			 		"group by sm.participant_id\r\n" + 
			 		"order by points desc\r\n" + 
			 		"fetch first 10 rows only";           /////////////
			 ps=connection.prepareStatement(String);
			 ResultSet rst = ps.executeQuery();
			 int rparticipant_id,rpoints,rgold,rsilver,rbronze;
			 while (rst.next()) {
				  rparticipant_id= rst.getInt("participant_id");
				  rpoints = rst.getInt("points");
				  rgold= rst.getInt("gold");
				  rsilver = rst.getInt("silver");
				  rbronze = rst.getInt("bronze");
		          System.out.println( "participant_id:"+rparticipant_id+ " " +"points:"+ rpoints+ " " + "gold:"+rgold+ " " +"silver:"+rsilver+ " " +"bronze:"+rbronze);
		        }
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void connectedAthletes(){
		 //getConnection();
		 try {
			 String String="select distinct po1.participant_id from\r\n" + 
			 		"(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id not in\r\n" + 
			 		"(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id != 44 and s.event_id in\r\n" + 
			 		"(select event_id from SCOREBOARD s where participant_id = 44 and olympic_id = 2))and s.event_id in\r\n" + 
			 		"(select event_id from SCOREBOARD s where participant_id in\r\n" + 
			 		"(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id != 44 and s.event_id in\r\n" + 
			 		"(select event_id from SCOREBOARD s where participant_id = 44 and olympic_id = 2)) and olympic_id = 1))po1 cross join\r\n" + 
			 		"(select participant_id from SCOREBOARD where participant_id = 44 and olympic_id = 2)po2"; 
			
			 ps=connection.prepareStatement(String);
			 ResultSet rst = ps.executeQuery();
			 int rparticipant_id;
			 while (rst.next()) {
				  rparticipant_id= rst.getInt("participant_id");
		          System.out.println( rparticipant_id);
		        }
			 ps.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
 
	 public static int login() throws SQLException {

		   System.out.println("username = ");
		   String username = j.nextLine();
		   System.out.println("password = ");
		   String password = j.nextLine();
		   
		   Statement st = connection.createStatement();
		   String query1 = "select username,passkey,role_id from user_account";
		   ResultSet res1 = st.executeQuery(query1);
		         
		   int rol ;
		   String rid,rpa;
	
		   while(res1.next()) {
		     rid = res1.getString(1);
		     rpa = res1.getString(2);
		     rol = res1.getInt(3);
		     if(rid.equals(username) && rpa.equals(password)) {
		       return(rol);
                  }}
				 return (-1);}

	 	public static void logout() throws SQLException, ParseException{
	 		   System.out.println("your user_id = ");
			   int user_id = j.nextInt();
	 		   SimpleDateFormat df = new SimpleDateFormat("yyyy-mmm-dd HH:mm:ss");//set the initial format of time stamp
	 		   java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime());
	 		   
	 		   String String= "update USER_ACCOUNT set last_login= ? where user_id=5";
	 		   ps=connection.prepareStatement(String);
			   ps.setDate(1,time);
			   ps.setInt(2,user_id);
			   ps.executeQuery();
			   ps.close();
	 		   
	 	}
	 	
	 	
	 public static void exit() throws SQLException{
	 		   j.close();
	 		   System.exit(0);   
	 		}
	 
	 
	 public static void main(String args[]) throws SQLException, ParseException {
		getConnection();
		System.out.println("position(username,password)....ex:Organizer(Hu Jintao,Beijng),Coach(coach 1,COACH),All(Peng Bo,GUEST)");
		int a=login();
		if(a==1) {
			System.out.println("1:createUser"+" "+"2:dropUser"+" "+"3:createEvent"+" "+"4:addEventOutcome"+" "+"5:displaySport"+" "+"6:displayEvent"+" "+"7:countryRanking"+" "+"8:topkAthletes"+" "+"9:connectedAthletes"+" "+"10:logout"+" "+"11:exit");
			//Scanner j = new Scanner(System.in);
			System.out.println("what do you want to do ?  please input the number: ");
			int Org = j.nextInt();
			while(Org>0 && Org<12) {
		    	 switch(Org){
		    	 case 1: createUser();break;
		    	 case 2:dropUser();break;
		    	 case 3: createEvent();break;
		    	 case 4: addEventOutcome();break;
		    	 case 5: displaySport();break;
		    	 case 6: displayEvent();break;
		    	 case 7: countryRanking();break;
		    	 case 8: topkAthletes();break;
		    	 case 9: connectedAthletes();break;
		    	 case 10: logout();break;
		    	 case 11: exit();break;
		    	 }
		     System.out.println("please input number again:");
		     Org=j.nextInt();
		     }}
		else if(a==2) {
			System.out.println("1:createTeam"+" "+"2:registerTeam"+" "+"3:addParticipant"+" "+"4:addTeamMember"+" "+"5:dropTeamMember"+" "+"6:displaySport"+" "+"7:displayEvent"+" "+"8:countryRanking"+" "+"9:topkAthletes"+" "+"10:connectedAthletes"+" "+"11:logout"+" "+"12:exit");
			System.out.println("what do you want to do ?  please input the number: ");
			int Coa = j.nextInt();
			while(Coa>0 && Coa<13) {
		    	 switch(Coa){
		    	 case 1: createTeam();break;
		    	 case 2:registerTeam();break;
		    	 case 3: addParticipant();break;
		    	 case 4: addTeamMember();break;
		    	 case 5: dropTeamMember();break;
		    	 case 6: displaySport();break;
		    	 case 7: displayEvent();break;
		    	 case 8: countryRanking();break;
		    	 case 9: topkAthletes();break;
		    	 case 10: connectedAthletes();break;
		    	 case 11: logout();break;
		    	 case 12: exit();break;
		    	 }
		     System.out.println("please input number again:");
		     Coa=j.nextInt();
		     }}
		else if(a==3) {
			System.out.println("1:displaySport"+" "+"2:displayEvent"+" "+"3:countryRanking"+" "+"4:topkAthletes"+" "+"5:connectedAthletes"+" "+"6:logout"+" "+"7:exit");
			System.out.println("what do you want to do ?  please input the number: ");
			int All= j.nextInt();
			while(All>0 && All<8) {
		    	 switch(All){
		    	 case 1: displaySport();break;
		    	 case 2: displayEvent();break;
		    	 case 3: countryRanking();break;
		    	 case 4: topkAthletes();break;
		    	 case 5: connectedAthletes();break;
		    	 case 6: logout();break;
		    	 case 7: exit();break;
		    	 }
		     System.out.println("please input number again:");
		     All=j.nextInt();
		     }
	 }
		connection.close();
	
	 }

}
