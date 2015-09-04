package test.geoip;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.junit.Test;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

public class GeoIpTest {

	@Test
	public void testCity() {
		File database = new File("C:\\workspace\\geoip\\GeoLite2-City.mmdb");
		System.out.println(database);
		
		try {
			DatabaseReader reader = new DatabaseReader.Builder(database).build();
			InetAddress ipAddress = InetAddress.getByName("183.111.148.39");
			
			CityResponse response = reader.city(ipAddress);
			Country country = response.getCountry();
			System.out.println(country.getIsoCode());
			System.out.println(country.getName());
			System.out.println(country.getNames().toString());
			
			Subdivision subdivision = response.getMostSpecificSubdivision();
			System.out.println(subdivision.getName());
			System.out.println(subdivision.getIsoCode());
			
			City city = response.getCity();
			System.out.println(city.getName());
			
			Postal postal = response.getPostal();
			System.out.println(postal.getCode());
			
			Location location = response.getLocation();
			System.out.println(location.getLatitude());
			System.out.println(location.getLongitude());
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
