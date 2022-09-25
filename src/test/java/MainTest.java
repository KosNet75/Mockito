import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.geo.GeoServiceImplMock;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.i18n.LocalizationServiceImplMock;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;
//import org.junit.Test;


public class MainTest {



@DisplayName("проверка, английский независимо от ip")
@Test


  public void test() {
  GeoService geoService = new GeoServiceImplMock();
  LocalizationService localizationService = new LocalizationServiceImpl();
  MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
  Map<String, String> headers = new HashMap<String, String>();
  headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
  Assertions.assertEquals(messageSender.send(headers), "Welcome");
  }

  @DisplayName("проверка, не русский (независимо от ip)")
  @Test

  public void test1() {
    GeoService geoService = new GeoServiceImplMock();
    LocalizationService localizationService = new LocalizationServiceImpl();
    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<String, String>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
    Assertions.assertNotEquals(messageSender.send(headers), "Добро пожаловать");

  }

  @DisplayName("проверка, на простую выдачу любого сообщения")
  @Test

  public void test2() {

    GeoService geoService = new GeoServiceImplMock();
    LocalizationService localizationService = new LocalizationServiceImplMock();
    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
    Map<String, String> headers = new HashMap<String, String>();
    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
    Assertions.assertEquals(messageSender.send(headers), "Хаюшки!");

  }


  @DisplayName("проверки определения локации RUSSIA по ip")
  @Test

  public void test3() {

    GeoService geoService = new GeoServiceImpl();
    String ipAddress = "172.123.12.19";
    Location location = geoService.byIp(ipAddress);
     String country = String.valueOf(location.getCountry());
    Assertions.assertEquals(country, "RUSSIA");
    System.out.println(country + " = " + "RUSSIA");

  }

  @DisplayName("проверки определения иной локации по ip")
  @Test

  public void test4() {

    GeoService geoService = new GeoServiceImpl();
    String ipAddress = "96.123.12.19";
    Location location = geoService.byIp(ipAddress);
    String country = String.valueOf(location.getCountry());
    Assertions.assertNotEquals(country, "RUSSIA");
    System.out.println(country + " != " + "RUSSIA");
  }


  @DisplayName("проверки определения локации локальный ip")
  @Test

  public void test5() {

    GeoService geoService = new GeoServiceImpl();
    String ipAddress = "127.0.0.1";
    Location location = geoService.byIp(ipAddress);
    String country = String.valueOf(location.getCountry());
   // System.out.println(String.valueOf(location.getCountry()));
    Assertions.assertEquals(country, "null");
    System.out.println(country + " = " + "null");
  }

}
