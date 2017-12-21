package jshan.temp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import jshan.library.connector.MySqlConnector;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShopDataWithMysql {
	
	/**
	 * 1. 상품 조회
	 * 2. 광고주로 캠페인 조회 
	 * @method main
	 * @see
	 * @param args void
	 */
	public static void main(String[] args){
		String fileName = "c:\\shopData3.txt";
		Connection conn1 = MySqlConnector.getConnection(); // 58
		Connection conn2 = MySqlConnector.getConnection2();// 54
		String[] userIds = new String[]{"latexnara","lazybee1","lbj11111","leebrenda","lejour","lenakim89","lexpayh","lhp2448","lhp24482","lighting1","littlemom","livart","ljh004414","ljh1","lmonium","lohacell","lolstreet","lookchic","looktique","lotteimall12","lottetours","lovehany","lsh1987","lsnmall","lsycol","lucover","luire00","lusida33","luxty","luzzibag","lylon01","madamboutique","madamebree","maderako","magnumkorea","mallstory","mamaschoice","mandri88","marantblue","mariswell","marlang","maryru","mastertm","mastideco","mays2016","mbaby","mcslux","md45678","mdmadame","medicean22","medicube","medience","megacoffee","megamassmart","megg","mellowtique","meosidda","merbliss","messe090909","mgdj","mgis","mhmhmom","michaa","midari76","midasb","mideun3665","mifema","milkable","milkydress","minagram","mindpharm","minibbong","minuet","minuherb","mipworld","miracletime","mism","miss21","misscandy","misslucyheel","missmigo","mitopia","mitoshop","miumiukids","mizitinstyle","mizmin","mjcong1","mmarket","mnhouse","moababa","mobel","molding1","mommymaru","monacogirls","monica2012","monoglot","moonbanggu","moonu","mosmoz","mrct0684","mrstreet","mugoong","mun1000","mustar98","mvmall","myclassy","mycouture","mydrqu","myid332","mysweetred","n2nfurniture","na92na92","nabbo","najeye","nakeupface","naktastyle","namasite","namecarz","nanajean","nanennaya","narostar","naturence","natureshop","nazenashoes","ndsoft123","neopharm","newisaplin","nh172668","nice","ninanoblock","ninenfour","nj04193","nlife07","noadeco","noble3450","nomad2","northface","nubelle","nubizio14","nuc1004","number22","nuny78","nutricore","nways","oabito","ocbbal","oesangmall","ohdog77","ohsman0","ohy2247","okidogki","okoss","olbaro","olssen","omedi","omegasun","onepeck","onlybijou","onoffeng","ontheriver","openhan","opening","optimus425","orasion77","oroche","osirang75","ota9","ottaku","pacc1","paperplanes","park6594","partner","partysu","pbcosmetic","peniking","peper","peripera","petaid","petitrang","petoutlet","petri","phc0128","pinpop","pippin11","pky031","pkyul","planets","player","ponyzn","popl","posha","primo","private100","proslab","publiclife","pumakorea","purenz","purplestory","qkrrhkd5","queenpuppy","queenspider","quick4885","rabbitboy","rabyola","rangpang","raontheday","realarticle","realskinkr","redgidrl","redhomme","redopin","reskin","retoffe","rgd256","rhfemtmxk12","rhkdduf1","richwatch","riena","rkdgodah","rkdgusrn575","rlagnsxo","rlagusgh1","rnekwls88","rocosix","romanson102","rorochat","rudiastory","runo","rurugold","sachainch","sainter75","saintmary","sajomall","samsamo","saongwon1","sarangopda","sareureu","sasakid","scentavenue","sderella","secret02","seoulgirls","seven710","sezwick2","shaen","sharmont1","shebeach","shedeell","shesmadam","shespop","shezbycoco","shoemaru","shoesmong1","show114com","shuzqueen","shygirlj8083","sierocosmetic","silktherapy","skiinny","skinmiso","skintalk","slim19","smallman","snaple","snipershop","soccernet","soim","solarguy","solnrock","soltmkt","somedayif","sonamu","sonatural","songarak007","soofee","soomuslin","soulmate0696","spash1","spdi","spocom","spris","spyderkorea","ssamoi32","sschojaec","ssfshop","ssgcom","ssgdfm","ssgdfs","ssgmall","ssomuch1118","ssongbyssong01","ssonyunara","ssosseg","ssumenam","ssunny","stares76","starpharm","storebom","styhome","styleberry"};
		PreparedStatement ps = null;
		long no = 0;
		String pcode, url, pnm = null, price, imgpath = null, purl, sitecode = "";
		String slink = "";
		JSONObject json = null;
		JSONArray jarray = null;
		File file = new File(fileName);
		FileWriter fw = null;
		try {
		  fw = new FileWriter(file, true);
		  for (String userid : userIds) {
		    ps = conn2.prepareStatement("select * from adsite a inner join adsite_mobile b on a.site_code = b.site_code inner join adlink c on a.site_code = c.site_code where a.userid = ? and gubun = 'ST' and b.state = 'Y' and c.script_no = 10167 and adyn = 'Y'");
		    ps.setString(1, userid);
		    ResultSet rs2 =  ps.executeQuery();
		    sitecode = "";
		    if(rs2.next())
		      sitecode = rs2.getString("site_code");
		    
		    System.out.println("userid>>>>"+userid);
        ps = conn1.prepareStatement("select * from MOB_SHOP_DATA where userid = ? limit 40000");
        ps.setString(1, userid);
        ResultSet rs =  ps.executeQuery();
		    boolean isExists = false;
		    jarray = new JSONArray();
		    json = new JSONObject();
		    while (rs.next()) {
		      isExists = true;
          no = rs.getLong("NO");
          pcode = rs.getString("PCODE");
          url = rs.getString("url");
          pnm = URLEncoder.encode(rs.getString("PNM"),"utf-8");
          price = rs.getString("PRICE");
          imgpath = URLEncoder.encode(rs.getString("IMGPATH"),"utf-8");
          purl = URLEncoder.encode(rs.getString("purl"),"utf-8");
          
          JSONObject jobj = new JSONObject();
          jobj.put("p_img", imgpath);
          jobj.put("p_name", pnm );
          jobj.put("p_price", price );
          jobj.put("p_link", purl );
          jobj.put("p_code", pcode);
          jobj.put("user_id", userid);
          jobj.put("no", no);
          if(StringUtils.isNotEmpty(sitecode)){
            slink = " http://www.dreamsearch.or.kr/servlet/drc?no="+no+"&kno="+no+"&s=10167&adgubun=ST&gb=ST&sc="+sitecode+"&mc=10167&userid="+userid+"&u="+userid+"&product=mbw&slink="+purl+"&pCode="+pcode+"&mobonlinkcate=none&viewTime=NO_IP_10167_1493020040344_4438&hId=4";
            jobj.put("site_code", sitecode);
            jobj.put("drc_link", slink);
          }else{
            jobj.put("site_code", "");
            jobj.put("drc_link", "");
          }
          jobj.put("state", StringUtils.isEmpty(sitecode) ? "N" : "Y" );
          jarray.add(jobj);
        }
		    if(isExists){
		      json.put(userid, jarray);
		      
		      
		      
		      fw.write(json.toString());
		      
		    }
      }
		  
		  // 파일안에 문자열 쓰기
		  System.out.println("END!!!");
		}
		catch (Exception e) {
		  
			e.printStackTrace();
		}
		finally{
		  if(fw != null )
        try {
          fw.close();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
			try {
			  conn1.close();
			  conn2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		PreparedStatement ps = conn.prepareStatement("");
	}
}
